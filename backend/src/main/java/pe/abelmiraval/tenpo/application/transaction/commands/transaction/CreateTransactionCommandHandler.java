package pe.abelmiraval.tenpo.application.transaction.commands.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pe.abelmiraval.tenpo.domain.constants.Constants;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionRepository;
import pe.abelmiraval.tenpo.infraestructure.shared.mediator.Handler;

import java.util.UUID;

@Component
public class CreateTransactionCommandHandler implements Handler<CreateTransactionCommand, UUID> {

    private final TransactionQuery query;
    private final TransactionRepository repository;

    public CreateTransactionCommandHandler(TransactionQuery query, TransactionRepository repository) {
        this.query = query;
        this.repository = repository;
    }

    @Override
    public ResponseEntity<UUID> handle(CreateTransactionCommand request){

        var count = query.countByUsername(request.username());

        if (count >= Constants.MAX_TRANSACTION_COUNT) {
            throw new IllegalStateException("El Tenpista ya tiene el máximo de 100 transacciones.");
        }

        var entity = new TransactionEntity(
                request.amount(),
                request.category(),
                request.username()
        );
        repository.save(entity);

        return new ResponseEntity<>(UUID.randomUUID(), HttpStatus.CREATED);
    }
}
