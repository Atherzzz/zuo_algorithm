package Heap;

import java.util.Arrays;

public class Main {
    public static int[] swap(int num1, int num2, int[] arr1){
        arr1[num1] = arr1[num1] ^ arr1[num2];
        arr1[num2] = arr1[num1] ^ arr1[num2];
        arr1[num1] = arr1[num1] ^ arr1[num2];
        return arr1;

    }

    public static void heapinsert(int[] arr1, int index){
        while(arr1[index]< arr1[(index-1)/2]){
            swap(index, (index-1)/2, arr1);
            index = (index-1)/2;
        }
    }
    public static void heapify(int[] arr1, int index, int heapsize){
        int left = 2*index + 1;
        while(left < heapsize){
            int least = 0;
            if(left+1 < heapsize){ least = arr1[left]>arr1[left+1]? left+1: left; }
            else{ least = left;}
            if(arr1[index]> arr1[least]){swap(index, least, arr1);}
            else{break;}
            index = least;
            left = 2*index + 1;
        }
    }
    public static void heap(int[] arr1, int heapsize){
        for(int i = 1; i< heapsize; i++){ heapinsert(arr1, i); }
    }
    public static int[] min_k(int[] arr1, int k) {
        int[] res = new int[k];
        heap(arr1, arr1.length);
        if(k!= arr1.length){
        for (int end = arr1.length; end != arr1.length - k; end--) {
            swap(0, end - 1, arr1);
            heapify(arr1, 0, end - 1);
        }
        for(int i : arr1){
            System.out.println(i);
        }
        res = Arrays.copyOfRange(arr1, arr1.length -k, arr1.length);
        return res;}
        else{
            for (int end = arr1.length; end != 1; end--) {
                swap(0, end - 1, arr1);
                heapify(arr1, 0, end - 1);
            }
            for(int i : arr1){
                System.out.println(i);
            }
            res = Arrays.copyOfRange(arr1, 0, arr1.length);
            return res;
        }
    }
    public static void main(String[] args) {
        int[] arr1 = {0,0,2,3,2,1,1,2,0,4};
        int k = 10;
        int[] res = min_k(arr1, k);
        for(int i : res){
            System.out.println(i);
        }
    }
}
