import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.TreeNode;

public class BT1 {
    static class Node
    {
        int val;
        Node left;
        Node right;
        Node(int val)
        {
            this.val=val;
            this.left= null;
            this.right = null;
        }
    }
    public static int size(Node root)
    {
        if(root==null)
            return 0;
        int left_size = size(root.left);
        int right_size = size(root.right);
        int final_size = left_size + right_size +1;
        return final_size;
    }
    public static int sum(Node root)
    {
        if(root==null)
            return 0;
        int left_sum = sum(root.left);
        int right_sum = sum(root.right);
        int final_sum = root.val + left_sum + right_sum;
        return final_sum;
    }

    public static int maximumOfTree(Node root)
    {
        if(root==null)
            // return 0;
            return Integer.MIN_VALUE; 
        int left_max = maximumOfTree(root.left);
        int right_max = maximumOfTree(root.right);
        int final_max = Math.max(root.val,Math.max(left_max,right_max));
        return final_max; 
    }
    public static int minimumOfTree(Node root)
    {
        if(root==null)
            // return 0;
            return Integer.MAX_VALUE; 
        
        int left_min = minimumOfTree(root.left);
        int right_min = minimumOfTree(root.right);
        int final_min = Math.min(root.val,Math.min(left_min,right_min));
        return final_min; 
    }
    public static int size(GTNode root)
    {
        if(root==null)
            return 0;
        int size = 1;
        for(int i=0;i<root.children.size();i++)
        {
            int childSize =  size(root.children.get(i));
            size+=childSize;
        }
        return size;
    }

    public boolean isPresent(Node root,int key)
    {
        if(root==null)
            return false;
        boolean lans = isPresent(root.left, key);
        boolean rans = isPresent(root.right, key);

        if(lans || rans || root.val==key)
            return true;
        return false;
    }
    public boolean isPresent_Helper(Node root,int key,ArrayList<Node>path)
    {
        if(root==null)
            return false;
        boolean lans = isPresent(root.left, key);
        boolean rans = isPresent(root.right, key);
        boolean fans = lans || rans || root.val==key;
        if(fans)
            path.add(root);
        if(fans)
            return true;
        else
            return false;
    }

    class queueItem
    {
        TreeNode node;
        int dist;
        queueItem(TreeNode root,int dist)
        {
            this.node=root;
            this.dist=dist;
        }
    }
    HashMap<TreeNode,TreeNode> childToParMapping;
    public void setUpRelation(TreeNode root,TreeNode par)
    {
        if(root == null)
            return;
        if(par!=null)
            childToParMapping.put(root,par);
        setUpRelation(root.left,root);
        setUpRelation(root.right,root);
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        childToParMapping = new HashMap<>();
        setUpRelation(root,null);

        List<Integer>ans = new ArrayList<>();
        LinkedList<queueItem>q = new LinkedList<>();
        q.add(new queueItem(target,0));
        HashSet<TreeNode>vis = new HashSet<>();

        
        vis.add(target);
        while(q.size()>0)
        {
            queueItem fnt = q.remove();
            if(fnt.dist == k)
            {
                ans.add(fnt.node.val);
            }

            if(fnt.node.left!=null && vis.contains(fnt.node.left)==false)
            {
                q.add(new queueItem(fnt.node.left,fnt.dist+1));
                vis.add(fnt.node.left);
            }
            if(fnt.node.right!=null && vis.contains(fnt.node.right)==false)
            {
                q.add(new queueItem(fnt.node.right,fnt.dist+1));
                vis.add(fnt.node.right);
            }
            TreeNode par = childToParMapping.get(fnt.node);
            if(par !=null && vis.contains(par)==false)
            {
                q.add(new queueItem(par,fnt.dist+1));
                vis.add(par);
            }
        }
        return ans;
    }
    int sum = 0;
    public int helper(TreeNode root,int csum)
    {
        if(root==null)
            return 0;
        csum = csum*10+root.val;
        if(root.left == null && root.right == null)
            sum+=csum;
        helper(root.left,csum);
        helper(root.right,csum);
        return 0;
    }
    public int sumNumbers(TreeNode root) {
        helper(root,0);
        return sum;
    }
    public static void main(String[] args) {
        Node root = new Node(1);
        Node c1 = new Node(2);
        Node c2 = new Node(3);

        root.left = c1;
        root.right = c2;

        System.out.println(sum(root));
        System.out.println(size(root));
    }
}
