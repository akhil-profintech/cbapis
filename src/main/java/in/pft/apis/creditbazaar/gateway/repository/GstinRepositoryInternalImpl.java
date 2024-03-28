package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.Gstin;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.GstinRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.OrganizationRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the Gstin entity.
 */
@SuppressWarnings("unused")
class GstinRepositoryInternalImpl extends SimpleR2dbcRepository<Gstin, Long> implements GstinRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final OrganizationRowMapper organizationMapper;
    private final GstinRowMapper gstinMapper;

    private static final Table entityTable = Table.aliased("gstin", EntityManager.ENTITY_ALIAS);
    private static final Table organizationTable = Table.aliased("organization", "e_organization");

    public GstinRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        OrganizationRowMapper organizationMapper,
        GstinRowMapper gstinMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Gstin.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.organizationMapper = organizationMapper;
        this.gstinMapper = gstinMapper;
    }

    @Override
    public Flux<Gstin> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Gstin> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = GstinSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(OrganizationSqlHelper.getColumns(organizationTable, "organization"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(organizationTable)
            .on(Column.create("organization_id", entityTable))
            .equals(Column.create("id", organizationTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Gstin.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Gstin> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Gstin> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<Gstin> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<Gstin> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<Gstin> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private Gstin process(Row row, RowMetadata metadata) {
        Gstin entity = gstinMapper.apply(row, "e");
        entity.setOrganization(organizationMapper.apply(row, "organization"));
        return entity;
    }

    @Override
    public <S extends Gstin> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
