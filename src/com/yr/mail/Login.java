package com.yr.mail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.util.ConnectionUtil;

public class Login extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String account = request.getParameter("account"); // 获取页面输入的参数,用来进行查询是否有权限操作
			String password = request.getParameter("pass");
			request.getSession().setAttribute("account", account); // 将值存入session
			request.getSession().setAttribute("pass", password);
			
			Connection conn=(Connection) ConnectionUtil.getConn();
			String sql = "select * from custerm where account = ? and password = ?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			String acc = "";
			String pass = "";
			Integer status = null;
			while(rs.next()){
				acc = rs.getString("account");
				pass = rs.getString("password");
				status = rs.getInt("status");
			}
			if(account.equals(acc) && password.equals(pass)){
				if(status.equals(1)){ // 激活状态
					resp.sendRedirect("success.jsp");
				}else{
					resp.sendRedirect("waio.jsp");
				}
			}else{
				request.setAttribute("error", "用户名或密码错误");
				resp.sendRedirect("login.jsp");
			}
		} catch (SQLException e) {
			resp.sendRedirect("login.jsp");
			e.printStackTrace();
		}
	}
}
