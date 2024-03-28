package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.TradeChannel;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TradeChannelCustomRepo
{
    Flux<TradeChannel> findAllByFilter(String filter, Pageable pageable);

    Mono<Long> countByFilter(String filter);
}
