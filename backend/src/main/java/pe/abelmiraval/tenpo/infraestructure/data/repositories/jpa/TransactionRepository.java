package pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pe.abelmiraval.tenpo.application.exceptions.DatabaseException;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;
import pe.abelmiraval.tenpo.domain.repositories.ITransactionRepository;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.interfaces.ITransactionJpaRepository;

@Repository
@Transactional
public class TransactionRepository implements ITransactionRepository {

    private final ITransactionJpaRepository repository;

    public TransactionRepository(ITransactionJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public int save(TransactionEntity transaction) {
        try {
            repository.save(transaction);
            return 1;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public int update(TransactionEntity transaction) {
        try {
            repository.save(transaction);
            return 1;
        }catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public int delete(TransactionEntity transaction) {
        try {
            transaction.setActive(false);
            repository.save(transaction);
            return 1;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
