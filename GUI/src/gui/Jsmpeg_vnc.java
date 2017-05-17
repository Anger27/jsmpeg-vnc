package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import net.miginfocom.swing.MigLayout;
import tool.Cmdexec;
import tool.GetmyIP;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;

/*
 * GUI_ver0.2
 * 
 * VNC with mpeg (ffmpeg)
 * server GUI program
 * 
 * server ip ���
 * port �Է¶� -p
 * bitrate (default : window size�� ����) -b
 * framerate -f
 * window size -s
 * crop area -c
 * enable/disable remote -i
 * */

public class Jsmpeg_vnc {

	private JFrame frame;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private boolean config_veiw = false;
	private JPanel config_panel;
	private JTextArea textPane;
	private Thread_cmdexecuter ex;

	private Cmdexec ce = Cmdexec.sharedInstance();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jsmpeg_vnc window = new Jsmpeg_vnc();
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
	public Jsmpeg_vnc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(" [Server] Jsmpeg_vcn");
		frame.setBounds(100, 100, 636, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				
				
				super.windowClosing(e);
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new MigLayout("", "[100.00,grow][10px:n:100px,grow]30[200px,left][79.00,grow][87.00]", "[10px][][][][][][grow][grow]"));
		
		GetmyIP getip = new GetmyIP();
		String myaddress = getip.myIP;
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "vnc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2, "cell 0 1 3 1,grow");
		panel_2.setLayout(new MigLayout("", "[100.00,grow][10px:n:100px,grow]30[200px,left]", "[]"));
		
		JLabel label_1 = new JLabel("IP : ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_1, "cell 0 0,alignx right");
		
		JLabel label_2 = new JLabel("220.149.11.227");
		panel_2.add(label_2, "cell 1 0");
		
		JLabel label_3 = new JLabel("\uB0B4 IP\uC8FC\uC18C");
		panel_2.add(label_3, "cell 2 0");
		
		JButton btnNewButton_1 = new JButton("��� ����");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				config_veiw = !config_veiw;
				config_panel.setVisible(config_veiw);
				
				//if(config_veiw)
					//config_panel.setPreferredSize(preferredSize);
			}
		});
		
		JButton btnNewButton = new JButton("����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ce.execute();
				
			}
		});
		panel.add(btnNewButton, "cell 3 1,alignx left");
		panel.add(btnNewButton_1, "cell 2 2,alignx right");
		
		JButton button = new JButton("����");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		panel.add(button, "cell 3 2,alignx left");
		
		config_panel = new JPanel();
		config_panel.setBorder(new TitledBorder(null, "���", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(config_panel, "cell 0 3 3 1,grow");
		config_panel.setLayout(new MigLayout("", "[100.00,grow][10px:n:100px,grow]30[200px,left]", "[][][][][]"));
		
		
		JLabel label_5 = new JLabel("Port : ");
		config_panel.add(label_5, "cell 0 0,alignx right");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		config_panel.add(textField_4, "cell 1 0,growx");
		
		JLabel label_10 = new JLabel("��Ʈ ����");
		config_panel.add(label_10, "cell 2 0");
		
		JLabel label_6 = new JLabel("Bitrate : ");
		config_panel.add(label_6, "cell 0 1,alignx right");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		config_panel.add(textField_5, "cell 1 1,growx");
		
		JLabel label_15 = new JLabel("bitrate in kilobit/s (default: estimated by output size)");
		config_panel.add(label_15, "flowx,cell 2 1");
		
		JLabel label_7 = new JLabel("Window size : ");
		config_panel.add(label_7, "cell 0 2,alignx right");
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		config_panel.add(textField_6, "cell 1 2,growx");
		
		JLabel label_12 = new JLabel("WxH. E.g: 640x480 (default: same as window size)");
		config_panel.add(label_12, "cell 2 2");
		
		JLabel label_8 = new JLabel("remote : ");
		config_panel.add(label_8, "cell 0 3,alignx right");
		
		JCheckBox checkBox = new JCheckBox("enable");
		config_panel.add(checkBox, "cell 1 3");
		
		JLabel label_13 = new JLabel("enable/disable remote input. E.g. -i 0 (default: 1)");
		config_panel.add(label_13, "cell 2 3");
		
		JLabel label_9 = new JLabel("crop : ");
		config_panel.add(label_9, "cell 0 4,alignx right");
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		config_panel.add(textField_7, "cell 1 4,growx");
		
		JLabel label_14 = new JLabel("aptured window as X,Y,W,H. E.g.: -c 200,300,640,480");
		config_panel.add(label_14, "cell 2 4");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, "cell 0 6 4 2,grow");
		
		textPane = new JTextArea();
		scrollPane_1.setViewportView(textPane);
	}

	
	

}
