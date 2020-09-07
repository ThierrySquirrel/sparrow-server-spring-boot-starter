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
package com.github.thierrysquirrel.sparrow.server.service;

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.BatchSparrowMessage;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.PageSparrowMessage;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessage;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowTopic;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder.BatchSparrowMessageBuilder;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder.PageSparrowMessageBuilder;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder.SparrowMessageBuilder;
import com.github.thierrysquirrel.sparrow.server.core.factory.execution.PushMessageFactoryExecution;
import com.github.thierrysquirrel.sparrow.server.error.SparrowServerException;
import com.github.thierrysquirrel.sparrow.server.mapper.SparrowMessageMapper;
import com.github.thierrysquirrel.sparrow.server.mapper.SparrowTopicMapper;
import com.github.thierrysquirrel.sparrow.server.mapper.builder.SparrowMessageEntityBuilder;
import com.github.thierrysquirrel.sparrow.server.mapper.constant.MapperConstant;
import com.github.thierrysquirrel.sparrow.server.mapper.entity.SparrowMessageEntity;
import com.github.thierrysquirrel.sparrow.server.mapper.entity.SparrowTopicEntity;
import com.github.thierrysquirrel.sparrow.server.mapper.template.SparrowTopicEntityCacheTemplate;
import com.github.thierrysquirrel.sparrow.server.mapper.utils.DateUtils;
import com.github.thierrysquirrel.sparrow.server.mapper.utils.DomainUtils;
import com.github.thierrysquirrel.sparrow.server.mapper.utils.MapperUtils;
import com.github.thierrysquirrel.sparrow.server.service.core.container.SparrowMessageEntityConstant;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName: AdministrationService
 * Description:
 * date: 2020/6/8 19:34
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class AdministrationService {
    @Resource
    private SparrowTopicMapper sparrowTopicMapper;
    @Resource
    private SparrowMessageMapper sparrowMessageMapper;
    @Resource
    private SparrowTopicEntityCacheTemplate sparrowTopicEntityCacheTemplate;

    public boolean createTopic(String topic, byte isCluster) {
        SparrowTopicEntity byTopic = getTopic (topic);
        if (!ObjectUtils.isEmpty (byTopic)) {
            return Boolean.FALSE;
        }
        sparrowTopicMapper.createSparrowTopic (topic, isCluster);
        return Boolean.TRUE;
    }

    @Transactional(rollbackFor = SparrowServerException.class)
    public void deleteTopic(String topic) {
        sparrowTopicMapper.deleteAllByTopic (topic);
        sparrowMessageMapper.deleteAllByTopic (topic);
        sparrowTopicEntityCacheTemplate.invalidate (topic);
        SparrowMessageEntityConstant.removeTopic (topic);
    }

    public SparrowTopicEntity getTopic(String topic) {
        SparrowTopicEntity sparrowTopicEntity = sparrowTopicEntityCacheTemplate.get (topic);
        if (ObjectUtils.isEmpty (sparrowTopicEntity)) {
            SparrowTopicEntity byTopicAndIsDeleted = sparrowTopicMapper.findByTopicAndIsDeleted (topic, MapperConstant.NOT_DELETED);

            if (ObjectUtils.isEmpty (byTopicAndIsDeleted)) {
                return null;
            }
            sparrowTopicEntityCacheTemplate.put (topic, byTopicAndIsDeleted);
            return byTopicAndIsDeleted;
        }
        return sparrowTopicEntity;
    }

    public List<SparrowTopic> getAllTopic() {
        List<SparrowTopicEntity> allByIsDeleted = sparrowTopicMapper.findAllByIsDeleted (MapperConstant.NOT_DELETED);
        return DomainUtils.domainConvertList (allByIsDeleted, SparrowTopic.class);
    }

    @Transactional(rollbackFor = SparrowServerException.class)
    public void removeExpiredData() {
        Date expirationDays = DateUtils.getExpirationDays ();
        byte deleted = MapperConstant.DELETED;
        sparrowMessageMapper.deleteAllByIsDeletedAndGmtModifiedBefore (deleted, expirationDays);
    }

    @Transactional(rollbackFor = SparrowServerException.class)
    public void batchConfirmConsumption(List<Long> idList) {
        sparrowMessageMapper.updateIsDeletedByIdList (MapperConstant.DELETED, idList);
    }

    @Transactional(rollbackFor = SparrowServerException.class)
    public void confirmConsumption(Long id) {
        sparrowMessageMapper.updateIsDeletedById (MapperConstant.DELETED, id);
    }

    public SparrowMessage postMessage(String topic, byte[] message) {
        Byte isCluster = getIsCluster (topic);
        if (null == isCluster) {
            return null;
        }
        SparrowMessageEntity sparrowMessageEntity = SparrowMessageEntityBuilder.builderPostMessage (topic, isCluster, message);
        sparrowMessageMapper.saveSparrowMessageEntity (sparrowMessageEntity);

        SparrowMessage sparrowMessage = DomainUtils.domainConvert (sparrowMessageEntity, SparrowMessage.class);
        if (MapperUtils.isClusterConversion (isCluster)) {
            PushMessageFactoryExecution.clusterPushMessage (sparrowMessage);
        } else {
            PushMessageFactoryExecution.broadcastPushMessage (sparrowMessage);
        }
        return sparrowMessage;
    }

    public PageSparrowMessage pullMessage(String topic, int pageIndex, int pageSize) {
        SparrowTopicEntity byTopic = getTopic (topic);
        if (ObjectUtils.isEmpty (byTopic)) {
            return null;
        }
        List<SparrowMessageEntity> sparrowMessageEntityList = sparrowMessageMapper.findAllByTopicAndIsDeleted (topic, MapperConstant.NOT_DELETED, pageIndex * pageSize, pageSize);
        int count = sparrowMessageMapper.findCountByTopicAndIsDeleted (topic, MapperConstant.NOT_DELETED);
        int pageTotal = (count + pageSize - MapperConstant.COUNT_OFFSET) / pageSize;

        List<SparrowMessage> sparrowMessagesList = DomainUtils.domainConvertList (sparrowMessageEntityList, SparrowMessage.class);
        return PageSparrowMessageBuilder.builderPullMessageResponse (topic, pageIndex, pageTotal, sparrowMessagesList);
    }

    public SparrowMessage pushBatchMessage(String topic, byte[] message) {
        Byte isCluster = getIsCluster (topic);
        if (null == isCluster) {
            return null;
        }
        SparrowMessageEntity sparrowMessageEntity = SparrowMessageEntityBuilder.builderPostMessage (topic, isCluster, message);
        boolean isFull = SparrowMessageEntityConstant.putSparrowMessageEntity (topic, sparrowMessageEntity);
        if (isFull) {
            List<SparrowMessageEntity> messageEntityList = SparrowMessageEntityConstant.getSparrowMessageEntity (topic);
            saveAllAndPush (topic, isCluster, messageEntityList);
        }
        return SparrowMessageBuilder.builderProducersResponseSparrowMessage (topic, isCluster, message);

    }

    public void flushConstant() {
        Map<String, List<SparrowMessageEntity>> messageEntityMap = SparrowMessageEntityConstant.getTimeoutSparrowMessageEntityMap ();
        for (Map.Entry<String, List<SparrowMessageEntity>> entityEntry : messageEntityMap.entrySet ()) {
            String topic = entityEntry.getKey ();
            Byte isCluster = getIsCluster (topic);
            if (null == isCluster) {
                continue;
            }
            saveAllAndPush (topic, isCluster, entityEntry.getValue ());
        }
    }

    private void saveAllAndPush(String topic, byte isCluster, List<SparrowMessageEntity> messageEntityList) {
        if (ObjectUtils.isEmpty (messageEntityList)) {
            return;
        }
        sparrowMessageMapper.saveAll (messageEntityList);

        List<SparrowMessage> sparrowMessageList = DomainUtils.domainConvertList (messageEntityList, SparrowMessage.class);
        BatchSparrowMessage batchSparrowMessage = BatchSparrowMessageBuilder.builderBatchSparrowMessage (sparrowMessageList);
        if (MapperUtils.isClusterConversion (isCluster)) {
            PushMessageFactoryExecution.clusterPushBatchMessage (topic, batchSparrowMessage);
        } else {
            PushMessageFactoryExecution.broadcastPushBatchMessage (topic, batchSparrowMessage);
        }
    }

    private Byte getIsCluster(String topic) {
        return getTopic (topic).getIsCluster ();
    }


}
