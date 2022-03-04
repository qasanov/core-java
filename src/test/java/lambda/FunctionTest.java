package lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see : https://mkyong.com/java8/java-8-function-examples/
 */
public class FunctionTest {

    @Test
    public void simpleFunction() {
        Function<String, Integer> function = arg -> arg.length();
        Integer stringLength = function.apply("Java8");
        assertThat(stringLength).isEqualTo(5);
    }

    @Test
    public void chainFunction() {
        Function<String, Integer> lengthFunction = arg -> arg.length();
        Function<Integer, Integer> multiplyFunction = arg -> arg * 2;
        Integer result = lengthFunction.andThen(multiplyFunction).apply("Java8");
        assertThat(result).isEqualTo(10);
    }

    @Test
    public void listToMap() {
        List<String> list = Arrays.asList("node", "c++", "java", "javascript");
        Map<String, Integer> map = convertListToMap(list, x -> x.length());
        map.forEach((s, integer) -> System.out.println("Key : '" + s + "' \t Val : " + integer));
    }

    private <T, R> Map<T, R> convertListToMap(List<T> list, Function<T, R> func) {
        Map<T, R> result = new HashMap<>();
        for (T t : list) {
            result.put(t, func.apply(t));
        }
        return result;
    }
}
