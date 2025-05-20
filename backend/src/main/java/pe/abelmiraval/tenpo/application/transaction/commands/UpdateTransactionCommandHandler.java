package pe.abelmiraval.tenpo.application.transaction.commands;

import org.springframework.stereotype.Service;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionRepository;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.command.CommandHandler;

@Service
public class UpdateTransactionCommandHandler implements CommandHandler<UpdateTransactionCommand, Boolean> {

    private final TransactionQuery query;
    private final TransactionRepository repository;

    public UpdateTransactionCommandHandler(TransactionQuery query, TransactionRepository repository) {
        this.query = query;
        this.repository = repository;
    }

    @Override
    public Boolean handle(UpdateTransactionCommand request) {
        var optional = query.getById(request.id());

        if (optional.isEmpty()) {
            return false;
        }

        var transaction = optional.get();
        transaction.setAmount(request.amount());
        transaction.setCategory(request.category());
        transaction.setUsername(request.username());

        repository.update(transaction);

        return true;
    }
}
