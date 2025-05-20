package pe.abelmiraval.tenpo.application.transaction.commands;

import org.springframework.stereotype.Component;
import pe.abelmiraval.tenpo.application.exceptions.BusinessException;
import pe.abelmiraval.tenpo.domain.constants.Constants;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionRepository;
import pe.abelmiraval.tenpo.infraestructure.shared.mediator.Handler;

@Component
public class CreateTransactionCommandHandler implements Handler<CreateTransactionCommand, Boolean> {

    private final TransactionQuery query;
    private final TransactionRepository repository;

    public CreateTransactionCommandHandler(TransactionQuery query, TransactionRepository repository) {
        this.query = query;
        this.repository = repository;
    }

    @Override
    public Boolean handle(CreateTransactionCommand request) {

        var count = query.countByUsername(request.username());

        if (count >= Constants.MAX_TRANSACTION_COUNT) {
            throw new BusinessException("amount","El Tenpista ya tiene el máximo de 100 transacciones.");
        }

        var entity = new TransactionEntity(
                request.amount(),
                request.category(),
                request.username(),
                request.date()
        );
        repository.save(entity);

        return true;
    }
}
