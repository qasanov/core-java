package references;


import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * @see : https://www.baeldung.com/java-phantom-reference
 */

public class PhantomReferenceFinalizeUsage<T> {

    private int numberOfCleanUp = 0;
    private ReferenceQueue<T> referenceQueue = new ReferenceQueue<>();
    private List<LargeObjectFinalizer> largeObjectFinalizerList = new ArrayList<>();

    public PhantomReferenceFinalizeUsage(List<T> items) {
        init(items);
    }

    private class LargeObjectFinalizer extends PhantomReference<T> {

        public LargeObjectFinalizer(T referent, ReferenceQueue<? super T> q) {
            super(referent, q);
        }

        public void finalizeResources() {
            // free resources
            PhantomReferenceFinalizeUsage.this.numberOfCleanUp++;
        }
    }


    private void init(List<T> items){
        for (T item : items) {
            largeObjectFinalizerList.add(new LargeObjectFinalizer(item, referenceQueue));
        }
    }

    public boolean isAllEnqueued(){
        for (LargeObjectFinalizer largeObjectFinalizer : largeObjectFinalizerList) {
            if(!largeObjectFinalizer.isEnqueued()){
                return false;
            }
        }
        return true;
    }

    public void finalizeResource(){
        Reference<?> referenceFromQueue;
        while ((referenceFromQueue = referenceQueue.poll()) != null) {
            ((LargeObjectFinalizer)referenceFromQueue).finalizeResources();
            referenceFromQueue.clear();
        }
    }

    public int getNumberOfCleanUp() {
        return numberOfCleanUp;
    }
}
