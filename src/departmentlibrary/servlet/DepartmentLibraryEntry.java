package DepartmentLibrary.servlet;

public class DepartmentLibraryEntry{
	Integer id;
	String type;
	String name;
	String additionalInfo;
	String available;
	
	public DepartmentLibraryEntry(Integer id, String type, String name, String additionalInfo, String available) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.additionalInfo = additionalInfo;
		this.available = available;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	
	
	
}
