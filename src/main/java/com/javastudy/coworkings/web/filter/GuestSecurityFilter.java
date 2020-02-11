package com.javastudy.coworkings.web.filter;

import com.javastudy.coworkings.entity.UserRole;

import java.util.EnumSet;
import java.util.Set;

public class GuestSecurityFilter extends AbstractSecurityFilter {
    private static final Set<UserRole> acceptedRoles = EnumSet.of(UserRole.GUEST);

    @Override
    Set<UserRole> getAcceptedRoles() {
        return acceptedRoles;
    }
}
