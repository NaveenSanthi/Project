import java.util.ArrayList;
import java.util.Iterator;
interface BroadbandConnection
{
	public boolean activate(Customer c);
    public boolean deActivate(Customer c);
	public int getCountByPlanType(String planType);
	public int getCountByPlanName(String planName);
}
class Customer
{
	private int custId;
	private String custName;
	private Plan plan;// object passing
	private int dataUsage;//in GB
	Customer(int custId,String custName,int dataUsage)
	{
		this.custId=custId;
		this.custName=custName;
		this.plan=plan;//object assigning
		this.dataUsage=dataUsage;
	}
	public void setcustId(int custId)
	{
		this.custId=custId;
	}
	public int getcustId()
	{
		return custId;
	}
	public void setcustName(String custName)
	{
		this.custName=custName;
	}
	public String getcustName()
	{
		return custName;
	}
	public void setplan(Plan plan)
	{
		this.plan=plan;
	}
	public Plan getplan()
	{
		return plan;
	}
	public int getBill()
	{
		int bill=0;
		if(plan.getplanName().equals("PLAN_LIM_10"))
		{
			bill=(plan.getbaseCharge()+25*(dataUsage-10));
		}
		else if(plan.getplanName().equals("SUPER_LIM_20"))
		{
			bill=(plan.getbaseCharge()+35*(dataUsage-20));
		}
		else
		{
			bill=5000;
		}
		return bill;		 
	}
	
}
class InvalidPlanTypeException extends Exception
{
	InvalidPlanTypeException(String msg)
	{
		super(msg);
	}
}
class InvalidPlanTypeException extends Exception
{
	InvalidPlanTypeException(String msg)
	{
		super(msg);
	}
}
class Plan
{
	private String planName,planType;
	private int baseCharge;
	Plan(String planName,String planType,int baseCharge) throws InvalidPlanTypeException
	{
		if(planType != "LimitedPlan" && planType != "UnlimitedPlan")
			throw new InvalidPlanTypeException("invalid plan");
		this.planName=planName;
		this.planType=planType;
		this.baseCharge=baseCharge;
		
	}
	public void setplanName(String planName)
	{
		this.planName=planName;
	}
	public String getplanName()
	{
		return planName;
	}
	public void setplanType(String planType)
	{
		this.planType=planType;
	}
	public String getplanType()
	{
		return planType;
	}
	public void setbaseCharge(int baseCharge)
	{
		this.baseCharge=baseCharge;
	}
	public int getbaseCharge()
	{
		return baseCharge;
	}
}

class ServiceProvider implements BroadbandConnection
{
	public ArrayList<Customer> custDetails=new ArrayList<Customer>();
	public boolean activate(Customer c)
	{
		if(custDetails.add(c))
			return true;
		else
			return false;
	}
	public boolean deActivate(Customer c)
	{
		if(custDetails.remove(c))
			return true;
		else
			return false;
	}
	public int getCountByPlanType(String planType)
	{
		int count=0;
		for(Customer cd:custDetails)
		{
			if(cd.getplan().getplanType().equals(planType))
				count++;
			
		}return count;
		
	}
	public int getCountByPlanName(String planName)
	{
		int count=0;
		for(Customer cd:custDetails)
		{
			if(cd.getplan().getplanName().equals(planName))
				count++;
			
		}return count;
		
	}
}
class Main
{
	public static void main (String arg[])
	{
		Plan p1=null;
		Plan p2=null;
		Plan p3=null;
		Customer c1=null;
		Customer c2=null;
		Customer c3=null;
		try
		{
		p1=new Plan("PLAN_LIM_10","LimitedPlan",200);
		}
		catch(InvalidPlanTypeException e)
		{
			e.printStackTrace();
		}
		c1=new Customer(101,"Alen Perea",15);
		c1.setplan(p1);
		int forc1=c1.getBill();
		System.out.println("Bill for Alen="+forc1);
		try
		{
		p2= new Plan("SUPER_LIM_20","LimitedPlan",400);
		}
		catch(InvalidPlanTypeException e)
		{
			e.printStackTrace();
		}
        c2= new Customer(102,"Sweta Kumari",24);
        c2.setplan(p2);
        int billForC2= c2.getBill();
        System.out.println("Bill for Sweta : " + billForC2);
		try
		{
		p3= new Plan("SUPER_UNLIM_20","UnlimitedPlan",500);
		}
		catch(InvalidPlanTypeException e)
		{
			e.printStackTrace();
		}
		c3= new Customer(103,"Devesh Joshi",22);
		c3.setplan(p3);
		int billForC3= c3.getBill();
		System.out.println("Bill for Devesh : " + billForC3);	 
		ServiceProvider p=new ServiceProvider();
		p.activate(c1);
		p.activate(c2);
		p.activate(c3);
		int countByPlanType=p.getCountByPlanType("LimitedPlan");
		System.out.println("countByPlanType for Limited plan="+countByPlanType);
		int getCountByPlanName=p.getCountByPlanName("SUPER_LIM_20");
		System.out.println("countByPlanType for SUPER_LIM_20 ="+getCountByPlanName);
		
	}
}
