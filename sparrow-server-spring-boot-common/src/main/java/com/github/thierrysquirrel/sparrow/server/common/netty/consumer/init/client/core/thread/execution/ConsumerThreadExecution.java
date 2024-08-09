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
package com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.thread.execution;

import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.factory.execution.SparrowConsumerFactoryExecution;
import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.thread.AbstractConsumerThread;
import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.listener.MessageListener;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: ConsumerThreadExecution
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
@Slf4j
public class ConsumerThreadExecution extends AbstractConsumerThread {
    public ConsumerThreadExecution(MessageListener messageListener, String url, String topic, SparrowMessage sparrowMessage) {
        super(messageListener, url, topic, sparrowMessage);
    }

    @Override
    protected void consumer(MessageListener messageListener, String url, String topic, SparrowMessage sparrowMessage) {
        try {
            SparrowConsumerFactoryExecution.consumer(messageListener, url, topic, sparrowMessage);
        } catch (Exception e) {
            log.error("consumerError", e);
        }
    }
}
