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
package com.github.thierrysquirrel.sparrow.server.init.core.thread.execution;

import com.github.thierrysquirrel.sparrow.server.core.container.ProducerMessageQuery;
import com.github.thierrysquirrel.sparrow.server.database.mapper.entity.SparrowMessageEntity;
import com.github.thierrysquirrel.sparrow.server.database.service.SparrowMessageService;
import com.github.thierrysquirrel.sparrow.server.database.service.core.execution.SparrowMessageServiceExecution;
import com.github.thierrysquirrel.sparrow.server.init.core.thread.AbstractFlushTimeoutMessageThread;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * ClassName: FlushTimeoutMessageThreadExecution
 * Description:
 * Date:2024/8/9
 *
 * @author ThierrySquirrel
 * @since JDK21
 **/
public class FlushTimeoutMessageThreadExecution extends AbstractFlushTimeoutMessageThread {
    public FlushTimeoutMessageThreadExecution(SparrowMessageService sparrowMessageService) {
        super(sparrowMessageService);
    }

    @Override
    protected void flush(SparrowMessageService sparrowMessageService) {
        Map<String, List<SparrowMessageEntity>> timeMessage = ProducerMessageQuery.pollTimeoutMessage();
        for (Map.Entry<String, List<SparrowMessageEntity>> timeMessageEntity : timeMessage.entrySet()) {
            List<SparrowMessageEntity> sparrowMessageEntityList = timeMessageEntity.getValue();
            if (ObjectUtils.isEmpty(sparrowMessageEntityList)) {
                continue;
            }
            SparrowMessageServiceExecution.asyncSaveAll(sparrowMessageService, sparrowMessageEntityList, timeMessageEntity.getKey());
        }
    }

}
