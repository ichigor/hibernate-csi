package br.ufsm.csi.seguranca.util;

import java.security.SecureRandom;

public class util {
    private static SecureRandom random = new SecureRandom();

    public static synchronized String generateToken() {
        long longToken = Math.abs( random.nextLong() );
        String random = Long.toString( longToken, 16 );
        return random;
    }
}
