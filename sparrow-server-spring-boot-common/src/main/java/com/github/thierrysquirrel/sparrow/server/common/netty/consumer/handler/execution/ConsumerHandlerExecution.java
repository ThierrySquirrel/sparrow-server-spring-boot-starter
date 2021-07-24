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
package com.github.thierrysquirrel.sparrow.server.common.netty.consumer.handler.execution;

import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.container.ResponseContainer;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessageBatch;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowResponse;

/**
 * ClassName: ConsumerHandlerExecution
 * Description:
 * date: 2020/12/8 1:39
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ConsumerHandlerExecution {
	private ConsumerHandlerExecution() {
	}

	public static void callResponse(SparrowResponse sparrowResponse) {
		SparrowMessageBatch sparrowMessageBatch = (SparrowMessageBatch) sparrowResponse.getData();
		Integer requestOffset = sparrowMessageBatch.getRequestOffset();
		ResponseContainer.callResponse(requestOffset, sparrowMessageBatch);
	}
}
