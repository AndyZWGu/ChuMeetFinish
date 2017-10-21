package com.gen.tool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.*;
import javax.sql.DataSource;

import org.jsoup.Jsoup;
import com.act.actMem.model.ActMemVO;

public class actCodeTrans {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
		
	public static String whoRU(int memID){
		String memName = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select memName from member where memID=?");
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();
			rs.next();
			memName=rs.getString("memName");
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memName;
	}
		
	public static Integer actMemCount(int actID){
		int memCount;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) as count from actmem where actid=? and actmemstatus<3");
			pstmt.setInt(1, actID);
			rs = pstmt.executeQuery();
			rs.next();
			memCount=rs.getInt("count");
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memCount;
	}
	
public static String poitoString(int POIID){
		String Str=null;
			switch(POIID){
case 1:Str="音樂";break;
case 2:Str="戲劇";break;
case 3:Str="舞蹈";break;
case 4:Str="親子";break;
case 5:Str="獨立音樂";break;
case 6:Str="展覽";break;
case 7:Str="講座";break;
case 8:Str="電影";break;
case 9:Str="運動";break;
case 10:Str="手作";break;
case 11:Str="綜藝";break;
case 12:Str="學習";break;
case 13:Str="競賽";break;
case 14:Str="徵選";break;
case 15:Str="其他";break;
case 16:Str="未知分類";break;
case 17:Str="演唱會";break;
case 18:Str="餐聚";break;
case 19:Str="研習課程";break;
case 20:Str="藝文";break;
case 21:Str="電競";break;
case 22:Str="線上活動";break;
case 23:Str="戶外";break;
case 24:Str="寵物";break;
case 25:Str="讀書會";break;
default: 
    System.out.println("????"); 
}
return Str;
}
	
