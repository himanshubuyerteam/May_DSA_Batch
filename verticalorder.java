import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class TreeNode
{
    int val;
    TreeNode left;
    TreeNode right;
}
public class verticalorder {
    class data
    {
        TreeNode node;
        int depth;
        data(TreeNode node,int depth)
        {
            this.node=node;
            this.depth=depth;
        }
    }
    public void width(TreeNode root,int []arr,int currVerticalOrder)
    {
        if(root==null)
            return;
        arr[0]=Math.min(arr[0],currVerticalOrder);
        arr[1]=Math.max(arr[1],currVerticalOrder);
        width(root.left, arr, currVerticalOrder-1);
        width(root.right, arr, currVerticalOrder+1);
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        int []arr = new int[2];
        List<List<Integer>>fans = new ArrayList<>();
        arr[0]=0;
        arr[1]=0;
        width(root, arr, 0);
        HashMap<Integer,ArrayList<Integer>>hm = new HashMap<>();
        int min = 0;
        int max = 0;
        ArrayDeque<data>q = new ArrayDeque<>();
        q.add(new data(root,0));

        while(q.size()>0)
        {
            data fnt = q.remove();
            min = Math.min(min,fnt.depth);
            max = Math.max(max,fnt.depth);
            if(hm.containsKey(fnt.depth))
            {
                ArrayList<Integer>currlist = hm.get(fnt.depth);
                currlist.add(fnt.node.val);
                hm.put(fnt.depth,currlist);
            }
            else
            {
                ArrayList<Integer>currList = new ArrayList<>();
                currList.add(fnt.node.val);
                hm.put(fnt.depth,currList);
            }
            if(fnt.node.left!=null)
                q.add(new data(fnt.node.left,fnt.depth-1));
            if(fnt.node.right!=null)
                q.add(new data(fnt.node.right,fnt.depth+1));
        }
        
        for(int i = arr[0];i<=arr[1];i++)
        {
            ArrayList<Integer>row = hm.get(i);
            fans.add(row);
        }
        return fans;
    }
}
