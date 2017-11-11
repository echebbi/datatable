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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fr.kazejiyu.generic.datatable.Column;
import fr.kazejiyu.generic.datatable.Columns;
import fr.kazejiyu.generic.datatable.Row;
import fr.kazejiyu.generic.datatable.Table;

public class SimpleColumns implements Columns {
	
	/** The table that owns the columns */
	private final Table table;
	
	/** The columns that compose the table */
	private final List <Column<?>> elements;
	
	/** Maps the header in a case-insensitive way to their index */
	private final Map <String,Integer> headerToIndex;
	
	SimpleColumns(Table table) {
		this.table = table;
		this.elements = new LinkedList<>();
		this.headerToIndex = new HashMap<>();
	}
	
	@Override
	public LinkedHashSet <String> headers() {
		LinkedHashSet <String> headers = new LinkedHashSet<>();
		
		for( Column <?> column : elements )
			headers.add(column.header());
		
		return headers;
	}

	@Override
	public Iterator<Column<?>> iterator() {
		return elements.iterator();
	}

	@Override
	public int size() {
		return elements.size();
	}

	@Override
	public Column<?> get(int index) {
		return elements.get(index);
	}
	
	private void addColumn(Column <?> column) {
		headerToIndex.put(column.header().toLowerCase(), size());
		elements.add(column);
	}

	@Override
	public Columns insert(int position, Column<?> column) {
		addColumn(column);
		
		for( Row row : table.rows() )
			row.insert(position, column);
		
		return this;
	}

	@Override
	public Columns remove(int index) {
		elements.remove(index);
		return this;
	}

	@Override
	public int indexOf(String header) {
		return headerToIndex.get(header);
	}

	@Override
	public <N> Columns create(Class<N> type, String header, Iterable<N> elements) {
		Iterator <N> itElement = elements.iterator();

		if( table.rows().size() == 0 ) {
			for( int id = 0 ; itElement.hasNext() ; id++ )
				table.rows().add(new SimpleRow(table, id, Arrays.asList(itElement.next())));
		}
		else {
			Iterator <Row> itRow = table.rows().iterator();
			
			while( itRow.hasNext() && itElement.hasNext() )
				itRow.next().add(itElement.next());
		}
		
		addColumn( new SimpleColumn<>(type, table, header) );
		return this;
	}

}