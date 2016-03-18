package myCode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TimerTask;

import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import javax.swing.text.PlainDocument;

public class CapMain extends JFrame implements ActionListener {
	
	private static CapMain Main;
	private static JPanel myPanel;
	private static JTabbedPane Tabs;
	private static JButton endFolder;
	private static JPanel tab1;
	private static JPanel tab2;
	private static JPanel tab3;
	private static GridBagConstraints Consts;
	private static GridBagLayout Layout;
	private static int returnVal;
	private static JFileChooser fc = new JFileChooser();
	private static File FolderChoice = new File("none selected");
	private static JLabel Filename;
	private static JLabel ComboLabel;
	private static JComboBox<String> FileTypes;
	private static JTextField percentage;
	private static JTextField speed;
	private static int state = 0;
	private static int speedInt;
	private static JButton Start = new JButton("Start");
	private static int frame = 0;
	private static int sizeint;
	private static String outstream;
	private static java.util.Timer timer = new java.util.Timer();
	private static File config;
	private static JButton VideoIze = new JButton("VideoIze");
	
	public static void main(String[] args) {
		Main = new CapMain();
		myPanel = new JPanel();
		Tabs = new JTabbedPane();
		tabBuilder();
		Main.setTitle("Screen Timelapse Capture");
		Main.add(myPanel, BorderLayout.CENTER);
		Main.setSize(480, 280);
		Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main.setMinimumSize(new Dimension(480,280));
		Main.setMaximumSize(new Dimension(500,500));
		Main.setVisible(true);
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            saveconfig();
	        }
		}));
		
	}
	
	public static void tabBuilder(){
		myPanel.setLayout(new GridLayout(1,1));
		tab1 = new JPanel();
		Layout = new GridBagLayout();
		tab1.setLayout(Layout);
		Tab1();
		//tab2.setLayout(Layout);
		Tabs.addTab("Setup",tab1);
		Tabs.addTab("POOTIS",new JTextArea("BUllocks"));
		myPanel.add(Tabs, BorderLayout.NORTH);
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    fc.setAcceptAllFileFilterUsed(false);
		
	}
	
	public static void Tab1(){
		endFolder = new JButton("Choose a Destination Folder:");
		endFolder.addActionListener(Main);
		endFolder.setActionCommand("DestFolder");
		Consts = new GridBagConstraints();
		Consts.gridy = 0;
		Consts.gridx = 0;
		Consts.weightx = 0.5;
		Consts.fill = GridBagConstraints.HORIZONTAL;
		tab1.add(endFolder,Consts);
		
		tab1JLabel();
		
		tab1JButton();
		
		Consts = new GridBagConstraints();
		Consts.gridy = 2;
		Consts.gridx = 1;
		Consts.weightx = 0.5;
		Consts.fill = GridBagConstraints.HORIZONTAL;
		percentage = new JTextField();
		PlainDocument doc = (PlainDocument) percentage.getDocument();
	    doc.setDocumentFilter(new DocFilter());
		tab1.add(percentage,Consts);
		
		Consts = new GridBagConstraints();
		Consts.gridy = 3;
		Consts.gridx = 1;
		Consts.weightx = 0.5;
		Consts.fill = GridBagConstraints.HORIZONTAL;
		speed = new JTextField();
		PlainDocument doc2 = (PlainDocument) speed.getDocument();
	    doc2.setDocumentFilter(new DocFilter());
		tab1.add(speed,Consts);
		
		
		Consts = new GridBagConstraints();
		Consts.gridy = 4;
		Consts.gridx = 0;
		Consts.weightx = 0.5;
		Consts.weighty = 0.5;
		Consts.fill = GridBagConstraints.REMAINDER;
		//tab1.add(new JPanel(), Consts);
		
		
	}

	public static void tab1JButton(){
		Consts = new GridBagConstraints();
		Consts.gridy = 4;
		Consts.gridx = 1;
		Consts.weightx = 0.5;
		Consts.weighty = 0.5;
		Consts.gridwidth = 1;
		Start.addActionListener(Main);
		Start.setActionCommand("start");
		Consts.fill = GridBagConstraints.BOTH;
		tab1.add(Start,Consts);
		
		Consts = new GridBagConstraints();
		Consts.gridy = 4;
		Consts.gridx = 0;
		Consts.weightx = 0.5;
		Consts.weighty = 0.5;
		Consts.gridwidth = 1;
		VideoIze.addActionListener(Main);
		VideoIze.setActionCommand("VideoIze");
		Consts.fill = GridBagConstraints.BOTH;
		tab1.add(VideoIze,Consts);
		
	}
	
	public static void tab1JLabel(){
		Filename = new JLabel("none Selected");
		Consts = new GridBagConstraints();
		Consts.gridy = 0;
		Consts.gridx = 1;
		Consts.weightx = 0.75;
		Consts.fill = GridBagConstraints.HORIZONTAL;
		tab1.add(Filename,Consts);
		
		Consts = new GridBagConstraints();
		Consts.gridy = 1;
		Consts.gridx = 0;
		Consts.weightx = 0.5;
		Consts.fill = GridBagConstraints.HORIZONTAL;
		ComboLabel = new JLabel("Choose File Type");
		tab1.add(ComboLabel,Consts);
		
		Consts = new GridBagConstraints();
		Consts.gridy = 1;
		Consts.gridx = 1;
		Consts.weightx = 0.5;
		Consts.fill = GridBagConstraints.HORIZONTAL;
		FileTypes = new JComboBox<String>();
		FileTypes.addItem("");
		FileTypes.addItem("png");
		FileTypes.addItem("jpeg");
		FileTypes.addItem("gif");
		tab1.add(FileTypes,Consts);
		
		Consts = new GridBagConstraints();
		Consts.gridy = 2;
		Consts.gridx = 0;
		Consts.weightx = 0.5;
		Consts.fill = GridBagConstraints.HORIZONTAL;
		ComboLabel = new JLabel("Choose % of screen size output should be");
		tab1.add(ComboLabel,Consts);
		
		Consts = new GridBagConstraints();
		Consts.gridy = 3;
		Consts.gridx = 0;
		Consts.weightx = 0.5;
		Consts.fill = GridBagConstraints.HORIZONTAL;
		ComboLabel = new JLabel("Amount to Speed Up (ex: 1x, 2x...)");
		tab1.add(ComboLabel,Consts);
	}


	



	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getActionCommand().equals("DestFolder")){
				returnVal = fc.showOpenDialog(endFolder);
				if(returnVal==0){
					FolderChoice = fc.getSelectedFile();
					Filename.setText(FolderChoice.getName());
					outstream = fc.getSelectedFile().getAbsolutePath();
					if(new File(outstream+"/"+"config").isFile()){
						config = new File(outstream+"/"+"config");
						String[] conf = ConfigParser.Parse(config);
						percentage.setText(conf[0]);
						speed.setText(conf[1]);
						frame = Integer.parseInt(conf[2]);
						FileTypes.setSelectedItem(conf[3]);
					}
				}
			}
		 if(e.getActionCommand().equals("start")){
			 if(speed.getText().equals("") || FileTypes.getSelectedItem().equals("") || 
					 percentage.getText().equals("") || Filename.getText().equals("none Selected")) 
				 return;
			 state *= -1;
			 state +=1;
			 if(Start.getText().equals("Start")){
				 Start.setText("Stop");
				 timer = new java.util.Timer();
			 } else {
				 try{
				 timer.cancel();
				 } catch (IllegalStateException alpha){
					 
				 }
				 Start.setText("Start");
				 return;
			 }
			 Main.pack();
			 speedInt = Integer.parseInt(speed.getText());
			 sizeint = Integer.parseInt(percentage.getText());
			 int printed = 0;
			 final int sleep = (1000/30)/speedInt;
			 try {
				final Toolkit toolkit = Toolkit.getDefaultToolkit();
				final Dimension screenSize = toolkit.getScreenSize();
				final Rectangle screenRect = new Rectangle(screenSize);
				final Robot robot = new Robot();
			
			 timer.scheduleAtFixedRate(new TimerTask()
		      {
		        public void run()
		        {
		        	try {
		        		
		    		 	frame+=1;
		    			Capture.TakeShot(frame, sizeint, (String)FileTypes.getSelectedItem(), outstream, robot, screenRect);  // make own thread
		    			//Capture captureRun = new Capture(frame, sizeint, (String)FileTypes.getSelectedItem(), outstream);
		    			//captureRun.start();
		    		} catch(NumberFormatException nfe) {
		    			System.exit(1);
		    		}
		        }
		      }, 0, sleep);
			 } catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 }
		 
		 if(e.getActionCommand().equals("VideoIze")){
			 //CompileVideo.BuildVideo(frame, outstream, Integer.parseInt(percentage.getText()), 
				//	 		(String)FileTypes.getSelectedItem(), Integer.parseInt(speed.getText()));
		 }
	}
	
	private static int saveconfig(){
		String out = percentage.getText() + "\n" + speed.getText() + "\n" + ((Integer)frame).toString() + "\n" + FileTypes.getSelectedItem();
		File file = new File(outstream + "/" +"config");
		FileWriter fw;
		if(outstream == null)
			return JFrame.EXIT_ON_CLOSE;
		try {
			fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
		    bw.write(out);
		    bw.close();
		    return JFrame.EXIT_ON_CLOSE;	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JFrame.EXIT_ON_CLOSE;
			
	}

}









