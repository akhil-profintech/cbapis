package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.PlacedOffer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PlacedOffer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlacedOfferRepository extends ReactiveCrudRepository<PlacedOffer, Long>, PlacedOfferRepositoryInternal ,PlacedOfferCustomRepository{
    Flux<PlacedOffer> findAllBy(Pageable pageable);

    @Override
    Mono<PlacedOffer> findOneWithEagerRelationships(Long id);

    @Override
    Flux<PlacedOffer> findAllWithEagerRelationships();

    @Override
    Flux<PlacedOffer> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM placed_offer entity WHERE entity.financerequest_id = :id")
    Flux<PlacedOffer> findByFinancerequest(Long id);

    @Query("SELECT * FROM placed_offer entity WHERE entity.financerequest_id IS NULL")
    Flux<PlacedOffer> findAllWhereFinancerequestIsNull();

    @Query("SELECT * FROM placed_offer entity WHERE entity.financepartner_id = :id")
    Flux<PlacedOffer> findByFinancepartner(Long id);

    @Query("SELECT * FROM placed_offer entity WHERE entity.financepartner_id IS NULL")
    Flux<PlacedOffer> findAllWhereFinancepartnerIsNull();

    @Override
    <S extends PlacedOffer> Mono<S> save(S entity);

    @Override
    Flux<PlacedOffer> findAll();

    @Override
    Mono<PlacedOffer> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PlacedOfferRepositoryInternal {
    <S extends PlacedOffer> Mono<S> save(S entity);

    Flux<PlacedOffer> findAllBy(Pageable pageable);

    Flux<PlacedOffer> findAll();

    Mono<PlacedOffer> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PlacedOffer> findAllBy(Pageable pageable, Criteria criteria);

    Mono<PlacedOffer> findOneWithEagerRelationships(Long id);

    Flux<PlacedOffer> findAllWithEagerRelationships();

    Flux<PlacedOffer> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
