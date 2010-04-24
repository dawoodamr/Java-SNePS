package snepsui.Interface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import snepsui.Commands.*;

import sneps.AndPath;
import sneps.BUnitPath;
import sneps.Cable;
import sneps.ComposePath;
import sneps.ConversePath;
import sneps.DomainRestrictPath;
import sneps.FUnitPath;
import sneps.IrreflexiveRestrictPath;
import sneps.KPlusPath;
import sneps.KStarPath;
import sneps.MolecularNode;
import sneps.Network;
import sneps.Node;
import sneps.OrPath;
import sneps.Path;
import sneps.RangeRestrictPath;
import sneps.RelativeComplementPath;
import sneps.UpCable;

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
	private SNePSInterface frame;
	private String newLine = "\n";
	private String endLine = "----------------------------------------" + "\n";
	private Point point;

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public MenuDrivenCommands(SNePSInterface frame) {
		super();
		this.network = null;
		this.frame = frame;
		this.point = new Point(10, 10);
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
			commandsPanel.add(new cmdDefine(network, frame));
		} 
		else if(commandMenusComboBox.getSelectedItem().equals("Case Frames")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("define-caseframe");
			dcbm.addElement("undefine-caseframe");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdCaseFrame(network, frame));
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
			commandsPanel.add(new cmdDefinePath(network, frame));
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
			commandsPanel.add(new cmdSetContext(network, frame));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Building Networks")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("assert");
			dcbm.addElement("add");
			dcbm.addElement("activate");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAssert(network, frame));
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
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDump(network, frame));
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
			commandsPanel.add(new cmdFind(network, frame));
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
			commandsPanel.add(new cmdAndEntailment(network, frame));
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
			commandsPanel.add(new cmdPerform(network, frame));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Primitive Acts")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("define-primaction");
			dcbm.addElement("do-all");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDefinePrimaction(network));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Complex Acts")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("{<plan, p>,<act, a>}");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdActPlan(network, frame));
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
			commandsPanel.add(new cmdDefine(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("undefine")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUndefine(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("define-caseframe")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdCaseFrame(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("undefine-caseframe")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUndefineCaseFrame(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("assert")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAssert(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("add")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAdd(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("activate")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdActivate(network, frame));
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
			commandsPanel.add(new cmdDefinePath(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("undefine-path")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUndefinePath(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("add-to-context")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAddToContext(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("set-context")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdSetContext(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("set-default-context")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdSetDefaultContext());
		}
		else if(commandsComboBox.getSelectedItem().equals("remove-from-context")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdRemoveFromContext(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("describe-context")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDescribeContext(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("list-hypotheses")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdListHypotheses(network));
		}
		else if(commandsComboBox.getSelectedItem().equals("dump")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDump(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("describe")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDescribe(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("full-describe")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDescribe(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("find")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFind(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("findassert")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFindAssert(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("findconstant")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFindConstant(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("findbase")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFindBase(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("findvariable")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFindVariable(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("findpattern")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFindPattern(network, frame));
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
			commandsPanel.add(new cmdDeduce(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("deducetrue")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDeduceTrue(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("deducefalse")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDeduceFalse(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("deducewh")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDeduceWH(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("deducewhnot")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDeduceWHNOT(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("{<whenever, p>,<do, a>}")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdWheneverDo(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("{<when, p>,<do, a>}")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdWhenDo(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("{<if, p>,<do, a>}")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdIfDo(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("believe")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdBelieve(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("disbelieve")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDisbelieve(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("do-all")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDoAll(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("do-one")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDoOne(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("{<plan, p>,<act, a>}")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDoOne(network, frame));
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
			commandsPanel.add(new cmdAndEntailment(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("Or-Entailment")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdOrEntailment(network, frame));
		}
		else if(commandsComboBox.getSelectedItem().equals("Numerical Entailment")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdNumericalEntailment(network, frame));
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
		commandsPanel.add(new cmdAssert(network, frame));
		commandsComboBox.setSelectedItem("assert");
		commandMenusComboBox.setSelectedItem("Building Networks");
		this.repaint();
		this.validate();
	}
	
	public void defineMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDefine(network, frame));
		commandsComboBox.setSelectedItem("define");
		commandMenusComboBox.setSelectedItem("Relations");
		this.repaint();
		this.validate();
	}
	
	public void findMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdFind(network, frame));
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
		commandsPanel.add(new cmdDump(network, frame));
		commandsComboBox.setSelectedItem("dump");
		commandMenusComboBox.setSelectedItem("Displaying Information");
		this.repaint();
		this.validate();
	}

	public void describeMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDescribe(network, frame));
		commandsComboBox.setSelectedItem("describe");
		commandMenusComboBox.setSelectedItem("Displaying Information");
		this.repaint();
		this.validate();
	}

	public void fullDescribeMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdFullDescribe(network, frame));
		commandsComboBox.setSelectedItem("full-describe");
		commandMenusComboBox.setSelectedItem("Displaying Information");
		this.repaint();
		this.validate();
	}

	public void undefineMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdUndefine(network, frame));
		commandsComboBox.setSelectedItem("undefine");
		commandMenusComboBox.setSelectedItem("Relations");
		this.repaint();
		this.validate();
	}

	public void addMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdAdd(network, frame));
		commandsComboBox.setSelectedItem("add");
		commandMenusComboBox.setSelectedItem("Building Networks");
		this.repaint();
		this.validate();
	}

	public void activateMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdActivate(network, frame));
		commandsComboBox.setSelectedItem("activate");
		commandMenusComboBox.setSelectedItem("Building Networks");
		this.repaint();
		this.validate();
	}

	public void undefinePathMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdUndefinePath(network, frame));
		commandsComboBox.setSelectedItem("undefine-path");
		commandMenusComboBox.setSelectedItem("Path-Based Inference");
		this.repaint();
		this.validate();
	}

	public void setContextMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdSetContext(network, frame));
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
		commandsPanel.add(new cmdAddToContext(network, frame));
		commandsComboBox.setSelectedItem("set-to-context");
		commandMenusComboBox.setSelectedItem("Contexts");
		this.repaint();
		this.validate();
	}

	public void removeFromContextMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdRemoveFromContext(network, frame));
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
		commandsPanel.add(new cmdListHypotheses(network));
		commandsComboBox.setSelectedItem("list-hypothese");
		commandMenusComboBox.setSelectedItem("Contexts");
		this.repaint();
		this.validate();
	}
	
	public void defineCaseFrameMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdCaseFrame(network, frame));
		commandsComboBox.setSelectedItem("define-caseframe");
		commandMenusComboBox.setSelectedItem("Case Frames");
		this.repaint();
		this.validate();
	}
	
	public void undefineCaseFrameMenuButton() {
		commandsPanel.removeAll();
		commandsPanel.add(new cmdUndefineCaseFrame(network, frame));
		commandsComboBox.setSelectedItem("undefine-caseframe");
		commandMenusComboBox.setSelectedItem("Case Frames");
		this.repaint();
		this.validate();
	}
	
	/**
	 * Displays all the information of a node
	 * @param node the node that its information will be displayed
	 */
	public void nodeInfo(Node node) {
		LinkedList<Node> connectedNodes = new LinkedList<Node>();
		
		/*Print out node name*/
		frame.getOutputPanel1().writeToTextArea("Node Name: " + node.getIdentifier() + newLine);
		
		/*Print out semantic class*/
		node.getEntity();
		
		/*Print out cable*/
		if(node instanceof MolecularNode) {
			MolecularNode molNode = (MolecularNode) node;
			LinkedList<Cable> cables = molNode.getCableSet().getCables();
			
			if(!cables.isEmpty()) {
				frame.getOutputPanel1().writeToTextArea("Cable Set (Nodes pointing from " + node.getIdentifier() + "):" + newLine);
				for(Cable item1 : cables) {
					item1.getRelation();
					LinkedList<Node> nodes = item1.getNodeSet().getNodes();
					
					for(Node item2 : nodes) {
						frame.getOutputPanel1().writeToTextArea(item2.getIdentifier() + newLine);
						connectedNodes.add(item2);
					}
				}
			}
		}
		
		/*Print out up cable*/
		LinkedList<UpCable> nodeUpCables = node.getUpCableSet().getUpCables();
		
		if(!nodeUpCables.isEmpty()) {
			frame.getOutputPanel1().writeToTextArea("Up Cable Set (Nodes pointing to " + node.getIdentifier() + "): " + newLine);
			for(UpCable item3 : nodeUpCables) {
				item3.getRelation();
				LinkedList<Node> nodes = item3.getNodeSet().getNodes();
				
				for(Node item4 : nodes) {
					frame.getOutputPanel1().writeToTextArea(item4.getIdentifier() + newLine);
					connectedNodes.add(item4);
				}
			}
		}
		
		frame.getOutputPanel1().writeToTextArea(endLine);
		frame.getNodesResult().addNodes(connectedNodes);
	}
	
	/**
	 * Converts a SNePS path to a string for display purposes 
	 * @param path the path to be converted to a string
	 * @return the created path string
	 */
	public String createPath(Path path) {
		/*And*/
		if(path instanceof AndPath) {
			AndPath currentPath = (AndPath) path;
			LinkedList<Path> paths = currentPath.getPaths();
			String pathString = "(and ";
			
			for (int i = 0; i < paths.size(); i++) {
				pathString += createPath(paths.get(i));
			}
			
			pathString += ")";
			return pathString;
		/*Compose*/	
		} else if (path instanceof ComposePath) {
			ComposePath currentPath = (ComposePath) path;
			LinkedList<Path> paths = currentPath.getPaths();
			String pathString = "(compose ";
			
			for (int i = 0; i < paths.size(); i++) {
				pathString += createPath(paths.get(i));
			}
			
			pathString += ")";
			return pathString;
		/*Converse*/
		} else if (path instanceof ConversePath) {
			ConversePath currentPath = (ConversePath) path;
			return "(converse " + createPath(currentPath.getPath()) + ")";
		/*DomainRestrict*/
		} else if (path instanceof DomainRestrictPath) {
			DomainRestrictPath currentPath = (DomainRestrictPath) path;
			return "(domain-restrict (" + createPath(currentPath.getQ()) + currentPath.getNode().getIdentifier() 
				+ ")" + createPath(currentPath.getP()) + ")";
		/*IrreflexiveRestrict*/
		} else if (path instanceof IrreflexiveRestrictPath) {
			IrreflexiveRestrictPath currentPath = (IrreflexiveRestrictPath) path;
			return "(irreflexive-restrict " + createPath(currentPath.getPath()) + ")";
		/*KPlus*/
		} else if (path instanceof KPlusPath) {
			KPlusPath currentPath = (KPlusPath) path;
			return "(kplus " + createPath(currentPath.getPath()) + ")";
		/*KStar*/
		} else if (path instanceof KStarPath) {
			KStarPath currentPath = (KStarPath) path;
			return "(kstar " + createPath(currentPath.getPath()) + ")";
		/*Or*/
		} else if (path instanceof OrPath) {
			OrPath currentPath = (OrPath) path;
			LinkedList<Path> paths = currentPath.getPaths();
			String pathString = "(or ";
			
			for (int i = 0; i < paths.size(); i++) {
				pathString += createPath(paths.get(i));
			}
			
			pathString += ")";
			return pathString;
		/*RangeRestrict*/
		} else if (path instanceof RangeRestrictPath) {
			RangeRestrictPath currentPath = (RangeRestrictPath) path;
			return "(range-restrict " + createPath(currentPath.getP()) + "(" + createPath(currentPath.getQ()) 
				+ currentPath.getNode().getIdentifier() + "))";
		/*RelativeComplement*/
		} else if (path instanceof RelativeComplementPath) {
			RelativeComplementPath currentPath = (RelativeComplementPath) path;
			return "(relative-complement " + createPath(currentPath.getP()) + createPath(currentPath.getQ()) + ")";
		/*FUnitPath*/	
		} else if (path instanceof FUnitPath) {
			FUnitPath currentPath = (FUnitPath) path;
			return currentPath.getRelationName();
		/*BUnitPath*/
		} else if (path instanceof BUnitPath) {
			BUnitPath currentPath = (BUnitPath) path;
			return currentPath.getRelationName();
		} else {
			return "";
		}
	}
	
	public Point cascadePosition() {
		double x = point.getX() + 5;
		double y = point.getY() + 10;
		point.setLocation(x, y);
		return point;
	}
	
	public void cascadeBack() {
		double x = point.getX() - 5;
		double y = point.getY() - 10;
		point.setLocation(x, y);
	}
}