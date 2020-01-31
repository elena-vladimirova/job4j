package ru.job4j.email;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class Email {

    public static Map<String, List<String>> merge(List<User> users) {

        /**
         * Построение карты emails <key = email, value = user> по заданному правилу
         */
        Map<String, String> emails = new HashMap<>();
        for (User u : users) {
            String name = u.name;
            for (String email : u.emails) {
                if (emails.containsKey(email)) {
                    name = emails.get(email);
                    break;
                }
            }
            for (String email : u.emails) {
                emails.putIfAbsent(email, name);
            }
        }

        /**
         * Группировка карты emails по пользователям.
         */
        Map<String, List<String>> mergedUsers = emails.keySet()
                                                      .stream()
                                                      .collect(Collectors.groupingBy(k -> emails.get(k),
                                                                                     Collectors.mapping(k -> k,
                                                                                                        Collectors.toList())
                                                                                     )
                                                              );

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
        List<User> users = List.of(new User("user1", List.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")),
                                   new User("user2", List.of("foo@gmail.com", "ups@pisem.net")),
                                   new User("user3", List.of("xyz@pisem.net", "vasya@pupkin.com")),
                                   new User("user4", List.of("ups@pisem.net", "aaa@bbb.ru")),
                                   new User("user5", List.of("xyz@pisem.net"))
                                  );
        Map<String, List<String>> merged = Email.merge(users);
        System.out.println(merged);
    }
}
