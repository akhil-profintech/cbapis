package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.ParticipantSettlement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the ParticipantSettlement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParticipantSettlementRepository
    extends ReactiveCrudRepository<ParticipantSettlement, Long>, ParticipantSettlementRepositoryInternal,ParticipantSettlementCustomRepo {
    Flux<ParticipantSettlement> findAllBy(Pageable pageable);

    @Override
    Mono<ParticipantSettlement> findOneWithEagerRelationships(Long id);

    @Override
    Flux<ParticipantSettlement> findAllWithEagerRelationships();

    @Override
    Flux<ParticipantSettlement> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM participant_settlement entity WHERE entity.settlement_id = :id")
    Flux<ParticipantSettlement> findBySettlement(Long id);

    @Query("SELECT * FROM participant_settlement entity WHERE entity.settlement_id IS NULL")
    Flux<ParticipantSettlement> findAllWhereSettlementIsNull();

    @Override
    <S extends ParticipantSettlement> Mono<S> save(S entity);

    @Override
    Flux<ParticipantSettlement> findAll();

    @Override
    Mono<ParticipantSettlement> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ParticipantSettlementRepositoryInternal {
    <S extends ParticipantSettlement> Mono<S> save(S entity);

    Flux<ParticipantSettlement> findAllBy(Pageable pageable);

    Flux<ParticipantSettlement> findAll();

    Mono<ParticipantSettlement> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ParticipantSettlement> findAllBy(Pageable pageable, Criteria criteria);

    Mono<ParticipantSettlement> findOneWithEagerRelationships(Long id);

    Flux<ParticipantSettlement> findAllWithEagerRelationships();

    Flux<ParticipantSettlement> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
