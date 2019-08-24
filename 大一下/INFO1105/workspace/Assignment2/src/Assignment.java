import java.util.HashMap;
import java.util.*;

import textbook.LinkedBinaryTree;
import textbook.LinkedQueue;
import textbook.Position;

public class Assignment {

	/**
	 * Convert an arithmetic expression (in prefix notation), to a binary tree
	 * 
	 * Binary operators are +, -, * (i.e. addition, subtraction, multiplication)
	 * Anything else is assumed to be a variable or numeric value
	 * 
	 * Example: "+ 2 15" will be a tree with root "+", left child "2" and right
	 * child "15" i.e. + 2 15
	 * 
	 * Example: "+ 2 - 4 5" will be a tree with root "+", left child "2", right
	 * child a subtree representing "- 4 5" i.e. + 2 - 4 5
	 * 
	 * This method runs in O(n) time
	 * 
	 * @param expression
	 *            - an arithmetic expression in prefix notation
	 * @return BinaryTree representing an expression expressed in prefix
	 *         notation
	 * @throws IllegalArgumentException
	 *             if expression was not a valid expression
	 */
	public static LinkedBinaryTree<String> prefix2tree(String expression) throws IllegalArgumentException {
		if (expression == null) {
			throw new IllegalArgumentException("Expression string was null");
		}
		// break up the expression string using spaces, into a queue
		LinkedQueue<String> tokens = new LinkedQueue<String>();
		for (String token : expression.split(" ")) {
			tokens.enqueue(token);
		}
		// recursively build the tree
		return prefix2treerec(tokens);
	}
	
	/**
	 * Recursive helper method to build an tree representing an arithmetic
	 * expression in prefix notation, where the expression has already been
	 * broken up into a queue of tokens
	 * 
	 * @param tokens
	 * @return
	 * @throws IllegalArgumentException
	 *             if expression was not a valid expression
	 */
	private static LinkedBinaryTree<String> prefix2treerec(LinkedQueue<String> tokens) throws IllegalArgumentException {
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();

		// use the next element of the queue to build the root
		if (tokens.isEmpty()) {
			throw new IllegalArgumentException("String was not a valid arithmetic expression in prefix notation");
		}
		String element = tokens.dequeue();
		tree.addRoot(element);

		// if the element is a binary operation, we need to build the left and
		// right subtrees
		if (element.equals("+") || element.equals("-") || element.equals("*")) {
			LinkedBinaryTree<String> left = prefix2treerec(tokens);
			LinkedBinaryTree<String> right = prefix2treerec(tokens);
			tree.attach(tree.root(), left, right);
		}
		// otherwise, assume it's a variable or a value, so it's a leaf (i.e.
		// nothing more to do)

		return tree;
	}
	
	/**
	 * Test to see if two trees are identical (every position in the tree stores the same value)
	 * 
	 * e.g. two trees representing "+ 1 2" are identical to each other, but not to a tree representing "+ 2 1"
	 * @param a
	 * @param b
	 * @return true if the trees have the same structure and values, false otherwise
	 */
	public static boolean equals(LinkedBinaryTree<String> a, LinkedBinaryTree<String> b) {
		return equalsrec(a, b, a.root(), b.root());
	}

