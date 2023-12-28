package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.AnchorTrader;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the AnchorTrader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnchorTraderRepository extends ReactiveCrudRepository<AnchorTrader, Long>, AnchorTraderRepositoryInternal {
    Flux<AnchorTrader> findAllBy(Pageable pageable);

    @Override
    <S extends AnchorTrader> Mono<S> save(S entity);

    @Override
    Flux<AnchorTrader> findAll();

    @Override
    Mono<AnchorTrader> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface AnchorTraderRepositoryInternal {
    <S extends AnchorTrader> Mono<S> save(S entity);

    Flux<AnchorTrader> findAllBy(Pageable pageable);

    Flux<AnchorTrader> findAll();

    Mono<AnchorTrader> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<AnchorTrader> findAllBy(Pageable pageable, Criteria criteria);
}
