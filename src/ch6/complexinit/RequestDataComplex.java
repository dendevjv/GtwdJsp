package ch6.complexinit;

import shared.AttributeType;
import shared.SetByAttribute;

public class RequestDataComplex {
    protected String secretCode;
    protected int happiness;
    protected String[] extra;
    protected String comments;
    protected double grade;
    protected String[] team;
    
    public RequestDataComplex() {
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
    
    @SetByAttribute(type=AttributeType.SELECTED)
    public String[] getTeam() {
        return team;
    }
}
