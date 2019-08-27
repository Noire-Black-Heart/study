import static org.junit.Assert.*;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import textbook.LinkedBinaryTree;
import java.util.*;

public class TestAssignment {
	
	// Set up JUnit to be able to check for expected exceptions
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	public LinkedBinaryTree<String> buildSimpleTree(){
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
		tree.addRoot("+");
		tree.addLeft(tree.root(), "-");
		tree.addRight(tree.root(), "c");
		tree.addLeft(tree.left(tree.root()), "2");
		tree.addRight(tree.left(tree.root()), "2");
		return tree;
	}

	// Some simple testing of prefix2tree
	@Test(timeout = 100)
	public void testPrefix2tree() {
		
		LinkedBinaryTree<String> tree;

		tree = Assignment.prefix2tree("hi");
		assertEquals(1, tree.size());
		assertEquals("hi", tree.root().getElement());

		tree = Assignment.prefix2tree("+ 5 10");
		assertEquals(3, tree.size());
		assertEquals("+", tree.root().getElement());
		assertEquals("5", tree.left(tree.root()).getElement());
		assertEquals("10", tree.right(tree.root()).getElement());
		
		tree = Assignment.prefix2tree("- 5 10");
		assertEquals(3, tree.size());
		assertEquals("-", tree.root().getElement());
		assertEquals("5", tree.left(tree.root()).getElement());
		assertEquals("10", tree.right(tree.root()).getElement());
		
		tree = Assignment.prefix2tree("* 5 10");
		assertEquals(3, tree.size());
		assertEquals("*", tree.root().getElement());
		assertEquals("5", tree.left(tree.root()).getElement());
		assertEquals("10", tree.right(tree.root()).getElement());
				
		tree = Assignment.prefix2tree("+ 5 - 4 3");
		assertEquals(5, tree.size());
		assertEquals("+", tree.root().getElement());
		assertEquals("5", tree.left(tree.root()).getElement());
		assertEquals("-", tree.right(tree.root()).getElement());
		assertEquals("4", tree.left(tree.right(tree.root())).getElement());
		assertEquals("3", tree.right(tree.right(tree.root())).getElement());
		
		thrown.expect(IllegalArgumentException.class);
		tree = Assignment.prefix2tree("+ 5 - 4");
	}
	
	// example of using the Assignment.equals method to check that "- x + 1 2" simplifies to "- x 3"
	@Test(timeout = 100)
	public void testSimplify1() {
		LinkedBinaryTree<String> tree = Assignment.prefix2tree("- x + 1 2");
		tree = Assignment.simplify(tree);
		LinkedBinaryTree<String> expected = Assignment.prefix2tree("- x 3");
		assertTrue(Assignment.equals(tree, expected));
	}
	
		//test tree2prefix with null tree
				@Test(timeout = 100)
				public void testTree2PrefixIllegal1() {
					thrown.expect(IllegalArgumentException.class);
					String prefix = Assignment.tree2prefix(null);
				}
		//test tree2prefix with illegal tree---tree has only symbol
				@Test(timeout = 100)
				public void testTree2PrefixIllegal2() {
					LinkedBinaryTree<String> tree = Assignment.prefix2tree("2");
					tree.addLeft(tree.root(), "+");
					thrown.expect(IllegalArgumentException.class);
					String prefix2 = Assignment.tree2prefix(tree);
				}
				//test tree2prefix with illegal tree---tree has only number
				@Test(timeout = 100)
				public void testTree2PrefixIllegal3() {
					LinkedBinaryTree<String> tree = Assignment.prefix2tree("2");
					tree.addLeft(tree.root(), "2");
					tree.addLeft(tree.left(tree.root()), "2");
					thrown.expect(IllegalArgumentException.class);
					String prefix3 = Assignment.tree2prefix(tree);
				}
				
		//test tree2prefix with examples
				@Test(timeout = 100)
				public void testTree2PrefixExample() {
					LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
					tree.addRoot("+");
					tree.addLeft(tree.root(), "1");
					tree.addRight(tree.root(), "-");
					tree.addLeft(tree.right(tree.root()), "2");
					tree.addRight(tree.right(tree.root()), "3");
					String prefix = Assignment.tree2prefix(tree);
					assertEquals("+ 1 - 2 3", prefix);
				}
				
		//test tree2infix with null tree
				@Test(timeout = 100)
				public void testTree2InfixIllegal1() {
					thrown.expect(IllegalArgumentException.class);
					String prefix = Assignment.tree2infix(null);
				}
		//test tree2infix with illegal tree
				@Test(timeout = 100)
				public void testTree2InfixIllegal2() {
					LinkedBinaryTree<String> tree = Assignment.prefix2tree("2");
					tree.addLeft(tree.root(), "+");
					thrown.expect(IllegalArgumentException.class);
					String prefix2 = Assignment.tree2infix(tree);
				}
		//test tree2infix with examples
				@Test(timeout = 100)
				public void testTree2infixExample() {
					LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
					tree.addRoot("+");
					tree.addLeft(tree.root(), "1");
					tree.addRight(tree.root(), "-");
					tree.addLeft(tree.right(tree.root()), "2");
					tree.addRight(tree.right(tree.root()), "3");
					String infix = Assignment.tree2infix(tree);
					assertEquals("(1+(2-3))", infix);
				}
				
