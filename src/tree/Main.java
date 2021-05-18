package tree;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    int[] preorder;
    HashMap<Integer, Integer> map = new HashMap<>();

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            System.out.println();
            map.put(inorder[i], i);
        }
        return recurr(0, 0, inorder.length - 1);
    }

    TreeNode recurr(int pre_root, int in_left, int in_right) {
        if (in_left > in_right) {
            return null;
        }
        TreeNode res = new TreeNode(preorder[pre_root]);
        int index = map.get(preorder[pre_root]);
        res.left = recurr(pre_root + 1, in_left, index - 1);
        res.right = recurr(pre_root + (index - in_left) + 1, index + 1, in_right);
        return res;
    }

    public TreeNode get_first_head(TreeNode A, TreeNode B) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(A);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.val == B.val) {
                return cur;
            }
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return new TreeNode(-1);
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null && B != null) {
            return false;
        }
        if (B == null) {
            return true;
        }
        if (get_first_head(A, B).val == -1) {
            return false;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(get_first_head(A, B));
        queue2.add(B);
        while (!queue2.isEmpty()) {
            if (queue1.isEmpty()) {
                return false;
            }
            TreeNode cur1 = queue1.poll();
            TreeNode cur2 = queue2.poll();
            if (cur1.val != cur2.val) {
                return false;
            }
            if (cur1.left != null) {
                queue1.add(cur1.left);
            }
            if (cur1.right != null) {
                queue1.add(cur1.right);
            }
            if (cur2.left != null) {
                queue2.add(cur2.left);
            }
            if (cur2.right != null) {
                queue2.add(cur2.right);
            }
        }
        return true;
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        } else {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();
                if (cur.right != null) stack.add(cur.right);
                if (cur.left != null) stack.add(cur.left);
                TreeNode temp = cur.right;
                cur.right = cur.left;
                cur.left = temp;
            }
            return root;
        }
    }

    public TreeNode mirrorTree_2(TreeNode root) {
        if (root == null) {
            return null;
        } else {
            TreeNode temp = root.left;
            root.left = mirrorTree_2(root.right);
            root.right = mirrorTree_2(temp);
            return root;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return process(root.left, root.right);
        }
    }

    public boolean process(TreeNode L, TreeNode R) {
        if (L == null && R == null) {
            return true;
        }
        if (L == null || R == null || L.val != R.val) {
            return false;
        }
        return process(L.right, R.left) && process(L.left, R.right);
    }

    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return null;
        } else {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            ArrayList<Integer> temp = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                temp.add(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            int[] arr = new int[temp.size()];
            for (int i = 0; i < temp.size(); i++) {
                arr[i] = temp.get(i);
            }
            return arr;
        }
    }

    HashMap<Integer, ArrayList<Integer>> hashmap;

    private List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        } else {
            hashmap = new HashMap<>();
            process(root, 0);
            System.out.println(hashmap.entrySet());
            List<List<Integer>> res = new ArrayList<>();
            Object[] keySet = hashmap.keySet().toArray();
            for (Object key : keySet) {
                res.add(hashmap.get(key));
            }
        }
    }

    public void process(TreeNode root, int index) {
        if (!hashmap.containsKey(index)) {
            hashmap.put(index, new ArrayList<>());
        }
        hashmap.get(index).add(root.val);
        process(root.left, index + 1);
        process(root.right, index + 1);
    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        } else {
            LinkedHashMap<TreeNode, Integer> map = new LinkedHashMap<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            Integer level = 0;
            map.put(root, level);
//            ArrayList<Integer> temp = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                Integer cur_level = map.get(cur);
                if (cur.left != null) {
                    queue.add(cur.left);
                    map.put(cur.left, cur_level + 1);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                    map.put(cur.right, cur_level + 1);
                }
            }
            Integer max = Integer.MIN_VALUE;
            for (Integer value : map.values()) {
                max = value > max ? value : max;
            }
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i <= max; i++) {
                res.add(new ArrayList<Integer>());
            }
            for (TreeNode j : map.keySet()) {
                res.get(map.get(j)).add(j.val);
            }
            for (Integer i = 0; i <= max; i++) {
                if (i % 2 != 0) {
                    Stack<Integer> s = new Stack<>();
                    for (Integer j : res.get(i)) {
                        s.push(j);
                    }
                    Integer pointer = 0;
                    while (!s.isEmpty()) {
                        res.get(i).set(pointer, s.pop());
                        pointer++;
                    }
                }
            }
            return res;
        }
    }

        public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            LinkedHashMap<TreeNode, Integer> map = new LinkedHashMap<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            Integer level = 0;
            map.put(root, level);
