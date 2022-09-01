package edu.java.inheritance02;

// 학생은 사람이다. -> Person: Superclass, Student: Subclass
public class Student extends Person {
    // field
    private String school;
    
    // 생성자
    public Student() {
//        super(); // superclass의 기본 생성자 호출 - 생략 가능.
        System.out.println("School() 생성자 호출");
    }
    
    public Student(String school) {
        this.school = school;
        System.out.println("Student(school) 생성자 호출");
    }
    
    public Student(String name, String school) {
        super(name);
        this.school = school;
        System.out.println("Student(name, school) 생성자 호출");
    }
    
    // getter/setter
    public String getSchool() {
        return school;
    }
    
    public void setSchool(String school) {
        this.school = school;
    }

}
