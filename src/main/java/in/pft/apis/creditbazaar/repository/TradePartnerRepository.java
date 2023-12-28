package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.TradePartner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the TradePartner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TradePartnerRepository extends ReactiveCrudRepository<TradePartner, Long>, TradePartnerRepositoryInternal {
    Flux<TradePartner> findAllBy(Pageable pageable);

    @Override
    <S extends TradePartner> Mono<S> save(S entity);

    @Override
    Flux<TradePartner> findAll();

    @Override
    Mono<TradePartner> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface TradePartnerRepositoryInternal {
    <S extends TradePartner> Mono<S> save(S entity);

    Flux<TradePartner> findAllBy(Pageable pageable);

    Flux<TradePartner> findAll();

    Mono<TradePartner> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<TradePartner> findAllBy(Pageable pageable, Criteria criteria);
}
