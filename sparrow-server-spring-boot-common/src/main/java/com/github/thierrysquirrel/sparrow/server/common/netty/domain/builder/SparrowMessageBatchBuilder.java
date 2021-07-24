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
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessageBatch;

import java.util.List;

/**
 * ClassName: SparrowMessageBatchBuilder
 * Description:
 * date: 2020/12/8 1:26
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowMessageBatchBuilder {
	private SparrowMessageBatchBuilder() {
	}

	public static SparrowMessageBatch builderSparrowMessageBatch(Integer requestOffset, List<SparrowMessage> sparrowMessageList) {
		SparrowMessageBatch sparrowMessageBatch = new SparrowMessageBatch();
		sparrowMessageBatch.setRequestOffset(requestOffset);
		sparrowMessageBatch.setSparrowMessageList(sparrowMessageList);
		return sparrowMessageBatch;
	}
}
