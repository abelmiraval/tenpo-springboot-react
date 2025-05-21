package pe.abelmiraval.tenpo.application.transaction.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.abelmiraval.tenpo.application.exceptions.BusinessException;
import pe.abelmiraval.tenpo.domain.constants.Constants;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.TransactionRepository;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateTransactionCommandHandlerTest {

    private TransactionQuery query;
    private TransactionRepository repository;
    private CreateTransactionCommandHandler handler;

    @BeforeEach
    void setUp() {
        query = mock(TransactionQuery.class);
        repository = mock(TransactionRepository.class);
        handler = new CreateTransactionCommandHandler(query, repository);
    }

    @Test
    void handle_ShouldCreateTransaction_WhenCountIsLessThanLimit() {
        // Arrange
        CreateTransactionCommand command = new CreateTransactionCommand(
                100, "shopping", "john_doe", new Date()
        );

        when(query.countByUsername("john_doe")).thenReturn(10);

        // Act
        Boolean result = handler.handle(command);

        // Assert
        assertTrue(result);
        verify(repository, times(1)).save(any(TransactionEntity.class));
    }

    @Test
    void handle_ShouldThrowException_WhenCountEqualsOrExceedsLimit() {
        // Arrange
        CreateTransactionCommand command = new CreateTransactionCommand(
                200, "travel", "jane_doe", new Date()
        );

        when(query.countByUsername("jane_doe")).thenReturn(Constants.MAX_TRANSACTION_COUNT);

        // Act
        BusinessException exception = assertThrows(BusinessException.class, () -> handler.handle(command));

        // Assert
        assertEquals("amount", exception.getField());
        assertEquals("El Tenpista ya tiene el máximo de 100 transacciones.", exception.getMessage());

        verify(repository, never()).save(any());
    }
}
