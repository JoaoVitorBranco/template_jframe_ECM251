package entidades;

import java.util.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class StudentData {
    private String name;
    private int id;
    private Time time;
    private Date date;
    private float final_grade;
    private int miss;
    private SimpleDateFormat date_formatter = new SimpleDateFormat("yyyy-MM-dd");  
    private SimpleDateFormat time_formatter = new SimpleDateFormat("HH:mm:ss");
    
    public StudentData(String name, int id){
        this.name = name;
        this.id = id;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getFinal_grade() {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String roundedString = decimalFormat.format(this.final_grade);
        String n = roundedString.replace(",", ".");
        float roundedFloat = Float.parseFloat(n);
        return roundedFloat;
    }

    public double getFinal_grade_double(){
        double d = this.final_grade;
        return d;
    }

    public void setFinal_grade(float final_grade) {
        this.final_grade = final_grade;
    }

    public int getMiss() {
        return this.miss;
    }

    public void setMiss(int miss) {
        this.miss = miss;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void print_dados(){
        System.out.println("Nome: " + this.name);
        System.out.println("ID: " + this.id);
        System.out.println("Nota final: " + this.final_grade);
        System.out.println("Faltas: " + this.miss);
        System.out.println("Data: " + this.getCurrentDateString());
        System.out.println("Hora: " + this.getCurrentTimeString());
    }


}
