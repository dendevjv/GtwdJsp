package ch3.defaultvalidate;

public class RequestDataDefault {
	private static final String DEFAULT_AVERSION = "No Aversion";
	private static final String DEFAULT_HOBBY = "No Hobby";
	
	protected String hobby;
	protected String aversion;
	
	public final String getHobby() {
		if (isValidHobby()) {
			return hobby;
		} else {
			return DEFAULT_HOBBY;
		}
	}
	
	public final void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	public final String getAversion() {
		if (isValidAversion()) {
			return aversion;
		} else {
			return DEFAULT_AVERSION;
		}
	}
	
	public final void setAversion(String aversion) {
		this.aversion = aversion;
	}
	
	private boolean isValidHobby() {
		return hobby != null && hobby.trim().length() > 0;
	}
	
	private boolean isValidAversion() {
		return aversion != null && aversion.trim().length() > 0;
	}
}
