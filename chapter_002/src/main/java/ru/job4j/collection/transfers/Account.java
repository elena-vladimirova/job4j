package ru.job4j.collection.transfers;

/**
 * Класс для работы со счетами
 *
 * @author Elena Vladimirova (avtobusova@mail.ru)
 */
public class Account {

    /**
     * Уникальный ключ: реквизиты счета
     */
    private String requisites;
    /**
     * Сумма на счете
     */
    private double value;

    public String getRequisites() {
        return requisites;
    }

    public double getValue() {
        return value;
    }

    public Account(String requisites, double value) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Перечисление денег с одного счета на другой
     *
     * @param dstAccount счет для завчисления средств
     * @param amount сумма зачисления
     * @return Признак, корректно ли выполнена операция: true - корректно, false - не корректно
     */
    boolean transfer(Account dstAccount, double amount) {
        boolean success = false;
        if (amount > 0 && amount < this.value && dstAccount != null) {
            success = true;
            this.value -= amount;
            dstAccount.value += amount;
        }
        return success;
    }
}
