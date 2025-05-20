package pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITransactionJpaRepository extends JpaRepository<TransactionEntity, Long>
{

    /**
     * Find a transaction by its ID and check if it is active.
     *
     * @param id the ID of the transaction
     * @return the transaction entity if found and active, otherwise null
     */
    Optional<TransactionEntity> findByIdAndActiveTrue(Long id);


    /**
     * Find all transactions that are active.
     *
     * @return a list of active transactions
     */
    List<TransactionEntity> findAllByActiveTrue();


    /**
     * Count the number of active transactions for a given username.
     *
     * @param username the username to count transactions for
     * @return the count of active transactions for the given username
     */
    int countByActiveTrueAndUsername(String username);
}