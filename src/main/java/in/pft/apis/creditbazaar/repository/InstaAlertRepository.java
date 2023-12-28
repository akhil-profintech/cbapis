package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.InstaAlert;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the InstaAlert entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstaAlertRepository extends ReactiveCrudRepository<InstaAlert, Long>, InstaAlertRepositoryInternal {
    Flux<InstaAlert> findAllBy(Pageable pageable);

    @Override
    Mono<InstaAlert> findOneWithEagerRelationships(Long id);

    @Override
    Flux<InstaAlert> findAllWithEagerRelationships();

    @Override
    Flux<InstaAlert> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM insta_alert entity WHERE entity.trade_entity_id = :id")
    Flux<InstaAlert> findByTradeEntity(Long id);

    @Query("SELECT * FROM insta_alert entity WHERE entity.trade_entity_id IS NULL")
    Flux<InstaAlert> findAllWhereTradeEntityIsNull();

    @Override
    <S extends InstaAlert> Mono<S> save(S entity);

    @Override
    Flux<InstaAlert> findAll();

    @Override
    Mono<InstaAlert> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface InstaAlertRepositoryInternal {
    <S extends InstaAlert> Mono<S> save(S entity);

    Flux<InstaAlert> findAllBy(Pageable pageable);

    Flux<InstaAlert> findAll();

    Mono<InstaAlert> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<InstaAlert> findAllBy(Pageable pageable, Criteria criteria);

    Mono<InstaAlert> findOneWithEagerRelationships(Long id);

    Flux<InstaAlert> findAllWithEagerRelationships();

    Flux<InstaAlert> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
