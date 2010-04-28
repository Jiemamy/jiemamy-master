/*
 * Copyright 2009 Jiemamy Project and the Others.
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
package org.jiemamy.util.enhancer.example._01hello;

import java.lang.reflect.InvocationTargetException;

import org.jiemamy.utils.enhancer.Invocation;
import org.jiemamy.utils.enhancer.InvocationHandler;

/**
 * 処理をハンドルして前後でトレース情報を出力する。
 * @version $Date$
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class HandleAndTrace implements InvocationHandler {

    public Object handle(Invocation invocation) throws Throwable {
        
        // 最初に before:でトレース
        System.out.println("before: " + invocation.getTarget());
        try {
            // 実際の処理を実行する
            return invocation.proceed();
        }
        catch (InvocationTargetException e) {
            throw e.getCause();
        }
        finally {
            // 最後にafter:でトレース
            System.out.println("after: " + invocation.getTarget());
        }
    }
}
