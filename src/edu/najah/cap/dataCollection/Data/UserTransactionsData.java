package edu.najah.cap.dataCollection.Data;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.payment.Transaction;

import java.util.List;

public class UserTransactionsData {
    private static IPayment paymentService;

    public UserTransactionsData(IPayment paymentService) {
        UserTransactionsData.paymentService = paymentService;
    }

    public List<Transaction> getTransactions(String userName) throws SystemBusyException, BadRequestException, NotFoundException {
       try {
           return paymentService.getTransactions(userName);
       }catch (SystemBusyException | BadRequestException | NotFoundException e) {
           throw e;
       } catch (Exception e) {
           throw new RuntimeException("Unexpected error occurred", e);
       }
    }

    public double getBalance(String userName){
       return paymentService.getBalance(userName);
    }
}
