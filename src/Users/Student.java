package Users;

public class Student {
    private static final int EIGHT = 8;
    private static final int ZERO = 0;

    private String firstName;
    private String lastName;
    private int age;
    private String studentID;
    private String email;
    private String major;


    public Student(String first, String last, int age, String studentID, String mail, String major)
    {
        setFirst(first);
        setLast(last);
        setEmail(mail);
        if(studentID.length() > EIGHT)
            throw new IllegalArgumentException("Invalid student id!");
        this.studentID = studentID;
        if(age <= ZERO)
            throw new IllegalArgumentException("Invalid Age!");
        this.age = age;
        setMajor(major);
    }

    public void setFirst(String name)
    {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Invalid first name !");
        for(int i = 0; i < name.length(); i++)
        {
            if(!Character.isLetter(name.charAt(i)))
                throw new IllegalArgumentException("Invalid First Name !");
        }
        this.firstName = name;
    }

    public void setLast(String name)
    {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Invalid last name !");
        for(int i = 0; i < name.length(); i++)
        {
            if(!Character.isLetter(name.charAt(i)))
                throw new IllegalArgumentException("Invalid Last Name !");
        }
        this.lastName = name;
    }

    public void setMajor(String major)
    {
        if(major == null || major.isEmpty())
            throw new IllegalArgumentException("Invalid major !");
        for(int i = 0; i < major.length(); i++)
        {
            if(!Character.isLetter(major.charAt(i)))
                throw new IllegalArgumentException("Invalid Major !");
        }
        this.major = major;
    }

    public void setEmail(String mail)
    {
        if(mail == null || mail.isEmpty())
            throw new IllegalArgumentException("Invalid E-mail address!");
        boolean found = false;
        boolean foundDot = false;
        for(int i = 0; i < mail.length(); i++)
        {
            if(mail.charAt(i) == '@')
            {
                found = true;
            }
            if(mail.charAt(i) == '.')
            {
                foundDot = true;
            }
        }
        if(!found || !foundDot)
            throw new IllegalArgumentException("Invalid E-mail address!");
        this.email = mail;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getStudentID()
    {
        return this.studentID;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getMajor()
    {
        return this.major;
    }

    public int getAge()
    {
        return this.age;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + studentID + "), Age: " + age +
                ", Email: " + email + ", Major: " + major;
    }

    @Override
    public boolean equals(Object a)
    {
        if(this.getClass() != a.getClass())
            return false;
        Student student = (Student) a;
        return (this.getFirstName().equals(student.getFirstName()) &&
                this.getLastName().equals(student.getLastName()) &&
                this.getStudentID().equals(student.getStudentID()));
    }
}