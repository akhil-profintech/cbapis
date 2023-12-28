package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.Context;
import in.pft.apis.creditbazaar.repository.rowmapper.ActionRowMapper;
import in.pft.apis.creditbazaar.repository.rowmapper.ContextRowMapper;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoinCondition;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC custom repository implementation for the Context entity.
 */
@SuppressWarnings("unused")
class ContextRepositoryInternalImpl extends SimpleR2dbcRepository<Context, Long> implements ContextRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final ActionRowMapper actionMapper;
    private final ContextRowMapper contextMapper;

    private static final Table entityTable = Table.aliased("context", EntityManager.ENTITY_ALIAS);
    private static final Table actionTable = Table.aliased("action", "action");

    public ContextRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        ActionRowMapper actionMapper,
        ContextRowMapper contextMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Context.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.actionMapper = actionMapper;
        this.contextMapper = contextMapper;
    }

    @Override
    public Flux<Context> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Context> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = ContextSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(ActionSqlHelper.getColumns(actionTable, "action"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(actionTable)
            .on(Column.create("action_id", entityTable))
            .equals(Column.create("id", actionTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Context.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Context> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Context> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<Context> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<Context> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<Context> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private Context process(Row row, RowMetadata metadata) {
        Context entity = contextMapper.apply(row, "e");
        entity.setAction(actionMapper.apply(row, "action"));
        return entity;
    }

    @Override
    public <S extends Context> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
