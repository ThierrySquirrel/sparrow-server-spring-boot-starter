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
package com.github.thierrysquirrel.sparrow.server.common.netty.client.core.factory;

import com.github.thierrysquirrel.sparrow.server.common.netty.client.init.ConsumerInit;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder.SparrowRequestContextBuilder;

/**
 * ClassName: ConsumerClusterFactory
 * Description:
 * date: 2020/6/11 7:36
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ConsumerClusterFactory {
    private ConsumerClusterFactory() {
    }

    private static void request(ConsumerInit consumerInit, SparrowRequestContext sparrowRequestContext) throws InterruptedException {
        consumerInit.init ();
        consumerInit.getChannel ().writeAndFlush (sparrowRequestContext);
    }

    public static void ping(ConsumerInit consumerInit, String topic, String clusterUrl) throws InterruptedException {
        SparrowRequestContext sparrowRequestContext = SparrowRequestContextBuilder.builderPing (topic);
        request (consumerInit, sparrowRequestContext);
    }

    public static void pull(String topic, String clusterUrl, int pageIndex, int pageSize) throws InterruptedException {
        SparrowRequestContext sparrowRequestContext = SparrowRequestContextBuilder.builderPullMessage (topic, pageIndex, pageSize);
        ConsumerInit consumerInit = ConsumerClusterCacheFactory.getConsumerInit (topic, clusterUrl);
        request (consumerInit, sparrowRequestContext);
    }
}
