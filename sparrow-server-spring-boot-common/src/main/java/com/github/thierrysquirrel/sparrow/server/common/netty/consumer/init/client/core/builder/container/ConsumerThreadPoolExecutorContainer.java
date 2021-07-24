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
package com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.builder.container;

import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.builder.ThreadPoolExecutorBuilder;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: ConsumerThreadPoolExecutorContainer
 * Description:
 * date: 2020/12/8 4:17
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ConsumerThreadPoolExecutorContainer {
	private static final Map<String, ThreadPoolExecutor> CONSUMER_THREAD_POOL_EXECUTOR = Maps.newConcurrentMap();

	private ConsumerThreadPoolExecutorContainer() {
	}

	public static ThreadPoolExecutor getThreadPoolExecutor(String topic) {
		return CONSUMER_THREAD_POOL_EXECUTOR.computeIfAbsent(topic, ThreadPoolExecutorBuilder::builderSparrowConsumerThreadPoolExecutor);
	}
}
