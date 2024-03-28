package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.CBCREProcess;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.CBCREProcessRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the CBCREProcess entity.
 */
@SuppressWarnings("unused")
class CBCREProcessRepositoryInternalImpl extends SimpleR2dbcRepository<CBCREProcess, Long> implements CBCREProcessRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final FinanceRequestRowMapper financerequestMapper;
    private final CBCREProcessRowMapper cbcreprocessMapper;

    private static final Table entityTable = Table.aliased("cbcre_process", EntityManager.ENTITY_ALIAS);
    private static final Table financeRequestTable = Table.aliased("finance_request", "financeRequest");

    public CBCREProcessRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        FinanceRequestRowMapper financerequestMapper,
        CBCREProcessRowMapper cbcreprocessMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(CBCREProcess.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.financerequestMapper = financerequestMapper;
        this.cbcreprocessMapper = cbcreprocessMapper;
    }

    @Override
    public Flux<CBCREProcess> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<CBCREProcess> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = CBCREProcessSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(FinanceRequestSqlHelper.getColumns(financeRequestTable, "financeRequest"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(financeRequestTable)
            .on(Column.create("finance_request_id", entityTable))
            .equals(Column.create("id", financeRequestTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, CBCREProcess.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<CBCREProcess> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<CBCREProcess> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<CBCREProcess> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<CBCREProcess> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<CBCREProcess> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private CBCREProcess process(Row row, RowMetadata metadata) {
        CBCREProcess entity = cbcreprocessMapper.apply(row, "e");
        entity.setFinanceRequest(financerequestMapper.apply(row, "financeRequest"));
        return entity;
    }

    @Override
    public <S extends CBCREProcess> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
