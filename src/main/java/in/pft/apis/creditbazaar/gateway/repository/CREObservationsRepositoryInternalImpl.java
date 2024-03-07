package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.CREObservations;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.CREObservationsRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the CREObservations entity.
 */
@SuppressWarnings("unused")
class CREObservationsRepositoryInternalImpl
    extends SimpleR2dbcRepository<CREObservations, Long>
    implements CREObservationsRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final IndividualAssessmentRowMapper individualassessmentMapper;
    private final CREObservationsRowMapper creobservationsMapper;

    private static final Table entityTable = Table.aliased("cre_observations", EntityManager.ENTITY_ALIAS);
    private static final Table individualassessmentTable = Table.aliased("individual_assessment", "individualassessment");

    public CREObservationsRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        IndividualAssessmentRowMapper individualassessmentMapper,
        CREObservationsRowMapper creobservationsMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(CREObservations.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.individualassessmentMapper = individualassessmentMapper;
        this.creobservationsMapper = creobservationsMapper;
    }

    @Override
    public Flux<CREObservations> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<CREObservations> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = CREObservationsSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(IndividualAssessmentSqlHelper.getColumns(individualassessmentTable, "individualassessment"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(individualassessmentTable)
            .on(Column.create("individualassessment_id", entityTable))
            .equals(Column.create("id", individualassessmentTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, CREObservations.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<CREObservations> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<CREObservations> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<CREObservations> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<CREObservations> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<CREObservations> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private CREObservations process(Row row, RowMetadata metadata) {
        CREObservations entity = creobservationsMapper.apply(row, "e");
        entity.setIndividualassessment(individualassessmentMapper.apply(row, "individualassessment"));
        return entity;
    }

    @Override
    public <S extends CREObservations> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
