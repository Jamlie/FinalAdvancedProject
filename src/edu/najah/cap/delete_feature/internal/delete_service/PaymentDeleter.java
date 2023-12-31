package edu.najah.cap.delete_feature.internal.delete_service;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.payment.Transaction;

import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentDeleter implements IServiceDeleter {
    private final IPayment paymentService;

    public PaymentDeleter(IPayment paymentService) {
        this.paymentService = paymentService;
    }
    private static final Logger logger = Logger.getLogger(PaymentDeleter.class.getName());

    @Override
    public synchronized void delete(String username) {
        List<Transaction> transactions;
        try {
            transactions = paymentService.getTransactions(username);
        } catch (NotFoundException | BadRequestException | SystemBusyException e) {
            logger.log(Level.SEVERE, "Failed to get transactions for user {0}", username);
            throw new RuntimeException(e);
        }

        var transactionIterator = transactions.iterator();

        while (transactionIterator.hasNext()) {
            try {
                try {
                    if (Instant.now().getEpochSecond() % 3 == 0) {
                        Thread.sleep(1);
                    }
                } catch (InterruptedException ignored) {}
                var transaction = transactionIterator.next();
                transactionIterator.remove();
                paymentService.removeTransaction(username, transaction.getId());
            } catch (SystemBusyException | NotFoundException | BadRequestException ignored) {
                logger.log(Level.WARNING, "Failed to delete transaction for user {0}", username);
            }
        }

        logger.log(Level.INFO, "Deleted all transactions for user {0}", username);
    }
}
