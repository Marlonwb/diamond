package person.marlon.diamond.service.template;

import org.junit.jupiter.api.extension.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * A @TestTemplate method can only be executed when at least one TestTemplateInvocationContextProvider is registered.
 * Each such provider is responsible for providing a Stream of TestTemplateInvocationContext instances. Each context
 * may specify a custom display name and a list of additional extensions that will only be used for the next invocation
 * of the @TestTemplate method.
 *
 * The following example shows how to write a test template as well as how to register and implement a
 * TestTemplateInvocationContextProvider.
 *
 * A test template with accompanying extension
 */
public class MyTestTemplateInvocationContextProvider implements TestTemplateInvocationContextProvider {
    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return true;
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        return Stream.of(invocationContext("foo"), invocationContext("bar"));
    }

    private TestTemplateInvocationContext invocationContext(String parameter) {
        return new TestTemplateInvocationContext() {
            @Override
            public String getDisplayName(int invocationIndex) {
                return parameter;
            }

            @Override
            public List<Extension> getAdditionalExtensions() {
                return Collections.singletonList(new ParameterResolver() {
                    @Override
                    public boolean supportsParameter(ParameterContext parameterContext,
                                                     ExtensionContext extensionContext) {
                        return parameterContext.getParameter().getType().equals(String.class);
                    }

                    @Override
                    public Object resolveParameter(ParameterContext parameterContext,
                                                   ExtensionContext extensionContext) {
                        return parameter;
                    }
                });
            }
        };
    }
}
