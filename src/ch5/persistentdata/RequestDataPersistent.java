package ch5.persistentdata;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import shared.PersistentBase;

@Entity
public class RequestDataPersistent 
		extends PersistentBase 
		implements Serializable {
	protected String hobby;
	protected String aversion;
	protected int daysPerWeek;

	@Pattern(regexp = ".*\\S.*", message = "cannot be empty")
	@NotNull
	public final String getHobby() {
		return hobby;
	}

	public final void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Pattern(regexp = ".*\\S.*", message = "cannot be empty")
	@NotNull
	public final String getAversion() {
		return aversion;
	}

	public final void setAversion(String aversion) {
		this.aversion = aversion;
	}

	@Min(value = 1, message = "cannot be less than 1, if this is a hobby.")
	@Max(value = 7, message = "cannot be greater than 7. A week only has 7 days.")
	public final int getDaysPerWeek() {
		return daysPerWeek;
	}

	public final void setDaysPerWeek(int daysPerWeek) {
		this.daysPerWeek = daysPerWeek;
	}

}
