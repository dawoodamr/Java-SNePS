/**
 * @(#)ReportSet.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/21
 */
package snip.ds;

import java.util.Vector;

import match.ds.Binding;

public class ReportSet
{
	private Vector<Report> rs;
	
	/**
	 * Create a new empty reports list
	 */
	public ReportSet()
	{
		rs =new Vector<Report>();
	}
	
	/**
	 * Check if the reports list is new or not
	 * @return true or false
	 */
	public boolean isNew()
	{
		return rs.isEmpty();
	}
	
	/**
	 * Insert the report r in the reports list with checking that r is not
	 * in the reports list
	 * @param r report
	 */
    public void insert(Report r)
	{
    	boolean found=false;
    	for(int i=0;i<rs.size();i++)
    	{
    		if(r.isEqual(rs.get(i)))
    		{
    			found=true;
    		}
    	}
    	if(!found)
    		rs.add(r);
	}
    
    /**
	 * Insert the report r in the reports list without checking that r is not
	 * in the reports list
	 * @param r report
	 */
    public void putIn(Report r)
    {
    	rs.add(r);
    }
    
    /**
     * Union this with the reports list rs
     * @param rs reoprtset
     */
    public void union(ReportSet rs)
    {
    	for(int i=0;i<rs.rs.size();i++)
    	{
    		this.insert(rs.rs.get(i));
    	}
    }
    
    /**
     * Return the first report in the reports list
     * @return report
     */
    public Report choose()
    {
    	return this.rs.get(0);
    }
    
    /**
     * Return a reports list with all reports except the first report
     * @return reportset
     */
    public ReportSet others()
    {
    	ReportSet r=new ReportSet();
    	for(int i=1;i<this.rs.size();i++)
    	{
    		r.putIn(this.rs.get(i));
    	}
    	return r;
    }
    
    /**
     * Returns a reports list equal to this and removed from it the report r
     * @param r report
     * @return reprotset
     */
    public ReportSet remove(Report r)
    {
    	ReportSet r1=new ReportSet();
    	for(int i=0;i<this.rs.size();i++)
    	{
    		if(!r.isEqual(this.rs.get(i)))
    			r1.putIn(this.rs.get(i));
    	}
    	return r1;
    }
    
    /**
     * Check if the report r exists in the reports list
     * @param r report
     * @return true or false
     */
    public boolean isMember(Report r)
    {
    	for(int i=0;i<this.rs.size();i++)
    	{
    		if(r.isEqual(this.rs.get(i)))
    			return true;
    	}
    	return false;
    }
    
    /**
     * Add the binding m to the substitutions lists of all reports in the reports list
     * @param m binding
     */
    public void addBinding(Binding m)
    {
    	for(int i=0;i<rs.size();i++)
    	{
    		rs.get(i).addBinding(m);
    	}
    }
    
    /**
	 * Returns the number of reports in the reports list
	 * @return integer
	 */
	public int cardinality()
	{
		return rs.size();
	}
	
	/**
	 * return the report number x in the reports list
	 * @param x the report number
	 * @return Report
	 */
	public Report getReport(int x)
	{
		return rs.get(x);
	}
}