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
 * {@link Factory}の実装クラス。
 * @version $Date$
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class FactoryImpl implements Factory {

    public Hoge newHoge() {
        return new Hoge();
    }

    public Foo newFoo() {
        return new Foo();
    }

    public <T> T newInstance(Class<T> type) {
        // new FactoryExtension() とすることで、FactoryExtensionも
        // FactoryImplのプロダクト扱いとなる。
        // FactoryExtension#newInstance を拡張することで、
        // このメソッドの戻り値も好きに変更することができる。
        return new FactoryExtension().newInstance(type);
    }
}
