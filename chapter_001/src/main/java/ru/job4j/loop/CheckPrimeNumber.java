package ru.job4j.loop;

public class CheckPrimeNumber {
    public boolean check(int num) {
        boolean prime = false;
        for (int i=num; i>0; i--){
            if ((num % 5) == 0) {
                prime = true;
                break;
            }
        }
        return prime;
    }
}