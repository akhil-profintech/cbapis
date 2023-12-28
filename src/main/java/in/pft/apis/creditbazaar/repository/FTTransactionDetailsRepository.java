package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.FTTransactionDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the FTTransactionDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FTTransactionDetailsRepository
    extends ReactiveCrudRepository<FTTransactionDetails, Long>, FTTransactionDetailsRepositoryInternal {
    Flux<FTTransactionDetails> findAllBy(Pageable pageable);

    @Override
    Mono<FTTransactionDetails> findOneWithEagerRelationships(Long id);

    @Override
    Flux<FTTransactionDetails> findAllWithEagerRelationships();

    @Override
    Flux<FTTransactionDetails> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM ft_transaction_details entity WHERE entity.disbursement_id = :id")
    Flux<FTTransactionDetails> findByDisbursement(Long id);

    @Query("SELECT * FROM ft_transaction_details entity WHERE entity.disbursement_id IS NULL")
    Flux<FTTransactionDetails> findAllWhereDisbursementIsNull();

    @Query("SELECT * FROM ft_transaction_details entity WHERE entity.repayment_id = :id")
    Flux<FTTransactionDetails> findByRepayment(Long id);

    @Query("SELECT * FROM ft_transaction_details entity WHERE entity.repayment_id IS NULL")
    Flux<FTTransactionDetails> findAllWhereRepaymentIsNull();

    @Query("SELECT * FROM ft_transaction_details entity WHERE entity.participantsettlement_id = :id")
    Flux<FTTransactionDetails> findByParticipantsettlement(Long id);

    @Query("SELECT * FROM ft_transaction_details entity WHERE entity.participantsettlement_id IS NULL")
    Flux<FTTransactionDetails> findAllWhereParticipantsettlementIsNull();

    @Override
    <S extends FTTransactionDetails> Mono<S> save(S entity);

    @Override
    Flux<FTTransactionDetails> findAll();

    @Override
    Mono<FTTransactionDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface FTTransactionDetailsRepositoryInternal {
    <S extends FTTransactionDetails> Mono<S> save(S entity);

    Flux<FTTransactionDetails> findAllBy(Pageable pageable);

    Flux<FTTransactionDetails> findAll();

    Mono<FTTransactionDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<FTTransactionDetails> findAllBy(Pageable pageable, Criteria criteria);

    Mono<FTTransactionDetails> findOneWithEagerRelationships(Long id);

    Flux<FTTransactionDetails> findAllWithEagerRelationships();

    Flux<FTTransactionDetails> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
