package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.Disbursement;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.DisbursementRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the Disbursement entity.
 */
@SuppressWarnings("unused")
class DisbursementRepositoryInternalImpl extends SimpleR2dbcRepository<Disbursement, Long> implements DisbursementRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final FinanceRequestRowMapper financerequestMapper;
    private final FinancePartnerRowMapper financepartnerMapper;
    private final DisbursementRowMapper disbursementMapper;

    private static final Table entityTable = Table.aliased("disbursement", EntityManager.ENTITY_ALIAS);
    private static final Table financerequestTable = Table.aliased("finance_request", "financerequest");
    private static final Table financepartnerTable = Table.aliased("finance_partner", "financepartner");

    public DisbursementRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        FinanceRequestRowMapper financerequestMapper,
        FinancePartnerRowMapper financepartnerMapper,
        DisbursementRowMapper disbursementMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Disbursement.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.financerequestMapper = financerequestMapper;
        this.financepartnerMapper = financepartnerMapper;
        this.disbursementMapper = disbursementMapper;
    }

    @Override
    public Flux<Disbursement> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Disbursement> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = DisbursementSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
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
        String select = entityManager.createSelect(selectFrom, Disbursement.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Disbursement> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Disbursement> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<Disbursement> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<Disbursement> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<Disbursement> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private Disbursement process(Row row, RowMetadata metadata) {
        Disbursement entity = disbursementMapper.apply(row, "e");
        entity.setFinancerequest(financerequestMapper.apply(row, "financerequest"));
        entity.setFinancepartner(financepartnerMapper.apply(row, "financepartner"));
        return entity;
    }

    @Override
    public <S extends Disbursement> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
