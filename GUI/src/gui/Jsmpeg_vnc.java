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
import tool.GetmyIP;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
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

	private JFrame frame;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private boolean config_veiw = false;
	private JPanel config_panel;

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
		frame.setBounds(100, 100, 636, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		panel.setLayout(new MigLayout("", "[100.00,grow][10px:n:100px,grow]30[200px,left][][87.00]", "[grow][][][10px:n:150px][][][][grow][grow]"));
		
		GetmyIP getip = new GetmyIP();
		String myaddress = getip.myIP;
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "cell 0 1 3 1,grow");
		panel_2.setLayout(new MigLayout("", "[100.00,grow][10px:n:100px,grow]30[200px,left]", "[]"));
		
		JLabel label_1 = new JLabel("IP : ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_1, "cell 0 0,alignx right");
		
		JLabel label_2 = new JLabel("220.149.11.227");
		panel_2.add(label_2, "cell 1 0");
		
		JLabel label_3 = new JLabel("\uB0B4 IP\uC8FC\uC18C");
		panel_2.add(label_3, "cell 2 0");
		
		JButton btnNewButton = new JButton("실행");
		panel.add(btnNewButton, "cell 4 1,growx");
		
		JButton btnNewButton_1 = new JButton("고급 설정");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				config_veiw = !config_veiw;
				config_panel.setVisible(config_veiw);
			}
		});
		panel.add(btnNewButton_1, "cell 0 2,alignx right");
		
		JButton button = new JButton("종료");
		panel.add(button, "cell 4 2,growx");
		
		config_panel = new JPanel();
		panel.add(config_panel, "cell 0 3 3 1,grow");
		config_panel.setLayout(new MigLayout("", "[100.00,grow][10px:n:100px,grow]30[200px,left]", "[][][][][][][][grow][grow]"));
		
		JLabel label_5 = new JLabel("Port : ");
		config_panel.add(label_5, "cell 0 0,alignx right");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		config_panel.add(textField_4, "cell 1 0,growx");
		
		JLabel label_10 = new JLabel("\uD3EC\uD2B8 \uC8FC\uC18C \uC9C0\uC815 1234567890123456789");
		config_panel.add(label_10, "cell 2 0");
		
		JLabel label_6 = new JLabel("Bitrate : ");
		config_panel.add(label_6, "cell 0 1,alignx right");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		config_panel.add(textField_5, "cell 1 1,growx");
		
		JLabel label_15 = new JLabel("New label");
		config_panel.add(label_15, "flowx,cell 2 1");
		
		JLabel label_7 = new JLabel("Window size : ");
		config_panel.add(label_7, "cell 0 2,alignx right");
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		config_panel.add(textField_6, "cell 1 2,growx");
		
		JLabel label_12 = new JLabel("New label");
		config_panel.add(label_12, "cell 2 2");
		
		JLabel label_8 = new JLabel("remote : ");
		config_panel.add(label_8, "cell 0 3,alignx right");
		
		JCheckBox checkBox = new JCheckBox("enable");
		config_panel.add(checkBox, "cell 1 3");
		
		JLabel label_13 = new JLabel("New label");
		config_panel.add(label_13, "cell 2 3");
		
		JLabel label_9 = new JLabel("crop : ");
		config_panel.add(label_9, "cell 0 4,alignx right");
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		config_panel.add(textField_7, "cell 1 4,growx");
		
		JLabel label_14 = new JLabel("New label");
		config_panel.add(label_14, "cell 2 4");
		
		JTextPane textPane = new JTextPane();
		panel.add(textPane, "cell 0 5 5 4,grow");
	}
	
	

}
