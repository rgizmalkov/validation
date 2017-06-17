package main.validation;

/**
 * Created by romanizmalkov on 15.06.17.
 */
public enum BuildInstruction {
    ADD_BALANCED(ValidationExecutorOperation.BALANCED),
    ADD_NON_BALANCED(ValidationExecutorOperation.NON_BALANCED);

    private ValidationExecutorOperation instruction;

    BuildInstruction(ValidationExecutorOperation instruction) {
        this.instruction = instruction;
    }
}
