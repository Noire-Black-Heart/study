package soft3410;

/**
 * A basic linked list implementation of a set.
 */
public class OptimisticLinkedListIntSet
    extends contention.abstractions.AbstractCompositionalIntSet {
  final private Node head;
  final private Node tail;

  public OptimisticLinkedListIntSet() {
    head = new Node(Integer.MIN_VALUE);
    tail = new Node(Integer.MAX_VALUE);
    head.setNext(tail);
  }

  /**
   * Add a new int to the set.
   *
   * @param value The new int to be added
   * @return false if the int already exists in the set
   */
  public boolean addInt(int value) {
    while (true) {
      Node predecessor = findPredecessor(value);
      Node current = predecessor.getNext();
      if (current.value == value) {
        return false;
      }
      synchronized (predecessor) {
        synchronized (current) {
          if (validateSuccession(predecessor, current)) {
            Node node = new Node(value, current);
            predecessor.setNext(node);
            return true;
          }
        }
      }
    }
  }

  /**
   * Remove an int from the set.
   *
   * @param value The int to be removed
   * @return false if the int did not exist in the set
   */
  public boolean removeInt(int value) {
    while (true) {
      Node predecessor = findPredecessor(value);
      Node current = predecessor.getNext();
      synchronized (predecessor) {
        synchronized (current) {
          // Check the nodes we found are still the right nodes.
          if (validateSuccession(predecessor, current)) {
            if (current.value == value) {
              predecessor.setNext(current.getNext());
              return true;
            }
            return false;
          }
        }
      }
    }
  }

  /**
   * Check if an int is a member of the set.
   *
   * @param value The int to be checked
   * @return true if value exists in the set
   */
  public boolean containsInt(int value) {
    while (true) {
      Node predecessor = findPredecessor(value);
      Node current = predecessor.getNext();
      if (current.value == value) {
        return true;
      }
      if (validateSuccession(predecessor, current)) {
        return current.value == value;
      }
    }
  }

  private boolean validateSuccession(Node predecessor, Node succ) {
    synchronized (predecessor) {
      synchronized (succ) {
        Node current = findPredecessor(succ.value);
        if (current == predecessor) {
          return predecessor.getNext() == succ;
        }

        return false;
      }
    }
  }

  public Node findPredecessor(int value) {
    Node predecessor = head;
    while (predecessor.getNext().value < value) {
      predecessor = predecessor.getNext();
    }
    return predecessor;
  }

  public int size() {
    int size = 0;
    Node current = head.getNext();
    while (current != tail) {
      current = current.getNext();
      size++;
    }
    return size;
  }

  /**
   * Empty the set.
   */
  public void clear() {
    synchronized (head) {
      synchronized (tail) {
        head.setNext(tail);
      }
    }
  }

  class Node {

    final private int value;
    private Node next;

    Node(int value, Node next) {
      this.value = value;
      this.next = next;
    }

    Node(int value) {
      this(value, null);
    }

    void setNext(Node next) {
      this.next = next;
    }

    Node getNext() {
      return next;
    }
  }

}

