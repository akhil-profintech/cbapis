package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.Trade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Trade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TradeRepository extends ReactiveCrudRepository<Trade, Long>, TradeRepositoryInternal,TradeCustomRepository {
    Flux<Trade> findAllBy(Pageable pageable);

    @Override
    Mono<Trade> findOneWithEagerRelationships(Long id);

    @Override
    Flux<Trade> findAllWithEagerRelationships();

    @Override
    Flux<Trade> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM trade entity WHERE entity.financerequest_id = :id")
    Flux<Trade> findByFinancerequest(Long id);

    @Query("SELECT * FROM trade entity WHERE entity.financerequest_id IS NULL")
    Flux<Trade> findAllWhereFinancerequestIsNull();

    @Query("SELECT * FROM trade entity WHERE entity.anchortrader_id = :id")
    Flux<Trade> findByAnchortrader(Long id);

    @Query("SELECT * FROM trade entity WHERE entity.anchortrader_id IS NULL")
    Flux<Trade> findAllWhereAnchortraderIsNull();

    @Query("SELECT * FROM trade entity WHERE entity.tradepartner_id = :id")
    Flux<Trade> findByTradepartner(Long id);

    @Query("SELECT * FROM trade entity WHERE entity.tradepartner_id IS NULL")
    Flux<Trade> findAllWhereTradepartnerIsNull();

    @Override
    <S extends Trade> Mono<S> save(S entity);

    @Override
    Flux<Trade> findAll();

    @Override
    Mono<Trade> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface TradeRepositoryInternal {
    <S extends Trade> Mono<S> save(S entity);

    Flux<Trade> findAllBy(Pageable pageable);

    Flux<Trade> findAll();

    Mono<Trade> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Trade> findAllBy(Pageable pageable, Criteria criteria);

    Mono<Trade> findOneWithEagerRelationships(Long id);

    Flux<Trade> findAllWithEagerRelationships();

    Flux<Trade> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
