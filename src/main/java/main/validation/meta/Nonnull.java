package main.validation.meta;

import main.validation.Validation;
import main.validation.ValidationResolver;
import main.validation.resolver.NonnullValidationResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by romanizmalkov on 14.06.17.
 */

@Validation
@ValidationResolver(validator = NonnullValidationResolver.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Nonnull {
}
