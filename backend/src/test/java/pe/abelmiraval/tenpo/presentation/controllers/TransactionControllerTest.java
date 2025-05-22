package pe.abelmiraval.tenpo.presentation.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pe.abelmiraval.tenpo.application.transaction.commands.CreateTransactionCommand;
import pe.abelmiraval.tenpo.application.transaction.commands.DeleteTransactionCommand;
import pe.abelmiraval.tenpo.application.transaction.commands.UpdateTransactionCommand;
import pe.abelmiraval.tenpo.application.transaction.queries.GetAllTransactionQuery;
import pe.abelmiraval.tenpo.application.transaction.queries.GetAllTransactionResponse;
import pe.abelmiraval.tenpo.application.transaction.queries.GetByIdTransactionQuery;
import pe.abelmiraval.tenpo.application.transaction.queries.GetByIdTransactionResponse;
import pe.abelmiraval.tenpo.application.wrapper.BaseResponse;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.dispatchable.DispatchableHandler;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    private DispatchableHandler mediator;
    private TransactionController controller;

    @BeforeEach
    void setUp() {
        mediator = mock(DispatchableHandler.class);
        controller = new TransactionController(mediator);
    }

    @Test
    void testGetById_WhenFound() {
        var id = 1L;
        var responseMock = new GetByIdTransactionResponse(1L,20, new Date());
        when(mediator.dispatch(any(GetByIdTransactionQuery.class))).thenReturn(responseMock);

        ResponseEntity<BaseResponse<GetByIdTransactionResponse>> response = controller.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody().getMessage());
        assertTrue(response.getBody().isSuccess());
        assertNotNull(response.getBody().getData());
    }

    @Test
    void testGetById_WhenNotFound() {
        var id = 2L;
        when(mediator.dispatch(any(GetByIdTransactionQuery.class))).thenReturn(null);

        ResponseEntity<BaseResponse<GetByIdTransactionResponse>> response = controller.getById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(response.getBody().isSuccess());
        assertNull(response.getBody().getData());
    }

    @Test
    void testGetAll() {
        List<GetAllTransactionResponse> transactions = List.of(new GetAllTransactionResponse(1L, 20,"deposit","abelmiraval", new Date()));
        when(mediator.dispatch(any(GetAllTransactionQuery.class))).thenReturn(transactions);

        var response = controller.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody().getMessage());
        assertEquals(transactions, response.getBody().getData());
    }

    @Test
    void testSave_WhenSuccess() {
        CreateTransactionCommand command = new CreateTransactionCommand( 20, "deposit","abelmiraval", new Date()); // set fields as needed
        when(mediator.dispatch(command)).thenReturn(true);

        var response = controller.save(command);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody());
    }

    @Test
    void testSave_WhenFailure() {
        CreateTransactionCommand command = new CreateTransactionCommand( 20, "deposit","abelmiraval", new Date()); // set fields as needed
        when(mediator.dispatch(command)).thenReturn(false);

        var response = controller.save(command);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(response.getBody());
    }

    @Test
    void testUpdate_WhenSuccess() {
        UpdateTransactionCommand command = new UpdateTransactionCommand(1L,20,"deposit","abelmiraval"); // set fields as needed
        when(mediator.dispatch(command)).thenReturn(true);

        var response = controller.update(command);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }

    @Test
    void testUpdate_WhenNotFound() {
        UpdateTransactionCommand command = new UpdateTransactionCommand(1L,20,"deposit","abelmiraval"); // set fields as needed
        when(mediator.dispatch(command)).thenReturn(false);

        var response = controller.update(command);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(response.getBody());
    }

    @Test
    void testDelete_WhenSuccess() {
        Long id = 1L;
        when(mediator.dispatch(any(DeleteTransactionCommand.class))).thenReturn(true);

        var response = controller.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(response.getBody());
    }

    @Test
    void testDelete_WhenNotFound() {
        Long id = 99L;
        when(mediator.dispatch(any(DeleteTransactionCommand.class))).thenReturn(false);

        var response = controller.delete(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(response.getBody());
    }
}
