import java.util.ArrayList;
import java.util.List;

class backTracking{
    public void helper(int sr,int sc,int dr,int dc,
                        String path,ArrayList<String>ans,int [][]maze)
    { 
        if(sr>dr || sc>dc || sr<0 || sc<0 || maze[sr][sc]==0)
            return;
        if(sr==dr && sc==dc)
        {
            ans.add(path);
            return;
        }
        maze[sr][sc]=0;
        helper(sr+1,sc,dr,dc,path+"D",ans,maze);
        helper(sr,sc-1,dr,dc,path+"L",ans,maze);
        helper(sr,sc+1,dr,dc,path+"R",ans,maze);
        helper(sr-1,sc,dr,dc,path+"U",ans,maze);
        maze[sr][sc]=1;
    }
    public ArrayList<String> ratInMaze(int[][] maze) {
        // code here
        int n = maze.length;
        int sr = 0;
        int sc = 0;
        int dr = n-1;
        int dc = n-1;
        ArrayList<String>ans = new ArrayList<>();
        helper(sr,sc,dr,dc,"",ans,maze);
        return ans;
    }

    public void swap(StringBuilder sb,int i,int j)
    {
        char temp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, temp);
    }
    public void helper_permutation(StringBuilder str,int idx,List<String>ans)
    {
        if(idx == str.length())
        {
            ans.add(str.toString());
            return;
        }
        for(int op = idx;op<str.length();op++)
        {
            swap(str,idx,op);
            helper_permutation(str, idx+1, ans);
            swap(str,idx,op);
        }
    }
    public List<String> permutation(String str)
    {
        List<String>ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder(str);
        helper_permutation(sb,0,ans);
        return ans;
    }
    public boolean isPossible (char[][]arr,int row,int col)
    {
        for(int i=0;i<row;i++)
        {
            if(arr[i][col]=='Q')
                return false;
        }
        for(int i=row-1,j=col-1;i>=0 && j>=0 ;i--,j--)
        {
            if(arr[i][j]=='Q')
                return false;
        }
        for(int i=row-1,j=col+1;i>=0 && j<arr.length;i--,j++)
        {
            if(arr[i][j]=='Q')
                return false;
        }

        return true;
    }
    public void helper_queen(int totalNoOfQueen,List<List<String>>ans, char [][]arr, int row)
    {
        if(totalNoOfQueen == row)
        {
            List<String>smallans = new ArrayList<>();
            for(int []row:arr)
            {
                smallans.add(String.valueOf(row));
            }
            ans.add(smallans);
            return;
        }
        for(int col = 0;col<totalNoOfQueen;col++)
        {
            if(isPossible(arr,col,row))
            {
                arr[row][col]='Q';
                helper_queen(totalNoOfQueen,ans,arr,row+1);
                arr[row][col]='.';
            }
        }
    }
    public List<List<String>> nqueen(int n)
    {
        List<List<String>> ans = new ArrayList<>();
        char [][]chessBoard = new char[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                chessBoard[i][j]='.';
            }
        }
        helper_queen(n,ans,chessBoard,0);
        return ans;
    }
    public static void main(String[] args) {
        
    }
}