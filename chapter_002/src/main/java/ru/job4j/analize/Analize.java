package ru.job4j.analize;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {

    /**
     * Метод возвращает статистику по списку User.
     * @param previous начальное состояние коллекции
     * @param current текущее состояние коллекции
     * @return объект класса Info, содержащий статистику по коллекции
     */
    public static Info diff(List<User> previous, List<User> current) {

        Info info = new Info();
        Map<Integer, User> currentMap = current.stream().collect(Collectors.toMap(u -> u.id, u -> u));

        for (User prevUser : previous) {
            User currUser = currentMap.get(prevUser.id);
            if (currUser != null) {
                if (!currUser.equals(prevUser)) {
                    info.changed += 1;
                }
            } else {
                info.deleted += 1;
            }
        }

        info.added = current.size() - (previous.size() - info.deleted);

        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    /**
     * Статистика по коллекции
     */
    public static class Info {
        /**
         * Число добавленных элементов
         */
        int added;
        /**
         * Число измененных элементов
         */
        int changed;
        /**
         * Число удаленных элементов
         */
        int deleted;
    }

}