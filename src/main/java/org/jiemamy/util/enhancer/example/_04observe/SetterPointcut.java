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
package org.jiemamy.util.enhancer.example._04observe;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.NotFoundException;

import org.jiemamy.utils.enhancer.InvocationPointcut;

/**
 * {@code set}から始まるメソッドのみを対象とするポイントカット。
 * @version $Date$
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class SetterPointcut implements InvocationPointcut {
    
    public boolean isTarget(CtClass self, CtBehavior behavior) {
        // set* だけに制限
        if (behavior.getName().startsWith("set") == false) {
            return false;
        }
        
        // 引数一つだけ
        try {
            return behavior.getParameterTypes().length == 1;
        }
        catch (NotFoundException e) {
            return false;
        }
    }
}
