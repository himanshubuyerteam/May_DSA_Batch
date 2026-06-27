import java.util.Arrays;

class bs2
{
    //https://www.geeksforgeeks.org/problems/square-root/1
    int floorSqrt(int n) {
        
        int left = 1;
        int right = n;
        int ans = 1;
        while(left<=right)
        {
            int mid = (left+right)/2;

            if(mid*mid == n)
                return mid;
            else if(mid*mid>n)
                right = mid-1;
            else  //  mid*mid<n
            {
                ans = mid;
                left = mid+1;
            }
        }
        return ans;
    }

    //https://leetcode.com/problems/koko-eating-bananas/
    public boolean isPossibleToEat(int []piles,int hr,int speed)
    {
        long time = 0;
        for(int i=0;i<piles.length;i++)
        {
            double time_per_pile = Math.ceil((double)piles[i]/speed);

            long time_needed = (long)Math.ceil(time_per_pile);
            time+=time_needed;
        }

        if(time>hr)
            return false;
        else
            return true;
    }
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = piles[0];
        for(int pile:piles)
            right = Math.max(right,pile);
        int possibleAns = right;
        while(left<=right)
        {
            int mid = (left+right)/2;
            if(isPossibleToEat(piles,h,mid))
            {
                possibleAns = mid;
                right = mid-1;
            }
            else
            {
                left = mid+1;
            }
        }
        return possibleAns;
    }


    public boolean isPossibleToTransfer(int []arr,int days,int beltWt)
    {
        int day = 1;
        int currWt = 0;
        for(int i=0;i<arr.length;i++)
        {
            currWt = currWt+arr[i];
            if(currWt>beltWt)
            {
                day++;
                currWt = arr[i];
            }
        }
        if(day>days)
            return false;
        return true;
    }

    //https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
    public int shipWithinDays(int[] weights, int days) {
        int left = weights[0];
        int right = 0;

        for(int x:weights)
        {
            left = Math.max(left,x);
            right +=x;
        }

        while(left<right)
        {
            int mid = (left+right)/2;

            if(!isPossibleToTransfer(weights,days,mid))
            {
                left = mid+1;
            }
            else
            {
                right= mid;
            }
        }
        return left;
    }

    public int minTime(int[] arr, int k) {
        // code here
        long left = arr[0];
        long right = 0;
        
        
        for(int x:arr)
        {
            left = Math.max(left,x);
            right +=x;
        }
        
        while(left<right)
        {
            long mid = (left+right)/2; // per painter
            
            if(!isPossibleToPaint(arr,mid,k))
            {
                left = mid+1;
            }
            else
            {
                right = mid;
            }
        }
        return (int)left;
    }
    

    //https://www.geeksforgeeks.org/problems/the-painters-partition-problem1535/1
    public boolean isPossibleToPaint(int []arr,long perPainterWork,
                                    int noofPainter)
    {
        int painter = 1;
        int painterWork = 0;
        for(int i=0;i<arr.length;i++)
        {
            painterWork+=arr[i];
            if(painterWork>perPainterWork)
            {
                painter++;
                painterWork = arr[i];
            }
        }
        if(painter>noofPainter)
            return false;
        else
            return true;
    }

    public boolean isPossbileToPlace(int []arr,int noOfCow,int dist)
    {
        int cowPlace = 1;
        int posOfCow = arr[0];
        for(int i=1;i<arr.length;i++)
        {
            if(arr[i]-posOfCow>=dist)
            {
                cowPlace ++;
                posOfCow = arr[i];
            }
        }
        return cowPlace>=noOfCow;
    }
    public int aggressiveCows(int[] stalls, int k) {
        // code here
        Arrays.sort(stalls);
        
        int n = stalls.length;
        int min = stalls[0];
        int max = stalls[n-1];
        
        int left = 1;
        int right = max-min;
        int pa = 0;
        while(left<=right)
        {
            int mid = (left+right)/2; //distance b/w any 2 Cows
            
            if(isPossbileToPlace(stalls,k,mid))
            {
                pa = mid;
                left = mid+1;
            }
            else
            {
                right = mid-1;
            }
        }
        return pa;
    }
}