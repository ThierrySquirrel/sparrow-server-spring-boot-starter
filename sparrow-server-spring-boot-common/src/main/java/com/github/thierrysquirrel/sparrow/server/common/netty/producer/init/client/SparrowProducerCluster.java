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
package com.github.thierrysquirrel.sparrow.server.common.netty.producer.init.client;

import com.github.thierrysquirrel.sparrow.server.common.netty.producer.listener.DefaultProducerFail;
import com.github.thierrysquirrel.sparrow.server.common.netty.utils.constant.SocketAddressConstant;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: SparrowProducerCluster
 * Description:
 * date: 2020/12/7 19:54
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowProducerCluster {
	private static final Map<String, List<SparrowProducer>> SPARROW_PRODUCER = Maps.newConcurrentMap();
	private static final Map<String, AtomicInteger> SPARROW_PRODUCER_OFFSET = Maps.newConcurrentMap();

	private SparrowProducerCluster() {
	}

	public static SparrowProducer getSparrowProducer(String clusterUrl, String topic) {
		List<SparrowProducer> sparrowProducerList = SPARROW_PRODUCER.computeIfAbsent(topic, key -> createSparrowProducerList(clusterUrl, topic));
		int increment = SPARROW_PRODUCER_OFFSET.computeIfAbsent(topic, key -> new AtomicInteger()).incrementAndGet();
		int offset = increment & sparrowProducerList.size() - 1;
		return sparrowProducerList.get(offset);
	}


	private static List<SparrowProducer> createSparrowProducerList(String clusterUrl, String topic) {
		String[] urlSplit = clusterUrl.split(SocketAddressConstant.URL_SEPARATOR);
		List<SparrowProducer> sparrowProducerList = new ArrayList<>();
		DefaultProducerFail defaultProducerFail = new DefaultProducerFail();
		for (String url : urlSplit) {
			sparrowProducerList.add(new SparrowProducer(defaultProducerFail, url, topic));
		}
		return sparrowProducerList;
	}

}
