package ru.job4j.oop;

public class Battery {

    private int load;

    public Battery(int load) {
        this.load = load;
    }

    public void exchange(Battery another) {
        another.load = another.load + this.load;
        this.load = 0;
    }

    public static void main(String[] args) {
        Battery b1 = new Battery(10);
        Battery b2 = new Battery(5);
        System.out.println("b1.load = " + b1.load + " b2.load = " + b2.load);
        b1.exchange(b2);
        System.out.print("b1.load = " + b1.load + " b2.load = " + b2.load);
    }

}
