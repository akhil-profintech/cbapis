package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.UpdateVA;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.TradeEntityRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.UpdateVARowMapper;
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
 * Spring Data R2DBC custom repository implementation for the UpdateVA entity.
 */
@SuppressWarnings("unused")
class UpdateVARepositoryInternalImpl extends SimpleR2dbcRepository<UpdateVA, Long> implements UpdateVARepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final TradeEntityRowMapper tradeentityMapper;
    private final UpdateVARowMapper updatevaMapper;

    private static final Table entityTable = Table.aliased("update_va", EntityManager.ENTITY_ALIAS);
    private static final Table tradeEntityTable = Table.aliased("trade_entity", "tradeEntity");

    public UpdateVARepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        TradeEntityRowMapper tradeentityMapper,
        UpdateVARowMapper updatevaMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(UpdateVA.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.tradeentityMapper = tradeentityMapper;
        this.updatevaMapper = updatevaMapper;
    }

    @Override
    public Flux<UpdateVA> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<UpdateVA> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = UpdateVASqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(TradeEntitySqlHelper.getColumns(tradeEntityTable, "tradeEntity"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(tradeEntityTable)
            .on(Column.create("trade_entity_id", entityTable))
            .equals(Column.create("id", tradeEntityTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, UpdateVA.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<UpdateVA> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<UpdateVA> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<UpdateVA> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<UpdateVA> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<UpdateVA> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private UpdateVA process(Row row, RowMetadata metadata) {
        UpdateVA entity = updatevaMapper.apply(row, "e");
        entity.setTradeEntity(tradeentityMapper.apply(row, "tradeEntity"));
        return entity;
    }

    @Override
    public <S extends UpdateVA> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
