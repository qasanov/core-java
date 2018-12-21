package references;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakReferenceUsage<T> {

    private WeakReference<T> reference;
    private ReferenceQueue<T> referenceQueue;

    public WeakReferenceUsage(T referent) {
        referenceQueue = new ReferenceQueue<>();
        reference = new WeakReference<>(referent,referenceQueue);
    }


    public T get(){
        T value = reference.get();
        if(value == null)
            throw new RuntimeException("Object already removed by GC");

        return value;
    }

    public void remove(){
        reference.clear();
    }

}
