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
package com.github.thierrysquirrel.sparrow.server.core.factory.execution;

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.BatchSparrowMessage;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessage;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder.SparrowRequestContextBuilder;
import com.github.thierrysquirrel.sparrow.server.core.factory.ConsumerLoadBalancing;
import com.github.thierrysquirrel.sparrow.server.core.factory.HeartbeatFactory;
import com.github.thierrysquirrel.sparrow.server.core.factory.PushMessageFactory;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * ClassName: PushMessageFactoryExecution
 * Description:
 * date: 2020/6/10 14:53
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class PushMessageFactoryExecution {
    private PushMessageFactoryExecution() {
    }

    public static void clusterPushMessage(SparrowMessage sparrowMessage) {
        String topic = sparrowMessage.getTopic ();
        ChannelHandlerContext channelHandlerContext = ConsumerLoadBalancing.getChannelHandlerContext (topic);
        if (ObjectUtils.isEmpty (channelHandlerContext)) {
            return;
        }
        SparrowRequestContext sparrowRequestContext = SparrowRequestContextBuilder.builderPushMessage (sparrowMessage);
        PushMessageFactory.pushMessage (channelHandlerContext, sparrowRequestContext);
    }

    public static void broadcastPushMessage(SparrowMessage sparrowMessage) {
        String topic = sparrowMessage.getTopic ();
        List<ChannelHandlerContext> topicConsumerChannel = HeartbeatFactory.getTopicConsumerChannel (topic);
        if (ObjectUtils.isEmpty (topicConsumerChannel)) {
            return;
        }
        SparrowRequestContext sparrowRequestContext = SparrowRequestContextBuilder.builderPushMessage (sparrowMessage);
        topicConsumerChannel.forEach (ctx -> PushMessageFactory.pushMessage (ctx, sparrowRequestContext));
    }

    public static void clusterPushBatchMessage(String topic, BatchSparrowMessage batchSparrowMessage) {
        ChannelHandlerContext channelHandlerContext = ConsumerLoadBalancing.getChannelHandlerContext (topic);
        if (ObjectUtils.isEmpty (channelHandlerContext)) {
            return;
        }
        SparrowRequestContext sparrowRequestContext = SparrowRequestContextBuilder.builderPushBatchMessage (batchSparrowMessage);
        PushMessageFactory.pushMessage (channelHandlerContext, sparrowRequestContext);
    }

    public static void broadcastPushBatchMessage(String topic, BatchSparrowMessage batchSparrowMessage) {
        List<ChannelHandlerContext> topicConsumerChannel = HeartbeatFactory.getTopicConsumerChannel (topic);
        if (ObjectUtils.isEmpty (topicConsumerChannel)) {
            return;
        }
        SparrowRequestContext sparrowRequestContext = SparrowRequestContextBuilder.builderPushBatchMessage (batchSparrowMessage);
        topicConsumerChannel.forEach (ctx -> PushMessageFactory.pushMessage (ctx, sparrowRequestContext));
    }
}
