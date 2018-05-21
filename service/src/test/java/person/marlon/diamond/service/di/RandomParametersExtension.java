package person.marlon.diamond.service.di;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Parameter;

/**
 * {@code RandomParametersExtension} showcases the {@link ParameterResolver}
 * extension API of JUnit 5 by providing injection support for random values at the
 * method parameter level.
 *
 * <p>For real world use cases for this and other extension points, check out
 * the extensions provided by the Spring and Mockito projects among others.
 *
 * <p>Please refer to the
 * <a href="https://github.com/junit-team/junit5/wiki/Third-party-Extensions">
 * Third-party Extensions wiki page</a> for additional references.
 *
 * @since 5.2
 *
 *
 */
class RandomParametersExtension implements ParameterResolver {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    @interface Random {
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.isAnnotated(Random.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return getRandomValue(parameterContext.getParameter(), extensionContext);
    }

    private Object getRandomValue(Parameter parameter, ExtensionContext extensionContext) {
        Class<?> type = parameter.getType();
        java.util.Random random = extensionContext.getRoot().getStore(ExtensionContext.Namespace.GLOBAL)//
                .getOrComputeIfAbsent(java.util.Random.class);
        if (int.class.equals(type)) {
            return random.nextInt();
        }
        if (double.class.equals(type)) {
            return random.nextDouble();
        }
        throw new ParameterResolutionException("No random generator implemented for " + type);
    }
}
