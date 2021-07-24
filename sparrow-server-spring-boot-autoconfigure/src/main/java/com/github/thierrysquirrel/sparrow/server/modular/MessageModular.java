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
package com.github.thierrysquirrel.sparrow.server.modular;

import com.github.thierrysquirrel.sparrow.server.annotation.SparrowServerEvent;
import com.github.thierrysquirrel.sparrow.server.annotation.SparrowServerModular;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.constant.Event;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.constant.Modular;
import com.github.thierrysquirrel.sparrow.server.database.service.SparrowMessageService;
import com.github.thierrysquirrel.sparrow.server.modular.execution.MessageModularExecution;
import io.netty.channel.ChannelHandlerContext;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: MessageModular
 * Description:
 * date: 2020/12/7 3:48
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@SparrowServerModular(modular = Modular.MESSAGE)
public class MessageModular {

	@Resource
	private SparrowMessageService sparrowMessageService;

	@SparrowServerEvent(event = Event.POST_MESSAGE)
	public void postMessage(ChannelHandlerContext ctx, SparrowRequestContext msg, String topic, byte[] message) {
		MessageModularExecution.postMessage(sparrowMessageService, topic, message);
	}

	@SparrowServerEvent(event = Event.PULL_MESSAGE)
	public void pullMessage(ChannelHandlerContext ctx, SparrowRequestContext msg,Integer requestOffset, String topic) {
		MessageModularExecution.pullMessage(ctx, sparrowMessageService,requestOffset, topic);
	}

	@SparrowServerEvent(event = Event.CONFIRM_CONSUMPTION)
	public void confirmConsumption(ChannelHandlerContext ctx, SparrowRequestContext msg, List<Long> idList) {
		MessageModularExecution.confirmConsumption(sparrowMessageService, idList);
	}
}
