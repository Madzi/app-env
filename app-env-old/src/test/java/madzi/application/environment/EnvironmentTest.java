package madzi.application.environment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Environment")
class EnvironmentTest {

    @Test
    @DisplayName("can use lambda as key when it needed")
    void testGetAdHocProperty() {
        final String key = "adhoc";
        final String value = "value";
        final Environment environment = Environment.builder().with(() -> key, value).build();
        Assertions.assertEquals(value, environment.getProperty(() -> key));
    }

    @Test
    @DisplayName("can return value for existing value")
    void testGetPropertyExistingVariable() {
        final String value = "42";
        final Environment environment = Environment.builder().with(TestVariable.MY_VARIABLE, value).build();
        Assertions.assertEquals(value, environment.getRequiredProperty(TestVariable.MY_VARIABLE));
    }

    @Test
    @DisplayName("can return NULL value for existing variable")
    void testGetPropertyExistingNullVariable() {
        final String value = null;
        final Environment environment = Environment.builder().with(TestVariable.MY_VARIABLE, value).build();
        Assertions.assertEquals(value, environment.getRequiredProperty(TestVariable.MY_VARIABLE));
    }

    @Test
    @DisplayName("can raise exception for non existing variable")
    void testGetPropertyNonExistingVariable() {
        final Environment environment = Environment.builder().build();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            environment.getRequiredProperty(TestVariable.MY_VARIABLE);
        });
    }

    @Test
    @DisplayName("can provide variable when it's exists")
    void testGetDefaultPropertyExistingVariable() {
        final String value = "42";
        final Environment environment = Environment.builder().with(TestVariable.MY_VARIABLE, value).build();
        Assertions.assertEquals(value, environment.getProperty(TestVariable.MY_VARIABLE, null));
    }

    @Test
    @DisplayName("can provide null value when variable is null")
    void testGetDefaultPropertyExistingNullVariable() {
        final String value = "42";
        final Environment environment = Environment.builder().with(TestVariable.MY_VARIABLE, null).build();
        Assertions.assertEquals(null, environment.getProperty(TestVariable.MY_VARIABLE, value));
    }

    @Test
    @DisplayName("can provide default value when variable is not exists")
    void testGetDefaultPropertyNonExistingVariable() {
        final String value = "42";
        final Environment environment = Environment.builder().build();
        Assertions.assertEquals(value, environment.getProperty(TestVariable.MY_VARIABLE, value));
    }

    @Test
    @DisplayName("can return existing value")
    void testGetRequiredPropertyExistingVariable() {
        final String value = "42";
        final Environment environment = Environment.builder().with(TestVariable.MY_VARIABLE, value).build();
        Assertions.assertEquals(value, environment.getRequiredProperty(TestVariable.MY_VARIABLE));
    }

    @Test
    @DisplayName("can return NULL value, when variable is present")
    void testGetRequiredPropertyExistingNullVariable() {
        final Environment environment = Environment.builder().with(TestVariable.MY_VARIABLE, null).build();
        Assertions.assertNull(environment.getRequiredProperty(TestVariable.MY_VARIABLE));
    }

    @Test
    @DisplayName("can raise exception when required varialbe is missing")
    void testGetRequiredPropertyNonExistingVariable() {
        final Environment environment = Environment.builder().build();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            environment.getRequiredProperty(TestVariable.MY_VARIABLE);
        });
    }

    @Test
    @DisplayName("can be created with provided value")
    void testPossibilityCreateWithVariable() {
        final String myValue = "my-value";
        final Environment environment = Environment.builder().with(TestVariable.MY_VARIABLE, myValue).build();
        Assertions.assertEquals(myValue, environment.getProperty(TestVariable.MY_VARIABLE));
    }

    enum TestVariable implements Environment.Variable {
        MY_VARIABLE("my.key");

        TestVariable(final String key) {
            this.key = key;
        }

        private final String key;

        @Override
        public String key() {
            return key;
        }
    }
}
