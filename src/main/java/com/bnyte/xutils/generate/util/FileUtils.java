package com.bnyte.xutils.generate.util;

import java.io.File;

/**
 * @auther bnyte
 * @date 2021-10-09 15:58
 * @email bnytezz@gmail.com
 */
public class FileUtils {

    /**
     * 传入以"."作为分割的包名称来进行循环递归创建文件夹，如果packagePath的值是"/root/users/bnyte/test"而packageName的值是"com.bnyte.yaml"那么此时创建出来的文件则是"/root/users/bnyte/test/com.bnyte.yaml"
     * @param packagePath 需要制定的包所在的绝对路径 如：/root/users/bnyte/test
     * @param packageName 包名称 如：com.bnyte.yaml
     */
    public static void createPackageFolders(String packagePath, String packageName) {
        StringBuilder createMkdirsPath = new StringBuilder(packagePath);
        String[] split = packageName.split("\\.");
        for (String s : split) {
            createMkdirsPath
                    .append(File.separator)
                    .append(s);
        }
        File file = new File(createMkdirsPath.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
    }

}
