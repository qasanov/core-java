package references;

import org.junit.Before;
import org.junit.Test;
import util.ThreadUtil;

import static org.assertj.core.api.Assertions.assertThat;

public class PhantomReferenceFinalizeUsageSingleObjTest {

    private PhantomReferenceFinalizeUsageSingleObj<Object> phantomReference;
    private Object referent;

    @Before
    public void setUp() throws Exception {
        referent = new Object();
        phantomReference = new PhantomReferenceFinalizeUsageSingleObj<>(referent);
    }

    @Test
    public void referentEnqueued() {
        referent = null;
        System.gc();
        ThreadUtil.sleep();
        assertThat(phantomReference.isEnqueued()).isTrue();
    }

    @Test
    public void resourceCleaned() {
        referent = null;
        System.gc();
        ThreadUtil.sleep();
        phantomReference.finalizeResource();
        assertThat(phantomReference.isResourceCleaned()).isTrue();
    }
}