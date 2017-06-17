package main.validation.example;

import lombok.ToString;
import main.validation.meta.Nonnull;

/**
 * Created by romanizmalkov on 14.06.17.
 */
@ToString
public class ClassE {
    @Nonnull
    private String str1 = "str1";
    @Nonnull
    private String str2 = "str2";
}
