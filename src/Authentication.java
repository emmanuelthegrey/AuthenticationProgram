import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;

public class Authentication {

	public static void main(String[] args) throws Exception {
		
		try{

		printFile(checkPassword(checkUserName(userInfo())));
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}

	public static ArrayList<UserInfo> userInfo() throws Exception {
		String[] userInfo;

		ArrayList<UserInfo> users = new ArrayList<UserInfo>();

		String lineToBeRead = null;
		FileReader reader = null;
		BufferedReader bufferedReader = null;

		reader = new FileReader("C:\\Users\\emmanuel\\workspace\\Final Project SNHU\\credentials.txt");

		bufferedReader = new BufferedReader(reader);

		while ((lineToBeRead = bufferedReader.readLine()) != null) {
			userInfo = lineToBeRead.split(",");
			String userName = userInfo[0].trim();
			String hashP = userInfo[1].trim();
			String pw = userInfo[2].trim();
			String position = userInfo[3].trim();

			UserInfo user = new UserInfo(userName, hashP, pw, position);
			users.add(user);
		}
		return users;
	}

	public static UserInfo checkUserName(ArrayList<UserInfo> users) throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter username: ");
		String input = sc.next();

		int length = users.size();
		int tries = 0;
		do {
			for (int i = 0; i < length; i++) {
				String username = users.get(i).getUserName();
				if (username.equals(input)) {

					return users.get(i);
				}

			}
			System.out.print("Invalid Username, Please try again: ");
			tries++;
			if (tries == 3) {
				throw new Exception("Too many failed attempts...now exiting");
			}
			input = sc.next();
		} while (true);
	}

	public static UserInfo checkPassword(UserInfo user) throws Exception {
		Scanner sc = new Scanner(System.in);
		int tries = 0;
		System.out.print("Enter password: ");
		String input = sc.nextLine();
		String pass = user.getHash();
		do {
			String original = input;

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(original.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}

			String userHash = sb.toString();
			if (userHash.equals(pass)) {
				return user;
			}
			System.out.print("Invalid password, please try again: ");
			tries++;
			if (tries == 3) {
				throw new Exception("Too many failed attempts...now exiting");
			}

			input = sc.next();
		} while (true);

	}

	public static void printFile(UserInfo user) throws Exception {
		Scanner input = new Scanner(System.in);
		String choice = "y";
		
do{
		String position = user.getPosition();
		String zooPath = "C:\\Users\\emmanuel\\workspace\\Final Project SNHU\\Vet.txt";
		String adminPath = "C:\\Users\\emmanuel\\workspace\\Final Project SNHU\\admin.txt";
		String vetPath = "C:\\Users\\emmanuel\\workspace\\Final Project SNHU\\admin.txt";

		String lineToBeRead = null;
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		// File vetFile = new File(vetPath);
		// File zookeeperFile = new File(zooPath);
		// File adminFile = new File(adminPath);

		if (position.equals("zookeeper")) {
			reader = new FileReader(zooPath);
		} else if (position.equals("admin")) {
			reader = new FileReader(adminPath);

		} else if (position.equals("veterinarian")) {
			reader = new FileReader(vetPath);

		}
		bufferedReader = new BufferedReader(reader);

		System.out.println(bufferedReader.readLine());

		while ((lineToBeRead = bufferedReader.readLine()) != null) {
			System.out.println(bufferedReader.readLine());

		}
		System.out.print("Do you want to log out? y/n: ");
		choice = input.nextLine();
}while(!choice.equalsIgnoreCase("y"));

		// reader = new FileReader("C:\\Users\\emmanuel\\workspace\\Final
		// Project SNHU\\credentials.txt");
		//
		// bufferedReader = new BufferedReader(reader);

		// while ((lineToBeRead = bufferedReader.readLine()) != null) {
		// userInfo = lineToBeRead.split(",");
		// String userName = userInfo[0].trim();
		// String hashP = userInfo[1].trim();
		// String pw = userInfo[2].trim();
		// String position = userInfo[3].trim();
		//
		// UserInfo user = new UserInfo(userName, hashP, pw, position);
		// users.add(user);
		// }

	}
}