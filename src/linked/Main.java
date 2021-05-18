package linked;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.concurrent.CompletionException;

// =浅复制， 如果是变量则复制一个新的内存地址存放， 如果是class则用指针指向同一内存地址
public class Main {
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value = data;
        }
    }

    class Complex_Node {
        int val;
        Complex_Node next;
        Complex_Node random;

        public Complex_Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
     public static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
      }

    /***
     * 链表反转
     * @param head
     * @return
     */
      public static int[] get_reverse_arr(ListNode head){
        int length = 0;
        Stack<ListNode> stack = new Stack<ListNode>();
        while(head != null){
            stack.push(head);
            head = head.next;
            length+=1;
        }
        int[] arr1 = new int[length];
        for(int i =0; i<length; i++){
            arr1[i] = stack.pop().val;
        }
        return arr1;
      }
      public static int get_list_length(ListNode head){
          int length = 0;
          ListNode temp = head;
          while(temp != null){
              length++;
              temp = temp.next;
          }
          return length;
      }

    /**
     * 得到倒数k个组成的链表
     * @param head
     * @param k
     * @return
     */
     public static ListNode get_k_end(ListNode head, int k) {
         int length = get_list_length(head);
         if (k == length) {
             return head;
         } else {
             int counter = 0;
             ListNode res = new ListNode(0);
             while (head != null) {
                 head = head.next;
                 counter++;
                 if (counter == length - k) {
                     res = head;
                     break;
                 }
             }
             return res;
         }
     }
     public static ListNode get_reverse_listnode(ListNode head){
         int length = 0;
         ListNode res = head;
         Stack<ListNode> stack = new Stack<ListNode>();
         while(head != null){
                 stack.push(head);
                 head = head.next;
                 length+=1;
             }
         stack.pop();
         ListNode temp = res;
         for(int i =0; i<length; i++){
                 res.val = stack.pop().val;
                 res = res.next;
             }
         return temp;
         }
    /***
     * 判断回文传统方法
     * @param head
     * @return
     */
    public static boolean isParr(Node head){
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while(head != null){
            if (head.value != stack.pop().value){return false;}
            head = head.next;
        }
        return true;
    }

    /**
     * 判断回文快慢指针
     * @param head
     * @return
     */
    public static boolean isParr_slow_quick(Node head){
        Node pointer1 = head;
        Node pointer2 = head;
        Node pointer3 = null;
        while(pointer1.next.next != null && pointer2.next != null){
            pointer1 = pointer1.next.next;
            pointer2 = pointer2.next;
        }
        pointer1 = pointer2.next;
        pointer2.next = null;
        while(pointer1 != null){
            pointer3 = pointer1.next;
            pointer1.next = pointer2;
            pointer2 = pointer1;
            pointer1 = pointer3;
        }
        boolean res = true;
        pointer3 = pointer2;
        pointer1 = head;
        while(pointer2!= null && pointer1!= null){
            if(pointer2.value != pointer1.value){
                res = false;
                break;
            }
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        pointer2 = pointer3.next;
        pointer3.next = null;
        while(pointer2 != null){
            pointer1 = pointer2.next;
            pointer2.next = pointer3;
            pointer3 = pointer2;
            pointer2 = pointer1;
        }
        return res;
    }
    public static ListNode combine_two(ListNode head1, ListNode head2){
        ListNode res = new ListNode(-1), temp = res;
        while(head1 != null && head2 != null) {
            if (head1.val > head2.val) {
                temp.next = head2;
                head2 = head2.next;
                temp = temp.next;
            } else {
                temp.next = head1;
                head1 = head1.next;
                temp = temp.next;
            }
        }
        if(head1 == null){
            temp.next = head2;
        }
        else{
            temp.next = head1;
        }
        return res.next;
    }

    public Complex_Node copyRandomList(Complex_Node head) {
        if (head == null){return null;}
        HashMap<Complex_Node, Complex_Node> Sites = new HashMap<>();
        Complex_Node temp = head;
        while(temp!= null){
            Sites.put(temp, new Complex_Node(temp.val));
            temp = temp.next;
        }
        temp = head;
        while (temp!=null){
            Sites.get(temp).next = Sites.get(temp.next);
            Sites.get(temp).random = Sites.get(temp.random);
            temp = temp.next;
        }
        return Sites.get(head);
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> last = new HashSet<>();
        while(headA!= null){
            last.add(headA);
            headA = headA.next;
        }
        while(headB!= null){
            if (last.contains(headB)) {
                return headB;
            }
            headB = headB.next;
            }
        return null;
        }
    public ListNode deleteNode(ListNode head, int val) {
        if(head == null) return null;
        ListNode help = head;
        ArrayList<Integer> temp = new ArrayList<>();
        while(help!= null){
            temp.add(help.val);
            help = help.next;
        }
        if(temp.contains(val)){
            Integer A = val;
            temp.remove(A);
            int i = 0;
            ListNode res = new ListNode(temp.get(i));
            head.next = res;
            while(i!= temp.size()-1){
                i++;
                ListNode temp1 = new ListNode(temp.get(i));
                res.next= temp1;
                res = res.next;
            }
            return head.next;
        }
        else{return null;}
    }
    public ListNode deleteNode2(ListNode head, int val) {
        if(head == null){return null;}
        ListNode temp = head;
        while(temp!= null){
            if(temp.val == val){
                temp = temp.next;
                return temp;
            }
            if(temp.next.val == val){
                temp.next = temp.next.next;
                return head;
            }
            else{
                temp = temp.next;
            }
        }
        return null;
    }
    }



