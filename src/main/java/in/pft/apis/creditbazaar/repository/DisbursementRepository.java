package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.Disbursement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Disbursement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DisbursementRepository extends ReactiveCrudRepository<Disbursement, Long>, DisbursementRepositoryInternal {
    Flux<Disbursement> findAllBy(Pageable pageable);

    @Override
    Mono<Disbursement> findOneWithEagerRelationships(Long id);

    @Override
    Flux<Disbursement> findAllWithEagerRelationships();

    @Override
    Flux<Disbursement> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM disbursement entity WHERE entity.financerequest_id = :id")
    Flux<Disbursement> findByFinancerequest(Long id);

    @Query("SELECT * FROM disbursement entity WHERE entity.financerequest_id IS NULL")
    Flux<Disbursement> findAllWhereFinancerequestIsNull();

    @Query("SELECT * FROM disbursement entity WHERE entity.financepartner_id = :id")
    Flux<Disbursement> findByFinancepartner(Long id);

    @Query("SELECT * FROM disbursement entity WHERE entity.financepartner_id IS NULL")
    Flux<Disbursement> findAllWhereFinancepartnerIsNull();

    @Override
    <S extends Disbursement> Mono<S> save(S entity);

    @Override
    Flux<Disbursement> findAll();

    @Override
    Mono<Disbursement> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface DisbursementRepositoryInternal {
    <S extends Disbursement> Mono<S> save(S entity);

    Flux<Disbursement> findAllBy(Pageable pageable);

    Flux<Disbursement> findAll();

    Mono<Disbursement> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Disbursement> findAllBy(Pageable pageable, Criteria criteria);

    Mono<Disbursement> findOneWithEagerRelationships(Long id);

    Flux<Disbursement> findAllWithEagerRelationships();

    Flux<Disbursement> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
