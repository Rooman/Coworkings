package com.javastudy.coworkings.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DefaultSecurityService implements SecurityService {
    @Override
    public Session login(String name, String password) {
        return null;
    }

    @Override
    public void logout(String token) {

    }

    @Override
    public Session getSession(String token) {
        return null;
    }

    @Override
    public String hashPassword(String password, String sole) throws NoSuchAlgorithmException {
        String passNSole = password + sole;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(passNSole.getBytes());
        BigInteger bigInt = new BigInteger(1, messageDigest);
        String hashedPass = bigInt.toString(16);

        return hashedPass;
    }
}
