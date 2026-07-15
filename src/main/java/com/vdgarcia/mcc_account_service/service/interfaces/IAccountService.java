package com.vdgarcia.mcc_account_service.service.interfaces;

import com.vdgarcia.mcc_account_service.dto.AccountDTO;
import com.vdgarcia.mcc_account_service.dto.DepositDTO;
import com.vdgarcia.mcc_account_service.util.ICrud;

public interface IAccountService extends ICrud<AccountDTO> {

    public AccountDTO depositInAccount(DepositDTO depositDTO);

}
