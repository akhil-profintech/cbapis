package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.FundsTransfer;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.FundsTransferRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.TradeEntityRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the FundsTransfer entity.
 */
@SuppressWarnings("unused")
class FundsTransferRepositoryInternalImpl extends SimpleR2dbcRepository<FundsTransfer, Long> implements FundsTransferRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final TradeEntityRowMapper tradeentityMapper;
    private final FundsTransferRowMapper fundstransferMapper;

    private static final Table entityTable = Table.aliased("funds_transfer", EntityManager.ENTITY_ALIAS);
    private static final Table tradeEntityTable = Table.aliased("trade_entity", "tradeEntity");

    public FundsTransferRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        TradeEntityRowMapper tradeentityMapper,
        FundsTransferRowMapper fundstransferMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(FundsTransfer.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.tradeentityMapper = tradeentityMapper;
        this.fundstransferMapper = fundstransferMapper;
    }

    @Override
    public Flux<FundsTransfer> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<FundsTransfer> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = FundsTransferSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(TradeEntitySqlHelper.getColumns(tradeEntityTable, "tradeEntity"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(tradeEntityTable)
            .on(Column.create("trade_entity_id", entityTable))
            .equals(Column.create("id", tradeEntityTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, FundsTransfer.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<FundsTransfer> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<FundsTransfer> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<FundsTransfer> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<FundsTransfer> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<FundsTransfer> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private FundsTransfer process(Row row, RowMetadata metadata) {
        FundsTransfer entity = fundstransferMapper.apply(row, "e");
        entity.setTradeEntity(tradeentityMapper.apply(row, "tradeEntity"));
        return entity;
    }

    @Override
    public <S extends FundsTransfer> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
