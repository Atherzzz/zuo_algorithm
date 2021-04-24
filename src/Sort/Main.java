package Sort;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static int[] swap(int num1, int num2, int[] arr1){
        arr1[num1] = arr1[num1] ^ arr1[num2];
        arr1[num2] = arr1[num1] ^ arr1[num2];
        arr1[num1] = arr1[num1] ^ arr1[num2];
        return arr1;

    }
    public static int[] bubble_sort(int[] arr1){
        for(int i = 0; i < arr1.length; i++){
            for(int j = 0; j < arr1.length - i - 1; j++){
                arr1 = arr1[j]> arr1[j+1] ? swap(j, j+1, arr1): arr1;
            }
        }
        return arr1;
    }
    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 5, 3, 4, 3, 2, 1};
        int[] arr_sort = new int[arr1.length];
        arr_sort = bubble_sort(arr1);
        for(int i = 0; i<arr_sort.length;i++)
            System.out.println(arr_sort[i]);

        }
}
