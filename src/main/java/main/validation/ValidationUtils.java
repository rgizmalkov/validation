package main.validation;

import com.google.common.collect.ImmutableSet;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by romanizmalkov on 15.06.17.
 */
public class ValidationUtils {


    private final static ImmutableSet<Class<?>> endPoints;

    static {
        List<Class<?>> points = Arrays.asList(
                Integer.class,
                Long.class,
                Float.class,
                Double.class,
                BigDecimal.class,
                Character.class,
                Byte.class,
                Short.class,
                String.class,
                Void.class
        );

        endPoints = ImmutableSet.copyOf(points);
    }

    public static <T> boolean isEndPoint(@Nonnull T obj){
        return isEndPoint(obj.getClass());
    }

    public static <T> boolean isEndPoint(@Nonnull Class<T> objClass){
        return objClass.isPrimitive() || endPoints.contains(objClass) || objClass.isInstance(Iterable.class) || objClass.isInstance(Map.class);
    }
}
