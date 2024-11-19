package com.bank.transaction.service;

import com.bank.transaction.model.Transaction;
import com.bank.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction updateTransactionStatus(Long id, String status) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            Transaction existingTransaction = transaction.get();
            existingTransaction.setStatus(status);
            existingTransaction.setUpdatedAt(existingTransaction.getUpdatedAt()); // Update the timestamp
            return transactionRepository.save(existingTransaction);
        }
        return null;
    }
}