package ru.job4j.collection.transfers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для работы со списками пользователей и счетов.
 *
 * @author Elena Vladimirova (avtobusova@mail.ru)
 */
public class Bank {

    /**
     * Коллекция пользователей и список их счетов.
     */
    Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод возвращает пользователя по паспорту.
     *
     * @param passport паспорт пользователя
     * @return Найденный пользователь или null.
     */
    private User getUser(String passport) {
        User user = users.keySet().stream().filter(u -> u.getPassport() == passport).findFirst().orElse(null);
        return user;
    }

    /**
     * Метод возвращает счет по реквизитам.
     *
     * @param requisites реквизиты счета
     * @param list список счетов
     * @return Найденный счет.
     */
    private Account getAccount(String requisites, List<Account> list) {
        Account account = list.stream().filter(l -> l.getRequisites() == requisites).findFirst().orElse(null);
        return account;
    }

    /**
     * Добавление пользователя.
     *
     * @param user Пользователь
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Удаление пользователя.
     *
     * @param user Пользователь
     */
    public void deleteUser(User user) {
        users.remove(user);

    }

    /**
     * Удаление пользователя.
     *
     * @param passport паспорт пользователя
     * @param account счет
     */
    public void addAccountToUser(String passport, Account account) {
        User user =  getUser(passport);
        if (user != null) {
            this.users.get(getUser(passport)).add(account);
        }
    }

    /**
     * Удаление счета пользователя.
     *
     * @param passport паспорт пользователя
     * @param account счет
     */
    public void deleteAccountFromUser(String passport, Account account) {
        User user =  getUser(passport);
        if (user != null) {
            this.users.get(getUser(passport)).remove(account);
        }
    }

    /**
     * Список счетов для пользователя.
     *
     * @param passport паспорт пользователя
     * @return Список счетов
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = new ArrayList<Account>();
        User user =  getUser(passport);
        if (user != null) {
            result = this.users.get(getUser(passport));
        }
        return result;
    }

    /**
     * Перечисление денег с одного счета на другой
     *
     * @param srcPassport паспорт пользователя, со счета которого списываются средства
     * @param srcRequisite реквизиты счета, с которого списываются средства
     * @param destPassport паспорт пользователя, которому перечисляются средства
     * @param dstRequisite реквизиты счета, на который зачисляются средства
     * @param amount сумма зачисления
     * @return Признак, корректно ли выполнена операция: true - корректно, false - не корректно
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        boolean result = false;
        User srcUser = getUser(srcPassport);
        User dstUser = getUser(destPassport);
        if (srcUser != null && dstUser != null) {
            Account srcAccount = getAccount(srcRequisite, users.get(srcUser));
            Account dstAccount = getAccount(dstRequisite, users.get(dstUser));
            if (srcAccount != null && dstAccount != null) {
                result = srcAccount.transfer(dstAccount, amount);
            }
        }
        return result;
    }
}
