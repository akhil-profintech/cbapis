package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.Repayment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Repayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RepaymentRepository extends ReactiveCrudRepository<Repayment, Long>, RepaymentRepositoryInternal {
    Flux<Repayment> findAllBy(Pageable pageable);

    @Override
    Mono<Repayment> findOneWithEagerRelationships(Long id);

    @Override
    Flux<Repayment> findAllWithEagerRelationships();

    @Override
    Flux<Repayment> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM repayment entity WHERE entity.financerequest_id = :id")
    Flux<Repayment> findByFinancerequest(Long id);

    @Query("SELECT * FROM repayment entity WHERE entity.financerequest_id IS NULL")
    Flux<Repayment> findAllWhereFinancerequestIsNull();

    @Override
    <S extends Repayment> Mono<S> save(S entity);

    @Override
    Flux<Repayment> findAll();

    @Override
    Mono<Repayment> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface RepaymentRepositoryInternal {
    <S extends Repayment> Mono<S> save(S entity);

    Flux<Repayment> findAllBy(Pageable pageable);

    Flux<Repayment> findAll();

    Mono<Repayment> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Repayment> findAllBy(Pageable pageable, Criteria criteria);

    Mono<Repayment> findOneWithEagerRelationships(Long id);

    Flux<Repayment> findAllWithEagerRelationships();

    Flux<Repayment> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
