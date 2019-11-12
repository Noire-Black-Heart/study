package soft3410;

import java.util.concurrent.locks.*;

/**
 * A basic linked list implementation of a set.
 */
public class ReadWriteLockLinkedListIntSet
    extends contention.abstractions.AbstractCompositionalIntSet {
  final private Node head;
  final private Node tail;
  private ReadWriteLock lock = new ReentrantReadWriteLock();

  public ReadWriteLockLinkedListIntSet() {
    head = new Node(Integer.MIN_VALUE);
    tail = new Node(Integer.MAX_VALUE);
    head.setNext(tail);
  }

  /**
   * Add a new int to the set.
   * 
   * @param item  The new int to be added
   * @return false if the int already exists in the set
   */
  public boolean addInt(int item) {
    lock.writeLock().lock();

    Node pred = head;
    Node curr = head.getNext();
    while (curr.getValue() < item) {
      pred = curr;
      curr = pred.getNext();
    }
    boolean result = false;
    if (curr.getValue() != item) {
      Node node = new Node(item);
      node.setNext(curr);
      pred.setNext(node);
      result = true;
    }
    lock.writeLock().unlock();
    return result;
  }

  /**
   * Remove an int from the set.
   * 
   * @param item  The int to be removed
   * @return false if the int did not exist in the set
   */
  public boolean removeInt(int item) {
    lock.writeLock().lock();

    Node pred = head;
    Node curr = head.getNext();
    while (curr.getValue() < item) {
      pred = curr;
      curr = pred.getNext();
    }
    boolean result = false;
    if (curr.getValue() == item) {
      pred.setNext(curr.getNext());
      result = true;
    }
    lock.writeLock().unlock();
    return result;
  }

  /**
   * Check if an int is a member of the set.
   * 
   * @param item  The int to be checked
   * @return true if item exists in the set
   */
  public boolean containsInt(int item) {
    lock.readLock().lock();

    Node pred = head;
    Node curr = head.getNext();
    while (curr.getValue() < item) {
      pred = curr;
      curr = pred.getNext();
    }
    lock.readLock().unlock();
    return (curr.getValue() == item);
  }

  public int size() {
    lock.readLock().lock();

    int size = 0;
    Node pred;
    Node curr = head.getNext();
    while (curr.getNext() != null) {
      pred = curr;
      curr = pred.getNext();
      size++;
    }
    lock.readLock().unlock();
    return size;
  }

  /**
   * Empty the set.
   */
  public void clear() {
    lock.writeLock().lock();
    head.setNext(tail);
    lock.writeLock().unlock();
  }

  class Node {

    final private int value;
    private Node next;

    public Node(int value, Node next) {
      this.value = value;
      this.next = next;
    }

    public Node(int value) {
      this(value, null);
    }

    public int getValue() {
      return value;
    }

    public void setNext(Node next) {
      this.next = next;
    }

    public Node getNext() {
      return next;
    }
  }

}
