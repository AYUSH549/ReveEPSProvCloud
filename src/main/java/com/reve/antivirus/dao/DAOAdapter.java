package com.reve.antivirus.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.support.JdbcUtils;
//import databasemanager.DatabaseManager;
import reveantivirus.resources.DAOManager;


public class DAOAdapter {

	private static final Logger logger = LogManager.getLogger(DAOAdapter.class.getName());
	
	@Qualifier("datasource")
	@Autowired
	private static  javax.sql.DataSource dataSource;

	
	  public static <T> T findById(Connection con, Class<T> c, Object primaryKey, HashMap<String, Object> filters)
	    {
	        Connection cn = con;
	        try
	        {
	            if(cn == null)
	            {
	               // cn = DatabaseManager.getInstance().getConnection();
	            }
	            return DAOManager.getDAO(c).findById(cn, primaryKey, filters);
	        }
	        catch (Exception e)
	        {
	            logger.fatal(e.getMessage(), e);
	            return null;
	        }
	        finally
	        {
	            if (cn != null && con==null)
	            {
	                try
	                {
	                  //  DatabaseManager.getInstance().freeConnection(cn);
	                }
	                catch (Exception e)
	                {
	                }
	            }
	        }
	    }
	  
	  public static <T> List<T> find(Connection con, Class<T> c, HashMap<String, Object> filters, int[] range)
	    {
	        Connection cn = con;
	        try
	        {
	            if(cn == null)
	            {
	              //  cn = DatabaseManager.getInstance().getConnection();
	            }
	            return DAOManager.getDAO(c).find(cn, filters, range);
	        }
	        catch (Exception e)
	        {
	            logger.fatal(e.getMessage(), e);
	            return null;
	        }
	        finally
	        {
	            if (cn != null && con == null)
	            {
	                try
	                {
	               //     DatabaseManager.getInstance().freeConnection(cn);
	                }
	                catch (Exception e)
	                {
	                }
	            }
	        }
	    }
	  
	  public static <T> int[] insert(Connection con,Class<T> c, List<T> list)
	    {
	        Connection cn = con;
	        try
	        {
	            if(cn == null)
	            {
	             //   cn = DatabaseManager.getInstance().getConnection();
	            }
	            return DAOManager.getDAO(c).insert(cn, list);
	        }
	        catch (Exception e)
	        {
	            logger.fatal(e.getMessage(), e);
	            return null;
	        }
	        finally
	        {
	            if (cn != null && con == null)
	            {
	                try
	                {
	                 //  DatabaseManager.getInstance().freeConnection(cn);
	                }
	                catch (Exception e)
	                {
	                }
	            }
	        }
	    }
	  
	  public static<T> int insert(Connection con,Class<T> c, T data)
	    {
	        Connection cn = con;
	        
	        try
	        {
	            if(cn == null){
	                cn =  dataSource.getConnection();
	            }
	            return DAOManager.getDAO(c).insert(cn, data);
	        }
	        catch (Exception e)
	        {
	            logger.fatal(e.getMessage(), e);
	            return -1;
	        }
	        finally
	        {
	            if (cn != null && con == null)
	            {
	                try
	                {
	                	//dataSource.getConnection().close();
	                	//logger.info("Connection closed");
	                }
	                catch (Exception e)
	                {
	                }
	            }
	        }
	    }

	    
	  
	  public static <T> int update(Connection con,Class<T> c, T data)
	    {
	        Connection cn = con;
	        try
	        {
	            if(cn == null)
	            {
	              //  cn = DatabaseManager.getInstance().getConnection();
	            }
	            return DAOManager.getDAO(c).update(cn, data);
	        }
	        catch (Exception e)
	        {
	            logger.fatal(e.getMessage(), e);
	            return 0;
	        }
	        finally
	        {
	            if (cn != null && con == null)
	            {
	                try
	                {
	                  //  DatabaseManager.getInstance().freeConnection(cn);
	                }
	                catch (Exception e)
	                {
	                }
	            }
	        }
	    }

	    public static <T> int deleteById(Connection con, Class<T> c, Object primaryKey, HashMap<String, Object> filters)
	    {
	        Connection cn = con;
	        try
	        {
	            if(cn == null)
	            {
	              //  cn = DatabaseManager.getInstance().getConnection();
	            }
	            return DAOManager.getDAO(c).deleteById(cn, primaryKey, filters);
	        }
	        catch (Exception e)
	        {
	            logger.fatal(e.getMessage(), e);
	            return 0;
	        }
	        finally
	        {
	            if (cn != null && con == null)
	            {
	                try
	                {
	                  //  DatabaseManager.getInstance().freeConnection(cn);
	                }
	                catch (Exception e)
	                {
	                }
	            }
	        }
	    }

	    public static <T> int delete(Connection con,Class<T> c, HashMap<String, Object> filters)
	    {
	        Connection cn = con;
	        try
	        {
	            if(cn == null)
	            {
	              //  cn = DatabaseManager.getInstance().getConnection();
	            }
	            return DAOManager.getDAO(c).delete(cn, filters);
	        }
	        catch (Exception e)
	        {
	            logger.fatal(e.getMessage(), e);
	            return 0;
	        }
	        finally
	        {
	            if (cn != null && con == null)
	            {
	                try
	                {
	                  //  DatabaseManager.getInstance().freeConnection(cn);
	                }
	                catch (Exception e)
	                {
	                }
	            }
	        }
	    }

	    public static <T> int count(Connection con, Class<T> c)
	    {
	        return count(con, c, null);
	    }

	    public static <T> int count(Connection con, Class<T> c, HashMap<String, Object> filters)
	    {
	        Connection cn = con;
	        try
	        {
	            if(cn == null)
	            {
	             //   cn = DatabaseManager.getInstance().getConnection();
	            }
	            return DAOManager.getDAO(c).count(cn, filters);
	        }
	        catch (Exception e)
	        {
	            logger.fatal(e.getMessage(), e);
	            return -1;
	        }
	        finally
	        {
	            if (cn != null && con == null)
	            {
	                try
	                {
	                  //  DatabaseManager.getInstance().freeConnection(cn);
	                }
	                catch (Exception e)
	                {
	                }
	            }
	        }
	    }
	    
	    public static <T> T findById(Connection con, Class<T> c, Object primaryKey)
	    {
	        return findById(con,c, primaryKey, null);
	    }

	    
	    public static <T> List<T> findRange(Connection con, Class<T> c, int[] range)
	    {
	        return find(con, c, null, range);
	    }
	    

	    public static <T> List<T> findAll(Connection con,Class<T> c)
	    {
	        return find(con, c, null, null);
	    }



}
