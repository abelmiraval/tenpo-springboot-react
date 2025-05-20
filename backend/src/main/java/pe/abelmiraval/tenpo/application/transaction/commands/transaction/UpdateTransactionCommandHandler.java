package pe.abelmiraval.tenpo.application.transaction.commands.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionRepository;
import pe.abelmiraval.tenpo.infraestructure.shared.mediator.Handler;

@Component
public class UpdateTransactionCommandHandler implements Handler<UpdateTransactionCommand, Boolean> {

    private final TransactionQuery query;
    private final TransactionRepository repository;

    public UpdateTransactionCommandHandler(TransactionQuery query, TransactionRepository repository) {
        this.query = query;
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Boolean> handle(UpdateTransactionCommand request) {
        var entity = query.getById(request.id());
        entity.setAmount(request.amount());
        entity.setCategory(request.category());
        entity.setUsername(request.username());

        repository.update(entity);

        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}
