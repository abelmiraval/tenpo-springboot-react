package pe.abelmiraval.tenpo.application.transaction.commands;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.HandledBy;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.command.Command;

import java.util.Date;

@HandledBy(handler = CreateTransactionCommandHandler.class)
public record CreateTransactionCommand(@Min(value = 0, message = "El monto debe ser mayor o igual a 0") Integer amount,
                                       @NotBlank(message = "La categoría es obligatoria") String category,
                                       @NotBlank(message = "El usuario es obligatorio") String username,
                                       @PastOrPresent(message = "La fecha no puede ser futura") Date date) implements Command<Boolean> {
}
