package transparencyDemoEngine.controls.controller;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class ControllerManager extends JFrame implements ActionListener
{
	private ArrayList<Controller> controlChoices;
	private ArrayList<String> controllerNames;
	private Controller[] controllers;
	
	private JComboBox ControllerList1, ControllerList2;
	private Controller controlP1;
	private Controller controlP2;
	
	public ControllerManager(ArrayList<Controller> things)
	{
		controlChoices = things;
		controllerNames = new ArrayList<String>();
		this.setSize(440, 200);
		createControllerList();
		createWindow();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void createControllerList() 
	{
		ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment(); 
		controllers = ce.getControllers();
		for(Controller c : controllers)
		{
			controllerNames.add(c.getName());
		}
	}

	private void createWindow() 
	{
		JPanel whole = new JPanel(new GridBagLayout());

		GridBagConstraints bag = new GridBagConstraints();

		bag.gridx = 0;
		bag.gridy = 0;
		bag.gridwidth = 5;
		bag.gridheight = 1;

		JLabel header = new JLabel("Controller Manager");

		whole.add(header, bag);

		bag.gridx = 0;
		bag.gridy = 1;
		bag.gridwidth = 2;

		JLabel menuHeader1 = new JLabel("Player 1");

		whole.add(menuHeader1, bag);

		bag.gridx = 3;

		JLabel menuHeader2 = new JLabel("Player 2");

		whole.add(menuHeader2, bag);

		bag.gridx = 0;
		bag.gridy = 2;

		//Your first menu specifications here
		ControllerList1 = new JComboBox(controllerNames.toArray());
		whole.add(ControllerList1, bag);

		bag.gridx = 3;

		//Your second menu specifications here
		ControllerList2 = new JComboBox(controllerNames.toArray());
		whole.add(ControllerList2, bag);

		bag.gridx = 2;
		bag.gridy = 4;
		bag.gridwidth = 1;

		//Your button for "Go" here

		JButton saveButton = new JButton("OK");
		saveButton.addActionListener(this);
		whole.add(saveButton, bag);
		
		this.add(whole);		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{

		for(Controller c : controllers)
		{
			if(c.getName().equals(this.ControllerList1.getSelectedItem().toString()))
			{
				
				controlP1 = c;
			}
			if(c.getName().equals(this.ControllerList2.getSelectedItem().toString()))
			{
				controlP2 = c;
			}
		}
		controlChoices.add(controlP1);
		controlChoices.add(controlP2);
		this.dispose();
		
	}
	
}
