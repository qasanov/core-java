package streams;

import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see : https://stackify.com/streams-guide-java-8/
 */
public class InfiniteStreamTest {

    @Test
    public void generate() {
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    @Test
    public void iterate() {
        Stream.iterate(2, integer -> integer * 2).limit(10).forEach(System.out::println);
    }
}
