import java.util.Scanner; 
public class InventoryMain {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String passwd;
		int user,adchoice,empchoice;
		do{
		System.out.println("1.Admin\n2.Employee\n3.Exit");
		user=s.nextInt();
		switch(user)
		{
		case 1:
			System.out.println("Enter Password(password is admin)");
			passwd=s.next();
			
			if(passwd.equals("admin"))
			{
				do{
			System.out.println("1. Add  an item\n2. Remove an item\n3. Search for an item\n4. View Items\n5. Return");
			adchoice=s.nextInt();
			adminop adop=new adminop();
			switch(adchoice)
			{
			case 1:
				adop.insert();
				break;
			case 2:
				adop.remove();
				System.out.println("Entry is deleted");
				break;
			case 3:
				adop.search();
				break;
			case 4:
				adop.view();
				break;
			case 5:
				break;
			}
				
			}while(adchoice!=5);
		    }
			else
			{
				System.out.println("Incorrect Password");
				break;
			}
			break;
		case 2:
			do{
			System.out.println("1. View all items\n2. Search for an item\n3. Order an item\n4. Return");
			empchoice=s.nextInt();
			empop itm=new empop();
			switch(empchoice)
			{
			case 1:
				
				itm.view();
				break;
			case 2:
				itm.search();
				break;
			case 3:
				itm.order();
				//itm.view();
			}
			}while(empchoice!=4);
			break;
			
		}
		}while(user!=3);
s.close();
	}

}
