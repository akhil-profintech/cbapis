package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.CollectionTransactionDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CollectionTransactionDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CollectionTransactionDetailsRepository
    extends ReactiveCrudRepository<CollectionTransactionDetails, Long>, CollectionTransactionDetailsRepositoryInternal {
    Flux<CollectionTransactionDetails> findAllBy(Pageable pageable);

    @Override
    Mono<CollectionTransactionDetails> findOneWithEagerRelationships(Long id);

    @Override
    Flux<CollectionTransactionDetails> findAllWithEagerRelationships();

    @Override
    Flux<CollectionTransactionDetails> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM collection_transaction_details entity WHERE entity.disbursement_id = :id")
    Flux<CollectionTransactionDetails> findByDisbursement(Long id);

    @Query("SELECT * FROM collection_transaction_details entity WHERE entity.disbursement_id IS NULL")
    Flux<CollectionTransactionDetails> findAllWhereDisbursementIsNull();

    @Query("SELECT * FROM collection_transaction_details entity WHERE entity.repayment_id = :id")
    Flux<CollectionTransactionDetails> findByRepayment(Long id);

    @Query("SELECT * FROM collection_transaction_details entity WHERE entity.repayment_id IS NULL")
    Flux<CollectionTransactionDetails> findAllWhereRepaymentIsNull();

    @Override
    <S extends CollectionTransactionDetails> Mono<S> save(S entity);

    @Override
    Flux<CollectionTransactionDetails> findAll();

    @Override
    Mono<CollectionTransactionDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CollectionTransactionDetailsRepositoryInternal {
    <S extends CollectionTransactionDetails> Mono<S> save(S entity);

    Flux<CollectionTransactionDetails> findAllBy(Pageable pageable);

    Flux<CollectionTransactionDetails> findAll();

    Mono<CollectionTransactionDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CollectionTransactionDetails> findAllBy(Pageable pageable, Criteria criteria);

    Mono<CollectionTransactionDetails> findOneWithEagerRelationships(Long id);

    Flux<CollectionTransactionDetails> findAllWithEagerRelationships();

    Flux<CollectionTransactionDetails> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
