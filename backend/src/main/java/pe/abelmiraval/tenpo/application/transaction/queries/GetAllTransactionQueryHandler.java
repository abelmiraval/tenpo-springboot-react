package pe.abelmiraval.tenpo.application.transaction.queries;

import org.springframework.stereotype.Component;
import pe.abelmiraval.tenpo.domain.repositories.ITransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.shared.mediator.Handler;

import java.util.List;

@Component
public class GetAllTransactionQueryHandler implements QueryHandler<GetAllTransactionQuery, List<GetAllTransactionResponse>> {

    private final ITransactionQuery query;

    public GetAllTransactionQueryHandler(TransactionQuery query) {
        this.query = query;
    }

    @Override
    public List<GetAllTransactionResponse> handle(GetAllTransactionQuery query) {
        return this.query.getAll().stream()
                .map(t -> new GetAllTransactionResponse(t.getId(), t.getAmount(), t.getDate()))
                .toList();
    }

}
