package pe.abelmiraval.tenpo.application.transaction.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionRepository;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DeleteTransactionCommandHandlerTest {
    private TransactionQuery query;
    private TransactionRepository repository;
    private DeleteTransactionCommandHandler handler;

    @BeforeEach
    void setUp() {
        query = mock(TransactionQuery.class);
        repository = mock(TransactionRepository.class);
        handler = new DeleteTransactionCommandHandler(query, repository);
    }

    @Test
    void handle_ShouldDeleteTransaction_WhenExists() {
        // Arrange
        DeleteTransactionCommand command = new DeleteTransactionCommand(1L);

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

        verify(repository, times(1)).delete(existingEntity);
    }


    @Test
    void handle_ShouldReturnFalse_WhenTransactionDoesNotExist() {
        // Arrange
        DeleteTransactionCommand command = new DeleteTransactionCommand(2L);

        when(query.getById(2L)).thenReturn(Optional.empty());

        // Act
        Boolean result = handler.handle(command);

        // Assert
        assertFalse(result);

        verify(repository, never()).delete(any());
    }
}
