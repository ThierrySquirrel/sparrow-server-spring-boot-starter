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
package com.github.thierrysquirrel.sparrow.server.event.core.factory;

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.PageSparrowMessage;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessage;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder.SparrowRequestContextBuilder;
import com.github.thierrysquirrel.sparrow.server.core.factory.RateLimiterFactory;
import com.github.thierrysquirrel.sparrow.server.mapper.entity.SparrowTopicEntity;
import com.github.thierrysquirrel.sparrow.server.service.AdministrationService;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * ClassName: MessageHandlerFactory
 * Description:
 * date: 2020/6/10 3:02
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class MessageHandlerFactory {
    private MessageHandlerFactory() {
    }

    public static SparrowRequestContext batchConfirmConsumption(AdministrationService administrationService, List<Long> idList) {
        administrationService.batchConfirmConsumption (idList);
        return SparrowRequestContextBuilder.builderSuccess ();
    }

    public static SparrowRequestContext confirmConsumption(AdministrationService administrationService, Long id) {
        administrationService.confirmConsumption (id);
        return SparrowRequestContextBuilder.builderSuccess ();
    }

    public static SparrowRequestContext postMessage(AdministrationService administrationService, String topic, byte[] message) {
        SparrowTopicEntity topicEntity = administrationService.getTopic (topic);
        if (ObjectUtils.isEmpty (topicEntity)) {
            return SparrowRequestContextBuilder.builderFailed ("PostMessage Topic Does Not Exist");
        }
        if (!RateLimiterFactory.isRelease (topic)) {
            SparrowMessage sparrowMessage = administrationService.pushBatchMessage (topic, message);
            return SparrowRequestContextBuilder.builderSuccess (sparrowMessage);
        }
        SparrowMessage sparrowMessage = administrationService.postMessage (topic, message);
        return SparrowRequestContextBuilder.builderSuccess (sparrowMessage);
    }

    public static SparrowRequestContext pullMessage(AdministrationService administrationService, String topic, int pageIndex, int pageSize) {
        PageSparrowMessage pageSparrowMessage = administrationService.pullMessage (topic, pageIndex, pageSize);
        if (ObjectUtils.isEmpty (pageSparrowMessage)) {
            return SparrowRequestContextBuilder.builderFailed ("PullMessage Topic Does Not Exist");
        }
        return SparrowRequestContextBuilder.builderPullMessageResponse (pageSparrowMessage);
    }
}
