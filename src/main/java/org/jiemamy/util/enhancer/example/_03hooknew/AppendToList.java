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
package org.jiemamy.util.enhancer.example._03hooknew;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.jiemamy.utils.enhancer.Invocation;
import org.jiemamy.utils.enhancer.InvocationHandler;

/**
 * 結果のリストの末尾に余計な文字列を追加する。
 * @version $Date: 2009-09-21 02:27:46 +0900 (月, 21  9 2009) $
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class AppendToList implements InvocationHandler {

    public Object handle(Invocation invocation) throws Throwable {
        try {
            // 実際の処理を実行する
            @SuppressWarnings("unchecked")
            
            // 結果の末尾に単純名でも追加しておく
            List<Object> list = (List<Object>) invocation.proceed();
            list.add(invocation.getTarget().getDeclaringClass().getSimpleName());
            
            return list;
        }
        catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
