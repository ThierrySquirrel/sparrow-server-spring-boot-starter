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
package com.github.thierrysquirrel.sparrow.server.netty.core.thread.execution;

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.netty.core.factory.execution.ModularMethodExecution;
import com.github.thierrysquirrel.sparrow.server.netty.core.thread.AbstractSparrowServerEventThread;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: SparrowServerEventThreadExecution
 * Description:
 * date: 2020/12/7 3:22
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class SparrowServerEventThreadExecution extends AbstractSparrowServerEventThread {
	public SparrowServerEventThreadExecution(ChannelHandlerContext ctx, SparrowRequestContext msg) {
		super (ctx, msg);
	}

	/**
	 * event
	 *
	 * @param ctx ctx
	 * @param msg msg
	 */
	@Override
	protected void event(ChannelHandlerContext ctx, SparrowRequestContext msg) {
		try {
			ModularMethodExecution.invoke (ctx, msg);
		} catch (Exception e) {
			log.error ("event Error",e);
		}
	}
}
