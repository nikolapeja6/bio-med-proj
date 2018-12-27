package rs.ac.bg.etf.pp1;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.*;

public class Gui {
	
	static final JFileChooser fc = new JFileChooser();
	static JTextArea textArea;
	static File file;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		create();
	}

	public static void create() {
		JFrame frame = new JFrame("Patient state evaluator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1100, 700);

		createMenu(frame);
		createContent(frame);

		// JButton button = new JButton("Press");
		// frame.getContentPane().add(button); // Adds Button to content pane of
		// frame
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void createContent(JFrame frame){
		textArea = new JTextArea();
		frame.add(textArea);
	}

	public static void createMenu(JFrame frame) {
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
		KeyStroke keyStroke;

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		// Build the first menu.
		menu = new JMenu("File");
		menu.getAccessibleContext().setAccessibleDescription("File related options");
		menuBar.add(menu);

		// a group of JMenuItems


		menuItem = new JMenuItem("Open Rules");
		keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
		menuItem.setAccelerator(keyStroke);
		menuItem.getAccessibleContext().setAccessibleDescription("Open rules from spec file");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int returnVal = fc.showOpenDialog(frame);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            file = fc.getSelectedFile();
		            StringBuilder sb = new StringBuilder();
		            try(BufferedReader br = new BufferedReader(new FileReader(file))){
		            	String line = br.readLine();
		            	while(line != null){
		            		sb.append(line);
		            		sb.append("\r\n");
		            		line = br.readLine();
		            	}
		            	textArea.setText(sb.toString());
		            }catch(Exception ee){
		            	ee.printStackTrace();
		            }
		        }
			}
		});
		menu.add(menuItem);

		
		menuItem = new JMenuItem("Save Rules");
		keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
		menuItem.setAccelerator(keyStroke);
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		

		menu.addSeparator();
		/*
		
		ButtonGroup group = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Another one");
		rbMenuItem.setMnemonic(KeyEvent.VK_O);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		// a group of check box menu items
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
		cbMenuItem.setMnemonic(KeyEvent.VK_C);
		menu.add(cbMenuItem);

		cbMenuItem = new JCheckBoxMenuItem("Another one");
		cbMenuItem.setMnemonic(KeyEvent.VK_H);
		menu.add(cbMenuItem);

		// a submenu
		menu.addSeparator();
		submenu = new JMenu("A submenu");
		submenu.setMnemonic(KeyEvent.VK_S);

		menuItem = new JMenuItem("An item in the submenu");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		submenu.add(menuItem);

		menuItem = new JMenuItem("Another item");
		submenu.add(menuItem);
		menu.add(submenu);
		*/
		
	}

}
