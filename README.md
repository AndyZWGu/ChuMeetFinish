# ChuMeet
![alt text](https://github.com/AndyZWGu/ChuMeet/blob/master/WebContent/HTML/assets/LOGO/ChuMeet_NavLogo_25.png "Logo 標題文字範例一")

## Build by Eclipse,For BA103G2 Team share;


# 1.開發說明

    1.統一資料庫帳密為帳號**BA103G2**,密碼**a123**方便整合
 
    2.統一Tomcat的Context.xml設定TestDB為**BA103G2DB**

    3.先用您的GitHub帳號Fork一份到您那兒~,編碼統一為**UTF-8**
 
    4.每次作業請先Pull回自己的最新版本,完成功能時提交commit and push到你自己的帳號專案中
 
    5.在自己的GitHub專案用Pull request提交到我這裡,我做總整理,ok就會合併～感謝～
 
    6.盡量在現有程式中加寫檔案,不涉及刪除原有檔案或改寫共用檔案,地址轉換統一管理在WEB-INF下的web.xml之中加寫
 
    7.模板是Template.jsp這一隻,可以放在任何地方,根據你要的內容撰寫在放在自己的資料夾下面
 
    8.開發順序JSP=>對應web.xml轉址=>Servlet=>forward或sendRedirect轉址=>JSP,了蓋謀～
 
    9.複合查詢(1對1,1對多,多對1)詳見ch_0403,import其他VO達到Join效果
 
    10.監聽與攔截待補完,框架Spring,Struct,Hibernate待補完
 
# 2.目錄結構

    ChuMeetWebsite/
    │
    ├── WebContent/
    │   ├── WEB-INF      -註冊路徑用,注意別打錯,會報錯。
    │   ├── HTML         -只存放網頁資料,即時更新對應最新的頁面內容
    │   ├── front-end    -只存放<前端>畫面用的__<JSP>__,取用資料全來自於HTML資料夾下
    │   ├── back-end     -只存放<後端>畫面用的__<JSP>__,取用資料全來自於HTML資料夾下
    │   └── Template.jsp -模板 
    │
    ├── src/
    │   ├── com/member/
    │   │            ├── controller -放我會員用的Servlet程式,對應Web.xml與JSP交互操作。
    │   │            ├── model      -放會員相關Model,依序為VO(DTO),DAO(Interface),JDBCDAO(測試類),DAO(Impl,JNDI類),Service(業務邏輯)。
    │   │            └── ........   -更多
    │   ├── com/act/
    │   │         ├── ...
    │   │         ├── ...
    │   │         └── ...
    │   │  
    │   └── com/club/
    │             ├── ...
    │             ├── ...
    │             └── ...
    │
    └─── README.md -說明文件
 
# 3.常見問題處理

 ## 配置
     1.jar檔該匯入的匯一匯
   
     2.編碼統一為UTF-8
   
     3.JSP頁面使用JSTL語法記得要引入才有效果**<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>**
     
     4.活動要先執行Uploads_JDBC上傳必要的基本圖片,再去爬蟲更多的活動資料來用

 ## 連線
     1.JDBC-檢查JDBC連線設定是否正確,檢查SQL指令是否正確,檢查欄位設定參數,問號<?>對應數量是否正確
  
     2.JNDI-檢查Tomcat下的Context.xml是否正確,檢查SQL指令是否正確,檢查欄位設定參數,問號<?>對應數量是否正確
  
     3.拜拜或求救大神~
    
 ## 路徑語法
     1.各種好用語法收錄在**tools.java**裡,**一定****一定****一定**多去看看,很重要所以說三次,別死在死胡同裡惹～時間寶貴！
 
     2.form表單action,腳本script,超連結href一律用**<%=request.getContextPath()%>/**開頭,注意這裡的斜線是有的！
   
     3.普通寫法,**沒/是從根目錄**起頭,**有/斜線是從當前該文件的目錄**起頭
 
     4.blob欄位在VO裡面一律宣告為Byte[],寫入與讀取流程為
     InputStream=>Byte[]<=>資料庫<=>ResultSet的getBinaryStream()<=InputStream
   
     5.上傳看範例ch04_FileUpload跟這個好的完整答案
     https://stackoverflow.com/questions/2422468/how-to-upload-files-to-server-using-jsp-servlet/2424824#2424824
     
     5.資料庫型別請確認正確,如正確卻沒有報錯,可能是長度不夠！！！！！！！！！
   
 