	/**
	 * Recursive helper method to compare two trees
	 * @param aTree one of the trees to compare
	 * @param bTree the other tree to compare
	 * @param aRoot a position in the first tree
	 * @param bRoot a position in the second tree (corresponding to a position in the first)
	 * @return true if the subtrees rooted at the given positions are identical
	 */
	private static boolean equalsrec(LinkedBinaryTree<String> aTree, LinkedBinaryTree<String> bTree, Position<String> aRoot, Position<String> bRoot) {
		//if either of the positions is null, then they are the same only if they are both null
		if(aRoot == null || bRoot == null) {
			return (aRoot == null) && (bRoot == null);
		}
		//first check that the elements stored in the current positions are the same
		String a = aRoot.getElement();
		String b = bRoot.getElement();
		if((a==null && b==null) || a.equals(b)) {
			//then recursively check if the left subtrees are the same...
			boolean left = equalsrec(aTree, bTree, aTree.left(aRoot), bTree.left(bRoot));
			//...and if the right subtrees are the same
			boolean right = equalsrec(aTree, bTree, aTree.right(aRoot), bTree.right(bRoot));
			//return true if they both matched
			return left && right;
		}
		else {
			return false;
		}
	}

	
	/**
	 * Given a tree, this method should output a string for the corresponding
	 * arithmetic expression in prefix notation, without (parenthesis) (also
	 * known as Polish notation)
	 * 
	 * Example: A tree with root "+", left child "2" and right child "15" would
	 * be "+ 2 15" Example: A tree with root "-", left child a subtree
	 * representing "(2+15)" and right child "4" would be "- + 2 15 4"
	 * 
	 * Ideally, this method should run in O(n) time
	 * 
	 * @param tree
	 *            - a tree representing an arithmetic expression
	 * @return prefix notation expression of the tree
	 * @throws IllegalArgumentException
	 *             if tree was not a valid expression
	 */
	public static String tree2prefix(LinkedBinaryTree<String> tree) throws IllegalArgumentException {
		// TODO: Implement this method
	 String prefix = "";
		if(tree == null){
			throw new IllegalArgumentException();
		}
		prefix = tree2prefixrec(tree, tree.root(), prefix);
		prefix = prefix.trim();
		return prefix;
	}
	
	
	private static String tree2prefixrec (LinkedBinaryTree<String> tree, Position<String> current, String prefix){
		//the way to check if its illegal
		if(current.getElement().equals("+") || current.getElement().equals("-") || current.getElement().equals("*")){
				if(tree.numChildren(current) < 2){
					throw new IllegalArgumentException();
				}
		}
		else {
			if(!current.equals(tree.root())){
			if(!tree.parent(current).getElement().equals("+")&&!tree.parent(current).getElement().equals("-")&&!tree.parent(current).getElement().equals("*")){
				throw new IllegalArgumentException();
			}
			}
		}
		//the main algorithm
		prefix = prefix + current.getElement() + " ";
		if(tree.left(current) != null){
			prefix =  tree2prefixrec(tree, tree.left(current), prefix);
		}
		if(tree.right(current) != null){
			prefix =  tree2prefixrec(tree, tree.right(current), prefix);
		}
		return prefix;
	}

	/**
	 * Given a tree, this method should output a string for the corresponding
	 * arithmetic expression in infix notation with parenthesis (i.e. the most
	 * commonly used notation).
	 * 
	 * Example: A tree with root "+", left child "2" and right child "15" would
	 * be "(2+15)"
	 * 
	 * Example: A tree with root "-", left child a subtree representing "(2+15)"
	 * and right child "4" would be "((2+15)-4)"
	 * 
	 * Optionally, you may leave out the outermost parenthesis, but it's fine to
	 * leave them on. (i.e. "2+15" and "(2+15)-4" would also be acceptable
	 * output for the examples above)
	 * 
	 * Ideally, this method should run in O(n) time
	 * 
	 * @param tree
	 *            - a tree representing an arithmetic expression
	 * @return infix notation expression of the tree
	 * @throws IllegalArgumentException
	 *             if tree was not a valid expression
	 */
	public static String tree2infix(LinkedBinaryTree<String> tree) throws IllegalArgumentException {
		// TODO: Implement this method
		 String infix = "";
			if(tree == null){
				throw new IllegalArgumentException();
			}
			if(!isArithmeticExpression(tree)){
				throw new IllegalArgumentException();
			}
			infix = tree2infixrec(tree, tree.root(), infix);
			infix = infix.trim();
			return infix;
	}

	private static String tree2infixrec(LinkedBinaryTree<String> tree, Position<String> current, String infix){
		//the main algorithm
				if(tree.left(current) != null){
					infix = infix + "(";
					infix =  tree2infixrec(tree, tree.left(current), infix);
				}
				
				infix = infix + current.getElement();
				
				if(tree.right(current) != null){
					infix =  tree2infixrec(tree, tree.right(current), infix);
					infix = infix+")";
				}
				return infix;
	}
	/**
	 * Given a tree, this method should simplify any subtrees which can be
	 * evaluated to a single integer value.
	 * 
	 * Ideally, this method should run in O(n) time
	 * 
	 * @param tree
	 *            - a tree representing an arithmetic expression
	 * @return resulting binary tree after evaluating as many of the subtrees as
	 *         possible
	 * @throws IllegalArgumentException
	 *             if tree was not a valid expression
	 */
	public static LinkedBinaryTree<String> simplify(LinkedBinaryTree<String> tree) throws IllegalArgumentException {
		// TODO: Implement this method
		if(!isArithmeticExpression(tree)){
			throw new IllegalArgumentException();
		}
		String oldPrefix = tree2prefix(tree);
		String newPrefix = simplifyHelper(oldPrefix);
		LinkedBinaryTree<String> newTree = prefix2tree(newPrefix);
		return newTree;
	}
	
