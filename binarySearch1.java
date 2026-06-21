public class binarySearch1 {
    public boolean isPresent_Linear(int []arr,int key)
    {
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==key)
                return true;
        }
        return false;
    }

    public boolean isPresent_binary(int []arr,int key)
    {
        int l = 0;
        int r = arr.length-1;
        while(l<=r)
        {
            int mid = (l+r)/2;
            if(arr[mid]==key)
                return true;
            else if(arr[mid]>key)
                r= mid-1;
            else
                l=mid+1;
        }
        return false;
    }

    public int firstOcc(int []arr,int key)
    {
        int l = 0;
        int r = arr.length-1;
        while(l<=r)
        {
            int mid = (l+r)/2;
            if( (arr[mid]==key) && (mid==0 || arr[mid-1]!=key) )
                return mid;
            else if(arr[mid]<key)
                l = mid+1;
            else
                r=mid-1;
        }
        return -1;
    }
    public int lastOcc(int []arr,int key)
    {
        int l = 0;
        int r = arr.length-1;
        while(l<=r)
        {
            int mid = (l+r)/2;
            if( (arr[mid]==key) && (mid==arr.length-1 || arr[mid+1]!=key) )
                return mid;
            else if(arr[mid]>key)
                r=mid-1;
            else
                l=mid+1;
        }
        return -1;
    }

    public int countOcc(int []arr,int key)
    {
        int lastOcc = lastOcc(arr,key);
        int firstOcc = firstOcc(arr,key);
        if(lastOcc ==-1 || firstOcc==-1)
            return 0;
        return lastOcc-firstOcc+1;
    }
    public int[] searchRange(int[] nums, int target) {
        int []ans = new int[2];

        ans[0]=firstOcc(nums,target);
        ans[1]=lastOcc(nums,target);
        return ans;
    }

    public int searchInsert(int[] arr, int target) 
    {
        int low = 0;
        int high = arr.length-1;

        while(low<=high)
        {
            int mid = (low+high)/2;

            if(arr[mid]==target)
                return mid;
            else if(arr[mid]>target)
                high = mid-1;
            else
                low = mid+1;
        }
        return low;
    }


    //Log(N)
    //O(1)
    public int findMin(int[] arr) 
    {

        int l = 0;
        int h = arr.length-1;

        while(l<h)
        {
            int mid = (l+h)/2;
            if(arr[mid]>arr[h])
                l=mid+1;
            else  // arr[h]>arr[mid]
                h=mid;
        }

        return arr[h];
    }

    public int noOfRotation(int[] arr) 
    {
        int l = 0;
        int h = arr.length-1;
        while(l<h)
        {
            int mid = (l+h)/2;
            if(arr[mid]>arr[h])
                l=mid+1;
            else  // arr[h]>arr[mid]
                h=mid;
        }
        return h;
    }
    public int binarySearch(int []arr,int tar,int s,int e)
    {
        while(s<=e)
        {
            int mid = (s+e)/2;

            if(arr[mid]==tar)
            {
                return mid;
            }
            else if(arr[mid]>tar)
            {
                e=mid-1;
            }
            else
                s=mid+1;
        }
        return -1;
    }
    public int searchInRSA(int []arr,int tar)
    {
        int idxOfMinEle = noOfRotation(arr);

        int ans1 = binarySearch(arr,tar,0,idxOfMinEle-1);

        if(ans1==-1)
        {
            return binarySearch(arr,tar,idxOfMinEle,arr.length-1);
        }
        else
            return ans1;
    }
    
}
