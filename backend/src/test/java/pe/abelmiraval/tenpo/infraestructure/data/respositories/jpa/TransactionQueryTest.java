package pe.abelmiraval.tenpo.infraestructure.data.respositories.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.interfaces.ITransactionJpaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionQueryTest {

    private ITransactionJpaRepository jpaRepository;
    private TransactionQuery query;

    @BeforeEach
    void setUp() {
        jpaRepository = mock(ITransactionJpaRepository.class);
        query = new TransactionQuery(jpaRepository);
    }

    @Test
    void getById_ShouldReturnTransaction_WhenExistsAndIsActive() {
        // Arrange
        Long id = 1L;
        TransactionEntity transaction = new TransactionEntity();
        transaction.setId(id);

        when(jpaRepository.findByIdAndActiveTrue(id)).thenReturn(Optional.of(transaction));

        // Act
        Optional<TransactionEntity> result = query.getById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        verify(jpaRepository, times(1)).findByIdAndActiveTrue(id);
    }

    @Test
    void getById_ShouldReturnEmpty_WhenTransactionIsNotFound() {
        // Arrange
        Long id = 2L;

        when(jpaRepository.findByIdAndActiveTrue(id)).thenReturn(Optional.empty());

        // Act
        Optional<TransactionEntity> result = query.getById(id);

        // Assert
        assertTrue(result.isEmpty());
        verify(jpaRepository, times(1)).findByIdAndActiveTrue(id);
    }

    @Test
    void getAll_ShouldReturnListOfActiveTransactions() {
        // Arrange
        TransactionEntity t1 = new TransactionEntity();
        TransactionEntity t2 = new TransactionEntity();

        when(jpaRepository.findAllByActiveTrue()).thenReturn(List.of(t1, t2));

        // Act
        List<TransactionEntity> result = query.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(jpaRepository, times(1)).findAllByActiveTrue();
    }

    @Test
    void countByUsername_ShouldReturnCountOfActiveTransactions() {
        // Arrange
        String username = "abel";
        when(jpaRepository.countByActiveTrueAndUsername(username)).thenReturn(3);

        // Act
        int result = query.countByUsername(username);

        // Assert
        assertEquals(3, result);
        verify(jpaRepository, times(1)).countByActiveTrueAndUsername(username);
    }
}