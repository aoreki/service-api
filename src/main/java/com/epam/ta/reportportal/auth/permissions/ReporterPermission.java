/*
 * Copyright 2016 EPAM Systems
 *
 *
 * This file is part of EPAM Report Portal.
 * https://github.com/reportportal/service-api
 *
 * Report Portal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Report Portal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Report Portal.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.epam.ta.reportportal.auth.permissions;

import com.epam.ta.reportportal.auth.ReportPortalUser;
import com.epam.ta.reportportal.store.database.entity.project.ProjectRole;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * Validates that user is allowed to report (start/finish, launch, start/finish item, log)
 *
 * @author Andrei Varabyeu
 */
@Component
@LookupPermission({ "reporterPermission" })
public class ReporterPermission extends BaseProjectPermission {

	/**
	 * Validates that user is allowed to report (start/finish, launch, start/finish item, log)
	 */
	@Override
	protected boolean checkAllowed(@NotNull ReportPortalUser user, @NotNull String project, ProjectRole role) {
		return role.sameOrHigherThan(ProjectRole.CUSTOMER);
	}
}