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
package com.github.thierrysquirrel.sparrow.server.database.service.core.thread;

import com.github.thierrysquirrel.sparrow.server.database.mapper.entity.SparrowMessageEntity;
import com.github.thierrysquirrel.sparrow.server.database.service.SparrowMessageService;

import java.util.List;

/**
 * ClassName: AbstractAsyncSaveAllThread
 * Description:
 * date: 2020/12/7 5:25
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public abstract class AbstractAsyncSaveAllThread implements Runnable {

	private final SparrowMessageService sparrowMessageService;
	private final List<SparrowMessageEntity> sparrowMessageEntityList;
	private final String topic;

	protected AbstractAsyncSaveAllThread(SparrowMessageService sparrowMessageService, List<SparrowMessageEntity> sparrowMessageEntityList, String topic) {
		this.sparrowMessageService = sparrowMessageService;
		this.sparrowMessageEntityList = sparrowMessageEntityList;
		this.topic = topic;
	}

	/**
	 * asyncSaveAll
	 *
	 * @param sparrowMessageService    sparrowMessageService
	 * @param sparrowMessageEntityList sparrowMessageEntityList
	 * @param topic                    topic
	 */
	protected abstract void asyncSaveAll(SparrowMessageService sparrowMessageService, List<SparrowMessageEntity> sparrowMessageEntityList, String topic);

	@Override
	public void run() {
		asyncSaveAll(this.sparrowMessageService, this.sparrowMessageEntityList, this.topic);
	}
}
