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
package com.github.thierrysquirrel.sparrow.server.service.core.container;

import com.github.thierrysquirrel.sparrow.server.mapper.entity.SparrowMessageEntity;
import com.github.thierrysquirrel.sparrow.server.service.core.constant.SparrowMessageEntityConstantConstant;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ClassName: SparrowMessageEntityConstant
 * Description:
 * date: 2020/9/7 22:23
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class SparrowMessageEntityConstant {
    private static final Map<String, LinkedBlockingQueue<SparrowMessageEntity>> MESSAGE_ENTITY_MAP = Maps.newConcurrentMap ();
    private static final Map<String, Long> PUT_MESSAGE_ENTITY_TIME_MAP = Maps.newConcurrentMap ();

    private SparrowMessageEntityConstant() {
    }

    public static boolean putSparrowMessageEntity(String topic, SparrowMessageEntity sparrowMessageEntity) {
        LinkedBlockingQueue<SparrowMessageEntity> messageEntityQueue = MESSAGE_ENTITY_MAP.computeIfAbsent (topic, key -> new LinkedBlockingQueue<> ());
        PUT_MESSAGE_ENTITY_TIME_MAP.put (topic, System.currentTimeMillis ());
        try {
            messageEntityQueue.put (sparrowMessageEntity);
            if (messageEntityQueue.size () >= SparrowMessageEntityConstantConstant.MAX_CAPACITY) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            log.error ("putSparrowMessageEntity Error", e);
        }
        return Boolean.FALSE;
    }

    public static List<SparrowMessageEntity> getSparrowMessageEntity(String topic) {
        List<SparrowMessageEntity> messageEntityList = new ArrayList<> ();
        LinkedBlockingQueue<SparrowMessageEntity> sparrowMessageEntities = MESSAGE_ENTITY_MAP.get (topic);
        if(!ObjectUtils.isEmpty (sparrowMessageEntities)){
            sparrowMessageEntities.drainTo (messageEntityList, SparrowMessageEntityConstantConstant.MAX_CAPACITY);
        }
        return messageEntityList;
    }

    public static Map<String, List<SparrowMessageEntity>> getTimeoutSparrowMessageEntityMap() {
        Map<String, List<SparrowMessageEntity>> timeoutMessageEntityMap = Maps.newConcurrentMap ();
        PUT_MESSAGE_ENTITY_TIME_MAP.forEach ((topic, putMessageTime) -> putTimeoutMessageEntityMap (timeoutMessageEntityMap, topic, putMessageTime));
        return timeoutMessageEntityMap;
    }

    public static void removeTopic(String topic) {
        MESSAGE_ENTITY_MAP.remove (topic);
        PUT_MESSAGE_ENTITY_TIME_MAP.remove (topic);
    }

    private static void putTimeoutMessageEntityMap(Map<String, List<SparrowMessageEntity>> timeoutMessageEntityMap, String topic, Long putMessageTime) {
        long thisTime = System.currentTimeMillis ();
        if (thisTime - putMessageTime >= SparrowMessageEntityConstantConstant.TIME_OUT_MILLIS) {
            timeoutMessageEntityMap.put (topic, getSparrowMessageEntity (topic));
        }
    }

}
