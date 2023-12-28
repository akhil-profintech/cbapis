package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.AcceptedOffer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the AcceptedOffer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcceptedOfferRepository extends ReactiveCrudRepository<AcceptedOffer, Long>, AcceptedOfferRepositoryInternal {
    Flux<AcceptedOffer> findAllBy(Pageable pageable);

    @Override
    Mono<AcceptedOffer> findOneWithEagerRelationships(Long id);

    @Override
    Flux<AcceptedOffer> findAllWithEagerRelationships();

    @Override
    Flux<AcceptedOffer> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM accepted_offer entity WHERE entity.financerequest_id = :id")
    Flux<AcceptedOffer> findByFinancerequest(Long id);

    @Query("SELECT * FROM accepted_offer entity WHERE entity.financerequest_id IS NULL")
    Flux<AcceptedOffer> findAllWhereFinancerequestIsNull();

    @Query("SELECT * FROM accepted_offer entity WHERE entity.financepartner_id = :id")
    Flux<AcceptedOffer> findByFinancepartner(Long id);

    @Query("SELECT * FROM accepted_offer entity WHERE entity.financepartner_id IS NULL")
    Flux<AcceptedOffer> findAllWhereFinancepartnerIsNull();

    @Query("SELECT * FROM accepted_offer entity WHERE entity.anchortrader_id = :id")
    Flux<AcceptedOffer> findByAnchortrader(Long id);

    @Query("SELECT * FROM accepted_offer entity WHERE entity.anchortrader_id IS NULL")
    Flux<AcceptedOffer> findAllWhereAnchortraderIsNull();

    @Override
    <S extends AcceptedOffer> Mono<S> save(S entity);

    @Override
    Flux<AcceptedOffer> findAll();

    @Override
    Mono<AcceptedOffer> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface AcceptedOfferRepositoryInternal {
    <S extends AcceptedOffer> Mono<S> save(S entity);

    Flux<AcceptedOffer> findAllBy(Pageable pageable);

    Flux<AcceptedOffer> findAll();

    Mono<AcceptedOffer> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<AcceptedOffer> findAllBy(Pageable pageable, Criteria criteria);

    Mono<AcceptedOffer> findOneWithEagerRelationships(Long id);

    Flux<AcceptedOffer> findAllWithEagerRelationships();

    Flux<AcceptedOffer> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
