package org.neuro4j.compiler.builder;

/*
 * Copyright (c) 2013-2016, Neuro4j
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Map;

import org.neuro4j.workflow.common.FlowExecutionException;
import org.neuro4j.workflow.common.SWFParametersConstants;
import org.neuro4j.workflow.loader.f4j.NodeXML;
import org.neuro4j.workflow.node.SwitchNode;

public class SwitchBlockBuilder extends AbstractBuilder {

	public SwitchBlockBuilder(NodeXML node, Map<String, String> names) {
		super(node, names);
	}

	public void buildNewStatment(StringBuffer buffer) throws FlowExecutionException
	{
		String relationName = node
				.getConfig(SWFParametersConstants.SWITCH_NODE_ACTION_NAME);
		if (relationName == null) {
			relationName = SWFParametersConstants.SWITCH_NODE_DEFAULT_PARAMETER_VALUE;
		}
		relationName = relationName.replaceAll("\"", "");
		buffer.append("  ").append(getImpClassName()).append(" ").append(names.get(this.node.getUuid())).append("  =  new ").append(getImpClassName()).append("(\"").append(getNodename());
		buffer.append("\", \"").append(node.getUuid()).append("\", \"").append(relationName).append("\"); \n");

		
		
	}

	@Override
	public String getImpClassName() {
		return SwitchNode.class.getSimpleName();
	}

}
