package service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.EmpDao;
import model.EmpBean;

public class IdSearch {
	public void execute(HttpServletRequest request) throws Exception {
		EmpDao daoTest = null;
		String empId = request.getParameter("paramId");
		try {
			if (empId != null && !empId.isEmpty()) {
				daoTest = new EmpDao();
				EmpBean empBean = daoTest.getEmpDataById(Integer.parseInt(empId));

				if (empBean != null) {
					ArrayList<EmpBean> empList = new ArrayList<EmpBean>();
					empList.add(empBean);
					request.setAttribute("empList", empList);
				} else {
					request.setAttribute("message", "該当者はいません");
				}
			} else {
				//空欄で検索
				request.setAttribute("message", "検索IDを入力して下さい");
			}
		} finally {
			if (daoTest != null) {
				daoTest.close();
			}
		}
	}
}
