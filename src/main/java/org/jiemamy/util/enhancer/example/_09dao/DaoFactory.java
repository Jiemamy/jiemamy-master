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

import java.io.IOException;
import java.net.URL;

/**
 * {@link Dao}オブジェクトのみを生成するファクトリのインターフェース。
 * @version $Date: 2009-10-08 22:05:56 +0900 (木, 08 10 2009) $
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public interface DaoFactory {

    /**
     * @param url 設定情報?
     * @return 生成したDao
     * @throws IOException 初期化に失敗した場合
     */
    Dao newInstance(URL url) throws IOException;
}
