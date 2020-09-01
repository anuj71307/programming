package com.programs.problems;


import com.programs.stack.Stack;
import com.programs.trees.BinarySearchTree;
import com.programs.trees.BinaryTree;
import com.programs.trees.ITree;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;


public class TreeProblems {


    public static void main(String args[]) throws Exception {

        TreeProblems tp = new TreeProblems();
        TreeNode node = new TreeNode(45);
        node.left = new TreeNode(6);
        node.left.left = new TreeNode(11);
        node.left.right = new TreeNode(13);
        node.left.right.left = new TreeNode(12);
        node.right = new TreeNode(9);
        node.right.right = new TreeNode(8);
        node.right.right.left = new TreeNode(19);
        node.right.right.left.left = new TreeNode(5);
        node.right.right.right = new TreeNode(24);
        System.out.print(tp.findPath(node, 41));
    }

    int res;

    /**
     * https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
     * Leetcode 1372
     * Longest zigzag path in binary tree. At each node if it was left node then we should check height on right side and vide versa
     * Brute force approach would be to traverse at each node.
     * Optimal solution- This is similar to finding height of tree so if we do post order traversal
     * and at each node we check value of left height and right height and return this pair.
     * Once we have checked for both left and right height and have pair of both left and right child
     * then for left child we need to see what it's right value is and for right child we need to check what its left height is
     * to do this we return a array of size 2
     * Time complexity O(N)
     * @param root
     * @return
     */
    public int longestZigZag(TreeNode root) {
        if (root == null) return 0;
        res = 0;
        //HashMap<TreeNode,Pair> map = new HashMap();
        longestZigZag(root, 1);
        return res - 1;

    }

    public int[] longestZigZag(TreeNode root, int k) {
        if (root == null) return new int[]{0, 0};
        int[] lr = longestZigZag(root.left, k);
        int[] rr = longestZigZag(root.right, k);
        int[] arr = new int[]{1, 1};
        if (root.left != null) {
            arr[0] += lr[1];
        }
        if (root.right != null) {
            arr[1] += rr[0];
        }

        res = Math.max(res, Math.max(arr[0], arr[1]));
        return arr;
    }

    /**
     * https://www.geeksforgeeks.org/find-distance-root-given-node-binary-tree/
     * Find distance from root to node
     * Time complexity O(N)
     *
     * @param root
     * @param x
     * @return
     */
    int findDistance(TreeNode root, int x) {
        if (root == null) return -1;
        if (root.value == x) return 0;
        int dis = findDistance(root.left, x);
        if (dis != -1) return dis + 1;
        dis = findDistance(root.right, x);
        if (dis != -1) return dis + 1;
        return -1;
    }

    /**
     * https://www.geeksforgeeks.org/print-path-root-given-node-binary-tree/
     * Find path from root to given node
     * Time complexity O(N)
     *
     * @param root
     * @param x
     * @return
     */
    List<TreeNode> findPath(TreeNode root, int x) {
        if (root == null) return null;
        List<TreeNode> path = new ArrayList<>();
        findPath(root, path, x);
        return path;
    }

    private boolean findPath(TreeNode root, List<TreeNode> path, int x) {
        if (root == null) return false;
        path.add(root);
        if (root.value == x) {
            return true;
        }
        if (findPath(root.left, path, x) || findPath(root.right, path, x)) return true;
        path.remove(path.size() - 1);
        return false;

    }

    /**
     * Find two nodes in a binary tree which sum equals to target
     * All nodes will have positive value and target also
     * We just do level order traversal ie BFS and keep a set of all element before this level.
     * at each node we just check if set contains target-node.value.
     *
     * @param root   = root of tree
     * @param target = target
     * @return
     */
    public int t2Sum(TreeNode root, int target) {

        HashSet<Integer> set = new HashSet();
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        HashSet<Integer> temp = new HashSet<>();
        while (!que.isEmpty()) {
            int i = que.size();
            while (i > 0) {
                i--;
                TreeNode node = que.poll();
                int val = node.value;
                if (set.contains(target - val)) {
                    return 1;
                }
                temp.add(val);
                if (node.left != null) {
                    que.add(node.left);
                }
                if (node.right != null) {
                    que.add(node.right);
                }
            }
            set.addAll(temp);
            temp.clear();
        }
        return 0;
    }

    /**
     * https://leetcode.com/problems/all-possible-full-binary-trees/
     * LeetCode 894
     * Generate all structurally unique possible full tree of given size
     *
     * @param N
     * @return
     */
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> list = new ArrayList();
        if (N % 2 == 0) return list;
        TreeNode root = new TreeNode(0);
        list.add(root);
        if (N == 1) return list;

        HashMap<Integer, List<TreeNode>> total = new HashMap<>();
        total.put(1, list);

