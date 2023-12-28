package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.CREObservations;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CREObservations entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CREObservationsRepository extends ReactiveCrudRepository<CREObservations, Long>, CREObservationsRepositoryInternal {
    Flux<CREObservations> findAllBy(Pageable pageable);

    @Override
    Mono<CREObservations> findOneWithEagerRelationships(Long id);

    @Override
    Flux<CREObservations> findAllWithEagerRelationships();

    @Override
    Flux<CREObservations> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM cre_observations entity WHERE entity.cbcreprocess_id = :id")
    Flux<CREObservations> findByCbcreprocess(Long id);

    @Query("SELECT * FROM cre_observations entity WHERE entity.cbcreprocess_id IS NULL")
    Flux<CREObservations> findAllWhereCbcreprocessIsNull();

    @Query("SELECT * FROM cre_observations entity WHERE entity.individualassessment_id = :id")
    Flux<CREObservations> findByIndividualassessment(Long id);

    @Query("SELECT * FROM cre_observations entity WHERE entity.individualassessment_id IS NULL")
    Flux<CREObservations> findAllWhereIndividualassessmentIsNull();

    @Override
    <S extends CREObservations> Mono<S> save(S entity);

    @Override
    Flux<CREObservations> findAll();

    @Override
    Mono<CREObservations> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CREObservationsRepositoryInternal {
    <S extends CREObservations> Mono<S> save(S entity);

    Flux<CREObservations> findAllBy(Pageable pageable);

    Flux<CREObservations> findAll();

    Mono<CREObservations> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CREObservations> findAllBy(Pageable pageable, Criteria criteria);

    Mono<CREObservations> findOneWithEagerRelationships(Long id);

    Flux<CREObservations> findAllWithEagerRelationships();

    Flux<CREObservations> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
