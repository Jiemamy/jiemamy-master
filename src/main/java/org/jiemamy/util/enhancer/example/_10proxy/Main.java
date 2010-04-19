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
package org.jiemamy.util.enhancer.example._10proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.concurrent.Callable;

import org.jiemamy.utils.enhancer.Enhance;
import org.jiemamy.utils.enhancer.Enhancer;
import org.jiemamy.utils.enhancer.InterfaceEnhancer;
import org.jiemamy.utils.enhancer.driver.ProxyDriver;

/**
 * このパッケージのプログラムエントリ。
 * @version $Date: 2009-10-05 20:36:43 +0900 (月, 05 10 2009) $
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class Main {

    /**
     * プログラムエントリ。
     * @param args 無視される
     * @throws Exception うまくいかない場合
     */
    public static void main(String...args) throws Exception {
        Enhancer<ExecutableFactory> enhancer = new InterfaceEnhancer<ExecutableFactory>(
                ExecutableFactory.class,
                Object.class, // 今回はnew時にproceed()を呼ばないので親クラスは関係ない
                Arrays.asList(new Enhance[] {
                        // Runnableのインスタンス生成の代わりに、下記のハンドラが登録されたProxyを返す
                        ProxyDriver.newEnhance(Runnable.class, new java.lang.reflect.InvocationHandler() {
                            public Object invoke(Object proxy, Method method, Object[] a) {
                                System.out.printf("Runnableのメソッドが呼ばれた(%s)%n", method);
                                System.out.println("ここでは特に何もしない");
                                return null;
                            }
                        }),
                        // Callableのインスタンス生成の代わりに、下記のハンドラが登録されたProxyを返す
                        ProxyDriver.newEnhance(Callable.class, new java.lang.reflect.InvocationHandler() {
                            public Object invoke(Object proxy, Method method, Object[] a) {
                                System.out.printf("Callableのメソッドが呼ばれた(%s)%n", method);
                                System.out.println("ので、\"Hello\"を返しておく");
                                return "Hello";
                            }
                        }),
                })
        );
        System.out.println("ファクトリを生成");
        ExecutableFactory factory = enhancer.getFactory().newInstance();

        System.out.println("プロダクトを生成");
        Runnable runnable = factory.newRunnable();
        Callable<String> callable = factory.newCallable();

        System.out.println("プロダクトはいずれもProxyになってるはず");
        System.out.printf("runnable.getClass() is proxy = %s%n", Proxy.isProxyClass(runnable.getClass()));
        System.out.printf("callable.getClass() is proxy = %s%n", Proxy.isProxyClass(callable.getClass()));

        System.out.println("run()メソッドの呼び出し");
        runnable.run();

        System.out.println("call()メソッドの呼び出し");
        System.out.printf("callable.call() = \"%s\"%n", callable.call());
    }
}
