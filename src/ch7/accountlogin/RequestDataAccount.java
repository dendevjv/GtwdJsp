package ch7.accountlogin;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import shared.PersistentBase;

@Entity
public class RequestDataAccount extends PersistentBase implements Serializable {
    private static final long serialVersionUID = -642542482526171299L;

    protected String hobby;
    protected String aversion;
    protected String accountNumber;

    @Pattern(regexp = "[a-zA-Z]{2}\\d{3}", message = "must be in the format AA999.")
    @NotNull
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

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

}
