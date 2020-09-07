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
package com.github.thierrysquirrel.sparrow.server.common.netty.constant;

/**
 * ClassName: Command
 * Description:
 * date: 2020/6/8 17:44
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public enum Command {
    /**
     * CreateTopic
     */
    CREATE_TOPIC,
    /**
     * DeleteTopic
     */
    DELETE_TOPIC,
    /**
     * GetAllTopic
     */
    GET_ALL_TOPIC,
    /**
     * GetTopic
     */
    GET_TOPIC,
    /**
     * Pang
     */
    PANG,
    /**
     * Ping
     */
    PING,
    /**
     * BatchConfirmConsumption
     */
    BATCH_CONFIRM_CONSUMPTION,
    /**
     * ConfirmConsumption
     */
    CONFIRM_CONSUMPTION,
    /**
     * PostMessage
     */
    POST_MESSAGE,
    /**
     * PullMessage
     */
    PULL_MESSAGE,
    /**
     * PushMessage
     */
    PUSH_MESSAGE,
    /**
     * PushBatchMessage
     */
    PUSH_BATCH_MESSAGE,
    /**
     * SynchronousClusterTopicCache
     */
    SYNCHRONOUS_CLUSTER_TOPIC_CACHE;
}
