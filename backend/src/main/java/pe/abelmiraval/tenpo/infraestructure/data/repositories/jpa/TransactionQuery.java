package pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa;

import org.springframework.stereotype.Service;
import pe.abelmiraval.tenpo.domain.repositories.ITransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.interfaces.ITransactionJpaRepository;

import java.util.List;

@Service
public class TransactionQuery implements ITransactionQuery {
    private final ITransactionJpaRepository repository;

    public TransactionQuery(ITransactionJpaRepository repository) {
        this.repository = repository;
    }


    @Override
    public TransactionEntity getById(Long id) {
        return repository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    @Override
    public List<TransactionEntity> getAll() {
        return repository.findAllByActiveTrue();
    }

    @Override
    public int countByUsername(String username) {
        return repository.countByActiveTrueAndUsername(username);
    }

}
