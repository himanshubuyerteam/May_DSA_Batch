class NumArray {
    int []arr;
    int []psum;
    public NumArray(int[] nums) {
        arr = nums;
        psum = new int[arr.length];
        psum[0]=arr[0];
        for(int i=1;i<arr.length;i++)
        {
            psum[i]=psum[i-1]+arr[i];
        }
    }
    
    public int sumRange(int left, int right) {
        if(left == 0)
            return psum[right];
        else
            return psum[right]-psum[left-1];
    }
}

public int pivotIndex(int[] nums) 
{
    int n = nums.length;
    int []psum = new int[n];
    psum[0]=nums[0];
    for(int i=1;i<n;i++)
        psum[i]=psum[i-1]+nums[i];
    
    for(int k=0;k<n;k++)
    {
        if(k==0)
        {
            int lsum = 0;
            int rsum = psum[n-1]-psum[0];
            if(lsum == rsum)
                return k;
        }
        else
        {
            int lsum = psum[k-1];
            int rsum = psum[n-1]-psum[k];
            if(lsum == rsum)
                return k;
        }
    }
    return -1;
}


public int[] productExceptSelf(int[] arr) {
    int n  = arr.length;

    int []pp  = new int[n];
    int []sp = new int[n];

    pp[0]=arr[0];
    for(int i=1;i<n;i++)
        pp[i]=pp[i-1]*arr[i];

    sp[n-1]=arr[n-1];
    for(int i=n-2;i>=0;i--)
        sp[i]=sp[i+1]*arr[i];

    int []fans = new int [n];

    fans[0]=sp[1];
    fans[n-1]=pp[n-2];

    for(int i=1;i<n-1;i++)
    {
        fans[i] = pp[i-1]*sp[i+1];
    }
    return fans;
}


public int trap(int[] arr) {
    int n = arr.length;
    int []pmax = new int[n];
    int []smax = new int[n];

    pmax[0]=arr[0];

    for(int i=1;i<n;i++)
        pmax[i]=Math.max(pmax[i-1],arr[i]);
    
    smax[n-1]=arr[n-1];

    for(int i=n-2;i>=0;i--)
        smax[i]=Math.max(smax[i+1],arr[i]);

    int water = 0;
    for(int i=0;i<n;i++)
    {
        int boundary = Math.min(pmax[i],smax[i]);
        water+=boundary-arr[i];
    }
    return water;   
}

public int trap_optimizeVersion(int[] arr) {
    int n = arr.length;
    int l = 0;
    int r = n-1;

    int lm = 0;
    int rm = 0;
    int water = 0;
    while(l<r)
    {
        lm = Math.max(lm,arr[l]);
        rm = Math.max(rm,arr[r]);

        if(rm>lm)
        {
            water += lm-arr[l];
            l++;
        }
        else
        {
            water+=rm-arr[r];
            r--;
        }
    }
    return water;
}