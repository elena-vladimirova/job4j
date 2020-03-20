package ru.job4j.gc.size;

public class User {

    private String name;
    private String education;

    public User(String name, String education) {
        this.name = name;
        this.education = education;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("!!!finalize!!!" + this.name);
    }

    public static void info() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Used memory:"
                          + (runtime.totalMemory() - runtime.freeMemory()));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4000; i++) {
            System.out.println("---start---" + i);
            info();
            User user = new User("Lena" + i, "higher education");
            info();
            user = null;
            System.out.println("---finish---");
        }
    }
}
