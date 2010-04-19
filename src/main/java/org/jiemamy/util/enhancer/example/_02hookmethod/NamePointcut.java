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
package org.jiemamy.util.enhancer.example._02hookmethod;

import javassist.CtBehavior;
import javassist.CtClass;

import org.jiemamy.utils.enhancer.InvocationPointcut;

/**
 * 特定の名前を持つ実行を対象とするポイントカット。
 * @version $Date: 2009-09-21 02:27:46 +0900 (月, 21  9 2009) $
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class NamePointcut implements InvocationPointcut {
    
    private final String name;
    
    /**
     * インスタンスを生成する。
     * @param name 対象の名前
     */
    public NamePointcut(String name) {
        super();
        this.name = name;
    }

    public boolean isTarget(CtClass self, CtBehavior behavior) {
        return behavior.getName().equals(name);
    }
}
