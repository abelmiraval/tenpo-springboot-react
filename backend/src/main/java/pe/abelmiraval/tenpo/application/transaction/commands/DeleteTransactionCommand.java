package pe.abelmiraval.tenpo.application.transaction.commands;

import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.HandledBy;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.command.Command;

@HandledBy(handler = DeleteTransactionCommandHandler.class)
public record DeleteTransactionCommand(Long id) implements Command<Boolean> {
}
