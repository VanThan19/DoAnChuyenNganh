package com.vanthan.supper.trungtam.service;

import com.vanthan.supper.trungtam.entity.Account;
import com.vanthan.supper.trungtam.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;

    public Account Authenticate(String username,String password) throws Exception{
        // 1. Ràng buộc dữ liệu trống
        if(username == null || username.trim().isEmpty()) {
            throw new Exception("Vui lòng nhập Username hoặc Email!");
        }
        Account acc = accountRepo.findByUsernameOrEmail(username,username);

        if(acc == null) {
            throw new Exception("Tài khoản không tồn tại trong hệ thống!");
        }
        if(!acc.getPassword().equals(password)) {
            throw new Exception("Mật khẩu không chính xác!");
        }
        if (!acc.isStatus()) {
            throw new Exception("Tài khoản này đã bị khóa!");
        }
        return acc;
    }

}
