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
package com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.thread;

import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.listener.MessageListener;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessage;

/**
 * ClassName: AbstractConsumerThread
 * Description:
 * date: 2020/12/8 3:56
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public abstract class AbstractConsumerThread implements Runnable {
	private final MessageListener messageListener;
	private final String url;
	private final String topic;
	private final SparrowMessage sparrowMessage;

	protected AbstractConsumerThread(MessageListener messageListener, String url, String topic, SparrowMessage sparrowMessage) {
		this.messageListener = messageListener;
		this.url = url;
		this.topic = topic;
		this.sparrowMessage = sparrowMessage;
	}

	/**
	 * consumer
	 *
	 * @param messageListener messageListener
	 * @param url             url
	 * @param topic           topic
	 * @param sparrowMessage  sparrowMessage
	 */
	protected abstract void consumer(MessageListener messageListener, String url, String topic, SparrowMessage sparrowMessage);

	@Override
	public void run() {
		consumer(this.messageListener, this.url, this.topic, this.sparrowMessage);
	}
}
