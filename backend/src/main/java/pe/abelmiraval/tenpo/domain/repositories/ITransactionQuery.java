package pe.abelmiraval.tenpo.domain.repositories;

import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;

import java.util.List;
import java.util.Optional;

public interface ITransactionQuery {

    /**
     * Retrieves a transaction by its ID.
     *
     * @param id the ID of the transaction
     * @return the transaction entity if found
     */
    Optional<TransactionEntity> getById(Long id);

    /**
     * Retrieves all transactions.
     *
     * @return a list of all transaction entities
     */
    List<TransactionEntity> getAll();

    /**
     * Counts the number of transactions for a given username.
     *
     * @param username the username to count transactions for
     * @return the count of transactions for the given username
     */
    int countByUsername(String username);
}
