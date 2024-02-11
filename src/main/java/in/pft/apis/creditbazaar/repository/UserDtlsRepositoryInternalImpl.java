package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.UserDtls;
import in.pft.apis.creditbazaar.repository.rowmapper.OrganizationRowMapper;
import in.pft.apis.creditbazaar.repository.rowmapper.UserDtlsRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the UserDtls entity.
 */
@SuppressWarnings("unused")
class UserDtlsRepositoryInternalImpl extends SimpleR2dbcRepository<UserDtls, Long> implements UserDtlsRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final OrganizationRowMapper organizationMapper;
    private final UserDtlsRowMapper userdtlsMapper;

    private static final Table entityTable = Table.aliased("user_dtls", EntityManager.ENTITY_ALIAS);
    private static final Table organizationTable = Table.aliased("organization", "e_organization");

    public UserDtlsRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        OrganizationRowMapper organizationMapper,
        UserDtlsRowMapper userdtlsMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(UserDtls.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.organizationMapper = organizationMapper;
        this.userdtlsMapper = userdtlsMapper;
    }

    @Override
    public Flux<UserDtls> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<UserDtls> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = UserDtlsSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(OrganizationSqlHelper.getColumns(organizationTable, "organization"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(organizationTable)
            .on(Column.create("organization_id", entityTable))
            .equals(Column.create("id", organizationTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, UserDtls.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<UserDtls> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<UserDtls> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<UserDtls> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<UserDtls> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<UserDtls> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private UserDtls process(Row row, RowMetadata metadata) {
        UserDtls entity = userdtlsMapper.apply(row, "e");
        entity.setOrganization(organizationMapper.apply(row, "organization"));
        return entity;
    }

    @Override
    public <S extends UserDtls> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
