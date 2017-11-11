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
package fr.kazejiyu.generic.datatable;

/**
 * A table containing {@link Rows} and {@link Columns}.
 * 
 * @author Emmanuel CHEBBI
 */
public interface Table {
	
	/** @return whether the table is empty or not */
	public default boolean isEmpty() {
		return rows().isEmpty() || columns().isEmpty();
	}

	/** @return the rows of the table */
	public Rows rows();
	
	/** @return the columns of the table */
	public Columns columns();
	
}
