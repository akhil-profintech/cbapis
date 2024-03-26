package in.pft.apis.creditbazaar.gateway.repository;

import in.pft.apis.creditbazaar.gateway.domain.Gstin;
import in.pft.apis.creditbazaar.gateway.domain.Repayment;
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
public class GstinCustomRepoImpl implements GstinCustomRepo
{
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    @Override
    public Flux<Gstin> findAllByFilter(String filter, Pageable pageable) {
        String[] conditions = filter.split(",");
        Criteria criteria = Criteria.empty();
        for (String condition : conditions) {
            String[] parts = condition.split(":");
            if (parts.length == 2) {
                criteria = criteria.and(where(parts[0]).is(parts[1]));
            }
        }
        return r2dbcEntityTemplate
            .select(Gstin.class)
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
            .select(Gstin.class)
            .matching(query(criteria))
            .count();
    }
}
