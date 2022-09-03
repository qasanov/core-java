package streams;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @see : https://www.tutorialspoint.com/java8/java8_streams.htm
 *        https://stackify.com/streams-guide-java-8/
 */
public class BasicStreamTest {

    private final List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private final List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

    private final List<String> stringListWithUnderScore = Arrays.asList("a_b_c",  "b_c_d", "e_f_g", "a_b_c", "j_k_l");

    @Test
    public void splitString() {
        List<Object[]> ojbList= stringListWithUnderScore.stream()
                .map( s -> s.split("_"))
                .map(strings -> new Object[]{strings[0],strings[1],strings[2]})
                .collect(Collectors.toList());

        assertThat(ojbList.size()).isEqualTo(stringListWithUnderScore.size());
        assertThat(ojbList.get(0).length).isEqualTo(3);
    }

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
    public void peek() {
        Integer[] integers = integerList.stream().map(integer -> integer * integer).peek(System.out::println).toArray(Integer[]::new);
        assertThat(integers.length).isEqualTo(integerList.size());
    }

    @Test
    public void map() {
        integerList.stream().map(integer -> integer * 2).forEach(System.out::println);
    }

    @Test
    public void flatMap() {
        List<List<String>> namesNested = Arrays.asList(
                Arrays.asList("Java", "8"),
                Arrays.asList("Angular", "12"),
                Arrays.asList("Solidity", "0.8"));

        List<String> namesFlatStream = namesNested.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        assertThat(namesFlatStream.size()).isEqualTo(namesNested.size() * 2);
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
    public void min() {
        Optional<Integer> min = integerList.stream().min(Integer::compareTo);
        assertThat(min.get()).isEqualTo(1);
    }

    @Test
    public void max() {
        Optional<Integer> max = integerList.stream().max(Integer::compareTo);
        assertThat(max.get()).isEqualTo(10);
    }

    @Test
    public void distinct() {
        List<Integer> list = Arrays.asList(1, 1, 3, 4, 5, 5, 7);
        Stream<Integer> distinct = list.stream().distinct();
        assertThat(distinct).isEqualTo(Arrays.asList(1, 3, 4, 5, 7));
    }

    @Test
    public void reduce() {
        Integer sum = integerList.stream().reduce(0, Integer::sum);
        assertThat(sum).isEqualTo(55);
    }

    @Test
    public void allMatch() {
        boolean allMatch = integerList.stream().allMatch(integer -> integer % 2 == 0);
        assertThat(allMatch).isEqualTo(false);
    }

    @Test
    public void anyMatch() {
        boolean anyMatch = integerList.stream().anyMatch(integer -> integer % 2 == 0);
        assertThat(anyMatch).isEqualTo(true);
    }

    @Test
    public void noneMatch() {
        boolean noneMatch = integerList.stream().limit(5).noneMatch(integer -> integer % 7 == 0);
        assertThat(noneMatch).isEqualTo(true);
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
    public void parallelProcessing() {
        long count = stringList.parallelStream().filter(s -> s.isEmpty()).count();
        assertThat(count).isEqualTo(2);
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
