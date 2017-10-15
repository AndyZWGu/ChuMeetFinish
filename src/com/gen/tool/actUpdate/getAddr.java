package com.gen.tool.actUpdate;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import com.google.gson.*;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class getAddr {

	
	public static void main(String[] args) throws Exception {
		String adr="111臺北市士林區文林路751號";
		System.out.println(getThePostal(adr));
	}
	
public static Integer getThePostal(String actAdr) throws Exception {

	int post;
	
	// Create a trust manager that does not validate certificate chains
	TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
	    public X509Certificate[] getAcceptedIssuers(){return null;}
	    public void checkClientTrusted(X509Certificate[] certs, String authType){}
	    public void checkServerTrusted(X509Certificate[] certs, String authType){}
	}};

	// Install the all-trusting trust manager
	try {
	    SSLContext sc = SSLContext.getInstance("TLS");
	    sc.init(null, trustAllCerts, new SecureRandom());
	    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	} catch (Exception e) {
	    ;
	}
	String json = actJSON.readUrl("http://zip5.5432.tw/zip5json.py?adrs="+actAdr);
	AdrVO adrv = new Gson().fromJson(json, AdrVO.class);
	post=Integer.parseInt(adrv.getZipcode().substring(0,3));
	System.out.println(post);
	return post;
	
}
}
