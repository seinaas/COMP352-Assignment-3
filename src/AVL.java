import java.util.ArrayList;

public class AVL {
    /**
     * Inner node class
     */
    private class Node {
        Vehicle vehicle;
        int height;
        Node left, right;

        Node(Vehicle d) {
            vehicle = d;
            height = 1;
        }
    }


    private Node root, greater, lesser;
    private ArrayList<String> allKeys;

    /**
     * Utility function to return the height of the tree
     * @param N Node to start from
     * @return Height of the tree
     */
    private int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    /**
     * Gets the maximum of two integers
     * @param a Integer a
     * @param b Integer b
     * @return Higher integer
     */
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    /**
     * Right rotates the subtree at node y
     * @param y Root node of subtree
     * @return New root node
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    /**
     * Left rotates the subtree at node x
     * @param x Root node of subtree
     * @return New root node
     */
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    /**
     * Get balance of tree
     * @param N root node
     * @return Balance difference [-1, 1]
     */
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    /**
     * Insert a new node into the AVL
     * @param node Root node
     * @param key Vehicle key
     * @param v Vehicle
     * @return New node
     */
    private Node insert(Node node, String key, Vehicle v) {
        /* 1. Perform the normal BST rotation */
        if (node == null) {
            return (new Node(v));
        }

        if (key.compareTo(node.vehicle.getKey()) < 0)
            node.left = insert(node.left, key, v);
        else if (key.compareTo(node.vehicle.getKey()) > 0)
            node.right = insert(node.right, key, v);
        else // Equal keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),
                height(node.right));

		/* 3. Get the balance factor of this ancestor
		node to check whether this node became
		Unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then
        // there are 4 cases Left Left Case
        if (balance > 1 && key.compareTo(node.left.vehicle.getKey()) < 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key.compareTo(node.right.vehicle.getKey()) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key.compareTo(node.left.vehicle.getKey()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key.compareTo(node.right.vehicle.getKey()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    /**
     * Gets node with lowest key value
     * @param node Root node
     * @return Node with lowest key value
     */
    Node minValueNode(Node node) {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }

    /**
     * Deletes a node from the AVL
     * @param root Root node
     * @param key Key of node to delete
     * @return New root
     */
    private Node deleteNode(Node root, String key) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;

        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (key.compareTo(root.vehicle.getKey()) < 0)
            root.left = deleteNode(root.left, key);

            // If the key to be deleted is greater than the
            // root's key, then it lies in right subtree
        else if (key.compareTo(root.vehicle.getKey()) > 0)
            root.right = deleteNode(root.right, key);

            // if key is same as root's key, then this is the node
            // to be deleted
        else {

            // node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // No child case
                if (temp == null) {
                    temp = root;
                    allKeys.remove(temp);
                    root = null;
                } else // One child case
                    root = temp; // Copy the contents of
                // the non-empty child
            } else {

                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.vehicle = temp.vehicle;

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.vehicle.getKey());
            }
        }

        // If the tree had only one node then return
        if (root == null) {
            return root;
        }

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = max(height(root.left), height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    /**
     * Preorder Traversal of the AVL
     * @param node Root node
     */
    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.vehicle.getKey() + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * Inorder Traversal of the AVL
     * @param root Root node
     */
    private void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        allKeys.add(root.vehicle.getKey());
        inOrder(root.right);
    }

    /**
     * Search through avl to find specific node with corresponding key
     * @param root Root node
     * @param key Key to search
     * @return Found node
     */
    private Node find(Node root, String key) {
        while (root != null) {
            int comp = key.compareTo(root.vehicle.getKey());
            if (comp == 0) {
                return root;
            } else if (comp > 0) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return null;
    }

    /**
     * Finds the next key of given key in lexicographic order
     * @param root Root node
     * @param key Key to find
     */
    private void findNext(Node root, String key) {
        int comp;
        while (root != null) {
            comp = root.vehicle.getKey().compareTo(key);
            if (comp > 0) {
                greater = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
    }

    /**
     * Finds the previous key of given key in lexicographic order
     * @param root Root node
     * @param key Key to find
     */
    private void findPrev(Node root, String key) {
        int comp;
        while (root != null) {
            comp = root.vehicle.getKey().compareTo(key);
            if (comp < 0) {
                lesser = root;
                root = root.right;
            } else if (comp > 0) {
                root = root.left;
            } else {
                if (root.left != null) {
                    lesser = root.left;
                }
                root = root.left;
            }
        }
    }

    /**
     * All keys in AVL
     * @return Returns an ArrayList containing all the keys sorted in lexicographic order
     */
    public ArrayList<String> allKeys() {
        allKeys= new ArrayList<String>();
        inOrder(this.root);
        return allKeys;
    }

    /**
     * Mediator function that calls deleteNode
     * @param key Key to remove
     * @return Returns true if remove was successful and false otherwise
     */
    public boolean remove(String key) {
        try {
            this.deleteNode(this.root, key);
            return true;
        } catch (NullPointerException e) {
            System.out.println("The key doesn't exist in the CVR!");
            return false;
        }
    }

    /**
     * Mediator function that calls insert
     * @param key Key of vehicle
     * @param vehicle Vehicle to insert
     */
    public void add(String key, Vehicle vehicle) {
        this.root=this.insert(this.root, key, vehicle);
    }

    /**
     * Calls find function and returns the vehicle info
     * @param key Key to find
     * @return Vehicle info
     */
    public Vehicle getValues(String key) {
        return find(this.root, key).vehicle;
    }

    /**
     * Mediator function that calls findNext
     * @param key Key to find
     * @return Next key
     */
    public String nextKey(String key) {
        findNext(this.root, key);
        String val = greater.vehicle.getKey();
        greater = null;
        return val;
    }

    /**
     * Mediator function that calls findPrev
     * @param key Key to find
     * @return Previous key
     */
    public String prevKey(String key) {
        findPrev(this.root, key);
        String val = lesser.vehicle.getKey();
        lesser = null;
        return val;
    }

    /**
     * Empty the AVL
     */
    public void clear() {
        root.left=null;
        root.right=null;
        root=null;
    }
}
