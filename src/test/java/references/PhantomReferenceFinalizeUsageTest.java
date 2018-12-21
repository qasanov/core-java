package references;

import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PhantomReferenceFinalizeUsageTest {

    private static final int LIST_SIZE = 10;
    private static final long DEFAULT_SLEEP_TIME = 300;
    private List<Object> largeObjects;
    private PhantomReferenceFinalizeUsage<Object> referenceApp;

    @Before
    public void setUp() throws Exception {
        largeObjects = new ArrayList<>();
        for (int i = 0; i < LIST_SIZE; i++) {
            largeObjects.add(new Object());
        }
        referenceApp = new PhantomReferenceFinalizeUsage<>();
    }

    @Test
    public void referentEnqueued() {
        referenceApp.addItem(largeObjects);

        largeObjects = null;
        System.gc();
        sleep(DEFAULT_SLEEP_TIME);

        assertThat(referenceApp.isAllEnqueued()).isTrue();
    }

    @Test
    public void resourcedCleaned() {
        referenceApp.addItem(largeObjects);

        largeObjects = null;
        System.gc();
        sleep(DEFAULT_SLEEP_TIME);

        referenceApp.finalizeResource();
        assertThat(referenceApp.getNumberOfCleanUp()).isEqualTo(LIST_SIZE);
    }

    private void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
