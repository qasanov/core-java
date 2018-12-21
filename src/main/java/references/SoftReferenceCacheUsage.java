package references;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @see : http://peters-andoird-blog.blogspot.com/2012/05/softreference-cache.html
 */

public class SoftReferenceCacheUsage<K,V> {

    private final Map<K, SoftReference<V>> map;

    public SoftReferenceCacheUsage() {
        map = new HashMap<K, SoftReference<V>>();
    }

    public void put(K key, V value) {
        map.put(key, new SoftReference<V>(value));
    }

    public V get(K key){
        V value = null;
        SoftReference<V> reference = map.get(key);
        if(reference != null)
            value = reference.get();

        return value;
    }
}
