import java.util.ArrayList;
import java.util.Comparator;

/**
 * An implementation of a map using a sorted table. All accessors run
 * in O(log n) worst-case time, other than subMap, which runs in O(s + log n)
 * where s is the size of the resulting submap, and the complete iterations
 * that run in O(n) time.
 * TODO: Finish implementation
 */
public class SortedTableMap<K,V> extends AbstractSortedMap<K,V> {

  private ArrayList<MapEntry<K,V>> table = new ArrayList<>( );

  /** Constructs an empty map using the natural ordering of keys. */
  public SortedTableMap() { super(); }

  /**
   * Constructs an empty map using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the map
   */
  public SortedTableMap(Comparator<K> comp) { super(comp); }

  /**
   * Returns the number of entries in the map.
   * @return number of entries in the map
   */
  @Override
  public int size() { return table.size(); }

  /**
   * Returns the value associated with the specified key, or null if no such entry exists.
   * @param key  the key whose associated value is to be returned
   * @return the associated value, or null if no such entry exists
   */
  @Override
  public V get(K key) throws IllegalArgumentException {
    int j = findIndex(key);
    if(j == size() || compare(key, table.get(j)) != 0)  //no match
      return null;
    return table.get(j).getValue();
  }

  /**
   * Associates the given value with the given key. If an entry with
   * the key was already in the map, this replaced the previous value
   * with the new one and returns the old value. Otherwise, a new
   * entry is added and null is returned.
   * @param key    key with which the specified value is to be associated
   * @param value  value to be associated with the specified key
   * @return the previous value associated with the key (or null, if no such entry)
   */
  @Override
  public V put(K key, V value) throws IllegalArgumentException {
    int j = findIndex(key);
    if (j < size() && compare(key, table.get(j)) == 0) //match exists
      return table.get(j).setValue(value);
    table.add(j, new MapEntry<K,V>(key, value)); //otherwise it is new
    return null;  
  }

  /**
   * Removes the entry with the specified key, if present, and returns
   * its associated value. Otherwise does nothing and returns null.
   * @param key  the key whose entry is to be removed from the map
   * @return the previous value associated with the removed key, or null if no such entry exists
   */
  @Override
  public V remove(K key) throws IllegalArgumentException {
    int j = findIndex(key);
    if(j == size() || compare(key, table.get(j)) != 0)  //no match
      return null;
    return table.remove(j).getValue();  
  }

  /**
   * Returns an iterable collection of all key-value entries of the map.
   *
   * @return iterable collection of the map's entries
   */
  @Override
  public Iterable<Entry<K,V>> entrySet() {
    ArrayList<Entry<K,V>> buffer = new ArrayList<>();
    return buffer;
  }

  /** Returns the smallest index for range table[low..high] inclusive storing an entry with 
  a key greater than or equal to k (or else index high+1, by convention). */

  private int findIndex(K key, int low, int high) {
    if(high < low) return high + 1;  //no entry qualifies
    int mid = (low + high) / 2;
    int comp = compare(key, table.get(mid));
    if (comp == 0)
      return mid; //found exact match
    else if (comp < 0)
      return findIndex(key, low, mid - 1);  //answer is left of mid
    else
      return findIndex(key, mid + 1, high);  //answer is right of mid
  }

  /** Version of findIndex that searches the entire table */
  private int findIndex(K key) { return findIndex(key, 0, table.size() - 1); }
}
