package main.validation;


import main.validation.ValidationTree.ValidationObject;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by romanizmalkov on 15.06.17.
 */
public class ValidationExecutor {
    private ValidationTree validationTree = new ValidationTree();
    private List<ValidationExecutorOperation> operations;

    public ValidationExecutor(){
        this.operations = new LinkedList<>();
        operations.add(ValidationExecutorOperation.NON_BALANCED);
        operations.add(ValidationExecutorOperation.NON_CIRCLING);
        operations.add(ValidationExecutorOperation.NON_MULTI_STREAM);
    }

    public <O> ValidationExecutor compute(@Nonnull O object){
        ValidationObject<O> tree = validationTree.validationTree(object);
        List<ValidationResponse> responses = validate(tree);
        return null;
    }

    private <O> List<ValidationResponse> validate(ValidationObject<O> tree) {


        return;
    }



    public ValidationExecutor balanced(boolean is){
        operations.add(ValidationExecutorOperation.BALANCED);
        return this;
    }

    public ValidationExecutor concurrent(boolean is){
        operations.add(ValidationExecutorOperation.MULTI_STREAM);
        return this;
    }

    public ValidationExecutor circling(boolean is){
        operations.add(ValidationExecutorOperation.CIRCLING);
        return this;
    }

    public ValidationExecutor balanced(){
        balanced(true);
        return this;
    }

    public ValidationExecutor concurrent(){
        concurrent(true);
        return this;
    }

    public ValidationExecutor circling(){
        circling(true);
        return this;
    }

}
