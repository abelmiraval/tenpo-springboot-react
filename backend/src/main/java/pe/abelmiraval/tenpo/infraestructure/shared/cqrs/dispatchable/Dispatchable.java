package pe.abelmiraval.tenpo.infraestructure.shared.cqrs.dispatchable;

import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.command.Command;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.query.Query;

/**
 * Marker interface for {@link Query} or {@link Command}
 */
public interface Dispatchable<TResult> {
}