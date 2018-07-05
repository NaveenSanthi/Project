import java.util.*;
class Connection
{
	private int connId;
	private Subscriber subscriber;
	private Package pkg;
	private String sDate;
	Connection(int connId,Subscriber subscriber,Package pkg,String sDate)
	{
		this.connId=connId;
		this.subscriber=subscriber;
		this.pkg=pkg;
		this.sDate=sDate;
	}
	void setconnId(int connId)
	{
		this.connId=connId;
	}
	int getconnId()
	{
		return connId;
	}
	void setsubscriber(Subscriber subscriber)
	{
		this.subscriber=subscriber;
	}
	Subscriber getsubscriber()
	{
		return subscriber;
	}
	void setpkg(Package pkg)
	{
		this.pkg=pkg;
	}
	Package getpkg()
	{
		return pkg;
	}
	void setsDate(String sDate)
	{
		this.sDate=sDate;
	}
	String getsDate()
	{
		return sDate;
	}
}
class InvalidPackageException extends Exception
{
	InvalidPackageException(String msg)
	{
		super(msg);
	}
}
class InvalidSubscriberException extends Exception
{
	InvalidSubscriberException(String msg)
	{
		super(msg);
	}

}
class Package
{
	private int pkgId,noOfChannels;
	private String pkgName;
	private double cost;
	Package(int pkgId,String pkgName,int noOfChannels,double cost)
	{
		this.pkgId=pkgId;
		this.pkgName=pkgName;
		this.noOfChannels=noOfChannels;
		this.cost=cost;
	}
	void setpkgId(int pkgId)
	{
		this.pkgId=pkgId;
	}
	int getpkgId()
	{
		return pkgId;
	}
	void setpkgName(String pkgName)
	{
		this.pkgName=pkgName;
	}
	String getpkgName()
	{
		return pkgName;
	}
	void setnoOfChannels(int noOfChannels)
	{
		this.noOfChannels=noOfChannels;
	}
	int getnoOfChannels()
	{
		return noOfChannels;
	}
	void setcost(double cost)
	{
		this.cost=cost;
	}
	double getcost()
	{
		return cost;
	}
	void isvalids(int noOfChannels) throws InvalidPackageException
	{
		if(noOfChannels<10)
		{
			throw new InvalidPackageException("invlaid package");
		}
	}
}
class SkyD2H
{
	private ArrayList<Connection>connlist=new ArrayList<Connection>();
	public ArrayList<Package> pkglist = new ArrayList<Package>();
	public void addConnection(Connection con)
	{
		connlist.add(con);
	}
	public void addPackage(Package pkg)
	{
		pkglist.add(pkg);
	}
	public double getPackageCost(String pkgName)
	{
		/*Iterator itr=pkglist.iterator();
		while(itr.hasNext())
		{
			Package pkg =(Package)itr.next();
			if(pkgName==pkg.getpkgName())
			{
				return pkg.getcost();
			}
			
		}
		return 0;*/
		for(Package pg:pkglist)
			if(pg.getpkgName()==pkgName)
				return pg.getcost();
			return 0;
	}
	public int getCountSubscriberConnection(int sId)
	{
		/*Iterator connitr= connlist.iterator();
		while(connitr.hasNext())
		{
			Connection con=(Connection)connitr.next();
			Subscriber sub=con.getsubscriber();
			if(sId==sub.getsId())
				return connlist.size();
			
		}return 0;*/
		for(Connection con:connlist)
			if(con.getsubscriber().getsId()==sId)
				return connlist.size();
			return 0;
			
	}
	
}
class Subscriber
{
	private int sId;
	private String sName,email;
	long contact;
	Subscriber(int sId,String sName,long contact,String email)
	{
		this.sId=sId;
		this.sName=sName;
		this.email=email;
		this.contact=contact;
	}
	void setsId(int sId)
	{
		this.sId=sId;
	}
	int getsId() 
	{
		return sId;
	}
	void setsName(String sName)
	{
		this.sName=sName;
	}
	String getsName()
	{
		return sName;
	}
	void setcontact(long contact)
	{
		this.contact=contact;
	}
	long getcontact()
	{
		return contact;
	}
	void setemail(String email)
	{
		this.email=email;
	}
	String getemail()
	{
		return email;
	}
	void isvalid(long contact)throws InvalidSubscriberException
	{
		if(contact<1000000000L || contact>9999999999L)
		{
			throw new InvalidSubscriberException("Invalid Mobile Number");
		}
		
		
	}
}
class OperationD2H
{
	public static void main(String arg[])
	{
		Subscriber s1=null;
		Package p1=null;
		Package p2=null;
        try
		{
		s1=new Subscriber(101,"nav",9750208902L,"nave@gmail.com");
		s1.isvalid(s1.getcontact());
		}
		catch(InvalidSubscriberException e)
		{
			e.printStackTrace();
			//System.out.println(e);
		}
		try
		{
		p1=new Package(1,"SouthSpecial",30,50);
		p2=new Package(2,"Northspecial",175,400);
		p1.isvalids(p1.getnoOfChannels());
		}
		catch(InvalidPackageException e1)
		{
			e1.printStackTrace();
			//System.out.println(e1);
		}
		Connection c1=new Connection(1001, s1, p1, "15-jan-18");
        Connection c2=new Connection(1002, s1, p2, "25-jan-18");
		SkyD2H sky=new SkyD2H();
		sky.addPackage(p1);
		sky.addPackage(p2);
		sky.addConnection(c1);
		sky.addConnection(c2);
		System.out.println("Cost of Package Southspecial: "+sky.getPackageCost("Northspecial"));
		// Cost of Package SouthSpecial: 350.0
		System.out.println("connection of Subscriber 101:"+sky.getCountSubscriberConnection(101));
		
	}
} 
