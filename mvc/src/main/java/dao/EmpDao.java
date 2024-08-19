package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.EmpBean;

public class EmpDao {
	private Connection connection;

	public EmpDao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = System.getenv("DB_URL");
		String user = System.getenv("DB_USER");
		String password = System.getenv("DB_PASSWORD");
		connection = DriverManager.getConnection(url, user, password);
	}

	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public EmpBean getEmpDataById(int empId) throws SQLException {
		EmpBean bean = null;
		PreparedStatement pstatment = null;
		ResultSet rs = null;
		try {
			String sql = "select * from employee where employee_id = ?";
			pstatment = connection.prepareStatement(sql);
			pstatment.setInt(1, empId);
			rs = pstatment.executeQuery();

			if (rs.next()) {
				bean = new EmpBean();
				bean.setId(rs.getInt("employee_id"));
				bean.setName(rs.getString("employee_name"));
				bean.setAddress(rs.getString("employee_address"));
				bean.setAge(rs.getInt("employee_age"));
				bean.setMail(rs.getString("employee_mail"));
			}
			rs.close();
		} finally {
			pstatment.close();
		}
		return bean;
	}
	

	public ArrayList<EmpBean> getEmpDataByAge(int age1, int age2) throws SQLException {
		ArrayList<EmpBean> empData = null;
		PreparedStatement pstatment = null;
		ResultSet rs = null;
		try {
			String sql = "select * from employee where employee_age between ? and ?";
			pstatment = connection.prepareStatement(sql);
			pstatment.setInt(1, age1);
			pstatment.setInt(2, age2);
			rs = pstatment.executeQuery();
			empData = new ArrayList<EmpBean>();

			while (rs.next()) {
				EmpBean bean = new EmpBean();
				bean = new EmpBean();
				bean.setId(rs.getInt("employee_id"));
				bean.setName(rs.getString("employee_name"));
				bean.setAddress(rs.getString("employee_address"));
				bean.setAge(rs.getInt("employee_age"));
				bean.setMail(rs.getString("employee_mail"));
				empData.add(bean);
			}
			rs.close();
		} finally {
			pstatment.close();
		}

		return empData;
	}


}
