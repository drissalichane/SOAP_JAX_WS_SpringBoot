package com.example.demo.tp13.ws;

import com.example.demo.tp13.entities.BankAccount;
import com.example.demo.tp13.enums.AccountType;
import com.example.demo.tp13.repositories.BankAccountRepository;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@WebService(serviceName = "BankWS")
public class BankSoapService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @WebMethod
    public List<BankAccount> getAccounts() {
        return bankAccountRepository.findAll();
    }

    @WebMethod
    public BankAccount getAccountById(@WebParam(name = "id") Long id) {
        return bankAccountRepository.findById(id).orElse(null);
    }

    @WebMethod
    public BankAccount createAccount(@WebParam(name = "balance") double balance,
            @WebParam(name = "type") AccountType type) {
        BankAccount account = new BankAccount(null, balance, new Date(), type);
        return bankAccountRepository.save(account);
    }

    @WebMethod
    public boolean deleteAccount(@WebParam(name = "id") Long id) {
        if (bankAccountRepository.existsById(id)) {
            bankAccountRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
