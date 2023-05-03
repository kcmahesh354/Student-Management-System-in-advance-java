
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("serial")
class Student implements Serializable{

    int RegNo;
    String Name;
    String School ;
    String Level;
    double Percentage ;

    public Student(int RegNo, String Name, String School, String Level, double Percentage)
    {
        this.RegNo = RegNo;
        this.Name = Name;
        this.School = School;
        this.Level=Level;
        this.Percentage =Percentage;
    }

    public String toString()
    {
        return "\nStudent Details :" + "\nRegNo: " + this.RegNo + "\nName: " + this.Name + "\nSchool: " +
                this.School + "\nGrade: " + this.Level+ "\nLevel: " + this.Percentage;
    }

}

public class StudentManagement
{
    static void display(ArrayList<Student> al)
    {
        System.out.println("\n--------------Student List---------------\n");
        System.out.println(String.format("%-10s%-15s%-10s%-20s%-10s", "RegNo","Name","School","Grade","Percentage"));
        for(Student s : al)
        {
            System.out.println(String.format("%-5s%-20s%-10s%-15s%-10s",s.RegNo,s.Name,s.School,s.Level,s.Percentage));
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        int RegNo;
        String Name;
        String School;
        String Level;
        double Percentage;

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> al = new ArrayList<Student>();

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos =null;
        try{

            f = new File("C:\\Users\\kcmah\\Desktop\\Student Management System\\src\\EmployeeDataList1.txt");
            if(f.exists())
            {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                al = (ArrayList<Student>)ois.readObject();
            }

        }
        catch(Exception exp){
            System.out.println(exp);
        }
        do
        {
            System.out.println("\n----------Welcome to the Student Management System ----------\n");
            System.out.println("1). Add Student to the DataBase\n" +
                    "2). Search for Student\n" +
                    "3). Edit Student details\n" +
                    "4). Delete Student Details\n" +
                    "5). Display all Students in School\n" +
                    "6). EXIT\n");
            System.out.println("\n-------------------------------------------------------\n");
            System.out.println("Enter your choice : ");
            int ch = sc.nextInt();

            switch(ch)
            {
                case 1:System.out.println("\nEnter the following details to ADD list:\n");
                    System.out.println("Enter ID :");
                    RegNo = sc.nextInt();
                    System.out.println("Enter Name :");
                    Name = sc.next();
                    System.out.println("Enter School :");
                    School = sc.next();
                    System.out.println("Level :");
                    Level = sc.next();
                    System.out.println("Percentage :");
                    Percentage = sc.nextDouble();
                    al.add(new Student(RegNo, Name, School, Level, Percentage));
                    display(al);
                    break;

                case 2: System.out.println("Enter the Student RegNo to search :");
                    RegNo = sc.nextInt();
                    int i=0;
                    for(Student s: al)
                    {
                        if(RegNo == s.RegNo)
                        {
                            System.out.println(s+"\n");
                            i++;
                        }
                    }
                    if(i == 0)
                    {
                        System.out.println("\nStudent Details are not available, Please enter a valid RegNo!!");
                    }
                    break;

                case 3: System.out.println("\nEnter the Student ID to EDIT the details");
                    RegNo = sc.nextInt();
                    int j=0;
                    for(Student s: al)
                    {
                        if(RegNo == s.RegNo)
                        {
                            j++;
                            do{
                                int ch1 =0;
                                System.out.println("\nEDIT Student Details :\n" +
                                        "1). Student RegNo\n" +
                                        "2). Name\n" +
                                        "3). School\n" +
                                        "4). Grade\n" +
                                        "5). Percentage\n" +
                                        "6). GO BACK\n");
                                System.out.println("Enter your choice : ");
                                ch1 = sc.nextInt();
                                switch(ch1)
                                {
                                    case 1: System.out.println("\nEnter new Student RegNo:");
                                        s.RegNo =sc.nextInt();
                                        System.out.println(s+"\n");
                                        break;

                                    case 2: System.out.println("Enter new Student Name:");
                                        s.Name =sc.nextLine();
                                        System.out.println(s+"\n");
                                        break;

                                    case 3: System.out.println("Enter new Student School :");
                                        s.School =sc.nextLine();
                                        System.out.println(s+"\n");
                                        break;

                                    case 4: System.out.println("Enter new Student Grade :");
                                        s.Level =sc.nextLine();
                                        System.out.println(s+"\n");
                                        break;

                                    case 5: System.out.println("Enter new Student Percentage :");
                                        s.Percentage = sc.nextDouble();
                                        System.out.println(s+"\n");
                                        break;

                                    case 6: j++;
                                        break;

                                    default : System.out.println("\nEnter a correct choice from the List :");
                                        break;

                                }
                            }
                            while(j==1);
                        }
                    }
                    if(j == 0)
                    {
                        System.out.println("\nStudent Details are not available, Please enter a valid RegNo!!");
                    }

                    break;

                case 4: System.out.println("\nEnter Student RegNo to DELETE from the Database :");
                    RegNo = sc.nextInt();
                    int k=0;
                    try{
                        for(Student s: al)
                        {
                            if(RegNo == s.RegNo)
                            {
                                al.remove(s);
                                display(al);
                                k++;
                            }
                        }
                        if(k == 0)
                        {
                            System.out.println("\nStudent Details are not available, Please enter a valid RegNo!!");
                        }
                    }
                    catch(Exception ex){
                        System.out.println(ex);
                    }
                    break;

                case 5: try {
                    al = (ArrayList<Student>)ois.readObject();

                } catch (ClassNotFoundException e2) {

                    System.out.println(e2);
                } catch (Exception e2) {

                    System.out.println(e2);
                }
                    display(al);
                    break;

                case 6: try {
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(al);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                catch(Exception e2){
                    e2.printStackTrace();
                }
                finally{
                    try {
                        fis.close();
                        ois.close();
                        fos.close();
                        oos.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }
                    System.out.println("\nYou have chosen EXIT !! Saving Files and closing the tool.");
                    sc.close();
                    System.exit(0);
                    break;

                default : System.out.println("\nEnter a correct choice from the List :");
                    break;

            }
        }
        while(true);
    }

}