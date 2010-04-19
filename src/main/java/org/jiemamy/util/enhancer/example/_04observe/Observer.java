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
package org.jiemamy.util.enhancer.example._04observe;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jiemamy.utils.enhancer.Invocation;
import org.jiemamy.utils.enhancer.InvocationHandler;

/**
 * 第1引数を監視してコールバックオブジェクトに移譲する。
 * @version $Date: 2009-09-21 02:27:46 +0900 (月, 21  9 2009) $
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class Observer implements InvocationHandler {
    
    /**
     * コールバックインターフェース
     */
    public interface Callback {
        
        /**
         * このハンドラから呼び出される
         * @param source 呼び出し元
         * @param value ハンドルした結果
         */
        void handle(Method source, Object value);
    }

    private Callback delegate;
    
    /**
     * インスタンスを生成する。
     * @param delegate 移譲先のオブジェクト
     */
    public Observer(Callback delegate) {
        super();
        this.delegate = delegate;
    }

    public Object handle(Invocation invocation) throws Throwable {
        Object result;
        try {
            // 実際の処理を実行する
            result = invocation.proceed();
        }
        catch (InvocationTargetException e) {
            throw e.getCause();
        }
        
        // 移譲する
        Object target = invocation.getArguments()[0];
        delegate.handle((Method) invocation.getTarget(), target);

        // 監視結果を引き渡す
        return result;
    }
}
