package pe.abelmiraval.tenpo.application.transaction.commands.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pe.abelmiraval.tenpo.infraestructure.shared.mediator.Handler;

import java.util.UUID;

@Component
public class CreateTransactionCommandHandler implements Handler<CreateTransactionCommand, UUID> {
    @Override
    public ResponseEntity<UUID> handle(CreateTransactionCommand request){

        return new ResponseEntity<>(UUID.randomUUID(), HttpStatus.CREATED);
    }
}
