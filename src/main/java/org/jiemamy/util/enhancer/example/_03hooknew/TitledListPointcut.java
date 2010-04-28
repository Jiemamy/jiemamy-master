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

import javassist.CtBehavior;
import javassist.CtClass;

import org.jiemamy.utils.enhancer.InvocationPointcut;
import org.jiemamy.utils.enhancer.Pointcuts;

/**
 * {@link TitledList}の生成を対象とするポイントカット。
 * @version $Date$
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class TitledListPointcut implements InvocationPointcut {
    
    public boolean isTarget(CtClass self, CtBehavior behavior) {
        // コンストラクタのみ
        if (Pointcuts.CONSTRUCTORS.isTarget(self, behavior) == false) {
            return false;
        }
        // TitledListのみ
        if (self.getName().equals(TitledList.class.getName()) == false) {
            return false;
        }

        return true;
    }
}
