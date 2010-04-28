/*
 * Copyright 2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.jiemamy.util.enhancer.example._09dao;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

import org.jiemamy.utils.enhancer.Invocation;
import org.jiemamy.utils.enhancer.InvocationHandler;

/**
 * {@link DaoFactory#newInstance(URL)}のあたりをフックする。
 * @version $Date$
 * @author Suguru ARAKAWA
 */
public class DaoInitializer implements InvocationHandler {

    public Object handle(Invocation invocation) throws Throwable {
        System.out.printf("%s の呼び出しをフック%n", invocation);

        System.out.println("とりあえずインスタンスを生成");
        Object result;
        try {
            result = invocation.proceed();
        }
        catch (InvocationTargetException e) {
            throw e.getCause();
        }

        // なぜかインスタンスがProperties
        System.out.printf("%sの親クラスは%s%n",
            result.getClass().getName(),
            result.getClass().getSuperclass().getName());

        // そして引数にはURLが来てる
        System.out.printf("そして引数には%sが来る%n", Arrays.toString(invocation.getArguments()));
        URL url = (URL) invocation.getArguments()[0];

        // ロードしておく
        System.out.printf("プロパティを%sの内容で初期化%n", url);
        InputStream in = url.openStream();
        try {
            ((Properties) result).load(in);
        }
        finally {
            in.close();
        }

        System.out.printf("初期化が終わったインスタンスの内容は%s%n", result.toString());

        // 最後にnewした結果として返す
        return result;
    }
}
