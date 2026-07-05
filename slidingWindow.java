public class slidingWindow {

    public int maxSubarraySum(int[] arr, int k) {
        int ans =0;
        int sum =0;
        for(int i=0;i<k;i++)
            sum+=arr[i];
        ans = sum;
        
        for(int i=k;i<arr.length;i++)
        {
            sum = sum +arr[i] - arr[i-k];
            if(sum>ans)
                ans = sum;
        }
        
        return ans; 
            
        
    }

    public double findMaxAverage(int[] arr, int k) {
        double ans =0;
        double sum =0;
        for(int i=0;i<k;i++)
            sum+=arr[i];
        ans = sum;
        
        for(int i=k;i<arr.length;i++)
        {
            sum = sum +arr[i] - arr[i-k];
            if(sum>ans)
                ans = sum;
        }
        
        return ans/k; 
    }
    
    public int minSubArrayLen(int tar, int[] arr) {
        int start = 0;
        int end = 0;
        int sum = 0;
        int min_len = Integer.MAX_VALUE;

        while(end<arr.length)
        {
            sum = sum+arr[end];

            while(sum>=tar)
            {
                int curr_len = end - start +1;
                if(curr_len<min_len)
                    min_len = curr_len;
                
                sum = sum - arr[start];
                start++;
            }
            end++;
        }

        if(min_len == Integer.MAX_VALUE)
            return 0;
        return min_len;

    }
    public String minWindow(String s, String t) {
        int s1 = s.length();
        int s2 = t.length();
        int count = s2;
        int start = 0;
        int end = 0;
        int len = Integer.MAX_VALUE;
        int startIdx = 0;

        int []freq = new int[256];

        //Step1
        for(int i=0;i<s2;i++)
        {
            char ch = t.charAt(i);
            freq[ch]++;
        }

        while(end<s1)
        {
            char ch = s.charAt(end);
            if(freq[ch]>0)
            {
                count--;
            }
            freq[ch]--;
            end++;

            while(count==0)
            {
                int curr_len = end - start;
                if(curr_len<len)
                {
                    len = curr_len;
                    startIdx=start;
                }
                char ch2 = s.charAt(start);
                if(freq[ch2]==0)
                    count++;
                start++;
                freq[ch2]++;
            }   
        }

        if(len == Integer.MAX_VALUE)
            return "";
        return s.substring(startIdx,startIdx+len);
    }
    public int lengthOfLongestSubstring(String str) {
        int s = 0;
        int e = 0;
        int []freq = new int [256];
        int maxLen = 0;
        boolean dup = false;

        while(e<str.length())
        {
            char ch = str.charAt(e);
            if(freq[ch]==1)
                dup = true;
            freq[ch]++;
            e++;

            while(dup == true)
            {
                char ch2 = str.charAt(s);
                if(freq[ch2]==2)
                {
                    dup = false;
                }
                freq[ch2]--;
                s++;
            }
            int curr_len = e-s;
            if(curr_len>maxLen)
                maxLen = curr_len;
        }
        return maxLen;
    }
}
