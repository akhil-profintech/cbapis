package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.AcceptedOffer;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.AcceptedOfferRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.AnchorTraderRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.FinancePartnerRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.FinanceRequestRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the AcceptedOffer entity.
 */
@SuppressWarnings("unused")
class AcceptedOfferRepositoryInternalImpl extends SimpleR2dbcRepository<AcceptedOffer, Long> implements AcceptedOfferRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final FinanceRequestRowMapper financerequestMapper;
    private final AnchorTraderRowMapper anchortraderMapper;
    private final FinancePartnerRowMapper financepartnerMapper;
    private final AcceptedOfferRowMapper acceptedofferMapper;

    private static final Table entityTable = Table.aliased("accepted_offer", EntityManager.ENTITY_ALIAS);
    private static final Table financerequestTable = Table.aliased("finance_request", "financerequest");
    private static final Table anchortraderTable = Table.aliased("anchor_trader", "anchortrader");
    private static final Table financepartnerTable = Table.aliased("finance_partner", "financepartner");

    public AcceptedOfferRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        FinanceRequestRowMapper financerequestMapper,
        AnchorTraderRowMapper anchortraderMapper,
        FinancePartnerRowMapper financepartnerMapper,
        AcceptedOfferRowMapper acceptedofferMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(AcceptedOffer.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.financerequestMapper = financerequestMapper;
        this.anchortraderMapper = anchortraderMapper;
        this.financepartnerMapper = financepartnerMapper;
        this.acceptedofferMapper = acceptedofferMapper;
    }

    @Override
    public Flux<AcceptedOffer> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<AcceptedOffer> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = AcceptedOfferSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(FinanceRequestSqlHelper.getColumns(financerequestTable, "financerequest"));
        columns.addAll(AnchorTraderSqlHelper.getColumns(anchortraderTable, "anchortrader"));
        columns.addAll(FinancePartnerSqlHelper.getColumns(financepartnerTable, "financepartner"));
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
            .leftOuterJoin(financepartnerTable)
            .on(Column.create("financepartner_id", entityTable))
            .equals(Column.create("id", financepartnerTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, AcceptedOffer.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<AcceptedOffer> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<AcceptedOffer> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<AcceptedOffer> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<AcceptedOffer> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<AcceptedOffer> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private AcceptedOffer process(Row row, RowMetadata metadata) {
        AcceptedOffer entity = acceptedofferMapper.apply(row, "e");
        entity.setFinancerequest(financerequestMapper.apply(row, "financerequest"));
        entity.setAnchortrader(anchortraderMapper.apply(row, "anchortrader"));
        entity.setFinancepartner(financepartnerMapper.apply(row, "financepartner"));
        return entity;
    }

    @Override
    public <S extends AcceptedOffer> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
