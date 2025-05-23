package pe.abelmiraval.tenpo.infraestructure.shared.cqrs;

import pe.abelmiraval.tenpo.infraestructure.shared.cqrs.dispatchable.Dispatchable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to signify which handler should handle the {@link Dispatchable}
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandledBy {
    Class<? extends BaseHandler<? extends Dispatchable<?>, ?>> handler();
}