package com.epam.petersburg.ncr41.service;

import com.epam.petersburg.ncr41.dao.interfaces.AccountDao;
import com.epam.petersburg.ncr41.dao.interfaces.CardDao;
import com.epam.petersburg.ncr41.dao.interfaces.TransactionDao;
import com.epam.petersburg.ncr41.model.Account;
import com.epam.petersburg.ncr41.model.Card;
import com.epam.petersburg.ncr41.model.Transaction;
import com.epam.petersburg.ncr41.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentHistoryServiceTest {

    private PaymentHistoryService paymentHistoryService = new PaymentHistoryService(new User(
            2,
            "Alex",
            "Wayde",
            "Wayde@epam.com",
            "12345",
            2)
    );

    @BeforeAll
    static void setUp() {
        System.out.println("Registration service tests start");
    }

    @AfterAll
    static void done() {
        System.out.println("Registration service tests finished");
    }

    @Test
    void getUsersTransactionsTest() {
        List<Account> accountList = new ArrayList<>();
        List<Card> cardList = new ArrayList<>();
        List<Transaction> transactionList= new ArrayList<>();
        List<Transaction> userTwoTransactionList = new ArrayList<>();
        prepareTestData(accountList, cardList, transactionList, userTwoTransactionList);

        paymentHistoryService.setCardDao(Mockito.mock(CardDao.class));
        paymentHistoryService.setTransactionDao(Mockito.mock(TransactionDao.class));
        paymentHistoryService.setAccountDao(Mockito.mock(AccountDao.class));
        when(paymentHistoryService.getCardDao().findAll()).thenReturn(cardList);
        when(paymentHistoryService.getAccountDao().findAll()).thenReturn(accountList);
        when(paymentHistoryService.getTransactionDao().findAll()).thenReturn(transactionList);
        assertEquals(userTwoTransactionList, paymentHistoryService.getUsersTransactions());
        assertNotEquals(transactionList, paymentHistoryService.getUsersTransactions());
    }

    private void prepareTestData(List<Account> testAccounts, List<Card> testCards, List<Transaction> testTransactions, List<Transaction> expectedResultTransactions) {
        BigDecimal balance = new BigDecimal(23535);
        BigDecimal amount1 = new BigDecimal(2000);
        BigDecimal amount2 = new BigDecimal(235);
        BigDecimal amount3 = new BigDecimal(12555);
        testAccounts.add(new Account("1234567890123457", balance, 1, false));
        testAccounts.add(new Account("1234567890123456", balance, 2, false));

        testCards.add(new Card(5432876543289765L, "1234567890123456"));
        testCards.add(new Card(5432876543289766L, "1234567890123456"));
        testCards.add(new Card(5432876543289767L, "1234567890123457"));

        testTransactions.add(new Transaction(1, 5432876543289765L, LocalDate.now(), amount1, "1234567890123457"));
        testTransactions.add(new Transaction(2, 5432876543289766L, LocalDate.now(), amount2, "1234567890123457"));
        testTransactions.add(new Transaction(3, 5432876543289767L, LocalDate.now(), amount3, "1234567890123456"));

        expectedResultTransactions.add(new Transaction(1, 5432876543289765L, LocalDate.now(), amount1, "1234567890123457"));
        expectedResultTransactions.add(new Transaction(2, 5432876543289766L, LocalDate.now(), amount2, "1234567890123457"));
    }
}
