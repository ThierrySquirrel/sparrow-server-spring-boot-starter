/**
 * Copyright 2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.thierrysquirrel.sparrow.server.init.core.thread;

/**
 * ClassName: AbstractSparrowServerInitializationThread
 * Description:
 * date: 2020/12/7 3:11
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public abstract class AbstractSparrowServerInitializationThread implements Runnable {
	private final String sparrowServerUrl;

	protected AbstractSparrowServerInitializationThread(String sparrowServerUrl) {
		this.sparrowServerUrl = sparrowServerUrl;
	}

	/**
	 * Initialization
	 *
	 * @param sparrowServerUrl sparrowServerUrl
	 */
	protected abstract void initialization(String sparrowServerUrl);

	@Override
	public void run() {
		initialization (this.sparrowServerUrl);
	}
}
