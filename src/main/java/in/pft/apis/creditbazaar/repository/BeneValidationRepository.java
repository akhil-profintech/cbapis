package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.BeneValidation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the BeneValidation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BeneValidationRepository extends ReactiveCrudRepository<BeneValidation, Long>, BeneValidationRepositoryInternal {
    Flux<BeneValidation> findAllBy(Pageable pageable);

    @Override
    Mono<BeneValidation> findOneWithEagerRelationships(Long id);

    @Override
    Flux<BeneValidation> findAllWithEagerRelationships();

    @Override
    Flux<BeneValidation> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM bene_validation entity WHERE entity.trade_entity_id = :id")
    Flux<BeneValidation> findByTradeEntity(Long id);

    @Query("SELECT * FROM bene_validation entity WHERE entity.trade_entity_id IS NULL")
    Flux<BeneValidation> findAllWhereTradeEntityIsNull();

    @Override
    <S extends BeneValidation> Mono<S> save(S entity);

    @Override
    Flux<BeneValidation> findAll();

    @Override
    Mono<BeneValidation> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface BeneValidationRepositoryInternal {
    <S extends BeneValidation> Mono<S> save(S entity);

    Flux<BeneValidation> findAllBy(Pageable pageable);

    Flux<BeneValidation> findAll();

    Mono<BeneValidation> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<BeneValidation> findAllBy(Pageable pageable, Criteria criteria);

    Mono<BeneValidation> findOneWithEagerRelationships(Long id);

    Flux<BeneValidation> findAllWithEagerRelationships();

    Flux<BeneValidation> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
