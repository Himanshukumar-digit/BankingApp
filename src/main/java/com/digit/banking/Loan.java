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
@WebServlet("/loan")
public class Loan extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int choice = Integer.parseInt(req.getParameter("choice"));
		HttpSession session = req.getSession();
		
		String url = "jdbc:mysql://localhost:3306/banking";
		String user = "root";
		String pwd = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pwd);
			pstmt = con.prepareStatement("Select * from loan where lid=?");
			pstmt.setInt(1, choice);
            resultSet = pstmt.executeQuery();
			if(resultSet.next()==true) {
				session.setAttribute("l_id", resultSet.getInt("lid"));
				session.setAttribute("l_type", resultSet.getString("ltype"));
				session.setAttribute("tenure", resultSet.getInt("tenure"));
				session.setAttribute("interest", resultSet.getInt("interest"));
				session.setAttribute("description", resultSet.getString("description"));
				resp.sendRedirect("/Banking_Application/LoanDetails.jsp");
				
		}
			else {
				resp.sendRedirect("/Banking_Application/LoanFail.jsp");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
