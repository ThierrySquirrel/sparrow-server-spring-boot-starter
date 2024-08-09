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
package com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client;

import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.container.MessageNumberContainer;
import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.factory.execution.ConsumerExecution;
import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.factory.execution.SparrowConsumerCheckFactoryExecution;
import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.listener.MessageListener;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessage;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessageBatch;

import java.util.List;

/**
 * ClassName: SparrowConsumer
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public class SparrowConsumer implements Runnable {
    private final MessageListener messageListener;
    private final String url;
    private final String topic;
    private boolean isPullMessage = true;

    public SparrowConsumer(MessageListener messageListener, String url, String topic) {
        this.messageListener = messageListener;
        this.url = url;
        this.topic = topic;
    }

    private void pullMessage() {
        SparrowMessageBatch sparrowMessageBatch = SparrowConsumerCheckFactoryExecution.pullMessage(url, topic);
        if (sparrowMessageBatch == null) {
            return;
        }
        List<SparrowMessage> sparrowMessageList = sparrowMessageBatch.getSparrowMessageList();
        MessageNumberContainer.addMessageNumber(topic, sparrowMessageList.size());
        for (SparrowMessage sparrowMessage : sparrowMessageList) {
            ConsumerExecution.consumer(messageListener, url, topic, sparrowMessage);
        }
    }

    public void tryStop() {
        isPullMessage = false;
    }

    @Override
    public void run() {
        while (isPullMessage) {
            pullMessage();
        }
    }

}
