package com.pictet.book.web.util;

public class Common {

    public static Long toLong(Integer value) {
        return value != null ? value.longValue() : null;
    }
}
