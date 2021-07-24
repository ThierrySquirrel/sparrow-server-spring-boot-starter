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
package com.github.thierrysquirrel.sparrow.server.init.core.factory.execution;

import com.github.thierrysquirrel.sparrow.server.core.constant.ThreadPoolExecutorConstant;
import com.github.thierrysquirrel.sparrow.server.database.service.SparrowMessageService;
import com.github.thierrysquirrel.sparrow.server.init.core.constant.FlushTimeoutMessageInitConstant;
import com.github.thierrysquirrel.sparrow.server.init.core.thread.execution.FlushTimeoutMessageThreadExecution;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: FlushTimeoutMessageInitExecution
 * Description:
 * date: 2020/12/7 7:11
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class FlushTimeoutMessageInitExecution {
	private FlushTimeoutMessageInitExecution() {
	}

	public static void flush(SparrowMessageService sparrowMessageService) {
		FlushTimeoutMessageThreadExecution flushTimeoutMessageThreadExecution = new FlushTimeoutMessageThreadExecution(sparrowMessageService);
		ScheduledThreadPoolExecutor flushTimeoutMessage = ThreadPoolExecutorConstant.FLUSH_TIMEOUT_MESSAGE;
		flushTimeoutMessage.scheduleWithFixedDelay(flushTimeoutMessageThreadExecution, FlushTimeoutMessageInitConstant.INITIAL_DELAY, FlushTimeoutMessageInitConstant.DELAY, TimeUnit.MILLISECONDS);
	}
}
