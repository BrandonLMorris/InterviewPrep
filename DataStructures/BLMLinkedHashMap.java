package DataStructures;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * A basic implementation on a Hash Map that uses Linked Lists to resolved
 * collisions. The functionality is meant to mimic that of LinkedHashMap
 * in Java's standard library.
 *
 * Created by Brandon Morris on 1/27/16.
 */
public class BLMLinkedHashMap<K, V> implements Map<K, V> {

  /**
   * Removes all of the mappings from this map. The map will be empty after this
   * this method is complete.
   */
  public void clear() {
    // TODO
  }

  /**
   * Returns true if this map contains a mapping for the specified key.
   *
   * @param key  value whose presence is tested
   * @return  true if the map contains a mapping for the specified key
   */
  public boolean containsKey(Object key) {
    // TODO
    return false;
  }

  /**
   * Returns true if this map maps one (or more) keys to the specified value.
   *
   * @param value the value whose presence in this map is to be tested
   * @return  true if this map maps one or more keys to the value
   */
  public boolean containsValue(Object value) {
    // TODO
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
    // TODO
    return null;
  }

  /**
   * Compares the specified object with this map for equality. Returns true
   * is the given object is also a map and the two maps represent the same
   * mappings.
   *
   * @param o the object to be compared to this map
   * @return  true if the specified object is equal to this map
   */
  public boolean equals(Object o) {
    // TODO
    return false;
  }

  /**
   * Returns the hash code for this map. The hash code of a map is defined to be
   * the sum of each entry in the map's entrySet() view. This ensures that
   * m1.equals(m2) implies that m1.hashCode() == m2.hashCode() for any two maps
   * m1 and m2.
   *
   * @return  the hash code value for this map
   */
  public int hashCode() {
    // TODO
    return Integer.MAX_VALUE;
  }

  /**
   * Returns the value to which the specified key is mapped, or null if this
   * map contains no mapping for the key.
   *
   * @param key the key whose associated value is to be returned
   * @return  the value to which the specified key is mapped, or null if no
   *          mapping for the key
   */
  public V get(Object key) {
    // TODO
    return null;
  }

  /** Returns true if this Map contains no mappings */
  public boolean isEmpty() {
    // TODO
    return false;
  }

  /**
   * Returns a Set vew of the keys contained in this map. The set is backed by
   * the map, so any changes to the map are reflected in the set, and vice-
   * versa.
   *
   * @return  a set view of the keys contained in this map.
   */
  public Set<K> keySet() {
    // TODO
    return null;
  }

  /**
   *
   * @param key
   * @param value
   * @return
   */
  public V put(K key, V value) {
    // TODO
    return null;
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
    // TODO
  }

  /**
   * Removes the mapping for a key from this map if it is present.
   *
   * @param key the key whose mapping is to be removed from the map
   * @return  the value to which this map previously associated the key
   */
  public V remove(Object key) {
    // TODO
    return null;
  }

  /** Return the number of mappings in this map */
  public int size() {
    // TODO
    return 0;
  }

  /**
   * Returns a Collection view of the values contained in this map. The
   * collection is backed by the map, so changed to the map are reflected in the
   * collection, and vice-versa.
   *
   * @return  a colletion view of the values contained in this map
   */
  public Collection<V> values() {
    // TODO
    return null;
  }
}
