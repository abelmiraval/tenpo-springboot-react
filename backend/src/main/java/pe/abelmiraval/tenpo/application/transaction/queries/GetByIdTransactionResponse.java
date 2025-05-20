package pe.abelmiraval.tenpo.application.transaction.queries;

import java.util.Date;

public record GetByIdTransactionResponse(Long id, Integer amount, Date date) {}