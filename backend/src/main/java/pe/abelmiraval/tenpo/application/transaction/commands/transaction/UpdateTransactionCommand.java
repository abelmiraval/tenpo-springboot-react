package pe.abelmiraval.tenpo.application.transaction.commands.transaction;

public record UpdateTransactionCommand(Long id, Integer amount, String category, String username)  {
}
