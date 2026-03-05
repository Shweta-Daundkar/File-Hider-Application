package model;

public class Data {
	private int id;
	private String fileName;
	private String path;
	private String email;
	public Data(int id, String fileName, String path, String email) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.path = path;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public Data(String email) {
		super();
		this.email = email;
	}
	public Data(int id, String fileName, String path) {
	    this.id = id;
	    this.fileName = fileName;
	    this.path = path;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}