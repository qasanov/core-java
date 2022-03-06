package streams;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamArrayTest {

    @Test
    public void streamOfObjectArray() {
        String[] strings = {"Hello", "Java" ,"8"};
        Stream<String> stringsStreams = Stream.of(strings);
        assertThat(stringsStreams.count()).isEqualTo(strings.length);
    }

    @Test
    public void streamOfPrimitiveArray() {
        int[] ints = {1,2,3,4,5};
        IntStream intStream = IntStream.of(ints);
        int sum = intStream.sum();
        assertThat(sum).isEqualTo(15);
    }

    @Test
    public void arraysStreamWithRange() {
        String[] strings = {"a", "b" ,"c", "d", "e" ,"f"};
        Stream<String> stringStream = Arrays.stream(strings, 2, 5);
        String mergedString = stringStream.collect(Collectors.joining(""));
        assertThat(mergedString).isEqualTo("cde");
    }

    @Test
    public void arraysStreamSum() {
        int[] ints = {1,2,3,4,5};
        int sum = Arrays.stream(ints).sum();
        assertThat(sum).isEqualTo(15);
    }

    @Test
    public void streamToArray() {
        Stream<String> stringStream = Stream.of("a", "b", "c", "d", "e", "f");
        String[] strings = stringStream.toArray(String[]::new); //size -> new String[size]
        assertThat(strings.length).isEqualTo(strings.length);
    }

    @Test
    public void arrayToStream() {
        int[] ints = {1,2,3,4,5};
        IntStream intStream = Arrays.stream(ints);
        int sum = intStream.sum();
        assertThat(sum).isEqualTo(15);
    }
}
