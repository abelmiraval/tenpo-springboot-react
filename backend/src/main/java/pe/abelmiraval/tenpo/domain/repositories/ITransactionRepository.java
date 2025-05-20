package pe.abelmiraval.tenpo.domain.repositories;

import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;

public interface ITransactionRepository {

    /**
     * Saves a transaction to the database.
     *
     * @param transaction the transaction entity to save
     * @return the number of rows affected
     */
    int save(TransactionEntity transaction);


    /**
     * Updates a transaction in the database.
     *
     * @param transaction the transaction entity to update
     * @return the number of rows affected
     */
    int update(TransactionEntity transaction);

    /**
     * Deletes a transaction from the database.
     *
     * @param transaction the transaction entity to delete
     * @return the number of rows affected
     */
    int delete(TransactionEntity transaction);
}
