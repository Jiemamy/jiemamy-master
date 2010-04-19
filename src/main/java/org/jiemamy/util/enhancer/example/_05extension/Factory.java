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

/**
 * いくつかの拡張方法を説明するファクトリ。
 * @version $Date: 2009-09-21 02:27:46 +0900 (月, 21  9 2009) $
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public interface Factory {
    
    /**
     * @return Hoge
     */
    Hoge newHoge();
    
    /**
     * @return Foo
     */
    Foo newFoo();
    
    /**
     * エンハンサのための拡張ポイント。
     * @param <T> 生成するオブジェクトの型
     * @param type 生成するオブジェクトの型
     * @return　生成したオブジェクト、失敗した場合は{@code null}
     */
    <T> T newInstance(Class<T> type);
}
