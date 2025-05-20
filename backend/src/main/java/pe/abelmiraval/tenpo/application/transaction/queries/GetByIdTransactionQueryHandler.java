package pe.abelmiraval.tenpo.application.transaction.queries;

import org.springframework.stereotype.Service;
import pe.abelmiraval.tenpo.domain.repositories.ITransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.query.QueryHandler;

@Service
public class GetByIdTransactionQueryHandler implements QueryHandler<GetByIdTransactionQuery, GetByIdTransactionResponse> {

    private final ITransactionQuery query;

    public GetByIdTransactionQueryHandler(ITransactionQuery query) {
        this.query = query;
    }

    @Override
    public GetByIdTransactionResponse handle(GetByIdTransactionQuery query) {
        return this.query.getById(query.getId()).map(t -> new GetByIdTransactionResponse(t.getId(), t.getAmount(), t.getDate())).orElse(null);
    }

}
