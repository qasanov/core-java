package streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectorTest {

    private final List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private final List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");


    @Test
    public void joining() {
        String joined = stringList.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(","));
        assertThat(joined).isNotEmpty();
    }

    @Test
    public void toSet() {
        Set<Integer> integerSet = integerList.stream().filter(integer -> integer % 2 == 0).collect(Collectors.toSet());
        assertThat(integerSet).isEqualTo(Stream.of(2,4,6,8,10).collect(Collectors.toSet()));
    }

    @Test
    public void partitioningBy() {
        Map<Boolean, List<Integer>> evenOrOddNumberList = integerList.stream().collect(Collectors.partitioningBy(integer -> integer % 2 == 0));
        assertThat(evenOrOddNumberList.get(true).size()).isEqualTo(5);
        assertThat(evenOrOddNumberList.get(false).size()).isEqualTo(5);
    }

    @Test
    public void groupingBy() {
        Map<Integer, List<String>> collect = stringList.stream().filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(string -> Integer.valueOf(string.length())));
        collect.forEach((s, strings) -> System.out.println(s + " - " + strings));
    }

    @Test
    public void mapping() {

    }

    @Test
    public void reducing() {

    }
}
