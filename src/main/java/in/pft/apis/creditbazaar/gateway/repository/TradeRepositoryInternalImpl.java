package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.Trade;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.AnchorTraderRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.FinanceRequestRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.TradePartnerRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.TradeRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the Trade entity.
 */
@SuppressWarnings("unused")
class TradeRepositoryInternalImpl extends SimpleR2dbcRepository<Trade, Long> implements TradeRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final FinanceRequestRowMapper financerequestMapper;
    private final AnchorTraderRowMapper anchortraderMapper;
    private final TradePartnerRowMapper tradepartnerMapper;
    private final TradeRowMapper tradeMapper;

    private static final Table entityTable = Table.aliased("trade", EntityManager.ENTITY_ALIAS);
    private static final Table financerequestTable = Table.aliased("finance_request", "financerequest");
    private static final Table anchortraderTable = Table.aliased("anchor_trader", "anchortrader");
    private static final Table tradepartnerTable = Table.aliased("trade_partner", "tradepartner");

    public TradeRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        FinanceRequestRowMapper financerequestMapper,
        AnchorTraderRowMapper anchortraderMapper,
        TradePartnerRowMapper tradepartnerMapper,
        TradeRowMapper tradeMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Trade.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.financerequestMapper = financerequestMapper;
        this.anchortraderMapper = anchortraderMapper;
        this.tradepartnerMapper = tradepartnerMapper;
        this.tradeMapper = tradeMapper;
    }

    @Override
    public Flux<Trade> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Trade> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = TradeSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(FinanceRequestSqlHelper.getColumns(financerequestTable, "financerequest"));
        columns.addAll(AnchorTraderSqlHelper.getColumns(anchortraderTable, "anchortrader"));
        columns.addAll(TradePartnerSqlHelper.getColumns(tradepartnerTable, "tradepartner"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(financerequestTable)
            .on(Column.create("financerequest_id", entityTable))
            .equals(Column.create("id", financerequestTable))
            .leftOuterJoin(anchortraderTable)
            .on(Column.create("anchortrader_id", entityTable))
            .equals(Column.create("id", anchortraderTable))
            .leftOuterJoin(tradepartnerTable)
            .on(Column.create("tradepartner_id", entityTable))
            .equals(Column.create("id", tradepartnerTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Trade.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Trade> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Trade> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<Trade> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<Trade> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<Trade> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private Trade process(Row row, RowMetadata metadata) {
        Trade entity = tradeMapper.apply(row, "e");
        entity.setFinancerequest(financerequestMapper.apply(row, "financerequest"));
        entity.setAnchortrader(anchortraderMapper.apply(row, "anchortrader"));
        entity.setTradepartner(tradepartnerMapper.apply(row, "tradepartner"));
        return entity;
    }

    @Override
    public <S extends Trade> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
