package com.invest.getdata;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface Data<T> {
    public T getData() throws IOException;
}
