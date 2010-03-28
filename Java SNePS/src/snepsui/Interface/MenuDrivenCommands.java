package snepsui.Interface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import snepsui.Commands.*;

import sneps.Network;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MenuDrivenCommands extends javax.swing.JPanel {
	private JComboBox commandsComboBox;
	private JComboBox commandMenusComboBox;
	private JPanel commandsPanel;
	private DefaultComboBoxModel jComboBox1Model;
	private Network network;

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public MenuDrivenCommands() {
		super();
		this.network = null;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			this.setLayout(null);
			{
				jComboBox1Model = 
					new DefaultComboBoxModel(
							new String[] { "define", "undefine", "define-caseframe" , "undefine-caseframe", "deduce", "define-path",
									 "undefine-path", "set-context", "set-default-context", "add-to-context", "remove-from-context",
									 "list-context-names", "describe-context", "list-hypotheses", "build", "assert", "add", "activate", 
									 "erase", "silent-erase", "resetnet", "clear-infer-all", "clear-infer", "dump", "describe",
									 "full-describe", "show", "surface", "find", "findassert", "findconstant", "findbase", 
									 "findvariable", "findpatter", "deducetrue", "deducefalse", "deducewh", "deducewhnot",
									 "And-Entailment", "Or-Entailment", "Numerrical Entailment", "AndOr", "Thresh", "Universal Quantifier",
									 "Numerical Quantifier", "ev-trace", "unev-trace", "in-trace", "unin-trace", "multi::print-regs"});
				commandsComboBox = new JComboBox();
				this.add(commandsComboBox);
				commandsComboBox.setModel(jComboBox1Model);
				commandsComboBox.setBounds(397, 28, 196, 22);
				commandsComboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent evt) {
						commandsComboBoxItemStateChanged(evt);
					}
				});
			}
			{
				ComboBoxModel jComboBox2Model = 
					new DefaultComboBoxModel(
							new String[] { "Relations", "Case Frames", "Reduction Inference", "Path-Based Inference", "Contexts", "Building Networks", 
									"Deleting Information", "Displaying Information", "Retrieving Information", "Connectives", "Quantifiers", 
									"Tracing Inference", "Acting", "Primitive Acts", "Complex Acts", "Goals", "Precondition and Effects", 
									"Transformers","Defining New Commands", "Functions Returning Sets of Nodes or Unitpaths"});
				commandMenusComboBox = new JComboBox();
				this.add(commandMenusComboBox);
				commandMenusComboBox.setModel(jComboBox2Model);
				commandMenusComboBox.setBounds(189, 28, 196, 22);
				commandMenusComboBox.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						commandMenusComboBoxActionPerformed(evt);
					}
				
				});
			}
			{
				commandsPanel = new JPanel();
				this.add(commandsPanel);
				commandsPanel.setBounds(40, 67, 694, 233);
				commandsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			}
			{

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void commandMenusComboBoxActionPerformed(ActionEvent evt) {
		if(commandMenusComboBox.getSelectedItem().equals("Relations")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("define");
			dcbm.addElement("undefine");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDefine(network));
		} 
		else if(commandMenusComboBox.getSelectedItem().equals("Case Frames")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("define-caseframe");
			dcbm.addElement("undefine-caseframe");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdCaseFrame(network));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Reduction Inference")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("deduce");
			commandsComboBox.setModel(dcbm);
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Path-Based Inference")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("define-path");
			dcbm.addElement("undefine-path");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDefinePath(network));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Contexts")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("set-context");
			dcbm.addElement("set-default-context");
			dcbm.addElement("add-to-context");
			dcbm.addElement("remove-from-context");
			dcbm.addElement("describe-context");
			dcbm.addElement("list-hypotheses");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdSetContext(network));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Building Networks")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("assert");
			dcbm.addElement("add");
			dcbm.addElement("activate");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAssert(network));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Deleting Information")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("erase");
			dcbm.addElement("silent-erase");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdErase(network));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Displaying Information")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("dump");
			dcbm.addElement("describe");
			dcbm.addElement("full-describe");
			dcbm.addElement("surface");
			commandsComboBox.setModel(dcbm);
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Retrieving Information")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("find");
			dcbm.addElement("findassert");
			dcbm.addElement("findconstant");
			dcbm.addElement("findbase");
			dcbm.addElement("findvariable");
			dcbm.addElement("findpattern");
			dcbm.addElement("deduce");
			dcbm.addElement("deducetrue");
			dcbm.addElement("deducefalse");
			dcbm.addElement("deducewh");
			dcbm.addElement("deducewhnot");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFind(network));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Connectives")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("And-Entailment");
			dcbm.addElement("Or-Entailment");
			dcbm.addElement("Numerical Entailment");
			dcbm.addElement("AndOr");
			dcbm.addElement("Thresh");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAndEntailment(network));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Quantifiers")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("Universal Quantifier");
			dcbm.addElement("Skolem Function");
			dcbm.addElement("Numerical Quantifier");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUniversalQunatifier());
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Tracing Inference")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("ev-trace");
			dcbm.addElement("unev-trace");
			dcbm.addElement("in-trace");
			dcbm.addElement("unin-trace");
			dcbm.addElement("multi::print-regs");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdEVTrace());
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Acting")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("perform");
			dcbm.addElement("{<whenever, p>,<do, a>}");
			dcbm.addElement("{<when, p>,<do, a>}");
			dcbm.addElement("{<if, p>,<do, a>}");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdPerform(network));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Primitive Acts")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("define-primaction");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDefinePrimaction(network));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Complex Acts")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("{<plan, p>,<act, a>}");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdActPlan(network));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Goals")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			commandsComboBox.setModel(dcbm);
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Preconditions and Effects")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("{<act, a>,<precondition, p>}");
			dcbm.addElement("{<act, a>,<effect, p>}");
			commandsComboBox.setModel(dcbm);
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Transformers")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("apply-function-to-ns");
			dcbm.addElement("java-list-to-ns");
			dcbm.addElement("ns-to-java-list");
			dcbm.addElement("node-to-java-object");
			dcbm.addElement("java-object-to-node");
			commandsComboBox.setModel(dcbm);
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Defining New Commands")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("defsnepscom");
			commandsComboBox.setModel(dcbm);
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Functions Returning Sets of Nodes or Unitpaths")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("list-nodes");
			dcbm.addElement("*");
			dcbm.addElement("^");
			dcbm.addElement("&");
			dcbm.addElement("+");
			dcbm.addElement("-");
			dcbm.addElement("=");
			dcbm.addElement("_");
			dcbm.addElement(">");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdListNodes());
		}
		this.repaint();
		this.validate();
	}

	private void commandsComboBoxItemStateChanged(ItemEvent evt) {
		if(commandsComboBox.getSelectedItem().equals("define")) {	
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDefine(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("undefine")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUndefine(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("define-caseframe")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdCaseFrame(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("undefine-caseframe")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUndefineCaseFrame(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("assert")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAssert(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("add")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAdd(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("activate")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdActivate(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("erase")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdErase(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("silent-erase")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdSilentErase(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("define-path")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDefinePath(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("undefine-path")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUndefinePath(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("add-to-context")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAddToContext(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("set-context")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdSetContext(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("set-default-context")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdSetDefaultContext());
		}
		else if(commandsComboBox.getSelectedItem().equals("remove-from-context")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdRemoveFromContext(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("describe-context")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDescribeContext(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("list-hypotheses")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdListHypotheses());
		}
		else if(commandsComboBox.getSelectedItem().equals("dump")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDump(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("describe")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDescribe(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("full-describe")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDescribe(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("find")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFind(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("findassert")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFindAssert(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("findconstant")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFindConstant(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("findbase")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFindBase(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("findvariable")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFindVariable(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("findpattern")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFindPattern(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("ev-trace")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdEVTrace());
		}
		else if(commandsComboBox.getSelectedItem().equals("unev-trace")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUNEVTrace());
		}
		else if(commandsComboBox.getSelectedItem().equals("in-trace")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdINTrace());
		}
		else if(commandsComboBox.getSelectedItem().equals("unin-trace")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUNINTrace());
		}
		else if(commandsComboBox.getSelectedItem().equals("multi::print-regs")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdMultiPrintRegs());
		}
		else if(commandsComboBox.getSelectedItem().equals("deduce")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDeduce(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("deducetrue")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDeduceTrue(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("deducefalse")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDeduceFalse(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("deducewh")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDeduceWH(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("deducewhnot")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDeduceWHNOT(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("{<whenever, p>,<do, a>}")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdWheneverDo(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("{<when, p>,<do, a>}")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdWhenDo(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("{<if, p>,<do, a>}")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdIfDo(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("believe")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdBelieve(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("disbelieve")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDisbelieve(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("do-all")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDoAll(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("do-one")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDoOne());
		}
		else if(commandsComboBox.getSelectedItem().equals("do-one")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDoOne());
		}
		else if(commandsComboBox.getSelectedItem().equals("{<plan, p>,<act, a>}")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDoOne());
		}
		else if(commandsComboBox.getSelectedItem().equals("list-nodes")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdListNodes());
		}
		else if(commandsComboBox.getSelectedItem().equals("Universal Quantifier")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUniversalQunatifier());
		}
		else if(commandsComboBox.getSelectedItem().equals("And-Entailment")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAndEntailment(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("Or-Entailment")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdOrEntailment(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("Numerical Entailment")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdNumericalEntailment(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("define-primaction")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDefinePrimaction(network));
		}
		this.repaint();
		this.validate();
	}
	
	public void assertMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdAssert(network));
		commandsComboBox.setSelectedItem("assert");
		commandMenusComboBox.setSelectedItem("Building Networks");
		this.repaint();
		this.validate();
	}
	
	public void defineMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDefine(network));
		commandsComboBox.setSelectedItem("define");
		commandMenusComboBox.setSelectedItem("Relations");
		this.repaint();
		this.validate();
	}
	
	public void findMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdFind(network));
		commandsComboBox.setSelectedItem("find");
		commandMenusComboBox.setSelectedItem("Retrieving Information");
		this.repaint();
		this.validate();
	}

	public void eraseMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdErase(network));
		commandsComboBox.setSelectedItem("erase");
		commandMenusComboBox.setSelectedItem("Deleting Information");
		this.repaint();
		this.validate();
	}

	public void silentEraseMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdSilentErase(network));
		commandsComboBox.setSelectedItem("silent-erase");
		commandMenusComboBox.setSelectedItem("Deleting Information");
		this.repaint();
		this.validate();
	}

	public void dumpMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDump(network));
		commandsComboBox.setSelectedItem("dump");
		commandMenusComboBox.setSelectedItem("Displaying Information");
		this.repaint();
		this.validate();
	}

	public void describeMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDescribe(network));
		commandsComboBox.setSelectedItem("describe");
		commandMenusComboBox.setSelectedItem("Displaying Information");
		this.repaint();
		this.validate();
	}

	public void fullDescribeMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdFullDescribe(network));
		commandsComboBox.setSelectedItem("full-describe");
		commandMenusComboBox.setSelectedItem("Displaying Information");
		this.repaint();
		this.validate();
	}

	public void undefineMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdUndefine(network));
		commandsComboBox.setSelectedItem("undefine");
		commandMenusComboBox.setSelectedItem("Relations");
		this.repaint();
		this.validate();
	}

	public void addMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdAdd(network));
		commandsComboBox.setSelectedItem("add");
		commandMenusComboBox.setSelectedItem("Building Networks");
		this.repaint();
		this.validate();
	}

	public void activateMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdActivate(network));
		commandsComboBox.setSelectedItem("activate");
		commandMenusComboBox.setSelectedItem("Building Networks");
		this.repaint();
		this.validate();
	}

	public void undefinePathMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdUndefinePath(network));
		commandsComboBox.setSelectedItem("undefine-path");
		commandMenusComboBox.setSelectedItem("Path-Based Inference");
		this.repaint();
		this.validate();
	}

	public void setContextMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdSetContext(network));
		commandsComboBox.setSelectedItem("set-context");
		commandMenusComboBox.setSelectedItem("Contexts");
		this.repaint();
		this.validate();
	}

	public void setDefaultContextMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdSetDefaultContext());
		commandsComboBox.setSelectedItem("set-default-context");
		commandMenusComboBox.setSelectedItem("Contexts");
		this.repaint();
		this.validate();
	}

	public void addToContextMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdAddToContext(network));
		commandsComboBox.setSelectedItem("set-to-context");
		commandMenusComboBox.setSelectedItem("Contexts");
		this.repaint();
		this.validate();
	}

	public void removeFromContextMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdRemoveFromContext(network));
		commandsComboBox.setSelectedItem("remove-from-context");
		commandMenusComboBox.setSelectedItem("Contexts");
		this.repaint();
		this.validate();
	}

	public void describeContextMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDescribeContext(network));
		commandsComboBox.setSelectedItem("describe-context");
		commandMenusComboBox.setSelectedItem("Contexts");
		this.repaint();
		this.validate();
	}

	public void listHypothesesMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdListHypotheses());
		commandsComboBox.setSelectedItem("list-hypothese");
		commandMenusComboBox.setSelectedItem("Contexts");
		this.repaint();
		this.validate();
	}

	public void listContextNamesMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdListContextNames());
		commandsComboBox.setSelectedItem("list-context-names");
		commandMenusComboBox.setSelectedItem("Contexts");
		this.repaint();
		this.validate();
	}
	
	public void defineCaseFrameMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdCaseFrame(network));
		commandsComboBox.setSelectedItem("define-caseframe");
		commandMenusComboBox.setSelectedItem("Case Frames");
		this.repaint();
		this.validate();
	}
	
	public void undefineCaseFrameMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdUndefineCaseFrame(network));
		commandsComboBox.setSelectedItem("undefine-caseframe");
		commandMenusComboBox.setSelectedItem("Case Frames");
		this.repaint();
		this.validate();
	}
}