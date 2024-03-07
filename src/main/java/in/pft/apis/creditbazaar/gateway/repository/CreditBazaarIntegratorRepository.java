package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.CreditBazaarIntegrator;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CreditBazaarIntegrator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreditBazaarIntegratorRepository
    extends ReactiveCrudRepository<CreditBazaarIntegrator, Long>, CreditBazaarIntegratorRepositoryInternal {
    Flux<CreditBazaarIntegrator> findAllBy(Pageable pageable);

    @Override
    <S extends CreditBazaarIntegrator> Mono<S> save(S entity);

    @Override
    Flux<CreditBazaarIntegrator> findAll();

    @Override
    Mono<CreditBazaarIntegrator> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CreditBazaarIntegratorRepositoryInternal {
    <S extends CreditBazaarIntegrator> Mono<S> save(S entity);

    Flux<CreditBazaarIntegrator> findAllBy(Pageable pageable);

    Flux<CreditBazaarIntegrator> findAll();

    Mono<CreditBazaarIntegrator> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CreditBazaarIntegrator> findAllBy(Pageable pageable, Criteria criteria);
}
