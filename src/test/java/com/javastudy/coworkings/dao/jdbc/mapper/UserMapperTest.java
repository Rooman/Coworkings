package com.javastudy.coworkings.dao.jdbc.mapper;

import com.javastudy.coworkings.entity.User;
import com.javastudy.coworkings.entity.UserRole;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserMapperTest {

    @Test
    void testRowMap() throws SQLException {
        //prepare
        UserMapper userMapper = new UserMapper();
        ResultSet resultSetMock = mock(ResultSet.class);

        when(resultSetMock.getLong("id")).thenReturn(1L);
        when(resultSetMock.getString("username")).thenReturn("testUser");
        when(resultSetMock.getString("password")).thenReturn("testPassword");
        when(resultSetMock.getString("fullName")).thenReturn("Name Surname");
        when(resultSetMock.getString("role")).thenReturn("USER");
        when(resultSetMock.getString("tel")).thenReturn("095332222");
        when(resultSetMock.getString("email")).thenReturn("email@mailbox.net");
        when(resultSetMock.getString("city")).thenReturn("Kyiv");
        when(resultSetMock.getString("sole")).thenReturn("testVeryLongSole");

        //when
        User actual = userMapper.mapRow(resultSetMock);

        //then
        assertEquals(1L, actual.getId());
        assertEquals("testUser", actual.getUsername());
        assertEquals("testPassword", actual.getPassword());
        assertEquals("Name Surname", actual.getFullName());
        assertEquals(UserRole.USER, actual.getUserRole());
        assertEquals("095332222", actual.getTel());
        assertEquals("email@mailbox.net", actual.getEmail());
        assertEquals("Kyiv", actual.getCity());
        assertEquals("testVeryLongSole", actual.getSole());
    }
}