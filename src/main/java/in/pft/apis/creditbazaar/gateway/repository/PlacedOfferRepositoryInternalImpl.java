package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.PlacedOffer;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.FinancePartnerRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.FinanceRequestRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.PlacedOfferRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the PlacedOffer entity.
 */
@SuppressWarnings("unused")
class PlacedOfferRepositoryInternalImpl extends SimpleR2dbcRepository<PlacedOffer, Long> implements PlacedOfferRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final FinanceRequestRowMapper financerequestMapper;
    private final FinancePartnerRowMapper financepartnerMapper;
    private final PlacedOfferRowMapper placedofferMapper;

    private static final Table entityTable = Table.aliased("placed_offer", EntityManager.ENTITY_ALIAS);
    private static final Table financerequestTable = Table.aliased("finance_request", "financerequest");
    private static final Table financepartnerTable = Table.aliased("finance_partner", "financepartner");

    public PlacedOfferRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        FinanceRequestRowMapper financerequestMapper,
        FinancePartnerRowMapper financepartnerMapper,
        PlacedOfferRowMapper placedofferMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(PlacedOffer.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.financerequestMapper = financerequestMapper;
        this.financepartnerMapper = financepartnerMapper;
        this.placedofferMapper = placedofferMapper;
    }

    @Override
    public Flux<PlacedOffer> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<PlacedOffer> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = PlacedOfferSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(FinanceRequestSqlHelper.getColumns(financerequestTable, "financerequest"));
        columns.addAll(FinancePartnerSqlHelper.getColumns(financepartnerTable, "financepartner"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(financerequestTable)
            .on(Column.create("financerequest_id", entityTable))
            .equals(Column.create("id", financerequestTable))
            .leftOuterJoin(financepartnerTable)
            .on(Column.create("financepartner_id", entityTable))
            .equals(Column.create("id", financepartnerTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, PlacedOffer.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<PlacedOffer> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<PlacedOffer> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<PlacedOffer> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<PlacedOffer> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<PlacedOffer> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private PlacedOffer process(Row row, RowMetadata metadata) {
        PlacedOffer entity = placedofferMapper.apply(row, "e");
        entity.setFinancerequest(financerequestMapper.apply(row, "financerequest"));
        entity.setFinancepartner(financepartnerMapper.apply(row, "financepartner"));
        return entity;
    }

    @Override
    public <S extends PlacedOffer> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
