package streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see : https://stackify.com/streams-guide-java-8/
 */
public class StreamConceptTest {

    @Test
    public void shortCircuiting() {
        Stream<Integer> infiniteStream = Stream.iterate(2, integer -> integer * 2);
        List<Integer> integerList = infiniteStream
                .peek(System.out::println)
                .skip(3)
                .limit(5)
                .collect(Collectors.toList());
        assertThat(integerList).isEqualTo(Arrays.asList(16, 32, 64, 128, 256));
    }

    @Test
    public void lazyEvaluation() {
        Stream<Integer> infiniteStream = Stream.iterate(5, integer -> integer * 2);
        Optional<Integer> first = infiniteStream
                .peek(System.out::println)
                .filter(integer -> integer % 5 == 0)
                .filter(integer -> integer % 8 == 0)
                .limit(5)
                .findFirst();
        assertThat(first.get()).isEqualTo(40);
    }
}
