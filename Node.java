/**
 * A generic class to define the structure of the AVL tree node
 * 
 * @author Akshay
 * @param <T>
 *
 */
public class Node<T> {
	Node left, right;
	T data;
	int height;

	Node(T input) {
		left = null;
		right = null;
		data = input;
		height = 0;
	}

}
