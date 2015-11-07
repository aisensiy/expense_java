package com.tw.mapper;

import com.tw.domain.Approvement;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApprovementMapperTest extends MapperTestBase {

    private ApprovementMapper mapper;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        mapper = sqlSession.getMapper(ApprovementMapper.class);
    }

    @Test
    public void should_create_approvment() throws Exception {
        Approvement approvement = new Approvement(1);
        mapper.createApprovement(1, approvement);
        assertThat(approvement.getId(), not(0));
    }


}