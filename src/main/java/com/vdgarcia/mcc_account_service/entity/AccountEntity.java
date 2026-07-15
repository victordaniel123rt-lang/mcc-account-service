package com.vdgarcia.mcc_account_service.entity;

import com.vdgarcia.mcc_account_service.dto.AccountDTO;
import com.vdgarcia.mcc_account_service.dto.CustomerDTO;
import com.vdgarcia.mcc_account_service.util.IMapper;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "t_account")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class AccountEntity implements IMapper<AccountDTO> {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, length = 60)
    private String id;
    @Column(name = "account_number", nullable = false, length = 30)
    private String accountNumber;
    @Column(name = "account_name", nullable = false, length = 60)
    private String accountName;
    @Column(name = "account_balance", precision = 10, scale = 2)
    private BigDecimal accountBalance;
    @Column(name = "customer_cu", nullable = false, length = 20)
    private String customerCu;




    @Override
    public AccountDTO getDTO() {
        return AccountDTO.builder()
                .id(id)
                .accountBalance(accountBalance)
                .accountName(accountName)
                .customer(CustomerDTO.builder().cu(this.customerCu).build())

                .build();
    }

    @Override
    public void setDTO(AccountDTO accountDTO) {
        this.id = accountDTO.getId();
        this.accountName = accountDTO.getAccountName();
        this.customerCu = accountDTO.getCustomer().getCu();
        this.accountNumber = accountDTO.getAccountNumber();
        this.accountBalance = accountDTO.getAccountBalance();
    }
}
