package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.Context;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Context entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContextRepository extends ReactiveCrudRepository<Context, Long>, ContextRepositoryInternal {
    Flux<Context> findAllBy(Pageable pageable);

    @Override
    Mono<Context> findOneWithEagerRelationships(Long id);

    @Override
    Flux<Context> findAllWithEagerRelationships();

    @Override
    Flux<Context> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM context entity WHERE entity.action_id = :id")
    Flux<Context> findByAction(Long id);

    @Query("SELECT * FROM context entity WHERE entity.action_id IS NULL")
    Flux<Context> findAllWhereActionIsNull();

    @Override
    <S extends Context> Mono<S> save(S entity);

    @Override
    Flux<Context> findAll();

    @Override
    Mono<Context> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ContextRepositoryInternal {
    <S extends Context> Mono<S> save(S entity);

    Flux<Context> findAllBy(Pageable pageable);

    Flux<Context> findAll();

    Mono<Context> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Context> findAllBy(Pageable pageable, Criteria criteria);

    Mono<Context> findOneWithEagerRelationships(Long id);

    Flux<Context> findAllWithEagerRelationships();

    Flux<Context> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
