/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.table.types.inference;

import org.apache.flink.annotation.Internal;
import org.apache.flink.table.types.inference.transforms.DataTypeConversionClassTransformation;
import org.apache.flink.table.types.logical.LogicalTypeRoot;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Transformations for transforming one data type to another.
 *
 * @see TypeTransformation
 */
@Internal
public class TypeTransformations {

	/**
	 * Returns a type transformation that transforms data type to a new data type whose conversion
	 * class is {@link java.sql.Timestamp}/{@link java.sql.Time}/{@link java.sql.Date}
	 * if the original data type is TIMESTAMP/TIME/DATE.
	 */
	public static TypeTransformation timeToSqlTypes() {
		Map<LogicalTypeRoot, Class<?>> conversions = new HashMap<>();
		conversions.put(LogicalTypeRoot.TIMESTAMP_WITHOUT_TIME_ZONE, Timestamp.class);
		conversions.put(LogicalTypeRoot.TIME_WITHOUT_TIME_ZONE, Time.class);
		conversions.put(LogicalTypeRoot.DATE, Date.class);
		return new DataTypeConversionClassTransformation(conversions);
	}
}
