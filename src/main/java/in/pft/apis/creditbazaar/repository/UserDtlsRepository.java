package in.pft.apis.creditbazaar.repository;

import in.pft.apis.creditbazaar.domain.UserDtls;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the UserDtls entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserDtlsRepository extends ReactiveCrudRepository<UserDtls, Long>, UserDtlsRepositoryInternal {
    Flux<UserDtls> findAllBy(Pageable pageable);

    @Override
    Mono<UserDtls> findOneWithEagerRelationships(Long id);

    @Override
    Flux<UserDtls> findAllWithEagerRelationships();

    @Override
    Flux<UserDtls> findAllWithEagerRelationships(Pageable page);

    @Query("SELECT * FROM user_dtls entity WHERE entity.organization_id = :id")
    Flux<UserDtls> findByOrganization(Long id);

    @Query("SELECT * FROM user_dtls entity WHERE entity.organization_id IS NULL")
    Flux<UserDtls> findAllWhereOrganizationIsNull();

    @Override
    <S extends UserDtls> Mono<S> save(S entity);

    @Override
    Flux<UserDtls> findAll();

    @Override
    Mono<UserDtls> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface UserDtlsRepositoryInternal {
    <S extends UserDtls> Mono<S> save(S entity);

    Flux<UserDtls> findAllBy(Pageable pageable);

    Flux<UserDtls> findAll();

    Mono<UserDtls> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<UserDtls> findAllBy(Pageable pageable, Criteria criteria);

    Mono<UserDtls> findOneWithEagerRelationships(Long id);

    Flux<UserDtls> findAllWithEagerRelationships();

    Flux<UserDtls> findAllWithEagerRelationships(Pageable page);

    Mono<Void> deleteById(Long id);
}
