package lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see : https://mkyong.com/java8/java-8-method-references-double-colon-operator/
 *        https://www.baeldung.com/java-method-references
 */
public class MethodReferenceTest {

    private List<String> list = Arrays.asList("Hello", "world", "java","8");

    @Test
    public void version1_AnonymousClass() {
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void version2_LambdaExpression() {
        list.forEach( s -> System.out.println(s) );
    }

    @Test
    public void version3_MethodReference() {
        list.forEach(System.out::println);
    }


    /**
     * @apiNote  : (args) -> ClassName.staticMethod(args)  SAME AS   ClassName::staticMethod
     */
    @Test
    public void staticReference() {
        Function<String,Integer> lambdaExp = s->Integer.parseInt(s);
        Function<String,Integer> staticRef = Integer::parseInt;

        String valueAsString = "10";
        Integer val1 = lambdaExp.apply(valueAsString);
        Integer val2 = staticRef.apply(valueAsString);

        assertThat(val1).isEqualTo(val2);
    }

    /**
     * @apiNote : (args) -> object.instanceMethodName(args) SAME AS object::instanceMethodName
     */
    @Test
    public void instanceReferenceOfParticularObject() {
        StringComparator stringComparator = new StringComparator();
        list.stream().sorted((a,b) ->stringComparator.compareByLength(a,b)).forEach(System.out::println);
        list.stream().sorted(stringComparator::compareByLength).forEach(System.out::println);
    }

    /**
     * @apiNote : (args, rest) -> args.instanceMethodName(rest) SAME AS ClassNameOfArgs::instanceMethodName
     */
    @Test
    public void instanceReferenceOfArbitraryObject() {
        list.stream().sorted((a,b)->a.compareToIgnoreCase(b)).forEach(System.out::println);
        list.stream().sorted(String::compareToIgnoreCase).forEach(System.out::println);
    }


    @Test
    public void referenceToConstructor() {
        list.stream().map((arg) -> new WordClass(arg)).forEach(System.out::println);
        list.stream().map(WordClass::new).forEach(System.out::println);
    }



    class StringComparator {
        public int compareByLength(String s1, String s2){
            return Integer.compare(s1.length(), s2.length());
        }
    }

    class WordClass{
        String word;

        public WordClass(String word) {
            this.word = word;
        }

        @Override
        public String toString() {
            return "WordClass{" +
                    "word='" + word + '\'' +
                    '}';
        }
    }
}
