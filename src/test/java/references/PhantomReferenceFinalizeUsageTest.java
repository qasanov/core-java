package references;

import org.junit.Before;
import org.junit.Test;
import util.ThreadHelp;

import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PhantomReferenceFinalizeUsageTest {

    private static final int LIST_SIZE = 10;

    private List<Object> largeObjects;
    private PhantomReferenceFinalizeUsage<Object> referenceApp;

    @Before
    public void setUp() throws Exception {
        largeObjects = new ArrayList<>();
        for (int i = 0; i < LIST_SIZE; i++) {
            largeObjects.add(new Object());
        }
        referenceApp = new PhantomReferenceFinalizeUsage<>(largeObjects);
    }

    @Test
    public void referentEnqueued() {
        largeObjects = null;
        System.gc();
        ThreadHelp.sleep();

        assertThat(referenceApp.isAllEnqueued()).isTrue();
    }

    @Test
    public void resourcedCleaned() {
        largeObjects = null;
        System.gc();
        ThreadHelp.sleep();

        referenceApp.finalizeResource();
        assertThat(referenceApp.getNumberOfCleanUp()).isEqualTo(LIST_SIZE);
    }


}
