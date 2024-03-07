package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.VANumber;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the VANumber entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VANumberRepository extends ReactiveCrudRepository<VANumber, Long>, VANumberRepositoryInternal {
    Flux<VANumber> findAllBy(Pageable pageable);

    @Override
    Mono<VANumber> findOneWithEagerRelationships(Long id);

    @Override
    Flux<VANumber> findAllWithEagerRelationships();

    @Override
    Flux<VANumber> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM va_number entity WHERE entity.trade_entity_id = :id")
    Flux<VANumber> findByTradeEntity(Long id);

    @Query("SELECT * FROM va_number entity WHERE entity.trade_entity_id IS NULL")
    Flux<VANumber> findAllWhereTradeEntityIsNull();

    @Override
    <S extends VANumber> Mono<S> save(S entity);

    @Override
    Flux<VANumber> findAll();

    @Override
    Mono<VANumber> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface VANumberRepositoryInternal {
    <S extends VANumber> Mono<S> save(S entity);

    Flux<VANumber> findAllBy(Pageable pageable);

    Flux<VANumber> findAll();

    Mono<VANumber> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<VANumber> findAllBy(Pageable pageable, Criteria criteria);

    Mono<VANumber> findOneWithEagerRelationships(Long id);

    Flux<VANumber> findAllWithEagerRelationships();

    Flux<VANumber> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
