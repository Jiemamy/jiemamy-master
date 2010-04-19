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
package org.jiemamy.util.enhancer.example._02hookmethod;

import java.lang.reflect.InvocationTargetException;

import org.jiemamy.utils.enhancer.Invocation;
import org.jiemamy.utils.enhancer.InvocationHandler;

/**
 * 最初の引数を3倍する。
 * @version $Date: 2009-09-21 02:27:46 +0900 (月, 21  9 2009) $
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class FirstParamMult3 implements InvocationHandler {

    public Object handle(Invocation invocation) throws Throwable {
        
        // getArguments の内容を書き換えると与える引数も変わる
        Object[] arguments = invocation.getArguments();
        arguments[0] = (Integer) arguments[0] * 3;
        try {
            // 実際に実行する
            return invocation.proceed();
        }
        catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
