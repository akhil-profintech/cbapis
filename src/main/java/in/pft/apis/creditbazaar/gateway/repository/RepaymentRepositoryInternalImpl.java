package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.Repayment;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.FinanceRequestRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.RepaymentRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the Repayment entity.
 */
@SuppressWarnings("unused")
class RepaymentRepositoryInternalImpl extends SimpleR2dbcRepository<Repayment, Long> implements RepaymentRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final FinanceRequestRowMapper financerequestMapper;
    private final RepaymentRowMapper repaymentMapper;

    private static final Table entityTable = Table.aliased("repayment", EntityManager.ENTITY_ALIAS);
    private static final Table financerequestTable = Table.aliased("finance_request", "financerequest");

    public RepaymentRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        FinanceRequestRowMapper financerequestMapper,
        RepaymentRowMapper repaymentMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Repayment.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.financerequestMapper = financerequestMapper;
        this.repaymentMapper = repaymentMapper;
    }

    @Override
    public Flux<Repayment> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Repayment> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = RepaymentSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(FinanceRequestSqlHelper.getColumns(financerequestTable, "financerequest"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(financerequestTable)
            .on(Column.create("financerequest_id", entityTable))
            .equals(Column.create("id", financerequestTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Repayment.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Repayment> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Repayment> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<Repayment> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<Repayment> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<Repayment> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private Repayment process(Row row, RowMetadata metadata) {
        Repayment entity = repaymentMapper.apply(row, "e");
        entity.setFinancerequest(financerequestMapper.apply(row, "financerequest"));
        return entity;
    }

    @Override
    public <S extends Repayment> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
