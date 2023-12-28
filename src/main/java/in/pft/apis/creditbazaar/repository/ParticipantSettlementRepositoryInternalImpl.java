package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.ParticipantSettlement;
import in.pft.apis.creditbazaar.repository.rowmapper.ParticipantSettlementRowMapper;
import in.pft.apis.creditbazaar.repository.rowmapper.SettlementRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the ParticipantSettlement entity.
 */
@SuppressWarnings("unused")
class ParticipantSettlementRepositoryInternalImpl
    extends SimpleR2dbcRepository<ParticipantSettlement, Long>
    implements ParticipantSettlementRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final SettlementRowMapper settlementMapper;
    private final ParticipantSettlementRowMapper participantsettlementMapper;

    private static final Table entityTable = Table.aliased("participant_settlement", EntityManager.ENTITY_ALIAS);
    private static final Table settlementTable = Table.aliased("settlement", "settlement");

    public ParticipantSettlementRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        SettlementRowMapper settlementMapper,
        ParticipantSettlementRowMapper participantsettlementMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(ParticipantSettlement.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.settlementMapper = settlementMapper;
        this.participantsettlementMapper = participantsettlementMapper;
    }

    @Override
    public Flux<ParticipantSettlement> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<ParticipantSettlement> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = ParticipantSettlementSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(SettlementSqlHelper.getColumns(settlementTable, "settlement"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(settlementTable)
            .on(Column.create("settlement_id", entityTable))
            .equals(Column.create("id", settlementTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, ParticipantSettlement.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<ParticipantSettlement> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<ParticipantSettlement> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<ParticipantSettlement> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<ParticipantSettlement> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<ParticipantSettlement> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private ParticipantSettlement process(Row row, RowMetadata metadata) {
        ParticipantSettlement entity = participantsettlementMapper.apply(row, "e");
        entity.setSettlement(settlementMapper.apply(row, "settlement"));
        return entity;
    }

    @Override
    public <S extends ParticipantSettlement> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
