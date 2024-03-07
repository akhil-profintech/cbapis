package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.TradeChannel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the TradeChannel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TradeChannelRepository extends ReactiveCrudRepository<TradeChannel, Long>, TradeChannelRepositoryInternal {
    Flux<TradeChannel> findAllBy(Pageable pageable);

    @Override
    <S extends TradeChannel> Mono<S> save(S entity);

    @Override
    Flux<TradeChannel> findAll();

    @Override
    Mono<TradeChannel> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface TradeChannelRepositoryInternal {
    <S extends TradeChannel> Mono<S> save(S entity);

    Flux<TradeChannel> findAllBy(Pageable pageable);

    Flux<TradeChannel> findAll();

    Mono<TradeChannel> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<TradeChannel> findAllBy(Pageable pageable, Criteria criteria);
}
