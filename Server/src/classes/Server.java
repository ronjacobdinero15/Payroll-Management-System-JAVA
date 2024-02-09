package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Server {
	private static final int PORT = 5000; // Choose a suitable port number

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server started. Waiting for client connection...");

			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
				PrintWriter writerLogin = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String requestType = reader.readLine();

				if ("LOGIN".equals(requestType)) {
					String username = reader.readLine();
					String password = reader.readLine();
					if (validateLogin(username, password)) {
						writerLogin.println("SUCCESS");
					} else {
						writerLogin.println("FAILURE");
					}
				} else if ("DELETE_EMPLOYEE_WORKED_RECORD".equals(requestType)) {
					int selectedRowTable = Integer.parseInt(reader.readLine());
					try {
						BufferedReader reader1 = new BufferedReader(new FileReader("datas/logged_in.txt"));
						String line = reader1.readLine();
						String[] fields = line.split("// ");
						String ID = fields[0];

						Path path = Paths.get("employees/" + ID + ".txt");
						List<String> lines = Files.readAllLines(path);

						lines.remove(selectedRowTable);

						Files.write(path, lines);
						reader1.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				} else if ("DELETE_BLUE_TABLE_ROW".equals(requestType)) {
					int selectedRowBlueTable = Integer.parseInt(reader.readLine());
					try {
						Path path = Paths.get("datas/admin.txt");
						List<String> lines = Files.readAllLines(path);

						lines.remove(selectedRowBlueTable);

						// Update txt file
						Files.write(path, lines);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				} else if ("DELETE_ORANGE_TABLE_ROW".equals(requestType)) {
					int selectedRowOrangeTable = Integer.parseInt(reader.readLine());
					try {
						Path path = Paths.get("datas/employee_list.txt");
						List<String> lines = Files.readAllLines(path);

						String extractedLine = lines.get(selectedRowOrangeTable);
						String[] field = extractedLine.split("// ");

						lines.remove(selectedRowOrangeTable);

						Files.write(path, lines);

						File notepadFile = new File("employees/" + field[0] + ".txt");
						if (notepadFile.exists()) {
							notepadFile.delete();
						}
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				} else if ("SUBMIT".equals(requestType)) {
					BufferedReader reader1 = new BufferedReader(new FileReader("datas/logged_in.txt"));
					String line = reader1.readLine();
					if (line != null) {
						String[] fields = line.split("// ");

						String formattedDate = reader.readLine();
						String HoursWorked = reader.readLine();
						String DailyRate = reader.readLine();

						FileWriter writer = new FileWriter("employees/" + fields[0] + ".txt", true);
						writer.write(fields[0] + "// " + fields[1] + "// " + fields[2] + "// " + fields[3] + "// "
								+ fields[4] + "// " + formattedDate + "// " + HoursWorked + "// " + DailyRate + "\n");
						writer.close();

						FileWriter writer_1 = new FileWriter("datas/admin.txt", true);
						writer_1.write(fields[0] + "// " + fields[1] + "// " + fields[2] + "// " + fields[3] + "// "
								+ fields[4] + "// " + formattedDate + "// " + HoursWorked + "// " + DailyRate + "\n");
						writer_1.close();
					}
					reader1.close();
				} else if ("EMPLOYEE_TABLE".equals(requestType)) {
					sendEmployeeTableData(writerLogin);
				} else if ("BLUE_TABLE".equals(requestType)) {
					sendBlueTableData("datas/admin.txt", writerLogin);
				} else if ("ORANGE_TABLE".equals(requestType)) {
					sendOrangeTableData("datas/employee_list.txt", writerLogin);
				} else if ("CREATE".equals(requestType)) {
					String id = reader.readLine();
					String password = reader.readLine();
					String name = reader.readLine();
					String department = reader.readLine();
					String position = reader.readLine();
					String formattedDate = reader.readLine();

					PrintWriter writerCreate = new PrintWriter(clientSocket.getOutputStream(), true);

					File notepadFile = new File("employees/" + id + ".txt");
					if (notepadFile.exists()) {
						writerCreate.println("ID_EXISTS");
					} else {
						notepadFile.createNewFile();
						writerCreate.println("SUCCESS");

						FileWriter writer1 = new FileWriter("employees/" + id + ".txt");
						writer1.write(id + "// " + password + "// " + name + "// " + department + "// " + position
								+ "// " + formattedDate + "// new// new\n");
						writer1.close();

						FileWriter writer2 = new FileWriter("datas/employee_list.txt", true);
						writer2.write(id + "// " + password + "// " + name + "// " + department + "// " + position
								+ "// " + formattedDate + "// new// new\n");
						writer2.close();

						FileWriter writer3 = new FileWriter("datas/admin.txt", true);
						writer3.write(id + "// " + password + "// " + name + "// " + department + "// " + position
								+ "// " + formattedDate + "// new// new\n");
						writer3.close();
					}
				}
				clientSocket.close();
				System.out.println("Client disconnected.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean validateLogin(String username, String password) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("datas/employee_list.txt"));

			String line;
			while ((line = reader.readLine()) != null) {
				String[] index = line.split("// ");

				String storedID = index[0];
				String storedPassword = index[1];
				if (username.equals(storedID) && password.equals(storedPassword)) {
					FileWriter writer = new FileWriter("datas/logged_in.txt");
					writer.write("");
					writer.write(line);
					writer.close();

					reader.close();
					return true;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static void sendBlueTableData(String filePath, PrintWriter writer) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				writer.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void sendOrangeTableData(String filePath, PrintWriter writer) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				writer.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void sendEmployeeTableData(PrintWriter writer) {
		try (BufferedReader reader = new BufferedReader(new FileReader("datas/logged_in.txt"))) {
			String line = reader.readLine();
			String[] fields = line.split("// ");
			String ID = fields[0];

			BufferedReader reader_1 = new BufferedReader(new FileReader("employees/" + ID + ".txt"));
			String line_1;
			while ((line_1 = reader_1.readLine()) != null) {
				writer.println(line_1);
			}
			reader_1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
