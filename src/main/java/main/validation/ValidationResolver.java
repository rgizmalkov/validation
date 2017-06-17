package main.validation;

import java.lang.annotation.*;

/**
 * Created by romanizmalkov on 13.06.17.
 */

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidationResolver {
    Class<? extends Validator<?, ? extends Annotation>> validator();
    String errorMessage() default "";
}
