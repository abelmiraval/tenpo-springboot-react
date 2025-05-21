package pe.abelmiraval.tenpo.application.transaction.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionRepository;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class UpdateTransactionCommandHandlerTest {

    private TransactionQuery query;
    private TransactionRepository repository;
    private UpdateTransactionCommandHandler handler;

    @BeforeEach
    void setUp() {
        query = mock(TransactionQuery.class);
        repository = mock(TransactionRepository.class);
        handler = new UpdateTransactionCommandHandler(query, repository);
    }

    @Test
    void handle_ShouldUpdateTransaction_WhenExists() {
        // Arrange
        UpdateTransactionCommand command = new UpdateTransactionCommand(
                1L, 150, "health", "john_doe"
        );

        TransactionEntity existingEntity = new TransactionEntity();
        existingEntity.setId(1L);
        existingEntity.setAmount(100);
        existingEntity.setCategory("shopping");
        existingEntity.setUsername("old_user");
        existingEntity.setDate(new Date());

        when(query.getById(1L)).thenReturn(Optional.of(existingEntity));

        // Act
        Boolean result = handler.handle(command);

        // Assert
        assertTrue(result);
        assertEquals(150, existingEntity.getAmount());
        assertEquals("health", existingEntity.getCategory());
        assertEquals("john_doe", existingEntity.getUsername());

        verify(repository, times(1)).update(existingEntity);
    }

    @Test
    void handle_ShouldReturnFalse_WhenTransactionDoesNotExist() {
        // Arrange
        UpdateTransactionCommand command = new UpdateTransactionCommand(
                99L, 200, "travel", "jane_doe"
        );

        when(query.getById(99L)).thenReturn(Optional.empty());

        // Act
        Boolean result = handler.handle(command);

        // Assert
        assertFalse(result);
        verify(repository, never()).update(any());
    }
}