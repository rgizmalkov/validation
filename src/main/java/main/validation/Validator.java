package main.validation;

import java.lang.annotation.Annotation;

/**
 * Created by romanizmalkov on 13.06.17.
 */
public interface Validator<ValidationObject, AnnotationObject extends Annotation> {
    ValidationAnswer validate(ValidationObject vo, AnnotationObject ao);
}
