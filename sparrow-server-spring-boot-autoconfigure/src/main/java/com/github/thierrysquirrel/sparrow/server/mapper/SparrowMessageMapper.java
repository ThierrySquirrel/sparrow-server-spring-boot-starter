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
package com.github.thierrysquirrel.sparrow.server.mapper;

import com.github.thierrysquirrel.sparrow.server.mapper.entity.SparrowMessageEntity;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * ClassName: SparrowMessageMapper
 * Description:
 * date: 2020/6/10 19:03
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Mapper
public interface SparrowMessageMapper {

    /**
     * initSparrowMessageEntity
     *
     * @return int
     */
    @Update("CREATE TABLE IF NOT EXISTS `sparrow_message_entity`  (" +
            "  `id` int(4) NOT NULL AUTO_INCREMENT," +
            "  `topic` varchar(32) NOT NULL,\n" +
            "  `is_cluster` tinyint(1) NOT NULL DEFAULT 1," +
            "  `message` blob NOT NULL," +
            "  `is_deleted` tinyint(1) NOT NULL DEFAULT 0," +
            "  `gmt_create` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0)," +
            "  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0)," +
            "  PRIMARY KEY (`id`) USING BTREE," +
            "  INDEX `idx_topic`(`topic`) USING BTREE" +
            ")")
    int initSparrowMessageEntity();

    /**
     * deleteAllByTopic
     *
     * @param topic topic
     * @return int
     */
    @Delete("DELETE FROM sparrow_message_entity WHERE topic = #{topic}")
    int deleteAllByTopic(@Param("topic") String topic);

    /**
     * deleteAllByIsDeletedAndGmtModifiedBefore
     *
     * @param isDeleted   isDeleted
     * @param gmtModified gmtModified
     * @return int
     */
    @Delete("DELETE FROM sparrow_message_entity WHERE is_deleted = #{isDeleted} AND gmt_modified < #{gmtModified}")
    int deleteAllByIsDeletedAndGmtModifiedBefore(@Param("isDeleted") byte isDeleted, @Param("gmtModified") Date gmtModified);

    /**
     * updateIsDeletedByIdList
     *
     * @param isDeleted isDeleted
     * @param idList    idList
     * @return int
     */
    @Update(" <script> UPDATE sparrow_message_entity SET is_deleted = #{isDeleted} WHERE id IN <foreach collection='idList' open='(' item='id' separator=',' close=')'> #{id}</foreach> </script>")
    int updateIsDeletedByIdList(@Param("isDeleted") byte isDeleted, @Param("idList") List<Long> idList);

    /**
     * updateIsDeletedById
     *
     * @param isDeleted isDeleted
     * @param id        id
     * @return int
     */
    @Update("UPDATE sparrow_message_entity SET is_deleted = #{isDeleted} WHERE id = #{id}")
    int updateIsDeletedById(@Param("isDeleted") byte isDeleted, @Param("id") long id);

    /**
     * saveSparrowMessageEntity
     *
     * @param sparrowMessageEntity sparrowMessageEntity
     * @return int
     */
    @Insert("INSERT INTO sparrow_message_entity (topic,is_cluster,message) VALUES (#{topic},#{isCluster},#{message})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int saveSparrowMessageEntity(SparrowMessageEntity sparrowMessageEntity);

    /**
     * findAllByTopicAndIsDeleted
     *
     * @param topic     topic
     * @param isDeleted isDeleted
     * @param pageSize  pageSize
     * @param pageIndex pageIndex
     * @return List
     */
    @Select("SELECT id,topic,is_cluster,message FROM sparrow_message_entity WHERE topic = #{topic} AND is_deleted = #{isDeleted} LIMIT #{pageSize},#{pageIndex}")
    List<SparrowMessageEntity> findAllByTopicAndIsDeleted(@Param("topic") String topic, @Param("isDeleted") byte isDeleted, int pageSize, int pageIndex);

    /**
     * findCountByTopicAndIsDeleted
     *
     * @param topic     topic
     * @param isDeleted isDeleted
     * @return int
     */
    @Select("SELECT COUNT(*) FROM sparrow_message_entity WHERE topic = #{topic} AND is_deleted = #{isDeleted}")
    int findCountByTopicAndIsDeleted(@Param("topic") String topic, @Param("isDeleted") byte isDeleted);

    /**
     * saveAll
     *
     * @param messageEntityList messageEntityList
     * @return int
     */
    @Insert("<script> " +
            "INSERT INTO sparrow_message_entity (topic,is_cluster,message) VALUES" +
            "<foreach collection='messageEntityList' item='entity' separator=','> " +
            "(#{entity.topic},#{entity.isCluster},#{entity.message})" +
            "</foreach>  " +
            "</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int saveAll(@Param("messageEntityList") List<SparrowMessageEntity> messageEntityList);
}
