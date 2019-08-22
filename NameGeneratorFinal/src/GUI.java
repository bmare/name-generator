
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.io.File;


/*To test the GUI put WizardNames.txt on your desktop, then in the path text field of the GUI type: /Users/YOURUSERNAMEHERE/Desktop/WizardNames.txt. 
 * proceed to type in however many names you want to generate and then hit the generate names button. If you specified the path correctly the labels text should change
 * and there should be a new text file called names.txt in your home folder */

public class GUI extends JPanel implements  ActionListener {
	private NameGenerator n;
	
	//labels for text fields
	private JLabel pathName;
	private JLabel numberOfNames;
	private JLabel namesArea;
	
	//text fields
	private JFormattedTextField path;
	private JFormattedTextField number;
	
	private JButton generateNames;
	

	
	private NumberFormat numberRestrict;
	private String thePath;
	private int theNumber;
	
	private StringBuilder sb;
	
	//implements the name generator class
	public void constructNameGeneration() {
		try {	
			File file = new File(path.getText());
			n = new NameGenerator(file.getPath());
			n.setNumberofNames(theNumber); 
			n.setPathName(thePath);
			n.randomLine();
			setLabelText();
			n.writeText(n.names);
		} catch(Exception e) {
			this.namesArea.setText(e.getMessage());
		}
	}
	//sets namesArea label to the contents of the names array list
	public void setLabelText() {
		try {
			sb = new StringBuilder();
			for (int i=0; i < n.names.size();i++) {
			    sb.append(n.names.get(i) + ", ");
			}
			this.namesArea.setText(sb.toString());
		} catch(Exception e) {
			this.namesArea.setText(e.getMessage());
		}
	}
	//Gui constructor initializing text fields, labels and adding them to a pane
	public GUI()  {
		super(new BorderLayout());
		pathName = new JLabel("Type Path: ");
		numberOfNames = new JLabel("How many names would you like to print?");
		namesArea = new JLabel("Names will be printed here!");
		
		path = new JFormattedTextField();
		path.setColumns(10);

		
		number = new JFormattedTextField(numberRestrict);
		number.setColumns(10);

		
		generateNames = new JButton("Generate All teh Namez!");
		generateNames.addActionListener(this);
		
		pathName.setLabelFor(path);
		numberOfNames.setLabelFor(number);

		
		JPanel labelPane = new JPanel(new GridLayout(0,1));
		labelPane.add(pathName);
		labelPane.add(numberOfNames);
		
		JPanel namesPane = new JPanel(new GridLayout(0,1));
		namesPane.add(namesArea);

		
		JPanel fieldPane = new JPanel(new GridLayout(0,1));
		fieldPane.add(path);
		fieldPane.add(number);

		
		JPanel buttonPane = new JPanel(new GridLayout(1,0));
		fieldPane.add(generateNames);
		
		setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
        add(buttonPane, BorderLayout.NORTH);
        add(namesPane, BorderLayout.PAGE_END);
        
	}
	//method to do stuff when the button is pressed
	public void actionPerformed(ActionEvent evt) {
		try {
			String p = path.getText();
			String n = number.getText();
			if(p.length() > 0 && n.length() > 0) {
				thePath = (String)path.getText();
				theNumber = Integer.parseInt((String)number.getText());
			} else {
				throw new Exception("You typed nothing in fool");
			}
			constructNameGeneration();
		} catch(Exception e) {
			this.namesArea.setText(e.getMessage());
		}
	}
	//makes the GUI visible and sets the default close operation to Exit on Close
	public void setUpGui() {
		JFrame j = new JFrame("Blah");
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.add(new GUI());
		j.pack();
		j.setVisible(true);
	}
	//some of this code was my attempt to make this project executable unfortunately times ubiquitous toll laid a scourge upon my mind and prevented me from completing this goal
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GUI g = new GUI();
				g.setUpGui();	
			}
		});
		
	}
}
