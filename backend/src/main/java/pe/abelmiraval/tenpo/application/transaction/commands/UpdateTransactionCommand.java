package pe.abelmiraval.tenpo.application.transaction.commands;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.HandledBy;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.command.Command;

@HandledBy(handler = UpdateTransactionCommandHandler.class)
public record UpdateTransactionCommand(Long id,
                                       @Min(value = 0, message = "El monto debe ser mayor o igual a 0") Integer amount,
                                       @NotBlank(message = "La categoría es obligatoria") String category,
                                       @NotBlank(message = "El usuario es obligatorio") String username) implements Command<Boolean> {
}
