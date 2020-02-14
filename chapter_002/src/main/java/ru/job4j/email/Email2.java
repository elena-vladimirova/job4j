package ru.job4j.email;

import java.util.*;

/**
 * Имеется n пользователей, каждому из них соответствует список email-ов
 * (всего у всех пользователей m email-ов).
 * Например:
 * user1 ->xxx@ya.ru,foo@gmail.com,lol@mail.ru
 * user2 ->foo@gmail.com,ups@pisem.net
 * user3 ->xyz@pisem.net,vasya@pupkin.com
 * user4 ->ups@pisem.net,aaa@bbb.ru
 * user5 ->xyz@pisem.net
 *
 * Считается, что если у двух пользователей есть общий email, значит это
 * один и тот же пользователь.
 * Требуется построить
 * и реализовать алгоритм, выполняющий слияние пользователей. На выходе
 * должен быть список пользователей с их email-ами (такой же как на
 * входе).
 * В качестве имени объединенного пользователя можно брать любое из
 * исходных имен. Список email-ов пользователя должен содержать только
 * уникальные email-ы.
 * Параметры n и m произвольные, длина конкретного списка email-ов никак
 * не ограничена.
 * Требуется, чтобы асимптотическое время работы полученного решения было
 * линейным, или близким к линейному.
 *
 * Возможный ответ на задачу в указанном примере:
 * user1 ->xxx@ya.ru,foo@gmail.com,lol@mail.ru,ups@pisem.net,aaa@bbb.ru
 * user3 ->xyz@pisem.net,vasya@pupkin.com
 */
public class Email2 {

    public static Map<String, Set<String>> merge(List<User> users) {

        Map<String, String> emails = new HashMap<>();
        Map<String, Set<String>> mergedUsers = new HashMap<>();

        for (User u : users) {
            String name = u.name;
            for (String email : u.emails) {
                if (emails.containsKey(email)) {
                    name = emails.get(email);
                    break;
                }
            }
            for (String email : u.emails) {
                emails.put(email, name);
            }
            if (mergedUsers.containsKey(name)) {
                mergedUsers.get(name).addAll(u.emails);
            } else {
                mergedUsers.put(name, new HashSet<>(u.emails));
            }
        }
        return mergedUsers;
    }

    public static class User {

        String name;
        List<String> emails;

        public User(String name, List<String> emails) {
            this.name = name;
            this.emails = emails;
        }
    }

    public static void main(String[] args) {
        List<User> users = List.of(
                new User("user1", List.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")),
                new User("user2", List.of("foo@gmail.com", "ups@pisem.net")),
                new User("user3", List.of("xyz@pisem.net", "vasya@pupkin.com")),
                new User("user4", List.of("ups@pisem.net", "aaa@bbb.ru")),
                new User("user5", List.of("xyz@pisem.net"))
        );
        Map<String, Set<String>> merged = Email2.merge(users);
        System.out.println(merged);
    }
}
