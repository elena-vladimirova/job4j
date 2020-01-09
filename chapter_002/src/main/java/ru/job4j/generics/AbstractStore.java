package ru.job4j.generics;

import java.util.Iterator;

public abstract class AbstractStore<U extends Base> implements Store<U> {

    private SimpleArray<U> values;

    public AbstractStore(SimpleArray<U> values) {
        this.values = values;
    }

    @Override
    public void add(U model) {
        this.values.add(model);
    }

    @Override
    public boolean replace(String id, U model) {
        boolean result = false;
        U user = this.findById(id);
        if (user != null) {
            this.values.set(values.getPosition(user), model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        U user = this.findById(id);
        if (user != null) {
            this.values.remove(values.getPosition(user));
            result = true;
        }
        return result;
    }

    @Override
    public U findById(String id) {
        Iterator<U> it = this.values.iterator();
        U result = null;
        while (it.hasNext()) {
            U currentUser = it.next();
            if (currentUser.getId() == id) {
                result = currentUser;
                break;
            }
        }
        return result;
    }
}
