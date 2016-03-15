package com.meleeChat.info;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class SecureRandomString {
    private SecureRandom random = new SecureRandom();

    public String nextString() {
        return new BigInteger(130, random).toString(32);
    }

}
