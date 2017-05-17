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
 * server ip 출력
 * port 입력란 -p
 * bitrate (default : window size에 맞춤) -b
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
		frame.setBounds(100, 100, 636, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//프로세스 종료!! taskkill.bat 실행

				
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
		panel.setLayout(new MigLayout("", "[100.00,grow][10px:n:100px,grow]30[200px,left][79.00,grow]", "[10px][][][][][grow][grow][grow]"));
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "vnc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2, "cell 0 1 3 1,grow");
		panel_2.setLayout(new MigLayout("", "[100.00,grow][10px:n:100px,grow]30[200px,left]", "[]"));
		
		JLabel label_1 = new JLabel("IP : ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_1, "cell 0 0,alignx right");
		
		GetmyIP getip = new GetmyIP();
		JLabel label_2 = new JLabel(getip.myIP);
		panel_2.add(label_2, "cell 1 0");
		
		JLabel label_3 = new JLabel("\uB0B4 IP\uC8FC\uC18C");
		panel_2.add(label_3, "cell 2 0");
		
		JButton btnNewButton_1 = new JButton("고급 설정");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				config_veiw = !config_veiw;
				config_panel.setVisible(config_veiw);
				
				//if(config_veiw)
					//config_panel.setPreferredSize(preferredSize);
			}
		});
		
		JButton btnNewButton = new JButton("실행");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				execute();
			}
		});
		panel.add(btnNewButton, "cell 3 1,alignx left");
		panel.add(btnNewButton_1, "cell 2 2,alignx right");
		
		JButton button = new JButton("종료");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancel();
			}
		});
		panel.add(button, "cell 3 2,alignx left");
		
		config_panel = new JPanel();
		config_panel.setVisible(false);
		config_panel.setBorder(new TitledBorder(null, "고급", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(config_panel, "cell 0 3 3 1,grow");
		config_panel.setLayout(new MigLayout("", "[100.00,grow][10px:n:100px,grow]30[200px,left]", "[][][][][][]"));
		
		
		JLabel label_5 = new JLabel("Port : ");
		config_panel.add(label_5, "cell 0 0,alignx right");
		
		port_textfield = new JTextField();
		port_textfield.setColumns(10);
		config_panel.add(port_textfield, "cell 1 0,growx");
		
		JLabel label_10 = new JLabel("\uD3EC\uD2B8 \uC8FC\uC18C \uC9C0\uC815 1234567890123456789");
		config_panel.add(label_10, "cell 2 0");
		
		JLabel label_6 = new JLabel("Bitrate : ");
		config_panel.add(label_6, "cell 0 1,alignx right");
		
		bitrate_textfield = new JTextField();
		bitrate_textfield.setColumns(10);
		config_panel.add(bitrate_textfield, "cell 1 1,growx");
		
		JLabel label_15 = new JLabel("New label");
		config_panel.add(label_15, "flowx,cell 2 1");
		
		JLabel label = new JLabel("Framerate : ");
		config_panel.add(label, "cell 0 2,alignx trailing");
		
		framerate_textfield = new JTextField();
		framerate_textfield.setColumns(10);
		config_panel.add(framerate_textfield, "cell 1 2,growx");
		
		JLabel label_12 = new JLabel("New label");
		config_panel.add(label_12, "cell 2 2");
		
		JLabel label_7 = new JLabel("Window size : ");
		config_panel.add(label_7, "cell 0 3,alignx right");
		
		windowsize_textfield = new JTextField();
		windowsize_textfield.setColumns(10);
		config_panel.add(windowsize_textfield, "cell 1 3,growx");
		
		JLabel label_13 = new JLabel("New label");
		config_panel.add(label_13, "cell 2 3");
		
		JLabel label_8 = new JLabel("remote : ");
		config_panel.add(label_8, "cell 0 4,alignx right");
		
		remote_checkBox = new JCheckBox("enable");
		config_panel.add(remote_checkBox, "cell 1 4");
		
		JLabel label_14 = new JLabel("New label");
		config_panel.add(label_14, "cell 2 4");
		
		JLabel label_9 = new JLabel("crop : ");
		config_panel.add(label_9, "cell 0 5,alignx right");
		
		crop_textfield = new JTextField();
		crop_textfield.setColumns(10);
		config_panel.add(crop_textfield, "cell 1 5,growx");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, "cell 0 5 3 3,grow");
		
		textPane = new JTextArea();
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
		//고급설정창이 띄워져있을때
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
		//고급설정 안하면 vnc에서 알아서 default 값 적용.
		
		mList.add(arg);
		
		

		
		return mList.toArray(new String[mList.size()]);
	}
	

	//cmd : 파일명 [옵션] desktop
	
	private void execute() {
		//start.bat 실행
		//Thread 통해 실행
		if(isExec){
			System.out.println("이미 실행중입니다.");
			textPane.append("오류 : 이미 실행중입니다.\n");
			return;
		}
		isExec = true;
		String cmd[] = option();
		Access_vnc start_thread  = new Access_vnc(cmd);
		start_thread.start();
		
		//오류메시지
		
	}
	
	private void cancel(){
		//taskkill.bat 실행
		if(!isExec){
			System.out.println("실행 중이 아닙니다.");
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
					textPane.append("오류 : jsmpeg-vnc.exe 파일이 없습니다.\n");
				}
				if(line.contains("exist")){
					textPane.append("실행 : jsmpeg-vnc.exe\n");
				}
				if(line.contains("성공")){
					textPane.append("종료 : jsmpeg-vnc.exe\n");
				}
			}
			}catch (Exception e) {
				
			}
				super.run();
			}

		
	}




}
