package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.Settlement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Settlement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SettlementRepository extends ReactiveCrudRepository<Settlement, Long>, SettlementRepositoryInternal ,SettlementCustomRepository{
    Flux<Settlement> findAllBy(Pageable pageable);

    @Override
    Mono<Settlement> findOneWithEagerRelationships(Long id);

    @Override
    Flux<Settlement> findAllWithEagerRelationships();

    @Override
    Flux<Settlement> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM settlement entity WHERE entity.financerequest_id = :id")
    Flux<Settlement> findByFinancerequest(Long id);

    @Query("SELECT * FROM settlement entity WHERE entity.financerequest_id IS NULL")
    Flux<Settlement> findAllWhereFinancerequestIsNull();

    @Override
    <S extends Settlement> Mono<S> save(S entity);

    @Override
    Flux<Settlement> findAll();

    @Override
    Mono<Settlement> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SettlementRepositoryInternal {
    <S extends Settlement> Mono<S> save(S entity);

    Flux<Settlement> findAllBy(Pageable pageable);

    Flux<Settlement> findAll();

    Mono<Settlement> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Settlement> findAllBy(Pageable pageable, Criteria criteria);

    Mono<Settlement> findOneWithEagerRelationships(Long id);

    Flux<Settlement> findAllWithEagerRelationships();

    Flux<Settlement> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
