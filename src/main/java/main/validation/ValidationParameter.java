package main.validation;

import lombok.SneakyThrows;
import lombok.ToString;
import main.validation.example.ClassD;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;

/**
 * Created by romanizmalkov on 13.06.17.
 */
@ToString
public class ValidationParameter {

    private Class<?> validatorClass;
    private Annotation annotation;

    public ValidationParameter(@Nonnull Class<?> validatorClass,@Nonnull Annotation annotation) {
        this.validatorClass = validatorClass;
        this.annotation = annotation;
    }

    @SneakyThrows @SuppressWarnings("all")
    public <O, A extends Annotation> Validator<O, A> newInstanceOfValidator(){
        Class<A> aClass = (Class<A>) annotation.annotationType();
        ValidationResolver validationResolver = aClass.getAnnotation(ValidationResolver.class);
        Class<? extends Validator<O, A>> validatorClass = (Class<? extends Validator<O, A>>) validationResolver.validator();
        return validatorClass.newInstance();
    }

    public Class<? extends Annotation> getAnnotation(){
        return annotation.annotationType();
    }
}
