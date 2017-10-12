package filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.*;

public class MemPrivFilter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 該會員隱私設定】
		String memID = req.getParameter("memID");
		if(memID!=null){
			MemberService memSvc = new MemberService();
			MemberVO memVO = memSvc.getOneMember(Integer.valueOf(memID));
			Integer memPriv = memVO.getMemPriv();
			// 【從 隱私設定或好友關係判斷是否對外開放資料查詢】
			HttpSession session = req.getSession();
			//0不開放1好友2公開
			if(memPriv==0){
				session.setAttribute("guestPriv", 0);
				chain.doFilter(request, response);
			}else if(memPriv==1 && memID!=null){
				session.setAttribute("guestPriv", 1);
				chain.doFilter(request, response);
			}else if (memPriv==2){
				session.setAttribute("guestPriv", 1);
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}

		
	}
}