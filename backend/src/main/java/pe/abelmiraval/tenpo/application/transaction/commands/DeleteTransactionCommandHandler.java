package pe.abelmiraval.tenpo.application.transaction.commands;

import org.springframework.stereotype.Service;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionRepository;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.command.CommandHandler;

@Service
public class DeleteTransactionCommandHandler implements CommandHandler<DeleteTransactionCommand, Boolean> {

    private final TransactionQuery query;
    private final TransactionRepository repository;

    public DeleteTransactionCommandHandler(TransactionQuery query, TransactionRepository repository) {
        this.query = query;
        this.repository = repository;
    }

    @Override
    public Boolean handle(DeleteTransactionCommand request) {

        var entity = query.getById(request.getId());

        if (entity.isEmpty()) {
            return false;
        }

        var transaction = entity.get();
        repository.delete(transaction);

        return true;
    }

}
