package trinity.poolmanager.ui;

import java.awt.EventQueue;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import trinity.db.mysql.SQLConnectResult;
import trinity.poolmanager.Db;
import trinity.poolmanager.Handler;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainForm {

	private JFrame frame;
	private Db db;
	private Handler handler;
	private JTextField txtEntry;
	private JTextField txtName;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tpMainTabPain = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tpMainTabPain, BorderLayout.CENTER);
		
		JPanel pPoolManagement = new JPanel();
		tpMainTabPain.addTab("Pool Management", null, pPoolManagement, null);
		pPoolManagement.setLayout(null);
		
		JLabel lblEntry = new JLabel("Entry");
		lblEntry.setBounds(13, 10, 37, 15);
		pPoolManagement.add(lblEntry);
		
		txtEntry = new JTextField();
		txtEntry.setBounds(78, 8, 124, 19);
		lblEntry.setLabelFor(txtEntry);
		pPoolManagement.add(txtEntry);
		txtEntry.setColumns(10);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBounds(78, 39, 252, 19);
		pPoolManagement.add(txtName);
		txtName.setColumns(10);
		
		JButton btnNewButton = new JButton("Do DB thing");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Integer entry = Integer.parseInt(txtEntry.getText());
				txtName.setText(db.GetGameObjectName(entry));
				
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Boolean results = handler.LoadData();
				frame.setCursor(Cursor.getDefaultCursor());
			}
		});
		btnNewButton.setBounds(214, 5, 116, 25);
		pPoolManagement.add(btnNewButton);
		
		JLabel lblName = new JLabel("Name");
		lblName.setLabelFor(txtName);
		lblName.setBounds(12, 41, 66, 15);
		pPoolManagement.add(lblName);
		
		JPanel pConfig = new JPanel();
		FlowLayout fl_pConfig = (FlowLayout) pConfig.getLayout();
		fl_pConfig.setAlignment(FlowLayout.RIGHT);
		tpMainTabPain.addTab("Configuration", null, pConfig, null);

		frame.addWindowListener(new java.awt.event.WindowAdapter() 
		{
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) 
		    {
		    	db.dispose();
		    }
		});	
		
		// This is just to make sure form is initialized first
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				db = new Db();
				SQLConnectResult result = db.Connect("localhost", "world_335_pooling-s3", "trinity", "trinity");
				handleResult(result);
				handler = new Handler(db);
			}
		});
	}
	
	private void handleResult(SQLConnectResult result)
	{
		if (result == SQLConnectResult.RESULT_BADCLASS)
		{
			JOptionPane.showMessageDialog(frame, "Unable to load either MySQL or MariaDB connector for java", 
					                      "MySQL Initialization error", JOptionPane.ERROR_MESSAGE);
			System.exit(10);
		}
		
		if (result == SQLConnectResult.RESULT_CREDENTIALERROR)
			JOptionPane.showMessageDialog(frame, "Invalid login details, unable to connect", 
							              "MySQL Initialization error", JOptionPane.WARNING_MESSAGE);
		
		if (result == SQLConnectResult.RESULT_DBACCESSDENIED)
			JOptionPane.showMessageDialog(frame, "Access denied to database, unable to connect", 
							              "MySQL Initialization error", JOptionPane.WARNING_MESSAGE);
		
		if (result == SQLConnectResult.RESULT_SQLEXCEPTION)
		{
			JOptionPane.showMessageDialog(frame, "Unknown SQL Exception", 
		              "MySQL Initialization error", JOptionPane.ERROR_MESSAGE);
			System.exit(10);
		}

		if (result == SQLConnectResult.RESULT_OTHEREXCEPTION)
		{
			JOptionPane.showMessageDialog(frame, "Unknown Exception", 
		              "MySQL Initialization error", JOptionPane.ERROR_MESSAGE);
			System.exit(10);
		}		
	}
}