public static Integer strtoPOI(String poic){
	Integer poiID=1;

		switch(poic){
		case "音樂":poiID=1;break;
		case "戲劇":poiID=2;break;
		case "舞蹈":poiID=3;break;
		case "親子":poiID=4;break;
		case "獨立音樂":poiID=5;break;
		case "展覽":poiID=6;break;
		case "講座":poiID=7;break;
		case "電影":poiID=8;break;
		case "運動":poiID=9;break;
		case "手作":poiID=10;break;
		case "綜藝":poiID=11;break;
		case "學習":poiID=12;break;
		case "競賽":poiID=13;break;
		case "徵選":poiID=14;break;
		case "其他":poiID=15;break;
		case "未知分類":poiID=16;break;
		case "演唱會":poiID=17;break;
		case "餐聚":poiID=18;break;
		case "研習課程":poiID=19;break;
		case "藝文":poiID=20;break;
		case "電競":poiID=21;break;
		case "線上活動":poiID=22;break;
		case "戶外":poiID=23;break;
		case "寵物":poiID=24;break;
		case "讀書會":poiID=25;break;
		default: 
		    System.out.println("????"); 
		}
		return poiID;
		}
			
	
	
	
	
	
	
	
	public static String actStattoString(int actStatID){
			String Str=null;
				switch(actStatID){
				case 1:Str="籌辦中";break;
				case 2:
					Str="進行中";
					break;
				case 3:
					Str="已完成";
					break;
				case 4:
					Str="停權";
					break;
				case 5:
					Str="暫停中";
					break;
				case 6:
					Str="已取消";
					break;
	            default: 
	                System.out.println("????"); 
				}
			return Str;
		}

	public static String actPosttoString(int postNo){
				String Str=null;
				switch(postNo){
						case 999:Str="線上";break;
						case 100:Str="臺北市中正區";break;
						case 103:Str="臺北市大同區";break;
						case 104:Str="臺北市中山區";break;
						case 105:Str="臺北市松山區";break;
						case 106:Str="臺北市大安區";break;
						case 108:Str="臺北市萬華區";break;
						case 110:Str="臺北市信義區";break;
						case 111:Str="臺北市士林區";break;
						case 112:Str="臺北市北投區";break;
						case 114:Str="臺北市內湖區";break;
						case 115:Str="臺北市南港區";break;
						case 116:Str="臺北市文山區";break;
						case 200:Str="基隆市仁愛區";break;
						case 201:Str="基隆市信義區";break;
						case 202:Str="基隆市中正區";break;
						case 203:Str="基隆市中山區";break;
						case 204:Str="基隆市安樂區";break;
						case 205:Str="基隆市暖暖區";break;
						case 206:Str="基隆市七堵區";break;
						case 207:Str="新北市萬里區";break;
						case 208:Str="新北市金山區";break;
						case 220:Str="新北市板橋區";break;
						case 221:Str="新北市汐止區";break;
						case 222:Str="新北市深坑區";break;
						case 223:Str="新北市石碇區";break;
						case 224:Str="新北市瑞芳區";break;
						case 226:Str="新北市平溪區";break;
						case 227:Str="新北市雙溪區";break;
						case 228:Str="新北市貢寮區";break;
						case 231:Str="新北市新店區";break;
						case 232:Str="新北市坪林區";break;
						case 233:Str="新北市烏來區";break;
						case 234:Str="新北市永和區";break;
						case 235:Str="新北市中和區";break;
						case 236:Str="新北市土城區";break;
						case 237:Str="新北市三峽區";break;
						case 238:Str="新北市樹林區";break;
						case 239:Str="新北市鶯歌區";break;
						case 241:Str="新北市三重區";break;
						case 242:Str="新北市新莊區";break;
						case 243:Str="新北市泰山區";break;
						case 244:Str="新北市林口區";break;
						case 247:Str="新北市蘆洲區";break;
						case 248:Str="新北市五股區";break;
						case 249:Str="新北市八里區";break;
						case 251:Str="新北市淡水區";break;
						case 252:Str="新北市三芝區";break;
						case 253:Str="新北市石門區";break;
						case 260:Str="宜蘭縣宜蘭";break;
						case 261:Str="宜蘭縣頭城";break;
						case 262:Str="宜蘭縣礁溪";break;
						case 263:Str="宜蘭縣壯圍";break;
						case 264:Str="宜蘭縣員山";break;
						case 265:Str="宜蘭縣羅東";break;
						case 266:Str="宜蘭縣三星";break;
						case 267:Str="宜蘭縣大同";break;
						case 268:Str="宜蘭縣五結";break;
						case 269:Str="宜蘭縣冬山";break;
						case 270:Str="宜蘭縣蘇澳";break;
						case 272:Str="宜蘭縣南澳";break;
						case 290:Str="宜蘭縣釣魚台列嶼";break;
						case 300:Str="宜蘭縣新竹市";break;
						case 302:Str="新竹縣竹北";break;
						case 303:Str="新竹縣湖口";break;
						case 304:Str="新竹縣新豐";break;
						case 305:Str="新竹縣新埔";break;
						case 306:Str="新竹縣關西";break;
						case 307:Str="新竹縣芎林";break;
						case 308:Str="新竹縣寶山";break;
						case 310:Str="新竹縣竹東";break;
						case 311:Str="新竹縣五峰";break;
						case 312:Str="新竹縣橫山";break;
						case 313:Str="新竹縣尖石";break;
						case 314:Str="新竹縣北埔";break;
						case 315:Str="新竹縣峨眉";break;
						case 320:Str="桃園縣中壢";break;
						case 324:Str="桃園縣平鎮";break;
						case 325:Str="桃園縣龍潭";break;
						case 326:Str="桃園縣楊梅";break;
						case 327:Str="桃園縣新屋";break;
						case 328:Str="桃園縣觀音";break;
						case 330:Str="桃園縣桃園";break;
						case 333:Str="桃園縣龜山";break;
						case 334:Str="桃園縣八德";break;
						case 335:Str="桃園縣大溪";break;
						case 336:Str="桃園縣復興";break;
						case 337:Str="桃園縣大園";break;
						case 338:Str="桃園縣蘆竹";break;
						case 350:Str="苗栗縣竹南";break;
						case 351:Str="苗栗縣頭份";break;
						case 352:Str="苗栗縣三灣";break;
						case 353:Str="苗栗縣南庄";break;
						case 354:Str="苗栗縣獅潭";break;
						case 356:Str="苗栗縣後龍";break;
						case 357:Str="苗栗縣通霄";break;
						case 358:Str="苗栗縣苑裡";break;
						case 360:Str="苗栗縣苗栗";break;
						case 361:Str="苗栗縣造橋";break;
						case 362:Str="苗栗縣頭屋";break;
						case 363:Str="苗栗縣公館";break;
						case 364:Str="苗栗縣大湖";break;
						case 365:Str="苗栗縣泰安";break;
						case 366:Str="苗栗縣銅鑼";break;
						case 367:Str="苗栗縣三義";break;
						case 368:Str="苗栗縣西湖";break;
						case 369:Str="苗栗縣卓蘭";break;
						case 400:Str="臺中市中區";break;
						case 401:Str="臺中市東區";break;
						case 402:Str="臺中市南區";break;
						case 403:Str="臺中市西區";break;
						case 404:Str="臺中市北區";break;
						case 406:Str="臺中市北屯區";break;
						case 407:Str="臺中市西屯區";break;
						case 408:Str="臺中市南屯區";break;
						case 411:Str="臺中市太平區";break;
						case 412:Str="臺中市大里區";break;
						case 413:Str="臺中市霧峰區";break;
						case 414:Str="臺中市烏日區";break;
						case 420:Str="臺中市豐原區";break;
						case 421:Str="臺中市后里區";break;
						case 422:Str="臺中市石岡區";break;
						case 423:Str="臺中市東勢區";break;
						case 424:Str="臺中市和平區";break;
						case 426:Str="臺中市新社區";break;
						case 427:Str="臺中市潭子區";break;
						case 428:Str="臺中市大雅區";break;
						case 429:Str="臺中市神岡區";break;
						case 432:Str="臺中市大肚區";break;
						case 433:Str="臺中市沙鹿區";break;
						case 434:Str="臺中市龍井區";break;
						case 435:Str="臺中市梧棲區";break;
						case 436:Str="臺中市清水區";break;
						case 437:Str="臺中市大甲區";break;
						case 438:Str="臺中市外埔區";break;
						case 439:Str="臺中市大安區";break;
						case 500:Str="彰化縣彰化";break;
						case 502:Str="彰化縣芬園";break;
						case 503:Str="彰化縣花壇";break;
						case 504:Str="彰化縣秀水";break;
						case 505:Str="彰化縣鹿港";break;
						case 506:Str="彰化縣福興";break;
						case 507:Str="彰化縣線西";break;
						case 508:Str="彰化縣和美";break;
						case 509:Str="彰化縣伸港";break;
						case 510:Str="彰化縣員林";break;
						case 511:Str="彰化縣社頭";break;
						case 512:Str="彰化縣永靖";break;
						case 513:Str="彰化縣埔心";break;
						case 514:Str="彰化縣溪湖";break;
						case 515:Str="彰化縣大村";break;
						case 516:Str="彰化縣埔鹽";break;
						case 520:Str="彰化縣田中";break;
						case 521:Str="彰化縣北斗";break;
						case 522:Str="彰化縣田尾";break;
						case 523:Str="彰化縣埤頭";break;
						case 524:Str="彰化縣溪州";break;
						case 525:Str="彰化縣竹塘";break;
						case 526:Str="彰化縣二林";break;
						case 527:Str="彰化縣大城";break;
						case 528:Str="彰化縣芳苑";break;
						case 530:Str="彰化縣二水";break;
						case 540:Str="南投縣南投";break;
						case 541:Str="南投縣中寮";break;
						case 542:Str="南投縣草屯";break;
						case 544:Str="南投縣國姓";break;
						case 545:Str="南投縣埔里";break;
						case 546:Str="南投縣仁愛";break;
						case 551:Str="南投縣名間";break;
						case 552:Str="南投縣集集";break;
						case 553:Str="南投縣水里";break;
						case 555:Str="南投縣魚池";break;
						case 556:Str="南投縣信義";break;
						case 557:Str="南投縣竹山";break;
						case 558:Str="南投縣鹿谷";break;
						case 600:Str="南投縣嘉義市";break;
						case 602:Str="嘉義縣番路";break;
						case 603:Str="嘉義縣梅山";break;
						case 604:Str="嘉義縣竹崎";break;
						case 605:Str="嘉義縣阿里山";break;
						case 606:Str="嘉義縣中埔";break;
						case 607:Str="嘉義縣大埔";break;
						case 608:Str="嘉義縣水上";break;
						case 611:Str="嘉義縣鹿草";break;
						case 612:Str="嘉義縣太保";break;
						case 613:Str="嘉義縣朴子";break;
						case 614:Str="嘉義縣東石";break;
						case 615:Str="嘉義縣六腳";break;
						case 616:Str="嘉義縣新港";break;
						case 621:Str="嘉義縣民雄";break;
						case 622:Str="嘉義縣大林";break;
						case 623:Str="嘉義縣溪口";break;
						case 624:Str="嘉義縣義竹";break;
						case 625:Str="嘉義縣布袋";break;
						case 630:Str="雲林縣斗南";break;
						case 631:Str="雲林縣大埤";break;
						case 632:Str="雲林縣虎尾";break;
						case 633:Str="雲林縣土庫";break;
						case 634:Str="雲林縣褒忠";break;
						case 635:Str="雲林縣東勢";break;
						case 636:Str="雲林縣臺西";break;
						case 637:Str="雲林縣崙背";break;
						case 638:Str="雲林縣麥寮";break;
						case 640:Str="雲林縣斗六";break;
						case 643:Str="雲林縣林內";break;
						case 646:Str="雲林縣古坑";break;
						case 647:Str="雲林縣莿桐";break;
						case 648:Str="雲林縣西螺";break;
						case 649:Str="雲林縣二崙";break;
						case 651:Str="雲林縣北港";break;
						case 652:Str="雲林縣水林";break;
						case 653:Str="雲林縣口湖";break;
						case 654:Str="雲林縣四湖";break;
						case 655:Str="雲林縣元長";break;
						case 700:Str="臺南市中西區";break;
						case 701:Str="臺南市東區";break;
						case 702:Str="臺南市南區";break;
						case 704:Str="臺南市北區";break;
						case 708:Str="臺南市安平區";break;
						case 709:Str="臺南市安南區";break;
						case 710:Str="臺南市永康區";break;
						case 711:Str="臺南市歸仁區";break;
						case 712:Str="臺南市新化區";break;
						case 713:Str="臺南市左鎮區";break;
						case 714:Str="臺南市玉井區";break;
						case 715:Str="臺南市楠西區";break;
						case 716:Str="臺南市南化區";break;
						case 717:Str="臺南市仁德區";break;
						case 718:Str="臺南市關廟區";break;
						case 719:Str="臺南市龍崎區";break;
						case 720:Str="臺南市官田區";break;
						case 721:Str="臺南市麻豆區";break;
						case 722:Str="臺南市佳里區";break;
						case 723:Str="臺南市西港區";break;
						case 724:Str="臺南市七股區";break;
						case 725:Str="臺南市將軍區";break;
						case 726:Str="臺南市學甲區";break;
						case 727:Str="臺南市北門區";break;
						case 730:Str="臺南市新營區";break;
						case 731:Str="臺南市後壁區";break;
						case 732:Str="臺南市白河區";break;
						case 733:Str="臺南市東山區";break;
						case 734:Str="臺南市六甲區";break;
						case 735:Str="臺南市下營區";break;
						case 736:Str="臺南市柳營區";break;
						case 737:Str="臺南市鹽水區";break;
						case 741:Str="臺南市善化區";break;
						case 742:Str="臺南市大內區";break;
						case 743:Str="臺南市山上區";break;
						case 744:Str="臺南市新市區";break;
						case 745:Str="臺南市安定區";break;
						case 800:Str="高雄市新興區";break;
						case 801:Str="高雄市前金區";break;
						case 802:Str="高雄市苓雅區";break;
						case 803:Str="高雄市鹽埕區";break;
						case 804:Str="高雄市鼓山區";break;
						case 805:Str="高雄市旗津區";break;
						case 806:Str="高雄市前鎮區";break;
						case 807:Str="高雄市三民區";break;
						case 811:Str="高雄市楠梓區";break;
						case 812:Str="高雄市小港區";break;
						case 813:Str="高雄市左營區";break;
						case 814:Str="高雄市仁武區";break;
						case 815:Str="高雄市大社區";break;
						case 820:Str="高雄市岡山區";break;
						case 821:Str="高雄市路竹區";break;
						case 822:Str="高雄市阿蓮區";break;
						case 823:Str="高雄市田寮區";break;
						case 824:Str="高雄市燕巢區";break;
						case 825:Str="高雄市橋頭區";break;
						case 826:Str="高雄市梓官區";break;
						case 827:Str="高雄市彌陀區";break;
						case 828:Str="高雄市永安區";break;
						case 829:Str="高雄市湖內區";break;
						case 830:Str="高雄市鳳山區";break;
						case 831:Str="高雄市大寮區";break;
						case 832:Str="高雄市林園區";break;
						case 833:Str="高雄市鳥松區";break;
						case 840:Str="高雄市大樹區";break;
						case 842:Str="高雄市旗山區";break;
						case 843:Str="高雄市美濃區";break;
						case 844:Str="高雄市六龜區";break;
						case 845:Str="高雄市內門區";break;
						case 846:Str="高雄市杉林區";break;
						case 847:Str="高雄市甲仙區";break;
						case 848:Str="高雄市桃源區";break;
						case 849:Str="高雄市那瑪夏區";break;
						case 851:Str="高雄市茂林區";break;
						case 852:Str="高雄市茄萣區";break;
						case 817:Str="南海諸島東沙";break;
						case 819:Str="南海諸島南沙";break;
						case 880:Str="澎湖縣馬公";break;
						case 881:Str="澎湖縣西嶼";break;
						case 882:Str="澎湖縣望安";break;
						case 883:Str="澎湖縣七美";break;
						case 884:Str="澎湖縣白沙";break;
						case 885:Str="澎湖縣湖西";break;
						case 900:Str="屏東縣屏東";break;
						case 901:Str="屏東縣三地門";break;
						case 902:Str="屏東縣霧臺";break;
						case 903:Str="屏東縣瑪家";break;
						case 904:Str="屏東縣九如";break;
						case 905:Str="屏東縣里港";break;
						case 906:Str="屏東縣高樹";break;
						case 907:Str="屏東縣盬埔";break;
						case 908:Str="屏東縣長治";break;
						case 909:Str="屏東縣麟洛";break;
						case 911:Str="屏東縣竹田";break;
						case 912:Str="屏東縣內埔";break;
						case 913:Str="屏東縣萬丹";break;
						case 920:Str="屏東縣潮州";break;
						case 921:Str="屏東縣泰武";break;
						case 922:Str="屏東縣來義";break;
			            default: 
			                System.out.println("locwat????"); 
						}
					return Str;
				}
	
