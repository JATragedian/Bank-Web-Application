package com.epam.petersburg.ncr41.service;

import com.epam.petersburg.ncr41.dao.impl.AccountDaoImpl;
import com.epam.petersburg.ncr41.dao.impl.CardDaoImpl;
import com.epam.petersburg.ncr41.dao.impl.TransactionDaoImpl;
import com.epam.petersburg.ncr41.model.Account;
import com.epam.petersburg.ncr41.model.Card;
import com.epam.petersburg.ncr41.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class PaymentServiceTest {

    //Test varibles
    private String payerAccountID = "12345678900000000001";
    private Card payerCard = new Card(2123450000000013L, "12345678900000000001");
    private Account payerAccount = new Account("12345678900000000001", new BigDecimal(500), 5, false);
    private Account receiveAccount = new Account("12345678900000000004", new BigDecimal(500), 5, false);
    private String card;
    private ArrayList<Account> accountList = new ArrayList<>();
    private User user = new User();
    private Map<Card, Account> map = new HashMap<>();

    @Before
    public void setUp() {
        accountList.add(payerAccount);
        map.put(payerCard, payerAccount);
    }

    @Test
    public void testSuccessfulPayment() {
        card = "2123450000000013";
        String initAmount = "1";
        String targetAccount = "12345678900000000004";

        PaymentService paymentService = getTestPeymentService();
        String result = paymentService.makePayment(card, initAmount, targetAccount, user);
        assertEquals("Payment successful", result);
    }

    @Test
    public void testEmptyField() {
        card = "";
        String initAmount = "1";
        String targetAccount = "12345678900000000004";

        PaymentService paymentService = getTestPeymentService();
        String result = paymentService.makePayment(card, initAmount, targetAccount, user);
        assertEquals("ERROR: Invalid card number", result);
    }

    @Test
    public void testInvalidCard() {
        String card = "some card";
        String initAmount = "1";
        String targetAccount = "12345678900000000004";

        PaymentService paymentService = getTestPeymentService();
        String result = paymentService.makePayment(card, initAmount, targetAccount, user);
        assertEquals("ERROR: Invalid card number", result);

    }

    @Test
    public void testInsufficcientFunds() {
        String card = "2123450000000013";
        String initAmount = "501";
        String targetAccount = "12345678900000000004";

        PaymentService paymentService = getTestPeymentService();
        String result = paymentService.makePayment(card, initAmount, targetAccount, user);
        assertEquals("ERROR: Insufficient funds", result);

    }

    public PaymentService getTestPeymentService() {

        PaymentService paymentService = new PaymentService();
        paymentService.setAccountDao(Mockito.mock(AccountDaoImpl.class));
        paymentService.setCardDao(Mockito.mock(CardDaoImpl.class));
        paymentService.setTransactionDao(Mockito.mock(TransactionDaoImpl.class));

        when(paymentService.getCardDao().findOne(2123450000000013L)).thenReturn(payerCard);
        when(paymentService.getAccountDao().findOne(receiveAccount.getAccountID())).thenReturn(receiveAccount);
        when(paymentService.getAccountDao().findOne(payerAccountID)).thenReturn(payerAccount);
        when(paymentService.getAccountDao().findByUserId(anyInt())).thenReturn(accountList);
        when(paymentService.getCardDao().findByAccountId(anyInt(), anyString())).thenReturn(map);

        return paymentService;

    }

}
