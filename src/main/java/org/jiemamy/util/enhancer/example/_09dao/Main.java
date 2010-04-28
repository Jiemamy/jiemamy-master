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
package org.jiemamy.util.enhancer.example._09dao;

import java.util.Arrays;
import java.util.Properties;

import org.jiemamy.utils.enhancer.Enhance;
import org.jiemamy.utils.enhancer.Enhancer;
import org.jiemamy.utils.enhancer.InterfaceEnhancer;

/**
 * このパッケージのプログラムエントリ。
 * @version $Date$
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class Main {

    /**
     * プログラムエントリ。
     * @param args 無視される
     * @throws Exception うまくいかない場合
     */
    public static void main(String...args) throws Exception {
        Enhancer<DaoFactory> enhancer = new InterfaceEnhancer<DaoFactory>(
                DaoFactory.class,
                Properties.class, // Propertiesを実装するようにする
                Arrays.<Enhance>asList(
                    // Daoの実装を生成する際に、 DaoFactory.newInstanceの引数でうまい具合に初期化
                    new Enhance(new NewPointcut(), new DaoInitializer())
                )
        );
        System.out.println("ファクトリを生成");
        DaoFactory factory = enhancer.getFactory().newInstance();

        System.out.println("プロダクトを生成");
        Dao dao = factory.newInstance(Main.class.getResource("example.properties"));

        System.out.println("helloプロパティの表示");
        String hello = dao.getProperty("hello");
        System.out.printf("dao.getProperty(\"hello\") = %s%n", hello);
    }
}
