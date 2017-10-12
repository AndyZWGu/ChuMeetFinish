<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%

%>
<!-- Head BEGIN -->

<head>
	<!-- 共用Header -->
  <c:import url="/front-end/head.jsp">
</c:import>
	<!-- 共用Header -->
  <!--  my styles  -->
  <!--@@@@@@@@@@@@@@@@@@@@@@@@@@ 自己的CSS用連結寫到這邊 @@@@@@@@@@@@@@@@@@@@@@@@@@@-->
  <!--!!!!!!!!!!!!!!!!!!!!!!!!!! 放在最後一行優先權越高 !!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
  <!--#################### 單頁CSS路徑統一放在/src/xxx/css/xxx.css ###########-->
  <!--%%%%%%%%%%%%%%%%%% 第一行可以刪掉，那是activity(也就是敏道的活動頁)專用的CSS %%%%%-->
  <link href="<%=request.getContextPath()%>/HTML/src/index/index-byAGu.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/HTML/assets/pages/css/slider.css" rel="stylesheet">
</head>
<!-- Head END -->

<!-- Body BEGIN -->
<body class="chumeet">
  <c:import url="/front-end/userHeader.jsp">
</c:import>

  <!-- Header Start -->
  <c:import url="/front-end/header.jsp">
</c:import>

  <!-- Header END -->

  <!--主頁面要修改的都在這下面-->
      <!--主頁面要修改的都在這下面-->
    <div class="page-slider margin-bottom-40">
        <div id="carousel-example-generic" class="carousel slide carousel-slider">
            <!-- Indicators -->
            <ol class="carousel-indicators carousel-indicators-frontend">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <!-- First slide -->
                <div class="item carousel-item-one active">
                    <div class="container">
                        <div class="carousel-position-gu text-uppercase text-center">
                            <h2 class="margin-bottom-20 animate-delay carousel-title-v5" data-animation="animated fadeInDown">
                                歡迎來到 <br/>
                                <span class="carousel-title-normal">ChuMeet</span>
                            </h2>
                            <p class="carousel-subtitle-v5 border-top-bottom margin-bottom-30" data-animation="animated fadeInDown">有趣＆好玩的社群平台</p><a class="btn btn-primary btn-lg" href="#" data-animation="animated fadeInUp">馬上加入我們</a>
                        </div>
                    </div>
                </div>


                <!-- Second slide -->
                <div class="item carousel-item-two">
                    <div class="container">
                        <div class="carousel-position-gu2">
                            <h2 class="animate-delay carousel-title-v6 text-uppercase" data-animation="animated fadeInDown">
                                認識新朋友?
                            </h2>
                            <p class="carousel-subtitle-v8 border-top-bottom margin-bottom-30" data-animation="animated fadeInDown">
                                來會員中心尋找&看揪友們動態吧!
                            </p>
                            <a class="btn btn-primary btn-lg" href="#" data-animation="animated fadeInUp">GO</a>
                        </div>
                    </div>
                </div>

                <!-- three slide -->
                <div class="item carousel-item-three">
                    <div class="container">
                        <div class="carousel-position-gu3 text-center">
                            <h2 class="animate-delay carousel-title-v3 text-uppercase" data-animation="animated fadeInDown">
                                找尋您喜歡的事物?
                            </h2>
                            <p class="carousel-subtitle-v5 border-top-bottom margin-bottom-30" data-animation="animated fadeInDown">
                                去活動廣場尋寶吧!
                            </p>
                            <p class="carousel-subtitle-v7 margin-bottom-30" data-animation="animated fadeInDown">

                            </p>
                            <a class="btn btn-primary btn-lg" href="#" data-animation="animated fadeInUp">找活動</a>
                        </div>
                    </div>
                </div>

                <!-- Four slide -->
                <div class="item carousel-item-four">
                    <div class="container">
                        <div class="carousel-position-gu4 text-uppercase text-center">
                            <h2 class="margin-bottom-20 animate-delay carousel-title-v6" data-animation="animated fadeInDown">
                                一群志同道合的好友 <br/>
                                <span class="carousel-title-normal">相聚或聊天</span>
                            </h2>
                            <p class="carousel-subtitle-v8 border-top-bottom margin-bottom-30" data-animation="animated fadeInDown">試試社團大廳看看吧!</p>
                            <a class="btn btn-primary btn-lg" href="#" data-animation="animated fadeInUp">玩社團</a>
                        </div>
                    </div>
                    <!-- Four slide -->
                </div>

            </div>

            <!-- Controls -->
            <a class="left carousel-control carousel-control-shop carousel-control-frontend" href="#carousel-example-generic" role="button" data-slide="prev">
                <i class="fa fa-angle-left" aria-hidden="true"></i>
            </a>
            <a class="right carousel-control carousel-control-shop carousel-control-frontend" href="#carousel-example-generic" role="button" data-slide="next">
                <i class="fa fa-angle-right" aria-hidden="true"></i>
            </a>
        </div>
    </div>

    <!--///////////////////////////////////////////////////////////////////////////////////////-->
    <div class="main">
        <div class="container">
            <hr class="colorgraph">
            <!-- BEGIN SERVICE BOX -->
            <div class="row service-box margin-bottom-40">
                <div class="col-md-4 col-sm-4">
                    <div class="service-box-heading">
                        <em><i class="fa fa-check blue"></i></em>
                        <span>會員成就評價&揪點兌換貼圖</span>
                    </div>
                    <p>您可以隨著活躍度&評價等等...獲得經驗提升等級以及獲得揪點，用來兌換各種可愛或酷炫的貼圖獎賞喔!</p>
                </div>
                <div class="col-md-4 col-sm-4">
                    <div class="service-box-heading">
                        <em><i class="fa fa-location-arrow red"></i></em>
                        <span>出遊地圖定位&打卡</span>
                    </div>
                    <p>不管您是開揪還是參與者，好的地圖查詢與打卡簽到都是不可少的功能。</p>
                </div>
                <div class="col-md-4 col-sm-4">
                    <div class="service-box-heading">
                        <em><i class="fa fa-compress green"></i></em>
                        <span>專屬社團相簿&聊天室</span>
                    </div>
                    <p>上傳屬於你們的活動點滴照片，順便開個聊天室商討一下，下一個活動要辦甚麼好呢?</p>
                </div>
            </div>
            <hr class="colorgraph">
        </div>
    </div>
    <!--======================================================================================================-->

    <!--======================================================================================================-->
    <div id="poi-menu" class="light-wrapper">
        <section class="ss-style-top"></section>
        <div class="container inner">
            <h2 class="section-title text-center">分類</h2>
            <p class="lead text-center">尋找您感興趣的主題！</p>

            <div class="row">
                <div class="col-sm-3 col-md-3">
                    <div class="menu-images "><img src="../HTML/src/index/img/menu/hot-drinks.png" alt="Hot Drinks"></div>
                    <div class="menu-titles">
                        <h1 class="">運動</h1>
                    </div>
                    <div class="menu-items ">
                        <ul>
                            <li>路跑</li>
                            <li>攀岩</li>
                            <li>登山</li>
                            <li>衝浪</li>
                            <li>其他運動</li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3 col-md-3">
                    <div class="menu-images "><img src="../HTML/src/index/img/menu/ice-drinks.png" alt="Ice Drinks"></div>
                    <div class="menu-titles">
                        <h1 class="">學習</h1>
                    </div>
                    <div class="menu-items ">
                        <ul>
                            <li>語言研習</li>
                            <li>文化文學</li>
                            <li>企管行銷</li>
                            <li>資訊科技</li>
                            <li>藝術設計</li>
                            <li>電影實務</li>
                            <li>健身課程</li>
                            <li>心靈成長</li>
                            <li>技能培養</li>
                            <li>生活綜合</li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3 col-md-3">
                    <div class="menu-images "><img src="../HTML/src/index/img/menu/smoothies.png" alt="Smoothies"></div>
                    <div class="menu-titles">
                        <h1 class="">餐聚</h1>
                    </div>
                    <div class="menu-items ">
                        <ul>
                            <li>生態賞析</li>
                            <li>主題活動</li>
                            <li>民俗節慶</li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3 col-md-3">
                    <div class="menu-images "><img src="../HTML/src/index/img/menu/deserts.png" alt="Deserts"></div>
                    <div class="menu-titles">
                        <h1 class="">藝文活動</h1>
                    </div>
                    <div class="menu-items ">
                        <ul>
                            <li>戲劇表演</li>
                            <li>舞蹈表演</li>
                            <li>特技表演</li>
                            <li>演唱會</li>
                            <li>音樂會</li>
                            <li>Live Band</li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-3 col-md-3">
                    <div class="menu-images "><img src="../HTML/src/index/img/menu/cocktails.png" alt="Hot Drinks"></div>
                    <div class="menu-titles">
                        <h1 class="">電影</h1>
                    </div>
                    <div class="menu-items ">
                        <ul>
                            <li>電影快訊</li>
                            <li>主題影展</li>
                            <li>電視廣播</li>
                            <li>追星活動</li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3 col-md-3">
                    <div class="menu-images "><img src="../HTML/src/index/img/menu/beer.png" alt="Ice Drinks"></div>
                    <div class="menu-titles">
                        <h1 class="">電競</h1>
                    </div>
                    <div class="menu-items ">
                        <ul>
                            <li>英雄聯盟</li>
                            <li>鬥陣特工</li>
                            <li>爐石</li>
                            <li>其他</li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3 col-md-3">
                    <div class="menu-images "><img src="../HTML/src/index/img/menu/wine.png" alt="Smoothies"></div>
                    <div class="menu-titles">
                        <h1 class="">寵物</h1>
                    </div>
                    <div class="menu-items ">
                        <ul>
                            <li>貓咪</li>
                            <li>小狗</li>
                            <li>鳥類</li>
                            <li>其他</li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3 col-md-3">
                    <div class="menu-images "><img src="../HTML/src/index/img/menu/sprites.png" alt="Deserts"></div>
                    <div class="menu-titles">
                        <h1 class="">其他</h1>
                    </div>
                    <div class="menu-items ">
                        <ul>
                            <li>親子</li>
                            <li>校園</li>
                            <li>比賽</li>
                            <li>展覽</li>
                            <li>其他</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-12 poi-btn">
                    <a class="btn btn-primary btn-lg" href="#" data-animation="animated fadeInUp">看更多</a>
                </div>
            </div>

        </div>
        <!-- /.container -->
        <section class="ss-style-bottom"></section>
    </div>
    <!--/#food-menu-->
    <!--======================================================================================================-->


    <!-- BEGIN RECENT WORKS -->
    <div id="hot-act" class="light-wrapper row recent-work margin-bottom-40">
        <section class="ss-style-top"></section>
        <div class="container inner">
            <h2 class="section-title text-center">熱門活動</h2>
            <p class="lead text-center">看看現在熱門的活動有什麼吧！</p>
            <!-- item -->
            <div class="col-md-3">
                <div class="">
                    <div class="recent-work-item menu-images">
                        <em>
                            <img src="../HTML/src/index/500x314-exactly.jpg" alt="Amazing Project" class="img-responsive">
                          </em>
                        <a class="recent-work-description" href="javascript:;">
                            <h4>♥義賣跳蚤市場▲塔羅占卜▲vintage▲手作甜點..一起幫助流浪兔!Holiday ya二手市集</h4>
                            <b>《Holiday ya二手市集》 台灣第一個以社會企業模式打造的市集！ 再生x愛兔x歡樂 愛心義賣跳蚤市場x交換書/CD x創意市集x手作甜點x古著</b>
                        </a>
                    </div>
                </div>
            </div>
            <!-- item -->
            <!-- item -->
            <div class="col-md-3">
                <div class="">
                    <div class="recent-work-item menu-images">
                        <em>
                                            <img src="../HTML/src/index/500x314-music.jpg" alt="Amazing Project" class="img-responsive">
                                            
                                          </em>
                        <a class="recent-work-description" href="javascript:;">
                            <h4>臺中爵士音樂節 2017 Jazz Green Land 綺響花園 市民廣場/草悟道</h4>
                            <b>「萬種風情」是臺中這一座魅力城市的代名詞，飲食文化、山海風景、人文藝術…處處皆不經意流瀉臺中市民對這塊土地的情感，而邁入第十五個</b>
                        </a>
                    </div>
                </div>
            </div>
            <!-- item -->
            <!-- item -->
            <div class="col-md-3">
                <div class="">
                    <div class="recent-work-item menu-images">
                        <em>
                                <img src="../HTML/src/index/img_2/500x300.jpg" alt="Amazing Project" class="img-responsive">
                                
                              </em>
                        <a class="recent-work-description" href="javascript:;">
                            <h4>九死一生的上班旅程【一日系列第三十集 - 玉山篇】 </h4>
                            <b>主持人：邰智源、KID木柏昇 木曜四超玩 每周四晚上9點：http://bit.ly/2iIbNns  本節目合作提案請洽cbpm@walkgame.com</b>
                        </a>
                    </div>
                </div>
            </div>
            <!-- item -->
            <!-- item -->
            <div class="col-md-3">
                <div class="">
                    <div class="recent-work-item menu-images">
                        <em>
                                <img src="../HTML/src/index/fisu.jpg" alt="Amazing Project" class="img-responsive">
                                
                              </em>
                        <a class="recent-work-description" href="javascript:;">
                            <h4>世界大學運動會2017</h4>
                            <b>2017夏季世界大學運動會將在臺北市舉辦，這將是臺灣有史以來最大及層級最高的國際運動賽會。我一直期望臺北是一個健康、活力的城市。</b>
                        </a>
                    </div>
                </div>
            </div>
            <!-- item -->
            <div class="row">
                <div class="col-sm-12 col-md-12 poi-btn">
                    <a class="btn btn-primary btn-lg" href="#" data-animation="animated fadeInUp">找更多</a>
                </div>
            </div>
        </div>
        <!-- /.container -->
        <section class="ss-style-bottom"></section>
    </div>
    <!-- END RECENT WORKS -->

    <!-- END BLOCKQUOTE BLOCK -->
    <!--======================================================================================================-->
    <!-- BEGIN TABS AND TESTIMONIALS -->
    <div id="trend" class="row mix-block margin-bottom-40 tab">
        <!-- TABS -->
        <div class=" col-xs-offset-1 col-xs-5 col-md-7 tab-style-1">
            <ul class="nav nav-tabs">
                <li class="active"><a href="#tab-1" data-toggle="tab">公告</a></li>
                <li><a href="#tab-2" data-toggle="tab">人氣排行榜</a></li>
                <li><a href="#tab-3" data-toggle="tab">追蹤榜</a></li>
                <li><a href="#tab-4" data-toggle="tab">評分榜</a></li>
                <li><a href="#tab-5" data-toggle="tab">最新留言</a></li>
                <li><a href="#tab-6" data-toggle="tab">廣告優惠</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane row fade in active" id="tab-1">
                    <div class="col-md-12 col-sm-12">
                        <div class="list-group">
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ann">1</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ann">Second item</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ann">Third item</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ann">Lorem ipsum dolor sit amet consectetur adipisicing elit. Obcaecati aspernatur mollitia sequi repellendus enim est veritatis eos exercitationem hic corporis asperiores reprehenderit ex, quisquam delectus necessitatibus? Doloremque harum eum fuga?</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ann">Second item</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ann">Third item</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ann">First item</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ann">Second item</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ann">Third item</a>
                        </div>
                        <div class="col-md-12 col-sm-12 text-right">
                            <a class="btn btn-info" href="#">更多公告資訊</a>
                        </div>
                    </div>
                </div>
                <div class="tab-pane row fade" id="tab-2">
                    <div class="col-md-12 col-sm-12">
                        <div class="list-group">
                            <a href="#" class="list-group-item">2</a>
                            <a href="#" class="list-group-item">Second item</a>
                            <a href="#" class="list-group-item">Third item</a>
                            <a href="#" class="list-group-item">Lorem ipsum dolor sit amet consectetur adipisicing elit. Obcaecati aspernatur mollitia sequi repellendus enim est veritatis eos exercitationem hic corporis asperiores reprehenderit ex, quisquam delectus necessitatibus? Doloremque harum eum fuga?</a>
                            <a href="#" class="list-group-item">Second item</a>
                            <a href="#" class="list-group-item">Third item</a>
                            <a href="#" class="list-group-item">First item</a>
                            <a href="#" class="list-group-item">Second item</a>
                            <a href="#" class="list-group-item">Third item</a>
                        </div>
                    </div>
                </div>
                <div class="tab-pane row fade" id="tab-3">
                    <div class="col-md-12 col-sm-12">
                        <div class="list-group">
                            <a href="#" class="list-group-item">3</a>
                            <a href="#" class="list-group-item">Second item</a>
                            <a href="#" class="list-group-item">Third item</a>
                            <a href="#" class="list-group-item">Lorem ipsum dolor sit amet consectetur adipisicing elit. Obcaecati aspernatur mollitia sequi repellendus enim est veritatis eos exercitationem hic corporis asperiores reprehenderit ex, quisquam delectus necessitatibus? Doloremque harum eum fuga?</a>
                            <a href="#" class="list-group-item">Second item</a>
                            <a href="#" class="list-group-item">Third item</a>
                            <a href="#" class="list-group-item">First item</a>
                            <a href="#" class="list-group-item">Second item</a>
                            <a href="#" class="list-group-item">Third item</a>
                        </div>
                    </div>
                </div>
                <div class="tab-pane row fade" id="tab-4">
                    <div class="col-md-12 col-sm-12">
                        <div class="list-group">
                            <a href="#" class="list-group-item">4</a>
                            <a href="#" class="list-group-item">Second item</a>
                            <a href="#" class="list-group-item">Third item</a>
                            <a href="#" class="list-group-item">Lorem ipsum dolor sit amet consectetur adipisicing elit. Obcaecati aspernatur mollitia sequi repellendus enim est veritatis eos exercitationem hic corporis asperiores reprehenderit ex, quisquam delectus necessitatibus? Doloremque harum eum fuga?</a>
                            <a href="#" class="list-group-item">Second item</a>
                            <a href="#" class="list-group-item">Third item</a>
                            <a href="#" class="list-group-item">First item</a>
                            <a href="#" class="list-group-item">Second item</a>
                            <a href="#" class="list-group-item">Third item</a>
                        </div>
                    </div>
                </div>
                <div class="tab-pane row fade" id="tab-5">
                    <div class="col-md-12 col-sm-12">
                        <div class="list-group">
                            <a href="#" class="list-group-item">5</a>
                            <a href="#" class="list-group-item">Second item</a>
                            <a href="#" class="list-group-item">Third item</a>
                            <a href="#" class="list-group-item">L</a>
                            <a href="#" class="list-group-item">Second item</a>
                            <a href="#" class="list-group-item">Third item</a>
                            <a href="#" class="list-group-item">First item</a>
                            <a href="#" class="list-group-item">Second item</a>
                            <a href="#" class="list-group-item">Third item</a>
                        </div>
                    </div>
                </div>
                <div class="tab-pane row fade" id="tab-6">
                    <div class="col-md-12 col-sm-12 ">
                        <div class="list-group ">
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ad">6</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ad">Second item</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ad">Third item</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ad">Lorem ipsum dolor sit amet consectetur adipisicing elit. Obcaecati aspernatur mollitia sequi repellendus enim est veritatis eos exercitationem hic corporis asperiores reprehenderit ex, quisquam delectus necessitatibus? Doloremque harum eum fuga?</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ad">Second item</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ad">Third item</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ad">First item</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ad">Second item</a>
                            <a href="#" class="list-group-item" data-toggle="modal" data-target="#Ad">Third item</a>
                        </div>
                        <div class="col-md-12 col-sm-12 text-right">
                            <a class="btn btn-danger" href="# ">更多廣告訊息</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- END TABS -->

        <!--**********************************MODAL**********************************-->
        <!-- 公告用 -->
        <div class="modal fade" id="Ann" role="dialog">
            <div class="modal-dialog modal-lg ">
                <div class="modal-content ">
                    <div class="modal-header ">
                        <button type="button " class="close " data-dismiss="modal">&times;</button>
                        <h4 class="modal-title ">公告標題</h4>
                    </div>
                    <div class="modal-body ">
                        <p>內容</p>
                    </div>
                    <div class="modal-footer ">
                        <button type="button " class="btn btn-default " data-dismiss="modal">關閉</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- 廣告用 -->
        <div class="modal fade" id="Ad" role="dialog">
            <div class="modal-dialog modal-lg ">
                <div class="modal-content ">
                    <div class="modal-header ">
                        <button type="button " class="close " data-dismiss="modal">&times;</button>
                        <h4 class="modal-title ">廣告標題</h4>
                    </div>
                    <div class="modal-body ">
                        <p>內容</p>
                    </div>
                    <div class="modal-footer ">
                        <button type="button " class="btn btn-default " data-dismiss="modal">關閉</button>
                    </div>
                </div>
            </div>
        </div>
        <!--**********************************MODAL**********************************-->
        <div class="photo-stream ">
            <h1>活躍揪友</h1>
            <div class="col-xs-6 col-md-4">
                <div class="row">
                    <div class=" col-xs-3 col-md-3 menu-images">
                        <a href="javascript:; "><img alt=" " src="../HTML/src/index/avatar/1.jpg " class="img-responsive "></a>
                    </div>
                    <div class=" col-xs-3 col-md-3 menu-images">
                        <a href="javascript:; "><img alt=" " src="../HTML/src/index/avatar/2.jpg " class="img-responsive "></a>
                    </div>
                    <div class=" col-xs-3 col-md-3 menu-images">
                        <a href="javascript:; "><img alt=" " src="../HTML/src/index/avatar/3.jpg " class="img-responsive "></a>
                    </div>
                </div>
                <div class="row">
                    <div class=" col-xs-3 col-md-3 menu-images">
                        <a href="javascript:; "><img alt=" " src="../HTML/src/index/avatar/4.jpg " class="img-responsive "></a>
                    </div>
                    <div class=" col-xs-3 col-md-3 menu-images">
                        <a href="javascript:; "><img alt=" " src="../HTML/src/index/avatar/5.jpg " class="img-responsive "></a>
                    </div>
                    <div class=" col-xs-3 col-md-3 menu-images">
                        <a href="javascript:; "><img alt=" " src="../HTML/src/index/avatar/6.jpg " class="img-responsive "></a>
                    </div>
                </div>
                <div class="row">
                    <div class=" col-xs-3 col-md-3 menu-images">
                        <a href="javascript:; "><img alt=" " src="../HTML/src/index/avatar/7.jpg " class="img-responsive "></a>
                    </div>
                    <div class=" col-xs-3 col-md-3 menu-images">
                        <a href="javascript:; "><img alt=" " src="../HTML/src/index/avatar/8.jpg " class="img-responsive "></a>
                    </div>
                    <div class=" col-xs-3 col-md-3 menu-images">
                        <a href="javascript:; "><img alt=" " src="../HTML/src/index/avatar/9.jpg " class="img-responsive "></a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <a class="btn btn-success" href="# ">挖掘</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END TABS AND TESTIMONIALS -->
    <!--======================================================================================================-->
    <!--================================================================-->

  
  <!-- BEGIN FOOTER -->
<c:import url="/front-end/footer.jsp">
</c:import>
  <!-- END FOOTER -->

	<!-- 共用Js -->
 <c:import url="/front-end/publicJS.jsp">
</c:import>
  	<!-- 共用Js -->
  	
</body>
<!-- END BODY -->

</html>
