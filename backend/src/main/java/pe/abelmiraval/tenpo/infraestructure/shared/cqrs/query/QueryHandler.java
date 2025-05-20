package pe.abelmiraval.tenpo.infraestructure.shared.cqrs.query;

import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.BaseHandler;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.dispatchable.Dispatchable;

public interface QueryHandler<TQuery extends Dispatchable<TResult>, TResult> extends BaseHandler<TQuery, TResult> {
}