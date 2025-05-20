package pe.abelmiraval.tenpo.application.transaction.commands;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateTransactionCommand(
        Long id,
        @Min(value = 0, message = "El monto debe ser mayor o igual a 0") Integer amount,
        @NotBlank(message = "La categoría es obligatoria") String category,
        String username) {
}
