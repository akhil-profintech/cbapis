package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.InstaAlert;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.InstaAlertRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.TradeEntityRowMapper;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.core.sql.*;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoinCondition;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Spring Data R2DBC custom repository implementation for the InstaAlert entity.
 */
@SuppressWarnings("unused")
class InstaAlertRepositoryInternalImpl extends SimpleR2dbcRepository<InstaAlert, Long> implements InstaAlertRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final TradeEntityRowMapper tradeentityMapper;
    private final InstaAlertRowMapper instaalertMapper;

    private static final Table entityTable = Table.aliased("insta_alert", EntityManager.ENTITY_ALIAS);
    private static final Table tradeEntityTable = Table.aliased("trade_entity", "tradeEntity");

    public InstaAlertRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        TradeEntityRowMapper tradeentityMapper,
        InstaAlertRowMapper instaalertMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(InstaAlert.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.tradeentityMapper = tradeentityMapper;
        this.instaalertMapper = instaalertMapper;
    }

    @Override
    public Flux<InstaAlert> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<InstaAlert> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = InstaAlertSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(TradeEntitySqlHelper.getColumns(tradeEntityTable, "tradeEntity"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(tradeEntityTable)
            .on(Column.create("trade_entity_id", entityTable))
            .equals(Column.create("id", tradeEntityTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, InstaAlert.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<InstaAlert> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<InstaAlert> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<InstaAlert> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<InstaAlert> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<InstaAlert> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private InstaAlert process(Row row, RowMetadata metadata) {
        InstaAlert entity = instaalertMapper.apply(row, "e");
        entity.setTradeEntity(tradeentityMapper.apply(row, "tradeEntity"));
        return entity;
    }

    @Override
    public <S extends InstaAlert> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
