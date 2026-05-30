import java.util.Arrays;

class sortingAlgo
{

    public static void bubbleSort(int []arr)
    {
        System.out.println("Before Sorting");
        System.out.println(Arrays.toString(arr));
        int noOfElement  = arr.length;
        boolean isSwapped ;
        for(int itt=1;itt<=noOfElement-1;itt++)
        {
            isSwapped = false;
            for(int j=0;j<noOfElement-itt;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    int temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]= temp;
                    isSwapped = true;
                }
            }
            if(isSwapped == false)
                break;
        }
        System.out.println("After Sorting");
        System.out.println(Arrays.toString(arr));
    }
    public static void main(String[] args) {
        int []arr = {5,9,8,2,1};
        bubbleSort(arr);
    }
}