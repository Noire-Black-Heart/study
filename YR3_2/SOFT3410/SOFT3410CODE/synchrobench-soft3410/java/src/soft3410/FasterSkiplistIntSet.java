package soft3410;

import java.util.Random;

import contention.abstractions.AbstractCompositionalIntSet;

public class FasterSkiplistIntSet extends AbstractCompositionalIntSet {

//-----------------------------------------------------------------------------------------------	
	//node class
	class Node{
		
		final private int value;
	    public Node up, down, left, right;

	    Node(int value) {
	      this.value = value;
	    }

	    int getValue() {
	      return value;
	    }

	}
//---------------------------------------------------------------------------------------------
	private Node head, tail;
	private int size;
	public int level;
	private Random random = new Random();
	private static final double probability=0.5;
	
	
	public FasterSkiplistIntSet() {
		this.head = new Node(Integer.MIN_VALUE);
		this.tail = new Node(Integer.MAX_VALUE);
		this.size = 0;
		this.level = 0;
		horizontalLink(head, tail);
	}
	
	private void horizontalLink(Node node1, Node node2) {//1 is left
		node1.right = node2;
		node2.left = node1;
	}
	
	private void verticalLink(Node node1, Node node2) {//1 is higher
		node1.down = node2;
		node2.up = node1;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.size = 0;
		this.level = 0;
		horizontalLink(head, tail);
	}
	

    private Node findNode(int value){
        Node p = head;
        while(true){
        	//traverse the list from most top level
        	
            while (p.right.getValue() != Integer.MAX_VALUE && p.right.getValue() <= value) {
                p = p.right;
             
            }
            //if smaller than right node, try go down one level
            if (p.down != null) {
                p = p.down;
                //then repeat the loop on going right
            }else {
            	//at bottom list, target found
                break;
            }

        }
        return p;
    }
	
	
	@Override
	public boolean addInt(int x) {
		
		Node node = findNode(x);
		
		//if already have this node, return false
		if(x == node.getValue()) {
			return false;
		}
		
		//create the new node and insert it at most bottom level
		Node newNode = new Node(x);
		insertAfter(node, newNode);
		
		//promotion to upper level action
		int currentlevel = 0;
		
		while(random.nextDouble() <= probability) {//when we should promote the new node
			
			if(currentlevel >= level) {//if target level is beyond current level, create new level
				level++;
				
				Node newTail = new Node(Integer.MAX_VALUE);
				Node newHead = new Node(Integer.MIN_VALUE);
				
				horizontalLink(newHead, newTail);
				verticalLink(newHead, head);
				verticalLink(newTail, tail);
				
				head = newHead;
				tail = newTail;
				
			}
			
			 /**
		     * find the node that serves as the left node for the newly promoted node in the upper level. 
		     *           3                                                                             3---5
		     * eg.   1-2-3-4-6          , target is 5, then we should find 3 and go up, to produce 1-2-3-4-5-6
		     * */
			
			while(node.up == null) {
				node = node.left;
			}
			node = node.up;
			
			//copy the new node to the upper level
			Node upNode = new Node(x);
			insertAfter(node, upNode);
			verticalLink(upNode, newNode);
			
			newNode = upNode;
			currentlevel++;
			
		}
		
		size++;
		
		return true;
	}

	private void insertAfter(Node node, Node newNode) {//insert new node after node
		// TODO Auto-generated method stub
		newNode.left = node;
		newNode.right = node.right;
		node.right.left = newNode;
		node.right = newNode;
	}

	@Override
	public boolean removeInt(int x) {
		// TODO Auto-generated method stub
		Node node = findNode(x);
		
		//if dont have the node, return false;
		if(node.getValue() != x) {
			return false;
		}
		
		//begin from the most bottom node, delete the node from down to up, one by one level
		while(node != null) {
			
			horizontalLink(node.left, node.right);
			
			node = node.up;
		}
		
		
		//if last element of top level is deleted, remove top level
		while(head.right == tail && head.down != null) {
			head = head.down;
			tail = tail.down;
			head.up = null;
			tail.up = null;
			level --;
		}
		
		
		size--;
		return true;
	}

	@Override
	public boolean containsInt(int x) {
		// TODO Auto-generated method stub
		Node p = findNode(x);
		if(x == p.getValue()) {
			return true;
		}
		
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	

}
