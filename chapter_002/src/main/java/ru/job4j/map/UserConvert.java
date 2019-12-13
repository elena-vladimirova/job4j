package ru.job4j.map;

import java.util.HashMap;
import java.util.List;

public class UserConvert {

    public static HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap();
        list.forEach(l -> result.put(l.getId(), l));
        return result;
    }
}
