package com.digit.banking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/transfer")
public class Transfer extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res1;
	private ResultSet res2;
	private ResultSet res3;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String Bank_name = req.getParameter("Bank_name");

		String Ifsc_code = req.getParameter("Ifsc_Code");

		int Cust_id = Integer.parseInt(req.getParameter("Cust_id"));

		int Sender_accno = Integer.parseInt(req.getParameter("sender_accno"));

		int Reciever_accno = Integer.parseInt(req.getParameter("reciever_accno"));

		String Reciever_Ifsc = req.getParameter("reciever_Ifsc");

		int Amount = Integer.parseInt(req.getParameter("amount"));

		String url = "jdbc:mysql://localhost:3306/banking";

		String user = "root";

		String pwd = "root";
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pwd);

			pstmt = con.prepareStatement("select * from Register where Cust_id=? and Ifsc_Code=? and ACC_No=?");

			pstmt.setInt(1, Cust_id);
			pstmt.setString(2, Ifsc_code);

			pstmt.setInt(3, Sender_accno);

			res1 = pstmt.executeQuery();

			if (res1.next() == true) {
				pstmt = con.prepareStatement("Select * from register where Ifsc_Code=? and ACC_No=? ");
				pstmt.setString(1, Reciever_Ifsc);
				pstmt.setInt(2, Reciever_accno);
				res2 = pstmt.executeQuery();

				if (res2.next() == true) {
					pstmt = con.prepareStatement("Select Balance From register where ACC_No=? ");
					pstmt.setInt(1, Sender_accno);
					res3 = pstmt.executeQuery();
					res3.next();
					int Balance = res3.getInt(1);
					if (Balance > Amount) {
						pstmt = con.prepareStatement("Update Register set Balance=Balance-? where ACC_No=? ");
						pstmt.setInt(1, Amount);
						pstmt.setInt(2, Sender_accno);
						int x1 = pstmt.executeUpdate();
						if (x1 > 0) {
							pstmt = con.prepareStatement("Update register set Balance = Balance+? where ACC_No=?");
							pstmt.setInt(1, Amount);
							pstmt.setInt(2, Reciever_accno);
							int x2 = pstmt.executeUpdate();
							if (x2 > 0) {
								pstmt = con.prepareStatement("Insert into transfers values(?,?,?,?,?,?,?,?)");
								pstmt.setInt(1, Cust_id);
								pstmt.setString(2, Bank_name);
								pstmt.setString(3, Ifsc_code);
								pstmt.setInt(4, Sender_accno);
								pstmt.setString(5, Reciever_Ifsc);

								pstmt.setInt(6, Reciever_accno);

								pstmt.setInt(7, Amount);

								Random r = new Random(System.currentTimeMillis());

								int t_id = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));

								pstmt.setInt(8, t_id);

								int x3 = pstmt.executeUpdate();
								if (x3 > 0) {
									resp.sendRedirect("/Banking_Application/transfersuccess.jsp");
								} else {
									resp.sendRedirect("/Banking_Application/fail.jsp");
								}

							} else {
								// x2
							}

						} else {
							// x1
						}

					}

				}
			}
		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

}
