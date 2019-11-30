package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Приятного путешествия!");
    }

    @Override
    public void passengers(int count) {
        System.out.println("Количество пассажиров: " + count);
    }

    @Override
    public double tank(double amount) {
        return amount * 10;
    }

    public static void main(String[] args) {
        Bus b = new Bus();
        b.drive();
        b.passengers(10);
        System.out.println("К оплате " + b.tank(2));
    }
}
