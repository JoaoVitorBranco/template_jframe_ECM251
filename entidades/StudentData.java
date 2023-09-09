package entidades;

import java.util.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class StudentData {
    private int id_grade;
    private int id_user;
    private Time time;
    private Date date;
    private float grade;
    private SimpleDateFormat date_formatter = new SimpleDateFormat("yyyy-MM-dd");  
    private SimpleDateFormat time_formatter = new SimpleDateFormat("HH:mm:ss");
    
    public StudentData(){
        this.id_grade = 0;
        this.id_user = 0;
        this.time = null;
        this.date = null;
        this.grade = 0;
    }

    public StudentData(int id_grade, int id_user){
        this.id_user = id_user;
        this.id_grade = id_grade;
        this.time = null;
        this.date = null;
        this.grade = 0;
    }
    
    public String getCurrentTimeString(){
        return this.time_formatter.format(new Date());
    }
    
    public String getCurrentDateString(){
        return this.date_formatter.format(new Date());
    }

    public String getTimeString(){
        return this.time_formatter.format(this.time);
    }

    public String getDateString(){
        return this.date_formatter.format(this.date);
    }

    public int getIdGrade() {
        return this.id_grade;
    }

    public void setIdGrade(int id_grade) {
        this.id_grade = id_grade;
    }

    public int getIdUser() {
        return this.id_user;
    }

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }

    public Time getTime() {
        return this.time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getGrade() {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String roundedString = decimalFormat.format(this.grade);
        String n = roundedString.replace(",", ".");
        float roundedFloat = Float.parseFloat(n);
        return roundedFloat;
    }

    public double getGrade_double(){
        double d = this.grade;
        return d;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String toString(){
        return "Id Grade: " + this.id_grade + "\n" +
               "Id User: " + this.id_user + "\n" +
               "Nota: " + this.grade + "\n" +
               "Data: " + this.getCurrentDateString() + "\n" +
               "Hora: " + this.getCurrentTimeString();
    }


}
