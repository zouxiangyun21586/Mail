package com.yr.mail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.util.ConnectionUtil;
import com.yr.util.MailTest;

public class MailLogin extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String email = request.getParameter("email");
		try {
			Connection conn=(Connection) ConnectionUtil.getConn();
			String selsql = "select * from custerm where account = ?";
			PreparedStatement pre=(PreparedStatement) conn.prepareStatement(selsql);
			pre.setString(1, account);
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				if(account != null && !account.equals("")){
					String status = rs.getString("status");
					String time = rs.getString("time");
					if(status.equals("0")){
						//没激活
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						java.util.Date date = sdf.parse(time);
						long  s1 = date.getTime();//将时间转为毫秒
						long s2 = System.currentTimeMillis();//得到当前的毫秒
						long  day = (s2-s1)/1000/60/60/24;
		                //验证链接是否过期 
		                if(day > 1){ // 如果超过一天就过期
		                	request.setAttribute("account", account);
		                	request.setAttribute("email", email);
		                	request.getRequestDispatcher("jihuo.jsp").forward(request, response);
		                } else {
		                	String sql = "update custerm set status = ? where account = ?";
		        			PreparedStatement  ps = (PreparedStatement) conn.prepareStatement(sql);
		        			ps.setInt(1, 1);
		        			ps.setString(2, account);
		        			ps.execute();
		        			response.sendRedirect("succ.jsp");
		                } 
					}
				}else{
					response.sendRedirect("errorNot.jsp");
				}
			}
		} catch (Exception e) {
			request.setAttribute("account", account);
			request.setAttribute("email", email);
			response.sendRedirect("error.jsp");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String account = req.getParameter("account");
		String email = req.getParameter("email");
		String password1 = req.getParameter("password1");
		String password2 = req.getParameter("password2");
		
		String smtp = "smtp.126.com";// smtp服务器
		String from = "tzh_java@126.com";// 邮件显示名称
		String copyto = "";// 抄送人邮件地址
		String subject = "账号激活";// 邮件标题
		String content = "点击下面链接激活账号,一天内有效,请尽快激活！</br></br><a href='http://192.168.1.86:8080/Mail/mailLogin?account="+account+"'>激活账号</a></br></br><a href='http://192.168.1.86:8080/Mail/waioMa?account="+account+"&email="+email+"'>重新发送激活码</a>";
		String username = "tzh_java@126.com";// 发件人真实的账户名
		String password ="tzz123";// 发件人密码
		try {
			if(!name.equals("") && !account.equals("") && !password1.equals("") && !password2.equals("")){
				if(name != null && account != null && password1 != null && password2 != null){
					if(password1.equals(password2)){
						Connection conn=(Connection) ConnectionUtil.getConn();
						String sql = "insert into custerm(name,account,password,email,time) values(?,?,?,?,?)";
						PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
						ps.setString(1, name);
						ps.setString(2, account);
						ps.setString(3, password1);
						ps.setString(4, email);
						Timestamp time = new Timestamp(System.currentTimeMillis()); 
						ps.setTimestamp(5, time);
						ps.execute();
						MailTest.sendAndCc(smtp, from, email, copyto, subject, content, username, password);
						resp.sendRedirect("login.jsp");
					}else{
						req.setAttribute("error", "两次密码不一致");
						resp.sendRedirect("login.jsp");
					}
				}
			}else{
				req.setAttribute("errot", "请按照规范正确输入.");
				resp.sendRedirect("login.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
