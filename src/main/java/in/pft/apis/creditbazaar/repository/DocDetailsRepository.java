package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.DocDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the DocDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocDetailsRepository extends ReactiveCrudRepository<DocDetails, Long>, DocDetailsRepositoryInternal {
    Flux<DocDetails> findAllBy(Pageable pageable);

    @Override
    Mono<DocDetails> findOneWithEagerRelationships(Long id);

    @Override
    Flux<DocDetails> findAllWithEagerRelationships();

    @Override
    Flux<DocDetails> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM doc_details entity WHERE entity.disbursement_id = :id")
    Flux<DocDetails> findByDisbursement(Long id);

    @Query("SELECT * FROM doc_details entity WHERE entity.disbursement_id IS NULL")
    Flux<DocDetails> findAllWhereDisbursementIsNull();

    @Query("SELECT * FROM doc_details entity WHERE entity.repayment_id = :id")
    Flux<DocDetails> findByRepayment(Long id);

    @Query("SELECT * FROM doc_details entity WHERE entity.repayment_id IS NULL")
    Flux<DocDetails> findAllWhereRepaymentIsNull();

    @Query("SELECT * FROM doc_details entity WHERE entity.participantsettlement_id = :id")
    Flux<DocDetails> findByParticipantsettlement(Long id);

    @Query("SELECT * FROM doc_details entity WHERE entity.participantsettlement_id IS NULL")
    Flux<DocDetails> findAllWhereParticipantsettlementIsNull();

    @Override
    <S extends DocDetails> Mono<S> save(S entity);

    @Override
    Flux<DocDetails> findAll();

    @Override
    Mono<DocDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface DocDetailsRepositoryInternal {
    <S extends DocDetails> Mono<S> save(S entity);

    Flux<DocDetails> findAllBy(Pageable pageable);

    Flux<DocDetails> findAll();

    Mono<DocDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<DocDetails> findAllBy(Pageable pageable, Criteria criteria);

    Mono<DocDetails> findOneWithEagerRelationships(Long id);

    Flux<DocDetails> findAllWithEagerRelationships();

    Flux<DocDetails> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
