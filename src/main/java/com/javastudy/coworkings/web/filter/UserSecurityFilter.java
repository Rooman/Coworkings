package com.javastudy.coworkings.web.filter;

import com.javastudy.coworkings.entity.UserRole;

import java.util.EnumSet;
import java.util.Set;

public class UserSecurityFilter extends AbstractSecurityFilter {
    @Override
    Set<UserRole> getAcceptedRoles() {
        return EnumSet.of(UserRole.USER);
    }
}
