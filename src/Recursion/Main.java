package Recursion;

public class Main {
    /**
     * 时间复杂度nlogn算法需要利用递归进行二分
     * 运用两个指针进行比较
     * 记住递归的基本格式：
     * funcA
     * 基础向
     * funcA前项
     * funcA后项
     * @param arr1
     * @return
     */
    public static int samll_sum(int[] arr1){
        int L = 0;
        int R = arr1.length-1;

        return process(arr1, L, R);
    }
    public static int process(int[] arr1, int L, int R){
        if (L==R){return 0;}
        int M = L + (R-L)/2;
        return  process(arr1, L, M) + process(arr1, M+1, R) + Sum(arr1, L, M, R);
    }
    public static int Sum(int[] arr1, int L, int M, int R){
        int pointer1 = L;
        int j =0;
        int pointer2 = M+1;
        int[] help = new int[R-L+1];
        int res = 0;
        while(pointer1<= M && pointer2<=R) {
            if (arr1[pointer1] < arr1[pointer2]) {
                res +=  arr1[pointer1] * (R - pointer2 + 1);
                help[j++] = arr1[pointer1++];
            } else {
                help[j++] = arr1[pointer2++];
            }
        }
        while(pointer1<=M){
            help[j++] = arr1[pointer1++];
        }
        while(pointer2<=R){
            help[j++] = arr1[pointer2++];
        }
        for (int i = 0; i < help.length; i ++ ){
            arr1[L+i] = help[i];
        }
        return res;
    }
    public static int Reverse_pair(int[] arr1){
        int L = 0;
        int R = arr1.length -1;
        int reverse_pair_sum = 0;
        reverse_pair_sum = process_reverse(arr1, L, R);
        return reverse_pair_sum;
    }
    public static int process_reverse(int[] arr1, int L, int R){
        if (L == R){ return 0;}
        int M = L + (R-L)/2;
        return process_reverse(arr1, L, M) + process_reverse(arr1, M+1, R) + reverse(arr1, L, M, R);
    }
    public static int reverse(int[] arr1, int L, int M, int R){
        int j = 0;
        int counter = 0;
        int pointer1 = L;
        int pointer2 = M+1;
        int[] help = new int[R-L+1];
        while(pointer1 <= M && pointer2 <= R){
            if(arr1[pointer1]<= arr1[pointer2]){
                help[j++] = arr1[pointer2++];
            }
            else{
                counter += (R - pointer2 + 1);
                for (int i = 0; i < (R-pointer2+1); i++){
                    System.out.println("reverse pair: " + arr1[pointer1] + " " + arr1[pointer2 + i]);
                }
                help[j++] = arr1[pointer1++];
            }
        }
        while(pointer1<=M){
            help[j++] = arr1[pointer1++];
        }
        while(pointer2<=R){
            help[j++] = arr1[pointer2++];
        }
        for (int i =0; i< help.length; i++){
            arr1[L+i] = help[i];
        }
        return counter;
    }



    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 5, 3, 4, 3, 2, 1};
        int sum;
        sum = Reverse_pair(arr1);
        System.out.println(sum);
    }
}
