package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.ProspectRequest;
import in.pft.apis.creditbazaar.repository.rowmapper.FinanceRequestRowMapper;
import in.pft.apis.creditbazaar.repository.rowmapper.ProspectRequestRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the ProspectRequest entity.
 */
@SuppressWarnings("unused")
class ProspectRequestRepositoryInternalImpl
    extends SimpleR2dbcRepository<ProspectRequest, Long>
    implements ProspectRequestRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final FinanceRequestRowMapper financerequestMapper;
    private final ProspectRequestRowMapper prospectrequestMapper;

    private static final Table entityTable = Table.aliased("prospect_request", EntityManager.ENTITY_ALIAS);
    private static final Table financerequestTable = Table.aliased("finance_request", "financerequest");

    public ProspectRequestRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        FinanceRequestRowMapper financerequestMapper,
        ProspectRequestRowMapper prospectrequestMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(ProspectRequest.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.financerequestMapper = financerequestMapper;
        this.prospectrequestMapper = prospectrequestMapper;
    }

    @Override
    public Flux<ProspectRequest> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<ProspectRequest> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = ProspectRequestSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(FinanceRequestSqlHelper.getColumns(financerequestTable, "financerequest"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(financerequestTable)
            .on(Column.create("financerequest_id", entityTable))
            .equals(Column.create("id", financerequestTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, ProspectRequest.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<ProspectRequest> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<ProspectRequest> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<ProspectRequest> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<ProspectRequest> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<ProspectRequest> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private ProspectRequest process(Row row, RowMetadata metadata) {
        ProspectRequest entity = prospectrequestMapper.apply(row, "e");
        entity.setFinancerequest(financerequestMapper.apply(row, "financerequest"));
        return entity;
    }

    @Override
    public <S extends ProspectRequest> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
