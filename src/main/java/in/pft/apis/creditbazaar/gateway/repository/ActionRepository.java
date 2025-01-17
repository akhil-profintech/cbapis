package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.Action;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Action entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActionRepository extends ReactiveCrudRepository<Action, Long>, ActionRepositoryInternal {
    Flux<Action> findAllBy(Pageable pageable);

    @Override
    <S extends Action> Mono<S> save(S entity);

    @Override
    Flux<Action> findAll();

    @Override
    Mono<Action> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ActionRepositoryInternal {
    <S extends Action> Mono<S> save(S entity);

    Flux<Action> findAllBy(Pageable pageable);

    Flux<Action> findAll();

    Mono<Action> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Action> findAllBy(Pageable pageable, Criteria criteria);
}
