package com.vdgarcia.mcc_account_service.service.impl;

import com.vdgarcia.mcc_account_service.client.ICustomerRestClient;
import com.vdgarcia.mcc_account_service.dto.AccountDTO;
import com.vdgarcia.mcc_account_service.dto.CustomerDTO;
import com.vdgarcia.mcc_account_service.dto.DepositDTO;
import com.vdgarcia.mcc_account_service.entity.AccountEntity;
import com.vdgarcia.mcc_account_service.repository.IAccountRepository;
import com.vdgarcia.mcc_account_service.service.interfaces.IAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final IAccountRepository repository;
    private final ICustomerRestClient customerRestClient;

    @Override
    public List<AccountDTO> getAll() {
        return this.repository.findAll().stream().map(AccountEntity::getDTO).toList();
    }

    @Override
    public AccountDTO add(AccountDTO accountDTO) {
        ResponseEntity<CustomerDTO>  responseEntitynewCustomerDTO = this.customerRestClient.add(accountDTO.getCustomer());
        if(responseEntitynewCustomerDTO.getStatusCode().is2xxSuccessful()){
            log.info("Customer added succesfully");
            accountDTO.setCustomer(responseEntitynewCustomerDTO.getBody());
            log.info("add account to account repository {}", accountDTO);
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setDTO(accountDTO);
            return  repository.save(accountEntity).getDTO();
        }else {
            log.info("Customer added failed");
            return AccountDTO.builder().build();
        }

    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public AccountDTO delete(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public AccountDTO getById(String id) {
        return null;
    }


    @Override
    public AccountDTO depositInAccount(DepositDTO depositDTO) {
        log.info("Deposit into account repository, {}", depositDTO);
        Optional<AccountEntity> optionalAccountEntity = repository.findByAccountNumberAndCustomerCu(depositDTO.getAccountNumber(),depositDTO.getCustomerCu());
        if(optionalAccountEntity.isPresent()){
            AccountEntity accountEntity = optionalAccountEntity.get();
            accountEntity.setAccountBalance(accountEntity.getAccountBalance().add(depositDTO.getAmount()));
            return repository.save(accountEntity).getDTO();
        }
        return AccountDTO.builder().build();
    }
}
