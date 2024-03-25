package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.FundsTransfer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the FundsTransfer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FundsTransferRepository extends ReactiveCrudRepository<FundsTransfer, Long>, FundsTransferRepositoryInternal{
    Flux<FundsTransfer> findAllBy(Pageable pageable);

    @Override
    Mono<FundsTransfer> findOneWithEagerRelationships(Long id);

    @Override
    Flux<FundsTransfer> findAllWithEagerRelationships();

    @Override
    Flux<FundsTransfer> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM funds_transfer entity WHERE entity.trade_entity_id = :id")
    Flux<FundsTransfer> findByTradeEntity(Long id);

    @Query("SELECT * FROM funds_transfer entity WHERE entity.trade_entity_id IS NULL")
    Flux<FundsTransfer> findAllWhereTradeEntityIsNull();

    @Override
    <S extends FundsTransfer> Mono<S> save(S entity);

    @Override
    Flux<FundsTransfer> findAll();

    @Override
    Mono<FundsTransfer> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface FundsTransferRepositoryInternal {
    <S extends FundsTransfer> Mono<S> save(S entity);

    Flux<FundsTransfer> findAllBy(Pageable pageable);

    Flux<FundsTransfer> findAll();

    Mono<FundsTransfer> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<FundsTransfer> findAllBy(Pageable pageable, Criteria criteria);

    Mono<FundsTransfer> findOneWithEagerRelationships(Long id);

    Flux<FundsTransfer> findAllWithEagerRelationships();

    Flux<FundsTransfer> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
