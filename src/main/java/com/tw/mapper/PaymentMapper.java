package com.tw.mapper;

import com.tw.domain.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentMapper {
    int createPayment(@Param("approvementId") int approvementId, @Param("payment") Payment payment);
}
