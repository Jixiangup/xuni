package com.bnyte.xutils.generate.util;

import java.util.Collection;

/**
 * 数组集合工具类
 * @auther bnyte
 * @date 2021-10-11 16:01
 * @email bnytezz@gmail.com
 */
public class CollectionUtils {

    public static boolean isEmpty (Collection<?> collection) {
        if (collection == null)
            return true;
        return collection.isEmpty();
    }

}
