package pe.abelmiraval.tenpo.infraestructure.shared.cqrs.dispatchable;

public interface DispatchableHandler {
    <TResult> TResult dispatch(Dispatchable<TResult> dispatchable);
}