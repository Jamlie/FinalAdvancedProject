package edu.najah.cap.dataCollection.Data;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.payment.Transaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserTransactionsData {
    private static final Logger userTransactionsDataLogger = Logger.getLogger(UserTransactionsData.class.getName());
    private static IPayment paymentService;

    public UserTransactionsData(IPayment paymentService) {
        UserTransactionsData.paymentService = paymentService;
    }

    public List<Transaction> getTransactions(String userName) throws SystemBusyException, BadRequestException, NotFoundException {
       try {
           userTransactionsDataLogger.log(Level.INFO, "Fetching transactions for userName: {0}", userName);
           return paymentService.getTransactions(userName);
       }catch (SystemBusyException | BadRequestException | NotFoundException e) {
           userTransactionsDataLogger.log(Level.WARNING, "Error fetching transactions for userName: {0}", new Object[]{userName, e});
           throw e;
       } catch (Exception e) {
           userTransactionsDataLogger.log(Level.SEVERE, "Unexpected error occurred while fetching transactions for userName: {0}", new Object[]{userName, e});
           throw new RuntimeException("Unexpected error occurred", e);
       }
    }

    public double getBalance(String userName){
        try {
            userTransactionsDataLogger.log(Level.INFO, "Fetching balance for userName: {0}", userName);
            double balance = paymentService.getBalance(userName);
            return balance;
        } catch (Exception e) {
            userTransactionsDataLogger.log(Level.SEVERE, "Unexpected error occurred while fetching balance for userName: {0}", new Object[]{userName, e});
            throw new RuntimeException("Unexpected error occurred", e);
        }
    }
}
