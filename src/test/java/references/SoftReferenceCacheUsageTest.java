package references;

import org.junit.Before;
import org.junit.Test;
import util.ThreadUtil;


import static org.assertj.core.api.Assertions.assertThat;

public class SoftReferenceCacheUsageTest {

    private SoftReferenceCacheUsage<String, Integer> cacheReference;

    @Before
    public void setUp() throws Exception {
        cacheReference = new SoftReferenceCacheUsage<>();
    }

    /**
     * @see : https://www.baeldung.com/java-soft-references
     */
    @Test
    public void addItemToCache() {
        String key = new String("someKey");
        Integer value = 15;

        cacheReference.put(key,value);

        value = null;
        Integer retrivedValue = cacheReference.get(key);
        assertThat(retrivedValue).isEqualTo(15);

        retrivedValue = null;
        value = null;
        System.gc();
        ThreadUtil.sleep();

        retrivedValue = cacheReference.get(key);
        assertThat(retrivedValue).isEqualTo(15);
    }
}