package com.cs.DAO;
import com.cs.models.Customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
	
	public  int registerCustomer(Customer c) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		String SAVE = "INSERT INTO CUSTOMER"
				     +"(name,email,password)"
				     +"VALUES(?,?,?)";
		
		int ID = -1;
		String[] COL = {"customer_id"};
		
		
		MySqlConnection mysql = new MySqlConnection();
	    try
	    {
	        conn = mysql.getConnection();
	        stmt = conn.prepareStatement(SAVE, COL);
	        stmt.setString(1, c.getName());
	        stmt.setString(2, c.getEmail());
	        stmt.setString(3, c.getPassword());
	        
	        stmt.executeUpdate();
	        result = stmt.getGeneratedKeys();
	        if(result != null && result.next()) {
	            ID = result.getInt(1);
	        }
	        System.out.println(ID);
	    }
	    catch (ClassNotFoundException | IOException | SQLException e)
	    {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    finally {
	    	if (result != null) {
	    		result.close();
	    	}
	    	if (stmt != null) {
	    		stmt.close();
	    	}
	    	if (conn != null) {
	    		conn.close();
	    	}
	    }
	    return ID; 
	}

	public static void main(String[] args) throws SQLException  {
		  Customer c = new Customer();
		  c.setName("Peter");
     	  c.setEmail("peter.parker@gmail.com");
		  c.setPassword("peter1234");
		  CustomerDAO dao = new CustomerDAO();
		  System.out.println(dao.registerCustomer(c));

	}

}
