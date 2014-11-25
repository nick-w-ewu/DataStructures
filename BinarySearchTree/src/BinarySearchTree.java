// BinaryTree.java 
public class BinarySearchTree { 
	// Root node pointer. Will be null for an empty tree. 
	private Node root; 
	/* 
   --Node-- 
   The binary tree is built using this nested node class. 
   Each node stores one data element, and has left and right 
   sub-tree pointer which may be null. 
   The node is a "dumb" nested class -- we just use it for 
   storage; it does not have any methods. 
	 */ 
	private class Node { 
		Node left; 
		Node right; 
		int data;

		public Node(int newData) { 
			left = null; 
			right = null; 
			data = newData; 
		} 
	}

	/** 
   Creates an empty binary tree -- a null root pointer. 
	 */ 
	public BinarySearchTree() { 
		root = null; 
	} 


	/** 
	 * Returns true if the given target is in the binary tree. 
	 * Uses a recursive helper. 
	 */ 
	public boolean lookup(int data)
	{ 
		return(lookup(root, data)); 
	} 

	/** 
	 * Recursive lookup  -- given a node, recur 
	 * down searching for the given data. 
	 */ 
	private boolean lookup(Node node, int data)
	{ 
		if (node==null)
		{ 
			return(false); 
		}

		if (data==node.data)
		{ 
			return(true); 
		} 
		else if (data<node.data)
		{ 
			return(lookup(node.left, data)); 
		} 
		else
		{ 
			return(lookup(node.right, data)); 
		} 
	} 

	/** 
	 * Inserts the given data into the binary tree. 
	 * Uses a recursive helper. 
	 */ 
	public void insert(int data)
	{ 
		root = insert(root, data); 
	} 

   /** 
    * Recursive insert -- given a node pointer, recur down and 
    * insert the given data into the tree. Returns the new 
    * node pointer (the standard way to communicate
    * a changed pointer back to the caller). 
    */ 
	private Node insert(Node node, int data)
	{ 
		if (node==null)
		{ 
			node = new Node(data); 
		} 
		else
		{ 
			if (data <= node.data)
			{ 
				node.left = insert(node.left, data); 
			} 
			else
			{ 
				node.right = insert(node.right, data); 
			} 
		}

		return(node); // in any case, return the new pointer to the caller 
	}
	
	/** 
	 * Returns the number of nodes in the tree. 
	 * Uses a recursive helper that recurs 
	 * down the tree and counts the nodes. 
	 */ 
	public int size() { 
		return(size(root)); 
	}
	private int size(Node node) { 
		if (node == null) return(0); 
		else { 
			return(size(node.left) + 1 + size(node.right)); 
		}
	}
	
	
	/** 
	 * Returns the max root-to-leaf depth of the tree. 
	 * Uses a recursive helper that recurs down to find 
	 * the max depth. 
	 */ 
	public int maxDepth() { 
		return(maxDepth(root)); 
	}
	private int maxDepth(Node node) { 
		if (node==null) { 
			return(0); 
		} 
		else { 
			int lDepth = maxDepth(node.left); 
			int rDepth = maxDepth(node.right);

			// use the larger + 1 
			return(Math.max(lDepth, rDepth) + 1); 
		} 
	} 
	
	
	/** 
	 * Prints the node values in the "inorder" order. 
	 * Uses a recursive helper to do the traversal. 
	 */ 
	public void printTree()
	{ 
		printTree(root); 
		System.out.println(); 
	}
	private void printTree(Node node)
	{ 
		if (node == null)
		{
			return;
		}

		// left, node itself, right 
		printTree(node.left); 
		System.out.print(node.data + "  "); 
		printTree(node.right); 
	} 
	
	
	/** 
	 * Given a binary tree, prints out all of its root-to-leaf 
	 * paths, one per line. Uses a recursive helper to do the work. 
	*/ 
	public void printPaths() { 
	  int[] path = new int[1000]; 
	  printPaths(root, path, 0); 
	}
	/** 
	 * Recursive printPaths helper -- given a node, and an array containing 
	 * the path from the root node up to but not including this node, 
	 * prints out all the root-leaf paths. 
	*/ 
	private void printPaths(Node node, int[] path, int pathLen) { 
	  if (node==null) return;

	  // append this node to the path array 
	  path[pathLen] = node.data; 
	  pathLen++;

	  // it's a leaf, so print the path that led to here 
	  if (node.left==null && node.right==null) { 
	    printArray(path, pathLen); 
	  } 
	  else { 
	  // otherwise try both subtrees 
	    printPaths(node.left, path, pathLen); 
	    printPaths(node.right, path, pathLen); 
	  } 
	}

