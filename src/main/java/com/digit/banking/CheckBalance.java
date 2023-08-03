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
@WebServlet("/CheckBalance")
public class CheckBalance extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int ACC_No = (int)session.getAttribute("ACC_No");
		
		String url = "jdbc:mysql://localhost:3306/banking";
		String user = "root";
		String pwd = "root";
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pwd);
			pstmt = con.prepareStatement("Select  Balance from Register where ACC_No=?");

			pstmt.setInt(1, ACC_No);

	
			resultSet = pstmt.executeQuery();
			if (resultSet.next() == true) {
				session.setAttribute("Balance", resultSet.getInt("Balance"));
				
				resp.sendRedirect("/Banking_Application/Balance.jsp");

			} else {
				resp.sendRedirect("/Banking_Application/BalanceFail.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
