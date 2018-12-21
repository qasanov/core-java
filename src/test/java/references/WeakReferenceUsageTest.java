package references;

import org.junit.Before;
import org.junit.Test;
import util.ThreadUtil;

import static org.junit.Assert.*;

import static org.assertj.core.api.Assertions.assertThat;

public class WeakReferenceUsageTest {

    private WeakReferenceUsage<String> reference;

    @Before
    public void setUp() throws Exception {
        String object = new String("someObject");
        reference = new WeakReferenceUsage(object);
    }

    @Test
    public void getKey() {
        String s = reference.get();
        assertThat(s).isNotNull();
    }

    @Test(expected = RuntimeException.class)
    public void getKeyAfterGCCall() {
        String s = reference.get();
        assertThat(s).isNotNull();

        s = null;
        System.gc();
        ThreadUtil.sleep();

        s = reference.get();
        assertThat(s).isNull();
    }
}