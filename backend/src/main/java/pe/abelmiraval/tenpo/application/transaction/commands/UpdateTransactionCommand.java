package pe.abelmiraval.tenpo.application.transaction.commands;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.HandledBy;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.command.Command;

@Value
@HandledBy(handler = UpdateTransactionCommandHandler.class)
public class UpdateTransactionCommand implements Command<Boolean> {
    public Long id;

    @Min(value = 0, message = "El monto debe ser mayor o igual a 0")
    public Integer amount;

    @NotBlank(message = "La categoría es obligatoria")
    public String category;

    public String username;
}
