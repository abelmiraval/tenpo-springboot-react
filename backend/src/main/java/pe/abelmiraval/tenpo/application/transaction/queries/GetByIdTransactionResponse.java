package pe.abelmiraval.tenpo.application.transaction.queries;

import java.util.Date;

public record GetByIdTransactionResponse(Long id, Integer amount, String category, String username, Date date) {}