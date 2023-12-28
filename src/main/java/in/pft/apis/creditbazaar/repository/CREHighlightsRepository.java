package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.CREHighlights;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CREHighlights entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CREHighlightsRepository extends ReactiveCrudRepository<CREHighlights, Long>, CREHighlightsRepositoryInternal {
    Flux<CREHighlights> findAllBy(Pageable pageable);

    @Override
    Mono<CREHighlights> findOneWithEagerRelationships(Long id);

    @Override
    Flux<CREHighlights> findAllWithEagerRelationships();

    @Override
    Flux<CREHighlights> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM cre_highlights entity WHERE entity.cbcreprocess_id = :id")
    Flux<CREHighlights> findByCbcreprocess(Long id);

    @Query("SELECT * FROM cre_highlights entity WHERE entity.cbcreprocess_id IS NULL")
    Flux<CREHighlights> findAllWhereCbcreprocessIsNull();

    @Query("SELECT * FROM cre_highlights entity WHERE entity.individualassessment_id = :id")
    Flux<CREHighlights> findByIndividualassessment(Long id);

    @Query("SELECT * FROM cre_highlights entity WHERE entity.individualassessment_id IS NULL")
    Flux<CREHighlights> findAllWhereIndividualassessmentIsNull();

    @Override
    <S extends CREHighlights> Mono<S> save(S entity);

    @Override
    Flux<CREHighlights> findAll();

    @Override
    Mono<CREHighlights> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CREHighlightsRepositoryInternal {
    <S extends CREHighlights> Mono<S> save(S entity);

    Flux<CREHighlights> findAllBy(Pageable pageable);

    Flux<CREHighlights> findAll();

    Mono<CREHighlights> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CREHighlights> findAllBy(Pageable pageable, Criteria criteria);

    Mono<CREHighlights> findOneWithEagerRelationships(Long id);

    Flux<CREHighlights> findAllWithEagerRelationships();

    Flux<CREHighlights> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
