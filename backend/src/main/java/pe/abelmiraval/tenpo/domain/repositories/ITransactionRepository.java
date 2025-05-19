package pe.abelmiraval.tenpo.domain.repositories;

import pe.abelmiraval.tenpo.domain.entities.Transaction;

public interface ITransactionRepository {
    public int save(Transaction transaction);

    public int update(Transaction transaction);

    public int delete(Long id);

    public Transaction findByUsername(String username);

}
