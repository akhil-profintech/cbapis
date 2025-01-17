package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.Authority;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

/**
 * Spring Data R2DBC repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends R2dbcRepository<Authority, String> {}
