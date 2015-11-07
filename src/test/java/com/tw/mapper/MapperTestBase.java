package com.tw.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;

public class MapperTestBase {
    protected SqlSession sqlSession;
    @Before
    public void setUp() throws Exception {
        sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.rollback();
        sqlSession.close();
    }
}
