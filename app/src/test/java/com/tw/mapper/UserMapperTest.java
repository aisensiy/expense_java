package com.tw.mapper;

import com.tw.domain.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class UserMapperTest extends MapperTestBase {

    private UserMapper userMapper;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void should_create_new_user() throws Exception {
        User user = new User("employee");
        userMapper.createUser(user);
        assertThat(user.getId(), not(0));
    }

    @Test
    public void should_get_user_by_id() throws Exception {
        User expected = new User("manager");
        userMapper.createUser(expected);

        User user = userMapper.getUserById(expected.getId());
        assertThat(user.getId(), is(user.getId()));
        assertThat(user.getRole(), is(user.getRole()));

    }
}