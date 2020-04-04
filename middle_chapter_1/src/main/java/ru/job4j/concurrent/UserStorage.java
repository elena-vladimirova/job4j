package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    private Map<Integer, User> users = new HashMap();

    public void add(User user) {
        users.put(user.id, user);
    }

    @GuardedBy("this")
    public synchronized void transfer(int fromId, int toId, int amount) throws InterruptedException {
        User userFrom = users.get(fromId);
        User userTo = users.get(toId);
        while (userFrom.amount < amount) {
            wait();
        }
        userFrom.amount -= amount;
        userTo.amount += amount;
        notifyAll();

    }

    static class User {
        public int id;
        public int amount;

        public User(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }
    }

    public static void main(String[] args) {
        UserStorage store = new UserStorage();
        User user1 = new User(1, 10);
        User user2 = new User(2, 10);
        store.add(user1);
        store.add(user2);
        try {
            store.transfer(1, 2, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(user1.amount);
        System.out.println(user2.amount);
    }
}
