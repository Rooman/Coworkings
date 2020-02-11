package com.javastudy.coworkings.dao.jdbc;

import com.javastudy.coworkings.ServiceLocator;
import com.javastudy.coworkings.dao.UserDao;
import com.javastudy.coworkings.entity.User;
import com.javastudy.coworkings.entity.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import static com.javastudy.coworkings.util.DataSourceLoader.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JdbcUserDaoTest {
    private UserDao userDao = ServiceLocator.getService(UserDao.class);

    @BeforeAll
    static void before() throws IOException, URISyntaxException {
        init("db/dml_db_data.sql");
        ServiceLocator.register(UserDao.class, new JdbcUserDao(getDataSource()));
    }

    @BeforeEach
    void setUp() throws SQLException {
        reloadData();
    }

    @AfterEach
    void tearDown() throws SQLException {
        cleanSchema();
    }

    @Test
    void getById() {

        User expectedUser = User.builder()
                .id((long) 1)
                .city("Kiev")
                .fullName("First User")
                .userRole(UserRole.USER)
                .email("first.user@gmail.com")
                .sole("sole123")
                .password("pass123")
                .tel("+380630636363")
                .username("firstUser")
                .build();

        User actualUser = userDao.getById(1);

        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getCity(), actualUser.getCity());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getFullName(), actualUser.getFullName());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        assertEquals(expectedUser.getUserRole(), actualUser.getUserRole());
        assertEquals(expectedUser.getTel(), actualUser.getTel());
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
    }
}
