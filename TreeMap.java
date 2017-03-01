//Names: Paul Torre, Are Oelsner
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a sorted map using a binary search tree.
 * TODO: Finish implementation
 */
public class TreeMap<K,V> extends AbstractSortedMap<K,V> {

  private class BinaryTree<K,V> {
    private class Node<K,V> {
      //Private Variables for Node class
      private Node parent;
      private Node left;
      private Node right;
      private K k;
      private V v;

      //Constructors for Node class
      public Node<K,V>() {
        parent = null;
        left = null;
        right = null;
        k = null;
        v = null;
      }

      //Node constructor from another node
      public Node<K,V>(Node node) {
        this.parent = node.parent;
        this.left = node.left;
        this.right = node.right;
        this.k = node.k;
        this.v = node.v;
      }

      //Basic Node constructor
      public Node<K,V>(K _k, V _v) {
        parent = null;
        k = _k;
        v = _v;
        left = null;
        right = null;
      }

      //Complex Node constructor
      public Node<K,V>(Node _parent, Node _left, Node _right, K _k, V _v) {
        parent = _parent;
        left = _left;
        right = _right;
        k = _k;
        v = _v;
      }

      //Accessor Methods
      public Node getParent() {return parent;}
      public Node getLeft() {return left;}
      public Node getRight() {return right;}

      public K getK() {return k;}
      public V getV() {return v;}

      //Setter Methods
      public void setParent(Node _parent) {parent = _parent;}
      public void setLeft(Node _left) {left = _left;}
      public void setRight(Node _right) {right = _right;}

      public void setK(K _k) {k = _k;}
      public void setV(V _v) {v = _v;}
    }


    //BinaryTree class
    //Private Variables
    private Node<K,V> root;
    private int size;

    //Constructors for BinaryTree class
    public BinaryTree/*generics*/() {
      root = null;
    }

    //BinaryTree class methods
    //Insert function: Inserts a key value pair into the binary search tree
    public void insert(K k, V v) {//I wasn't sure how we would pass key-value pairs
      Node<> ins = new Node<>(k,v); // As entries or as k and v separately
      insertHelper(ins, root);
    }

    //Recursive helper function for Node insertion
    private void insertHelper(Node ins, Node node) {
      if(node == null) {
        node = ins;
        size++;
      }
      else if(k > root.getK()) {
        insertHelper(ins, node.getRight());
      }
      else{
        insertHelper(ins, node.getLeft());
      }
    }

    //Find function: finds a node in the tree with value n
    public Node<K,V> find(K k) {
      return findHelper(k, root);

    //Recursive helper function for finding Nodes
    private Node<> findHelper(K k, Node node) {//iffy generics
      if(node.getK() == k) {//Need to use comparator?
        return node;
      }
      else if(k > root.getK()) {
        findHelper(k, node.getRight());
      }
      else{
        findHelper(k, node.getLeft());
      }
    }

    //Delete Function
    public Node<> delete(K k) {
      Node node = find(k);
      Node left = node.getLeft();
      Node right = node.getRight();
      Node ans = new Node(node);
      left.setParent(node.getParent());
      if(node.isLeft()) {
        node.getParent().setLeft(node.getLeft());
      }
      else{
        node.getParent().setRight(node.getRight());
      }
      if(left.getRight() == null) {
        left.setRight(right);
      }
      else{
        Node temp = left.getRight();
        while(temp.getRight() != null) {
          temp = temp.getRight();
        }
        temp.setRight(right);
      }
      size--;
    }



  }
/*Do we create a Binary Tree as a private variable?
 *
 *protected BalanceableBinaryTree<K,V> tree = new BalanceableBinaryTree<>();
 *
 *^^Textbook implementation on pg. 467
 */

  /** Constructs an empty map using the natural ordering of keys. */
  public TreeMap() {
    super();                  // the AbstractSortedMap constructor
  }

  /**
   * Constructs an empty map using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the map
   */
  public TreeMap(Comparator<K> comp) {
    super(comp);              // the AbstractSortedMap constructor
    //tree.addRoot(null); //Relies on having BinaryTree<K,V> tree
  }

  /**
   * Returns the number of entries in the map.
   * @return number of entries in the map
   */
  @Override
  public int size() {
    //return (tree.size() - 1) / 2; //Textbook
    return -1;
  }

  /**
   * Returns the value associated with the specified key,
   * or null if no such entry exists.
   * @param key  the key whose associated value is to be returned
   * @return the associated value, or null if no such entry exists
   */
  @Override
  public V get(K key) throws IllegalArgumentException {
    /*Textbook Implementation
     * checkKey(key);
     * Position<Entry<K,V>> p = treeSearch(root(), key);
     * rebalanceAccess(p);
     * if (isExternal(p)) return null;
     * return p.getElement().getValue();
     */
    return null;
  }

  /**
   * Associates the given value with the given key. If an entry with
   * the key was already in the map, this replaced the previous value
   * with the new one and returns the old value. Otherwise, a new
   * entry is added and null is returned.
   * @param key    key with which the specified value is to be associated
   * @param value  value to be associated with the specified key
   * @return the previous value associated with the key (or null,
   *         if no such entry)
   */
  @Override
  public V put(K key, V value) throws IllegalArgumentException {
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
    return null;
  }

  // Support for iteration
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
}
