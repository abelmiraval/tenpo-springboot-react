package pe.abelmiraval.tenpo.infraestructure.shared.cqrs.query;

import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.dispatchable.Dispatchable;

/**
 * Used for requests that read the application state
 */
public interface Query<TResult> extends Dispatchable<TResult> {
}