	/** 
	 Utility that prints ints from an array on one line. 
	*/ 
	private void printArray(int[] ints, int len) { 
	  int i; 
	  for (i=0; i<len; i++) { 
	   System.out.print(ints[i] + " "); 
	  } 
	  System.out.println(); 
	} 
	//---------------------------------------------------------------
	
	public boolean remove( int d ) {
		boolean ret[] = new boolean[1];
		ret[0] = false;
		this.root = removeEntry(this.root, d, ret);
		return ret[0];
	}
	
	// Removes an entry from the tree rooted at a given node.
	// rootNode is a reference to the root of a tree.
	// entry is the object to be removed.
	// removed[0] is set to true if the entry is removed, otherwise it is false.
	// Returns the root node of the resulting tree; if entry matches
	// an entry in the tree.
	private Node removeEntry(Node rootNode, int entry, boolean removed[])
	{
		if (rootNode != null)
		{
			int rootData = rootNode.data;

			if (entry == rootData)       // entry == root entry
			{
				removed[0] = true; 
				rootNode = removeFromRoot(rootNode);
			}
			else if (entry < rootData)   // entry < root entry
			{
				Node subtreeRoot = removeEntry(rootNode.left, entry, removed);
				rootNode.left = subtreeRoot;
			}
			else                       // entry > root entry
			{
				rootNode.right = removeEntry(rootNode.right, entry, removed);
			} // end if
		} // end if

		return rootNode;
	} // end removeEntry
	
	// Removes the entry in a given root node of a subtree.
	// rootNode is the root node of the subtree.
	// Returns the root node of the revised subtree.
	private Node removeFromRoot(Node rootNode)
	{
		// Case 1: rootNode has two children 
		if (rootNode.left != null && rootNode.right != null)
		{
			// find node with largest entry in left subtree
			Node largestNode = findLargest(rootNode.left);

			// replace entry in root
			rootNode.data = largestNode.data;

			// remove node with largest entry in left subtree
			rootNode.left = removeLargest(rootNode.left);
		} // end if 

		// Case 2: rootNode has at most one child
		else if (rootNode.right != null)
			rootNode = rootNode.right;
		else
			rootNode = rootNode.left;

		// Assertion: if rootNode was a leaf, it is now null

		return rootNode; 
	} // end removeEntry


	// Finds the node containing the largest entry in a given tree.
	// rootNode is the root node of the tree.
	// Returns the node containing the largest entry in the tree.
	private Node findLargest( Node rootNode )
	{
		if ( rootNode.right != null )
			rootNode = findLargest(rootNode.right);

		return rootNode;
	} // end findLargest
	
	
	// Removes the node containing the largest entry in a given tree.
	// rootNode is the root node of the tree.
	// Returns the root node of the revised tree.
	private Node removeLargest( Node rootNode )
	{
		if (rootNode.right != null) //current subtree has right subtree
		{
			Node curRoot = removeLargest(rootNode.right);
			rootNode.right = curRoot;
		}
		else 
			rootNode = rootNode.left;

		return rootNode;
	} // end removeLargest
	
//-------------------------------------------------------------------------------
	//
	public static void main(String argv[]){
		BinarySearchTree bst = new BinarySearchTree();
		int arr[] = { 9, 3, 6, 2, 7, 12, 1, 5, 4};
		for(int i : arr) {
			bst.insert(i);
		}
		
		bst.printTree();
		bst.printPaths();
		System.out.println(bst.remove(9) + "\n\n");
		bst.printPaths();
		
	}
	
}//end of class
