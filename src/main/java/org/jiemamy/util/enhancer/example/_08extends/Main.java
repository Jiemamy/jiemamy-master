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
package org.jiemamy.util.enhancer.example._08extends;

import java.util.Arrays;
import java.util.Date;

import org.jiemamy.utils.enhancer.Enhance;
import org.jiemamy.utils.enhancer.Enhancer;
import org.jiemamy.utils.enhancer.InterfaceEnhancer;

/**
 * このパッケージのプログラムエントリ。
 * @version $Date: 2009-10-08 22:05:56 +0900 (木, 08 10 2009) $
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class Main {

    /**
     * プログラムエントリ。
     * @param args 無視される
     * @throws Exception うまくいかない場合
     */
    public static void main(String...args) throws Exception {
        Enhancer<GreetingsFactory> enhancer = new InterfaceEnhancer<GreetingsFactory>(
                GreetingsFactory.class,
                Date.class, // Dateを実装するようにする
                Arrays.<Enhance>asList(new Enhance[] {
                        // メソッドなどはフックしない
                })
        );
        System.out.println("ファクトリを生成");
        GreetingsFactory factory = enhancer.getFactory().newInstance();

        System.out.println("プロダクトを生成");
        GreetingsInterface greetings = factory.newGreetings();

        if (greetings instanceof Date) {
            System.out.println("なぜかDateを実装しているので");
            System.out.println(greetings);
            System.out.println("今の時刻が表示される");
        }
    }
}
