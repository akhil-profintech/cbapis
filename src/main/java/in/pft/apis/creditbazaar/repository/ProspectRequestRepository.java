package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.ProspectRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the ProspectRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProspectRequestRepository extends ReactiveCrudRepository<ProspectRequest, Long>, ProspectRequestRepositoryInternal {
    Flux<ProspectRequest> findAllBy(Pageable pageable);

    @Override
    Mono<ProspectRequest> findOneWithEagerRelationships(Long id);

    @Override
    Flux<ProspectRequest> findAllWithEagerRelationships();

    @Override
    Flux<ProspectRequest> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM prospect_request entity WHERE entity.financerequest_id = :id")
    Flux<ProspectRequest> findByFinancerequest(Long id);

    @Query("SELECT * FROM prospect_request entity WHERE entity.financerequest_id IS NULL")
    Flux<ProspectRequest> findAllWhereFinancerequestIsNull();

    @Override
    <S extends ProspectRequest> Mono<S> save(S entity);

    @Override
    Flux<ProspectRequest> findAll();

    @Override
    Mono<ProspectRequest> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ProspectRequestRepositoryInternal {
    <S extends ProspectRequest> Mono<S> save(S entity);

    Flux<ProspectRequest> findAllBy(Pageable pageable);

    Flux<ProspectRequest> findAll();

    Mono<ProspectRequest> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ProspectRequest> findAllBy(Pageable pageable, Criteria criteria);

    Mono<ProspectRequest> findOneWithEagerRelationships(Long id);

    Flux<ProspectRequest> findAllWithEagerRelationships();

    Flux<ProspectRequest> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
