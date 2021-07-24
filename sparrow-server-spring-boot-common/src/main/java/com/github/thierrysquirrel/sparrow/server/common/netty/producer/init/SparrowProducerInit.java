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
package com.github.thierrysquirrel.sparrow.server.common.netty.producer.init;

import com.github.thierrysquirrel.sparrow.server.common.netty.producer.handler.SparrowProducerInitializer;
import com.github.thierrysquirrel.sparrow.server.common.netty.producer.init.container.ProducerEventLoopGroupContainer;
import com.github.thierrysquirrel.sparrow.server.common.netty.utils.ChannelUtils;
import com.github.thierrysquirrel.sparrow.server.common.netty.utils.SocketAddressUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * ClassName: SparrowProducerInit
 * Description:
 * date: 2020/12/7 19:20
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowProducerInit {
	private final String url;
	private Channel channel;

	public SparrowProducerInit(String url) {
		this.url = url;
	}

	public Channel init() throws InterruptedException {
		if (ChannelUtils.channelIsActive(channel)) {
			return channel;
		}
		channel = new Bootstrap().group(ProducerEventLoopGroupContainer.getNioEventLoopGroup(url))
				.channel(NioSocketChannel.class)
				.handler(new SparrowProducerInitializer())
				.connect(SocketAddressUtils.getInetSocketAddress(url))
				.sync()
				.channel();
		return channel;
	}
}
