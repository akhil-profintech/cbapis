package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.CreditAccountDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CreditAccountDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreditAccountDetailsRepository
    extends ReactiveCrudRepository<CreditAccountDetails, Long>, CreditAccountDetailsRepositoryInternal,CreditAccountDetailsCustomRepo {
    Flux<CreditAccountDetails> findAllBy(Pageable pageable);

    @Override
    Mono<CreditAccountDetails> findOneWithEagerRelationships(Long id);

    @Override
    Flux<CreditAccountDetails> findAllWithEagerRelationships();

    @Override
    Flux<CreditAccountDetails> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM credit_account_details entity WHERE entity.disbursement_id = :id")
    Flux<CreditAccountDetails> findByDisbursement(Long id);

    @Query("SELECT * FROM credit_account_details entity WHERE entity.disbursement_id IS NULL")
    Flux<CreditAccountDetails> findAllWhereDisbursementIsNull();

    @Query("SELECT * FROM credit_account_details entity WHERE entity.repayment_id = :id")
    Flux<CreditAccountDetails> findByRepayment(Long id);

    @Query("SELECT * FROM credit_account_details entity WHERE entity.repayment_id IS NULL")
    Flux<CreditAccountDetails> findAllWhereRepaymentIsNull();

    @Override
    <S extends CreditAccountDetails> Mono<S> save(S entity);

    @Override
    Flux<CreditAccountDetails> findAll();

    @Override
    Mono<CreditAccountDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CreditAccountDetailsRepositoryInternal {
    <S extends CreditAccountDetails> Mono<S> save(S entity);

    Flux<CreditAccountDetails> findAllBy(Pageable pageable);

    Flux<CreditAccountDetails> findAll();

    Mono<CreditAccountDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CreditAccountDetails> findAllBy(Pageable pageable, Criteria criteria);

    Mono<CreditAccountDetails> findOneWithEagerRelationships(Long id);

    Flux<CreditAccountDetails> findAllWithEagerRelationships();

    Flux<CreditAccountDetails> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
