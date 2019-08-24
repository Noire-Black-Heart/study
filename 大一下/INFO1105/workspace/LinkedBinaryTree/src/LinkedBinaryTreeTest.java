import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedBinaryTreeTest<E> {

	@Test
	public void testmirror() {
		LinkedBinaryTree tree = new LinkedBinaryTree();
		tree.addRoot("root");
		tree.addLeft(tree.root(), "cyka");
		tree.addRight(tree.root(), "blyat");
		tree.reverseMe();
		assertEquals("cyka", tree.right(tree.root()).getElement());
		
		
	}

}
