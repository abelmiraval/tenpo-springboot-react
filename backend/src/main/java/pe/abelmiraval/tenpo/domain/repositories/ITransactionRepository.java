package pe.abelmiraval.tenpo.domain.repositories;

import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;

public interface ITransactionRepository {
    int save(TransactionEntity transaction);

    int update(TransactionEntity transaction);

    int delete(TransactionEntity transaction);
}
