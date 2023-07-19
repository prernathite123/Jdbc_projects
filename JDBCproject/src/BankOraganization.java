

import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
public class BankOraganization {


public static void main(String[] args)  {
Scanner sc=new Scanner(System.in);
String name;
System.out.println("Enter your name");
name =sc.next();
try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
    PreparedStatement s1=con.prepareStatement("SELECT admin_name FROM admin WHERE admin_name=?");
    s1.setString(1, name);
    ResultSet rs = s1.executeQuery();
    if(rs.next())
    {
        String admin_name=rs.getString("admin_name");
        if( name.equals(admin_name)) {
            System.out.println("successfully login");
            int ch=0;
            do {
                System.out.println("1:Insertion");
                System.out.println("2:Display");
                System.out.println("3:Withdraw");
                System.out.println("4:Deposite");
                System.out.println("5:Log out");
                System.out.println("Enter the no for choice");

                ch=sc.nextInt() ;

                switch(ch)
                {
                    case 1:
                        System.out.println("Insertion operation");
                        insert();
                        break;

                    case 2:
                        System.out.println("Display data");
                        display();
                        break;

                    case 3:
                        System.out.println(" withdraw operation");
                        withdraw();
                        break;

                    case 4:
                        System.out.println("diposite operation");
                        deposite();
                        break;

                    case 5:
                        System.out.println("log out");

                    default:

                        System.out.println("invalid choice");
                        break;
                }
            }while(ch<5);

        }else
        {
            System.out.println("please try again");
        }
    }else
    {
        System.out.println("failed");
    }
}catch(ClassNotFoundException | SQLException e)

{
    e.printStackTrace();
}

}
public static void insert() throws ClassNotFoundException, SQLException {
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
PreparedStatement s=con.prepareStatement("insert into account values(?,?,?)");
Scanner sc=new Scanner(System.in);
int cost_id,balance;
String name;
System.out.println("enter the costomer id");
cost_id=sc.nextInt();
System.out.println("enter the name");
name=sc.next();
System.out.println("enter the balnace");
balance=sc.nextInt();
s.setInt(1,cost_id);
s.setString(2,name);
s.setInt(3,balance);
int a=s.executeUpdate();


if(a!=0)
{
    System.out.println("data inserted");
}
else
{
    System.out.println("insertion failed");
}

s.close();
con.close();
}
public static void display() throws ClassNotFoundException, SQLException {
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
Statement stat=con.createStatement();
String str="SELECT* FROM account";
ResultSet rs=stat.executeQuery(str);
System.out.println("Displaying all values from table ");
while(rs.next())
{
    int cost_id1=rs.getInt("cost_id");
    int balance1=rs.getInt("balance");
    String name1=rs.getString("name");
    System.out.println("Customer id :"+cost_id1);
    System.out.println("Customer balance:"+balance1);
    System.out.println("Customer name :"+name1);
}
rs.close();
stat.close();
con.close();
}
public static void withdraw()throws ClassNotFoundException, SQLException  {
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
PreparedStatement s=con.prepareStatement("UPDATE account SET balance=? WHERE cost_id=?");
Scanner sc=new Scanner(System.in);
int balance = 0,cost_id=0;
System.out.println("Enter the costumer id:");
cost_id=sc.nextInt();
System.out.println("Enter the amount for withdraw: ");
int wbalance =sc.nextInt();
PreparedStatement s1=con.prepareStatement("SELECT balance FROM account WHERE cost_id=?");
s1.setInt(1, cost_id);
ResultSet rs = s1.executeQuery();
if(rs.next())
{
    balance =rs.getInt("balance");

    if(balance>500)
    {
        balance=balance-wbalance;
        System.out.println("Balnace present in your account:"+balance);
    }
    else
    {
        System.out.println("Your current balance is not sufficient/n your current balnce :"+balance);
    }

    s.setInt(1,balance);
    s.setInt(2,cost_id);
    int a=s.executeUpdate();
    if(a!=0)
    {
        System.out.println("update successfully");
    }else
    {
        System.out.println("failed");
    }
}else
{
    System.out.println("customer not found");
}

rs.close();
con.close();

}
public static void deposite()throws ClassNotFoundException, SQLException {
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
PreparedStatement s=con.prepareStatement("UPDATE account SET balance=? WHERE cost_id=?");
Scanner sc=new Scanner(System.in);
int balance = 0,cost_id=0;
System.out.println("Enter the costumer id:");
cost_id=sc.nextInt();
System.out.println("Enter the amount for deposite: ");
int wbalance =sc.nextInt();
PreparedStatement s1=con.prepareStatement("SELECT balance FROM account WHERE cost_id=?");
s1.setInt(1, cost_id);
ResultSet rs = s1.executeQuery();
if(rs.next())
{
    balance =rs.getInt("balance");

    if(balance>0)
    {
        balance=balance+wbalance;
        System.out.println("Balnace present in your account:"+balance);
    }
    else
    {
        System.out.println("Your current balance is :"+balance);
    }

    s.setInt(1,balance);
    s.setInt(2,cost_id);
    int a=s.executeUpdate();
    if(a!=0)
    {
        System.out.println("update successfully");
    }else
    {
        System.out.println("failed");
    }
}else
{
    System.out.println("customer not found");
}

rs.close();
s.close();
con.close();

}
}

