package streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @see : https://www.tutorialspoint.com/java8/java8_streams.htm
 */
public class BasicStreamTest {

    private final List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private final List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");


    @Test
    public void filter() {
        long count = stringList.stream().filter(s -> s.isEmpty()).count();
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void forEach() {
        integerList.stream().forEach(System.out::print);
    }

    @Test
    public void map() {
        integerList.stream().map(integer -> integer * 2).forEach(System.out::println);
    }

    @Test
    public void limit() {
        Random random = new Random();
        IntPredicate intPredicate = value -> value > 0 && value < 1000;
        long count = random.ints().filter(intPredicate).limit(10).count();
        assertThat(count).isEqualTo(10);
    }

    @Test
    public void sort() {
        Random random = new Random();
        IntPredicate intPredicate = value -> value > 0 && value < 1000;
        random.ints().filter(intPredicate).limit(10).sorted().forEach(System.out::println);
    }

    @Test
    public void parallelProcessing() {
        long count = stringList.parallelStream().filter(s -> s.isEmpty()).count();
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void collectorIntoList() {
        List<String> collectedList = stringList.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        collectedList.stream().forEach(System.out::println);
        assertThat(collectedList.size()).isEqualTo(5);
    }

    @Test
    public void collectorIntoString() {
        String mergedString = stringList.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(","));
        assertThat(mergedString).isNotEmpty();
    }

    @Test
    public void statistics() {
        IntSummaryStatistics intSummaryStatistics = integerList.stream().mapToInt(integer -> integer).summaryStatistics();
        assertThat(intSummaryStatistics.getMax()).isEqualTo(10);
        assertThat(intSummaryStatistics.getMin()).isEqualTo(1);
        assertThat(intSummaryStatistics.getSum()).isEqualTo(55);
        assertThat(intSummaryStatistics.getAverage()).isEqualTo(5.5);
    }
}
