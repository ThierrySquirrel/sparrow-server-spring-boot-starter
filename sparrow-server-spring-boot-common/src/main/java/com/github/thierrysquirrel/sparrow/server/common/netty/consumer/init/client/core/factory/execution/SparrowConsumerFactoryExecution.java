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
package com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.factory.execution;

import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.container.MessageIdQuery;
import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.factory.SparrowConsumerFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.listener.MessageListener;
import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.listener.constant.ConsumerState;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessage;

import java.util.List;

/**
 * ClassName: SparrowConsumerFactoryExecution
 * Description:
 * date: 2020/12/8 3:47
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowConsumerFactoryExecution {
	private SparrowConsumerFactoryExecution() {
	}

	public static void consumer(MessageListener messageListener, String url, String topic, SparrowMessage sparrowMessage) throws InterruptedException {
		ConsumerState consumer = SparrowConsumerFactory.consumer(messageListener, topic, sparrowMessage);
		if (consumer == ConsumerState.SUCCESS) {
			Long id = sparrowMessage.getId();
			List<Long> idList = MessageIdQuery.putId(url, id);
			if (idList.isEmpty()) {
				return;
			}
			SparrowConsumerFactory.confirmConsumption(url, idList);
		}
	}
}
