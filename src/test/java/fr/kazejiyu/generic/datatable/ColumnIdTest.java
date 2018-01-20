package fr.kazejiyu.generic.datatable;

import static fr.kazejiyu.generic.datatable.core.impl.ColumnId.id;
import static fr.kazejiyu.generic.datatable.core.impl.ColumnId.n;
import static fr.kazejiyu.generic.datatable.core.impl.ColumnId.s;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import fr.kazejiyu.generic.datatable.core.impl.ColumnId;
import fr.kazejiyu.generic.datatable.core.impl.DataTable;

/**
 * Tests the behavior of {@link DataTable} instances.
 * 
 * @author Emmanuel CHEBBI
 */
@DisplayName("A ColumnId")
class ColumnIdTest {
	
	private Class<String> type;
	private String header;
	private ColumnId<String> id;
	
	@BeforeEach
	void createId() {
		type = String.class;
		header = "Header";
		id = id(type, header);
	}
	
	// type()

	@Test @DisplayName("returns the right type")
	void returns_the_right_type() {
		assertThat(id.type()).isEqualTo(type);
	}
	
	// header()
	
	@Test @DisplayName("returns the header without modification")
	void returns_the_header_without_modification() {
		assertThat(id.header()).isEqualTo(header);
	}
	
	// equals()
	
	@Test @DisplayName("is not equal to null")
	void is_not_equal_to_null() {
		assertThat(id).isNotEqualTo(null);
	}
	
	@Test @DisplayName("is not equal to a non ColumnId object")
	void is_not_equal_to_a_non_columnid_object() {
		assertThat(id).isNotEqualTo("hello");
	}
	
	@Test @DisplayName("is not equal to a similar id with different header")
	void is_not_equal_to_a_similar_id_with_different_header() {
		assertThat(id).isNotEqualTo(id(type, " " + header));
	}
	
	@Test @DisplayName("is not equal to a similar id with different type")
	void is_not_equal_to_a_similar_id_with_different_type() {
		assertThat(id).isNotEqualTo(id(this.getClass(), header));
	}
	
	@Test @DisplayName("is equal to self")
	void is_equal_to_self() {
		assertThat(id).isEqualTo(id);
	}
	
	@Test @DisplayName("is equal to an identical id")
	void is_equal_to_an_identical_id() {
		assertThat(id).isEqualTo(id(type, header));
	}
	
	@ParameterizedTest 
	@ValueSource(strings = {"HEADER", "header", "hEADER"})
	@DisplayName("is equal to an identical id without case consideration")
	void is_equal_to_an_identical_id_without_case_consideration(String otherHeader) {
		ColumnId<String> otherId = id(String.class, otherHeader);
		assertThat(id).isEqualTo(otherId);
	}
	
	// s()
	
	@Test @DisplayName("can create an identical column of strings id")
	void can_create_an_identical_column_of_strings_id() {
		assertThat(s(id)).isEqualTo(id);
	}
	
	@Test @DisplayName("can create an array of column of strings with identical id")
	void can_create_an_array_of_column_of_strings_with_identical_id() {
		ColumnId<String> s1 = id(String.class, "headER1");
		ColumnId<String> s2 = id(String.class, "  headER 2");
		ColumnId<String> s3 = id(String.class, "  ");
		ColumnId<String> s4 = id(String.class, "HEADER 4");
		
		assertThat(s(s1, s2, s3, s4)).containsExactly(s(s1), s(s2), s(s3), s(s4));
	}
	
	// n()
	
	@Test @DisplayName("can create an identical column of numbers id")
	void can_create_an_identical_column_of_numbers_id() {
		ColumnId<Double> dbl = id(Double.class, "double");
		assertThat(n(dbl)).isEqualTo(dbl);
	}
	
}