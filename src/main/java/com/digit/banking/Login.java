package com.digit.banking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Login")
public class Login extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int cust_id = Integer.parseInt(req.getParameter("Cust_id"));
		int pin = Integer.parseInt(req.getParameter("Pin"));
		String url = "jdbc:mysql://localhost:3306/banking";
		String user = "root";
		String pwd = "root";
		HttpSession session = req.getSession();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pwd);
			pstmt = con.prepareStatement("select * from register where Cust_id=? and Pin=?");

			pstmt.setInt(1, cust_id);

			pstmt.setInt(2,pin );
			resultSet = pstmt.executeQuery();
			if(resultSet.next()==true) {
				session.setAttribute("ACC_No", resultSet.getInt("ACC_No"));
				session.setAttribute("Customer_name", resultSet.getString("Customer_name"));
				resp.sendRedirect("/Banking_Application/Home.jsp");
				
				
			}
			else {
				resp.sendRedirect("/Banking_Application/LoginFail.html");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
