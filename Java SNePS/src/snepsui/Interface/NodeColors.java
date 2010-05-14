package snepsui.Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NodeColors extends JPanel {
	private JLabel patternNodeText;
	private JLabel baseNodeText;
	private JLabel variableNodeText;
	private JLabel closedNodeText;
	private JLabel patternNodeColor;
	private JLabel baseNodeColor;
	private JLabel variableNodeColor;
	private JLabel closedNodeColor;
	
	public NodeColors() {
		this.setPreferredSize(new Dimension(200,100));
		this.setLayout(new GridLayout(4,2));
		
		baseNodeText = new JLabel("Base Node");
		baseNodeColor = new JLabel();
		baseNodeColor.setBackground(Color.green);
		baseNodeColor.setOpaque(true);
		this.add(baseNodeText);
		this.add(baseNodeColor);
		
		variableNodeText = new JLabel("Variable Node");
		variableNodeColor = new JLabel();
		variableNodeColor.setBackground(Color.gray);
		variableNodeColor.setOpaque(true);
		this.add(variableNodeText);
		this.add(variableNodeColor);
		
		closedNodeText = new JLabel("Closed Node");
		closedNodeColor = new JLabel();
		closedNodeColor.setBackground(Color.yellow);
		closedNodeColor.setOpaque(true);
		this.add(closedNodeText);
		this.add(closedNodeColor);
		
		patternNodeText = new JLabel("Pattern Node");
		patternNodeColor = new JLabel();
		patternNodeColor.setBackground(Color.blue);
		patternNodeColor.setOpaque(true);
		this.add(patternNodeText);
		this.add(patternNodeColor);
	}
}
