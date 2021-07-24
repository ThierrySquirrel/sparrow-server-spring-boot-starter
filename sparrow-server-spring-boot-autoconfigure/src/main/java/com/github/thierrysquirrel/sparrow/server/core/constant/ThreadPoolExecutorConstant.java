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
package com.github.thierrysquirrel.sparrow.server.core.constant;

import com.github.thierrysquirrel.sparrow.server.core.builder.ThreadPoolExecutorBuilder;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: ThreadPoolExecutorConstant
 * Description:
 * date: 2020/12/7 3:26
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public final class ThreadPoolExecutorConstant {

	public static final ThreadPoolExecutor SPARROW_SERVER_EVENT = ThreadPoolExecutorBuilder.builderSparrowServerEventThreadPoolExecutor();
	public static final ThreadPoolExecutor SPARROW_SERVER_INITIALIZATION = ThreadPoolExecutorBuilder.builderSparrowServerInitializationThreadPoolExecutor();
	public static final ThreadPoolExecutor ASYNC_SPARROW_MESSAGE_SERVICE = ThreadPoolExecutorBuilder.builderAsyncSparrowMessageServiceThreadPoolExecutor();
	public static final ScheduledThreadPoolExecutor FLUSH_TIMEOUT_MESSAGE = ThreadPoolExecutorBuilder.builderFlushTimeoutMessageThreadPoolExecutor();
	public static final ScheduledThreadPoolExecutor DELETE_TIMEOUT_MESSAGE = ThreadPoolExecutorBuilder.builderDeleteTimeoutMessageThreadPoolExecutor();

	private ThreadPoolExecutorConstant() {
	}
}
