package com.vanthan.supper.trungtam.repository;

import com.vanthan.supper.trungtam.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepo extends JpaRepository<Payment,Long>
{
    Optional<Payment> findByTransactionNo(String transactionNo);
}
