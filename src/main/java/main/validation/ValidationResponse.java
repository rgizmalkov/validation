package main.validation;

import com.google.common.collect.ImmutableMap;

import java.util.*;

import static main.validation.ValidationResponse.ValidationLvl.*;

/**
 * Created by romanizmalkov on 15.06.17.
 */
public class ValidationResponse {
    private Class<?> objectClazz;
    private boolean validateSign;
    private Set<Class<? extends Validator>> validateBy;

    private Map<ValidationLvl, List<ValidationAnswer>> errors;

    private ValidationResponse(Builder builder){
        this.objectClazz = builder.objectClazz;
        this.validateSign = builder.validateSign;
        this.validateBy = builder.validateBy;
        this.errors = builder.errors;
    }

    public static class Builder{
        private Class<?> objectClazz;
        private boolean validateSign;
        private Set<Class<? extends Validator>> validateBy = new HashSet<>();

        private ValidationLvl deadLine = CRITICAL;


        private Map<ValidationLvl, List<ValidationAnswer>> errors = ImmutableMap.<ValidationLvl, List<ValidationAnswer>>of(
                CRITICAL, new LinkedList<>(),
                WARNING, new LinkedList<>(),
                FATAL, new LinkedList<>(),
                INFO, new LinkedList<>()
        );

        private ValidationLvlMethod validationLvlMethod = new ValidationLvlMethod() {
            @Override
            public boolean method() {
                return errors.get(deadLine).size() > 0;
            }
        };

        public Builder param(ValidationLvl validationLvl, ValidationAnswer answer, Class<? extends Validator> validatorClass){
            List<ValidationAnswer> validationAnswers = errors.get(validationLvl);
            validationAnswers.add(answer);
            validateBy.add(validatorClass);
            return this;
        }

        public Builder critical(ValidationAnswer validationAnswer, Class<? extends Validator> validatorClass){
            List<ValidationAnswer> validationAnswers = errors.get(CRITICAL);
            validationAnswers.add(validationAnswer);
            validateBy.add(validatorClass);
            return this;
        }

        public Builder warning(ValidationAnswer validationAnswer, Class<? extends Validator> validatorClass){
            List<ValidationAnswer> validationAnswers = errors.get(WARNING);
            validationAnswers.add(validationAnswer);
            validateBy.add(validatorClass);
            return this;
        }

        public Builder fatal(ValidationAnswer validationAnswer, Class<? extends Validator> validatorClass){
            List<ValidationAnswer> validationAnswers = errors.get(FATAL);
            validationAnswers.add(validationAnswer);
            validateBy.add(validatorClass);
            return this;
        }

        public Builder info(ValidationAnswer validationAnswer, Class<? extends Validator> validatorClass){
            List<ValidationAnswer> validationAnswers = errors.get(INFO);
            validationAnswers.add(validationAnswer);
            validateBy.add(validatorClass);
            return this;
        }

        public Builder deadLine(ValidationLvl lvl){
            this.deadLine = lvl;
            return this;
        }

        public Builder sign(ValidationLvlMethod method){
            this.validationLvlMethod = method;
            return this;
        }


        public ValidationResponse build(Class<?> objectClazz){
            this.validateSign = validationLvlMethod.method();
            this.objectClazz = objectClazz;
            return new ValidationResponse(this);
        }
    }

    public enum ValidationLvl{
        CRITICAL("critical"),
        WARNING("warning"),
        FATAL("fatal"),
        INFO("info");

        private String value;

        ValidationLvl(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValidationResponse that = (ValidationResponse) o;

        if (validateSign != that.validateSign) return false;
        if (objectClazz != null ? !objectClazz.equals(that.objectClazz) : that.objectClazz != null) return false;
        if (validateBy != null ? !validateBy.equals(that.validateBy) : that.validateBy != null) return false;
        return errors != null ? errors.equals(that.errors) : that.errors == null;
    }

    @Override
    public int hashCode() {
        int result = objectClazz != null ? objectClazz.hashCode() : 0;
        result = 31 * result + (validateSign ? 1 : 0);
        result = 31 * result + (validateBy != null ? validateBy.hashCode() : 0);
        result = 31 * result + (errors != null ? errors.hashCode() : 0);
        return result;
    }
}
