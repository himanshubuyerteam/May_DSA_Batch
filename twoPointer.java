import java.util.ArrayList;
import java.util.Arrays;

public class twoPointer {
    //O(N^2)TC  
    //O(N)SC
    public int maxWater(int []arr)
    {
        ArrayList<Integer> possibleAns = new ArrayList<>();
        for(int i=0;i<arr.length;i++)
        {
            for(int j=i+1;j<arr.length;j++)
            {
                int height = Math.min(arr[i],arr[j]);
                int width = j-i;
                int water = height*width;
                possibleAns.add(water);
            }
        }
        int finalAns = possibleAns.get(0);
        for(int i=0;i<possibleAns.size();i++)
        {
            finalAns = Math.max(finalAns,possibleAns.get(i));
        }
        return finalAns;
    }


    //O(N^2)Tc
    //O(1)SC
    public int maxWater_better(int []arr)
    {
        int maxWater = 0;
        for(int i=0;i<arr.length;i++)
        {
            for(int j=i+1;j<arr.length;j++)
            {
                int height = Math.min(arr[i],arr[j]);
                int width = j-i;
                int water = height*width;
                // possibleAns.add(water);
                maxWater = Math.max(water,maxWater);
            }
        }
        return maxWater;
    }

    //O(n) TC
    //O(1) SC
    public int maxWater_best(int []arr)
    {
        int i=0;
        int j=arr.length-1;
        int maxWater = 0;
        while(i<j)
        {
            int heigth = Math.min(arr[i],arr[j]);
            int widht = j-i;
            int water = heigth*widht;
            maxWater = Math.max(maxWater,water);

            if(arr[i]>arr[j])
                j--;
            else
                i++;
        }
        return maxWater;
    }

    //O(N^2)TC
    //O(1)SC
    public boolean isTwoSumPossible(int []arr,int tar)
    {
        int n = arr.length;
        for(int i=0;i<n;i++)
        {
            for(int j=i+1;j<n;j++)
            {
                if(arr[i]+arr[j]==tar)
                    return true;
            }
        }
        return false;
    }
    //o(n) TC 
    //o(1) sc
    public boolean isTwoSumPossible_better(int []arr,int tar)
    {
        int i=0;
        int j= arr.length-1;
        while(i<j)
        {
            int csum = arr[i]+arr[j];
            if(csum==tar)
                return true;
            else if(csum>tar)
                j--;
            else
                i++;
        }
        return false;
    }

    public int [] twoSum(int []arr,int tar)
    {
        int i=0;
        int j= arr.length-1;
        int []ans = new int [2];
        Arrays.fill(ans, -1);
        while(i<j)
        {
            int csum = arr[i]+arr[j];
            if(csum==tar)
            {
                ans[0]=i+1;
                ans[1]=j+1;
                return ans;
            }
            else if(csum>tar)
                j--;
            else
                i++;
        }
        return ans;
    }

    public void sort01(int []arr)
    {
        int count_of_zero = 0;
        int count_of_one = 0;
        int n = arr.length;
        for(int i=0;i<n;i++)
        {
            if(arr[i]==1)
                count_of_one++;
            else
                count_of_zero++;
        }
        int idx=0;
        for(int i=0;i<count_of_zero;i++)
            arr[idx++]=0;
        for(int i=0;i<count_of_one;i++)
            arr[idx++]=1;
        return;
    }
    public void swap(int []arr,int i,int j)
    {
        int temp =arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    //o(n)tc 
    //o(1)SC
    public void sort01_better(int []arr)
    {
        int i=0;
        int j=0;
        while(i<arr.length)
        {
            if(arr[i]==0)
            {
                swap(arr,i,j);
                i++;
                j++;
            }
            else
            {
                i++;
            }
        }
    }
    //o(n)tc 
    // //o(1)SC
    public void moveZeroesToEnd(int []arr)
    {
        int i=0;
        int j=0;
        int n =arr.length;
        while(i<n)
        {
            if(arr[i]!=0)
            {
                swap(arr,i,j);
                j++;
            }
            i++;
        }
    }


    public void reverse(int []arr)
    {
        int i=0;
        int j=arr.length-1;
        while(i<j)
        {
            swap(arr,i,j);
            i++;
            j--;
        }
    }

    public void reverse(int []arr,int si,int ei)
    {
        int i=si;
        int j=ei;
        while(i<j)
        {
            swap(arr,i,j);
            i++;
            j--;
        }
    }

    public void rotate(int []arr,int k)
    {
        int n = arr.length;
        k = k%n;
        //step1 reverse full array
        reverse(arr, 0,n-1);
        //step2 reverse first k element
        reverse(arr, 0, k-1);
        //Step3 reverse last n-k elements
        reverse(arr, k, n-1);
    }

    
}
