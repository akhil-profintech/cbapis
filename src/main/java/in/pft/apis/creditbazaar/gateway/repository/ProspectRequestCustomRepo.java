package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.ProspectRequest;
import in.pft.apis.creditbazaar.gateway.domain.Repayment;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProspectRequestCustomRepo
{
    Flux<ProspectRequest> findAllByFilter(String filter, Pageable pageable);

    Mono<Long> countByFilter(String filter);
}
