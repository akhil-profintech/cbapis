package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.RequestOffer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the RequestOffer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestOfferRepository extends ReactiveCrudRepository<RequestOffer, Long>, RequestOfferRepositoryInternal,RequestOfferCustomRepository {
    Flux<RequestOffer> findAllBy(Pageable pageable);

    @Override
    Mono<RequestOffer> findOneWithEagerRelationships(Long id);

    @Override
    Flux<RequestOffer> findAllWithEagerRelationships();

    @Override
    Flux<RequestOffer> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM request_offer entity WHERE entity.financerequest_id = :id")
    Flux<RequestOffer> findByFinancerequest(Long id);

    @Query("SELECT * FROM request_offer entity WHERE entity.financerequest_id IS NULL")
    Flux<RequestOffer> findAllWhereFinancerequestIsNull();

    @Query("SELECT * FROM request_offer entity WHERE entity.financepartner_id = :id")
    Flux<RequestOffer> findByFinancepartner(Long id);

    @Query("SELECT * FROM request_offer entity WHERE entity.financepartner_id IS NULL")
    Flux<RequestOffer> findAllWhereFinancepartnerIsNull();

    @Override
    <S extends RequestOffer> Mono<S> save(S entity);

    @Override
    Flux<RequestOffer> findAll();

    @Override
    Mono<RequestOffer> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface RequestOfferRepositoryInternal {
    <S extends RequestOffer> Mono<S> save(S entity);

    Flux<RequestOffer> findAllBy(Pageable pageable);

    Flux<RequestOffer> findAll();

    Mono<RequestOffer> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<RequestOffer> findAllBy(Pageable pageable, Criteria criteria);

    Mono<RequestOffer> findOneWithEagerRelationships(Long id);

    Flux<RequestOffer> findAllWithEagerRelationships();

    Flux<RequestOffer> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
