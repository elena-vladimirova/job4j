package ru.job4j.loop;

public class PrimeNumber {
    public int calc(int finish) {
        int count = 0;
        CheckPrimeNumber primeNumber = new CheckPrimeNumber();
        for (int i = 2; i <= finish; i++) {
            //for (int j=i-1;j>1;j--){
            //    if ((i%j) == 0) {
            //        count--;
            //        break;
            //    }
            if (primeNumber.check(i)) {
                count++;
            }
        }
        return count;
    }
}
