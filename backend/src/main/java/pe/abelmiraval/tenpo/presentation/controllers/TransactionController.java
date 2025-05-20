package pe.abelmiraval.tenpo.presentation.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.abelmiraval.tenpo.application.transaction.commands.transaction.CreateTransactionCommand;
import pe.abelmiraval.tenpo.application.transaction.commands.transaction.DeleteTransactionCommand;
import pe.abelmiraval.tenpo.application.transaction.commands.transaction.UpdateTransactionCommand;
import pe.abelmiraval.tenpo.domain.repositories.ITransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;
import pe.abelmiraval.tenpo.infraestructure.shared.mediator.Mediator;
import pe.abelmiraval.tenpo.infraestructure.swagger.DeleteApiResponse;
import pe.abelmiraval.tenpo.infraestructure.swagger.GetApiResponse;
import pe.abelmiraval.tenpo.infraestructure.swagger.PostApiResponse;
import pe.abelmiraval.tenpo.infraestructure.swagger.PutApiResponse;

import java.util.List;
import java.util.UUID;

@Tag(name = "Transaction", description = "Transaction API")
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final Mediator mediator;
    private final ITransactionQuery query;

    public TransactionController(Mediator mediator, ITransactionQuery query) {
        this.mediator = mediator;
        this.query = query;
    }

    @Operation(summary = "Get By Id")
    @GetApiResponse
    @GetMapping("/{id}")
    public TransactionEntity getById(@PathVariable Long id) {
        return query.getById(id);
    }

    @Operation(summary = "Get All")
    @GetApiResponse
    @GetMapping()
    public List<TransactionEntity> getAll() {
        return query.getAll();
    }

    @Operation(summary = "Save")
    @PostApiResponse
    @PostMapping()
    public ResponseEntity<UUID> save(@RequestBody @Valid final CreateTransactionCommand request) {
        return mediator.handle(request, UUID.class);
    }

    @Operation(summary = "Update")
    @PutApiResponse
    @PutMapping()
    public ResponseEntity<Boolean> update(@RequestBody @Valid final UpdateTransactionCommand request) {
        return mediator.handle(request, Boolean.class);
    }

    @DeleteApiResponse
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        var command = new DeleteTransactionCommand(id);
        return mediator.handle(command, Boolean.class);
    }

}
