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

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessage;

/**
 * ClassName: SparrowMessageBuilder
 * Description:
 * date: 2020/9/8 5:43
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowMessageBuilder {
    private SparrowMessageBuilder() {
    }

    public static SparrowMessage builderProducersResponseSparrowMessage(String topic, byte isCluster, byte[] message) {
        SparrowMessage sparrowMessage = new SparrowMessage ();
        sparrowMessage.setTopic (topic);
        sparrowMessage.setIsCluster (isCluster);
        sparrowMessage.setMessage (message);
        return sparrowMessage;
    }
}
