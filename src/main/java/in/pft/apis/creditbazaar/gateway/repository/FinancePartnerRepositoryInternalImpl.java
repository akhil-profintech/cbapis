package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.FinancePartner;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.FinancePartnerRowMapper;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoin;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC custom repository implementation for the FinancePartner entity.
 */
@SuppressWarnings("unused")
class FinancePartnerRepositoryInternalImpl extends SimpleR2dbcRepository<FinancePartner, Long> implements FinancePartnerRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final FinancePartnerRowMapper financepartnerMapper;

    private static final Table entityTable = Table.aliased("finance_partner", EntityManager.ENTITY_ALIAS);

    public FinancePartnerRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        FinancePartnerRowMapper financepartnerMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(FinancePartner.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.financepartnerMapper = financepartnerMapper;
    }

    @Override
    public Flux<FinancePartner> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<FinancePartner> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = FinancePartnerSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, FinancePartner.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<FinancePartner> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<FinancePartner> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private FinancePartner process(Row row, RowMetadata metadata) {
        FinancePartner entity = financepartnerMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends FinancePartner> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
