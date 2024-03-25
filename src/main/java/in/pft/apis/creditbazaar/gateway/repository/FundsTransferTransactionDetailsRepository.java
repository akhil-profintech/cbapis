package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.FundsTransferTransactionDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the FundsTransferTransactionDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FundsTransferTransactionDetailsRepository
    extends ReactiveCrudRepository<FundsTransferTransactionDetails, Long>, FundsTransferTransactionDetailsRepositoryInternal,FundsTransferTransactionDetailsCustomRepo {
    Flux<FundsTransferTransactionDetails> findAllBy(Pageable pageable);

    @Override
    Mono<FundsTransferTransactionDetails> findOneWithEagerRelationships(Long id);

    @Override
    Flux<FundsTransferTransactionDetails> findAllWithEagerRelationships();

    @Override
    Flux<FundsTransferTransactionDetails> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM funds_transfer_transaction_details entity WHERE entity.participantsettlement_id = :id")
    Flux<FundsTransferTransactionDetails> findByParticipantsettlement(Long id);

    @Query("SELECT * FROM funds_transfer_transaction_details entity WHERE entity.participantsettlement_id IS NULL")
    Flux<FundsTransferTransactionDetails> findAllWhereParticipantsettlementIsNull();

    @Query("SELECT * FROM funds_transfer_transaction_details entity WHERE entity.disbursement_id = :id")
    Flux<FundsTransferTransactionDetails> findByDisbursement(Long id);

    @Query("SELECT * FROM funds_transfer_transaction_details entity WHERE entity.disbursement_id IS NULL")
    Flux<FundsTransferTransactionDetails> findAllWhereDisbursementIsNull();

    @Query("SELECT * FROM funds_transfer_transaction_details entity WHERE entity.repayment_id = :id")
    Flux<FundsTransferTransactionDetails> findByRepayment(Long id);

    @Query("SELECT * FROM funds_transfer_transaction_details entity WHERE entity.repayment_id IS NULL")
    Flux<FundsTransferTransactionDetails> findAllWhereRepaymentIsNull();

    @Override
    <S extends FundsTransferTransactionDetails> Mono<S> save(S entity);

    @Override
    Flux<FundsTransferTransactionDetails> findAll();

    @Override
    Mono<FundsTransferTransactionDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface FundsTransferTransactionDetailsRepositoryInternal {
    <S extends FundsTransferTransactionDetails> Mono<S> save(S entity);

    Flux<FundsTransferTransactionDetails> findAllBy(Pageable pageable);

    Flux<FundsTransferTransactionDetails> findAll();

    Mono<FundsTransferTransactionDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<FundsTransferTransactionDetails> findAllBy(Pageable pageable, Criteria criteria);

    Mono<FundsTransferTransactionDetails> findOneWithEagerRelationships(Long id);

    Flux<FundsTransferTransactionDetails> findAllWithEagerRelationships();

    Flux<FundsTransferTransactionDetails> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
