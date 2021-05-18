package others;

import java.lang.management.MemoryType;

public class Main {
    public double myPow(double x, int n) {
        if(x==1)return 1;
        else if(x== -1){
            return n%2==0? 1:-1;
        }
        else{
            if(n==0) return 1;
            else if(n>0){
                double temp = x;
                while(n!=1){
                    temp = temp*x;
                    n--;}
                return temp;
            }
            else{
                double temp = x;
                while(n!=1){
                    temp = temp/x;
                    if(temp==0){return temp;}
                    n++;}
                return temp;
            }
        }
    }
    public int findNthDigit(int n) {
        double temp = n;
        double i = 1;
        if (n <10){ return n;}
        temp = temp - 10;
        while(temp>0){
            i++;
            temp = temp-i*9*Math.pow(10, i-1);
        }
        int diff =(int)(temp/i);
        int num = (int)(Math.pow(10, i)) + diff;
        int real_diff = (int)(temp- diff*(i));
        int real_num = num -1;
        return (real_num/(int)Math.pow(10, -(real_diff+1)))%10;
    }

    public static void main(String[] args){
        System.out.println(findNthDigit(4));
    }

}
