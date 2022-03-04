package lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see : https://mkyong.com/java8/java-8-predicate-examples/
 */
public class PredicateTest {

    private final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Test
    public void simplePredicate() {
        Predicate<Integer> condition = x -> x > 8;
        List<Integer> filteredList = list.stream().filter(condition::test).collect(Collectors.toList());
        filteredList.stream().forEach(System.out::println);
        assertThat(filteredList.size()).isEqualTo(2);
    }

    @Test
    public void and() {
        Predicate<Integer> lessThen = x -> x < 8;
        Predicate<Integer> greaterThen = x -> x > 5;
        List<Integer> filteredList = list.stream().filter(greaterThen.and(lessThen)).collect(Collectors.toList());
        filteredList.stream().forEach(System.out::println);
        assertThat(filteredList.size()).isEqualTo(2);
    }

    @Test
    public void or() {
        Predicate<Integer> evenNumber = x -> x % 2 == 0;
        Predicate<Integer> specificNumber = x -> x == 5;
        List<Integer> filteredList = list.stream().filter(evenNumber.or(specificNumber)).collect(Collectors.toList());
        filteredList.stream().forEach(System.out::println);
        assertThat(filteredList.size()).isEqualTo(6);
    }

    @Test
    public void negate() {
        Predicate<Integer> evenNumber = x -> x % 2 == 0;
        List<Integer> filteredList = list.stream().filter(evenNumber.negate()).collect(Collectors.toList());
        filteredList.stream().forEach(System.out::println);
        assertThat(filteredList.size()).isEqualTo(5);
    }

    @Test
    public void test() {
        Predicate<Integer> evenNumber = x -> x % 2 == 0;
        List<Integer> filteredList = filter(list, evenNumber);
        filteredList.stream().forEach(System.out::println);
    }

    private List<Integer> filter(List<Integer> integerList, Predicate<Integer> predicate) {
        return integerList.stream().filter(predicate::test).collect(Collectors.toList());
    }
}
