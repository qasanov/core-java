package lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see : https://mkyong.com/java8/java-8-consumer-examples/
 */
public class ConsumerTest {

    private List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);


    @Test
    public void simpleConsumer() {
        Consumer<Integer> integerConsumer = x -> System.out.println(x);
        integerConsumer.accept(2);
    }

    /**
     * @see : https://medium.com/@knoldus/functional-java-lets-understand-the-higher-order-function-1a4d4e4f02af
     */
    @Test
    public void higherOrderFunction() {
        consume(list, x-> System.out.println(x));

        consume(list, x-> System.out.println(x*2));
    }

    private <T> void consume(List<T> list, Consumer<T> consumer){
        list.stream().forEach(consumer::accept);
    }
}
