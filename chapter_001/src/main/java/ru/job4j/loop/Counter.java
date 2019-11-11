package ru.job4j.loop;

public class Counter {
    public static int add(int start, int finish) {
        int sum = 0;
        int evenStart = (start%2==0)?start:(start+1);
        for (int i=evenStart;i<=finish;i=i+2) {
            sum+=i;
        }
        return sum;
    }
}