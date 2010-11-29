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

import org.jiemamy.utils.enhancer.Invocation;
import org.jiemamy.utils.enhancer.InvocationHandler;

/**
 * {@link FactoryImpl#newInstance(Class)}を拡張する。
 * この拡張は、{@code new FactoryExtension()}をフックして
 * 代わりに{@code new new ExtensionEnhancer.MyExtension()}を返すことで実現される。
 * なお、{@link HogeEnhancer}の手法と同様に
 * {@link FactoryExtension#newInstance(Class)}をフックする方法でもよい。
 * @version $Date$
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class ExtensionEnhancer implements InvocationHandler {
	
	@SuppressWarnings("unused")
	public Object handle(Invocation invocation) throws Throwable {
		// new FactoryExtension() -> new ExtensionEnhancer.MyExtension();
		return new ExtensionEnhancer.MyExtention();
	}
	

	/**
	 * {@link FactoryExtension}の拡張。
	 * {@link Bar}クラスのインスタンスを生成できる。
	 * @version $Date$
	 * @author Suguru ARAKAWA
	 */
	public class MyExtention extends FactoryExtension {
		
		@Override
		public <T>T newInstance(Class<T> type) {
			if (type == Bar.class) {
				return type.cast(new Bar());
			}
			return null;
		}
	}
}
