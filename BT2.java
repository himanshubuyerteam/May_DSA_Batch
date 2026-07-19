import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.TreeNode;

import org.w3c.dom.Node;

public class BT2 {
    public TreeNode build(int []inorder,int isi,int iei,int []postorder,int psi,int pei)
    {
        if(psi>pei || isi>iei)
        {
            System.out.println("Inside Null Case"+psi+" "+pei);
            System.out.println("Inside Null Case"+isi+" "+iei);
            return null;
        }
        TreeNode root = new TreeNode(postorder[pei]);
        int idx = -1;
        for(int i=0;i<inorder.length;i++)
        {
            if(inorder[i]==root.val)
            {
                idx = i;
            }
        }
        int leftCount = idx-isi;
        root.left= build(inorder,isi,idx-1,postorder,psi,psi+leftCount-1);
        root.right = build(inorder,idx+1,iei,postorder,psi+leftCount,pei-1);
        return root;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }

    public TreeNode build(int []inorder,int isi,int iei,int []preorder,int psi,int pei)
    {
        if(isi>iei || psi>pei)
            return null;
        TreeNode root = new TreeNode(preorder[psi]);
        int idx = -1;
        for(int i=0;i<inorder.length;i++)
        {
            if(inorder[i]==root.val)
            {
                idx = i; 
            }
        }
        int leftCount = idx - isi;

        root.left = build(inorder,isi,idx-1,preorder,psi+1,psi+leftCount);
        root.right = build(inorder,idx+1,iei,preorder,psi+leftCount+1,pei);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(inorder,0,inorder.length-1,preorder,0,preorder.length-1);
    }

    List<Integer>ans;
    public void preorder(TreeNode root)
    {
        if(root==null)
            return;
        ans.add(root.val);
        preorder(root.left);
        preorder(root.right);
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        ans = new ArrayList<>();
        preorder(root);
        return ans;
    }

    List<Integer>ans;
    public void inorder(TreeNode root)
    {
        if(root==null)
            return;
        inorder(root.left);
        ans.add(root.val);
        inorder(root.right);
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        ans = new ArrayList<>();
        inorder(root);
        return ans;
    }

    List<Integer>ans;
    public void postorder(TreeNode root)
    {
        if(root==null)
            return;
        postorder(root.left);
        postorder(root.right);
        ans.add(root.val);
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        ans = new ArrayList<>();
        postorder(root);
        return ans;
    }

    //Single List
    public ArrayList<Integer> levelOrder(Node root) {
        // code here
        LinkedList<Node> q = new LinkedList<>();
        q.add(root);
        ArrayList<Integer>ans = new ArrayList<>();
        while(q.size()>0)
        {
            Node fnt = q.remove();
            ans.add(fnt.data);
            if(fnt.left!=null)
                q.add(fnt.left);
            if(fnt.right!=null)
                q.add(fnt.right);
        }
        return ans;
    }

    //Nested List
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> fans = new ArrayList<>();
        LinkedList<TreeNode>q = new LinkedList<>();
        if(root==null)
            return fans;

        q.add(root);

        while(q.size()>0)
        {
            int size = q.size();
            List<Integer>smallans = new ArrayList<>();
            while(size-->0)
            {
                TreeNode fnt = q.remove();
                smallans.add(fnt.val);
                if(fnt.left!=null)
                    q.add(fnt.left);
                if(fnt.right!=null)
                    q.add(fnt.right);
            }
            fans.add(new ArrayList<>(smallans));
        }
        return fans;
    }
    public List<Integer> rightView(TreeNode root) {
        List<List<Integer>>lo = levelOrder(root);
        List<Integer>rv = new ArrayList<>();

        for(List<Integer>eachrow : lo)
        {
            rv.add(eachrow.get(eachrow.size()-1));
        }
        return rv;
    }

    public ArrayList<Integer> leftView(Node root) {
        List<List<Integer>>lo = levelOrder(root);
        ArrayList<Integer>lv = new ArrayList<>();

        for(List<Integer>eachrow : lo)
        {
            lv.add(eachrow.get(0));
        }
        return lv;
    }
}
