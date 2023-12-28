package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.CBCREProcess;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CBCREProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CBCREProcessRepository extends ReactiveCrudRepository<CBCREProcess, Long>, CBCREProcessRepositoryInternal {
    Flux<CBCREProcess> findAllBy(Pageable pageable);

    @Override
    <S extends CBCREProcess> Mono<S> save(S entity);

    @Override
    Flux<CBCREProcess> findAll();

    @Override
    Mono<CBCREProcess> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CBCREProcessRepositoryInternal {
    <S extends CBCREProcess> Mono<S> save(S entity);

    Flux<CBCREProcess> findAllBy(Pageable pageable);

    Flux<CBCREProcess> findAll();

    Mono<CBCREProcess> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CBCREProcess> findAllBy(Pageable pageable, Criteria criteria);
}
