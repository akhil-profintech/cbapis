package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.ClientCodes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the ClientCodes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientCodesRepository extends ReactiveCrudRepository<ClientCodes, Long>, ClientCodesRepositoryInternal {
    Flux<ClientCodes> findAllBy(Pageable pageable);

    @Override
    <S extends ClientCodes> Mono<S> save(S entity);

    @Override
    Flux<ClientCodes> findAll();

    @Override
    Mono<ClientCodes> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ClientCodesRepositoryInternal {
    <S extends ClientCodes> Mono<S> save(S entity);

    Flux<ClientCodes> findAllBy(Pageable pageable);

    Flux<ClientCodes> findAll();

    Mono<ClientCodes> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ClientCodes> findAllBy(Pageable pageable, Criteria criteria);
}
