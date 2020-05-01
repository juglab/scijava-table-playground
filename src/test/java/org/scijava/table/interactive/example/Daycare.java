package org.scijava.table.interactive.example;

import org.scijava.table.interactive.DefaultSelectionModel;
import org.scijava.table.interactive.SelectionModel;

import java.util.ArrayList;
import java.util.List;

class Daycare {
	private List<Kid> kids = new ArrayList<>();
	private SelectionModel<Kid> selectionModel = new DefaultSelectionModel<>();

	List<Kid> getKids() {
		return kids;
	}

	SelectionModel<Kid> getSelectionModel() {
		return selectionModel;
	}
}
