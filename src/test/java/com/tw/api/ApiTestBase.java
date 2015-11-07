package com.tw.api;

import com.tw.api.exception.InternalExceptionMapper;
import com.tw.mapper.CategoryMapper;
import com.tw.mapper.PolicyMapper;
import com.tw.mapper.UserMapper;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Application;

@RunWith(MockitoJUnitRunner.class)
public class ApiTestBase extends JerseyTest {
    @Mock
    PolicyMapper policyMapper;
    @Mock
    CategoryMapper categoryMapper;
    @Mock
    UserMapper userMapper;

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
                    }
                });
    }
}
