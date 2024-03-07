package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.IndividualAssessment;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.CBCREProcessRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.IndividualAssessmentRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the IndividualAssessment entity.
 */
@SuppressWarnings("unused")
class IndividualAssessmentRepositoryInternalImpl
    extends SimpleR2dbcRepository<IndividualAssessment, Long>
    implements IndividualAssessmentRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final CBCREProcessRowMapper cbcreprocessMapper;
    private final IndividualAssessmentRowMapper individualassessmentMapper;

    private static final Table entityTable = Table.aliased("individual_assessment", EntityManager.ENTITY_ALIAS);
    private static final Table cbcreprocessTable = Table.aliased("cbcre_process", "cbcreprocess");

    public IndividualAssessmentRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        CBCREProcessRowMapper cbcreprocessMapper,
        IndividualAssessmentRowMapper individualassessmentMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(IndividualAssessment.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.cbcreprocessMapper = cbcreprocessMapper;
        this.individualassessmentMapper = individualassessmentMapper;
    }

    @Override
    public Flux<IndividualAssessment> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<IndividualAssessment> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = IndividualAssessmentSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(CBCREProcessSqlHelper.getColumns(cbcreprocessTable, "cbcreprocess"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(cbcreprocessTable)
            .on(Column.create("cbcreprocess_id", entityTable))
            .equals(Column.create("id", cbcreprocessTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, IndividualAssessment.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<IndividualAssessment> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<IndividualAssessment> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<IndividualAssessment> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<IndividualAssessment> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<IndividualAssessment> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private IndividualAssessment process(Row row, RowMetadata metadata) {
        IndividualAssessment entity = individualassessmentMapper.apply(row, "e");
        entity.setCbcreprocess(cbcreprocessMapper.apply(row, "cbcreprocess"));
        return entity;
    }

    @Override
    public <S extends IndividualAssessment> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
