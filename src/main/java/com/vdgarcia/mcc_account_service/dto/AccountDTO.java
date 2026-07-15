package com.vdgarcia.mcc_account_service.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {

    private String id;
    private String accountNumber;
    private String accountName;
    private BigDecimal accountBalance;
    private CustomerDTO customer;
}
