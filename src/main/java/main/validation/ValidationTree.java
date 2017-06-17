package main.validation;

import com.google.common.collect.ImmutableSet;
import lombok.ToString;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

import static main.validation.ValidationUtils.isEndPoint;

/**
 * Created by romanizmalkov on 13.06.17.
 */
@ToString     @SuppressWarnings("all")
public class ValidationTree {

    public <T> ValidationObject<T> validationTree(@Nonnull T object){
        return buildTree(object, new ValidationObject<T>(null, object));
    }
    public <T> ValidationObject<T> validationTree(@Nonnull T object, BuildInstruction[] buildInstruction){
        return buildTreeWithParams(object, new ValidationObject<T>(null, object), buildInstruction);
    }

    private <T> ValidationObject<T> buildTree(@Nonnull T object, @Nonnull ValidationObject<T> root){
        Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            ReflectionUtils.makeAccessible(field);
            Object leave = ReflectionUtils.getField(field, object);
            ValidationParameter[] validateParams = getParamFromField(field);
            ValidationObject<Object> leaveValidationObject = new ValidationObject(validateParams, leave);
            if(!isEndPoint(leave)){
                buildLeave(leave, leaveValidationObject);
            }
                root.addLeave(leaveValidationObject);
        }

        return root;
    }

    private void buildLeave(@Nonnull Object leave,@Nonnull ValidationObject<Object> leaveValidationObject) {
        buildTree(leave, leaveValidationObject);
    }

    private <T> ValidationObject<T> buildTreeWithParams(T object, ValidationObject<T> validationObject, BuildInstruction[] buildInstruction){
        return buildTree(object, validationObject);
    }



    private ValidationParameter[] getParamFromField(@Nonnull Field field) {
        List<ValidationParameter> validationParameters = new ArrayList<ValidationParameter>();

        Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
        for (Annotation declaredAnnotation : declaredAnnotations) {
            Class<? extends Annotation> aClass = declaredAnnotation.annotationType();
            Validation validation = aClass.getAnnotation(Validation.class);
            ValidationResolver validationResolver = aClass.getAnnotation(ValidationResolver.class);
            if(validation == null || validationResolver == null){
                continue;
            }
            validationParameters.add(new ValidationParameter(validationResolver.validator(), declaredAnnotation));
        }
        return validationParameters.toArray(new ValidationParameter[validationParameters.size()]);
    }

    @ToString
    public static class ValidationObject<O>{
        private List<ValidationObject> leaves;

        private ValidationParameter[] validationParameter;

        private O object;

        public ValidationObject(ValidationParameter[] validationParameter,@Nonnull O object) {
            this.leaves = new LinkedList<ValidationObject>();
            this.validationParameter = validationParameter;
            this.object = object;
        }

        public void addLeave(ValidationObject<?> leave){
            leaves.add(leave);
        }

        public ValidationResponse validate(){
            for (ValidationParameter param: validationParameter) {
                Validator<Object, Annotation> objectAnnotationValidator = param.newInstanceOfValidator();
                Class<? extends Annotation> annotation = param.getAnnotation();
                objectAnnotationValidator.validate(object, );
            }

        }
    }



}
