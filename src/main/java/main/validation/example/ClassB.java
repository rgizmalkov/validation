package main.validation.example;

import lombok.ToString;
import main.validation.meta.Nonnull;

/**
 * Created by romanizmalkov on 14.06.17.
 */
@ToString
public class ClassB {
    @Nonnull
    private ClassC classC1= new ClassC();;
    @Nonnull
    private ClassC classC2= new ClassC();;
    @Nonnull
    private ClassC classC3= new ClassC();;
    @Nonnull
    private ClassD classD= new ClassD();;
}
