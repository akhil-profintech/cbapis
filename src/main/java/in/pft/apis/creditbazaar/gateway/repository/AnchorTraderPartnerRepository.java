package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.AnchorTraderPartner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the AnchorTraderPartner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnchorTraderPartnerRepository
    extends ReactiveCrudRepository<AnchorTraderPartner, Long>, AnchorTraderPartnerRepositoryInternal,AnchorTraderPartnerCustomRepo {
    Flux<AnchorTraderPartner> findAllBy(Pageable pageable);

    @Override
    Mono<AnchorTraderPartner> findOneWithEagerRelationships(Long id);

    @Override
    Flux<AnchorTraderPartner> findAllWithEagerRelationships();

    @Override
    Flux<AnchorTraderPartner> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM anchor_trader_partner entity WHERE entity.anchortrader_id = :id")
    Flux<AnchorTraderPartner> findByAnchortrader(Long id);

    @Query("SELECT * FROM anchor_trader_partner entity WHERE entity.anchortrader_id IS NULL")
    Flux<AnchorTraderPartner> findAllWhereAnchortraderIsNull();

    @Override
    <S extends AnchorTraderPartner> Mono<S> save(S entity);

    @Override
    Flux<AnchorTraderPartner> findAll();

    @Override
    Mono<AnchorTraderPartner> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface AnchorTraderPartnerRepositoryInternal {
    <S extends AnchorTraderPartner> Mono<S> save(S entity);

    Flux<AnchorTraderPartner> findAllBy(Pageable pageable);

    Flux<AnchorTraderPartner> findAll();

    Mono<AnchorTraderPartner> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<AnchorTraderPartner> findAllBy(Pageable pageable, Criteria criteria);

    Mono<AnchorTraderPartner> findOneWithEagerRelationships(Long id);

    Flux<AnchorTraderPartner> findAllWithEagerRelationships();

    Flux<AnchorTraderPartner> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
