package com.company;

import java.util.*;

public class Main {
    public static void sort(Map input, Integer sorting){
        Map<String, Integer> result = new TreeMap<String, Integer>();
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String, Integer>>(input.entrySet());
        if (sorting == 1){
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    int compare = (o1.getValue()).compareTo(o2.getValue());
                    return compare;
                }
            });
            for(Map.Entry<String,Integer> mapping:list){
                System.out.println(mapping.getKey() + "  " + mapping.getValue());
            }
        }
        else if (sorting == 0){
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    int compare = (o1.getValue()).compareTo(o2.getValue());
                    return -compare;
                }
            });
            for(Map.Entry<String,Integer> mapping:list){
                System.out.println(mapping.getKey() + "  " + mapping.getValue());
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the amount of people:");
        while(sc.hasNextLine()){
            int amount = Integer.parseInt(sc.nextLine());
            System.out.println("Please input the way of sorting (0 for Descending and 1 for Ascending)");
            int sorting = Integer.parseInt(sc.nextLine());
            Map<String, Integer> m1 = new TreeMap<String, Integer>();
            for(int i = 0; i < amount; i++) {
                System.out.println("please input next student's name and score, divide them using space");
                String name = sc.nextLine();
                String [] arr_name = name.split("\\s+");
                int score = Integer.parseInt(arr_name[1]);
                m1.put(arr_name[0], score);
            }
            sort(m1, sorting);
            }
        }
    }