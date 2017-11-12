/*
 * 		Copyright 2017 Emmanuel CHEBBI
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.kazejiyu.generic.datatable.impl;

import java.util.LinkedHashSet;

public class QueryContext {
	
	public final DataTable table;
	
	public final LinkedHashSet <String> selectedHeaders;
	
	public final Filters filters;

	public QueryContext(DataTable table, LinkedHashSet <String> selectedHeaders) {
		this.table = table;
		this.selectedHeaders = selectedHeaders;
		this.filters = new Filters();
	}
	
}
