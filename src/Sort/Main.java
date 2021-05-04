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

    /**
     * 插入排序
     * @param arr1
     * @return
     */
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

    public static int[] Shell_Sort(int[] arr1){
        int gap = arr1.length;
        while(gap!=0){
            gap = gap/2;
            for(int i =gap; i < arr1.length;  i++){
                int j = i;
                while(j - gap>=0 && arr1[j] < arr1[j - gap] ){
                    swap(j, j-gap, arr1);
                    j = j - gap;
                }
                }
            }

        return  arr1;
        }


    public static int[] Merge_Sort(int[] arr1){
        process(arr1, 0, arr1.length-1);
        return arr1;
    }

    /**
     *归并排序
     * @param arr input matrix
     * @param L strat point
     * @param R end point
     * 经典的递归方法
     * 格式函数A
     *          基础项
     *          函数A
     *          函数A
     *          函数B
     */

    public static void process(int[] arr, int L, int R){
        if (L==R){
            return;
        }
        int M = L + (R-L)/2;
        process(arr, L, M);
        process(arr, M+1, R);
        merge(arr, L, M, R);
    }


    public static void  merge(int[] arr, int L, int M, int R){
        int[] help = new int[R - L + 1];
        int j = 0;
        int pointer1 = L;
        int pointer2 = M +1;
        while(pointer1 <= M && pointer2 <= R){
            help[j++] = arr[pointer1]<= arr[pointer2]? arr[pointer1++]: arr[pointer2++];}
        while( pointer2 <= R){help[j++] = arr[pointer2++];}
        while(pointer1 <= M ){help[j++] = arr[pointer1++];}
        for (int i = 0; i < help.length; i ++ ){
            arr[L+i] = help[i];
        }
    }

    /**
     * 快速排序
     * @param arr1
     */
    public static void quick_sort(int[] arr1){
        int L = 0;
        int R = arr1.length -1;
        quick_sort(arr1, L, R);
    }
    public static void quick_sort(int[] arr1, int L, int R){
        if(L<R){
            swap(R, L+ (int)(Math.random()*(R - L + 1)), arr1);
            int[] p = partation(arr1, L, R);
            quick_sort(arr1, L, p[0] -1);
            quick_sort(arr1, p[1]+1, R);
        }
    }
    public static int[] partation(int[] arr1, int L, int R){
        int less = L -1;
        int more = R;
        while(L<more){
            if(arr1[L]< arr1[R]){
                swap(L, less+1, arr1);
                L++;
                less++;}
            else if(arr1[L] == arr1[R]){
                L++;
                }
            else{
                swap(L, more-1, arr1);
                more--;
            }
            }
        swap(more, R, arr1);
        return new int[] { less+1, more};
        }

    /**
     * 堆排序
     * @param arr1
     * @param index
     */
    public static void heapinsert(int[] arr1, int index){
        while(arr1[index]> arr1[(index-1)/2]){
            swap(index, (index-1)/2, arr1);
            index = (index-1)/2;
        }
    }
    public static void heapify(int[] arr1, int index, int heapsize){
        int left = 2*index + 1;
        while(left < heapsize){
            int largest = 0;
            if(left+1 < heapsize){ largest = arr1[left]<arr1[left+1]? left+1: left; }
            else{ largest = left;}
            if(arr1[index]< arr1[largest]){swap(index, largest, arr1);}
            else{break;}
            index = largest;
            left = 2*index + 1;
        }
    }
    public static void heap(int[] arr1, int heapsize){
        for(int i = 1; i< heapsize; i++){ heapinsert(arr1, i); }
    }
    public static void heap_sort(int[] arr1){
        heap(arr1, arr1.length);
        for(int end = arr1.length; end!=1; end--){
            swap(0, end-1, arr1);
            heapify(arr1, 0, end-1);
        }
    }
    public static void main(String[] args) {
        int[] arr1 = {12,7,7,8,8,10};
        heap_sort(arr1);
        for(int i : arr1){
            System.out.println(i);
        }
        }
}
