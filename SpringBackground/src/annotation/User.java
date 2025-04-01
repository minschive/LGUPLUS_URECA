package annotation;

public class User {
	private String username;
	
	@Encrypt
	private String password; // 암호화 필요. Spring 이 특정 필드 (@Encrypt 이 붙은)에 대해서 암호화
	
	public User() {}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}
