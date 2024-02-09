package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.EventQueue;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JViewport;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

public class AdminInterface extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private Timer timer;
	private SimpleDateFormat timeFormat;
	private JTextField txtID;
	private JTextField txtPassword;
	private JTextField txtName;
	int clickedCount = 0;

	private static final String SERVER_IP = "IP_ADDRESS_HERE";
	private static final int SERVER_PORT = 5000;

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
					AdminInterface frame = new AdminInterface();
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
	public AdminInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(132, 140, 207));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblAdmin = new JLabel("ADMIN");
		lblAdmin.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdmin.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAdmin.setBackground(Color.WHITE);
		lblAdmin.setForeground(Color.WHITE);
		lblAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAdmin.setForeground(Color.YELLOW);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblAdmin.setForeground(Color.WHITE);
			}
		});
		lblAdmin.setFont(new Font("Roboto", Font.BOLD, 30));
		lblAdmin.setBounds(135, 31, 156, 33);
		contentPane.add(lblAdmin);

		JLabel lblProfilePicture = new JLabel();
		lblProfilePicture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickedCount++;
				if (clickedCount == 1) {
					Image img = new ImageIcon(this.getClass().getResource("/profile_pictures/ey_avila.png")).getImage()
							.getScaledInstance(110, 100, Image.SCALE_SMOOTH);
					lblProfilePicture.setIcon(new ImageIcon(img));
				} else if ((clickedCount == 2)) {
					Image img = new ImageIcon(this.getClass().getResource("/profile_pictures/grumpy_avila.png"))
							.getImage().getScaledInstance(110, 100, Image.SCALE_SMOOTH);
					lblProfilePicture.setIcon(new ImageIcon(img));
				} else if ((clickedCount == 3)) {
					Image img = new ImageIcon(this.getClass().getResource("/profile_pictures/meditating_avila.png"))
							.getImage().getScaledInstance(110, 100, Image.SCALE_SMOOTH);
					lblProfilePicture.setIcon(new ImageIcon(img));
				} else if ((clickedCount == 4)) {
					Image img = new ImageIcon(this.getClass().getResource("/profile_pictures/ok_avila.png")).getImage()
							.getScaledInstance(110, 100, Image.SCALE_SMOOTH);
					lblProfilePicture.setIcon(new ImageIcon(img));
				} else if ((clickedCount == 5)) {
					Image img = new ImageIcon(this.getClass().getResource("/profile_pictures/serious_avila.png"))
							.getImage().getScaledInstance(110, 100, Image.SCALE_SMOOTH);
					lblProfilePicture.setIcon(new ImageIcon(img));
				} else if ((clickedCount == 6)) {
					Image img = new ImageIcon(this.getClass().getResource("/profile_pictures/sliding_avila.png"))
							.getImage().getScaledInstance(110, 100, Image.SCALE_SMOOTH);
					lblProfilePicture.setIcon(new ImageIcon(img));
				} else if ((clickedCount == 7)) {
					Image img = new ImageIcon(this.getClass().getResource("/profile_pictures/stolen_avila.png"))
							.getImage().getScaledInstance(110, 100, Image.SCALE_SMOOTH);
					lblProfilePicture.setIcon(new ImageIcon(img));
				} else if ((clickedCount == 8)) {
					Image img = new ImageIcon(this.getClass().getResource("/profile_pictures/tatay_avila.png"))
							.getImage().getScaledInstance(110, 100, Image.SCALE_SMOOTH);
					lblProfilePicture.setIcon(new ImageIcon(img));
				} else if ((clickedCount == 9)) {
					Image img = new ImageIcon(
							this.getClass().getResource("/profile_pictures/you_are_my_everything_avila.png")).getImage()
							.getScaledInstance(110, 100, Image.SCALE_SMOOTH);
					lblProfilePicture.setIcon(new ImageIcon(img));
				} else if ((clickedCount == 10)) {
					Image img = new ImageIcon(this.getClass().getResource("/profile_pictures/admin_icon.png"))
							.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
					lblProfilePicture.setIcon(new ImageIcon(img));
				} else {
					clickedCount = 0;
				}
			}
		});
		lblProfilePicture.setForeground(Color.WHITE);
		lblProfilePicture.setBounds(10, 11, 114, 105);
		Image img = new ImageIcon(this.getClass().getResource("/profile_pictures/admin_icon.png")).getImage()
				.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		lblProfilePicture.setIcon(new ImageIcon(img));
		lblProfilePicture.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblProfilePicture);

		JButton btnLogOut = new JButton("Logout");
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
		btnLogOut.setBounds(135, 75, 156, 41);
		btnLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogOut.setFocusable(false);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnLogOut.setForeground(new Color(228, 241, 254));
		btnLogOut.setBackground(new Color(159, 90, 253));
		btnLogOut.setFont(new Font("Roboto", Font.BOLD, 17));
		contentPane.add(btnLogOut);

		JLabel lblTime = new JLabel("TT:TT:TT_TT");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Roboto", Font.PLAIN, 30));
		lblTime.setBounds(1102, 27, 172, 41);
		time(lblTime);
		contentPane.add(lblTime);

		JLabel lblDate = new JLabel();
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBackground(Color.WHITE);
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Roboto", Font.PLAIN, 30));
		lblDate.setBounds(878, 31, 214, 33);
		lblDate.setText(date() + " | ");
		contentPane.add(lblDate);

		txtID = new JTextField();
		txtID.setFont(new Font("Roboto", Font.PLAIN, 17));
		txtID.setColumns(10);
		txtID.setBackground(Color.WHITE);
		txtID.setBounds(146, 364, 221, 29);
		contentPane.add(txtID);

		JLabel lblID = new JLabel("ID:");
		lblID.setForeground(Color.WHITE);
		lblID.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblID.setBackground(Color.WHITE);
		lblID.setBounds(10, 364, 126, 29);
		contentPane.add(lblID);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblPassword.setBackground(Color.WHITE);
		lblPassword.setBounds(10, 404, 126, 29);
		contentPane.add(lblPassword);

		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Roboto", Font.PLAIN, 17));
		txtPassword.setColumns(10);
		txtPassword.setBackground(Color.WHITE);
		txtPassword.setBounds(146, 404, 221, 29);
		contentPane.add(txtPassword);

		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblName.setBackground(Color.WHITE);
		lblName.setBounds(10, 444, 126, 29);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("Roboto", Font.PLAIN, 17));
		txtName.setColumns(10);
		txtName.setBackground(Color.WHITE);
		txtName.setBounds(146, 444, 221, 29);
		contentPane.add(txtName);

		JComboBox cbPosition = new JComboBox();
		cbPosition.setEnabled(false);
		cbPosition.setSelectedIndex(-1);
		cbPosition.setFont(new Font("Roboto", Font.PLAIN, 17));
		cbPosition.setFocusable(false);
		cbPosition.setBorder(null);
		cbPosition.setBackground(Color.WHITE);
		cbPosition.setBounds(146, 524, 221, 29);
		contentPane.add(cbPosition);

		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setForeground(Color.WHITE);
		lblDepartment.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblDepartment.setBackground(Color.WHITE);
		lblDepartment.setBounds(10, 484, 126, 29);
		contentPane.add(lblDepartment);

		JComboBox cbDepartment = new JComboBox();
		cbDepartment.setModel(new DefaultComboBoxModel(new String[] { "Software Development", "Web Development" }));
		cbDepartment.setSelectedIndex(-1);
		cbDepartment.setFont(new Font("Roboto", Font.PLAIN, 17));
		cbDepartment.setFocusable(false);
		cbDepartment.setBorder(null);
		cbDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbPosition.setEnabled(true);
				Object selectedDepartment = cbDepartment.getSelectedItem();
				if (selectedDepartment != null && selectedDepartment.equals("Software Development")) {
					cbPosition.setModel(new DefaultComboBoxModel(new String[] { "Front-end developer",
							"Back-end developer", "Full-stack developer", "QA engineer", "DevOps engineer",
							"Technical writer", "Database administrator", "Data scientist", "Security specialist" }));
				} else {
					cbPosition.setModel(new DefaultComboBoxModel(new String[] { "Front-end developer",
							"Back-end developer", "Full-stack developer", "UX/UI designer", "QA engineer",
							"DevOps engineer", "Technical writer", "SEO specialist", "Content strategist",
							"Digital marketer", "Analytics specialist" }));
				}
			}
		});
		cbPosition.setSelectedIndex(-1);
		cbDepartment.setBackground(Color.WHITE);
		cbDepartment.setBounds(146, 484, 221, 29);
		contentPane.add(cbDepartment);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setBackground(Color.PINK);
		dateChooser.setFont(new Font("Roboto", Font.PLAIN, 17));
		dateChooser.setFocusable(false);
		dateChooser.setBorder(null);
		dateChooser.setBackground(Color.WHITE);
		dateChooser.setBounds(146, 564, 221, 29);
		contentPane.add(dateChooser);

		JTable blue_table = new JTable();

		JScrollPane pane_1 = new JScrollPane(blue_table);
		pane_1.setViewportBorder(null);
		pane_1.setFocusable(false);
		pane_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pane_1.setBorder(null);
		pane_1.setFont(new Font("Roboto", Font.PLAIN, 19));
		pane_1.setForeground(Color.BLACK);
		pane_1.setBackground(Color.LIGHT_GRAY);
		pane_1.setBounds(377, 155, 897, 463);
		getContentPane().add(pane_1);

		JViewport viewport_1 = (JViewport) blue_table.getParent();
		viewport_1.setBackground(Color.WHITE);
		pane_1.setViewport(viewport_1);
		// Here we initialize the columns_1 with 8 arrays for table columns
		Object[] columns_1 = { "ID", "Password", "Name", "Department", "Position", "Date", "Hours Worked",
				"Daily Rate" };
		DefaultTableModel model_1 = new DefaultTableModel();
		model_1.setColumnIdentifiers(columns_1);
		blue_table.setModel(model_1);

		blue_table.setBackground(Color.WHITE);
		blue_table.setForeground(Color.BLACK);
		blue_table.setGridColor(Color.YELLOW);
		blue_table.setSelectionBackground(new Color(30, 139, 195));
		blue_table.setFont(new Font("Roboto", Font.PLAIN, 17));
		blue_table.setRowHeight(30);
		blue_table.setFocusable(false);
		blue_table.setAutoCreateRowSorter(true);

		blue_table.getColumnModel().getColumn(0).setPreferredWidth(0); // ID
		blue_table.getColumnModel().getColumn(1).setPreferredWidth(0); // Password
		blue_table.getColumnModel().getColumn(2).setPreferredWidth(100); // Name
		blue_table.getColumnModel().getColumn(3).setPreferredWidth(70); // Department
		blue_table.getColumnModel().getColumn(4).setPreferredWidth(90); // Position
		blue_table.getColumnModel().getColumn(5).setPreferredWidth(90); // Date
		blue_table.getColumnModel().getColumn(6).setPreferredWidth(0); // Hours Worked
		blue_table.getColumnModel().getColumn(7).setPreferredWidth(40); // Daily Rate

		JButton btnDeleteBlueTableRow = new JButton("DELETE");
		btnDeleteBlueTableRow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeleteBlueTableRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = blue_table.getSelectedRow();

				if (selectedRow >= 0) {
					int choice = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Confirmation",
							JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
							PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

							writer.println("DELETE_BLUE_TABLE_ROW");
							writer.println(selectedRow);

							model_1.removeRow(selectedRow);
							JOptionPane.showMessageDialog(null, "Deleted Successfully");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Select a row first");
				}
			}
		});
		btnDeleteBlueTableRow.setForeground(Color.WHITE);
		btnDeleteBlueTableRow.setFont(new Font("Roboto", Font.PLAIN, 17));
		btnDeleteBlueTableRow.setFocusable(false);
		btnDeleteBlueTableRow.setBackground(new Color(30, 139, 195));
		btnDeleteBlueTableRow.setBounds(1118, 75, 156, 41);
		contentPane.add(btnDeleteBlueTableRow);

		JTable orange_table = new JTable();

		JScrollPane pane_2 = new JScrollPane(orange_table);
		pane_2.setViewportBorder(null);
		pane_2.setFocusable(false);
		pane_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pane_2.setBorder(null);
		pane_2.setFont(new Font("Roboto", Font.PLAIN, 19));
		pane_2.setForeground(Color.BLACK);
		pane_2.setBackground(Color.LIGHT_GRAY);
		pane_2.setBounds(10, 155, 357, 187);
		getContentPane().add(pane_2);

		JViewport viewport_2 = (JViewport) orange_table.getParent();
		viewport_2.setBackground(Color.WHITE);
		pane_2.setViewport(viewport_2);
		Object[] columns_2 = { "ID", "Password", "Name" };

		DefaultTableModel model_2 = new DefaultTableModel();
		model_2.setColumnIdentifiers(columns_2);
		orange_table.setModel(model_2);

		orange_table.setBackground(Color.WHITE);
		orange_table.setForeground(Color.BLACK);
		orange_table.setGridColor(Color.YELLOW);
		orange_table.setSelectionBackground(new Color(255, 76, 48));
		orange_table.setFont(new Font("Roboto", Font.PLAIN, 17));
		orange_table.setRowHeight(30);
		orange_table.setFocusable(false);
		orange_table.setAutoCreateRowSorter(true);

		orange_table.getColumnModel().getColumn(0).setPreferredWidth(0); // ID
		orange_table.getColumnModel().getColumn(1).setPreferredWidth(0); // Password
		orange_table.getColumnModel().getColumn(2).setPreferredWidth(60); // Name

		JButton btnDeleteOrangeTableRow = new JButton("DELETE");
		btnDeleteOrangeTableRow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeleteOrangeTableRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowOrangeTable = orange_table.getSelectedRow();

				if (selectedRowOrangeTable >= 0) {
					int choice = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Confirmation",
							JOptionPane.YES_NO_OPTION);
					// YES == 0
					// NO == 1
					if (choice == JOptionPane.YES_OPTION) {
						try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
							PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

							writer.println("DELETE_ORANGE_TABLE_ROW");
							writer.println(selectedRowOrangeTable);

							model_2.removeRow(selectedRowOrangeTable);
							JOptionPane.showMessageDialog(null, "Deleted Successfully");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Select a row first");
				}
			}
		});
		btnDeleteOrangeTableRow.setForeground(Color.WHITE);
		btnDeleteOrangeTableRow.setFont(new Font("Roboto", Font.PLAIN, 17));
		btnDeleteOrangeTableRow.setFocusable(false);
		btnDeleteOrangeTableRow.setBackground(new Color(255, 76, 48));
		btnDeleteOrangeTableRow.setBounds(257, 610, 110, 41);
		contentPane.add(btnDeleteOrangeTableRow);

		JPanel panel_BlueTableTitle = new JPanel();
		panel_BlueTableTitle.setBackground(new Color(30, 139, 195));
		panel_BlueTableTitle.setBounds(377, 127, 897, 29);
		contentPane.add(panel_BlueTableTitle);

		JLabel lblBlueTableTitle = new JLabel("Employees Worked Records");
		lblBlueTableTitle.setForeground(Color.WHITE);
		lblBlueTableTitle.setFont(new Font("Roboto", Font.PLAIN, 17));
		panel_BlueTableTitle.add(lblBlueTableTitle);

		JPanel panel_orangeTableTitle = new JPanel();
		panel_orangeTableTitle.setFont(new Font("Roboto", Font.PLAIN, 17));
		panel_orangeTableTitle.setBackground(new Color(255, 76, 48));
		panel_orangeTableTitle.setBounds(10, 127, 357, 29);
		contentPane.add(panel_orangeTableTitle);

		JLabel lblOrangeTableTitle = new JLabel("Employee List");
		lblOrangeTableTitle.setForeground(Color.WHITE);
		lblOrangeTableTitle.setFont(new Font("Roboto", Font.PLAIN, 17));
		panel_orangeTableTitle.add(lblOrangeTableTitle);

		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setForeground(Color.WHITE);
		lblPosition.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblPosition.setBackground(Color.WHITE);
		lblPosition.setBounds(10, 524, 126, 29);
		contentPane.add(lblPosition);

		JButton btnClear = new JButton("CLEAR");
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					txtID.setText("");
					txtPassword.setText("");
					txtName.setText("");
					cbDepartment.setSelectedIndex(-1);
					cbPosition.setSelectedIndex(-1);
					cbPosition.setEnabled(false);
				}
			}
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Roboto", Font.PLAIN, 17));
		btnClear.setFocusable(false);
		btnClear.setBackground(new Color(249, 180, 45));
		btnClear.setBounds(137, 610, 110, 41);
		contentPane.add(btnClear);

		JButton btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("Payroll Management System");
				MessageFormat footer = new MessageFormat("Page {0, number, integer}");

				try {
					blue_table.print(JTable.PrintMode.NORMAL, header, footer);
				} catch (java.awt.print.PrinterException ex) {
					System.err.format("No Printer Found", ex.getMessage());
				}
			}
		});
		btnPrint.setForeground(Color.WHITE);
		btnPrint.setFont(new Font("Roboto", Font.PLAIN, 17));
		btnPrint.setFocusable(false);
		btnPrint.setBackground(new Color(51, 110, 123));
		btnPrint.setBounds(878, 75, 167, 41);
		contentPane.add(btnPrint);

		JLabel lblDateWorked = new JLabel("Date:");
		lblDateWorked.setForeground(Color.WHITE);
		lblDateWorked.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblDateWorked.setBackground(Color.WHITE);
		lblDateWorked.setBounds(10, 564, 126, 29);
		contentPane.add(lblDateWorked);

		JButton btnCreate = new JButton("CREATE");
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date selectedDate = dateChooser.getDate();

				if (txtID.getText().equals("") || txtPassword.getText().equals("") || txtName.getText().equals("")
						|| cbDepartment.getSelectedItem() == null || cbPosition.getSelectedItem() == null
						|| selectedDate == null) {
					JOptionPane.showMessageDialog(null, "Please fill in the blanks");
				} else {
					try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
						String formattedDate = dateFormat.format(selectedDate);

						PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
						BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

						writer.println("CREATE");
						writer.println(txtID.getText());
						writer.println(txtPassword.getText());
						writer.println(txtName.getText());
						writer.println(cbDepartment.getSelectedItem().toString());
						writer.println(cbPosition.getSelectedItem().toString());
						writer.println(formattedDate);

						String response = reader.readLine();

						if ("SUCCESS".equals(response)) {
							Object[] row = new Object[8];
							row[0] = txtID.getText();
							row[1] = txtPassword.getText();
							row[2] = txtName.getText();
							row[3] = cbDepartment.getSelectedItem();
							row[4] = cbPosition.getSelectedItem();
							row[5] = formattedDate;
							row[6] = "new";
							row[7] = "new";
							model_1.addRow(row);

							Object[] row_1 = new Object[3];
							row_1[0] = txtID.getText();
							row_1[1] = txtPassword.getText();
							row_1[2] = txtName.getText();
							model_2.addRow(row);

							JOptionPane.showMessageDialog(null, "Record created successfully.");
						} else if ("ID_EXISTS".equals(response)) {
							JOptionPane.showMessageDialog(null, "ID already exists.");
						} else {
							JOptionPane.showMessageDialog(null, "Failed to create record.");
						}
						reader.close();
						writer.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					txtID.setText("");
					txtPassword.setText("");
					txtName.setText("");
					cbDepartment.setSelectedIndex(-1);
					cbPosition.setSelectedIndex(-1);
					cbPosition.setEnabled(false);
				}
			}
		});
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setFont(new Font("Roboto", Font.PLAIN, 17));
		btnCreate.setFocusable(false);
		btnCreate.setBackground(new Color(22, 160, 133));
		btnCreate.setBounds(10, 610, 110, 41);
		contentPane.add(btnCreate);

		loadBlueTable(model_1);
		loadOrangeTable(model_2);
	}

	public String date() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		String dateOutput = sdf.format(date).toUpperCase();

		return dateOutput;
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

	public void loadBlueTable(DefaultTableModel model) {
		try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

			writer.println("BLUE_TABLE");
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split("// ");
				Object[] row = new Object[8];
				row[0] = fields[0]; // ID
				row[1] = fields[1]; // PASSWORD
				row[2] = fields[2]; // NAME
				row[3] = fields[3]; // DEPARTMENT
				row[4] = fields[4]; // POSITION
				row[5] = fields[5]; // DATE
				row[6] = fields[6]; // HOURS WORKED

				String regex = "\\d+";
				String text = fields[7];

				Pattern pt = Pattern.compile(regex);
				Matcher mt = pt.matcher(text);
				boolean result = mt.find();

				if (result) {
					row[7] = "\u20B1" + fields[7]; // DAILY RATE
				} else {
					row[7] = fields[7]; // new
				}
				model.addRow(row);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void loadOrangeTable(DefaultTableModel model) {
		try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

			writer.println("ORANGE_TABLE");
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split("// ");
				Object[] row = new Object[3];
				row[0] = fields[0]; // ID
				row[1] = fields[1]; // PASSWORD
				row[2] = fields[2]; // NAME
				model.addRow(row);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}