package pe.abelmiraval.tenpo.application.transaction.queries;

import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.HandledBy;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.query.Query;

import java.util.List;

@HandledBy(handler = GetAllTransactionQueryHandler.class)
public class GetAllTransactionQuery implements Query<List<GetAllTransactionResponse>> {
}
