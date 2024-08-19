package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AgeSearch;
import service.IdSearch;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request.setAttribute("message", "検索条件を入力して下さい");

		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/disp.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String searchBtn = request.getParameter("btn");
		String jsp;
		try {
			if (searchBtn != null && searchBtn.equals("IdSearch")) {
				IdSearch ids = new IdSearch();
				ids.execute(request);
				jsp = "/disp.jsp";
			}else if(searchBtn != null && searchBtn.equals("AgeSearch")){
				AgeSearch ages = new AgeSearch();
				ages.execute(request);
				jsp = "/disp.jsp";

			}else {
				request.setAttribute("errorMessage", "不正アクセスです");
				request.setAttribute("backAddress", "controller");
				jsp = "/error.jsp";
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "数値を入力して下さい");
			request.setAttribute("backAddress", "controller");
			jsp = "/error.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "JDBCのエラーです");
			request.setAttribute("backAddress", "controller");
			jsp = "/error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "エラーが発生しました");
			request.setAttribute("backAddress", "controller");
			jsp = "/error.jsp";
		}

		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

}
