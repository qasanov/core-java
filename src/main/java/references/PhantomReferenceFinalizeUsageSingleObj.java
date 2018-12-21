package references;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceFinalizeUsageSingleObj<T> {

    private boolean isResourceCleaned = false;
    private ReferenceQueue<T> referenceQueue = new ReferenceQueue<>();
    private LargeObjectFinalizer largeObjectFinalizer;

    public PhantomReferenceFinalizeUsageSingleObj(T item){
        this.largeObjectFinalizer = new LargeObjectFinalizer(item, referenceQueue);
    }

    private class LargeObjectFinalizer extends PhantomReference<T> {

        public LargeObjectFinalizer(T referent, ReferenceQueue<? super T> q) {
            super(referent, q);
        }

        public void finalizeResources() {
            // free resources
            isResourceCleaned = true;
        }
    }

    public boolean isEnqueued(){
        return largeObjectFinalizer.isEnqueued();
    }

    public void finalizeResource(){
        Reference<?> referenceFromQueue = referenceQueue.poll();
        if(referenceFromQueue != null){
            ((LargeObjectFinalizer)referenceFromQueue).finalizeResources();
            referenceFromQueue.clear();
        }
    }

    public boolean isResourceCleaned() {
        return isResourceCleaned;
    }
}
