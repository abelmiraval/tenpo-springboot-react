package pe.abelmiraval.tenpo.application.transaction.queries;

import org.springframework.stereotype.Service;
import pe.abelmiraval.tenpo.domain.repositories.ITransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.query.QueryHandler;

import java.util.List;

@Service
public class GetAllTransactionQueryHandler implements QueryHandler<GetAllTransactionQuery, List<GetAllTransactionResponse>> {

    private final ITransactionQuery query;

    public GetAllTransactionQueryHandler(ITransactionQuery query) {
        this.query = query;
    }

    @Override
    public List<GetAllTransactionResponse> handle(GetAllTransactionQuery query) {
        return this.query.getAll().stream()
                .map(t -> new GetAllTransactionResponse(t.getId(), t.getAmount(), t.getDate()))
                .toList();
    }

}
