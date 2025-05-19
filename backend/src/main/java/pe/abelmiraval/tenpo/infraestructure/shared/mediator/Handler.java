package pe.abelmiraval.tenpo.infraestructure.shared.mediator;

import org.springframework.http.ResponseEntity;

public interface Handler<Request, Response> {
    ResponseEntity<Response> handle(Request request);
}
