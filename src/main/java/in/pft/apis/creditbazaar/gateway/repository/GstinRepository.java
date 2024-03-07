package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.Gstin;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Gstin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GstinRepository extends ReactiveCrudRepository<Gstin, Long>, GstinRepositoryInternal {
    Flux<Gstin> findAllBy(Pageable pageable);

    @Override
    Mono<Gstin> findOneWithEagerRelationships(Long id);

    @Override
    Flux<Gstin> findAllWithEagerRelationships();

    @Override
    Flux<Gstin> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM gstin entity WHERE entity.organization_id = :id")
    Flux<Gstin> findByOrganization(Long id);

    @Query("SELECT * FROM gstin entity WHERE entity.organization_id IS NULL")
    Flux<Gstin> findAllWhereOrganizationIsNull();

    @Override
    <S extends Gstin> Mono<S> save(S entity);

    @Override
    Flux<Gstin> findAll();

    @Override
    Mono<Gstin> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface GstinRepositoryInternal {
    <S extends Gstin> Mono<S> save(S entity);

    Flux<Gstin> findAllBy(Pageable pageable);

    Flux<Gstin> findAll();

    Mono<Gstin> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Gstin> findAllBy(Pageable pageable, Criteria criteria);

    Mono<Gstin> findOneWithEagerRelationships(Long id);

    Flux<Gstin> findAllWithEagerRelationships();

    Flux<Gstin> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
