package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.VANumber;
import in.pft.apis.creditbazaar.repository.rowmapper.TradeEntityRowMapper;
import in.pft.apis.creditbazaar.repository.rowmapper.VANumberRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the VANumber entity.
 */
@SuppressWarnings("unused")
class VANumberRepositoryInternalImpl extends SimpleR2dbcRepository<VANumber, Long> implements VANumberRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final TradeEntityRowMapper tradeentityMapper;
    private final VANumberRowMapper vanumberMapper;

    private static final Table entityTable = Table.aliased("va_number", EntityManager.ENTITY_ALIAS);
    private static final Table tradeEntityTable = Table.aliased("trade_entity", "tradeEntity");

    public VANumberRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        TradeEntityRowMapper tradeentityMapper,
        VANumberRowMapper vanumberMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(VANumber.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.tradeentityMapper = tradeentityMapper;
        this.vanumberMapper = vanumberMapper;
    }

    @Override
    public Flux<VANumber> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<VANumber> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = VANumberSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(TradeEntitySqlHelper.getColumns(tradeEntityTable, "tradeEntity"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(tradeEntityTable)
            .on(Column.create("trade_entity_id", entityTable))
            .equals(Column.create("id", tradeEntityTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, VANumber.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<VANumber> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<VANumber> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<VANumber> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<VANumber> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<VANumber> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private VANumber process(Row row, RowMetadata metadata) {
        VANumber entity = vanumberMapper.apply(row, "e");
        entity.setTradeEntity(tradeentityMapper.apply(row, "tradeEntity"));
        return entity;
    }

    @Override
    public <S extends VANumber> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
