package pe.abelmiraval.tenpo.application.transaction.commands;

import lombok.Value;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.HandledBy;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.command.Command;

@Value
@HandledBy(handler = DeleteTransactionCommandHandler.class)
public class DeleteTransactionCommand implements Command<Boolean> {
    public Long id;
}
