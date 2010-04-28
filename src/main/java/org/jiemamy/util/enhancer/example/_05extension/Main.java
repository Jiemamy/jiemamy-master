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
package org.jiemamy.util.enhancer.example._05extension;

import org.jiemamy.utils.enhancer.Enhance;
import org.jiemamy.utils.enhancer.FactoryEnhancer;

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
        
        FactoryEnhancer<Factory> enhancer = new FactoryEnhancer<Factory>(
                Factory.class,
                FactoryImpl.class,
                new Enhance( // Hogeの拡張
                    new HogeEnhancePointcut(), new HogeEnhancer()),
                new Enhance( // Fooの拡張
                    new FooEnhancePointcut(), new FooEnhancer()),
                new Enhance( // 拡張ポイントによるBarの有効化
                    new ExtensionEnhancePointcut(), new ExtensionEnhancer())
        );
        
        Factory original = new FactoryImpl();
        Factory enhanced = enhancer.getEnhanced().newInstance();
        
        System.out.println("=== Hoge ===");
        System.out.println("[original] " + original.newHoge());
        System.out.println("[enhanced] " + enhanced.newHoge());

        System.out.println("=== Foo ===");
        System.out.println("[original] " + original.newFoo());
        System.out.println("[enhanced] " + enhanced.newFoo());
        
        System.out.println("=== Bar ===");
        System.out.println("[original] " + original.newInstance(Bar.class));
        System.out.println("[enhanced] " + enhanced.newInstance(Bar.class));
    }
}
