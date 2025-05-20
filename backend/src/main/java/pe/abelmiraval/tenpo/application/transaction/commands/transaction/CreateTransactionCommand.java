package pe.abelmiraval.tenpo.application.transaction.commands.transaction;


public record CreateTransactionCommand(Integer amount, String category, String username) {
}
