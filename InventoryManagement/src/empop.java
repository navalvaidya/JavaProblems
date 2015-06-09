import java.sql.*;
import java.util.Scanner; 

public class empop {
	
	public void view()
	{
		try
		{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","naval.2402");
			
			Statement statement = con.createStatement();
			
			ResultSet result=statement.executeQuery("SELECT * FROM items");
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
			System.out.println("sql exceoption occured: "+e);
		}
	}
	
	public void search()
	{
		try
		{
			Scanner s =new Scanner(System.in);
			
			ResultSet result;
			
			System.out.println("1.Enter ID\n2.Enter Name");
			
			int ch=s.nextInt();
			
			if(ch==1)
			{
				System.out.println("Enter ID");
				String expr=s.next();
			
				Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","naval.2402");
		
				PreparedStatement statement=con1.prepareStatement("SELECT * FROM items WHERE id=?");
		
				statement.setString(1,expr);
		
				result=statement.executeQuery();
			}
			else
			{
				System.out.println("Enter Name");
				String expr=s.next();
				
				Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","naval.2402");
				
				PreparedStatement statement=con1.prepareStatement("SELECT * FROM items WHERE name REGEXP ?");
				
				statement.setString(1,expr);
				
				result=statement.executeQuery();
			}
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
	s.close();
		}
		catch(SQLException e)
		{
			System.out.println("SQL Exception error: "+e);
		}
	}
	
	public void order()
	{
		try
		{
		Scanner s=new Scanner(System.in);
		
		ResultSet result = null;
		
		System.out.println("Enter ID of item");	    
		int ident=s.nextInt();
		
		System.out.println("Enter Quantity");	
		int quant=s.nextInt();
		
		Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","naval.2402");
		
		PreparedStatement stat1=con2.prepareStatement("UPDATE items SET quantity=quantity-? WHERE id = ?");
		
		stat1.setInt(1, quant);
		stat1.setInt(2, ident);
		
		stat1.executeUpdate();
		PreparedStatement stat2=con2.prepareStatement("SELECT * FROM items where id=?");
		stat2.setInt(1, ident);
		result = stat2.executeQuery();
		System.out.println("Your order has been placed\nDetails of your order");
		System.out.printf("%-8s","ID");
		System.out.printf("%-20s","Name");
		System.out.printf("%-15s","Quantity");
		System.out.printf("%-15s","Total");
		System.out.println("");
		while(result.next())
		{
			
		
			System.out.printf("%-8s",result.getString("id"));
			System.out.printf("%-20s",result.getString("name"));
			System.out.printf("%-15d",quant);
			System.out.printf("%-15s",result.getFloat("cost")*quant);
			System.out.println("");
			
		}
	s.close();
		}
		catch(SQLException e)
		{
			System.out.println("SQL ERROR: "+e);
		}
	}

}
