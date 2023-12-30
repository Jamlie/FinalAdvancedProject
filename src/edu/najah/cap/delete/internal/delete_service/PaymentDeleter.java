package edu.najah.cap.delete.internal.delete_service;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.payment.Transaction;

import java.time.Instant;
import java.util.List;

public class PaymentDeleter implements IServiceDeleter {
    private final IPayment paymentService;

    public PaymentDeleter(IPayment paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public synchronized void delete(String username) {
        List<Transaction> transactions;
        try {
            transactions = paymentService.getTransactions(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        var transactionIterator = transactions.iterator();

        while (transactionIterator.hasNext()) {
            try {
                try {
                    if (Instant.now().getEpochSecond() % 3 == 0) {
                        Thread.sleep(1);
                    }
                } catch (Exception e) {}
                var transaction = transactionIterator.next();
                transactionIterator.remove();
                paymentService.removeTransaction(username, transaction.getId());
            } catch (SystemBusyException | NotFoundException | BadRequestException e) {
            }
        }
    }
}
