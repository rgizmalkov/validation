package main.validation.example;

import lombok.ToString;
import main.validation.meta.Nonnull;

/**
 * Created by romanizmalkov on 14.06.17.
 */
@ToString
public class ClassD {
    @Nonnull
    private Integer val1 = 10;
    @Nonnull
    private Long val2 = 11L;
}
