package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.AnchorTraderPartner;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.AnchorTraderPartnerRowMapper;
import in.pft.apis.creditbazaar.gateway.repository.rowmapper.AnchorTraderRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the AnchorTraderPartner entity.
 */
@SuppressWarnings("unused")
class AnchorTraderPartnerRepositoryInternalImpl
    extends SimpleR2dbcRepository<AnchorTraderPartner, Long>
    implements AnchorTraderPartnerRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final AnchorTraderRowMapper anchortraderMapper;
    private final AnchorTraderPartnerRowMapper anchortraderpartnerMapper;

    private static final Table entityTable = Table.aliased("anchor_trader_partner", EntityManager.ENTITY_ALIAS);
    private static final Table anchortraderTable = Table.aliased("anchor_trader", "anchortrader");

    public AnchorTraderPartnerRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        AnchorTraderRowMapper anchortraderMapper,
        AnchorTraderPartnerRowMapper anchortraderpartnerMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(AnchorTraderPartner.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.anchortraderMapper = anchortraderMapper;
        this.anchortraderpartnerMapper = anchortraderpartnerMapper;
    }

    @Override
    public Flux<AnchorTraderPartner> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<AnchorTraderPartner> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = AnchorTraderPartnerSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(AnchorTraderSqlHelper.getColumns(anchortraderTable, "anchortrader"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(anchortraderTable)
            .on(Column.create("anchortrader_id", entityTable))
            .equals(Column.create("id", anchortraderTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, AnchorTraderPartner.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<AnchorTraderPartner> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<AnchorTraderPartner> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<AnchorTraderPartner> findOneWithEagerRelationships(Long id) {
        return findById(id);
    }

    @Override
    public Flux<AnchorTraderPartner> findAllWithEagerRelationships() {
        return findAll();
    }

    @Override
    public Flux<AnchorTraderPartner> findAllWithEagerRelationships(Pageable page) {
        return findAllBy(page);
    }

    private AnchorTraderPartner process(Row row, RowMetadata metadata) {
        AnchorTraderPartner entity = anchortraderpartnerMapper.apply(row, "e");
        entity.setAnchortrader(anchortraderMapper.apply(row, "anchortrader"));
        return entity;
    }

    @Override
    public <S extends AnchorTraderPartner> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
