/**
 * Copyright (c) 2013-2016, Neuro4j
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.neuro4j.workflow.cache;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.neuro4j.workflow.ActionBlock;
import org.neuro4j.workflow.ActionHandler;

/**
 * Contains all handlers  for specific classes.
 * 
 * Ex. Developer can define handler for custom block SystemOutBlock and this handler will be called
 * every time before method execute and after.
 *
 */
public class ActionHandlersRegistry{
	
	/**
	 * Default handler with no implementation
	 */
	final private ActionHandler defaultInstance = new ActionHandler(){};

	/**
	 *  Cache of all handlers
	 */
	private final ConcurrentMap<Class<? extends ActionBlock>, ActionHandler> cache = new ConcurrentHashMap<Class<? extends ActionBlock>, ActionHandler>();
	
	public ActionHandlersRegistry(final Map<Class<? extends ActionBlock>, ActionHandler> map) {
       this.cache.putAll(map);
	}
	
	/**
	 * Returns handler for requested class if it's exist or  default handler overwise.
	 * @param clazz class ? extends ActionBlock
	 * @return handler for requested class
	 */
	public ActionHandler get(final Class<? extends ActionBlock> clazz){
		return Optional.ofNullable(cache.get(clazz)).orElse(defaultInstance);
	}
}