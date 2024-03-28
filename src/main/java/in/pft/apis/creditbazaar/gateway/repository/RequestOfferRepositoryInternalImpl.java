package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.RequestOffer;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.FinancePartnerRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.FinanceRequestRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.RequestOfferRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the RequestOffer entity.
 */
@SuppressWarnings("unused")
class RequestOfferRepositoryInternalImpl extends SimpleR2dbcRepository<RequestOffer, Long> implements RequestOfferRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final FinanceRequestRowMapper financerequestMapper;
    private final FinancePartnerRowMapper financepartnerMapper;
    private final RequestOfferRowMapper requestofferMapper;

    private static final Table entityTable = Table.aliased("request_offer", EntityManager.ENTITY_ALIAS);
    private static final Table financerequestTable = Table.aliased("finance_request", "financerequest");
    private static final Table financepartnerTable = Table.aliased("finance_partner", "financepartner");

    public RequestOfferRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        FinanceRequestRowMapper financerequestMapper,
        FinancePartnerRowMapper financepartnerMapper,
        RequestOfferRowMapper requestofferMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(RequestOffer.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.financerequestMapper = financerequestMapper;
        this.financepartnerMapper = financepartnerMapper;
        this.requestofferMapper = requestofferMapper;
    }

    @Override
    public Flux<RequestOffer> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<RequestOffer> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = RequestOfferSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
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
        String select = entityManager.createSelect(selectFrom, RequestOffer.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<RequestOffer> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<RequestOffer> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<RequestOffer> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<RequestOffer> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<RequestOffer> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private RequestOffer process(Row row, RowMetadata metadata) {
        RequestOffer entity = requestofferMapper.apply(row, "e");
        entity.setFinancerequest(financerequestMapper.apply(row, "financerequest"));
        entity.setFinancepartner(financepartnerMapper.apply(row, "financepartner"));
        return entity;
    }

    @Override
    public <S extends RequestOffer> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
