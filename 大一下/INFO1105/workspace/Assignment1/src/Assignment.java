import java.util.*;

public class Assignment implements SubmissionHistory {
	
	protected class studentData{
		String unikey;
		submission root;
		
		studentData(String unikey, submission root){
			this.unikey = unikey;
			this.root = root;
		}
		
		public submission TreeSearch(Date k) {
			return TreeSearch(k, root);
		}
		
		public submission TreeSearch(Date k, submission subtreeRoot) {
			// base case: empty subtree
			if(subtreeRoot == null) {
				// k isn't in this subtree
				return null;
			}
			
			// base case: k matches the key in the current entry
			if(k.compareTo(subtreeRoot.getTime()) == 0) {
				// TODO: return the value
				return subtreeRoot;
			}
			// recursive case: k < the current entry
			else if(k.compareTo(subtreeRoot.getTime()) < 0) {
				// TODO: return the result of recursing to the left
				return TreeSearch(k, subtreeRoot.getLeft());
			}
			// recursive case: k > the current entry
			else {
				// TODO: return the result of recursing to the right
				return TreeSearch(k, subtreeRoot.getRight());
			}
		}
		
		public void removeHelper(Submission subtreeRoot){
			submission p = TreeSearch(subtreeRoot.getTime());
			if(p == null){
				return;//no such p node found
			}
			//case 1 : the node dont have child(is external leaf)
			if(p.getLeft() == null && p.getRight() == null){
				if(p.getParent() != null){
					if(p.getParent().getLeft() == p){
						p.getParent().setLeft(null);
					}else if(p.getParent().getRight() == p){
						p.getParent().setRight(null);
					}//break p's connection with parent
					p.setParent(null);
				}
				p = null;
			}
			//case 2 : the node have 1 child
			//left child
			else if(p.getLeft() != null && p.getRight() == null){
				if(p.getParent() != null){
					if(p.getParent().getLeft() == p){
						attachLeft(p.getParent(), p.getLeft());
					}else if(p.getParent().getRight() == p){
						attachRight(p.getParent(), p.getLeft());
					}//break p's connection with parent
					p.setParent(null);
				}else{//in case which p is the root that has only 1 child(2 nodes in total)
					p.getLeft().setParent(null);
					this.root = p.getLeft();
				}
				//right child
			}else if(p.getLeft() == null && p.getRight() != null){
				if(p.getParent() != null){
					if(p.getParent().getLeft() == p){
						attachLeft(p.getParent(), p.getRight());
					}else if(p.getParent().getRight() == p){
						attachRight(p.getParent(), p.getRight());
					}//break p's connection with parent
					p.setParent(null);
				}else{//in case which p is the root that has only 1 child(2 nodes in total)
					p.getRight().setParent(null);
					this.root = p.getRight();
				}
			}
			//case 3: the node has 2 children
			else if(p.getLeft() != null && p.getRight() != null){
				submission r = getInOrderPredecessor(p.getLeft());//r is the predecessor of p
				p = r;
					//p.setTime(r.getTime());//use r to replace p (??????)				
					removeHelper(r);//remove r
			}
			
		}
	}
	
protected class submission implements Submission {
		
		String unikey;
		private Date time;
		private Integer grade;
		private submission left;
		private submission right;
		private submission parent;
		
		submission(String unikey, Date time, Integer grade) {
			this.unikey = unikey;
			this.time = time;
			this.grade = grade;
			this.left = null;
			this.right = null;
			this.parent = null;
		}
		public String getUnikey(){return unikey;}
		
		public Date getTime() { return time; }
		
		public Integer getGrade() { return grade;}
		
		public submission getLeft() { return left; }
		public submission getRight() { return right; }
		public submission getParent() { return parent; }
		
		protected void setUnikey(String unikey) { this.unikey = unikey; }
		protected void setTime(Date time) { this.time = time; }
		protected void setGrade(Integer grade) { this.grade = grade; }

		protected void setLeft(submission entry) { left = entry; }
		protected void setRight(submission entry) { right = entry; }
		protected void setParent(submission entry) { parent = entry; }
	}
	
	ArrayList<studentData> unikeyCollection;
	
    public Assignment() {
        // TODO initialise your data structures
    	this.unikeyCollection = new ArrayList<studentData>();
    }
    
	
 // attaches the subtree rooted at 'child', to the parent
 	private void attachLeft(submission parent, submission child) {
 		if(child != null) { child.setParent(parent); }
 		if(parent != null) { parent.setLeft(child); }
 	}