	public static boolean isNumber(String s){
		try{
		int a =	Integer.parseInt(s);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	private static String simplifyHelper(String prefix){
		Stack<String> tokens = new Stack<String>();

		for (String token : prefix.split(" ")) {
			tokens.push(token);
		}
		
		
		Stack<String> stack = new Stack<String>();
		while(!tokens.isEmpty()){
			String s = tokens.peek();
			//calculation
			if(s.equals("+")){
				String operand1 = stack.pop();
				String operand2 = stack.pop();
				if(isNumber(operand1) && isNumber(operand2)){//the operands are number
					int sum = Integer.parseInt(operand1) + Integer.parseInt(operand2);
					String sumStr = Integer.toString(sum);
					stack.push(sumStr);
				}else{//the operands involve characters
					String combined = s + " " + operand1 + " " + operand2;
					stack.push(combined);
				}
				tokens.pop();
			}else if(s.equals("-")){
				String operand1 = stack.pop();
				String operand2 = stack.pop();
				if(isNumber(operand1) && isNumber(operand2)){//the operands are number
					int diff = Integer.parseInt(operand1) - Integer.parseInt(operand2);
					String diffStr = Integer.toString(diff);
					stack.push(diffStr);
				}else{//the operands involve characters
					String combined = s + " " + operand1 + " " + operand2;
					stack.push(combined);
				}
				tokens.pop();
			}else if(s.equals("*")){
				String operand1 = stack.pop();
				String operand2 = stack.pop();
				if(isNumber(operand1) && isNumber(operand2)){//the operands are number
					int pro = Integer.parseInt(operand1) * Integer.parseInt(operand2);
					String proStr = Integer.toString(pro);
					stack.push(proStr);
				}else{//the operands involve characters
					String combined = s + " " + operand1 + " " + operand2;
					stack.push(combined);
				}
				tokens.pop();
			}else{
				tokens.pop();
				stack.push(s);
			}
		}
		return stack.peek();
	}
	

	/**
	 * This should do everything the simplify method does AND also apply the following rules:
	 *  * 1 x == x  i.e.  (1*x)==x
	 *  * x 1 == x        (x*1)==x
	 *  * 0 x == 0        (0*x)==0
	 *  * x 0 == 0        (x*0)==0
	 *  + 0 x == x        (0+x)==x
	 *  + x 0 == 0        (x+0)==x
	 *  - x 0 == x        (x-0)==x
	 *  - x x == 0        (x-x)==0
	 *  
	 *  Example: - * 1 x x == 0, in infix notation: ((1*x)-x) = (x-x) = 0
	 *  
	 * Ideally, this method should run in O(n) time (harder to achieve than for other methods!)
	 * 
	 * @param tree
	 *            - a tree representing an arithmetic expression
	 * @return resulting binary tree after applying the simplifications
	 * @throws IllegalArgumentException
	 *             if tree was not a valid expression
	 */
	public static LinkedBinaryTree<String> simplifyFancy(LinkedBinaryTree<String> tree) throws IllegalArgumentException {
		// TODO: Implement this method
		if(!isArithmeticExpression(tree)){
			throw new IllegalArgumentException();
		}
		String oldPrefix = tree2prefix(tree);
		String newPrefix = simplifyFancyHelper(oldPrefix);
		LinkedBinaryTree<String> newTree = prefix2tree(newPrefix);
		return newTree;
	}
	
	private static String simplifyFancyHelper(String prefix){
		Stack<String> tokens = new Stack<String>();
		for (String token : prefix.split(" ")) {
			tokens.push(token);
		}
		
		Stack<String> stack = new Stack<String>();
		while(!tokens.isEmpty()){
			String s = tokens.peek();
			//calculation
			if(s.equals("+")){
				String operand1 = stack.pop();
				String operand2 = stack.pop();
				if(isNumber(operand1) && isNumber(operand2)){//the operands are number
					int sum = Integer.parseInt(operand1) + Integer.parseInt(operand2);
					String sumStr = Integer.toString(sum);
					stack.push(sumStr);
				}else{//the operands involve characters
					//check if the fancy rules can be applied to the whole branch of tree.
					String combined = "";
					if(operand1.equals("0")){//+ 0 ab = ab
						combined = operand2;
					}else if(operand2.equals("0")){//+ ab 0 = ab
						combined = operand1;
					}else{//no fancy rules, progress as normal.
						combined = s + " " + operand1 + " " + operand2;
					}
					
					stack.push(combined);
				}
				tokens.pop();
			}else if(s.equals("-")){
				String operand1 = stack.pop();
				String operand2 = stack.pop();
				if(isNumber(operand1) && isNumber(operand2)){//the operands are number
					int diff = Integer.parseInt(operand1) - Integer.parseInt(operand2);
					String diffStr = Integer.toString(diff);
					stack.push(diffStr);
				}else{//the operands involve characters
					//check if the fancy rules can be applied to the whole branch of tree.
					String combined = "";
					if(operand2.equals("0")){//- ab 0 = ab
						combined = operand1;
					}else if(operand1.equals(operand2)){//- ab ab = 0
						combined = "0";
					}else{//no fancy rules, progress as normal.
					combined = s + " " + operand1 + " " + operand2;
					}
					stack.push(combined);
				}
				tokens.pop();
			}else if(s.equals("*")){
				String operand1 = stack.pop();
				String operand2 = stack.pop();
				if(isNumber(operand1) && isNumber(operand2)){//the operands are number
					int pro = Integer.parseInt(operand1) * Integer.parseInt(operand2);
					String proStr = Integer.toString(pro);
					stack.push(proStr);
				}else{//the operands involve characters
					//check if the fancy rules can be applied to the whole branch of tree.
					String combined = "";
					if(operand1.equals("0")){//* 0 ab = 0;
						combined = "0";
					}else if(operand2.equals("0")){//* ab 0 = 0
						combined = "0";
					}else if(operand1.equals("1")){//* 1 ab = ab
						combined = operand2;
					}else if(operand2.equals("1")){//* ab 1 = ab
						combined = operand1;
					}else{//no fancy rules, progress as normal.
					combined = s + " " + operand1 + " " + operand2;
					}
					stack.push(combined);
				}
				tokens.pop();
			}else{
				tokens.pop();
				stack.push(s);
			}
		}
	return stack.peek();
	}
	
	/**
	 * Given a tree, a variable label and a value, this should replace all
	 * instances of that variable in the tree with the given value
	 * 
	 * Ideally, this method should run in O(n) time (quite hard! O(n^2) is easier.)
	 * 
	 * @param tree
	 *            - a tree representing an arithmetic expression
	 * @param variable
	 *            - a variable label that might exist in the tree
	 * @param value
	 *            - an integer value that the variable represents
	 * @return Tree after replacing all instances of the specified variable with
	 *         it's numeric value
	 * @throws IllegalArgumentException
	 *             if tree was not a valid expression, or either of the other
	 *             arguments are null
	 */
	public static LinkedBinaryTree<String> substitute(LinkedBinaryTree<String> tree, String variable, int value)
			throws IllegalArgumentException {
		// TODO: Implement this method
			//check if its illegal
		if(!isArithmeticExpression(tree)){
			throw new IllegalArgumentException();
		}//int cant be null here
		if(variable == null){
			throw new IllegalArgumentException();
		}
		
		String formula = tree2prefix(tree);
		String number = Integer.toString(value);
		formula = formula.replace(variable, number);
		LinkedBinaryTree<String> newTree = prefix2tree(formula);
		
		return newTree;
	}

	/**
	 * Given a tree and a a map of variable labels to values, this should
	 * replace all instances of those variables in the tree with the
	 * corresponding given values
	 * 
	 * Ideally, this method should run in O(n) expected time
	 * 
	 * @param tree
	 *            - a tree representing an arithmetic expression
	 * @param map
	 *            - a map of variable labels to integer values
	 * @return Tree after replacing all instances of variables which are keys in
	 *         the map, with their numeric values
	 * @throws IllegalArgumentException
	 *             if tree was not a valid expression, or map is null, or tries
	 *             to substitute a null into the tree
	 */
	public static LinkedBinaryTree<String> substitute(LinkedBinaryTree<String> tree, HashMap<String, Integer> map)
			throws IllegalArgumentException {
		// TODO: Implement this method
		//check if its illegal
		if(!isArithmeticExpression(tree)){
			throw new IllegalArgumentException();
		}
		if(map == null){
			throw new IllegalArgumentException();
		}
		
		String formula = tree2prefix(tree);
		try{
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			formula = formula.replace(entry.getKey(), Integer.toString(entry.getValue()));
		}
		}catch(NullPointerException e){
			throw new IllegalArgumentException();
		}
		LinkedBinaryTree<String> newTree = prefix2tree(formula);
		return newTree;
	}

	/**
	 * Given a tree, identify if that tree represents a valid arithmetic
	 * expression (possibly with variables)
	 * 
	 * Ideally, this method should run in O(n) expected time
	 * 
	 * @param tree
	 *            - a tree representing an arithmetic expression
	 * @return true if the tree is not null and it obeys the structure of an
	 *              arithmetic expression. Otherwise, it returns false
	 */
	public static boolean isArithmeticExpression(LinkedBinaryTree<String> tree) {
		// TODO: Implement this method
		if(tree != null){
			try{
				String prefix = tree2prefix(tree);
			}catch(IllegalArgumentException e){
				return false;
			}
			return true;
		}
		else{return false;}
	}

}

