package com.digit.banking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class RegisterC extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int Bank_id = Integer.parseInt(req.getParameter("Bank_id"));

		String Bank_name = req.getParameter("Bank_name");

		String Ifsc_code = req.getParameter("Ifsc_code");

		int ACC_no = Integer.parseInt(req.getParameter("ACC_no"));

		int Pin = Integer.parseInt(req.getParameter("Pin"));

		int Cust_id = Integer.parseInt(req.getParameter("Cust_id"));

		String Customer_name = req.getParameter("Customer_name");

		int Balance = Integer.parseInt(req.getParameter("Balance"));

		String Email = req.getParameter("Email");

		long Phone = Long.parseLong(req.getParameter("Phone"));

		String url = "jdbc:mysql://localhost:3306/banking";

		String user = "root";

		String pwd = "root";

		// Database connection

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pwd);

			pstmt = con.prepareStatement("insert into register values(?,?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, Bank_id);

			pstmt.setString(2, Bank_name);

			pstmt.setString(3, Ifsc_code);

			pstmt.setInt(4, ACC_no);

			pstmt.setInt(5, Pin);

			pstmt.setInt(6, Cust_id);

			pstmt.setString(7, Customer_name);

			pstmt.setInt(8, Balance);

			pstmt.setString(9, Email);

			pstmt.setLong(10, Phone);

			int x = pstmt.executeUpdate();

			if (x > 0) {

				resp.sendRedirect("/Banking_Application/RegisterSuccess.html");

			}

			else {

				resp.sendRedirect("/Banking_Application/RegisterFail.html");

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}
	}

}
