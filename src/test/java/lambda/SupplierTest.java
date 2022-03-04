package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.function.Supplier;
import static org.assertj.core.api.Assertions.assertThat;

public class SupplierTest {

    @Test
    public void supply() {
        Supplier<Fruit> fruitSupplier = () -> new Fruit("apple");
        Fruit fruit = fruitSupplier.get();
        assertThat(fruit.name).isEqualTo("apple");
    }

    @Test
    public void returnSupplier() {
        Fruit fruit = supplier("pineapple").get();
        assertThat(fruit.name).isEqualTo("pineapple");
    }

    /**
     * @see : https://www.javaguides.net/2018/10/factory-pattern-using-java-8-lambda-expressions.html
     *        https://stackoverflow.com/questions/23126207/what-is-the-most-effective-way-of-writing-a-factory-method
     */
    @Test
    public void asFactory() {
        Supplier<Fruit<String>> fruitSupplier = Fruit::new;
        Fruit<String> fruit = fruitSupplier.get();
        assertThat(fruit.name).isNull();
    }

    private <T> Supplier<Fruit<T>> supplier(T name) {
        return () ->new Fruit(name);
    }



    class Fruit<T>{
        private T name;

        public Fruit() {
        }

        public Fruit(T name) {
            this.name = name;
        }
    }
}
