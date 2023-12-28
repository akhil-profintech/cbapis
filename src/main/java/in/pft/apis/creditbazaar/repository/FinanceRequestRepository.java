package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.FinanceRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the FinanceRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinanceRequestRepository extends ReactiveCrudRepository<FinanceRequest, Long>, FinanceRequestRepositoryInternal {
    Flux<FinanceRequest> findAllBy(Pageable pageable);

    @Override
    Mono<FinanceRequest> findOneWithEagerRelationships(Long id);

    @Override
    Flux<FinanceRequest> findAllWithEagerRelationships();

    @Override
    Flux<FinanceRequest> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM finance_request entity WHERE entity.anchortrader_id = :id")
    Flux<FinanceRequest> findByAnchortrader(Long id);

    @Query("SELECT * FROM finance_request entity WHERE entity.anchortrader_id IS NULL")
    Flux<FinanceRequest> findAllWhereAnchortraderIsNull();

    @Override
    <S extends FinanceRequest> Mono<S> save(S entity);

    @Override
    Flux<FinanceRequest> findAll();

    @Override
    Mono<FinanceRequest> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface FinanceRequestRepositoryInternal {
    <S extends FinanceRequest> Mono<S> save(S entity);

    Flux<FinanceRequest> findAllBy(Pageable pageable);

    Flux<FinanceRequest> findAll();

    Mono<FinanceRequest> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<FinanceRequest> findAllBy(Pageable pageable, Criteria criteria);

    Mono<FinanceRequest> findOneWithEagerRelationships(Long id);

    Flux<FinanceRequest> findAllWithEagerRelationships();

    Flux<FinanceRequest> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