//	去除HTML標籤
    public static String delHTMLTag(String htmlStr){ 
//         String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定義script 
//         String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定義CSS 
         String regEx_html="<[^>]+>"; //定義HTML
         
//         Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
//         Matcher m_script=p_script.matcher(htmlStr); 
//         htmlStr=m_script.replaceAll(""); //-JS 
//         
//         Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
//         Matcher m_style=p_style.matcher(htmlStr); 
//         htmlStr=m_style.replaceAll(""); //-CSS
         
         Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
         Matcher m_html=p_html.matcher(htmlStr); 
         htmlStr=m_html.replaceAll(" "); //-HTML 
         htmlStr=m_html.replaceAll(""); //-HTML
         htmlStr=m_html.replaceAll("\n"); //-HTML 
         htmlStr=m_html.replaceAll("\r"); //-HTML
         int limit=170;
         if(htmlStr.trim().length()<170) {
        	 limit=htmlStr.trim().length();
         }
//         System.out.println("limit="+limit);
        return htmlStr.trim().substring(0,limit); //return
     } 
	
    public static String delHTMLTag(String htmlStr, Integer limit){ 
//      String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定義script 
//      String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定義CSS 
      String regEx_html="<[^>]+>"; //定義HTML
      
//      Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
//      Matcher m_script=p_script.matcher(htmlStr); 
//      htmlStr=m_script.replaceAll(""); //-JS 
//      
//      Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
//      Matcher m_style=p_style.matcher(htmlStr); 
//      htmlStr=m_style.replaceAll(""); //-CSS
      
      Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
      Matcher m_html=p_html.matcher(htmlStr); 
      htmlStr=m_html.replaceAll(" "); //-HTML 
      htmlStr=m_html.replaceAll(""); //-HTML
      htmlStr=m_html.replaceAll("\n"); //-HTML 
      htmlStr=m_html.replaceAll("\r"); //-HTML

      if(htmlStr.trim().length()<limit) {
     	 limit=htmlStr.trim().length();
      }
//      System.out.println("limit="+limit);
     return htmlStr.trim().substring(0,limit); //return
  } 
			
}
