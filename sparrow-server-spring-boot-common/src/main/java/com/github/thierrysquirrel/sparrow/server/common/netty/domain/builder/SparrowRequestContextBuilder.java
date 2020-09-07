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

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.*;

import java.util.List;

/**
 * ClassName: SparrowRequestContextBuilder
 * Description:
 * date: 2020/6/9 3:33
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowRequestContextBuilder {
    private SparrowRequestContextBuilder() {
    }

    private static SparrowRequestContext createSparrowRequestContext() {
        return new SparrowRequestContext ();
    }

    public static SparrowRequestContext builderCreateTopic(String topic, byte isCluster) {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowRequestContextCommandBuilder.builderCreateTopic (sparrowRequestContext);

        SparrowRequest sparrowRequest = SparrowRequestBuilder.builderCreateTopic (topic, isCluster);
        sparrowRequestContext.setSparrowRequest (sparrowRequest);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderDeleteTopic(String topic) {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowRequestContextCommandBuilder.builderDeleteTopic (sparrowRequestContext);

        SparrowRequest sparrowRequest = SparrowRequestBuilder.builderDeleteTopic (topic);
        sparrowRequestContext.setSparrowRequest (sparrowRequest);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderSynchronousClusterTopicCache(String topic) {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowRequestContextCommandBuilder.builderSynchronousClusterTopicCache (sparrowRequestContext);

        SparrowRequest sparrowRequest = SparrowRequestBuilder.builderSynchronousClusterTopicCache (topic);
        sparrowRequestContext.setSparrowRequest (sparrowRequest);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderGetAllTopic() {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowRequestContextCommandBuilder.builderGetAllTopic (sparrowRequestContext);

        SparrowRequest sparrowRequest = SparrowRequestBuilder.builderGetAllTopic ();
        sparrowRequestContext.setSparrowRequest (sparrowRequest);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderGetTopic(String topic) {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowRequestContextCommandBuilder.builderGetTopic (sparrowRequestContext);

        SparrowRequest sparrowRequest = SparrowRequestBuilder.builderGetTopic (topic);
        sparrowRequestContext.setSparrowRequest (sparrowRequest);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderPing(String topic) {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowRequestContextCommandBuilder.builderPing (sparrowRequestContext);

        SparrowRequest sparrowRequest = SparrowRequestBuilder.builderPing (topic);
        sparrowRequestContext.setSparrowRequest (sparrowRequest);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderPang() {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowRequestContextCommandBuilder.builderPang (sparrowRequestContext);

        SparrowResponse sparrowResponse = SparrowResponseBuilder.builderSuccess ();
        sparrowRequestContext.setSparrowResponse (sparrowResponse);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderBatchConfirmConsumption(List<Long> idList) {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowRequestContextCommandBuilder.builderBatchConfirmConsumption (sparrowRequestContext);

        SparrowRequest sparrowRequest = SparrowRequestBuilder.builderBatchConfirmConsumption (idList);
        sparrowRequestContext.setSparrowRequest (sparrowRequest);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderConfirmConsumption(Long id) {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowRequestContextCommandBuilder.builderConfirmConsumption (sparrowRequestContext);

        SparrowRequest sparrowRequest = SparrowRequestBuilder.builderConfirmConsumption (id);
        sparrowRequestContext.setSparrowRequest (sparrowRequest);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderPostMessage(String topic, byte[] message) {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowRequestContextCommandBuilder.builderPostMessage (sparrowRequestContext);

        SparrowRequest sparrowRequest = SparrowRequestBuilder.builderPostMessage (topic, message);
        sparrowRequestContext.setSparrowRequest (sparrowRequest);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderPushMessage(SparrowMessage sparrowMessage) {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowRequestContextCommandBuilder.builderPushMessage (sparrowRequestContext);

        SparrowResponse sparrowResponse = SparrowResponseBuilder.builderSuccess (sparrowMessage);
        sparrowRequestContext.setSparrowResponse (sparrowResponse);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderPullMessage(String topic, int pageIndex, int pageSize) {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowRequestContextCommandBuilder.builderPullMessage (sparrowRequestContext);

        SparrowRequest sparrowRequest = SparrowRequestBuilder.builderPullMessage (topic, pageIndex, pageSize);
        sparrowRequestContext.setSparrowRequest (sparrowRequest);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderPullMessageResponse(PageSparrowMessage pageSparrowMessage) {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowRequestContextCommandBuilder.builderPullMessage (sparrowRequestContext);

        SparrowResponse sparrowResponse = SparrowResponseBuilder.builderSuccess (pageSparrowMessage);
        sparrowRequestContext.setSparrowResponse (sparrowResponse);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderPushBatchMessage(BatchSparrowMessage batchSparrowMessage){
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowRequestContextCommandBuilder.builderPushBatchMessage (sparrowRequestContext);

        SparrowResponse sparrowResponse = SparrowResponseBuilder.builderSuccess (batchSparrowMessage);
        sparrowRequestContext.setSparrowResponse (sparrowResponse);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderSuccess() {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowResponse sparrowResponse = SparrowResponseBuilder.builderSuccess ();
        sparrowRequestContext.setSparrowResponse (sparrowResponse);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderSuccess(Object data) {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowResponse sparrowResponse = SparrowResponseBuilder.builderSuccess (data);
        sparrowRequestContext.setSparrowResponse (sparrowResponse);
        return sparrowRequestContext;
    }

    public static SparrowRequestContext builderFailed(String message) {
        SparrowRequestContext sparrowRequestContext = createSparrowRequestContext ();
        SparrowResponse sparrowResponse = SparrowResponseBuilder.builderFailed (message);
        sparrowRequestContext.setSparrowResponse (sparrowResponse);
        return sparrowRequestContext;
    }

}
