package pe.abelmiraval.tenpo.application.transaction.queries;

import lombok.Value;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.HandledBy;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.query.Query;

@Value
@HandledBy(handler = GetByIdTransactionQueryHandler.class)
public class GetByIdTransactionQuery implements Query<GetByIdTransactionResponse> {
    public Long id;
}
