package pe.abelmiraval.tenpo.application.transaction.commands;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Value;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.HandledBy;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.command.Command;

import java.util.Date;

@Value
@HandledBy(handler = CreateTransactionCommandHandler.class)
public class CreateTransactionCommand implements Command<Boolean> {
    @Min(value = 0, message = "El monto debe ser mayor o igual a 0")
    public Integer amount;
    @NotBlank(message = "La categoría es obligatoria")
    public String category;
    @NotBlank(message = "El usuario es obligatorio")
    public String username;
    @PastOrPresent(message = "La fecha no puede ser futura")
    public Date date;

}
