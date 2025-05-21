package pe.abelmiraval.tenpo.application.transaction.queries;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.abelmiraval.tenpo.domain.repositories.ITransactionQuery;
import pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities.TransactionEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetAllTransactionQueryHandlerTest {

    private ITransactionQuery query;
    private GetAllTransactionQueryHandler handler;

    @BeforeEach
    void setUp() {
        query = mock(ITransactionQuery.class);
        handler = new GetAllTransactionQueryHandler(query);
    }

    @Test
    void handle_ShouldReturnListOfResponses_WhenTransactionsExist() {
        // Arrange
        TransactionEntity t1 = new TransactionEntity();
        t1.setId(1L);
        t1.setAmount(100);
        t1.setDate(new Date());

        TransactionEntity t2 = new TransactionEntity();
        t2.setId(2L);
        t2.setAmount(200);
        t2.setDate(new Date());

        when(query.getAll()).thenReturn(Arrays.asList(t1, t2));

        // Act
        List<GetAllTransactionResponse> result = handler.handle(new GetAllTransactionQuery());

        // Assert
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).id());
        assertEquals(100, result.get(0).amount());
        assertEquals(2L, result.get(1).id());
        assertEquals(200, result.get(1).amount());

        verify(query, times(1)).getAll();
    }

    @Test
    void handle_ShouldReturnEmptyList_WhenNoTransactionsExist() {
        // Arrange
        when(query.getAll()).thenReturn(List.of());

        // Act
        List<GetAllTransactionResponse> result = handler.handle(new GetAllTransactionQuery());

        // Assert
        assertEquals(0, result.size());
        verify(query, times(1)).getAll();
    }
}
