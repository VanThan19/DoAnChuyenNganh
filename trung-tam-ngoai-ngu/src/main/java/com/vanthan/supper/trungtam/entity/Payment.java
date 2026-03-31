package com.vanthan.supper.trungtam.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name= "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "Method", length = 50, nullable = false)
    private String method;
    @Column(name = "Status", length = 30, nullable = false)
    private String status;
    @Column(name = "Transaction_No", length = 250,nullable = false)
    private String transactionNo;
    @Column(name = "CreatedAt", nullable = false)
    private java.time.LocalDateTime created_at = java.time.LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public Payment() {
    }

    public Payment(BigDecimal amount, String method, String status, String transactionNo, LocalDateTime created_at) {
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.transactionNo = transactionNo;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransaction_no() {
        return transactionNo;
    }

    public void setTransaction_no(String transaction_no) {
        this.transactionNo = transaction_no;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", transaction_no='" + transactionNo + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
