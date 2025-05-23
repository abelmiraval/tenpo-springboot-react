package pe.abelmiraval.tenpo.presentation.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.abelmiraval.tenpo.application.transaction.commands.CreateTransactionCommand;
import pe.abelmiraval.tenpo.application.transaction.commands.DeleteTransactionCommand;
import pe.abelmiraval.tenpo.application.transaction.commands.UpdateTransactionCommand;
import pe.abelmiraval.tenpo.application.transaction.queries.GetAllTransactionQuery;
import pe.abelmiraval.tenpo.application.transaction.queries.GetAllTransactionResponse;
import pe.abelmiraval.tenpo.application.transaction.queries.GetByIdTransactionQuery;
import pe.abelmiraval.tenpo.application.transaction.queries.GetByIdTransactionResponse;
import pe.abelmiraval.tenpo.application.wrapper.BaseResponse;
import pe.abelmiraval.tenpo.infraestructure.config.ratelimit.RateLimiter;
import pe.abelmiraval.tenpo.infraestructure.config.ratelimit.RateLimiters;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.dispatchable.DispatchableHandler;
import pe.abelmiraval.tenpo.infraestructure.swagger.DeleteApiResponse;
import pe.abelmiraval.tenpo.infraestructure.swagger.GetApiResponse;
import pe.abelmiraval.tenpo.infraestructure.swagger.PostApiResponse;
import pe.abelmiraval.tenpo.infraestructure.swagger.PutApiResponse;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Tag(name = "Transaction", description = "Transaction API")
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final DispatchableHandler mediator;
    public static final String SUCCESS = "Success";

    public TransactionController(DispatchableHandler mediator) {
        this.mediator = mediator;
    }

    @RateLimiters({
            @RateLimiter(timeUnit = TimeUnit.SECONDS, timeValue = 10, restriction = 2),
            @RateLimiter(timeValue = 10, restriction = 5)
    })
    @Operation(summary = "Get By Id")
    @GetApiResponse
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<GetByIdTransactionResponse>> getById(@PathVariable Long id) {
       var command = new GetByIdTransactionQuery(id);
        var response = mediator.dispatch(command);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponse<>(null, "Transaction not found", false, Collections.emptyList()));
        }

        return ResponseEntity.ok(new BaseResponse<>(response, SUCCESS, true, Collections.emptyList()));
    }


    @Operation(summary = "Get All")
    @GetApiResponse
    @GetMapping()
    public ResponseEntity<BaseResponse<List<GetAllTransactionResponse>>> getAll() {
        var response = mediator.dispatch(new GetAllTransactionQuery());

        return ResponseEntity.ok(new BaseResponse<>(response, SUCCESS, true, Collections.emptyList()));
    }

    @Operation(summary = "Save")
    @PostApiResponse
    @PostMapping()
    public ResponseEntity<Boolean> save(@RequestBody @Valid final CreateTransactionCommand command) {
        var response = mediator.dispatch(command);
        if (Boolean.TRUE.equals(response)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Boolean.FALSE);
    }

    @Operation(summary = "Update")
    @PutApiResponse
    @PutMapping()
    public ResponseEntity<Boolean> update(@RequestBody @Valid final UpdateTransactionCommand command) {
        var response = mediator.dispatch(command);
        if (Boolean.TRUE.equals(response)) {
            return ResponseEntity.ok(Boolean.TRUE);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Boolean.FALSE);
    }

    @DeleteApiResponse
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        var command = new DeleteTransactionCommand(id);
        var response = mediator.dispatch(command);
        if (Boolean.TRUE.equals(response)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Boolean.TRUE);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Boolean.FALSE);
    }

}
