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
package org.jiemamy.util.enhancer.example._01hello;

import javassist.CtBehavior;
import javassist.CtClass;

import org.jiemamy.utils.enhancer.InvocationPointcut;

/**
 * {@link Greetings#say()}のみを対象とするポイントカット。
 * @version $Date: 2009-09-21 02:27:46 +0900 (月, 21  9 2009) $
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class SayPointcut implements InvocationPointcut {
    
    public boolean isTarget(CtClass self, CtBehavior behavior) {
        
        // Greetingクラスのみに制限
        if (self.getName().equals(Greetings.class.getName()) == false) {
            return false;
        }
        
        // sayという名前だけに制限
        return behavior.getName().equals("say");
    }
}
