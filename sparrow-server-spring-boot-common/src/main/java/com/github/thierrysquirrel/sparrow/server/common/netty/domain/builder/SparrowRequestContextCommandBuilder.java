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
package com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder;

import com.github.thierrysquirrel.sparrow.server.common.netty.constant.Command;
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.Modular;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;

/**
 * ClassName: SparrowRequestContextCommandBuilder
 * Description:
 * date: 2020/6/9 3:31
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowRequestContextCommandBuilder {
    private SparrowRequestContextCommandBuilder() {
    }

    public static void builderCreateTopic(SparrowRequestContext sparrowRequestContext) {
        sparrowRequestContext.setModular (Modular.ADMINISTRATION);
        sparrowRequestContext.setCommand (Command.CREATE_TOPIC);
    }

    public static void builderDeleteTopic(SparrowRequestContext sparrowRequestContext) {
        sparrowRequestContext.setModular (Modular.ADMINISTRATION);
        sparrowRequestContext.setCommand (Command.DELETE_TOPIC);
    }

    public static void builderSynchronousClusterTopicCache(SparrowRequestContext sparrowRequestContext) {
        sparrowRequestContext.setModular (Modular.SYNCHRONIZING);
        sparrowRequestContext.setCommand (Command.SYNCHRONOUS_CLUSTER_TOPIC_CACHE);
    }

    public static void builderGetAllTopic(SparrowRequestContext sparrowRequestContext) {
        sparrowRequestContext.setModular (Modular.ADMINISTRATION);
        sparrowRequestContext.setCommand (Command.GET_ALL_TOPIC);
    }

    public static void builderGetTopic(SparrowRequestContext sparrowRequestContext) {
        sparrowRequestContext.setModular (Modular.ADMINISTRATION);
        sparrowRequestContext.setCommand (Command.GET_TOPIC);
    }

    public static void builderPing(SparrowRequestContext sparrowRequestContext) {
        sparrowRequestContext.setModular (Modular.HEARTBEAT);
        sparrowRequestContext.setCommand (Command.PING);
    }

    public static void builderPang(SparrowRequestContext sparrowRequestContext) {
        sparrowRequestContext.setModular (Modular.HEARTBEAT);
        sparrowRequestContext.setCommand (Command.PANG);
    }

    public static void builderBatchConfirmConsumption(SparrowRequestContext sparrowRequestContext) {
        sparrowRequestContext.setModular (Modular.MESSAGE);
        sparrowRequestContext.setCommand (Command.BATCH_CONFIRM_CONSUMPTION);
    }

    public static void builderConfirmConsumption(SparrowRequestContext sparrowRequestContext) {
        sparrowRequestContext.setModular (Modular.MESSAGE);
        sparrowRequestContext.setCommand (Command.CONFIRM_CONSUMPTION);
    }

    public static void builderPostMessage(SparrowRequestContext sparrowRequestContext) {
        sparrowRequestContext.setModular (Modular.MESSAGE);
        sparrowRequestContext.setCommand (Command.POST_MESSAGE);
    }

    public static void builderPullMessage(SparrowRequestContext sparrowRequestContext) {
        sparrowRequestContext.setModular (Modular.MESSAGE);
        sparrowRequestContext.setCommand (Command.PULL_MESSAGE);
    }

    public static void builderPushMessage(SparrowRequestContext sparrowRequestContext) {
        sparrowRequestContext.setModular (Modular.MESSAGE);
        sparrowRequestContext.setCommand (Command.PUSH_MESSAGE);
    }
    public static void builderPushBatchMessage(SparrowRequestContext sparrowRequestContext){
        sparrowRequestContext.setModular (Modular.MESSAGE);
        sparrowRequestContext.setCommand (Command.PUSH_BATCH_MESSAGE);
    }

}
