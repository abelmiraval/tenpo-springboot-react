package pe.abelmiraval.tenpo.application.transaction.queries;

import java.util.Date;

public record GetAllTransactionResponse(Long id, Integer amount, Date date) {}
