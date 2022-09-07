package madzi.application.environment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Environment")
class EnvironmentTest {

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
