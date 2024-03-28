package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.DocDetails;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.DocDetailsRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the DocDetails entity.
 */
@SuppressWarnings("unused")
class DocDetailsRepositoryInternalImpl extends SimpleR2dbcRepository<DocDetails, Long> implements DocDetailsRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final FinanceRequestRowMapper financerequestMapper;
    private final DocDetailsRowMapper docdetailsMapper;

    private static final Table entityTable = Table.aliased("doc_details", EntityManager.ENTITY_ALIAS);
    private static final Table financeRequestTable = Table.aliased("finance_request", "financeRequest");

    public DocDetailsRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        FinanceRequestRowMapper financerequestMapper,
        DocDetailsRowMapper docdetailsMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(DocDetails.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.financerequestMapper = financerequestMapper;
        this.docdetailsMapper = docdetailsMapper;
    }

    @Override
    public Flux<DocDetails> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<DocDetails> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = DocDetailsSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(FinanceRequestSqlHelper.getColumns(financeRequestTable, "financeRequest"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(financeRequestTable)
            .on(Column.create("finance_request_id", entityTable))
            .equals(Column.create("id", financeRequestTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, DocDetails.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<DocDetails> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<DocDetails> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<DocDetails> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<DocDetails> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<DocDetails> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private DocDetails process(Row row, RowMetadata metadata) {
        DocDetails entity = docdetailsMapper.apply(row, "e");
        entity.setFinanceRequest(financerequestMapper.apply(row, "financeRequest"));
        return entity;
    }

    @Override
    public <S extends DocDetails> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
