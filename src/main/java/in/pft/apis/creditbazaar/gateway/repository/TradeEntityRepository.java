package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.TradeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the TradeEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TradeEntityRepository extends ReactiveCrudRepository<TradeEntity, Long>, TradeEntityRepositoryInternal {
    Flux<TradeEntity> findAllBy(Pageable pageable);

    @Override
    <S extends TradeEntity> Mono<S> save(S entity);

    @Override
    Flux<TradeEntity> findAll();

    @Override
    Mono<TradeEntity> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface TradeEntityRepositoryInternal {
    <S extends TradeEntity> Mono<S> save(S entity);

    Flux<TradeEntity> findAllBy(Pageable pageable);

    Flux<TradeEntity> findAll();

    Mono<TradeEntity> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<TradeEntity> findAllBy(Pageable pageable, Criteria criteria);
}
