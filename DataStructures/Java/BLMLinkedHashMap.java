package DataStructures;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashSet;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;

/**
 * A basic implementation on a Hash Map that uses Linked Lists to resolved
 * collisions. The functionality is meant to mimic that of java.util.HashMap
 *
 * Created by Brandon Morris on 1/27/16.
 */
public class BLMLinkedHashMap<K, V> extends AbstractMap<K, V>
      implements Map<K, V> {
  public static final int DEFAULT_CAPACITY = 1000;
  public static final double DEFAULT_LOAD_FACTOR = 0.75d;

  private Object[] entries;
  private int capacity, load, size;
  private float loadFactor;

  private HashSet<K> keySet;
  private HashSet<BLMEntry<K, V>> entrySet;

  /** Basic constructor; create a new linked hash map */
  public BLMLinkedHashMap() {
    // Entries is an object array, since generics and arrays don't get along
    // well in Java. Values will be of actual type BLMLinkedList<BLMEntry<K, V>>
    entries = new Object[DEFAULT_CAPACITY];

    keySet = new HashSet<K>();
    entrySet = new HashSet<BLMEntry<K, V>>();

    load = 0;
    size = 0;
    capacity = DEFAULT_CAPACITY;
  }

  /**
   * Removes all of the mappings from this map. The map will be empty after this
   * this method is complete.
   */
  public void clear() {
//    for (K key : keySet()) {
//      remove(key);
//    }
    // Reset all the values of this map
    entries = new Object[DEFAULT_CAPACITY];
    keySet = new HashSet<K>();
    load = 0;
    size = 0;
    capacity = DEFAULT_CAPACITY;
  }

  /**
   * Returns true if this map contains a mapping for the specified key.
   *
   * @param key  value whose presence is tested
   * @return  true if the map contains a mapping for the specified key
   */
  public boolean containsKey(Object key) {
    return keySet().contains(key);
  }

  /**
   * Returns true if this map maps one (or more) keys to the specified value.
   *
   * @param value the value whose presence in this map is to be tested
   * @return  true if this map maps one or more keys to the value
   */
  public boolean containsValue(Object value) {
    for (K key : keySet()) {
      if (get(key).equals(value)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns a Set view of the mappings contained in this map. The set is backed
   * by the map, so any changes to the map are reflected in the set, and vice-
   * versa.
   *
   * @return  A Set representing this map
   */
  public Set<Entry<K,V>> entrySet() {
    return new EntrySet();
  }

  /**
   * Returns the value to which the specified key is mapped, or null if this
   * map contains no mapping for the key.
   *
   * @param key the key whose associated value is to be returned
   * @return  the value to which the specified key is mapped, or null if no
   *          mapping for the key
   */
  @SuppressWarnings("unchecked")
  public V get(Object key) {
    int index = getIndex(key);
    if (entries[index] != null) {
      BLMLinkedList<BLMEntry<K, V>> entryList =
          (BLMLinkedList<BLMEntry<K, V>>)entries[index];

      for (BLMEntry<K, V> entry : entryList) {
        if (entry.getKey().equals(key)) {
          return entry.getValue();
        }
      }
    }
    return null;
  }

  /** Returns true if this Map contains no mappings */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns a Set vew of the keys contained in this map. The set is backed by
   * the map, so any changes to the map are reflected in the set, and vice-
   * versa.
   *
   * @return  a set view of the keys contained in this map.
   */
  public Set<K> keySet() {
    return new KeySet();
  }

  /**
   * Associates the specified key with the specified value in the map. Returns
   * the old value for the specified key if a previous mapping existed.
   *
   * @param key they key to add to the map (or update)
   * @param value the value to associate with the given key in the map
   * @return the old value associated with the given key, or null
   */
  @SuppressWarnings("unchecked")
  public V put(K key, V value) {
    // Resize if overloaded
    if (load / (double)capacity > DEFAULT_LOAD_FACTOR) {
      // Overloaded, so double the capacity and re-insert all the values
      capacity *= 2;
      Object[] newEntries = new Object[capacity];
      load = 0;

      for (Map.Entry<K, V> entry : entrySet()) {
        int index = getIndex(entry.getKey());

        // Initialize the linked list if the entry is null
        if (newEntries[index] == null) {
          newEntries[index] = new BLMLinkedList<BLMEntry<K, V>>();
          load++;
        }

        // Add our map entry to the linked list at the index
        BLMLinkedList<BLMEntry<K, V>> entryList =
            (BLMLinkedList<BLMEntry<K, V>>)newEntries[index];
        entryList.add(new BLMEntry<K, V>(entry.getKey(), entry.getValue()));
      }

      // Reset the entries array
      entries = newEntries;
    }

    BLMEntry<K, V> toAdd = new BLMEntry<K, V>(key, value);
    int index = getIndex(key);

    // Find or create the linked list at the hash index
    BLMLinkedList<BLMEntry<K, V>> entryList;
    if (entries[index] == null) {
      entries[index] = new BLMLinkedList<BLMEntry<K,V>>();
      entryList = (BLMLinkedList<BLMEntry<K, V>>)entries[index];
      load++;
    } else {
      entryList = (BLMLinkedList<BLMEntry<K, V>>)entries[index];
    }

    // Look for a mapping with the current key to delete, then add the mapping
    V oldValue = null;
    for (BLMEntry<K, V> entry : entryList) {
      if (entry.getKey().equals(key)) {
        oldValue = entry.getValue();
        entryList.remove(entry);
        entrySet.remove(entry);
        size--;
      }
    }
    entryList.add(toAdd);
    size++;

    keySet.add(key);
    entrySet.add(toAdd);

    return oldValue;
  }

  /**
   * Copies all of the mappings from the specified map to this map.
   *
   * @param m mappings to be stored in this map
   * @throws ClassCastException if the class of a key or value in the specified
   *                            map prevents if from being stored in this map
   * @throws NullPointerException if the specified map is null
   * @throws IllegalArgumentException if some property of a key or value in
   *                                  the specified map prevents it from being
   *                                  stored in this map
   */
  public void putAll(Map<? extends K, ? extends V> m) {
    for (K key : m.keySet()) {
      put(key, m.get(key));
    }
  }

  /**
   * Removes the mapping for a key from this map if it is present.
   *
   * @param key the key whose mapping is to be removed from the map
   * @return  the value to which this map previously associated the key
   */
  @SuppressWarnings("unchecked")
  public V remove(Object key) {
    // TODO Shrink when sufficiently unloaded?
    int index = getIndex(key);

    if (entries[index] != null) {
      BLMLinkedList<BLMEntry<K, V>> entryList =
          (BLMLinkedList<BLMEntry<K, V>>)entries[index];

      // Go through the linked list for the
      for (BLMEntry<K, V> entry : entryList) {
        if (entry.getKey().equals(key)) {
          entryList.remove(entry);
          keySet.remove(entry.getKey());
          entrySet.remove(entry);
          size--;
          if (entryList.isEmpty()) {
            entries[index] = null;
            load--;
          }
          return entry.getValue();
        }
      }
    }
    return null;
  }

  /** Return the number of mappings in this map */
  public int size() {
    return size;
  }

  public boolean equals(Object o) {
    if (o instanceof Map) {
      Map m = (Map)o;
      return keySet().equals(m.keySet());
    }
    return false;
  }

  /**
   * Returns a Collection view of the values contained in this map. The
   * collection is backed by the map, so changed to the map are reflected in the
   * collection, and vice-versa.
   *
   * @return  a colletion view of the values contained in this map
   */
  public Collection<V> values() {
    // FIXME: should be backed by the map
    ArrayList<V> values = new ArrayList<V>();
    for (Map.Entry<K, V> entry : entrySet()) {
      values.add(entry.getValue());
    }
    return values;
  }

  /** Obtain the entry index from the hashcode of a key */
  private int getIndex(Object key) {
    return (key.hashCode() & Integer.MAX_VALUE) % capacity;
  }

  /**
   * A nested class to contain the entries of the contianing map.
   *
   * @param <K> the key type associated with this map
   * @param <V> the value type associated with this map
   */
  private static class BLMEntry<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;

    /** Basic constructor; create a new Entry */
    public BLMEntry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    /**
     * Compares the specified object with this entry for equality. Returns true
     * if the object is also a map entry and the two entries represent the same
     * mapping.
     *
     * @param o the object to compare with for equality
     * @return  true if the object is equal to this entry
     */
    public boolean equals(Object o) {
      if (o instanceof Map.Entry) {
        Map.Entry me = (Map.Entry)o;
        return key.equals(me.getKey()) && value.equals(me.getValue());
      }
      return false;
    }

    /** Returns the key object of this mapping */
    public K getKey() {
      return key;
    }

    /** Returns the value object of this mapping */
    public V getValue() {
      return value;
    }

    /**
     * Set the value object of this mapping to the specified object
     *
     * @param value the object to set the value of this mapping to
     * @return the old value that this mapping used to have
     */
    public V setValue(V value) {
      V oldVal = this.value;
      this.value = value;
      return oldVal;
    }
  }

  /**
   * Private class to support a set view of the keys in the map. The set is
   * backed by the map with wrapper methods for add() and remove(), so
   * performing those operations on a KeySet will have the same effect on the
   * map that this set represents.
   */
  private class KeySet extends AbstractSet<K> implements Set<K> {
    HashSet<K> keys;

    /** Basic constructor */
    public KeySet() {
      keys = BLMLinkedHashMap.this.keySet;
    }

    /** Return the number of keys in the set */
    public int size() {
      return keys.size();
    }

    /** Iterator over the keys in the set */
    public Iterator<K> iterator() {
      return keys.iterator();
    }

    /** Wrap the put operation to back the representing map */
    public boolean add(K key) {
      boolean retVal = !keys.contains(key);
      keys.add(key);

      // Map the key to null if not already in the map
      if (retVal) {
        BLMLinkedHashMap.this.put(key, null);
      }

      return retVal;
    }

    /** Wrap the remove operation to back the representing map */
    public boolean remove(Object key) {
      boolean retVal = keys.contains(key);
      keys.remove(key);

      if (retVal) {
        BLMLinkedHashMap.this.remove(key);
      }

      return retVal;
    }

    /** Return true if the key is in the set */
    public boolean contains(Object o) {
      return keys.contains(o);
    }

    // TODO: add any more methods that are necessary
  }

  /**
   * Private class to support a set view of the entries in the map. The set is
   * backed by the map with wrapper methods for add() and remove(), so
   * performing those operations on a KeySet will have the same effect on the
   * map that this set represents.
   */
  private class EntrySet extends AbstractSet<Map.Entry<K, V>>
      implements Set<Map.Entry<K, V>> {
    HashSet<BLMEntry<K, V>> entries;

    /** The sole constructor */
    public EntrySet() {
      entries = BLMLinkedHashMap.this.entrySet;
    }

    /** Return an iterator over the entries in this set */
    public Iterator<Map.Entry<K, V>> iterator() {
      return new Iterator<Map.Entry<K, V>>() {
        Iterator<BLMEntry<K, V>> itr = entries.iterator();

        /** Remove is not supported */
        public void remove() {
          throw new UnsupportedOperationException();
        }

        /** Return true if the iterator has another element */
        public boolean hasNext() {
          return itr.hasNext();
        }

        /** Return the next element from the iterator */
        public Map.Entry<K, V> next() {
          return itr.next();
        }
      };
    }

    /** Return true if this set contians an entry */
    public boolean contains(Object o) {
      return entries.contains(o);
    }

    public int size() {
      return entries.size();
    }
  }
}
