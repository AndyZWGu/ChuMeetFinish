$(function() {
	function t() {
		if (a.width() > 1e3) {
			i = a.width(), contantWidth = r.width();
			var t = i / 2 + contantWidth / 2 + 70,
				e = i / 2 + contantWidth / 2 + 75,
				n = i / 2 - contantWidth / 2 - 105;
			$(".social-icon").css({
				left: t + "px"
			}), $(".arrow-left").css({
				left: n + "px"
			}).attr("target", "_top"), $(".arrow-right").css({
				left: e + "px"
			}).attr("target", "_top"), a.scroll(function() {
				a.scrollTop() > 150 ? $(".social-icon, .arrow-left, .arrow-right").not("animated").fadeIn(300) : $(".social-icon, .arrow-left, .arrow-right").not("animated").fadeOut(300)
			})
		} else $(".social-icon, .arrow-left, .arrow-right").hide()
	}
	var e, i, n, o, a = $(window),
		r = ($(document), $("article")),
		c = location.href,
		l = "/articles/" + (n = c.split("/"))[n.length - 2] + "/";
	n.forEach(function(t) {
			if (-1 != t.indexOf(".html"))
				if (-1 != t.indexOf("?")) {
					var e = t.split("?");
					o = l + e[0]
				} else o = l + t
		}), $(".preview-img").unwrap("p"),
		function(t) {
			$.getJSON("/json/pageList.json", function(i) {
				i.forEach(function(e) {
					e.site == t && ($(".tag").addClass(e.tag), $("h1").attr("data-date", e.date), tagName = e.tag)
				});
				var n, a = [],
					r = 0;
				i.forEach(function(t) {
					t.tag == tagName && (a[r] = t, r += 1), -1 != t.site.indexOf(o) && (n = i.indexOf(t))
				});
				var c, l = [];
				if (r <= 5)
					for (e = 0; e < r; e++) c = a[e].img ? a[e].img : "/img" + a[e].site.replace(".html", "-s.jpg"), $("#other-articles").append('<a href="' + a[e].site + '"><div><img src="' + c + '"><h4>' + a[e].title + "</h4></div></a>");
				else
					for (e = r; e >= r - 4; e--) {
						var s = Math.floor(e * Math.random(e));
						l[e - 5] = a.splice(s, 1)[0], c = l[e - 5].img ? l[e - 5].img : "/img" + l[e - 5].site.replace(".html", "-s.jpg"), $("#other-articles").append('<a href="' + l[e - 5].site + '"><div><img src="' + c + '"><h4>' + l[e - 5].title + "</h4></div></a>")
					}
				0 == n ? ($(".next-article h4").html("沒有下一篇文章了呦~"), $(".previous-article h4").html("<a href=" + i[n + 1].site + ">" + i[n + 1].title + "</a>"), $(".arrow-right").css({
					top: "-100px"
				}), $(".arrow-left").attr("href", i[n + 1].site).attr("title", i[n + 1].title)) : n == i.length - 1 ? ($(".previous-article h4").html("沒有前一篇文章了呦~"), $(".next-article h4").html("<a href=" + i[n - 1].site + ">" + i[n - 1].title + "</a>"), $(".arrow-left").css({
					top: "-100px"
				}), $(".arrow-right").attr("href", i[n - 1].site).attr("title", i[n - 1].title)) : ($(".previous-article h4").html("<a href=" + i[n + 1].site + ">" + i[n + 1].title + "</a>"), $(".arrow-left").attr("href", i[n + 1].site).attr("title", i[n + 1].title), $(".next-article h4").html("<a href=" + i[n - 1].site + ">" + i[n - 1].title + "</a>"), $(".arrow-right").attr("href", i[n - 1].site).attr("title", i[n - 1].title))
			})
		}(o), t(),
		function(t) {
			$(".icon-home").on("click", function() {
				window.open("/index.html", "_self"), ga("send", "event", "iconMenu", "click", "home")
			}), $(".icon-list").on("click", function() {
				window.open("/list.html", "_self"), ga("send", "event", "iconMenu", "click", "list")
			}), $(".icon-facebook").on("click", function() {
				window.open("http://www.facebook.com/share.php?u=" + t, "_blank"), ga("send", "event", "iconMenu", "click", "facebook")
			}), $(".icon-google").on("click", function() {
				window.open("https://plus.google.com/share?url=" + t, "_blank"), ga("send", "event", "iconMenu", "click", "google")
			}), $(".icon-twitter").on("click", function() {
				window.open("http://twitter.com/home/?status=" + t, "_blank"), ga("send", "event", "iconMenu", "click", "twitter")
			}), $(".goto-top").on("click", function() {
				$("html,body").animate({
					scrollTop: "0"
				}, 750), ga("send", "event", "iconMenu", "click", "top")
			}), $(".previous-next").on("click", function() {
				ga("send", "event", "moreLink", "click", "preAndNext")
			}), $(".content a").on("click", function() {
				ga("send", "event", "contentLink", "click", $(this).attr("href"))
			}), $(".arrow-left, .arrow-right").on("click", function() {
				ga("send", "event", "moreLink", "click", "arrowLeftRight")
			})
		}(c), a.resize(t)
});