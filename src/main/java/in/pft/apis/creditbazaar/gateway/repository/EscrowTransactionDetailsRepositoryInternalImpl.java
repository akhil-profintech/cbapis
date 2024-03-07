package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.EscrowTransactionDetails;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.DisbursementRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.EscrowTransactionDetailsRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.RepaymentRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the EscrowTransactionDetails entity.
 */
@SuppressWarnings("unused")
class EscrowTransactionDetailsRepositoryInternalImpl
    extends SimpleR2dbcRepository<EscrowTransactionDetails, Long>
    implements EscrowTransactionDetailsRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final DisbursementRowMapper disbursementMapper;
    private final RepaymentRowMapper repaymentMapper;
    private final EscrowTransactionDetailsRowMapper escrowtransactiondetailsMapper;

    private static final Table entityTable = Table.aliased("escrow_transaction_details", EntityManager.ENTITY_ALIAS);
    private static final Table disbursementTable = Table.aliased("disbursement", "disbursement");
    private static final Table repaymentTable = Table.aliased("repayment", "repayment");

    public EscrowTransactionDetailsRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        DisbursementRowMapper disbursementMapper,
        RepaymentRowMapper repaymentMapper,
        EscrowTransactionDetailsRowMapper escrowtransactiondetailsMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(
                converter.getMappingContext().getRequiredPersistentEntity(EscrowTransactionDetails.class)
            ),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.disbursementMapper = disbursementMapper;
        this.repaymentMapper = repaymentMapper;
        this.escrowtransactiondetailsMapper = escrowtransactiondetailsMapper;
    }

    @Override
    public Flux<EscrowTransactionDetails> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<EscrowTransactionDetails> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = EscrowTransactionDetailsSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(DisbursementSqlHelper.getColumns(disbursementTable, "disbursement"));
        columns.addAll(RepaymentSqlHelper.getColumns(repaymentTable, "repayment"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(disbursementTable)
            .on(Column.create("disbursement_id", entityTable))
            .equals(Column.create("id", disbursementTable))
            .leftOuterJoin(repaymentTable)
            .on(Column.create("repayment_id", entityTable))
            .equals(Column.create("id", repaymentTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, EscrowTransactionDetails.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<EscrowTransactionDetails> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<EscrowTransactionDetails> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<EscrowTransactionDetails> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<EscrowTransactionDetails> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<EscrowTransactionDetails> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private EscrowTransactionDetails process(Row row, RowMetadata metadata) {
        EscrowTransactionDetails entity = escrowtransactiondetailsMapper.apply(row, "e");
        entity.setDisbursement(disbursementMapper.apply(row, "disbursement"));
        entity.setRepayment(repaymentMapper.apply(row, "repayment"));
        return entity;
    }

    @Override
    public <S extends EscrowTransactionDetails> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
