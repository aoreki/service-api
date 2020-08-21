/*
 * Copyright 2020 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.ta.reportportal.core.item.identity;

import com.epam.ta.reportportal.dao.TestItemRepository;
import com.epam.ta.reportportal.entity.item.TestItem;
import com.google.api.client.util.Lists;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:ihar_kahadouski@epam.com">Ihar Kahadouski</a>
 */
@Service
public class TestCaseHashGeneratorImpl implements TestCaseHashGenerator {

	private final TestItemRepository testItemRepository;

	public TestCaseHashGeneratorImpl(TestItemRepository testItemRepository) {
		this.testItemRepository = testItemRepository;
	}

	@Override
	public Integer generate(TestItem item, Long projectId, boolean useDb) {
		return prepare(item, projectId, useDb).hashCode();
	}

	private String prepare(TestItem item, Long projectId, boolean useDb) {
		List<CharSequence> elements = Lists.newArrayList();

		elements.add(projectId.toString());
		getPathNames(item, projectId, useDb).stream().filter(StringUtils::isNotEmpty).forEach(elements::add);
		elements.add(item.getName());
		item.getParameters()
				.stream()
				.map(parameter -> (!Strings.isNullOrEmpty(parameter.getKey()) ? parameter.getKey() + "=" : "") + parameter.getValue())
				.forEach(elements::add);

		return String.join(";", elements);
	}

	private List<String> getPathNames(TestItem testItem, Long projectId, boolean useDb) {
		if (useDb) {
			return new ArrayList<>(testItemRepository.selectPathNames(testItem.getItemId(), projectId).values());
		}
		List<Long> parentIds = IdentityUtil.getParentIds(testItem);
		return testItemRepository.findAllById(parentIds)
				.stream()
				.sorted(Comparator.comparingLong(TestItem::getItemId))
				.map(TestItem::getName)
				.collect(Collectors.toList());
	}
}
