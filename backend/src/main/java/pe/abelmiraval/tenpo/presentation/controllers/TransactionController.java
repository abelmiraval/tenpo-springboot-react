package pe.abelmiraval.tenpo.presentation.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.abelmiraval.tenpo.application.transaction.commands.transaction.CreateTransactionCommand;
import pe.abelmiraval.tenpo.domain.entities.Transaction;
import pe.abelmiraval.tenpo.infraestructure.shared.mediator.Mediator;
import pe.abelmiraval.tenpo.infraestructure.swagger.DeleteApiResponse;
import pe.abelmiraval.tenpo.infraestructure.swagger.GetApiResponse;
import pe.abelmiraval.tenpo.infraestructure.swagger.PostApiResponse;
import pe.abelmiraval.tenpo.infraestructure.swagger.PutApiResponse;

import java.util.UUID;

@Tag(name = "Transaction", description = "Transaction API")
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final Mediator mediator;

    public TransactionController(Mediator mediator) {
        this.mediator = mediator;
    }

    @Operation(summary = "Get By Id")
    @GetApiResponse
    @GetMapping("/{id}")
    public String getById(@PathVariable Long id) {
        // Logic to retrieve transaction by ID
        return "Transaction details for ID: " + id;
    }

    @Operation(summary = "Save")
    @PostApiResponse
    @PostMapping()
    public ResponseEntity<UUID> save(@RequestBody @Valid final CreateTransactionCommand request) {
        // Logic to save a transaction
        return mediator.handle(request, UUID.class);
    }

    @Operation(summary = "Update")
    @PutApiResponse
    @PutMapping()
    public String update(Transaction transaction) {
        // Logic to update a transaction
        return "Transaction updated successfully";
    }

    @DeleteApiResponse
    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id) {
        // Logic to delete a transaction
        return "Transaction deleted successfully";
    }

}
