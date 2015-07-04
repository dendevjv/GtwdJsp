package ch6.complexvalid;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import shared.AttributeType;
import shared.SetByAttribute;

public class ComplextDataValid {
    protected String secretCode;
    protected int happiness;
    protected String[] extra;
    protected String comments;
    protected double grade;
    protected String[] team;
    
    public ComplextDataValid() {
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
    
    @Size(min=0, max=3, message=": cannot select all teams.")
    @SetByAttribute(type=AttributeType.SELECTED)
    public String[] getTeam() {
        return team;
    }
}
