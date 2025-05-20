package pe.abelmiraval.tenpo.application.transaction.commands;

import org.springframework.stereotype.Service;
import pe.abelmiraval.tenpo.application.exceptions.BusinessException;
import pe.abelmiraval.tenpo.domain.constants.Constants;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionRepository;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.command.CommandHandler;

@Service
public class CreateTransactionCommandHandler implements CommandHandler<CreateTransactionCommand, Boolean> {

    private final TransactionQuery query;
    private final TransactionRepository repository;

    public CreateTransactionCommandHandler(TransactionQuery query, TransactionRepository repository) {
        this.query = query;
        this.repository = repository;
    }

    @Override
    public Boolean handle(CreateTransactionCommand request) {

        var count = query.countByUsername(request.getUsername());

        if (count >= Constants.MAX_TRANSACTION_COUNT) {
            throw new BusinessException("amount", "El Tenpista ya tiene el máximo de 100 transacciones.");
        }

        var entity = new TransactionEntity(
                request.getAmount(),
                request.getCategory(),
                request.getUsername(),
                request.getDate()
        );
        repository.save(entity);

        return true;
    }
}
