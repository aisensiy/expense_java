package com.tw.api;

import com.tw.api.exception.InternalExceptionMapper;
import com.tw.factory.ExpenseRequestFactory;
import com.tw.mapper.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Application;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class ApiTestBase extends JerseyTest {
    @Mock
    PolicyMapper policyMapper;
    @Mock
    CategoryMapper categoryMapper;
    @Mock
    UserMapper userMapper;
    @Mock
    ExpenseRequestMapper expenseRequestMapper;
    @Mock
    ExpenseRequestItemMapper expenseRequestItemMapper;
    @Mock
    RecipeMapper recipeMapper;
    @Mock
    ExpenseRequestFactory expenseRequestFactory;
    @Mock
    ApprovementMapper approvementMapper;
    @Mock
    PaymentMapper paymentMapper;

    @Override
    protected Application configure() {
        return new ResourceConfig().packages("com.tw.api")
                .register(InternalExceptionMapper.class)
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(policyMapper).to(PolicyMapper.class);
                        bind(categoryMapper).to(CategoryMapper.class);
                        bind(userMapper).to(UserMapper.class);
                        bind(expenseRequestMapper).to(ExpenseRequestMapper.class);
                        bind(expenseRequestItemMapper).to(ExpenseRequestItemMapper.class);
                        bind(recipeMapper).to(RecipeMapper.class);
                        bind(expenseRequestFactory).to(ExpenseRequestFactory.class);
                        bind(approvementMapper).to(ApprovementMapper.class);
                        bind(paymentMapper).to(PaymentMapper.class);
                    }
                });
    }
}
