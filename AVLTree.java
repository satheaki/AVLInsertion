import java.util.Scanner;

/**
 * A Class to implement the functions of AVL Tree
 * 
 * @author Akshay
 *
 */
public class AVLTree {

	public static Node root;
	
	AVLTree()
	{
		root=null;
	}

	/**
	 * 
	 * @param treeNode
	 * @return
	 */
	private int height(Node treeNode) {
		return treeNode == null ? -1 : treeNode.height;
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public <T extends Comparable<? super T>> Node insert(T input) {
		root = addNodeInAVL(input, root);
		return root;
	}

	/**
	 * 
	 * @param input
	 * @param newNode
	 * @return
	 */
	public <T extends Comparable<? super T>> Node addNodeInAVL(T input,
			Node newNode) {
		int heightDiff;
		if (newNode == null)
			newNode = new Node(input);
		else if (input.compareTo((T) newNode.data) < 0) {
			newNode.left = addNodeInAVL(input, newNode.left);
			heightDiff = height(newNode.left) - height(newNode.right);
			if (heightDiff == 2) {
				if (input.compareTo((T) newNode.left.data) < 0)
					newNode = LLRotation(newNode);
				else
					newNode = LRRotation(newNode);
			}
		} else if (input.compareTo((T) newNode.data) > 0) {
			newNode.right = addNodeInAVL(input, newNode.right);
			heightDiff = height(newNode.left) - height(newNode.right);
			if (heightDiff == 2) {
				if (input.compareTo((T) newNode.right.data) > 0)
					newNode = RRRotation(newNode);
				else
					newNode = RLRotation(newNode);
			}
		} else if (input.compareTo((T) newNode.data) == 0) {
			// return false;
		}

		newNode.height = max(height(newNode.left), height(newNode.right)) + 1;
		return newNode;

	}

	private int max(int leftHeight, int rightHeight) {
		return leftHeight > rightHeight ? leftHeight : rightHeight;
	}

//	private Node RLRotation(Node RLvoilatingNode) {
//		RLvoilatingNode.right = LLRotation(RLvoilatingNode.right);
//		return RRRotation(RLvoilatingNode);
//	}
//
//	private Node LRRotation(Node LRvoilatingNode) {
//		LRvoilatingNode.left = RRRotation(LRvoilatingNode.left);
//		return LLRotation(LRvoilatingNode);
//	}
//
//	private Node RRRotation(Node voilatingNode) {
//		Node temp = voilatingNode.right;
//		voilatingNode.right = temp.left;
//		temp.left = voilatingNode;
//		voilatingNode.height = max(height(voilatingNode.left),
//				height(voilatingNode.right)) + 1;
//		temp.height = max(height(temp.left), height(temp.right)) + 1;
//		return temp;
//	}
//
//	private Node LLRotation(Node voilatingNode) {
//		Node temp = voilatingNode.left;
//		voilatingNode.left = temp.right;
//		temp.right = voilatingNode;
//		voilatingNode.height = max(height(voilatingNode.left),
//				height(voilatingNode.right)) + 1;
//		temp.height = max(height(temp.left), height(temp.right)) + 1;
//		return temp;
//	}
	
	
	private Node RLRotation(Node k1) {
		k1.right = LLRotation(k1.right);
		return RRRotation(k1);
	}

	private Node LRRotation(Node k3) {
		k3.left = RRRotation(k3.left);
		return LLRotation(k3);
	}

	private Node RRRotation(Node k1) {
		Node k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = max(height(k1.left),
				height(k1.right)) + 1;
		k2.height = max(height(k2.left), height(k2.right)) + 1;
		return k2;
	}

	private Node LLRotation(Node k2) {
		Node k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = max(height(k2.left),
				height(k2.right)) + 1;
		k1.height = max(height(k1.left), height(k1.right)) + 1;
		return k1;
	}


	public void inorder(Node rootData) {
		if (rootData != null) {
			inorder(rootData.left);
			System.out.print("\t"+rootData.data);
			inorder(rootData.right);
		}
	}
	
	public void preorder(Node rootData) {
		if (rootData != null) {
			System.out.print("\t"+rootData.data);
			inorder(rootData.left);
			inorder(rootData.right);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int i=0;
		AVLTree mTree = new AVLTree();
		System.out.println("Enter Elements to insert:");
		while (i<5) {
			root = mTree.insert(scan.nextInt());
			i++;
		}
		System.out.println("Inorder:" + "");
		mTree.inorder(root);
		
		System.out.println("\npreorder:" + "");
		mTree.preorder(root);
	}

}
