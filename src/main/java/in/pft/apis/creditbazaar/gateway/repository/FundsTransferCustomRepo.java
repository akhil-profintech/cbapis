package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.FinanceRequestMapping;
import in.pft.apis.creditbazaar.gateway.domain.FundsTransfer;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FundsTransferCustomRepo
{
    Flux<FundsTransfer> findAllByFilter(String filter, Pageable pageable);

    Mono<Long> countByFilter(String filter);
}
