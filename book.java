import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;

class book
{
	public static boolean log(String n,String p)
	{
		String name = "cis";
		String password = "1234";
		return (name.compareTo(n)==0 && password.compareTo(p)==0);
	}

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		String t_name,t_pw;
		
		for(boolean user=false,logging=false;;)
		{
			if(user)
			{
				break;
			}
			else if(!(logging))
			{
				System.out.print("Enter your account:\t");
				t_name = input.nextLine();
				System.out.print("Enter your password:\t");
				t_pw = input.nextLine();
				user = log(t_name,t_pw);
				logging = true;
			}
			else if(logging && !(user))
			{
				int again=-1;
				String temp;
				System.out.print("Try again:\tEnter 1.\nFinish this operation:\tEnter 0.\nHere is your decision:");
				temp = input.nextLine();

				try
				{
					again = Integer.parseInt(temp);
				}
				catch(NumberFormatException e)
				{
					
					again = -1;
					System.out.print("Illegal opeartion,try again.\n");
				}
				finally
				{
					if(again==1)
					{
						System.out.print("\nEnter your account:\t");
						t_name = input.nextLine();
						System.out.print("Enter your password:\t");
						t_pw = input.nextLine();
						user = log(t_name,t_pw);
						logging = true;
					}
					else if(again == 0)
					{
						System.exit(again);
					}
				}
			}
		}
		/*************
		log end
		**************/
		nodeLink list1=new nodeLink();
		Scanner inputStream=null;
		boolean canAdd=false,isError=false;
		boolean fopen =false;
		try
		{
			inputStream = new Scanner(new FileInputStream("database.txt"));
			fopen=true;
		}
		catch(FileNotFoundException e)
		{
			System.out.print("No last use record");
		}
			for(;fopen;)
			{
				for(;inputStream.hasNextLine();)
				{
					String[] a=new String[5];
					String fin = inputStream.nextLine();
					StringTokenizer st = new StringTokenizer(fin,"\t");
					int index=0;
					for(;st.hasMoreTokens();index++)
					{
						if(index<5)
						{
							a[index]=st.nextToken();
						}
						switch(index)
						{
							case 0:
							if(!(node.checkName(a[index])))
							{
								System.out.println("Wrong name:"+a[index]);
								isError=true;
							}
							break;
							case 1:
							if(!(node.checkBirth(a[index])))
							{
								System.out.println("Wrong birth"+a[index]);
								isError=true;
							}
							break;
							case 2:
							if(!(node.checkPhoneNumber(a[index])))
							{
								System.out.println("Wrong phone"+a[index]);
								isError=true;
							}
							break;
							case 3:
							break;
							case 4:
							if(!(node.checkMail(a[index])))
							{
								System.out.println("Wrong mail"+a[index]);
								isError=true;
							}
							break;
							default:
							isError=true;
							break;
						}
						if(isError==true)
						{
							canAdd=true;
							isError=false;
						}
					}
					index=index-1;
					if(index>=5)
					{
						System.out.println("Wrong file data format out of range.");
					}
					else if(canAdd==true)
					{
						System.out.println("Wrong file data format in one of 5 types.");
					}
					else
					{
						node temp = new node(a[0],a[1],a[2],a[3],a[4]);
						if(list1.find(temp))
						{
							System.out.println("Wrong file data repeat.");
						}
						else
						{
							list1.addToTop(a[0],a[1],a[2],a[3],a[4]);
						}
					}
				}
				if(!(inputStream.hasNextLine()))
				break;
			}
			list1.save();
		inputStream.close();
		/*****************
		intialize
		*****************/
		String choose1="";
		String order="5";
		int page=0;
		for(boolean judge1=false;;)
		{
			char ch=' ';
			System.out.println("###MAIN MENU###\nUser normal operation:->1\nControl or setting:->2\nFinished operation->3");
			choose1 = input.nextLine();
			if(choose1.matches("^[1-2]+$"))
			{
				judge1=true;
				ch = choose1.charAt(0);
			}
			else if(choose1.matches("^[3]{1}$"))
			{
				break;
			}
			else
			{
				System.out.println("Invalid input");
			}
			
			String choose2="";
			for(boolean judge2=false;judge1 && ch=='1';)
			{
				System.out.println("###User Menu###\n0->ADD,1->search,2->modify,3->delete,4->show,5->out.");
				choose2 = input.nextLine();
		
				if(!(choose2.matches("^[0-5]{1}$")))
				{
						continue;
				}
				judge2=true;
				node temp = new node();
		
				switch(choose2.charAt(0))
				{
					case '0':/*ADD*/
					temp.setNode();
					if(list1.find(temp))
					{
						System.out.println("Already has same.");
					}
					else
					{
						list1.addToTop();
						list1.setHead(temp);
						list1.save();
						System.out.println("Add success.");
					}
					break;
					case '1':/*find*/
					boolean isfind=false;
					boolean[] SO = new boolean[5];
					boolean[] link = new boolean[list1.size()];
					for(int index=0;index<list1.size();index++)
					{
						link[index]=false;
					}
					for(int index=0;index<5;index++)
					{
						SO[index]=false;
					}
					String searchOrder="5";
					System.out.println("Enter:number\n5->show all position.\nOr combination of |0->name|1->birth|2->phone|3->class|4->mail");
					for(;;)
					{
						searchOrder = input.nextLine();
						if(searchOrder.matches("^[0-4]+$"))
						{
							for(int index=0;index<searchOrder.length();index++)
							{
								switch (searchOrder.charAt(index))
								{
									case '0':
									SO[0]=true;
									break;
									case '1':
									SO[1]=true;
									break;
									case '2':
									SO[2]=true;
									break;
									case '3':
									SO[3]=true;
									break;
									case '4':
									SO[4]=true;
									break;
									default:
									break;
								}
							}
							break;
						}
						else if(searchOrder.matches("^[5]{1}$"))
						{
							if(searchOrder.equals("5"))
							{
								temp.setNode();
								isfind=list1.find(temp);		
								if(isfind)
								{
									System.out.println(temp.toString()+" exsit!");
								}
								else
								{
									System.out.println(temp.toString()+" not exsit!");
								}
							}
							break;
						}
					}
					if(searchOrder.equals("5"))
					{
						break;
					}
					for(int index=0;index<5;index++)
					{
						String sear="";
						node t = list1.getHead();
						if(t==null)
						{
							break;
						}
						if(SO[index])
						{
							switch(index)
							{
								case 0:
								System.out.println("Name:");
								sear =input.nextLine();
								for(int ln=0;t!=null;ln++)
								{
									if(!(link[ln]) && t.getName().equals(sear))
									{
										System.out.println(t.toString("5"));
										link[ln]=true;
									}
									t=t.getRlink();
								}
								break;
								case 1:
								System.out.println("Birth:");
								sear =input.nextLine();
								for(int ln=0;t!=null;ln++)
								{
									if(!(link[ln]) && t.getBirth().equals(sear))
									{
										System.out.println(t.toString("5"));
										link[ln]=true;
									}
									t=t.getRlink();
								}
								break;
								case 2:
								System.out.println("Phone:");
								sear =input.nextLine();
								for(int ln=0;t!=null;ln++)
								{
									if(!(link[ln]) && t.getPhoneNumber().equals(sear))
									{
										System.out.println(t.toString("5"));
										link[ln]=true;
									}
									t=t.getRlink();
								}
								break;
								case 3:
								System.out.println("Class:");
								sear =input.nextLine();
								for(int ln=0;t!=null;ln++)
								{
									if(!(link[ln]) && t.getClassification().equals(sear))
									{
										System.out.println(t.toString("5"));
										link[ln]=true;
									}
									t=t.getRlink();
								}
								break;
								case 4:
								System.out.println("Mail:");
								sear =input.nextLine();
								for(int ln=0;t!=null;ln++)
								{
									if(!(link[ln]) && t.getMail().equals(sear))
									{
										System.out.println(t.toString("5"));
										link[ln]=true;
									}
									t=t.getRlink();
								}
								break;
								default:
								break;
							}
						}
					}
					/*
					if(searchOrder.equals("5"))
					{
						temp.setNode();
						isfind=list1.find(temp);		
						if(isfind)
						{
							System.out.println(temp.toString()+" exsit!");
						}
						else
						{
							System.out.println(temp.toString()+" not exsit!");
						}
					}
					*/
					break;
					case '2':/*modify*/
					boolean isModify = false;
					temp.setNode();
					isModify=list1.modify(temp);
						
					if(isModify)
					{
						System.out.println(temp.toString()+" modify!");
					}
					else
					{
						System.out.println(temp.toString()+" not exsit!");	
					}
					list1.save();
					break;
					case '3':/*delete*/
					boolean isDelete=false;
					temp.setNode();
					isDelete = list1.delete(temp);
					
					if(isDelete)
					{
						System.out.println(temp.toString()+" has been delete!");
					}
					else
					{
						System.out.println(temp.toString()+" not exsit! originally!");	
					}
					list1.save();
					break;
					case '4':/*show*/
					if(order.matches("^[0-4]{1,5}$"))
					{
						temp = list1.getHead();
						if(temp==null)
							System.out.println("null");
						for(int index=0;temp!=null;temp=temp.getRlink())
						{
							if(index==page && page > 0)
							{
								System.out.println("\f");
							}
							System.out.println(temp.toString(order));
							if(index<page)
							{
								index++;
							}
							else
							{
								index=1;
							}
						}
					}
					else if(order.matches("^[5]{1}$"))
					{
						temp= list1.getHead();
						System.out.println("All show success.");
						if(temp==null)
							System.out.println("null");
						for(;temp!=null;temp=temp.getRlink())
						{
							System.out.println(temp.toString(order));
						}
					}
					break;
					case '5':/*break*/
					judge1=false;
					judge2=false;
					break;
				}
			}
			nodeLink list2;
			for(boolean judge2=false;judge1 && ch=='2';)
			{
				System.out.println("0->Number of all data\n1->Copy file\n2->data show choose\n3->page data number:\n4->out");
				choose2 = input.nextLine();
				if(choose2.matches("^[0-4]{1}$"))
				{
					judge2=true;
					switch(choose2.charAt(0))
					{
						case '0':
						System.out.println("Data you have :"+list1.size());
						break;
						case '1':
						System.out.print("Copy file name:");
						String copy = input.nextLine();
						list1.save(copy);
						break;
						case '2':
						for(;;)
						{
							System.out.println("Enter:number\n5->show all position.\nOr combination of |0->name|1->birth|2->phone|3->class|4->mail");
							order = input.nextLine();
							if(order.matches("^[0-4]{1,5}$"))
							{
								break;
							}
							else if(order.matches("^[5]{1}$"))
							{
								break;
							}
						}
						break;
						case '3':
						for(;;)
						{
							System.out.println("If you need page.Enter data number per page\nIf not,Enter '0'");
							page = input.nextInt();
							if(page>=0)
							{
								break;
							}
						}
						break;
						case '4':
						judge1=false;
						judge2=false;
						break;
						default:
						break;
					}
				}
				
			}
		}
		//input.close();
	}
}