package com.javastudy.coworkings.security;

import com.javastudy.coworkings.entity.User;
import com.javastudy.coworkings.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class DefaultSecurityService implements SecurityService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final int DEFAULT_EXPIRE_DAYS = 1;
    private int expireDays;
    private List<Session> sessions = new ArrayList<>();
    private UserService userService;

    public DefaultSecurityService(UserService userService) {
        this.userService = userService;
        expireDays = DEFAULT_EXPIRE_DAYS;
    }

    @Override
    public Session login(String username, String password) {
        User user = userService.getByUsername(username);
        String hashedPassword = hashPassword(password, user.getSole());

        if (!hashedPassword.equals(user.getPassword())) {
            return null;
        }

        Session session = Session.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryTime(LocalDateTime.now().plusDays(expireDays))
                .build();

        sessions.add(session);
        return session;
    }

    @Override
    public void logout(String token) {
        sessions.removeIf(session -> token.equals(session.getToken()));
    }

    @Override
    public Session getSession(String token) {
        Iterator<Session> sessionIterator = sessions.iterator();
        while (sessionIterator.hasNext()) {
            Session session = sessionIterator.next();
            if (token.equals(session.getToken())) {
                if (session.getExpiryTime().isAfter(LocalDateTime.now())) {
                    return session;
                }
                sessionIterator.remove();
            }
        }
        return null;
    }

    private String hashPassword(String password, String sole){
        String passNSole = password + sole;

        MessageDigest md = null;
        String algorithm = "SHA-256";
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error happened while geting hashing algorithm {} for MessageDigest", algorithm);
        }

        byte[] messageDigest = md.digest(passNSole.getBytes());
        BigInteger bigInt = new BigInteger(1, messageDigest);
        String hashedPass = bigInt.toString(16);

        return hashedPass;
    }

    public void setExpireDays(int expireDays) {
        this.expireDays = expireDays;
    }
}
