package pe.abelmiraval.tenpo.infraestructure.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.abelmiraval.tenpo.domain.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>
{
}
