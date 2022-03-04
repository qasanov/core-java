package lambda;

import org.junit.Test;
import java.util.function.Predicate;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see : https://www.tutorialspoint.com/java8/java8_lambda_expressions.htm
 */
public class GeneralTest {

    private final int FIST_VALUE = 5;
    private final int SECOND_VALUE =3;
    private final int ADDITION_RESULT = 8;
    private final int SUBSTRACTION_RESULT = 2;

    @Test
    public void withTypeDeclaration() {
        GeneralOperation ops = (int a, int b) -> a+b;
        int opsResult = operate(ops);
        assertThat(opsResult).isEqualTo(ADDITION_RESULT);
    }

    @Test
    public void withoutTypeDeclaration() {
        GeneralOperation ops = (a, b) -> a-b;
        int opsResult = operate(ops);
        assertThat(opsResult).isEqualTo(SUBSTRACTION_RESULT);
    }

    @Test
    public void withReturnStatement() {
        GeneralOperation ops = (a, b) -> { return a+b; };
        int opsResult = operate(ops);
        assertThat(opsResult).isEqualTo(ADDITION_RESULT);
    }

    @Test
    public void withoutReturnStatement() {
        GeneralOperation ops = (a, b) -> a+b; ;
        int opsResult = operate(ops);
        assertThat(opsResult).isEqualTo(ADDITION_RESULT);
    }

    @Test
    public void singleParameterWithoutParenthesis() {
        Predicate<String> str = s -> s.equals("Hello");
        assertThat(str.test("Hello")).isEqualTo(true);
    }

    private int operate(GeneralOperation ops){
        return ops.operation(FIST_VALUE, SECOND_VALUE);
    }
}
