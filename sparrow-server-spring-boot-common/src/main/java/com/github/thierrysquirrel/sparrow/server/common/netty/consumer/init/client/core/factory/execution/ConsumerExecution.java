/**
 * Copyright 2024/8/9 ThierrySquirrel
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
 **/
package com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.factory.execution;

import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.builder.container.ConsumerThreadPoolExecutorContainer;
import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.thread.execution.ConsumerThreadExecution;
import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.listener.MessageListener;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessage;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: ConsumerExecution
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public class ConsumerExecution {
    private ConsumerExecution() {
    }

    public static void consumer(MessageListener messageListener, String url, String topic, SparrowMessage sparrowMessage) {
        ConsumerThreadExecution consumerThreadExecution = new ConsumerThreadExecution(messageListener, url, topic, sparrowMessage);
        ThreadPoolExecutor threadPoolExecutor = ConsumerThreadPoolExecutorContainer.getThreadPoolExecutor(topic);
        threadPoolExecutor.execute(consumerThreadExecution);
    }
}
