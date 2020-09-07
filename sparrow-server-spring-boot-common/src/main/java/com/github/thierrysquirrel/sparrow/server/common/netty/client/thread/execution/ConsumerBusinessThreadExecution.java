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
package com.github.thierrysquirrel.sparrow.server.common.netty.client.thread.execution;

import com.github.thierrysquirrel.sparrow.server.common.netty.client.core.factory.ConsumeHandlerFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.listener.ConsumerListener;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.thread.AbstractConsumerBusinessThread;
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.Command;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import io.netty.channel.ChannelHandlerContext;

/**
 * ClassName: ConsumerBusinessThreadExecution
 * Description:
 * date: 2020/6/11 6:53
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ConsumerBusinessThreadExecution extends AbstractConsumerBusinessThread {

    public ConsumerBusinessThreadExecution(ConsumerListener consumerListener, ChannelHandlerContext ctx, SparrowRequestContext msg) {
        super (consumerListener, ctx, msg);
    }

    @Override
    protected void consumerBusiness(ConsumerListener consumerListener, ChannelHandlerContext ctx, SparrowRequestContext msg) {
        Command command = msg.getCommand ();
        if (Command.PULL_MESSAGE == command) {
            ConsumeHandlerFactory.pullMessage (consumerListener, ctx, msg);
        }
        if (Command.PUSH_MESSAGE == command) {
            ConsumeHandlerFactory.pushMessage (consumerListener, ctx, msg);
        }
        if (Command.PUSH_BATCH_MESSAGE == command) {
            ConsumeHandlerFactory.pushBatchMessage (consumerListener, ctx, msg);
        }
    }
}
