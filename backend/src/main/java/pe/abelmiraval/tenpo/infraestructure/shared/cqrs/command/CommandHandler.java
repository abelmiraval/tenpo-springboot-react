package pe.abelmiraval.tenpo.infraestructure.shared.cqrs.command;

import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.BaseHandler;
import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.dispatchable.Dispatchable;

public interface CommandHandler<TCommand extends Dispatchable<TResult>, TResult> extends BaseHandler<TCommand, TResult> {
}