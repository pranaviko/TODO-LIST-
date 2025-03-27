package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Register;
import dao.ToDoDAO;
import dao.ToDoDAOImpl;

@WebServlet("/RegisterServlet") // Ensure you're using Servlet 3.0+
public class RegisterServlet extends HttpServlet {

 private static final long serialVersionUID = 1L; // Added for serialization

 protected void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
  response.setContentType("text/html");

  PrintWriter out = response.getWriter();

  try {
   String fname = request.getParameter("fname").trim();
   String lname = request.getParameter("lname").trim();
   String email = request.getParameter("email").trim();
   String pass = request.getParameter("pass").trim();
   long mobile = Long.parseLong(request.getParameter("mobile").trim());
   String address = request.getParameter("address").trim();

   Register reg = new Register(0, fname, lname, email, pass, mobile, address);
   ToDoDAO dao = ToDoDAOImpl.getInstance();
   int regId = dao.register(reg);

   if (regId > 0) {
    response.sendRedirect("./Login.jsp");
   } else {
    response.sendRedirect("./Register.html");
   }

  } catch (Exception e) {
   e.printStackTrace();
   out.println("<h3>Registration failed due to an error. Please try again.</h3>");
  } finally {
   out.close();
  }
 }
}