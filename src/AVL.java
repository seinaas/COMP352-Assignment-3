import java.util.ArrayList;

public class AVL {
    // Java program for deletion in AVL Tree

    class Node {
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
    // A utility function to get height of the tree
    int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node rightRotate(Node y) {
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

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node leftRotate(Node x) {
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

    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    Node insert(Node node, String key, Vehicle v) {
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
		Wunbalanced */
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

    /* Given a non-empty binary search tree, return the
    node with minimum key value found in that tree.
    Note that the entire tree does not need to be
    searched. */
    Node minValueNode(Node node) {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }

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

    // A utility function to print preorder traversal of
    // the tree. The function also prints height of every
    // node
    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.vehicle.getKey() + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        allKeys.add(root.vehicle.getKey());
        inOrder(root.right);
    }

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

    public boolean contains(Node root, String key) {
        if (root.vehicle.getKey().equals(key)) {
            return true;
        }

        return false;
    }

    public ArrayList<String> allKeys() {
        allKeys= new ArrayList<String>();
        inOrder(this.root);
        return allKeys;
    }

    public boolean remove(String key) {
        try {
            this.deleteNode(this.root, key);
            return true;
        } catch (NullPointerException e) {
            System.out.println("The key doesn't exist in the CVR!");
            return false;
        }
    }

    public void add(String key, Vehicle vehicle) {
        this.root=this.insert(this.root, key, vehicle);
    }

    public Vehicle getValues(String key) {
        return find(this.root, key).vehicle;
    }

    public String nextKey(String key) {
        findNext(this.root, key);
        String val = greater.vehicle.getKey();
        greater = null;
        return val;
    }

    public String prevKey(String key) {
        findPrev(this.root, key);
        String val = lesser.vehicle.getKey();
        lesser = null;
        return val;
    }

    public void clear() {
        root.left=null;
        root.right=null;
        root=null;
    }
}
