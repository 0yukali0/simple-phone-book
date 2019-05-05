import java.util.Scanner;

class node
{
	private String name;
	private String birth;
	private String phoneNumber;
	private String classification;
	private String mail;
	private node rlink;
	private node llink;
	
	public node(node copy)
	{
		this(copy.getName(),copy.getBirth(),copy.getPhoneNumber(),copy.getClassification(),copy.getMail(),copy.getRlink(),copy.getLlink());
	}
	public node()
	{
		this("Empty","Empty","Empty","Empty","Empty",null,null);
	}
		public node(String n,String b,String p,String c,String m)
	{
		this(n,b,p,c,m,null,null);
	}
	public node(String n,String b,String p,String c,String m,node r,node l)
	{
		name = n;
		birth = b;
		phoneNumber = p;
		classification = c;
		mail = m;
		rlink = r;
		llink = l;
	}
	public boolean setNode()
	{
		boolean j=false;
		j=setName();
		j=setBirth();
		j=setPhoneNumber();
		j=setClassification();
		j=setMail();
		return j;
	}
	
	public boolean setNode(node n2)
	{
		name = n2.getName();
		birth = n2.getBirth();
		phoneNumber = n2.getPhoneNumber();
		classification = n2.getClassification();
		mail = n2.getMail();
		return true;
	}
	public void resetNode()
	{
		name = "Empty";
		birth = "Empty";
		phoneNumber = "Empty";
		classification = "Empty";
		mail = "Empty";
		rlink = null;
	}

	public boolean setName()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("您所使用的是中文名稱(Use Chinese name?)嗎?\nYes or No\n");
		String in = input.nextLine().trim();
		if(in.compareToIgnoreCase("yes")==0)
		{
			System.out.print("請輸入您的名稱:\t");
			in = input.nextLine();
			name = in;
			return true;
		}
		else
		{
			System.out.print("Enter your name:\t");
		}
		
