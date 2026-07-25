class BSTNode
{
    int val;
    BSTNode right;
    BSTNode left;
}

class Question
{
    public int size(BSTNode root)
    {
        if(root==null)
            return 0;
        int lsize = size(root.left);
        int rsize = size(root.right);
        int tsize = lsize+rsize+1;
        return tsize;
    }
    public int sum(BSTNode root)
    {
        if(root==null)
            return 0;
        int lsum = sum(root.left);
        int rsum = sum(root.right);
        int tsum = lsum+rsum+root.val;
        return tsum;
    }
    public int min(BSTNode root)
    {
        while(root.left!=null)
            root=root.left;
        return root.val;
    }
    public int max(BSTNode root)
    {
        while(root.right!=null)
            root=root.right;
        return root.val;
    }
    public boolean find(BSTNode root,int key)
    {
        if(root==null)
            return false;
        else if(root.val == key)
            return true;
        else if(root.val>key)
            return find(root.left,key);
        else
            return find(root.right,key);
    }
    public BSTNode LCAOfBST(BSTNode root,int p,int q)
    {
        if(root.val>p && root.val>q)
            return LCAOfBST(root.left, p, q);
        else if(root.val<p && root.val<q)
            return LCAOfBST(root.right, p, q);
        else
            return root;
    }

    public BSTNode buildTree(int []sortedArr)
    {
        return builder(sortedArr,0,sortedArr.length-1);
    }
    public BSTNode builder(int []arr,int si,int ei)
    {
        if(si>ei)
            return null;
        int mid = (si+ei)/2;
        BSTNode root = new BSTNode(arr[mid]);

        root.left = buildTree(arr,si,mid-1);
        root.right = buildTree(arr,mid+1,ei);
        return root;
    }

    public BSTNode insertNode(BSTNode root,int insertVal)
    {
        BSTNode node = new BSTNode(insertVal);
        if(root==null)
            return node;
        BSTNode temp = root;
        while(temp!=null)
        {
            if(temp.val>insertVal)
            {
                if(temp.left==null)
                {
                    temp.left = node;
                    break;
                }
                temp = temp.left;
            }
            else
            {
                if(temp.right==null)
                {
                    temp.right = node;
                    break;
                }
                temp = temp.right;
            }
        }
        return root;
    }


    // public boolean isBST(BSTNode root)
    // {
    //     if(root.val > root.left.val && root.val<root.right.val)
    //         return true;
    //     return false;
    // }


    class dataObj
    {
        int minInTree;
        int maxInTree;
        boolean isBST;

    }
    public dataObj isBSTHelper(TreeNode root)
    {
        if(root==null)
        {
            dataObj baseCase = new dataObj();
            baseCase.minInTree = Integer.MAX_VALUE;
            baseCase.maxInTree = Integer.MIN_VALUE;
            baseCase.isBST = true;
            return baseCase;
        }
        dataObj lans = isBSTHelper(root.left);
        dataObj rans isBSTHelper(root.right);


        boolean c1 = root.val>lans.maxInTree;
        boolean c2 =root.val<rans.minInTree;
        boolean c3 = rans.isBST && lans.isBST;

        dataObj rootans = new dataObj();
        dataObj.minimumOfTree = Math.min(lans.minInTree,Math.min(root.val,rans.minInTree));
        dataObj.maximumOfTree = Math.max(lans.maxInTree,Math.max(root.val,rans.maxInTree));
        dataObj.isBST = c1 && c2 && c3;
        return rootans;
    }

    public boolean isBST(TreeNode root)
    {
        dataObj rootans = isBSTHelper(root);
        return rootans.isBST;
    }


    public TreeNode getMax(TreeNode root)
    {
        while(root.right!=null)
            root=root.right;
        return root;
    }

    public TreeNode getMin(TreeNode root)
    {
        while(root.left!=null)
            root=root.left;
        return root;
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null)
            return null;
        if(root.val>key)
            root.left = deleteNode(root.left,key);
        else if(root.val<key)
            root.right = deleteNode(root.right,key);
        else // root.val == key
        {   
            if(root.left == null || root.right==null)
            {
                if(root.left==null)
                    return root.right;
                else
                    return root.left;
            }
            else // Left is NotNull and Right is NotNull
            {
                // TreeNode replacementNode = getMax(root.left);
                TreeNode replacementNode = getMin(root.right);
                root.val = replacementNode.val;
                root.right = deleteNode(root.right,replacementNode.val);
            }
        }
        return root;
    }

}