package classes;

import java.awt.EventQueue;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import javax.swing.JViewport;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Cursor;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class EmployeeInterface extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String SERVER_IP = "IP_ADDRESS_HERE";
	private static final int SERVER_PORT = 5000;
	
	private JPanel contentPane;
	private JTextField txtID;
	private JPasswordField txtPassword;
	private JTextField txtName;
	private Timer timer;
	private SimpleDateFormat timeFormat;
	private JTextField txtDepartment;
	private JTextField txtPosition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmployeeInterface frame = new EmployeeInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EmployeeInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(82, 78, 183));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JTable table = new JTable();

		JScrollPane pane = new JScrollPane(table);
		pane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pane.setFont(new Font("Roboto", Font.PLAIN, 44));
		pane.setFocusable(false);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		pane.setBounds(10, 236, 1264, 408);
		getContentPane().add(pane);

		JViewport viewport = (JViewport) table.getParent();
		viewport.setBackground(Color.WHITE);
		pane.setViewport(viewport);
		Object[] columns = { "Date", "Hours Worked", "Daily Rate" };

		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		table.setBackground(Color.WHITE);
		table.setForeground(Color.BLACK);
		table.setGridColor(Color.RED);
		table.setSelectionForeground(Color.WHITE);
		table.setSelectionBackground(new Color(137, 196, 244));
		table.setFont(new Font("Roboto", Font.PLAIN, 17));
		table.setFocusable(false);
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setFont(new Font("Roboto", Font.PLAIN, 17));
		panel.setBorder(null);
		panel.setBackground(new Color(82, 78, 183));
		panel.setBounds(0, 0, 1284, 211);
		contentPane.add(panel);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setBackground(Color.PINK);
		dateChooser.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateChooser.setFocusable(false);
		dateChooser.setFont(new Font("Roboto", Font.PLAIN, 17));
		dateChooser.setBorder(null);
		dateChooser.setBackground(Color.WHITE);
		dateChooser.setBounds(513, 11, 221, 29);
		panel.add(dateChooser);

		JLabel lblDateWorked = new JLabel("Date:");
		lblDateWorked.setForeground(Color.WHITE);
		lblDateWorked.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblDateWorked.setBackground(Color.WHITE);
		lblDateWorked.setBounds(377, 11, 126, 29);
		panel.add(lblDateWorked);

		JLabel lblHoursWorked = new JLabel("Hours Worked:");
		lblHoursWorked.setForeground(Color.WHITE);
		lblHoursWorked.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblHoursWorked.setBackground(Color.WHITE);
		lblHoursWorked.setBounds(377, 51, 126, 29);
		panel.add(lblHoursWorked);

		JComboBox cbHoursWorked = new JComboBox();
		cbHoursWorked.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbHoursWorked.setFocusable(false);
		cbHoursWorked.setModel(new DefaultComboBoxModel(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
				15, 16, 17, 18, 19, 20, 21, 22, 23, 24 }));
		cbHoursWorked.setSelectedIndex(-1);
		cbHoursWorked.setFont(new Font("Roboto", Font.PLAIN, 17));
		cbHoursWorked.setBorder(null);
		cbHoursWorked.setBackground(Color.WHITE);
		cbHoursWorked.setBounds(513, 51, 221, 29);
		panel.add(cbHoursWorked);

		JButton btnLogOut = new JButton("Logout");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnLogOut.setBounds(1107, 160, 167, 41);
		btnLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogOut.setFocusable(false);
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogOut.setBackground(new Color(8, 14, 44));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLogOut.setBackground(new Color(159, 90, 253));
			}
		});
		btnLogOut.setForeground(new Color(228, 241, 254));
		btnLogOut.setBackground(new Color(159, 90, 253));
		btnLogOut.setFont(new Font("Roboto", Font.BOLD, 17));
		panel.add(btnLogOut);

		JLabel lblID = new JLabel("ID:");
		lblID.setForeground(Color.WHITE);
		lblID.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblID.setBackground(Color.WHITE);
		lblID.setBounds(10, 11, 128, 29);
		panel.add(lblID);

		txtID = new JTextField();
		txtID.setFocusable(false);
		txtID.setBackground(Color.LIGHT_GRAY);
		txtID.setDisabledTextColor(Color.WHITE);
		txtID.setFont(new Font("Roboto", Font.PLAIN, 17));
		txtID.setEditable(false);
		txtID.setBounds(146, 11, 221, 29);
		txtID.setColumns(10);
		panel.add(txtID);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblPassword.setBackground(Color.WHITE);
		lblPassword.setBounds(10, 51, 128, 29);
		panel.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setFocusable(false);
		txtPassword.setEditable(false);
		txtPassword.setBackground(Color.LIGHT_GRAY);
		txtPassword.setDisabledTextColor(Color.WHITE);
		txtPassword.setFont(new Font("Roboto", Font.PLAIN, 17));
		txtPassword.setColumns(10);
		txtPassword.setBounds(146, 51, 221, 29);
		txtPassword.setEchoChar('•');
		panel.add(txtPassword);

		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblName.setBackground(Color.WHITE);
		lblName.setBounds(10, 91, 128, 29);
		panel.add(lblName);

		txtName = new JTextField();
		txtName.setFocusable(false);
		txtName.setBackground(Color.LIGHT_GRAY);
		txtName.setDisabledTextColor(Color.WHITE);
		txtName.setFont(new Font("Roboto", Font.PLAIN, 17));
		txtName.setEditable(false);
		txtName.setColumns(10);
		txtName.setBounds(146, 91, 221, 29);
		panel.add(txtName);

		txtDepartment = new JTextField();
		txtDepartment.setFocusable(false);
		txtDepartment.setEditable(false);
		txtDepartment.setFont(new Font("Roboto", Font.PLAIN, 17));
		txtDepartment.setColumns(10);
		txtDepartment.setBackground(Color.LIGHT_GRAY);
		txtDepartment.setBounds(146, 131, 221, 29);
		panel.add(txtDepartment);

		txtPosition = new JTextField();
		txtPosition.setFocusable(false);
		txtPosition.setEditable(false);
		txtPosition.setFont(new Font("Roboto", Font.PLAIN, 17));
		txtPosition.setColumns(10);
		txtPosition.setBackground(Color.LIGHT_GRAY);
		txtPosition.setBounds(146, 171, 221, 29);
		panel.add(txtPosition);

		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date selectedDate = dateChooser.getDate();

				if (selectedDate == null || cbHoursWorked.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Please fill in the blanks");
				} else {
					SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
					String formattedDate = dateFormat.format(selectedDate);
					try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
						PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

						writer.println("SUBMIT");
						writer.println(formattedDate);
						writer.println(cbHoursWorked.getSelectedItem());
						writer.println(dailyRateDeduction((int) cbHoursWorked.getSelectedItem()));

						Object[] row = new Object[3];
						row[0] = formattedDate;
						row[1] = cbHoursWorked.getSelectedItem();
						row[2] = "\u20B1" + dailyRateDeduction((int) cbHoursWorked.getSelectedItem());
						model.addRow(row);

						JOptionPane.showMessageDialog(null, "Record created successfully.");
						writer.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					cbHoursWorked.setSelectedIndex(-1);
				}
			}
		});
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setFont(new Font("Roboto", Font.PLAIN, 17));
		btnSubmit.setFocusable(false);
		btnSubmit.setBackground(new Color(22, 160, 133));
		btnSubmit.setBounds(377, 108, 167, 41);
		panel.add(btnSubmit);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setBounds(567, 131, 167, 41);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowTable = table.getSelectedRow();

				if (selectedRowTable >= 0) {
					int choice = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Confirmation",
							JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
							PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

							writer.println("DELETE_EMPLOYEE_WORKED_RECORD");
							writer.println(selectedRowTable);

							model.removeRow(selectedRowTable);
							JOptionPane.showMessageDialog(null, "Deleted successfully");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Select a row first");
				}
			}
		});
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Roboto", Font.PLAIN, 17));
		btnDelete.setFocusable(false);
		btnDelete.setBackground(new Color(255, 76, 48));
		panel.add(btnDelete);

		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setForeground(Color.WHITE);
		lblDepartment.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblDepartment.setBackground(Color.WHITE);
		lblDepartment.setBounds(10, 131, 126, 29);
		panel.add(lblDepartment);

		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setForeground(Color.WHITE);
		lblPosition.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblPosition.setBackground(Color.WHITE);
		lblPosition.setBounds(10, 171, 126, 29);
		panel.add(lblPosition);

		JButton btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Print button functionality
				MessageFormat header = new MessageFormat("Payroll Management System");
				MessageFormat footer = new MessageFormat("Page {0, number, integer}");

				try {
					table.print(JTable.PrintMode.NORMAL, header, footer);
				} catch (java.awt.print.PrinterException ex) {
					System.err.format("No Printer Found", ex.getMessage());
				}
			}
		});
		btnPrint.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPrint.setForeground(Color.WHITE);
		btnPrint.setFont(new Font("Roboto", Font.PLAIN, 17));
		btnPrint.setFocusable(false);
		btnPrint.setBackground(Color.BLACK);
		btnPrint.setBounds(377, 160, 167, 41);
		panel.add(btnPrint);

		JLabel lblTime = new JLabel("TT:TT:TT_TT");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Roboto", Font.PLAIN, 84));
		lblTime.setBounds(783, 19, 491, 113);
		time(lblTime);
		panel.add(lblTime);

		JLabel lblDate = new JLabel();
		lblDate.setText("MAY 15, 2023 |");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Roboto", Font.PLAIN, 30));
		lblDate.setBackground(Color.WHITE);
		lblDate.setBounds(793, 131, 202, 33);
		AdminInterface admin = new AdminInterface();
		lblDate.setText(admin.date());
		panel.add(lblDate);

		JCheckBox chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxShowPassword.setBorder(null);
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checkbox to show password functionality
				char echoChar = chckbxShowPassword.isSelected() ? '\0' : '•';
				txtPassword.setEchoChar(echoChar);
			}
		});
		chckbxShowPassword.setFocusable(false);
		chckbxShowPassword.setForeground(new Color(228, 241, 254));
		chckbxShowPassword.setFont(new Font("Roboto", Font.PLAIN, 17));
		chckbxShowPassword.setBackground(new Color(72, 113, 247));
		chckbxShowPassword.setBounds(377, 80, 357, 22);
		panel.add(chckbxShowPassword);



		JPanel panel_EmployeeTableTitle = new JPanel();
		panel_EmployeeTableTitle.setFont(new Font("Roboto", Font.PLAIN, 17));
		panel_EmployeeTableTitle.setBackground(Color.DARK_GRAY);
		panel_EmployeeTableTitle.setBounds(10, 210, 1264, 29);
		contentPane.add(panel_EmployeeTableTitle);

		JLabel lblWorkHistory = new JLabel("Your Work History");
		lblWorkHistory.setBackground(Color.WHITE);
		lblWorkHistory.setForeground(Color.WHITE);
		lblWorkHistory.setFont(new Font("Roboto", Font.PLAIN, 17));
		panel_EmployeeTableTitle.add(lblWorkHistory);
		
		loadEmployeeTable(model);
	}

	public void time(JLabel lblTime) {
		timeFormat = new SimpleDateFormat("hh:mm:ss a");
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date now = new Date();
				String timeStr = timeFormat.format(now);
				lblTime.setText(timeStr);
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}

	public String dailyRateDeduction(double hoursWorked) {
		int hourlyRate = 0;
		String department = txtDepartment.getText();
		String pos = txtPosition.getText();

		if (department.equals("Software Development")) {
			if (pos.equals("Technical writer") || pos.equals("QA engineer")) {
				hourlyRate = 80;
			} else if (pos.equals("Front-end developer") || pos.equals("Back-end developer")) {
				hourlyRate = 100;
			} else if (pos.equals("Database administrator") || pos.equals("Full-stack developer")) {
				hourlyRate = 120;
			} else if (pos.equals("DevOps engineer") || pos.equals("Security specialist")) {
				hourlyRate = 140;
			} else {
				hourlyRate = 160; // Security specialist
			}
		} else { // Web Development
			if (pos.equals("Technical writer") || pos.equals("Content strategist")) {
				hourlyRate = 70;
			} else if (pos.equals("UX/UI designer") || pos.equals("QA engineer")) {
				hourlyRate = 90;
			} else if (pos.equals("SEO specialist")) {
				hourlyRate = 110;
			} else if (pos.equals("Front-end developer") || pos.equals("Back-end developer")) {
				hourlyRate = 130;
			} else if (pos.equals("Full-stack developer") || pos.equals("Analytics specialist")) {
				hourlyRate = 150;
			} else {
				hourlyRate = 170; // DevOps engineer & Digital marketer
			}
		}
		double grossIncome = hoursWorked * hourlyRate;
		double taxRate = 0.1;

		double incomeTax = grossIncome * taxRate;
		double netIncome = grossIncome - incomeTax;
		String output = String.format("%.2f", netIncome);
		return output;
	}
	
	public void loadEmployeeTable(DefaultTableModel model) {
		try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

			writer.println("EMPLOYEE_TABLE");
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split("// ");
				Object[] row = new Object[3];

				txtID.setText(fields[0]);
				txtPassword.setText(fields[1]);
				txtName.setText(fields[2]);
				txtDepartment.setText(fields[3]);
				txtPosition.setText(fields[4]);

				row[0] = fields[5]; // Date
				row[1] = fields[6]; // Hours Worked
				row[2] = fields[7]; // Daily Rate

				String regex = "\\d+";
				String text = fields[7];

				Pattern pt = Pattern.compile(regex);
				Matcher mt = pt.matcher(text);
				boolean result = mt.find();
				if (result) {
					row[2] = "\u20B1" + fields[7]; // Daily Rate
				} else {
					row[2] = fields[7]; // new
				}
				model.addRow(row);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}