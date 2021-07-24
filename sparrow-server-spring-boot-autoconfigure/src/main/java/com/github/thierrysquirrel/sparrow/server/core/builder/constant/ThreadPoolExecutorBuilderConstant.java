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
package com.github.thierrysquirrel.sparrow.server.core.builder.constant;

/**
 * ClassName: ThreadPoolExecutorBuilderConstant
 * Description:
 * date: 2020/12/7 3:24
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public final class ThreadPoolExecutorBuilderConstant {
	public static final int KEEP_ALIVE_TIME = 0;

	public static final String SPARROW_SERVER_EVENT = "SparrowServerEvent";
	public static final int SPARROW_SERVER_EVENT_CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
	public static final int SPARROW_SERVER_EVENT_MAXIMUM_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

	public static final String SPARROW_SERVER_INITIALIZATION = "SparrowServerInitialization";
	public static final int SPARROW_SERVER_INITIALIZATION_CORE_POOL_SIZE = 1;
	public static final int SPARROW_SERVER_INITIALIZATION_MAXIMUM_POOL_SIZE = 1;

	public static final String ASYNC_SPARROW_MESSAGE_SERVICE = "AsyncSparrowMessageService";
	public static final int ASYNC_SPARROW_MESSAGE_SERVICE_CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
	public static final int ASYNC_SPARROW_MESSAGE_SERVICE_MAXIMUM_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

	public static final String FLUSH_TIMEOUT_MESSAGE = "FlushTimeoutMessage";
	public static final int FLUSH_TIMEOUT_MESSAGE_CORE_POOL_SIZE = 1;

	public static final String DELETE_TIMEOUT_MESSAGE = "DeleteTimeoutMessage";
	public static final int DELETE_TIMEOUT_MESSAGE_CORE_POOL_SIZE = 1;

	private ThreadPoolExecutorBuilderConstant() {
	}
}
