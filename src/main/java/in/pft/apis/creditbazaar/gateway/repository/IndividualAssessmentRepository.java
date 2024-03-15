package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.IndividualAssessment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the IndividualAssessment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IndividualAssessmentRepository
    extends ReactiveCrudRepository<IndividualAssessment, Long>, IndividualAssessmentRepositoryInternal,IndividualAssessmentCustomRepo {
    Flux<IndividualAssessment> findAllBy(Pageable pageable);

    @Override
    Mono<IndividualAssessment> findOneWithEagerRelationships(Long id);

    @Override
    Flux<IndividualAssessment> findAllWithEagerRelationships();

    @Override
    Flux<IndividualAssessment> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM individual_assessment entity WHERE entity.cbcreprocess_id = :id")
    Flux<IndividualAssessment> findByCbcreprocess(Long id);

    @Query("SELECT * FROM individual_assessment entity WHERE entity.cbcreprocess_id IS NULL")
    Flux<IndividualAssessment> findAllWhereCbcreprocessIsNull();

    @Override
    <S extends IndividualAssessment> Mono<S> save(S entity);

    @Override
    Flux<IndividualAssessment> findAll();

    @Override
    Mono<IndividualAssessment> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface IndividualAssessmentRepositoryInternal {
    <S extends IndividualAssessment> Mono<S> save(S entity);

    Flux<IndividualAssessment> findAllBy(Pageable pageable);

    Flux<IndividualAssessment> findAll();

    Mono<IndividualAssessment> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<IndividualAssessment> findAllBy(Pageable pageable, Criteria criteria);

    Mono<IndividualAssessment> findOneWithEagerRelationships(Long id);

    Flux<IndividualAssessment> findAllWithEagerRelationships();

    Flux<IndividualAssessment> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