//            ArrayList<Integer> temp = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                Integer cur_level = map.get(cur);
                if (cur.left != null) {
                    queue.add(cur.left);
                    map.put(cur.left, cur_level + 1);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                    map.put(cur.right, cur_level + 1);
                }
            }
            Integer max = Integer.MIN_VALUE;
            for (Integer value : map.values()) {
                max = value > max ? value : max;
            }
            return max;
        }
    }
    public void get_path(TreeNode root, List<Integer> res, HashSet<TreeNode> set){
        if(root.left == null && root.right == null){set.add(root); res.add(root.val); return;}
        res.add(root.val);
        if(root.left!=null && !set.contains(root.left)){get_path(root.left, res, set);}
        else if()
        if(root.right!=null){get_path(root.right, res, set);}
    }
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        ArrayList<Integer> res = new ArrayList<>();
        ArrayList<List<Integer>> real_res = new ArrayList<>();
        HashSet<TreeNode> set = new HashSet<>();
        get_path(root,res);
        System.out.println(res);
        return real_res;
    }
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> temp = new LinkedList<>();
        temp.add(root);
        HashMap<TreeNode, ArrayList<Integer>> hash_path = new HashMap<>();
        HashMap<TreeNode, Integer> hash_sum = new HashMap<>();
        HashSet<TreeNode> leaf_set = new HashSet<>();
        ArrayList<Integer> path_list = new ArrayList<>(root.val);
        hash_path.put(root, path_list);
        hash_sum.put(root, root.val);
        TreeNode temp_node = new TreeNode(-1);
        while (!temp.isEmpty()) {
            temp_node = temp.poll();
            if (temp_node.left != null) {
                temp.add(temp_node.left);
                int sum = hash_sum.get(temp_node) + temp_node.left.val;
                ArrayList<Integer> temp1 = new ArrayList<>(hash_path.get(temp_node));
                temp1.add(temp_node.left.val);
                hash_path.put(temp_node.left, temp1);
                hash_sum.put(temp_node.left, sum);
            }
            if (temp_node.right != null) {
                temp.add(temp_node.right);
                int sum = hash_sum.get(temp_node) + temp_node.right.val;
                ArrayList<Integer> temp1 = new ArrayList<>(hash_path.get(temp_node));
                temp1.add(temp_node.right.val);
                hash_path.put(temp_node.right, temp1);
                hash_sum.put(temp_node.right, sum);
            }
            if (temp_node.left == null && temp_node.right == null) {
                leaf_set.add(temp_node);
            }
        }
        System.out.println(hash_path.entrySet());
        System.out.println(hash_sum.entrySet());
        for (TreeNode i : leaf_set) {
            if (hash_sum.get(i) == target) {
                res.add(hash_path.get(i));
            }
        }
        return res;
    }

    public class Codec {
        public int maxDepth(TreeNode root) {
            if(root == null) return 0;
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null){return null;}
            else{
                int depth = maxDepth(root);
                HashMap<TreeNode, Integer> depth_map = new HashMap<>();
                Queue<TreeNode> queue = new LinkedList<>();
                ArrayList<String> res = new ArrayList();
                queue.add(root);
                depth_map.put(root, 1);
                int cur_depth = 1;
                while(!queue.isEmpty()){
                    TreeNode temp = queue.poll();
                    cur_depth = depth_map.get(temp);
                    if(temp.val!= Integer.MIN_VALUE){
                        res.add(Integer.toString(temp.val));
                    }
                    else{
                        res.add("null");
                    }
                    if(temp.left!= null){
                        queue.add(temp.left);
                        depth_map.put(temp.left, cur_depth+1);
                    }
                    else if(cur_depth!=depth){
                        TreeNode temp_node = new TreeNode(Integer.MIN_VALUE);
                        queue.add(temp_node);
                        depth_map.put(temp_node, depth);}
                    else if(cur_depth == depth){
                        if(temp.val!= Integer.MIN_VALUE){
                            TreeNode temp_node = new TreeNode(Integer.MIN_VALUE);
                            queue.add(temp_node);
                            depth_map.put(temp_node, depth);}
                    }
                    if(temp.right!= null){
                        queue.add(temp.right);
                        depth_map.put(temp.right, cur_depth+1);}
                    else if(cur_depth!=depth){
                        TreeNode temp_node = new TreeNode(Integer.MIN_VALUE);
                        queue.add(temp_node);
                        depth_map.put(temp_node, depth);}
                    else if(cur_depth == depth){
                        if(temp.val!= Integer.MIN_VALUE){
                            TreeNode temp_node = new TreeNode(Integer.MIN_VALUE);
                            queue.add(temp_node);
                            depth_map.put(temp_node, depth);}
                    }
                }
                // System.out.println(res);
                return res.toString().replace(" ", "");
            }
        }
        public TreeNode deserialize(String data) {
            if (data == null) {
                return null;
            } else {
                data = data.substring(1, data.length() - 1);
                String[] data_arr = data.split(",");
                System.out.println(data_arr.length);
                TreeNode res = new TreeNode(Integer.parseInt(data_arr[0]));
                Queue<TreeNode> new_queue = new LinkedList<>();
                new_queue.add(res);
                Integer i =0;
                while(!new_queue.isEmpty()){
                    TreeNode cur_node = new_queue.poll();
                    i++;
                    if(!data_arr[i].equals("null")){
                        // System.out.println("hhhhhhhhhhh");
                        cur_node.left = new TreeNode(Integer.parseInt(data_arr[i]));
                        new_queue.add(cur_node.left);
                    }
                    i++;
                    if(!data_arr[i].equals("null")){
                        // System.out.println("hhhhhhhhhhh");
                        cur_node.right = new TreeNode(Integer.parseInt(data_arr[i]));
                        new_queue.add(cur_node.right);
                    }
                }
                return res;
            }
        }

    }
    public int kthLargest(TreeNode root, int k) {
        if(root!= null){
            TreeSet<Integer> treeSet = new TreeSet<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()){
                TreeNode cur_node = queue.poll();
                treeSet.add(cur_node.val);
                if(cur_node.left!=null){
                    queue.add(cur_node.left);
                }
                if(cur_node.right!=null){
                    queue.add(cur_node.right);
                }
            }
            ArrayList<Integer> temp = new ArrayList<>(treeSet);
            return temp.get(temp.size()-k);
        }
        else{return 0;}
    }
    public int depth(TreeNode root){
        if(root == null){return 0;}
        return Math.max(depth(root.left), depth(root.right))+1;
    }

    public boolean isBalanced(TreeNode root) {
        if(root == null){return true;}
        return Math.abs(depth(root.left) - depth(root.right))<=1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

    }
}
