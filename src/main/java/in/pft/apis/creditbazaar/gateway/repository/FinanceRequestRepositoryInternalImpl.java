package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.FinanceRequest;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.AnchorTraderRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.FinanceRequestRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the FinanceRequest entity.
 */
@SuppressWarnings("unused")
class FinanceRequestRepositoryInternalImpl extends SimpleR2dbcRepository<FinanceRequest, Long> implements FinanceRequestRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final AnchorTraderRowMapper anchortraderMapper;
    private final FinanceRequestRowMapper financerequestMapper;

    private static final Table entityTable = Table.aliased("finance_request", EntityManager.ENTITY_ALIAS);
    private static final Table anchortraderTable = Table.aliased("anchor_trader", "anchortrader");

    public FinanceRequestRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        AnchorTraderRowMapper anchortraderMapper,
        FinanceRequestRowMapper financerequestMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(FinanceRequest.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.anchortraderMapper = anchortraderMapper;
        this.financerequestMapper = financerequestMapper;
    }

    @Override
    public Flux<FinanceRequest> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<FinanceRequest> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = FinanceRequestSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(AnchorTraderSqlHelper.getColumns(anchortraderTable, "anchortrader"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(anchortraderTable)
            .on(Column.create("anchortrader_id", entityTable))
            .equals(Column.create("id", anchortraderTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, FinanceRequest.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<FinanceRequest> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<FinanceRequest> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<FinanceRequest> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<FinanceRequest> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<FinanceRequest> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private FinanceRequest process(Row row, RowMetadata metadata) {
        FinanceRequest entity = financerequestMapper.apply(row, "e");
        entity.setAnchortrader(anchortraderMapper.apply(row, "anchortrader"));
        return entity;
    }

    @Override
    public <S extends FinanceRequest> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
