package pe.abelmiraval.tenpo.application.transaction.queries;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.abelmiraval.tenpo.domain.repositories.ITransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetByIdTransactionQueryHandlerTest {

    private ITransactionQuery query;
    private GetByIdTransactionQueryHandler handler;

    @BeforeEach
    void setUp() {
        query = mock(ITransactionQuery.class);
        handler = new GetByIdTransactionQueryHandler(query);
    }

    @Test
    void handle_ShouldReturnResponse_WhenTransactionExists() {
        // Arrange
        long id = 1L;
        TransactionEntity transaction = new TransactionEntity();
        transaction.setId(id);
        transaction.setAmount(500);
        transaction.setDate(new Date());

        when(query.getById(id)).thenReturn(Optional.of(transaction));

        // Act
        GetByIdTransactionResponse result = handler.handle(new GetByIdTransactionQuery(id));

        // Assert
        assertNotNull(result);
        assertEquals(id, result.id());
        assertEquals(500, result.amount());
        assertEquals(transaction.getDate(), result.date());
        verify(query, times(1)).getById(id);
    }

    @Test
    void handle_ShouldReturnNull_WhenTransactionDoesNotExist() {
        // Arrange
        long id = 99L;
        when(query.getById(id)).thenReturn(Optional.empty());

        // Act
        GetByIdTransactionResponse result = handler.handle(new GetByIdTransactionQuery(id));

        // Assert
        assertNull(result);
        verify(query, times(1)).getById(id);
    }
}