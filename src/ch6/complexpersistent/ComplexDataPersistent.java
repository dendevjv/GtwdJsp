package ch6.complexpersistent;

import java.io.Serializable;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.IndexColumn;

import shared.AttributeType;
import shared.PersistentBase;
import shared.SetByAttribute;

@Entity
public class ComplexDataPersistent extends PersistentBase implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String secretCode;
    protected int happiness;
    protected String[] extra;
    protected String comments;
    protected double grade;
    protected String[] team;
    
    public ComplexDataPersistent() {
    }
      
    public void setSecretCode(String code) {
        this.secretCode = code;
    }
    
    public String getSecretCode() {
        return secretCode;
    }
    
    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }
    
    @SetByAttribute(type=AttributeType.CHECKED)
    public int getHappiness() {
        return happiness;
    }
    
    public void setExtra(String[] extra) {
        this.extra = extra;
    }

    @ElementCollection
    @IndexColumn(name = "extra_pos", base=0)
    @NotNull(message=": must select at least one extra.")
    @SetByAttribute(type=AttributeType.CHECKED)
    public String[] getExtra() {
        return extra;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public String getComments() {
        return comments;
    }
    
    public void setGrade(double grade) {
        this.grade = grade;
    }
    
    @SetByAttribute(type=AttributeType.SELECTED)
    public double getGrade() {
        return grade;
    }
    
    public void setTeam(String[] team) {
        this.team = team;
    }
    
    @ElementCollection
    @IndexColumn(name = "team_pos", base=0)
    @Size(min=0, max=3, message=": cannot select all teams.")
    @SetByAttribute(type=AttributeType.SELECTED)
    public String[] getTeam() {
        return team;
    }
}
