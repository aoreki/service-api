/*
 * Copyright 2019 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.ta.reportportal.core.launch;

import com.epam.ta.reportportal.commons.ReportPortalUser;
import com.epam.ta.reportportal.ws.model.launch.LaunchResource;
import com.epam.ta.reportportal.ws.model.launch.MergeLaunchesRQ;

/**
 * Merge launches handler in common one
 *
 * @author Pavel_Bortnik
 */
public interface MergeLaunchHandler {

	/**
	 * Merges launches.
	 *
	 * @param projectDetails  Project Details
	 * @param user            User
	 * @param mergeLaunchesRQ Request data
	 * @return OperationCompletionsRS
	 */
	LaunchResource mergeLaunches(ReportPortalUser.ProjectDetails projectDetails, ReportPortalUser user, MergeLaunchesRQ mergeLaunchesRQ);

}