		for(;;)
		{
			in = input.nextLine();
			if(in.compareToIgnoreCase("default")==0)
			{
				name = "Empty";
				System.out.print("Default set!\n");
				return true;
			}

			String temp = "^[A-Z][a-z]+[ \t]*([A-Z][a-z]+)*[ \t]+[A-Z][a-z]+$";
			if(in.matches(temp))
			{
				System.out.println("Valid name format");
				name = in.trim();
				break;
			}
			else
			{
				System.out.println("Error name format\nif you forget the format or right name,you can enter \"default\".\nIf you want to retry,enter the name.\n");
			}
			
		}
		return true;
	}
	public boolean setBirth()
	{
		boolean judge = false;
		Scanner input = new Scanner(System.in);
		String in;
		for(;!judge;)
		{
			System.out.print("If you have no idea about birth? Enter \"default\".\nIf no,Enter your birth in the MMDD:\t");

			in = input.nextLine();
			if(in.compareToIgnoreCase("default")==0)
			{
				birth = "Empty";
				System.out.print("Default set!\n");
				judge=true;
			}

			if(in.matches("^[0-9]{4}$"))
			{
				int m = Integer.parseInt(in.substring(0,2));
				int d = Integer.parseInt(in.substring(2));

				switch(m)
				{
					case 1:
					case 3:
					case 5:
					case 7:
					case 8:
					case 10:
					case 12:
					if(d>0&&d<32)
					{
						birth=in.trim();
						judge=true;
					}
					break;
					case 4:
					case 6:
					case 9:
					case 11:
					if(d>0&&d<31)
					{
						birth=in.trim();
						judge=true;
					}
					break;
					case 2:
					if(d>0&&d<30)
					{
						birth=in.trim();;
						judge=true;
					}
					break;
					default:
					System.out.print("Error month range\n");
					break;
				}
			}
		}

		return judge;
	}
	public boolean setPhoneNumber()
	{
		boolean judge = false;
		Scanner input = new Scanner(System.in);
		String in;

		for(;!judge;)
		{
			System.out.print("Use default set,Enter \"default\"\nEnter the phonenumber:\t");
			in = input.nextLine();
			if(in.compareToIgnoreCase("default")==0)
			{
				phoneNumber = "Empty";
				System.out.print("default set!\n");
				return true;
			}

		if(in.matches("^[(]{1}[0-9]{4}[)]{1}[0-9]{6}$"))
			{
				judge=true;
				phoneNumber = in;
				break;
			}
			else
			{
				System.out.print("Wrong phoneNumber format.\n");
			}
		}
		return judge;
		
	}
	public boolean setClassification()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Class:");
		String temp = input.nextLine();
		classification=temp;
		return true;
	}
	public boolean setMail()
	{
		boolean mailSet = false;
		Scanner input = new Scanner(System.in);
		String in;

		for(;;)
		{
			System.out.print("Use default:Enter \"default\".\nEnter your email:\t");
			in = input.nextLine().trim();
			if(in.compareToIgnoreCase("default")==0)
			{
				mail = "Empty";
				System.out.print("Default set!\n");
				return true;
			}
			String temp = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
			if(in.matches(temp))
			{
				System.out.println("Correct email format");
				mail = in;
				mailSet = true;
				break;
			}
			else
			{
				System.out.println("Error email format\nif you forget the format or right email,you can enter \"default\".\nIf you want to retry,enter your mail.\n");
			}
		}
		return mailSet;	
	}
	
	public void setRlink(node r)
	{
		rlink = r;
	}
	public void setLlink(node l)
	{
		llink = l;
	}

	public String getName() {return name;}
	public String getBirth() {return birth;}
	public String getPhoneNumber() {return phoneNumber;}
	public String getClassification() {return classification;}
	public String getMail() {return mail;}
	public node getRlink() {return rlink;}
	public node getLlink() {return llink;}
	public node getNode() 
	{
		return new node(getName(),getBirth(),getPhoneNumber(),getClassification(),getMail(),getRlink(),getLlink());
	}
	public boolean dataEquals(node n2)
	{
		return (getName().equals(n2.getName()) 
				&& getBirth().equals(n2.getBirth())
				&& getPhoneNumber().equals(n2.getPhoneNumber()) 
				&& getClassification().equals(n2.getClassification())
				&& getMail().equals(n2.getMail())
				);
	}
	
	public String toString()
	{
		String temp = getName()+"\t"+getBirth()+"\t"+getPhoneNumber()+"\t"+getClassification()+"\t"+getMail();
		return temp;
	}
	public String toString(String show)
	{
		if(show.matches("^[5]{1}$"))
		{
			String temp2 = "Name:"+getName()+"\tBirth:"+getBirth()+"\tPhoneNumber:"+getPhoneNumber()+"\tClassification:"+getClassification()+"\tMail:"+getMail();
			return temp2;
		}
		
		boolean[] a = new boolean[5];
		
		String temp="";
		for(int index=0;index<5;index++)
		{
			a[index]=false;
		}
		
		for(int index=0;index<show.length();index++)
		{
			switch(show.charAt(index))
			{
				case '0':
				if(!(a[0]))
				{
					temp = temp+"\tName:" + getName();
					a[0]=true;
				}
				break;
				case '1':
				if(!(a[1]))
				{
					temp = temp+"\tBirth:" + getBirth();
					a[1]=true;
				}
				break;
				case '2':
				if(!(a[2]))
				{
					temp = temp+"\tPhoneNumber:" + getPhoneNumber();
					a[2]=true;
				}
				break;
				case '3':
				if(!(a[3]))
				{
					temp =temp+ "\tClassification:" + getClassification();
					a[2]=true;
				}
				break;
				case '4':
				if(!(a[4]))
				{
					temp =temp+ "\tMail:" + getMail();
					a[4]=true;
				}
				break;
			}
		}
		return temp.trim();
	}
	public static boolean checkName(String data)
	{
		if(data.matches("^[A-Z][a-z]+[ \t]*([A-Z][a-z]+)*[ \t]+[A-Z][a-z]+$"))
		{
			return true;
		}else if(data.matches("^[^0-9A-Za-z]$"))
		{
			return true;
		}
		return false;
	}
	public static boolean checkBirth(String data)
	{
		return data.matches("^[0-9]{4}$");
	}
	public static boolean checkPhoneNumber(String data)
	{
		return data.matches("^[(]{1}[0-9]{4}[)]{1}[0-9]{6}$");
	}
	public static boolean checkMail(String data)
	{
		return data.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*");
	}
}