package ru.job4j.collection.transfers;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    @Test
    public void whenGetUserAccounts() {
        Bank bank = new Bank();
        User user1 = new User("4545 0698", "Popov Dmity");
        User user2 = new User("4545 0699", "Vladimirova Tanya");
        bank.addUser(user1);
        bank.addUser(user2);
        Account account1 = new Account("41415252", 10);
        Account account2 = new Account("41415253", 20);
        Account account3 = new Account("41415254", 30);
        bank.addAccountToUser("4545 0698", account1);
        bank.addAccountToUser("4545 0698", account2);
        bank.addAccountToUser("4545 0699", account3);
        List<Account> result = bank.getUserAccounts("4545 0698");
        List<Account> expected = Arrays.asList(account1, account2);
        assertThat(expected, is(result));
    }

    @Test
    public void whentransferMoney() {
        Bank bank = new Bank();
        User user1 = new User("4545 0698", "Popov Dmity");
        User user2 = new User("4545 0699", "Vladimirova Tanya");
        bank.addUser(user1);
        bank.addUser(user2);
        Account account1 = new Account("41415252", 10);
        Account account2 = new Account("41415253", 20);
        bank.addAccountToUser("4545 0698", account1);
        bank.addAccountToUser("4545 0699", account2);
        boolean transfer = bank.transferMoney("4545 0698", "41415252", "4545 0699", "41415253", 5);
        assertThat(account1.getValue(), is(5.0));
        assertThat(account2.getValue(), is(25.0));
    }
}
