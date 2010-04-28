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

import org.jiemamy.utils.enhancer.Enhance;
import org.jiemamy.utils.enhancer.FactoryEnhancer;
import org.jiemamy.utils.enhancer.Pointcuts;

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
                Factory.class, // ファクトリのインターフェース
                FactoryImpl.class, // 実装クラス
                // new ArrayList() は factory.newTitledList() に捻じ曲げる
                // ※先頭のほうにこの拡張を定義しないと、
                //   以降の new ArrayList()に対するすべての拡張がきかなくなる
                new Enhance(new ArrayListPointcut(), new ForceTitledList()),
                // new TitledList() はタイトルが繰り返される
                new Enhance(
                    new TitledListPointcut(),
                    new DuplicateStringParam()),
                // new ArrayList()と new TitledList() は末尾に文字列を追加する
                new Enhance(
                    Pointcuts.or(
                        new ArrayListPointcut(),
                        new TitledListPointcut()),
                    new AppendToList())
        );
        
        System.out.println("ファクトリを生成");
        Factory factory = enhancer.getEnhanced().newInstance();
        
        System.out.println("プロダクトを生成");
        System.out.println("newList() = " + factory.newList());
    }
}
