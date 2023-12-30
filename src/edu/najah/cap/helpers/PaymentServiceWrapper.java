package edu.najah.cap.helpers;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.exceptions.Util;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.payment.PaymentService;

public class PaymentServiceWrapper extends PaymentService {
    private IPayment paymentService;

    public PaymentServiceWrapper() {
        super();
    }

    public PaymentServiceWrapper(IPayment paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public synchronized void removeTransaction(String userName, String id) throws SystemBusyException, BadRequestException, NotFoundException {
        Util.validateUserName(userName);
        var transactions = paymentService.getTransactions(userName);
        if (transactions != null) {
            transactions.removeIf(transaction -> transaction.getId().equals(id));
        }
    }
}
