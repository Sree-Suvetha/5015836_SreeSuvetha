class Student
{
    int id;
    String name;
    String grade;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

 class StudentView
 {
    public void displayStudentDetails(String studentName, int studentId, String studentGrade) 
    {
        System.out.println("Student Details:");
        System.out.println("Name: " + studentName);
        System.out.println("ID: " + studentId);
        System.out.println("Grade: " + studentGrade);
    }
}

class StudentController
{
    private Student model;
    private StudentView view;
    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }
    public void setStudentName(String name) {
        model.setName(name);
    }
    public String getStudentName() {
        return model.getName();
    }

    public void setStudentId(int id) {
        model.setId(id);
    }

    public int getStudentId() {
        return model.getId();
    }

    public void setStudentGrade(String grade) {
        model.setGrade(grade);
    }

    public String getStudentGrade() {
        return model.getGrade();
    }

    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }

}

public class MVCPatternExample {
    public static void main(String aargs[])
    {
        Student stu=new Student();
        StudentView view = new StudentView();
        StudentController control=new StudentController(stu, view);

        control.setStudentId(1);
        control.setStudentName("Sree");
        control.setStudentGrade("A");
        control.updateView();
    }
}