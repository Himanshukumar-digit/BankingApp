package com.digit.banking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/changePassword")
public class ChangePassword extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private ServletRequest session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int prevPin = Integer.parseInt(req.getParameter("prevPin"));

		int newPin = Integer.parseInt(req.getParameter("newPin"));

		int cnfrmPin = Integer.parseInt(req.getParameter("cnfrmPin"));

		HttpSession session = req.getSession();

		String url = "jdbc:mysql://localhost:3306/banking";

		String user = "root";

		String pwd = "root";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pwd);

			if (newPin == cnfrmPin) {

				pstmt = con.prepareStatement("update Register set Pin = ? where ACC_No = ?");

				pstmt.setInt(1, newPin);

				pstmt.setInt(2, (int) session.getAttribute("ACC_No"));

				pstmt.executeUpdate();

				resp.sendRedirect("/MvcBankApp/ChangePassword.jsp");

			} else {

				resp.sendRedirect("/MvcBankApp/Home.jsp");

			}

		} catch (Exception e) {

			// TODO: handle exception

			e.printStackTrace();

//            writer.write();

		}

	}
}
