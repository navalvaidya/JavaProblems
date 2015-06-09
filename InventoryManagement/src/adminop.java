import java.sql.*;
import java.util.Scanner;
public class adminop extends empop {
	public void insert()
	{
		Scanner s=new Scanner(System.in);
		try
		{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","naval.2402");
		System.out.println("Enter ID");
		int id=s.nextInt();
		System.out.println("Enter Name");
		String name=s.next();
		System.out.println("Enter Cost");
		Float cost=s.nextFloat();
		System.out.println("Enter Seller ID");
		int sid=s.nextInt();
		System.out.println("Enter Quantity");
		int quant=s.nextInt();
		
		PreparedStatement stat1=con.prepareStatement("INSERT INTO items (id, name, cost, seller_id, quantity) VALUES (?,?,?,?,?)");
		stat1.setInt(1, id);
		stat1.setString(2, name);
		stat1.setFloat(3, cost);
		stat1.setInt(4, sid);
		stat1.setInt(5, quant);
		stat1.executeUpdate();
		PreparedStatement stat2=con.prepareStatement("SELECT * FROM items where id=?");
		stat2.setInt(1, id);
		ResultSet result = stat2.executeQuery();
		System.out.println("Following item is added to database");
		System.out.printf("%-8s","ID");
		System.out.printf("%-20s","Name");
		System.out.printf("%-15s","Cost");
		System.out.printf("%-15s","Seller ID");
		System.out.printf("%-15s","Quantity");
		System.out.println("");
		while(result.next())
		{
			
		
			System.out.printf("%-8s",result.getString("id"));
			System.out.printf("%-20s",result.getString("name"));
			System.out.printf("%-15s",result.getString("cost"));
			System.out.printf("%-15s",result.getString("seller_id"));
			System.out.printf("%-15s",result.getString("quantity"));
			System.out.println("");
			
		}
		}
		catch(SQLException e)
		{
			System.out.println("ERROR: "+e);
		}
	}
	public void remove()
	{
		Scanner s=new Scanner(System.in);
		try
		{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","naval.2402");
			System.out.println("Enter ID of item to be removed");
			int id=s.nextInt();
			PreparedStatement stat2=con.prepareStatement("SELECT * FROM items where id=?");
			stat2.setInt(1, id);
			ResultSet result = stat2.executeQuery();
			PreparedStatement stat1=con.prepareStatement("DELETE FROM items WHERE id=?");
			stat1.setInt(1, id);
			stat1.executeUpdate();
			
			System.out.println("Following item is Removed from database");
			System.out.printf("%-8s","ID");
			System.out.printf("%-20s","Name");
			System.out.printf("%-15s","Cost");
			System.out.printf("%-15s","Seller ID");
			System.out.printf("%-15s","Quantity");
			System.out.println("");
			while(result.next())
			{
				
			
				System.out.printf("%-8s",result.getString("id"));
				System.out.printf("%-20s",result.getString("name"));
				System.out.printf("%-15s",result.getString("cost"));
				System.out.printf("%-15s",result.getString("seller_id"));
				System.out.printf("%-15s",result.getString("quantity"));
				System.out.println("");
				
			}
		}
		catch(SQLException e)
		{
			System.out.println("ERROR: "+e);
		}
	}
	

}
