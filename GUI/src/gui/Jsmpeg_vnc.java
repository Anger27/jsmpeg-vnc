package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import tool.Cmdexec;
import tool.CreateBat;
import tool.GetmyIP;

import javax.swing.JTextArea;

/*
 * JSMPEG_VNC_GUI_VER0.3
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
	
	private static File tmp_path;
	public static String VNC_FILE_PATH = "";
	public String VNC_FILE_NAME = "jsmpeg-vnc\\jsmpeg-vnc.exe";

	private JFrame frame;
	private JTextField port_textfield;
	private JTextField bitrate_textfield;
	private JTextField windowsize_textfield;
	private JTextField crop_textfield;
	private boolean config_veiw = false;
	private JPanel config_panel;
	private JTextArea textPane;
	
	private String port = null;
	private String bitrate = null;
	private String framerate = null;
	private String windowsize = null;
	private String croparea = null;
	private boolean remote = true;
	
	
	
	protected boolean isExec = false;
	private JTextField framerate_textfield;
	private JCheckBox remote_checkBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		tmp_path = new File("."); 
		VNC_FILE_PATH = tmp_path.getAbsolutePath()+"\\jsmpeg-vnc\\";
		CreateBat cb = new CreateBat(VNC_FILE_PATH);
		cb.MakeStartBat();
		cb.MakeExitBat();
		
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
		frame.setBounds(100, 100, 550, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//���μ��� ����!! taskkill.bat ����

				
				super.windowClosing(e);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new MigLayout("", "[100.00][10px:n:100px,grow]30[]30[200px,left]", "[10px][][][][][grow][grow][grow]"));
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "VNC", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2, "cell 0 1 4 1,grow");
		panel_2.setLayout(new MigLayout("", "[100.00,grow][10px:n:100px,grow]30[200px,left]", "[]"));
		
		GetmyIP getip = new GetmyIP();
		
		JLabel label_1 = new JLabel("IP : ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_1, "cell 0 0,alignx right");
		JLabel label_2 = new JLabel("   192.168.121.1");
		panel_2.add(label_2, "cell 1 0,alignx left");
		
		JLabel lblDefaultPort = new JLabel("default port : 8080");
		panel_2.add(lblDefaultPort, "cell 2 0");
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				execute();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Config");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				config_veiw = !config_veiw;
				config_panel.setVisible(config_veiw);
				
				//if(config_veiw)
					//config_panel.setPreferredSize(preferredSize);
			}
		});
		panel.add(btnNewButton_1, "cell 1 2,alignx right");
		panel.add(btnNewButton, "cell 2 2,alignx left");
		
		config_panel = new JPanel();
		config_panel.setVisible(false);
		
		JButton btnExit = new JButton("Disconnect");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancel();
			}
		});
		panel.add(btnExit, "cell 3 2,alignx left");
		config_panel.setBorder(new TitledBorder(null, "Config", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(config_panel, "cell 0 3 4 1,grow");
		config_panel.setLayout(new MigLayout("", "[100.00,grow][10px:n:100px,grow]30[200px,left]", "[][][][][][]"));
		
		
		JLabel label_5 = new JLabel("Port : ");
		config_panel.add(label_5, "cell 0 0,alignx right");
		
		port_textfield = new JTextField();
		port_textfield.setColumns(10);
		config_panel.add(port_textfield, "cell 1 0,growx");
		
		JLabel lblPortdefault = new JLabel("port (default: 8080)");
		config_panel.add(lblPortdefault, "cell 2 0");
		
		JLabel label_6 = new JLabel("Bitrate : ");
		config_panel.add(label_6, "cell 0 1,alignx right");
		
		bitrate_textfield = new JTextField();
		bitrate_textfield.setColumns(10);
		config_panel.add(bitrate_textfield, "cell 1 1,growx");
		
		JLabel lblBitrateInKilobits = new JLabel("bitrate in kilobit/s");
		config_panel.add(lblBitrateInKilobits, "flowx,cell 2 1");
		
		JLabel label = new JLabel("Framerate : ");
		config_panel.add(label, "cell 0 2,alignx trailing");
		
		framerate_textfield = new JTextField();
		framerate_textfield.setColumns(10);
		config_panel.add(framerate_textfield, "cell 1 2,growx");
		
		JLabel lblTargetFrameratedefault = new JLabel("target framerate (default: 60)");
		config_panel.add(lblTargetFrameratedefault, "cell 2 2");
		
		JLabel label_7 = new JLabel("Window size : ");
		config_panel.add(label_7, "cell 0 3,alignx right");
		
		windowsize_textfield = new JTextField();
		windowsize_textfield.setColumns(10);
		config_panel.add(windowsize_textfield, "cell 1 3,growx");
		
		JLabel lblOutputSizeAs = new JLabel("output size (WxH)");
		config_panel.add(lblOutputSizeAs, "cell 2 3");
		
		JLabel lblRemote = new JLabel("Remote : ");
		config_panel.add(lblRemote, "cell 0 4,alignx right");
		
		remote_checkBox = new JCheckBox("enable");
		config_panel.add(remote_checkBox, "cell 1 4");
		
		JLabel lblEnabledisableRemoteInput = new JLabel("enable/disable remote input");
		config_panel.add(lblEnabledisableRemoteInput, "cell 2 4");
		
		JLabel lblCrop = new JLabel("Crop : ");
		config_panel.add(lblCrop, "cell 0 5,alignx right");
		
		crop_textfield = new JTextField();
		crop_textfield.setColumns(10);
		config_panel.add(crop_textfield, "cell 1 5,growx");
		
		JLabel lblxY = new JLabel("crop area in the captured window (X,Y,W,H)");
		config_panel.add(lblxY, "cell 2 5");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, "cell 0 5 4 3,grow");
		
		textPane = new JTextArea();
		textPane.setText("Usage : http://" + getip.myIP + ":8080\n");
		textPane.setEditable(false);
		scrollPane_1.setViewportView(textPane);
	}
	
	
	private String[] option(){
		//config_panel.isvisible
		// true -> gettextfield
		// false -> default
		//cmd = new String[]{"cmd","/c","vnc_start.bat jsmpeg-vnc.exe -p 80"}
		List<String> mList = new ArrayList<String>();
		
		mList.add("cmd");
		mList.add("/c");
		
		String arg = "vnc_start.bat jsmpeg-vnc.exe";
		//��޼���â�� �����������
		if(config_veiw){
			
			if(!port_textfield.getText().equals(""))
				arg= arg+" -p "+port_textfield.getText();
			if(!bitrate_textfield.getText().equals(""))
				arg= arg+" -b "+bitrate_textfield.getText();
			if(!framerate_textfield.getText().equals(""))
				arg= arg+" -f "+framerate_textfield.getText();
			if(!windowsize_textfield.getText().equals(""))
				arg= arg+" -s "+windowsize_textfield.getText();
			if(!crop_textfield.getText().equals(""))
				arg= arg+" -c "+crop_textfield.getText();
			
			arg = arg +" -i "+remote_checkBox.isSelected();
		}
		//��޼��� ���ϸ� vnc���� �˾Ƽ� default �� ����.
		
		mList.add(arg);
		
		

		
		return mList.toArray(new String[mList.size()]);
	}
	

	//cmd : ���ϸ� [�ɼ�] desktop
	
	private void execute() {
		//start.bat ����
		//Thread ���� ����
		if(isExec){
			System.out.println("Already running.");
			textPane.append("Error : Already running.\n");
			return;
		}
		isExec = true;
		String cmd[] = option();
		Access_vnc start_thread  = new Access_vnc(cmd);
		start_thread.start();
		
		//�����޽���
		
	}
	
	private void cancel(){
		//taskkill.bat ����
		if(!isExec){
			System.out.println("Not running.");
			return;
		}
		isExec = false;
		String cmd[] = {VNC_FILE_PATH+"vnc_exit.bat ","jsmpeg-vnc.exe"};
		Access_vnc exit_thread  = new Access_vnc(cmd);
		exit_thread.start();
		
	}
	
	class Access_vnc extends Thread {
		private String[] cmd = null;
		public Cmdexec start;

		public Access_vnc(String[] cmd) {
			this.cmd = cmd;

		}

		@Override
		public void run() {
			try{
			start = new Cmdexec(cmd);
			start.execute();

			String line = null;
			BufferedReader sbr = null;
			sbr = start.getReader();
			while ((line = sbr.readLine()) != null) {
				//textPane.append(line + "\n");
				if(line.contains("not exist")){
					textPane.append("error : Not found files : jsmpeg-vnc.exe.\n");
				}
				if(line.contains("exist")){
					textPane.append("connect : jsmpeg-vnc.exe\n");
				}
				if(line.contains("����")){
					textPane.append("disconnect : jsmpeg-vnc.exe\n");
				}
			}
			}catch (Exception e) {
				
			}
				super.run();
			}

		
	}




}
