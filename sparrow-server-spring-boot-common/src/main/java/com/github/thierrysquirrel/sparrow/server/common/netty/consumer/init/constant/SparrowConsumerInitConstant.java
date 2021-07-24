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
package com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.constant;

import com.github.thierrysquirrel.sparrow.server.common.netty.consumer.init.SparrowConsumerInit;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * ClassName: SparrowConsumerInitConstant
 * Description:
 * date: 2020/12/8 1:53
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowConsumerInitConstant {
	private static final Map<String, SparrowConsumerInit> SPARROW_PRODUCER_INIT = Maps.newConcurrentMap();

	private SparrowConsumerInitConstant() {
	}

	public static SparrowConsumerInit getSparrowConsumerInit(String url) {
		return SPARROW_PRODUCER_INIT.computeIfAbsent(url, SparrowConsumerInit::new);
	}
}
