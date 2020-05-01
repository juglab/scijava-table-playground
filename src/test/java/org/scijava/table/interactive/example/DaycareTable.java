package org.scijava.table.interactive.example;

import org.scijava.table.interactive.DefaultInteractiveTable;
import org.scijava.table.interactive.SelectionModelAdapter;

import java.util.HashMap;
import java.util.Map;

class DaycareTable extends DefaultInteractiveTable<Integer> {

	DaycareTable(Daycare daycare) {
		Map<Integer, Kid> mappingLeft = new HashMap<>();
		Map<Kid, Integer> mappingRight = new HashMap<>();
		int i = 0;
		appendColumn("name");
		appendColumn("age");
		for (Kid kid : daycare.getKids()) {
			appendRow();
			set("name", i, kid.getName());
			set("age", i, kid.getAge());
			mappingLeft.put(i, kid);
			mappingRight.put(kid, i);
			i++;
		}
		setSelectionModel(new SelectionModelAdapter<>(daycare.getSelectionModel(), mappingRight, mappingLeft));
	}
}
