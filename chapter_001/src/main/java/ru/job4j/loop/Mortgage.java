package ru.job4j.loop;

public class Mortgage {
    // amount - сумма, выданная по кредиту
    // salary - годовой доход
    // percent - процентная ставка по кредиту
    public int year(int amount, int salary, double percent) {
        int year = 1;
        // сумма к выплате по кредиту
        double creditSum = amount + amount * percent / 100;

        while (creditSum > salary) {
            year++;
            creditSum = creditSum - salary;
            creditSum = creditSum + creditSum * percent / 100;
        }
        return year;
    }
}
