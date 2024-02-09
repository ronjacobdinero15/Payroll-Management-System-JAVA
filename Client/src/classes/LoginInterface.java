package classes;

import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.io.BufferedReader;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginInterface extends JFrame {
	private static final long serialVersionUID = 1L;	
    private static final String SERVER_IP = "IP_ADDRESS_HERE";
    private static final int SERVER_PORT = 5000;
    
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	
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
					LoginInterface frame = new LoginInterface();
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
	public LoginInterface() {
		setBackground(new Color(120, 166, 200));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(179, 204, 223));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblPicture = new JLabel("");
		lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
		Image img_1 = new ImageIcon(this.getClass().getResource("/payroll_management_system_1.png")).getImage()
				.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
		lblPicture.setIcon(new ImageIcon(img_1));
		lblPicture.setBackground(new Color(179, 204, 223));
		lblPicture.setBounds(0, 42, 483, 454);
		contentPane.add(lblPicture);

		JPanel panelLogin = new JPanel();
		panelLogin.setBorder(null);
		panelLogin.setBackground(new Color(72, 113, 247));
		panelLogin.setBounds(484, 0, 380, 561);
		panelLogin.setLayout(null);
		contentPane.add(panelLogin);

		JLabel lblLogin = new JLabel("PMS LOGIN");
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblLogin.setForeground(new Color(194, 249, 112));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblLogin.setForeground(Color.WHITE);
			}
		});
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Roboto", Font.BOLD, 50));
		lblLogin.setForeground(new Color(228, 241, 254));
		lblLogin.setBounds(55, 45, 288, 66);
		panelLogin.add(lblLogin);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(228, 241, 254));
		lblUsername.setFont(new Font("Roboto", Font.PLAIN, 17));
		lblUsername.setBounds(55, 161, 81, 13);
		panelLogin.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(228, 241, 254));
		lblPassword.setFont(new Font("Roboto", Font.PLAIN, 17));
		lblPassword.setBounds(55, 266, 81, 21);
		panelLogin.add(lblPassword);

		JLabel lblLogInAs = new JLabel("LOG IN AS:");
		lblLogInAs.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogInAs.setForeground(new Color(228, 241, 254));
		lblLogInAs.setFont(new Font("Roboto", Font.PLAIN, 17));
		lblLogInAs.setBounds(57, 389, 286, 35);
		panelLogin.add(lblLogInAs);

		JPanel panelUsername = new JPanel();
		panelUsername.setBorder(null);
		panelUsername.setBackground(Color.WHITE);
		panelUsername.setBounds(55, 192, 288, 35);
		panelUsername.setLayout(null);
		panelLogin.add(panelUsername);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Roboto", Font.PLAIN, 17));
		txtUsername.setBorder(null);
		txtUsername.setBounds(10, 3, 232, 29);
		txtUsername.setColumns(10);
		panelUsername.add(txtUsername);

		JLabel lblUsernameIcon = new JLabel("");
		lblUsernameIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblUsernameIcon.setBorder(null);
		lblUsernameIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Image img_1 = new ImageIcon(this.getClass().getResource("/user_blue.png")).getImage()
						.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
				lblUsernameIcon.setIcon(new ImageIcon(img_1));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Image img_2 = new ImageIcon(this.getClass().getResource("/user_black.png")).getImage()
						.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
				lblUsernameIcon.setIcon(new ImageIcon(img_2));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				txtUsername.requestFocusInWindow();
			}
		});
		lblUsernameIcon.setBackground(Color.WHITE);
		lblUsernameIcon.setHorizontalAlignment(SwingConstants.CENTER);
		Image img_2 = new ImageIcon(this.getClass().getResource("/user_black.png")).getImage().getScaledInstance(20, 20,
				Image.SCALE_SMOOTH);
		lblUsernameIcon.setIcon(new ImageIcon(img_2));
		lblUsernameIcon.setBounds(240, 0, 48, 35);
		panelUsername.add(lblUsernameIcon);

		JPanel panelPassword = new JPanel();
		panelPassword.setBorder(null);
		panelPassword.setBackground(Color.WHITE);
		panelPassword.setBounds(55, 297, 288, 35);
		panelLogin.add(panelPassword);
		panelPassword.setLayout(null);

		pwdPassword = new JPasswordField();
		pwdPassword.setFont(new Font("Roboto", Font.PLAIN, 17));
		pwdPassword.setBorder(null);
		pwdPassword.setBounds(10, 3, 232, 29);
		pwdPassword.setEchoChar('•');
		panelPassword.add(pwdPassword);

		JLabel lblPasswordIcon = new JLabel("");
		lblPasswordIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblPasswordIcon.setBorder(null);
		lblPasswordIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Image img_1 = new ImageIcon(this.getClass().getResource("/key_blue.png")).getImage()
						.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
				lblPasswordIcon.setIcon(new ImageIcon(img_1));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Image img_2 = new ImageIcon(this.getClass().getResource("/key.png")).getImage().getScaledInstance(20,
						20, Image.SCALE_SMOOTH);
				lblPasswordIcon.setIcon(new ImageIcon(img_2));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				pwdPassword.requestFocusInWindow();
			}
		});
		lblPasswordIcon.setBounds(240, 0, 48, 35);
		lblPasswordIcon.setHorizontalAlignment(SwingConstants.CENTER);
		// Default image icon is set for lblPasswordIcon icon
		Image img_3 = new ImageIcon(this.getClass().getResource("/key.png")).getImage().getScaledInstance(20, 20,
				Image.SCALE_SMOOTH);
		lblPasswordIcon.setIcon(new ImageIcon(img_3));
		lblPasswordIcon.setBackground(Color.WHITE);
		panelPassword.add(lblPasswordIcon);
		
		JCheckBox chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxShowPassword.setBorder(null);
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char echoChar = chckbxShowPassword.isSelected() ? '\0' : '•';
				pwdPassword.setEchoChar(echoChar);
			}
		});
		chckbxShowPassword.setFocusable(false);
		chckbxShowPassword.setForeground(new Color(228, 241, 254));
		chckbxShowPassword.setFont(new Font("Roboto", Font.PLAIN, 17));
		chckbxShowPassword.setBackground(new Color(72, 113, 247));
		chckbxShowPassword.setBounds(55, 340, 149, 23);
		panelLogin.add(chckbxShowPassword);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (txtUsername.getText().equals("admin") && pwdPassword.getText().equals("123")) {
					JOptionPane.showMessageDialog(null, "Login successful!");
					AdminInterface admin = new AdminInterface();
					admin.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid username or password!", "Invalid credentials",
							JOptionPane.ERROR_MESSAGE);
					pwdPassword.setText("");
				}
			}
		});
		btnAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdmin.setFocusable(false);
		btnAdmin.setForeground(new Color(228, 241, 254));
		btnAdmin.setBackground(new Color(12, 122, 158));
		btnAdmin.setFont(new Font("Roboto", Font.BOLD, 17));
		btnAdmin.setBounds(55, 434, 119, 41);
		panelLogin.add(btnAdmin);

		JButton btnEmployee = new JButton("Employee");
		btnEmployee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEmployee.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (validateLogin(txtUsername.getText(), pwdPassword.getText())) {
					JOptionPane.showMessageDialog(null, "Login successful!");
					EmployeeInterface employee = new EmployeeInterface();
					employee.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid username or password!", "Invalid credentials",
							JOptionPane.ERROR_MESSAGE);
					pwdPassword.setText("");
				}
			}
		});
		btnEmployee.setFocusable(false);
		btnEmployee.setForeground(new Color(228, 241, 254));
		btnEmployee.setBackground(new Color(74, 21, 173));
		btnEmployee.setFont(new Font("Roboto", Font.BOLD, 17));
		btnEmployee.setBounds(224, 434, 119, 41);
		panelLogin.add(btnEmployee);
	}

    private boolean validateLogin(String username, String password) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.println("LOGIN");
            writer.println(username);
            writer.println(password);

            String response = reader.readLine();

            writer.close();
            reader.close();

            return "SUCCESS".equals(response);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}