package main.validation.example;

import lombok.ToString;
import main.validation.meta.Nonnull;

/**
 * Created by romanizmalkov on 14.06.17.
 */
@ToString
public class ClassA {
    @Nonnull
    private ClassB classB = new ClassB();
    @Nonnull
    private ClassC classC= new ClassC();;

//    @Nonnull
//    private ClassD classD = new ClassD();
//
//    @Nonnull
//    private ClassE classE = new ClassE();
}
