package com.vanthan.supper.trungtam.repository;

import com.vanthan.supper.trungtam.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account,Long> {

    Account findByUsernameOrEmail(String username, String username1);
}
