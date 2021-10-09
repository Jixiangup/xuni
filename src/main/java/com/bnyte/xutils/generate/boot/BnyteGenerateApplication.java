package com.bnyte.xutils.generate.boot;

import com.bnyte.xutils.generate.entity.MySQL;
import com.bnyte.xutils.generate.enums.GenerateType;
import com.bnyte.xutils.generate.handler.FreeMarkerHandle;

/**
 * 执行器
 * @auther bnyte
 * @date 2021-10-08 14:51
 * @email bnytezz@gmail.com
 */
public class BnyteGenerateApplication {

    // 执行代码生成器
    public static void execute (String filepath, MySQL mySQL, String packageName, GenerateType generateType) throws Exception {
        FreeMarkerHandle.builder(filepath, mySQL, packageName, generateType);
    }

}
