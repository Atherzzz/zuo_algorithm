package Sort;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    /**
     * 交换列表arr1中num1位置和num2位置的值（注意两个nun要不同，所以用之前判断一下）
     * @param num1: 第一个位置
     * @param num2：第二个位置
     * @param arr1：输入的数组
     * @return：输出改完的数组
     */
    public static int[] swap(int num1, int num2, int[] arr1){
        arr1[num1] = arr1[num1] ^ arr1[num2];
        arr1[num2] = arr1[num1] ^ arr1[num2];
        arr1[num1] = arr1[num1] ^ arr1[num2];
        return arr1;

    }

    /**
     * 冒泡排序
     * @param arr1 输入的乱序数组
     * @return输出有序数组
     */
    public static int[] bubble_sort(int[] arr1){
        for(int i = 0; i < arr1.length; i++){
            for(int j = 0; j < arr1.length - i - 1; j++){
                arr1 = arr1[j]> arr1[j+1] ? swap(j, j+1, arr1): arr1;
            }
        }
        return arr1;
    }

    /**
     * 选择排序
     * @param arr1 输入乱序数组
     * @return 输出有序数组
     */
    public static int[] selection_sort(int[] arr1){
        for(int i =0; i < arr1.length; i++){
            int min_index = i;
            for(int j = i + 1; j< arr1.length; j++){
                min_index = arr1[j]< arr1[min_index]? j: min_index;
            }
            arr1 = i != min_index ? swap(i, min_index, arr1): arr1 ;
        }
        return arr1;
    }

    public static int[] insert_sort(int[] arr1){
        for(int i =0; i < arr1.length; i++){
            int temp = i ;
            for(int j = i; j != 0; j--){
                if(arr1[temp]< arr1[j-1]){
                    arr1 = swap(temp, j-1, arr1);
                    temp = j-1;
                }
                else{
                    break;
                }
            }
        }
        return arr1;
    }


    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 5, 3, 4, 3, 2, 1};
        arr1 = insert_sort(arr1);
        for(int i = 0; i<arr1.length;i++)
            System.out.println(arr1[i]);

        }
}
