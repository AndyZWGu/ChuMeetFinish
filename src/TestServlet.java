/*
    代刚:   http://localhost:8081/SL314/PathServlet/1.txt?name1=peter1&name2=peter2
    莱猔種     (“1)猔種讽Τノ肂隔畖戈癟ゲ斗ㄏノ玡竚隔畖癸莱砞﹚
    猔種(“2)web.xmlず<url-pattern>琌<url-pattern>/PathServlet/*</url-pattern>                               
*/

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import com.member.model.*;

public class TestServlet extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
	  
	  //好友
	  FriendsService friSvc = new FriendsService();
	  //找一位好友
	  FriendsVO friendsVO = friSvc.getOneFriends(3, 1);
//	  System.out.println(friendsVO.getFriendType());
	  //找很多好友
	  List<FriendsVO> friendsVO2 = friSvc.getAllFriends(3);
	  for (FriendsVO list : friendsVO2) {
//		  System.out.println(list.getFriMem2());
	  }
	//好友數
			FriendsService friSvc2 = new FriendsService();
			 List<FriendsVO> friendsVO3 = friSvc.getAllFriends(8);
			 int friendsNum=0;
			  for (FriendsVO list : friendsVO3) {
				  if(list.getFriendType().contains("好友"))
					  friendsNum++;
//				  System.out.println(list.getFriMem2());
			  }
			  System.out.println(friendsNum);
	  
	  
	  
	  
	  doPost(req, res);
    }
	 
    
    public void doPost(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
 
    }
}
