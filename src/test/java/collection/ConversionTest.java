package collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConversionTest {

    @Test
    public void arraysToList_FixedSize_BackedByArray() {
        Integer[] ints = {1, 2, 3, 4, 5};
        List<Integer> integerList = Arrays.asList(ints);
        assertThat(integerList.size()).isEqualTo(ints.length);
    }

    @Test
    public void arraysToList_WithNewArrayList() {
        Integer[] ints = {1, 2, 3, 4, 5};
        List<Integer> integerList = new ArrayList<>(Arrays.asList(ints));
        assertThat(integerList.size()).isEqualTo(ints.length);
    }

    @Test
    public void listToArray() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        Integer[] targetArray = integerList.toArray(new Integer[0]);
        assertThat(targetArray.length).isEqualTo(integerList.size());
    }

}
