package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.FinancePartner;
import in.pft.apis.creditbazaar.gateway.domain.FinanceRequest;
import in.pft.apis.creditbazaar.gateway.domain.PlacedOffer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

@Repository
@RequiredArgsConstructor
public class PlacedOfferCustomRepositoryImpl implements PlacedOfferCustomRepository
{
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    @Override
    public Flux<PlacedOffer> findAllByFilter(String filter, Pageable pageable) {
        String[] conditions = filter.split(",");
        Criteria criteria = Criteria.empty();
        for (String condition : conditions) {
            String[] parts = condition.split(":");
            if (parts.length == 2) {
                criteria = criteria.and(where(parts[0]).is(parts[1]));
            }
        }
        return r2dbcEntityTemplate
            .select(PlacedOffer.class)
            .matching(query(criteria).with(pageable))
            .all();
    }

    @Override
    public Mono<Long> countByFilter(String filter) {
        String[] conditions = filter.split(",");
        Criteria criteria = Criteria.empty();
        for (String condition : conditions) {
            String[] parts = condition.split(":");
            if (parts.length == 2) {
                criteria = criteria.and(where(parts[0]).is(parts[1]));
            }
        }
        return r2dbcEntityTemplate
            .select(PlacedOffer.class)
            .matching(query(criteria))
            .count();
    }
}
