package com.tw;

import com.tw.api.exception.ResourceNotFoundExceptionHandler;
import com.tw.api.filter.OpenSessionInViewRequestFilter;
import com.tw.api.filter.OpenSessionInViewResponseFilter;
import com.tw.factory.ExpenseRequestFactory;
import com.tw.mapper.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class Main {

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://0.0.0.0/").port(8080).build();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final HttpServer httpServer = startServer();

        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                httpServer.shutdownNow();
            }
        }
    }

    private static HttpServer startServer() {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        final SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(sqlSessionFactory);

        final UserMapper userMapper = sqlSessionManager.getMapper(UserMapper.class);
        final CategoryMapper categoryMapper = sqlSessionManager.getMapper(CategoryMapper.class);
        final PolicyMapper policyMapper = sqlSessionManager.getMapper(PolicyMapper.class);
        final ExpenseRequestMapper expenseRequestMapper = sqlSessionManager.getMapper(ExpenseRequestMapper.class);
        final ExpenseRequestItemMapper expenseRequestItemMapper = sqlSessionManager.getMapper(ExpenseRequestItemMapper.class);
        final ApprovementMapper approvementMapper = sqlSessionManager.getMapper(ApprovementMapper.class);
        final PaymentMapper paymentMapper = sqlSessionManager.getMapper(PaymentMapper.class);
        final RecipeMapper recipeMapper = sqlSessionManager.getMapper(RecipeMapper.class);
        final ExpenseRequestFactory expenseRequestFactory = new ExpenseRequestFactory(expenseRequestMapper, expenseRequestItemMapper, recipeMapper);


        final ResourceConfig config = new ResourceConfig()
                .packages("com.tw.api")
                .register(ResourceNotFoundExceptionHandler.class)
                .register(OpenSessionInViewRequestFilter.class)
                .register(OpenSessionInViewResponseFilter.class)
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(policyMapper).to(PolicyMapper.class);
                        bind(categoryMapper).to(CategoryMapper.class);
                        bind(userMapper).to(UserMapper.class);
                        bind(expenseRequestMapper).to(ExpenseRequestMapper.class);
                        bind(expenseRequestItemMapper).to(ExpenseRequestItemMapper.class);
                        bind(expenseRequestFactory).to(ExpenseRequestFactory.class);
                        bindAsContract(ExpenseRequestFactory.class);
                        bind(sqlSessionManager).to(SqlSessionManager.class);
                        bind(expenseRequestFactory).to(ExpenseRequestFactory.class);
                        bind(recipeMapper).to(RecipeMapper.class);
                        bind(approvementMapper).to(ApprovementMapper.class);
                        bind(paymentMapper).to(PaymentMapper.class);
                    }
                });
        return GrizzlyHttpServerFactory.createHttpServer(getBaseURI(), config);
    }

}
