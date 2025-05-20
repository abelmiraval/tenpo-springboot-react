package pe.abelmiraval.tenpo.application.transaction.commands.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionRepository;
import pe.abelmiraval.tenpo.infraestructure.shared.mediator.Handler;

@Component
public class DeleteTransactionCommandHandler implements Handler<DeleteTransactionCommand, Boolean> {

    private final TransactionQuery query;
    private final TransactionRepository repository;

    public DeleteTransactionCommandHandler(TransactionQuery query, TransactionRepository repository) {
        this.query = query;
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Boolean> handle(DeleteTransactionCommand request) {

        var entity = query.getById(request.id());

        repository.delete(entity);

        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.NO_CONTENT);
    }

}
