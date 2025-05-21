package pe.abelmiraval.tenpo.infraestructure.data.respositories.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.abelmiraval.tenpo.application.exceptions.DatabaseException;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionRepository;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.interfaces.ITransactionJpaRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionRepositoryTest {

    private ITransactionJpaRepository jpaRepository;
    private TransactionRepository repository;

    @BeforeEach
    void setUp() {
        jpaRepository = mock(ITransactionJpaRepository.class);
        repository = new TransactionRepository(jpaRepository);
    }

    @Test
    void save_shouldReturn1_whenTransactionIsSavedSuccessfully() {
        // Arrange
        TransactionEntity transaction = new TransactionEntity();

        // Act
        int result = repository.save(transaction);

        // Assert
        assertEquals(1, result);
        verify(jpaRepository, times(1)).save(transaction);
    }

    @Test
    void save_shouldThrowDatabaseException_whenExceptionIsThrown() {
        // Arrange
        TransactionEntity transaction = new TransactionEntity();
        doThrow(new RuntimeException("DB Error")).when(jpaRepository).save(transaction);

        // Act & Assert
        DatabaseException exception = assertThrows(DatabaseException.class, () -> repository.save(transaction));
        assertEquals("DB Error", exception.getMessage());
    }

    @Test
    void update_shouldReturn1_whenTransactionIsUpdatedSuccessfully() {
        // Arrange
        TransactionEntity transaction = new TransactionEntity();

        // Act
        int result = repository.update(transaction);

        // Assert
        assertEquals(1, result);
        verify(jpaRepository, times(1)).save(transaction);
    }

    @Test
    void update_shouldThrowDatabaseException_whenExceptionIsThrown() {
        // Arrange
        TransactionEntity transaction = new TransactionEntity();
        doThrow(new RuntimeException("Update failed")).when(jpaRepository).save(transaction);

        // Act & Assert
        DatabaseException exception = assertThrows(DatabaseException.class, () -> repository.update(transaction));
        assertEquals("Update failed", exception.getMessage());
    }

    @Test
    void delete_shouldSetActiveFalseAndReturn1_whenSuccessful() {
        // Arrange
        TransactionEntity transaction = new TransactionEntity();
        transaction.setActive(true);

        // Act
        int result = repository.delete(transaction);

        // Assert
        assertEquals(1, result);
        verify(jpaRepository, times(1)).save(transaction);
    }

    @Test
    void delete_shouldThrowDatabaseException_whenExceptionIsThrown() {
        // Arrange
        TransactionEntity transaction = new TransactionEntity();
        doThrow(new RuntimeException("Delete failed")).when(jpaRepository).save(transaction);

        // Act & Assert
        DatabaseException exception = assertThrows(DatabaseException.class, () -> repository.delete(transaction));
        assertEquals("Delete failed", exception.getMessage());
    }
}