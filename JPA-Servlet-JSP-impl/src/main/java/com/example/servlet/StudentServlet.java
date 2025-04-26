package com.example.servlet;

import com.example.dao.StudentDAO;
import com.example.model.Student;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;

	@Override
	public void init() {
		studentDAO = new StudentDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null)
			action = "list";

		switch (action) {
		case "new":
			showNewForm(request, response);
			break;
		case "edit":
			showEditForm(request, response);
			break;
		case "delete":
			deleteStudent(request, response);
			break;
		default:
			listStudents(request, response);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("insert")) {
			insertStudent(request, response);
		} else if (action.equals("update")) {
			updateStudent(request, response);
		}
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Student> students = studentDAO.getAllStudents();
		request.setAttribute("students", students);
		request.getRequestDispatcher("student-list.jsp").forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("student-form.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Student student = studentDAO.getStudentById(id);
		request.setAttribute("student", student);
		request.getRequestDispatcher("student-form.jsp").forward(request, response);
	}

	private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		Student student = new Student(firstName, lastName, email);
		studentDAO.createStudent(student);
		response.sendRedirect("StudentServlet");
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		Student student = new Student(firstName, lastName, email);
		student.setId(id);
		studentDAO.updateStudent(student);
		response.sendRedirect("StudentServlet");
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		studentDAO.deleteStudent(id);
		response.sendRedirect("StudentServlet");
	}

}