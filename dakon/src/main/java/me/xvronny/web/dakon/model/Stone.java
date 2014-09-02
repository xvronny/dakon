package me.xvronny.web.dakon.model;

public class Stone {

	private final String uuid;
	
	public Stone(String uuid) {
		this.uuid = uuid;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) 
			return false;
		if (!(obj instanceof Stone))
			return false;
		Stone that = (Stone) obj;
		return this.uuid.equals(that.uuid);
	}
	
	@Override
	public int hashCode() {
		return this.uuid.hashCode();
	}
	
}
