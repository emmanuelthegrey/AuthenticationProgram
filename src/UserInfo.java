
public class UserInfo {
private String userName;
private String hash;
private String passWord;
private String position;

public UserInfo(String userName, String hash, String passWord, String position) {
	super();
	this.userName = userName;
	this.hash = hash;
	this.passWord = passWord;
	this.position = position;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getHash() {
	return hash;
}

public void setHash(String hash) {
	this.hash = hash;
}

public String getPassWord() {
	return passWord;
}

public void setPassWord(String passWord) {
	this.passWord = passWord;
}

public String getPosition() {
	return position;
}

public void setPosition(String position) {
	this.position = position;
}

}
