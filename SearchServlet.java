package com.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/searchBook")
public class SearchServlet extends HttpServlet {
	private static final String query = "SELECT ID, BOOKNAME, BOOKEDITION, BOOKPRICE FROM BOOKDATA WHERE BOOKNAME LIKE ? AND BOOKEDITION LIKE ?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");

        // Use "%" if fields are empty
        if (bookName == null || bookName.trim().isEmpty()) {
            bookName = "%";
        } else {
            bookName = "%" + bookName.trim() + "%";
        }

        if (bookEdition == null || bookEdition.trim().isEmpty()) {
            bookEdition = "%";
        } else {
            bookEdition = "%" + bookEdition.trim() + "%";
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "123456789");
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, bookName);
            ps.setString(2, bookEdition);
            ResultSet rs = ps.executeQuery();

            pw.println("<h2 class='text-center'>Search Results</h2>");
            pw.println("<table border='1' align='center'>");
            pw.println("<tr>");
            pw.println("<th>ID</th>");
            pw.println("<th>Name</th>");
            pw.println("<th>Edition</th>");
            pw.println("<th>Price</th>");
            pw.println("<th>Edit</th>");
            pw.println("<th>Delete</th>");
            pw.println("</tr>");

            boolean found = false;
            while (rs.next()) {
                found = true;
                pw.println("<tr>");
                pw.println("<td>" + rs.getInt(1) + "</td>");
                pw.println("<td>" + rs.getString(2) + "</td>");
                pw.println("<td>" + rs.getString(3) + "</td>");
                pw.println("<td>" + rs.getFloat(4) + "</td>");
                pw.println("<td><a href='editScreen?id=" + rs.getInt(1) + "'>Edit</a></td>");
                pw.println("<td><a href='deleteurl?id=" + rs.getInt(1) + "'>Delete</a></td>");
                pw.println("</tr>");
            }

            if (!found) {
                pw.println("<tr><td colspan='6' style='text-align:center;'>No books matched the search.</td></tr>");
            }

            pw.println("</table>");
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h2>" + se.getMessage() + "</h2>");
        }
        pw.println("<br><a href='search.html'>Back to Search</a>");
        pw.println("<br><a href='home.html'>Home</a>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }
}