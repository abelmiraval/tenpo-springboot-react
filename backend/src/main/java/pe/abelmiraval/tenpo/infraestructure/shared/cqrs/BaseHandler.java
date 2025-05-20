package pe.abelmiraval.tenpo.infraestructure.shared.cqrs;

import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.dispatchable.Dispatchable;

/**
 * Base handler for all types of {@link Dispatchable}
 * @param <TResult> the return type of the dispatchable
 */
public interface BaseHandler<TDispatchable extends Dispatchable<TResult>, TResult> {

    TResult handle(final TDispatchable dispatchable);
}