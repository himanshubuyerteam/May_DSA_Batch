class ListNode{

    int val;
    ListNode next;
}
public class LL1 {
    

    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode future = null;
        while(curr!=null)
        {
            future = curr.next;
            curr.next = prev;

            prev= curr;
            curr= future;
        }
        return prev;
    }
    //Even Case = Second Middle 
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    // Even Case = First Middle
    public ListNode middleNode_(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next!=null)
        {
            slow = slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    public boolean isPalindrome(ListNode head) {
        // Step1 Find the Middle

        ListNode middle = middleNode_(head);

        //Step2 Break into 2 LLs
        ListNode ll1 = middle.next;
        middle.next= null;

        //Step3 Reverse 2nd LL
        ListNode h1 = head;
        ListNode h2 = reverseList(ll1);

        while(h1!=null && h2!=null)
        {
            if(h1.val != h2.val)
                return false;
            h1 = h1.next;
            h2 = h2.next;
        }
        return true;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow  = head;
        ListNode fast = head;

        while(n>0)
        {
            fast = fast.next;
            n--;
        }
        if(fast==null)
            return head.next;
        while(fast.next!=null)
        {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    public void reorderList(ListNode head) {
        ListNode middle = middleNode(head);

        ListNode ll2 = middle.next;
        middle.next= null;

        ListNode h1 = head;
        ListNode h2 = reverseList(ll2);
        ListNode f1;
        ListNode f2;
        while(h1!=null && h2!=null)
        {
            f1 = h1.next;
            f2= h2.next;

            h1.next = h2;
            h2.next = f1;


            h1 = f1;
            h2 = f2;
        }
    
    }
}
