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

public class Waio extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String account = req.getParameter("account");
		String email = req.getParameter("email");
		String smtp = "smtp.126.com";// smtp服务器
		String from = "tzh_java@126.com";// 邮件显示名称
		String copyto = "";// 抄送人邮件地址
		String subject = "账号激活";// 邮件标题
		String content = "点击下面链接激活账号,一天内有效,请尽快激活！</br></br><a href='http://192.168.1.86:8080/Mail/mailLogin?account="+account+"'>激活账号</a></br></br><a hraf='http://192.168.1.86:8080/Mail/waioMa?account="+account+"&email="+email+"'>重新发送激活码</a>";
		String username = "tzh_java@126.com";// 发件人真实的账户名
		String password ="tzz123";// 发件人密码
		
		try {
			Connection conn=(Connection) ConnectionUtil.getConn();
			// 在链接失效后如果时间不改那么便永远激活不了(因为sql中时间没有改变而现实生活中时间一直都在改变)
			String sql = "update custerm set time = ? where account = ?"; // 修改时间
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			Timestamp time = new Timestamp(System.currentTimeMillis()); 
			ps.setTimestamp(1, time);
			ps.setString(2, account);
			ps.execute();
			if(account != null && !account.equals("")){
				MailTest.sendAndCc(smtp, from, email, copyto, subject, content, username, password);
				resp.sendRedirect("login.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			Connection conn=(Connection) ConnectionUtil.getConn();
			String account = req.getParameter("account");
			String email = req.getParameter("email");
			String sql = "select * from custerm where account = ?";
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, account);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(account != null && account.equals("")){
					String status = rs.getString("status");
					String time = rs.getString("time");
					if(status.equals(0)){
						//没激活
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						java.util.Date date = sdf.parse(time);
						long  s1 = date.getTime();//将时间转为毫秒
						long s2 = System.currentTimeMillis();//得到当前的毫秒
						long  day = (s2-s1)/1000/60/60/24;
		                //验证链接是否过期 
		                if(day > 1){ // 如果超过一天就过期
		                	req.setAttribute("account", account);
		                	req.setAttribute("email", email);
		                	req.getRequestDispatcher("jihuo.jsp").forward(req, resp);
		                } else {
		                	resp.sendRedirect("login.jsp");
		                }  
					}else {
						resp.sendRedirect("succ.jsp");
		            } 
				}else{
					resp.sendRedirect("errorNot.jsp");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
