package com.vdgarcia.mcc_account_service.util;

public interface IMapper<T> {
    T getDTO();
    void setDTO(T t);
}
