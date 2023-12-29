package edu.najah.cap.dataCollection.Data;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.payment.Transaction;

import java.util.List;

public class UserTransactionsData {
    private final  IPayment paymentService;

    public UserTransactionsData(IPayment paymentService) {
        this.paymentService = paymentService;
    }

    public List<Transaction> getTransactions(String userName) throws SystemBusyException, BadRequestException, NotFoundException {
       return paymentService.getTransactions(userName);
    }

    public double getBalance(String userName) {
        return paymentService.getBalance(userName);
    }
}