 	// attaches the subtree rooted at 'child', to the parent
 	private void attachRight(submission parent, submission child) {
 		if(child != null) { child.setParent(parent); }
 		if(parent != null) { parent.setRight(child); }
 	}
    
//////////////////////assignment methods////////////////////////
    @Override
    public Integer getBestGrade(String unikey) throws IllegalArgumentException{
        // TODO Implement this, ideally in better than O(n)
    	//exception
    	if(unikey == null){
    		throw new IllegalArgumentException();
    	}
    	int bestGrade = 0;
    	for(int i = 0; i < this.unikeyCollection.size(); i++){
    		if(this.unikeyCollection.get(i).unikey.equals(unikey)){
    			return getBestGradeRecursion(this.unikeyCollection.get(i).root, bestGrade);
    		}
    	}//no such student
        return null;
    }
    
    public Integer getBestGradeRecursion(submission subtreeRoot, Integer bestGrade){
    			//base case: change the temp value if finds higher value
    			if(bestGrade.compareTo(subtreeRoot.getGrade()) < 0){
    				bestGrade = subtreeRoot.getGrade();
    			}
    			
    			//recursion case: recursion left, then right
    			if(subtreeRoot.getLeft() != null){
    				bestGrade = getBestGradeRecursion(subtreeRoot.getLeft(), bestGrade);
    			}
    			if(subtreeRoot.getRight() != null){
    				bestGrade = getBestGradeRecursion(subtreeRoot.getRight(), bestGrade);
    			}
    			return bestGrade;
    }

    @Override
    public Submission getSubmissionFinal(String unikey)throws IllegalArgumentException {
        // TODO Implement this, ideally in better than O(n)
    	//this should be the smallest date value
    	//Date time = new Date(0L);
    	//exception
    	if(unikey == null){
    		throw new IllegalArgumentException();
    	}
    	for(int i = 0; i < this.unikeyCollection.size(); i++){
    		if(this.unikeyCollection.get(i).unikey.equals(unikey)){
    			return getLatestSubmissionRecursion(this.unikeyCollection.get(i).root);
    		}
    	}//no such student
        return null;
    }
    
    public submission getLatestSubmissionRecursion(submission subtreeRoot){
    			
    			if(subtreeRoot == null){
    				return null;
    			}
    			
    			if(subtreeRoot.getRight() == null){
    				return subtreeRoot;
    			}
    			else{
    				return getLatestSubmissionRecursion(subtreeRoot.getRight());
    			}
    			
    }

    @Override
    public Submission getSubmissionBefore(String unikey, Date deadline)throws IllegalArgumentException {
        // TODO Implement this, ideally in better than O(n)
    	//exception
    	if(unikey == null||deadline == null){
    		throw new IllegalArgumentException();
    	}
    	submission ans = null;
    	for(int i = 0; i < this.unikeyCollection.size(); i++){
    		if(this.unikeyCollection.get(i).unikey.equals(unikey)){
    			return getSubmissionBeforeRecursion(this.unikeyCollection.get(i).root, deadline, ans);
    		}
    	}//no such student
        return ans;
    }
    //helper to get in order predecessor
    public submission getInOrderPredecessor(submission subtreeRoot){//note that the root here should be the left child of desired node
    	if(subtreeRoot.getRight() != null){
    		return getInOrderPredecessor(subtreeRoot.getRight());
    	}
    	else{
    		return subtreeRoot;
    	}
    }
    
    public submission getSubmissionBeforeRecursion(submission subtreeRoot, Date deadline, submission ans){
    			if(subtreeRoot == null){
    				return ans;
    			}
    			else if(subtreeRoot.getTime().equals(deadline)){
    				return subtreeRoot;
    			}
    			else if(subtreeRoot.getTime().compareTo(deadline) < 1){
    				//ans = max(ans, subtreeRoot);
    				ans = subtreeRoot;
					if(subtreeRoot.getRight() != null){
    				return getSubmissionBeforeRecursion(subtreeRoot.getRight(), deadline, ans);
					}
					
    			}
    			else{
    				if(subtreeRoot.getLeft() != null){
    				return getSubmissionBeforeRecursion(subtreeRoot.getLeft(), deadline, ans);
    				}
    			}
    			return ans;
//    			if(subtreeRoot.getTime().compareTo(deadline) > 0 && subtreeRoot.getLeft() != null){
//    				return getSubmissionBeforeRecursion(subtreeRoot.getLeft(), deadline);
//    			}
//    			if(subtreeRoot.getTime().compareTo(deadline) > 0 && subtreeRoot.getLeft() == null){
//    				return null;
//    			}
//    			if(subtreeRoot.getRight() == null || subtreeRoot.getRight().getTime().compareTo(deadline) > 0){
//    				return subtreeRoot;
//    			}
//    			else{
//    				return getSubmissionBeforeRecursion(subtreeRoot.getRight(), deadline);
//    			}
    }

