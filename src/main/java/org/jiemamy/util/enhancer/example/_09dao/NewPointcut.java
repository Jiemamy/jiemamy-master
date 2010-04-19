/*
 * Copyright 2009 the Seasar Foundation and the Others.
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

import java.net.URL;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.NotFoundException;

import org.jiemamy.utils.enhancer.InvocationPointcut;

/**
 * {@link Dao}をnewする(実際にはその実装)個所をフック。
 * @version $Date: 2009-10-08 22:05:56 +0900 (木, 08 10 2009) $
 * @author Suguru ARAKAWA
 */
public class NewPointcut implements InvocationPointcut {

    public boolean isTarget(CtClass self, CtBehavior behavior) {
        try {
            // Daoのみ
            if (self.getName().equals(Dao.class.getName()) == false) {
                return false;
            }

            // newのみ
            if ((behavior instanceof CtConstructor) == false) {
                return false;
            }

            // 引数は(URL)のみ
            CtClass[] params = behavior.getParameterTypes();
            if (params.length != 1 || params[0].getName().equals(URL.class.getName()) == false) {
                return false;
            }

            return true;
        }
        catch (NotFoundException e) {
            return false;
        }
    }
}