        for (int i = 3; i <= N; i = i + 2) {
            List<TreeNode> l = new ArrayList<>();
            for (int j = 2; j < i; j = j + 2) {
                int k = j - 1;
                for (TreeNode temp : total.get(k)) {
                    int m = i - j;
                    for (TreeNode t : total.get(m)) {
                        TreeNode node = new TreeNode(0);
                        node.left = temp;
                        node.right = t;
                        l.add(node);
                    }
                }
            }
            total.put(i, l);
        }
        return total.get(N);
    }

    public void doIterative(TreeNode node) {
        if (node == null) {
            System.out.print(null + ", ");
            return;
        }
        System.out.print(node.value + ", ");
        doIterative(node.left);
        doIterative(node.right);
    }


    /**
     * https://leetcode.com/problems/deepest-leaves-sum/
     * Leetcode 1302
     * Time complexity - O(N) cause we traverse all the node
     * Space - Worst case O(H) where H is height of tree
     *
     * @param root
     * @return
     */
    public int deepestLeavesSum(TreeNode root) {
        int res[] = new int[1];

        int[] max = new int[1];
        sum(root, 0, max, res);
        return res[0];
    }

    public void sum(TreeNode root, int depth, int[] max, int[] res) {
        if (root == null) return;
        if (depth == max[0]) {
            res[0] += root.value;
        } else if (depth > max[0]) {
            max[0] = depth;
            res[0] = root.value;
        }
        sum(root.left, depth + 1, max, res);
        sum(root.right, depth + 1, max, res);
    }

    /**
     * Find in order successor of a node in Binary Search Tree. In node is not present or successor is not available return -1
     * Time complexity - if Balanced BST then Log(N) where N is number of node or O(H) where H ie height of tree
     * If tree is not balanced then time complexity is O(N) where is number of node
     * Space complexity is also same as time complexity
     *
     * @param root
     * @param data
     * @return
     */
    public BinarySearchTree<Integer> inOrderSuccessor(BinarySearchTree<Integer> root, int data) {

        if (root == null) return null;
        BinarySearchTree<Integer> succ = null;
        while (root != null) {
            if (root.getData() == data) {
                if (root.getRightNode() != null) {
                    return getLeftMost((BinarySearchTree<Integer>) root.getRightNode());
                } else {
                    return succ;
                }
            }

            if (root.getData() < data) {
                root = (BinarySearchTree<Integer>) root.getRightNode();
            } else {
                succ = root;
                root = (BinarySearchTree<Integer>) root.getLeftNode();
            }
        }

        return null;
    }

    private BinarySearchTree<Integer> getLeftMost(BinarySearchTree<Integer> rightNode) {
        while (rightNode.getLeftNode() != null) {
            rightNode = (BinarySearchTree<Integer>) rightNode.getLeftNode();
        }
        return rightNode;
    }

    public Node solve(Node A, int B) {
        return solve(A, B, 0);
    }

    /**
     * https://www.interviewbit.com/problems/remove-nodes-from-path-sum-less-than-b/
     *
     * @param node
     * @param sum
     * @param curr
     * @return
     */
    public Node solve(Node node, int sum, int curr) {
        if (node == null) return null;
        node.left = solve(node.left, sum, node.data + curr);
        node.right = solve(node.right, sum, node.data + curr);
        if (node.left == null && node.right == null && curr + node.data < sum) return null;
        return node;
    }

    void printPreOrder(Node node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    /**
     * https://www.geeksforgeeks.org/remove-nodes-root-leaf-paths-length-k/
     * Using improved height calculator function to do this. each time pass curr depth
     * and check depth+height is more than desired length or not
     * if not we simply delete the node
     *
     * @param root
     * @param length
     * @return
     */
    public Node removeNodes(Node root, int length) {
        int l = removeNode(root, length, 1);
        if (l < length) return null;
        return root;
    }


    public int removeNode(Node root, int length, int curr) {
        if (root == null) return 0;
        int left = removeNode(root.left, length, curr + 1);
        int right = removeNode(root.right, length, curr + 1);
        if (curr + left < length) root.left = null;
        if (curr + right < length) root.right = null;
        return Math.max(left, right) + 1;
    }

    /**
     * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
     */
    private Node connectIterative(Node root) {

        for (Node temp = root; temp != null; ) {

            Node head = new Node(0);
            Node tail = head;
            for (Node node = temp; node != null; node = node.next) {

                if (node.left != null) {
                    tail.next = node.left;
                    tail = tail.next;
                }
                if (node.right != null) {
                    tail.next = node.right;
                    tail = tail.next;
                }
            }
            temp = head.next;

        }

        return root;
    }

    /**
     * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
     * Using Queue
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) return root;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {

                // if(i==size-1) break;
                Node node = q.poll();
                if (i < size - 1) {
                    node.next = q.peek() != null ? q.peek() : null;
                }
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);

                }
            }
        }

        return root;

    }

    /**
     * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
     * Bottom Up Approach
     *
     * @param node
     * @return
     */
    int longestSubsequence(TreeNode node) {
        int res[] = new int[1];
        longestSubsequence(node, res);
        return res[0];
    }

    /**
     * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
     *
     * @param node
     * @param res
     * @return
     */
    private int longestSubsequence(TreeNode node, int[] res) {
        if (node == null) return 0;
        int retValue = 1;
        if (node.left != null) {
            int left = longestSubsequence(node.left, res);
            if (node.left.value - node.value == 1) {
                retValue = Math.max(retValue, left + 1);
            }
        }
        if (node.right != null) {
            int right = longestSubsequence(node.right, res);
            if (node.right.value - node.value == 1) {
                retValue = Math.max(retValue, right + 1);
            }
        }
        res[0] = Math.max(res[0], retValue);

        return retValue;
    }

    /**
     * https://leetcode.com/problems/most-frequent-subtree-sum/
     *
     * @param root
     * @return
     */
    private int[] findFrequentTreeSum(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        findFrequentTreeSum(root, map);
        List<Integer> list = new ArrayList<>();
        Iterator<Map.Entry<Integer, Integer>> itr = map.entrySet().iterator();
        int max = Integer.MIN_VALUE;
        while (itr.hasNext()) {
            Map.Entry<Integer, Integer> entry = itr.next();
            int key = entry.getKey();
            int val = entry.getValue();
            if (val < max) continue;
            else if (val == max) {
                list.add(key);
            } else {
                list.clear();
                max = val;
                list.add(key);
            }

        }
        int[] j = new int[list.size()];
        int m = 0;
        for (int k : list) {
            j[m++] = k;
        }
        return j;
    }

    private int findFrequentTreeSum(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) return 0;
        int left = findFrequentTreeSum(root.left, map);
        int right = findFrequentTreeSum(root.right, map);
        int val = left + right + root.value;
        map.put(val, map.getOrDefault(val, 0) + 1);
        return val;
    }

    /**
     * https://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
     *
     * @param root
     * @return
     */
    TreeNode convertToDLL(TreeNode root) {
        TreeNode[] head = new TreeNode[1];
        convertToDLL(root, head);
        return head[0];
    }

    void convertToDLL(TreeNode root, TreeNode[] head) {
        if (root == null) return;
        convertToDLL(root.right, head);
        root.right = head[0];
        if (head[0] != null) {
            head[0].left = root;
        }
        head[0] = root;
        convertToDLL(root.left, head);
    }

    /**
     * https://leetcode.com/problems/range-sum-of-bst/
     *
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        int sum = 0;
        if (root.value >= L && root.value <= R) {
            sum += root.value;
            return sum + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
        }

        if (root.value < L) {
            return sum + rangeSumBST(root.right, L, R);
        }
        return sum + rangeSumBST(root.left, L, R);
    }

    /**
     * https://www.geeksforgeeks.org/find-closest-element-binary-search-tree/
     *
     * @param root
     * @param k
     * @return
     */
    public static int maxDiff(Node root, int k) {
        if (root == null) return Integer.MAX_VALUE;
        if (root.data == k) return 0;
        int diff = Math.abs(k - root.data);
        int d = 0;
        if (root.data < k) {
            d = maxDiff(root.right, k);
        } else {
            d = maxDiff(root.left, k);
        }

        return Math.min(diff, d);
    }

    /**
     * https://leetcode.com/problems/count-complete-tree-nodes/
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        int l = getLHeight(root);
        int r = getRHeight(root);
        if (l == r) {
            return (int) (Math.pow(2, l)) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    int getRHeight(TreeNode root) {
        int h = 0;
        while (root != null) {
            h++;
            root = root.right;
        }
        return h;
    }

    int getLHeight(TreeNode root) {
        int h = 0;
        while (root != null) {
            h++;
            root = root.left;
        }
        return h;
    }

    /**
     * https://leetcode.com/problems/flip-equivalent-binary-trees/
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {

        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.value != root2.value) return false;

        if (root1.left == root2.left || (root1.left != null && root2.left != null && root2.left.value == root1.left.value)) {
            return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);

        }
        TreeNode left = root2.left;
        root2.left = root2.right;
        root2.right = left;

        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
    }

    /**
     * https://leetcode.com/problems/print-binary-tree/
     *
     * @param root
     * @return
     */
    public List<List<String>> printTree(TreeNode root) {

        int h = height(root);
        String arr[][] = new String[h][(1 << h) - 1];

        for (String[] a : arr) {
            Arrays.fill(a, "");
        }

        fill(root, arr, 0, 0, arr[0].length - 1);
        List<List<String>> list = new ArrayList<>();
        for (String[] a : arr) {
            list.add(Arrays.asList(a));
        }
        return list;
    }

    void fill(TreeNode root, String[][] arr, int index, int start, int end) {
        if (root == null) return;
        int mid = (start + end) / 2;
        arr[index][mid] = "" + root.value;
        fill(root.left, arr, index + 1, start, mid - 1);
        fill(root.right, arr, index + 1, mid + 1, end);
    }

    int height(TreeNode root) {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }


    /**
     * https://leetcode.com/problems/delete-nodes-and-return-forest/
     * check below method for optimized version
     *
     * @param root
     * @param to_delete
     * @return
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (to_delete == null || to_delete.length == 0) {
            List<TreeNode> temp = new ArrayList<TreeNode>();
            temp.add(root);
            return temp;
        }
        HashSet<Integer> set = new HashSet<>();
        for (Integer i : to_delete) {
            set.add(i);
        }

        Queue<Item> q = new LinkedList();
        q.add(new Item(root, null));
        List<Item> list_delete = new ArrayList();

        while (!q.isEmpty()) {
            Item it = q.poll();
            TreeNode temp = it.node;
            if (set.contains(temp.value)) {
                list_delete.add(new Item(temp, it.parent));
            }
            if (temp.left != null) {
                q.add(new Item(temp.left, temp));
            }
            if (temp.right != null) {
                q.add(new Item(temp.right, temp));
            }
        }

        boolean root_delete = false;
        HashSet<TreeNode> res = new HashSet<>();
        for (Item item : list_delete) {
            if (item.node == root) root_delete = true;
            if (res.contains(item.node)) {
                res.remove(item.node);
            }
            if (item.node.left != null) {
                res.add(item.node.left);
            }
            if (item.node.right != null) {
                res.add(item.node.right);
            }

            if (item.parent != null && item.parent.left == item.node) {
                item.parent.left = null;
            }
            if (item.parent != null && item.parent.right == item.node) {
                item.parent.right = null;
            }
        }
        if (!root_delete) res.add(root);
        return new ArrayList<>(res);
    }

    class Item {
        TreeNode node;
        TreeNode parent;

        Item(TreeNode n, TreeNode p) {
            node = n;
            parent = p;
        }

    }

    /**
     * https://leetcode.com/problems/delete-nodes-and-return-forest/
     * First we add all the keys to be deleted to set for quick access and check
     * Next we do recursive traversal. and check if node has to be deleted. if a node has to be delete we update their parent's reference to this node
     * such that now it doesnt have any parent. then we can traverse left and right subtree of this node which is supposed to be deleted.
     * Since its parent reference has changed ie node doesn;t exist ie it can be reached from parent node so for all of its chilren parent will be null.
     * we can use this check to see if we need to add this node in result set or not.
     * so if a node's parent is null and it's key doesn't exist in set then its a valid node to considered. so we add it to result set
     * Next we consider if a node's key doesn;t exist in set . in this case we traverse its' left and right subtree with this node as parent reference. cause we haven't changed its parent.
     * since we add node to result set when parent is null we are avoiding adding any subtree again in result set whose parent exist.
     *
     * @param root
     * @param to_delete
     * @return
     */
    public List<TreeNode> delNodes_recursve(TreeNode root, int[] to_delete) {
        if (to_delete == null || to_delete.length == 0) {
            List<TreeNode> temp = new ArrayList<TreeNode>();
            temp.add(root);
            return temp;
        }
        HashSet<Integer> set = new HashSet<>();
        for (Integer i : to_delete) {
            set.add(i);
        }
        List<TreeNode> res = new ArrayList<>();

        delete(root, null, set, res);

        return res;
    }

    public void delete(TreeNode root, TreeNode p, HashSet<Integer> set, List<TreeNode> res) {
        if (root == null) return;
        if (p == null && !set.contains(root.value)) {
            res.add(root);
        }

        if (set.contains(root.value)) {

            if (p != null) {
                if (p.left == root) {
                    p.left = null;
                } else {
                    p.right = null;
                }
            }

            delete(root.left, null, set, res);
            delete(root.right, null, set, res);
        } else {
            delete(root.left, root, set, res);
            delete(root.right, root, set, res);
        }
    }

    /**
     * https://leetcode.com/problems/delete-node-in-a-bst/
     *
     * @param root
     * @param key
     * @return
     */
    public Node deleteNode(Node root, int key) {
        if (root == null) return root;
        if (root.data < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.data > key) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            int min = getMin(root.right, key);
            root.data = min;
            root.right = deleteNode(root.right, min);
        }
        return root;
    }


    int getMin(Node node, int key) {
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    /**
     * print top view of a binary tree
     *
     * @param root
     */
    public void topView(Node root) {

        if (root == null) return;

        QueueO obj = new QueueO(root, 0);
        Queue<QueueO> q = new LinkedList<>();
        q.add(obj);

        TreeMap<Integer, Integer> map = new TreeMap<>();
        while (!q.isEmpty()) {
            obj = q.poll();
            if (!map.containsKey(obj.hd)) {
                map.put(obj.hd, obj.node.data);
            }

            if (obj.node.left != null) {
                q.add(new QueueO(obj.node.left, obj.hd - 1));
            }
            if (obj.node.right != null) {
                q.add(new QueueO(obj.node.right, obj.hd + 1));
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }

    }

    class QueueO {
        int hd;
        Node node;

        QueueO(Node node, int h) {
            this.node = node;
            hd = h;
        }
    }

    /**
     * https://leetcode.com/problems/sum-root-to-leaf-numbers/
     *
     * @param root
     * @return
     */
    public int sumNumbers(Node root) {
        return sum(root, 0);
    }

    public int sum(Node root, int sum) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            return sum * 10 + root.data;
        }

        return sum(root.left, sum * 10 + root.data) + sum(root.right, sum * 10 + root.data);
    }

    /**
     * https://leetcode.com/problems/distribute-coins-in-binary-tree/
     *
     * @param root
     * @return
     */
    public int distributeCoins(Node root) {
        int res[] = new int[1];
        distributeCoins(root, res);
        return res[0];
    }

    public int distributeCoins(Node root, int[] res) {
        if (root == null) return 0;
        int left = distributeCoins(root.left, res);
        int right = distributeCoins(root.right, res);
        int sum = Math.abs(left) + Math.abs(right);
        res[0] += sum;
        return root.data + left + right - 1;
    }

    /**
     * https://leetcode.com/problems/binary-tree-maximum-path-sum/
     *
     * @param root
     * @return
     */
    public int maxPathSum(Node root) {
        if (root == null) return 0;
        int[] arr = new int[1];
        arr[0] = Integer.MIN_VALUE;
        maxPathSum(root, arr);
        return arr[0];
    }

    public int maxPathSum(Node root, int[] res) {
        if (root == null) return 0;
        int left = maxPathSum(root.left, res);
        int right = maxPathSum(root.right, res);
        int max = Math.max(left, right) + root.data;
        max = Math.max(max, root.data);
        int r = max;
        max = Math.max(max, left + right + root.data);
        res[0] = Math.max(res[0], max);
        return r;
    }

    /**
     * https://leetcode.com/problems/diameter-of-binary-tree/
     *
     * @param tree
     * @return
     */
    public int diameterOfTree(ITree tree) {
        int[] res = new int[1];
        diameterOfTree(tree, res);
        return res[0];
    }

    private int diameterOfTree(ITree tree, int[] res) {
        if (tree == null) return 0;
        int left = diameterOfTree(tree.getLeftNode(), res);
        int right = diameterOfTree(tree.getRightNode(), res);
        res[0] = Math.max(res[0], left + right + 1);
        return Math.max(left, right) + 1;
    }

    /**
     * https://leetcode.com/problems/house-robber-iii/
     *
     * @param root
     * @return
     */
    public int rob(Node root) {
        return rob(root, new HashMap<>());
    }


    /**
     * https://leetcode.com/problems/house-robber-iii/
     *
     * @param root
     * @param map
     * @return
     */
    public int rob(Node root, HashMap<Node, Integer> map) {

        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);
        int val = root.data;
        if (root.left != null) {
            val += rob(root.left.left, map) + rob(root.left.right, map);
        }

        if (root.right != null) {
            val += rob(root.right.left, map) + rob(root.right.right, map);
        }

        val = Math.max(val, rob(root.left, map) + rob(root.right, map));
        map.put(root, val);
        return val;
    }

    /**
     * https://leetcode.com/problems/symmetric-tree/
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric(BinaryTree root) {
        return isSymmetric(root, root);
    }

    public static boolean isSymmetric(BinaryTree node1, BinaryTree node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null) return false;
        if (node2 == null) return false;
        if (node1.getData() != node2.getData()) return false;

        boolean val = isSymmetric((BinaryTree) node1.getLeftNode(), (BinaryTree) node2.getRightNode());
        if (!val) return false;
        else return isSymmetric((BinaryTree) node2.getLeftNode(), (BinaryTree) node1.getRightNode());
    }

    /**
     * https://leetcode.com/problems/validate-binary-search-tree/
     *
     * @param root
     * @return
     */
    public boolean isValidBST(ITree<Integer> root) {
        return isValidBST(root, null, null);
    }


    public boolean isValidBST(ITree<Integer> root, ITree<Integer> min, ITree<Integer> max) {
        if (root == null) return true;
        if (min != null && min.getData() >= root.getData()) return false;
        if (max != null && max.getData() <= root.getData()) return false;

        return isValidBST(root, min, root) && isValidBST(root, root, max);
    }

    /**
     * morris inorder traversal
     *
     * @param root root node
     * @param <T>
     */
    public static <T extends Comparable> void inOrderMorrisTraversal(ITree<T> root) {
        while (root != null) {
            if (root.getLeftNode() == null) {
                System.out.print(root.getData() + " ");
                root = root.getRightNode();
            } else {
                ITree<T> temp = root.getLeftNode();
                while (temp.getRightNode() != null && temp.getRightNode() != root) {
                    temp = temp.getRightNode();
                }
                if (temp.getRightNode() == null) {
                    temp.setRightNode(root);
                    root = root.getLeftNode();
                } else {
                    temp.setRightNode(null);
                    System.out.print(root.getData() + " ");
                    root = root.getRightNode();
                }

            }
        }
    }

    /**
     * do iterative inorder tyraversal of binary tree
     *
     * @param root
     * @param <T>
     */
    public static <T extends Comparable> void iterativeInorder(ITree<T> root) {

        if (root == null) return;
        Stack<ITree> st = new Stack<>();
        leftTraversal(root, st);
        ITree<T> node = null;
        while (!st.isEmpty()) {
            node = st.pop();
            System.out.print(node.getData() + " ");
            if (node.getRightNode() != null) {
                leftTraversal(node.getRightNode(), st);
            }
        }
    }

    /**
     * https://leetcode.com/problems/binary-tree-preorder-traversal/
     * We first visit all the left nodes of a tree and keep processing it. ie print it.
     * While doing this we also add it to stack
     * Later when we have traversed all left node. we pop element from stack and traverse right node of it in similar way
     *
     * @param root
     * @param <T>
     */
    public <T extends Comparable> void preorderTraversal(ITree<T> root) {
        Stack<ITree> st = new Stack();

        while (!st.isEmpty() || root != null) {
            if (root != null) {
                System.out.print(root.getData() + " ");
                st.push(root);
                root = root.getLeftNode();
            } else {
                root = st.pop();
                root = root.getRightNode();
            }
        }
    }

    /*
     Traverse left of a binary tree and put it in Stack
     */
    private static <T extends Comparable> void leftTraversal(ITree<T> node, Stack<ITree> st) {
        while (node != null) {
            st.push(node);
            node = node.getLeftNode();
        }
    }

    /**
     * Iteratuve post order of Binary tree using two stack
     *
     * @param tree
     * @param <T>
     */
    public static <T extends Comparable> void iterativePostOrder(ITree<T> tree) {

        Stack<ITree> stack = new Stack<>(10);
        stack.push(tree);
        Stack<ITree> stack2 = new Stack<>(10);
        ITree<T> temp;
        while (!stack.isEmpty()) {
            temp = stack.pop();
            if (temp.getLeftNode() != null) {
                stack.push(temp.getLeftNode());
            }
            if (temp.getRightNode() != null) {
                stack.push(temp.getRightNode());
            }
            stack2.push(temp);
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().getData() + " ");
        }
    }

    /**
     * iterative post order using one stack
     *
     * @param node
     * @param <T>
     */
    public static <T extends Comparable> void iterativePostOrderOneStack(ITree<T> node) {

        if (node == null) return;
        Stack<ITree> stack = new Stack<>(10);
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.getLeftNode();
            }
            ITree<T> temp = stack.peek().getRightNode();
            if (temp == null) {
                temp = stack.pop();
                System.out.print(temp.getData() + " ");
                while (!stack.isEmpty() && temp == stack.peek().getRightNode()) {
                    temp = stack.pop();
                    System.out.print(temp.getData() + " ");
                }
            } else {
                node = temp;
            }

        }

    }

    /**
     * https://www.geeksforgeeks.org/count-bst-nodes-that-are-in-a-given-range/
     * Given a binary search tree and a range count number
     * of nodes between them as per inorder traversal
     *
     * @param node
     * @param min
     * @param max
     * @return
     */
    static int countNodesBetween(ITree<Integer> node, int min, int max) {
        if (node == null) {
            return 0;
        }
        if (node.getData() >= min && node.getData() <= max) {
            return 1 + countNodesBetween(node.getLeftNode(), min, max);
        } else if (node.getData() < min) {
            return countNodesBetween(node.getRightNode(), min, max);
        } else return countNodesBetween(node.getLeftNode(), min, max);
    }

    /**
     * serialize a binary tree into string
     *
     * @param root
     * @return
     */
    public String serialize(Node root) {
        StringBuilder str = new StringBuilder();
        serialize(root, str);
        return str.toString();

    }

    public void serialize(Node root, StringBuilder str) {
        if (root == null) {
            str.append("n,");
            return;
        }
        str.append(root.data);
        str.append(",");
        serialize(root.left, str);
        serialize(root.right, str);
    }

    /**
     * deserialize a binary tree from given string
     *
     * @param data
     * @return
     */
    public Node deserialize(String data) {
        String[] arr = data.split(",");
        int[] index = new int[1];
        return deserialize(arr, index);
    }

    public Node deserialize(String[] arr, int[] index) {
        if (index[0] > arr.length) {
            return null;
        }

        String str = arr[index[0]];
        index[0]++;
        if (str.equals("n")) {
            return null;
        }

        Node node = new Node(Integer.valueOf(str));
        node.left = deserialize(arr, index);
        node.right = deserialize(arr, index);
        return node;
    }

    /**
     * https://www.geeksforgeeks.org/find-minimum-depth-of-a-binary-tree/
     *
     * @param root
     * @return
     */
    int minDepth(Node root) {
        // Your code here

        Result res = new Result();
        minDepth(root, res, 1);
        return res.val;
    }

    void minDepth(Node root, Result res, int level) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res.val = Math.min(res.val, level);
            return;
        }

        minDepth(root.left, res, level + 1);

        minDepth(root.right, res, level + 1);
    }

    class Result {
        int val = Integer.MAX_VALUE;
    }

    /**
     * do a zigzag traversal of a tree and return every level as list
     *
     * @param node
     * @return
     */
    public static ArrayList<ArrayList<Integer>> zigzagLevelOrder(Node node) {
        if (node == null) return null;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(node);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            ArrayList<Integer> li = new ArrayList<>();
            while (!stack1.isEmpty()) {
                Node temp = stack1.pop();
                li.add(temp.data);
                if (temp.left != null) {
                    stack2.push(temp.left);
                }
                if (temp.right != null) {
                    stack2.push(temp.right);
                }

            }

            if (!li.isEmpty()) {
                list.add(li);
            }
            li = new ArrayList<>();

            while (!stack2.isEmpty()) {
                Node temp = stack2.pop();
                li.add(temp.data);
                if (temp.right != null) {
                    stack1.push(temp.right);
                }
                if (temp.left != null) {
                    stack1.push(temp.left);
                }
            }
            if (!li.isEmpty()) {
                list.add(li);
            }


        }

        return list;

    }

    static ArrayList<ArrayList> printSpiral(Node node) {
        if (node == null)
            return null; // NULL check

        ArrayList<ArrayList> list = new ArrayList<>();
        // Create two stacks to store alternate levels
        // For levels to be printed from right to left
        Stack<Node> s1 = new Stack<Node>();
        // For levels to be printed from left to right
        Stack<Node> s2 = new Stack<Node>();

        // Push first level to first stack 's1'
        s1.push(node);

        // Keep printing while any of the stacks has some nodes
        while (!s1.isEmpty() || !s2.isEmpty()) {
            // Print nodes of current level from s1 and push nodes of
            // next level to s2
            ArrayList<Integer> li = new ArrayList<>();
            while (!s1.isEmpty()) {
                Node temp = s1.pop();
                li.add(temp.data);

                // Note that is right is pushed before left
                if (temp.left != null)
                    s2.push(temp.left);

                if (temp.right != null)
                    s2.push(temp.right);
            }

            list.add(li);
            li = new ArrayList<>();
            // Print nodes of current level from s2 and push nodes of
            // next level to s1
            while (!s2.isEmpty()) {
                Node temp = s2.pop();
                li.add(temp.data);

                // Note that is left is pushed before right
                if (temp.right != null)
                    s1.push(temp.right);
                if (temp.left != null)
                    s1.push(temp.left);
            }
            list.add(li);
            char[] arr = new char[2];
            new String(arr);
        }
        return list;


    }

    /**
     * class used to print bottom view of a tree
     */
    static class Pair {
        int data;
        int height;
    }

    /**
     * https://www.geeksforgeeks.org/bottom-view-binary-tree/
     *
     * @param root
     */
    public static void bottomView(Node root) {
        // Your code here
        TreeMap<Integer, Pair> map = new TreeMap<>();
        printBottom(root, map, 0, 0);
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Pair key = map.get(iterator.next());
            System.out.print(key.data + " ");
        }


    }

    private static void printBottom(Node root, TreeMap<Integer, Pair> map, int height, int hDis) {
        if (root == null) return;
        if (!map.containsKey(hDis)) {
            Pair pair = new Pair();
            pair.data = root.data;
            pair.height = height;
            map.put(hDis, pair);
        } else {
            Pair pair = map.get(hDis);
            if (pair.height <= height) {
                pair.height = height;
                pair.data = root.data;
            }

        }

        printBottom(root.left, map, height + 1, hDis - 1);
        printBottom(root.right, map, height + 1, hDis + 1);
    }

    /**
     * https://leetcode.com/problems/check-completeness-of-a-binary-tree/
     *
     * @param root
     * @return
     */
    public static boolean isCompleteTree(Node root) {
        if (root == null) return true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left != null) {

                if (flag) {
                    return false;
                }
                queue.add(node.left);
            } else {
                flag = true;
            }

            if (node.right != null) {

                if (flag) {
                    return false;
                }
                queue.add(node.right);
            } else {
                flag = true;
            }

        }

        return true;
    }


    /*
    https://www.geeksforgeeks.org/find-count-of-singly-subtrees/
     */
    static int countUniValue(Node node) {
        Count count = new Count();
        countUniValue(node, count);
        return count.res;
    }

    static class Count {
        int res;
    }

    /*
    https://www.geeksforgeeks.org/find-count-of-singly-subtrees/
     */
    private static boolean countUniValue(Node node, Count count) {
        if (node == null) return true;
        boolean left = countUniValue(node.left, count);
        boolean right = countUniValue(node.right, count);
        if (left && right && (node.left == null || (node.left != null && node.data == node.left.data))
                && (node.right == null || (node.right != null && node.data == node.right.data))) {
            count.res++;
            return true;
        }
        return false;
    }

    /**
     * vertical level traversal of binary tree.
     *
     * @param root
     * @return
     */
    static public List<List<Integer>> verticalTraversal(Node root) {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        preOrder(root, 0, map);
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> li = entry.getValue();
            Collections.sort(li);
            list.add(li);
        }
        return list;
    }


    static void preOrder(Node root, int dis, TreeMap<Integer, List<Integer>> map) {
        if (root == null) return;
        List<Integer> list = map.get(dis);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(root.data);
        map.put(dis, list);
        preOrder(root.left, dis - 1, map);
        preOrder(root.right, dis + 1, map);
    }

    /**
     * check if two nodes are cousin ie at same level but not same parent
     * https://www.geeksforgeeks.org/check-two-nodes-cousins-binary-tree/
     *
     * @param root
     * @param x
     * @param y
     * @return
     */
    public static boolean ifCousin(Node root, Node x, Node y) {
        //Your Code here.
        if (root == null) return false;
        List<Node> pathx = new ArrayList<>();
        getPath(root, x, pathx);
        List<Node> pathy = new ArrayList<>();
        getPath(root, y, pathy);
        if (pathx.size() != pathy.size()) return false;
        if (pathx.isEmpty() || pathy.isEmpty()) return false;
        return pathx.get(pathx.size() - 1) != pathy.get(pathy.size() - 1);
    }

    /**
     * get path of a node from root
     *
     * @param root
     * @param x
     * @param path
     * @return
     */
    static boolean getPath(Node root, Node x, List<Node> path) {
        if (root == null) {
            return false;
        }
        if (root == x) return true;
        path.add(root);
        if (getPath(root.left, x, path)) {
            return true;
        } else if (getPath(root.right, x, path)) {
            return true;
        }
        if (path.size() > 0)
            path.remove(path.size() - 1);

        return false;
    }

    /**
     * https://leetcode.com/problems/path-sum/
     *
     * @param node
     * @param sum
     */
    static boolean hasPathSum(Node node, int sum) {
        // Your code here
        if (node == null) return false;
        if (node.left == null && node.right == null) return node.data == sum;
        return hasPathSum(node.left, sum - node.data) || hasPathSum(node.right, sum - node.data);

    }


    /**
     * find all path from root to leaf which equals a given sum
     * https://leetcode.com/problems/path-sum-ii/
     *
     * @param root
     * @param sum
     * @return
     */
    public static List<List<Integer>> pathSum(Node root, int sum) {

        List<List<Integer>> res = new ArrayList();
        List<Integer> list = new ArrayList();
        pathSum(root, sum, res, list);
        return res;
    }

    public static void pathSum(Node root, int sum, List<List<Integer>> res, List<Integer> list) {

        if (root == null) return;

        if (root.left == null && root.right == null) {
            if (sum == root.data) {
                list.add(root.data);
                res.add(new ArrayList(list));
                list.remove(list.size() - 1);
            } else {
                return;
            }
        }

        list.add(root.data);
        pathSum(root.left, sum - root.data, res, list);
        pathSum(root.right, sum - root.data, res, list);
        list.remove(list.size() - 1);
    }

    /**
     * https://leetcode.com/problems/path-sum-iii/
     *
     * @param root
     * @param sum
     * @return
     */
    public static int pathSumAll(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        return pathSum(root, map, sum, 0);
    }

    private static int pathSum(TreeNode root, HashMap<Integer, Integer> map, int target, int runningSum) {

        if (root == null) return 0;

        runningSum += root.value;
        int sum = runningSum - target;
        int total = map.getOrDefault(sum, 0);
        if (runningSum == target) total++;
        map.put(runningSum, map.getOrDefault(runningSum, 0) + 1);
        total += pathSum(root.left, map, target, runningSum);
        total += pathSum(root.right, map, target, runningSum);
        map.put(runningSum, map.getOrDefault(runningSum, 0) - 1);
        return total;
    }


    public static int[][] levelOrder(Node A) {

        int i = 0;
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        doTraversal(A, lists, 0);
        int arr[][] = new int[lists.size()][];
        for (ArrayList<Integer> list : lists) {
            int[] temp = new int[list.size()];
            int k = 0;
            for (Integer integer : list) {
                temp[k] = integer;
                k++;
            }
            arr[i] = temp;
            i++;
        }

        return arr;
    }

    private static void doTraversal(Node a, ArrayList<ArrayList<Integer>> lists, int depth) {
        if (a == null) return;
        if (depth == lists.size()) {
            ArrayList<Integer> temp = new ArrayList<>();
            lists.add(temp);
        }
        ArrayList<Integer> temp = lists.get(depth);
        temp.add(a.data);
        doTraversal(a.left, lists, depth + 1);
        doTraversal(a.right, lists, depth + 1);
    }

}

class Node {
    int data;
    Node left;
    Node right;
    Node next;

    Node(int x) {
        data = x;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                " next = " + (next != null ? next.data : null) +
                '}';
    }
}


/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 * Time O(1) on average, O(n) worst case
 * Space O(h)
 */
class BSTIterator {

    Stack<BinarySearchTree> stack;

    public BSTIterator(BinarySearchTree<Integer> root) {
        stack = new Stack<>();
        iterate(root, stack);

    }

    void iterate(BinarySearchTree root, Stack<BinarySearchTree> stack) {

        while (root != null) {
            stack.push(root);
            root = (BinarySearchTree) root.getLeftNode();
        }

    }

    /**
     * @return the next smallest number
     */
    public int next() {
        BinarySearchTree<Integer> root = stack.pop();
        if (root.getRightNode() != null) {
            iterate((BinarySearchTree) root.getRightNode(), stack);
        }

        return root.getData();

    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}