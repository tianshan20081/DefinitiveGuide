package com.aoeng.degu.utils.net.asyncthhpclient;

import java.io.IOException;

@SuppressWarnings("serial")
public class Base64DataException extends IOException {
    public Base64DataException(String detailMessage) {
        super(detailMessage);
    }
}