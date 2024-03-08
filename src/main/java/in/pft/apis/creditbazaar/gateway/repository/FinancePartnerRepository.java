package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.FinancePartner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the FinancePartner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinancePartnerRepository extends ReactiveCrudRepository<FinancePartner, Long>, FinancePartnerRepositoryInternal,FinancePartnerCustomRepository {
    Flux<FinancePartner> findAllBy(Pageable pageable);

    @Override
    <S extends FinancePartner> Mono<S> save(S entity);

    @Override
    Flux<FinancePartner> findAll();

    @Override
    Mono<FinancePartner> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface FinancePartnerRepositoryInternal {
    <S extends FinancePartner> Mono<S> save(S entity);

    Flux<FinancePartner> findAllBy(Pageable pageable);

    Flux<FinancePartner> findAll();

    Mono<FinancePartner> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<FinancePartner> findAllBy(Pageable pageable, Criteria criteria);
}
