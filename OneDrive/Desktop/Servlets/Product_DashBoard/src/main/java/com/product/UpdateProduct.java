package com.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class UpdateProduct extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException 
	{
		int productId=Integer.parseInt(req.getParameter("productid"));
		int productPrice=Integer.parseInt(req.getParameter("productprice"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db?user=root&password=Saichandu@090");
			    PreparedStatement st=conn.prepareStatement("update product set productprice=? where productid=?;"))
			{
				st.setInt(2, productId);
				st.setInt(1, productPrice);
				
				int rs=st.executeUpdate();
				PrintWriter pw=res.getWriter();
				if(rs!=0)
				{
				   pw.print("<h1> "+rs+" data updated Succesfully!!</h1>");
				}
				else
					pw.print("<h1> data not updated !!</h1>");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}
	

}
