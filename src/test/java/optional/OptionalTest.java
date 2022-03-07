package optional;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @see : https://stackify.com/optional-java/
 */
public class OptionalTest {


    @Test(expected = NoSuchElementException.class)
    public void emptyObject() {
        Optional<Integer> optionalInteger = Optional.empty();
        optionalInteger.get();
    }

    @Test(expected = NullPointerException.class)
    public void of() {
        Optional<Integer> optionalInteger = Optional.of(null);
    }

    @Test
    public void ofNullable() {
        Optional<Integer> optionalInteger = Optional.ofNullable(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void ofNullable_get() {
        Optional<Integer> optionalInteger = Optional.ofNullable(null);
        optionalInteger.get();
    }

    @Test
    public void ofNullable_Get() {
        Optional<Integer> optionalInteger = Optional.ofNullable(77);
        assertThat(optionalInteger.get()).isEqualTo(77);
    }

    @Test
    public void ofNullable_IsPresent() {
        Optional<Integer> optionalInteger = Optional.ofNullable(77);
        assertThat(optionalInteger.isPresent()).isTrue();
    }

    @Test
    public void ofNullable_IfPresent() {
        Optional<Integer> optionalInteger = Optional.ofNullable(77);
        optionalInteger.ifPresent(integer -> assertThat(integer).isEqualTo(77));
    }

    @Test
    public void orElse() {
        Integer a = null, b = 77;
        Integer value = Optional.ofNullable(a).orElse(b);
        assertThat(value).isEqualTo(77);
    }

    @Test
    public void orElse_FirstParamExcepected() {
        Integer a = 44, b = 77;
        Integer value = Optional.ofNullable(a).orElse(b);
        assertThat(value).isEqualTo(44);
    }

    @Test
    public void orElseGet() {
        Integer a = null, b = 77;
        Integer value = Optional.ofNullable(a).orElseGet(() -> b);
        assertThat(value).isEqualTo(77);
    }

    @Test
    public void orElse_orElse_Similarity() {
        Integer a = null, b = 77;

        Integer orElseValue = Optional.ofNullable(a).orElse(b);
        assertThat(orElseValue).isEqualTo(77);

        Integer orElseGetValue = Optional.ofNullable(a).orElseGet(() -> b);
        assertThat(orElseGetValue).isEqualTo(77);
    }

    @Test
    public void orElse_orElse_Difference() {
        Employee a = new Employee(44);
        Employee b = new Employee(77);

        Employee orElseValue = Optional.ofNullable(a).orElse(b.increaseSalary());
        assertThat(orElseValue.salary).isEqualTo(44);
        assertThat(b.salary).isEqualTo(80);

        b.salary = 77;
        Employee orElseGetValue = Optional.ofNullable(a).orElseGet(() -> b.increaseSalary());
        assertThat(orElseGetValue.salary).isEqualTo(44);
        assertThat(b.salary).isEqualTo(77);
    }

    @Test(expected = IllegalArgumentException.class)
    public void orElseThrow() {
        Integer a = null;
        Integer integer = Optional.ofNullable(a).orElseThrow(IllegalArgumentException::new);
    }

    @Test
    public void map() {
        Employee employee = new Employee(44);
        Integer salary = Optional.ofNullable(employee).map(employee1 -> employee.salary).orElseGet(() -> -1);
        assertThat(salary).isGreaterThan(0);
    }

    //TODO : provide example
    @Test
    public void flatMap() {

    }

    //TODO : provide example
    @Test
    public void filter() {

    }

    @Test
    public void chaining() {
        Employee employee = new Employee(44);
        Address address = new Address("London");
        employee.address = address;

        String employeeAddress = Optional.ofNullable(employee)
                .flatMap(employee1 -> employee.getAddress())
                .flatMap(address1 -> address1.getStreet())
                .orElse("World");

        assertThat(employeeAddress).isEqualTo("London");
    }

    class Employee {

        int salary;
        Address address;

        public Employee(int salary) {
            this.salary = salary;
        }

        Employee increaseSalary() {
            salary += 3;
            return this;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }
    }

    class Address {
        private final String street;

        public Address(String street) {
            this.street = street;
        }

        public Optional<String> getStreet() {
            return Optional.ofNullable(street);
        }
    }


}
