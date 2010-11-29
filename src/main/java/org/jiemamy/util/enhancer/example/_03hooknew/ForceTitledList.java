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

import org.jiemamy.utils.enhancer.Invocation;
import org.jiemamy.utils.enhancer.InvocationHandler;

/**
 * {@link java.util.ArrayList}を生成しようとしたら、
 * {@link Factory#newTitledList(String)}を代わりに呼び出す。
 * @version $Date$
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class ForceTitledList implements InvocationHandler {
	
	@SuppressWarnings("unused")
	public Object handle(Invocation invocation) throws Throwable {
		Factory f = (Factory) invocation.getInvoker();
		return f.newTitledList("ByEnhancer");
	}
}
