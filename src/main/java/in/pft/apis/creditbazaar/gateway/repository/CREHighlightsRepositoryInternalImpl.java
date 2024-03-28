package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.CREHighlights;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.CREHighlightsRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the CREHighlights entity.
 */
@SuppressWarnings("unused")
class CREHighlightsRepositoryInternalImpl extends SimpleR2dbcRepository<CREHighlights, Long> implements CREHighlightsRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final IndividualAssessmentRowMapper individualassessmentMapper;
    private final CREHighlightsRowMapper crehighlightsMapper;

    private static final Table entityTable = Table.aliased("cre_highlights", EntityManager.ENTITY_ALIAS);
    private static final Table individualassessmentTable = Table.aliased("individual_assessment", "individualassessment");

    public CREHighlightsRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        IndividualAssessmentRowMapper individualassessmentMapper,
        CREHighlightsRowMapper crehighlightsMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(CREHighlights.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.individualassessmentMapper = individualassessmentMapper;
        this.crehighlightsMapper = crehighlightsMapper;
    }

    @Override
    public Flux<CREHighlights> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<CREHighlights> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = CREHighlightsSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(IndividualAssessmentSqlHelper.getColumns(individualassessmentTable, "individualassessment"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(individualassessmentTable)
            .on(Column.create("individualassessment_id", entityTable))
            .equals(Column.create("id", individualassessmentTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, CREHighlights.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<CREHighlights> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<CREHighlights> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<CREHighlights> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<CREHighlights> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<CREHighlights> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private CREHighlights process(Row row, RowMetadata metadata) {
        CREHighlights entity = crehighlightsMapper.apply(row, "e");
        entity.setIndividualassessment(individualassessmentMapper.apply(row, "individualassessment"));
        return entity;
    }

    @Override
    public <S extends CREHighlights> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
