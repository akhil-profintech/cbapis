package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.EscrowTransactionDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the EscrowTransactionDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EscrowTransactionDetailsRepository
    extends ReactiveCrudRepository<EscrowTransactionDetails, Long>, EscrowTransactionDetailsRepositoryInternal {
    Flux<EscrowTransactionDetails> findAllBy(Pageable pageable);

    @Override
    Mono<EscrowTransactionDetails> findOneWithEagerRelationships(Long id);

    @Override
    Flux<EscrowTransactionDetails> findAllWithEagerRelationships();

    @Override
    Flux<EscrowTransactionDetails> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM escrow_transaction_details entity WHERE entity.disbursement_id = :id")
    Flux<EscrowTransactionDetails> findByDisbursement(Long id);

    @Query("SELECT * FROM escrow_transaction_details entity WHERE entity.disbursement_id IS NULL")
    Flux<EscrowTransactionDetails> findAllWhereDisbursementIsNull();

    @Query("SELECT * FROM escrow_transaction_details entity WHERE entity.repayment_id = :id")
    Flux<EscrowTransactionDetails> findByRepayment(Long id);

    @Query("SELECT * FROM escrow_transaction_details entity WHERE entity.repayment_id IS NULL")
    Flux<EscrowTransactionDetails> findAllWhereRepaymentIsNull();

    @Override
    <S extends EscrowTransactionDetails> Mono<S> save(S entity);

    @Override
    Flux<EscrowTransactionDetails> findAll();

    @Override
    Mono<EscrowTransactionDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface EscrowTransactionDetailsRepositoryInternal {
    <S extends EscrowTransactionDetails> Mono<S> save(S entity);

    Flux<EscrowTransactionDetails> findAllBy(Pageable pageable);

    Flux<EscrowTransactionDetails> findAll();

    Mono<EscrowTransactionDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<EscrowTransactionDetails> findAllBy(Pageable pageable, Criteria criteria);

    Mono<EscrowTransactionDetails> findOneWithEagerRelationships(Long id);

    Flux<EscrowTransactionDetails> findAllWithEagerRelationships();

    Flux<EscrowTransactionDetails> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
