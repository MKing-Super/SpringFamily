package com.mk.util;

import java.util.UUID;

public class createUUID {
    public static  String getUUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
