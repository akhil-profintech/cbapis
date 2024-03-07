package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.UpdateVA;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the UpdateVA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UpdateVARepository extends ReactiveCrudRepository<UpdateVA, Long>, UpdateVARepositoryInternal {
    Flux<UpdateVA> findAllBy(Pageable pageable);

    @Override
    Mono<UpdateVA> findOneWithEagerRelationships(Long id);

    @Override
    Flux<UpdateVA> findAllWithEagerRelationships();

    @Override
    Flux<UpdateVA> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM update_va entity WHERE entity.trade_entity_id = :id")
    Flux<UpdateVA> findByTradeEntity(Long id);

    @Query("SELECT * FROM update_va entity WHERE entity.trade_entity_id IS NULL")
    Flux<UpdateVA> findAllWhereTradeEntityIsNull();

    @Override
    <S extends UpdateVA> Mono<S> save(S entity);

    @Override
    Flux<UpdateVA> findAll();

    @Override
    Mono<UpdateVA> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface UpdateVARepositoryInternal {
    <S extends UpdateVA> Mono<S> save(S entity);

    Flux<UpdateVA> findAllBy(Pageable pageable);

    Flux<UpdateVA> findAll();

    Mono<UpdateVA> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<UpdateVA> findAllBy(Pageable pageable, Criteria criteria);

    Mono<UpdateVA> findOneWithEagerRelationships(Long id);

    Flux<UpdateVA> findAllWithEagerRelationships();

    Flux<UpdateVA> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
