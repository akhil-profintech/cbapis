package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.EscrowAccountDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the EscrowAccountDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EscrowAccountDetailsRepository
    extends ReactiveCrudRepository<EscrowAccountDetails, Long>, EscrowAccountDetailsRepositoryInternal {
    Flux<EscrowAccountDetails> findAllBy(Pageable pageable);

    @Override
    Mono<EscrowAccountDetails> findOneWithEagerRelationships(Long id);

    @Override
    Flux<EscrowAccountDetails> findAllWithEagerRelationships();

    @Override
    Flux<EscrowAccountDetails> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM escrow_account_details entity WHERE entity.disbursement_id = :id")
    Flux<EscrowAccountDetails> findByDisbursement(Long id);

    @Query("SELECT * FROM escrow_account_details entity WHERE entity.disbursement_id IS NULL")
    Flux<EscrowAccountDetails> findAllWhereDisbursementIsNull();

    @Query("SELECT * FROM escrow_account_details entity WHERE entity.repayment_id = :id")
    Flux<EscrowAccountDetails> findByRepayment(Long id);

    @Query("SELECT * FROM escrow_account_details entity WHERE entity.repayment_id IS NULL")
    Flux<EscrowAccountDetails> findAllWhereRepaymentIsNull();

    @Override
    <S extends EscrowAccountDetails> Mono<S> save(S entity);

    @Override
    Flux<EscrowAccountDetails> findAll();

    @Override
    Mono<EscrowAccountDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface EscrowAccountDetailsRepositoryInternal {
    <S extends EscrowAccountDetails> Mono<S> save(S entity);

    Flux<EscrowAccountDetails> findAllBy(Pageable pageable);

    Flux<EscrowAccountDetails> findAll();

    Mono<EscrowAccountDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<EscrowAccountDetails> findAllBy(Pageable pageable, Criteria criteria);

    Mono<EscrowAccountDetails> findOneWithEagerRelationships(Long id);

    Flux<EscrowAccountDetails> findAllWithEagerRelationships();

    Flux<EscrowAccountDetails> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
