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

import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.client.core.factory.SparrowConsumerCheckFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessageBatch;

/**
 * ClassName: SparrowConsumerCheckFactoryExecution
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public class SparrowConsumerCheckFactoryExecution {
    private SparrowConsumerCheckFactoryExecution() {
    }

    public static SparrowMessageBatch pullMessage(String url, String topic) {
        boolean isFail = SparrowConsumerCheckFactory.consumptionTimeoutIdList(url);
        if (isFail) {
            return null;
        }
        boolean isMessageNumberMax = SparrowConsumerCheckFactory.checkMessageNumber(topic);
        if (isMessageNumberMax) {
            return null;
        }
        return SparrowConsumerCheckFactory.pullMessage(url, topic);
    }
}
