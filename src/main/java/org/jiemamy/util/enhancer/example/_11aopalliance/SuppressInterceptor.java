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
package org.jiemamy.util.enhancer.example._11aopalliance;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * {@link AbstractMethodError}を抑制するAOP Allianceのインターセプタ。
 * @version $Date$
 * @author Suguru ARAKAWA (Gluegent, Inc.)
 */
public class SuppressInterceptor implements MethodInterceptor {
	
	@SuppressWarnings("unused")
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.printf("%s の呼び出しをフック%n", invocation.getMethod());
		System.out.println("invocation.proceed()するとAbstractMethodErrorがスローされるからやめとく");
		return null;
	}
}
