package com.vdgarcia.mcc_account_service.controller;

import com.vdgarcia.mcc_account_service.dto.AccountDTO;
import com.vdgarcia.mcc_account_service.dto.DepositDTO;
import com.vdgarcia.mcc_account_service.service.interfaces.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountRestController {
    private final IAccountService service;


    @GetMapping
    public List<AccountDTO> getAllAccounts(){
        return this.service.getAll();
    }


    @PostMapping
    public AccountDTO addAccount(@RequestBody AccountDTO dto){
        return service.add(dto);
    }


    @PutMapping
    public AccountDTO depositAccount(@RequestBody DepositDTO depositDTO){
        return service.depositInAccount(depositDTO);
    }


}
