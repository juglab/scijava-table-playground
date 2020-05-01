package org.scijava.table.interactive.example;

import org.scijava.table.interactive.InteractiveTableDisplayViewer;
import org.scijava.table.interactive.SelectionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RunExample extends KeyAdapter implements SelectionListener {

	private final Daycare daycare = new Daycare();
	private final Kid rick = new Kid("Rick", 4);
	private final Kid lara = new Kid("Lara", 5);
	private JLabel focusLabel;

	private RunExample() {
		DaycareTable table = openDaycare();
		displayDaycare(table);
		displayControlWindow();
		daycare.getSelectionModel().listeners().add(this);
	}

	private void displayControlWindow() {
		JFrame window = new JFrame("Daycare");
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(new JLabel("Select this window and press 'l' to focus on Lara and 'r' to focus on Rick."));
		focusLabel = new JLabel("Currently focusing on no one.");
		panel.add(focusLabel);
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		window.addKeyListener(this);
		window.setContentPane(panel);
		window.pack();
		window.setVisible(true);
	}

	private void displayDaycare(DaycareTable table) {
		InteractiveTableDisplayViewer viewer = new InteractiveTableDisplayViewer(table);
		viewer.display();
	}

	private DaycareTable openDaycare() {
		daycare.getKids().add(rick);
		for (int i = 0; i < 30; i++) {
			daycare.getKids().add(new Kid("child" + i, i%6+1));
		}
		daycare.getKids().add(lara);
		return new DaycareTable(daycare);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == 'r') {
			daycare.getSelectionModel().clearSelection();
			daycare.getSelectionModel().setSelected(rick, true);
			daycare.getSelectionModel().focus(rick);
		}
		if(e.getKeyChar() == 'l') {
			daycare.getSelectionModel().clearSelection();
			daycare.getSelectionModel().setSelected(lara, true);
			daycare.getSelectionModel().focus(lara);
		}
	}

	public static void main( String[] args ) {
		new RunExample();
	}

	@Override
	public void selectionChanged() {

	}

	@Override
	public void focusChanged() {
		focusLabel.setText("Currently focusing on " + daycare.getSelectionModel().getFocused().getName() + ".");
	}
}

