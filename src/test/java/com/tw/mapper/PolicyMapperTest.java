package com.tw.mapper;

import com.tw.domain.Policy;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class PolicyMapperTest extends MapperTestBase {

    private PolicyMapper mapper;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        mapper = sqlSession.getMapper(PolicyMapper.class);
    }

    @Test
    public void should_create_policy() throws Exception {
        Policy expected = new Policy(1000, new Timestamp(1446875186752L));
        mapper.createPolicy(1, expected);
        assertThat(expected.getId(), not(0));
    }
}
