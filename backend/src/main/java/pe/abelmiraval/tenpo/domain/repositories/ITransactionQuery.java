package pe.abelmiraval.tenpo.domain.repositories;

import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;

import java.util.List;

public interface ITransactionQuery {
    TransactionEntity getById(Long id);

    List<TransactionEntity> getAll();

    int countByUsername(String username);
}
