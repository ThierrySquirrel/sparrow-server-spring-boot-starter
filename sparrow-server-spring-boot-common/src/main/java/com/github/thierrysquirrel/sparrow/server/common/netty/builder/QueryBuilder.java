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
package com.github.thierrysquirrel.sparrow.server.common.netty.builder;

import com.github.thierrysquirrel.sparrow.server.common.netty.builder.constant.QueryBuilderConstant;
import org.jctools.queues.MpmcUnboundedXaddArrayQueue;

/**
 * ClassName: QueryBuilder
 * Description:
 * date: 2020/12/8 2:23
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class QueryBuilder {

	private QueryBuilder() {
	}

	public static <T> MpmcUnboundedXaddArrayQueue<T> builderUnboundedQueue() {
		return new MpmcUnboundedXaddArrayQueue<>(QueryBuilderConstant.UNBOUNDED_CHUNK_SIZE, QueryBuilderConstant.UNBOUNDED_MAX_POOLED_CHUNKS);
	}
}
