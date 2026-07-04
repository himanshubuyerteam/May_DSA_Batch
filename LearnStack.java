import java.util.ArrayList;
import java.util.Stack;

class LearnStack{

    public boolean isValid(String s) {
        Stack<Character>st = new Stack<>();

        for(int i=0;i<s.length();i++)
        {
            char ch = s.charAt(i);

            if(ch=='{' || ch=='[' || ch=='(')
                st.push(ch);
            else  //Closing Bracket
            {
                if(st.size()==0)
                    return false;
                else
                {
                    char ch2 = st.peek();
                    if(ch=='}' && ch2=='{')
                        st.pop();
                    else if(ch==']' && ch2=='[')
                        st.pop();
                    else if(ch==')' && ch2=='(')
                        st.pop();
                    else
                        return false;
                }
            }
        }

        if(st.size()==0)
            return true;
        return false;
    }
    public int[] NGOR(int []arr)
    {
        Stack<Integer> st = new Stack<>();
        int []ans = new int[arr.length];
        for(int i = arr.length-1;i>=0;i--)
        {
            //REMOVE ALL SMALLER ELEMENT
            while(st.size()>0 && st.peek()<=arr[i])
                st.pop();
            if(st.size()==0)
                ans[i]=-1;
            else
                ans[i]=st.peek();
            st.push(arr[i]);
        }
        return ans;
    }

    public int[] NSOR(int []arr)
    {
        Stack<Integer> st = new Stack<>();
        int []ans = new int[arr.length];
        for(int i = arr.length-1;i>=0;i--)
        {
            //REMOVE ALL GREATER ELEMENT
            while(st.size()>0 && st.peek()>=arr[i])
                st.pop();
            if(st.size()==0)
                ans[i]=-1;
            else
                ans[i]=st.peek();
            st.push(arr[i]);
        }
        return ans;
    }

    public int[] NSOL(int []arr)
    {
        Stack<Integer> st = new Stack<>();
        int []ans = new int[arr.length];
        // for(int i = arr.length-1;i>=0;i--)
        for(int i = 0;i<arr.length;i++)
        {
            //REMOVE ALL GREATER ELEMENT
            while(st.size()>0 && st.peek()>=arr[i])
                st.pop();
            if(st.size()==0)
                ans[i]=-1;
            else
                ans[i]=st.peek();
            st.push(arr[i]);
        }
        return ans;
    }
    // NGOR NGOL NSOL NSOR VV IMPORTANT
    public ArrayList<Integer> calculateSpan(int[] arr) {
        // code here
        
        ArrayList<Integer>fans = new ArrayList<>();
        Stack<Integer>st = new Stack<>();
        
        int []ans = new int[arr.length];
        
        //NGOL
        for(int i=0;i<arr.length;i++)
        {
            //Smaller Element Remove
            while(st.size()>0 && arr[i]>=arr[st.peek()])
                st.pop();
            if(st.size()==0)
                ans[i]=-1;
            else
                ans[i]=st.peek();
            st.push(i);
        }
        
        for(int i=0;i<ans.length;i++)
        {
            fans.add(i-ans[i]);
        }
        return fans;
        
    }

    public int[] NSOL_helper(int []arr)
    {
        int []ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<arr.length;i++)
        {
            while(st.size()>0 && arr[i]<=arr[st.peek()])
                st.pop();
            if(st.size()==0)
                ans[i]=-1;
            else
                ans[i]=st.peek();
            st.push(i);
        }
        return ans;
    }

    public int[] NSOR_helper(int []arr)
    {
        int []ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        // for(int i=0;i<arr.length;i++)
        for(int i=arr.length-1;i>=0;i--)
        {
            while(st.size()>0 && arr[i]<=arr[st.peek()])
                st.pop();
            if(st.size()==0)
                ans[i]=arr.length;
            else
                ans[i]=st.peek();
            st.push(i);
        }
        return ans;
    }
    public int largestRectangleArea(int[] arr) {
        int []nsol = NSOL_helper(arr);
        int []nsor = NSOR_helper(arr);

        int maxArea = 0;
        for(int i=0;i<arr.length;i++)
        {
            int width = nsor[i]-nsol[i]-1;
            int height = arr[i];

            int curr_area = width*height;

            if(curr_area>maxArea)
                maxArea=curr_area;
        }
        return maxArea;
    }
    public int longestValidParentheses(String s) {

        int ob = 0;
        int cb = 0;

        int max_Score = 0;
        int curr_Score = 0;

        for(int i=0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(ch=='(')
                ob++;
            else
                cb++;
            if(ob == cb)
            {
                curr_Score = ob+cb;
            }
            max_Score = Math.max(max_Score,curr_Score);
            if(cb>ob)
            {
                cb = 0;
                ob = 0;
            }
        }

        ob = 0;
        cb = 0;

        for(int i=s.length()-1;i>=0;i--)
        {
            char ch = s.charAt(i);
            if(ch=='(')
                ob++;
            else
                cb++;
            if(ob == cb)
            {
                curr_Score = ob+cb;
            }
            max_Score = Math.max(max_Score,curr_Score);
            // if(cb>ob)
            if(ob>cb)
            {
                cb = 0;
                ob = 0;
            }
        }
        return max_Score;
        
    }

    public static void run()
    {
        Stack<Integer>st = new Stack<>();

        st.push(10);
        st.push(20);
        st.push(30);

        System.out.println(st.peek());
        System.out.println(st.pop());
        System.out.println(st.peek());
        System.out.println(st.size());

        st.clear();
        if(st.isEmpty())
        {
            System.out.println("Stack is Empty");
        }
        else
        {
            System.out.println("Stack is not Empty");
        }
        System.out.println(st.size());
    }
    public static void main(String[] args) {
        run();
    }
}