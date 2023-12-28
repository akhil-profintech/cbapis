package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.DocDetails;
import in.pft.apis.creditbazaar.repository.rowmapper.DisbursementRowMapper;
import in.pft.apis.creditbazaar.repository.rowmapper.DocDetailsRowMapper;
import in.pft.apis.creditbazaar.repository.rowmapper.ParticipantSettlementRowMapper;
import in.pft.apis.creditbazaar.repository.rowmapper.RepaymentRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the DocDetails entity.
 */
@SuppressWarnings("unused")
class DocDetailsRepositoryInternalImpl extends SimpleR2dbcRepository<DocDetails, Long> implements DocDetailsRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final DisbursementRowMapper disbursementMapper;
    private final RepaymentRowMapper repaymentMapper;
    private final ParticipantSettlementRowMapper participantsettlementMapper;
    private final DocDetailsRowMapper docdetailsMapper;

    private static final Table entityTable = Table.aliased("doc_details", EntityManager.ENTITY_ALIAS);
    private static final Table disbursementTable = Table.aliased("disbursement", "disbursement");
    private static final Table repaymentTable = Table.aliased("repayment", "repayment");
    private static final Table participantsettlementTable = Table.aliased("participant_settlement", "participantsettlement");

    public DocDetailsRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        DisbursementRowMapper disbursementMapper,
        RepaymentRowMapper repaymentMapper,
        ParticipantSettlementRowMapper participantsettlementMapper,
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
        this.disbursementMapper = disbursementMapper;
        this.repaymentMapper = repaymentMapper;
        this.participantsettlementMapper = participantsettlementMapper;
        this.docdetailsMapper = docdetailsMapper;
    }

    @Override
    public Flux<DocDetails> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<DocDetails> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = DocDetailsSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(DisbursementSqlHelper.getColumns(disbursementTable, "disbursement"));
        columns.addAll(RepaymentSqlHelper.getColumns(repaymentTable, "repayment"));
        columns.addAll(ParticipantSettlementSqlHelper.getColumns(participantsettlementTable, "participantsettlement"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(disbursementTable)
            .on(Column.create("disbursement_id", entityTable))
            .equals(Column.create("id", disbursementTable))
            .leftOuterJoin(repaymentTable)
            .on(Column.create("repayment_id", entityTable))
            .equals(Column.create("id", repaymentTable))
            .leftOuterJoin(participantsettlementTable)
            .on(Column.create("participantsettlement_id", entityTable))
            .equals(Column.create("id", participantsettlementTable));
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
        entity.setDisbursement(disbursementMapper.apply(row, "disbursement"));
        entity.setRepayment(repaymentMapper.apply(row, "repayment"));
        entity.setParticipantsettlement(participantsettlementMapper.apply(row, "participantsettlement"));
        return entity;
    }

    @Override
    public <S extends DocDetails> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
