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

package org.apache.flink.test.recovery;

import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.minicluster.LocalFlinkMiniCluster;

import org.junit.BeforeClass;

/**
 * Test cluster configuration with failure-rate recovery.
 */
public class SimpleRecoveryFailureRateStrategyITBase extends SimpleRecoveryITCaseBase {
	@BeforeClass
	public static void setupCluster() {
		Configuration config = new Configuration();
		config.setInteger(ConfigConstants.LOCAL_NUMBER_TASK_MANAGER, 2);
		config.setInteger(ConfigConstants.TASK_MANAGER_NUM_TASK_SLOTS, 2);
		config.setString(ConfigConstants.RESTART_STRATEGY, "failure-rate");
		config.setInteger(ConfigConstants.RESTART_STRATEGY_FAILURE_RATE_MAX_FAILURES_PER_INTERVAL, 1);
		config.setString(ConfigConstants.RESTART_STRATEGY_FAILURE_RATE_FAILURE_RATE_INTERVAL, "1 second");
		config.setString(ConfigConstants.RESTART_STRATEGY_FAILURE_RATE_DELAY, "100 ms");

		cluster = new LocalFlinkMiniCluster(config, false);

		cluster.start();
	}
}
