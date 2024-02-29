package xyz.cucumber.base.utils.packet;

public class ClientSession {

	private String id;
	private String name;
	private String hwid;
	
	public ClientSession(String id, String name, String hwid) {
		this.id = id;
		this.name = name;
		this.hwid = hwid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHwid() {
		return hwid;
	}

	public void setHwid(String hwid) {
		this.hwid = hwid;
	}

}
