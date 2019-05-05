import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

class nodeLink
{
	private node head;
	private int addNum;
	
	public nodeLink()
	{
		addNum=0;
		head = null;
	}
	public void addToTop(String n,String b,String p,String c,String m)
	{
		if(head==null)
		{
			head = new node(n,b,p,c,m);
		}
		else
		{
			node temp;
			temp = getHead();
			head = new node(n,b,p,c,m);
			head.setRlink(temp);
			temp.setLlink(head);
		}
	}
	public void addToTop()
	{
		if(head==null)
		{
			head = new node();
		}
		else
		{
			node temp=null;
			temp = getHead();
			head = new node();
			head.setRlink(temp.getNode());
			temp.setLlink(getHead());
		}
	}
	public boolean delete(node sample)
	{
		boolean isfind=false;
		node temp=getHead();
		isfind=find(sample);
		if(isfind)
		{
			for(;temp.dataEquals(sample)!=true;temp=temp.getRlink());
		}
		
		if(!isfind)
		{
			System.out.println("No such data,delete out!");
		}
		else if(temp.getRlink()==null && temp.getLlink() == null)
		{
			head = null;
			System.out.println("Last data has been delete.");
		}
		else if(temp.getRlink()==null)
		{
			temp.getLlink().setRlink(null);
			temp.setLlink(null);
			System.out.println("Buttom data has been delete.");
		}
		else if(temp.getLlink() == null)
		{
			temp.getRlink().setLlink(null);
			temp.setRlink(null);
			System.out.println("Top data has been delete.");
		}
		else
		{
			temp.getRlink().setLlink(temp.getLlink());
			temp.getLlink().setRlink(temp.getRlink());
			System.out.println("Middle data has been delete.");
		}
		return isfind;
	}
	public int size()
	{
		int count=0;
		node temp = head;
		while(temp!=null)
		{
			count++;
			temp = temp.getRlink();
		}
		return count;
	}
	public boolean nodeSet()
	{
		return head.setNode();
	}
	public String toString()
	{
		return head.toString();
	}
	public String toString(String showOrder)
	{
		return head.toString(showOrder);
	}
	public node getHead()
	{
		if(head==null)
			return null;
		else
			return new node(head);
	}
	
	public boolean find(node sample)
	{
		node temp=null;
		boolean isfind=false;
		if(head!=null)
		{
			temp=getHead();
			isfind=temp.dataEquals(sample);
	
			for(;!isfind;)
			{
			
				if(temp.getRlink()==null)
				{
					break;
				}
				else
				{
					temp=temp.getRlink();
				}
				isfind=temp.dataEquals(sample);
			}
		}
		
		if(isfind && temp!=null)
		{
			System.out.println("Data exist!");
			System.out.println(temp.toString());
		}
		else
		{
			System.out.println("Data doesn't exist!");
		}
		return isfind;
	}
	public boolean modify(node sample)
	{
		boolean isfind=false;
		String in;
		Scanner input = new Scanner(System.in);
		
		node temp=head;
		isfind=find(sample);
		if(isfind)
		{
			for(;temp.dataEquals(sample)!=true;temp=temp.getRlink());
		}
		else
		{
			return false;
		}
		
		System.out.print("Which data do you want to modify?\nAll->0 or choose one in\n1->name.\n2->birth.\n3->phone.\n4->class\n5->mail\nHere is your decision:\nOut:enter other word");
	
		in=input.nextLine();
		
		for(;;)
		{
		
			if(in.matches("^[1-5]{1}$"))
			{
				for(int index=0;index<1;index++)
				{
						System.out.print(index);
						System.out.print("Next modify\n");
						switch(in.charAt(index))
						{
							case '1':
							temp.setName();
							break;
							case '2':
							temp.setBirth();
							break;
							case '3':
							temp.setPhoneNumber();
							break;
							case '4':
							temp.setClassification();
							break;
							case '5':
							temp.setMail();
							break;
						}
				}
				System.out.print("out!");
				save();
				break;
			}
			else if(in.matches("^[0]{1}$"))
			{
				temp.setNode();
				System.out.print("All data modify success\n");
				save();
				break;
			}
			else
			{
				System.out.print("Keep data and Leave modify mode\n");
				break;
			}
		}
		return true;
	}
	public void setHead()
	{
		head.setNode();
	}
	public void setHead(node n2)
	{
		head.setNode(n2);
	}
	public void save()
	{
		PrintWriter outputStream=null;
		try
		{
			outputStream = new PrintWriter(new FileOutputStream("database.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("New file create");
		}
		finally
		{
			if(outputStream!=null)
			{
				node temp=getHead();
				for(;temp!=null;)
				{
					outputStream.println(temp.toString());
					temp = temp.getRlink();
				}
			}
		}
		outputStream.close();
	}
	public void save(String copy)
	{
		PrintWriter outputStream=null;
		try
		{
			outputStream = new PrintWriter(new FileOutputStream("C:\\Users\\new pc\\Desktop\\"+copy+".txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("New file create");
		}
		finally
		{
			if(outputStream!=null)
			{
				node temp=getHead();
				for(;temp!=null;)
				{
					outputStream.println(temp.toString());
					temp = temp.getRlink();
				}
			}
		}
		outputStream.close();
	}
	public void copyLink(nodeLink n2)
	{
		node temp=getHead();
		n2.setHead(temp);
		for(node temp2;temp!=null;)
		{
			temp2=temp.getNode();
			temp=temp.getRlink();
			temp=temp.getNode();
			temp2.setRlink(temp);
			temp.setLlink(temp2);
		}
		
	}
	public node bigCompareTo(String order)
	{
		nodeLink link2=null;
		node temp = getHead();
		boolean[] SO = new boolean[5];
		/*
		for(int index=0;index<;index++)
		{
			switch (order.charAt(index))
			{
				case: '0':
				for(int size2=0;size2<size();size2++)
				{
					temp = getHead();
					node temp2=temp.getRlink();
					for(;temp != null || temp.getName().compareTo(temp2.getName())!=0;temp=temp.getRlink())
					{
						if(temp.getName().compareTo(temp2.getName())>0)
						{
							link2.addToTop(temp.getName(),temp.getBirth(),temp.getPhoneNunmber(),temp.getClassification(),temp.getMail());
						}
						temp2=temp2.getRlink();
					}
					list1 = list2;
				}
				break;
				case: '1':
				for(int size2=0;size2<size();size2++)
				{
					temp = getHead();
					node temp2=temp.getRlink();
					for(;temp != null || temp.getBirth().compareTo(temp2.getBirth())!=0;temp=temp.getRlink())
					{
						if(temp.getBitrh().compareTo(temp2.getBirthth())>0)
						{
							link2.addToTop(temp.getName(),temp.getBirth(),temp.getPhoneNunmber(),temp.getClassification(),temp.getMail());
						}
						temp2=temp2.getRlink();
					}
					list1 = list2;
				}
				break;
				case  '2':
				break;
				case '3':
				break;
				case '4':
				default:
				break;
			}
		}
		
		switch(order.charAt(0))
		{
			case '0':
			for(;;)
			{
				
			}
			break;
			case '1':
			break;
			case '2':
			break;
			case '3':
			break;
			case '4':
			break;
		}
		*/
		return null;
	}
}