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
        User actualUser = User.newBuilder()
                .setId((long) 1)
                .setCity("Kiev")
                .setFullName("First User")
                .setRole(UserRole.USER)
                .setEmail("first.user@gmail.com")
                .setSole("sole123")
                .setPassword("pass123")
                .setTel("+380630636363")
                .setUsername("firstUser")
                .build();

        User factUser = userDao.getById(1);

        assertEquals(actualUser.getId(), factUser.getId());
        assertEquals(actualUser.getCity(), factUser.getCity());
        assertEquals(actualUser.getEmail(), factUser.getEmail());
        assertEquals(actualUser.getFullName(), factUser.getFullName());
        assertEquals(actualUser.getPassword(), factUser.getPassword());
        assertEquals(actualUser.getRole(), factUser.getRole());
        assertEquals(actualUser.getTel(), factUser.getTel());
        assertEquals(actualUser.getUsername(), factUser.getUsername());
    }
}