		//test simplify with example
				@Test(timeout = 100)
				public void testSimplifyExample() {
					LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
					tree = buildSimpleTree();
					tree = Assignment.simplify(tree);
					LinkedBinaryTree<String> expected = Assignment.prefix2tree("+ 0 c");
					assertTrue(Assignment.equals(tree, expected));
				}
				
		//test simplifyfancy with example
				@Test(timeout = 100)
				public void testSimplifyFancyExample() {
					LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
					tree = buildSimpleTree();
					tree = Assignment.simplifyFancy(tree);
					LinkedBinaryTree<String> expected = Assignment.prefix2tree("c");
					//System.out.println(tree.root().getElement());
					assertTrue(Assignment.equals(tree, expected));
				}

		//test simplifyfancy with a bigger example tree
				@Test(timeout = 100)
				public void testSimplifyFancyBigTree() {
					LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
					tree.addRoot("*");
					tree.addLeft(tree.root(), "1");
					tree.addRight(tree.root(), "+");
					tree.addLeft(tree.right(tree.root()), "-");
					tree.addLeft(tree.left(tree.right(tree.root())), "4");
					tree.addRight(tree.left(tree.right(tree.root())), "c");
					tree.addRight(tree.right(tree.root()), "0");
					tree = Assignment.simplifyFancy(tree);
					LinkedBinaryTree<String> expected = Assignment.prefix2tree("- 4 c");
					assertTrue(Assignment.equals(tree, expected));
				}
		//test simplify fancy with multiple variable
				@Test(timeout = 100)
				public void testFancyMultiVariable() {
					LinkedBinaryTree<String> tree = Assignment.prefix2tree("+ y * x 0");
					tree = Assignment.simplifyFancy(tree);
					LinkedBinaryTree<String> expected = Assignment.prefix2tree("y");
					assertTrue(Assignment.equals(tree, expected));
				}
		//test substitude null variable
				@Test(timeout = 100)
				public void testSubstitudeNull() {
					LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
					tree = buildSimpleTree();
					thrown.expect(IllegalArgumentException.class);
					tree = Assignment.substitute(tree, null, 2);
				}
				
		//test substitude
				@Test(timeout = 100)
				public void testSubstitude() {
					LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
					tree = buildSimpleTree();
					tree = Assignment.substitute(tree, "c", 0);
					LinkedBinaryTree<String> expected = Assignment.prefix2tree("+ - 2 2 0");
					//System.out.println(tree.root().getElement());
					assertTrue(Assignment.equals(tree, expected));
				}
		//test substitude map
				@Test(timeout = 100)
				public void testSubstitudeMap() {
					LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
					tree.addRoot("+");
					tree.addLeft(tree.root(), "c");
					tree.addRight(tree.root(), "-");
					tree.addLeft(tree.right(tree.root()), "a");
					tree.addRight(tree.right(tree.root()), "b");
					HashMap<String, Integer> map = new HashMap<String, Integer>();
					map.put("a", 1);
					map.put("b", 5);
					map.put("c", 3);
					tree = Assignment.substitute(tree, map);
					LinkedBinaryTree<String> expected = new LinkedBinaryTree<String>();
					expected.addRoot("+");
					expected.addLeft(expected.root(), "3");
					expected.addRight(expected.root(), "-");
					expected.addLeft(expected.right(expected.root()), "1");
					expected.addRight(expected.right(expected.root()), "5");
					//System.out.println(tree.root().getElement());
					assertTrue(Assignment.equals(tree, expected));
				}
		//test substitude null map
				@Test(timeout = 100)
				public void testSubstitudeNullMap() {
					LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
					tree = buildSimpleTree();
					HashMap<String, Integer> map = null;
					thrown.expect(IllegalArgumentException.class);
					tree = Assignment.substitute(tree, map);
					}
				
		
		//test substitude null element
				@Test(timeout = 100)
				public void testSubstitudeNullElement() {
					LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
					tree = buildSimpleTree();
					HashMap<String, Integer> map = new HashMap<String, Integer>();
					map.put(null, 1);
					thrown.expect(IllegalArgumentException.class);
					tree = Assignment.substitute(tree, map);
					}
		//test isArithmetricExpression with null tree
				@Test(timeout = 100)
				public void testIsArithmetricNull() {
					LinkedBinaryTree<String> tree = null;
					//thrown.expect(IllegalArgumentException.class);
					assertFalse(Assignment.isArithmeticExpression(tree));
					}
}
