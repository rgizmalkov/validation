package main.validation.resolver;

import main.validation.ValidationAnswer;
import main.validation.ValidationResolver;
import main.validation.ValidationResponse;
import main.validation.Validator;
import main.validation.meta.Nonnull;

/**
 * Created by romanizmalkov on 14.06.17.
 */
public class NonnullValidationResolver implements Validator<Object, Nonnull> {

    public ValidationAnswer validate(Object vo, Nonnull ao) {
        String desc = Nonnull.class.getAnnotation(ValidationResolver.class).errorMessage();
        Boolean result = vo != null;
       return new ValidationAnswer("Null validation", result ? "Success" : desc, result, this.getClass());
    }
}
