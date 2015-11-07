package com.tw.mapper;

import com.tw.domain.Payment;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class PaymentMapperTest extends MapperTestBase {

    private PaymentMapper mapper;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        mapper = sqlSession.getMapper(PaymentMapper.class);
    }

    @Test
    public void should_create_payment() throws Exception {
        Payment payment = new Payment();
        mapper.createPayment(1, payment);
        assertThat(payment.getId(), not(0));
    }
}