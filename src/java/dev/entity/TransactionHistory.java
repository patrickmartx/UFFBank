/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.entity;

import dev.utils.Status;
import java.util.Date;
import dev.utils.TransactionType;

/**
 *
 * @author Patrick
 */
public class TransactionHistory {

    private Long id;
    private Double value;
    private Date transactionDate;
    private TransactionType transactionType;
    private Long senderAccountId;
    private Long receiverAccountId;
    private Status status;

    public TransactionHistory() {
    }

    public TransactionHistory(Long id, Double value, Date transactionDate,
            TransactionType transactionType, Long senderAccountId, Long receiverAccountId, Status status) {
        this.id = id;
        this.value = value;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public Long getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