    @Override
    public Submission add(String unikey, Date timestamp, Integer grade)throws IllegalArgumentException {
        // TODO Implement this, ideally in better than O(n)
    	//exception
    	if(unikey == null||timestamp == null||grade == null){
    		throw new IllegalArgumentException();
    	}
    	for(int i = 0; i < this.unikeyCollection.size(); i++){
    		if(this.unikeyCollection.get(i).unikey.equals(unikey)){
    			submission newEntry = new submission(unikey, timestamp, grade);
    			this.unikeyCollection.get(i).root = put(unikey, timestamp, grade, this.unikeyCollection.get(i).root);
    			return newEntry;
    		}
    	}

        this.unikeyCollection.add(new studentData(unikey, new submission(unikey, timestamp, grade)));
   
        //this.unikeyCollection.get(this.unikeyCollection.size()-1).root = put(unikey, timestamp, grade, this.unikeyCollection.get(this.unikeyCollection.size()-1).root);
        return this.unikeyCollection.get(this.unikeyCollection.size()-1).root;
    }
    
    public submission put(String unikey, Date timestamp, Integer grade, submission subtreeRoot){
    	// base case: the key wasn't in the subtree
    			if(subtreeRoot == null) {
    				// we have reached a null subtree, where k should be
    				//TODO: create a new entry
    				submission newEntry = new submission(unikey, timestamp, grade);
    				//TODO: increment the size variable
    				//TODO: return the new entry
    				//subtreeRoot = newEntry;
    				return newEntry;
    			}

    			// base case: k matches the one in the current entry
    			if(timestamp.compareTo(subtreeRoot.getTime()) == 0) {
    				// TODO: create a new entry
    				submission newEntry = new submission(unikey, timestamp, grade);
    				// TODO: attachLeft the left child of the current entry to it		
    				attachLeft(newEntry, subtreeRoot.getLeft());
    				// TODO: attachRight the right child of the current entry to it		
    				attachRight(newEntry, subtreeRoot.getRight());
    				// TODO: return the new subtree
    				
    				return newEntry;
    			}
    			// recursive case: k < the current entry
    			else if(timestamp.compareTo(subtreeRoot.getTime()) < 0) {
    				// TODO: get the subtree resulting from recursing left
    				submission newRoot = put(unikey, timestamp, grade, subtreeRoot.getLeft());
    				// TODO: attach that subtree to the current entry
    				attachLeft(subtreeRoot, newRoot);
    				// TODO: return the modified entry
    				
    				return subtreeRoot;
    			}
    			// recursive case: k > the current entry
    			else {//if(timestamp.compareTo(subtreeRoot.getTime()) > 0){
    				// TODO: get the subtree resulting from recursing right
    				submission newRoot = put(unikey,timestamp,  grade, subtreeRoot.getRight());
    				// TODO: attach that subtree to the current entry
    				attachRight(subtreeRoot, newRoot);
    				// TODO: return the modified entry
    				
    				return subtreeRoot;
    			}
    		//	return subtreeRoot;
    }

    
    
    @Override
    public void remove(Submission submission) throws IllegalArgumentException{
        // TODO Implement this, ideally in better than O(n)
    	//exception
    	if(submission == null){
    		throw new IllegalArgumentException();
    	}
    	for(int i = 0; i < this.unikeyCollection.size(); i++){
    		if(this.unikeyCollection.get(i).unikey.equals(submission.getUnikey())){//located the student of that submission
    			this.unikeyCollection.get(i).removeHelper(submission);
    		}//submission removed
    		if(this.unikeyCollection.get(i).root == null){//the last submission of this student is removed
    			this.unikeyCollection.remove(i);//remove the student
    		}
    	}
    	//this.unikeyCollection.get(submission.getUnikey()).TreeSearch(submission.getTime());
    }

    @Override
    public List<String> listTopStudents() {
        // TODO Implement this, ideally in better than O(n)
        // (you may ignore the length of the list in the analysis)
    	List<String> students = new ArrayList<String>();
    	int topGrade = 0;
    	if(this.unikeyCollection.size() == 0){
    		return students;
    	}
    	for(int i = 0; i < this.unikeyCollection.size(); i++){
    			if(this.getBestGrade(this.unikeyCollection.get(i).unikey) > topGrade){
    				topGrade = this.getBestGrade(this.unikeyCollection.get(i).unikey);
    				students.clear();
    				students.add(this.unikeyCollection.get(i).unikey);
    			}else if(this.getBestGrade(this.unikeyCollection.get(i).unikey) == topGrade){
    				students.add(this.unikeyCollection.get(i).unikey);
    			}
    	}
    	
    	
        return students;
    }

    @Override
    public List<String> listRegressions() {
        // TODO Implement this, ideally in better than O(n^2)
    	List<String> students = new ArrayList<String>();
    	for(int i = 0; i < this.unikeyCollection.size(); i++){
    		if(this.getBestGrade(this.unikeyCollection.get(i).unikey) > this.getSubmissionFinal(this.unikeyCollection.get(i).unikey).getGrade()){
    			students.add(this.unikeyCollection.get(i).unikey);
    		}
    	}
        return students;
    }
}

