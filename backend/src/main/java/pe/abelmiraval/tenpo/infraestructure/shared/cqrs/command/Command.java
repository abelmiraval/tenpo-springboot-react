package pe.abelmiraval.tenpo.infraestructure.shared.cqrs.command;

import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.dispatchable.Dispatchable;

/**
 * Used for requests that change the application state
 */
public interface Command<TResult> extends Dispatchable<TResult> {
}
