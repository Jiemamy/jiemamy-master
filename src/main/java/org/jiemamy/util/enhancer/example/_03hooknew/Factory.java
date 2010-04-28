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

import java.util.List;

/**
 * リストを生成するファクトリ。
 * @version $Date$
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public interface Factory {
   
    /**
     * 空のリストを返す。
     * @param <T> リストの型
     * @return 生成したリスト
     */
    <T> List<T> newList();
    
    /**
     * 空のリンクリストを返す。
     * @param <T> リストの型
     * @param title リストのタイトル
     * @return 生成したリスト
     */
    <T> List<T> newTitledList(String title);
}
