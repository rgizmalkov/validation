package main.validation;

import javax.annotation.Nonnull;

/**
 * Created by romanizmalkov on 15.06.17.
 */
public class ValidationAnswer {
    private String title;
    private String description;
    private Boolean success;
    private Class<? extends Validator> validatedBy;

    public ValidationAnswer(@Nonnull String title, String description, @Nonnull Boolean success, Class<? extends Validator> validatedBy) {
        this.title = title;
        this.description = description;
        this.success = success;
        this.validatedBy = validatedBy;
    }
}
