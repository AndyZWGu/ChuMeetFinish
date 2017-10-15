package com.act.act.model;

/*
 Hibernate is providing a factory.getCurrentSession() method for retrieving the current session. A
 new session is opened for the first time of calling this method, and closed when the transaction is
 finished, no matter commit or rollback. But what does it mean by the “current session”? We need to
 tell Hibernate that it should be the session bound with the current thread.

 <hibernate-configuration>
 <session-factory>
 ...
 <property name="current_session_context_class">thread</property>
 ...
 </session-factory>
 </hibernate-configuration>

 */

import org.hibernate.*;

import hibernate.util.HibernateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import hibernate.util.HibernateUtil;

import javax.servlet.ServletException;

import com.act.act.model.Act_VO;
import com.act.actMem.model.ActMemVO;
import com.act.actPOI.model.ActPOIVO;
import com.gen.tool.actCodeTrans;
import com.gen.tool.tools;
import com.member.model.MemberHVO;
import com.poi.model.POIVO;

public class Act_DAO implements Act_interface {

	@Override
	public Integer insert(Act_VO actVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Integer actID = 0;
		try {
			session.beginTransaction();
			actID = Integer.parseInt(session.save(actVO).toString());
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		System.out.println(actID);

		return actID;
	}

	@Override
	public void update(Act_VO actVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(actVO);
			session.getTransaction().commit();
			System.out.println("OK!!!!!!!!!!");
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	
	
	
	@Override
	public ActFiestaVO getOne(Integer actID) {
		Act_VO actVO = null;
		ActFiestaVO actfVO=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			actVO = (Act_VO) session.get(Act_VO.class, actID);
			actfVO=new ActFiestaVO(actVO);			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return actfVO;
	}

	@Override
	public List<Act_VO> getAll() {
		List<Act_VO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Act_VO  order by actID");
			list = (List<Act_VO>) query.list();
			System.out.println(list.get(0).getActName());
			List<ActFiestaVO> listf = null;
			for (Act_VO actVO : list) {
				ActFiestaVO actf = new ActFiestaVO(actVO);
				System.out.println(actf.toString());
				listf.add(actf);
			}
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<ActFiestaVO> getAllFromNow() {
		List<Act_VO> list = new ArrayList<Act_VO>();
		List<ActFiestaVO> listf =  new ArrayList<ActFiestaVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Act_VO where actStartDate>systimestamp order by actStartDate");
			query.setMaxResults(50);
			list = query.list();
					if(!(list.size()==0)) {
							for (Act_VO actVO : list) {
									ActFiestaVO actf = new ActFiestaVO(actVO);
									listf.add(actf); 
							}
					}else {
						System.out.println("null x_x");
					}
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return listf;
	}

	
	
	@Override
	public List<ActFiestaVO> getActByPOIID(Integer POIID) {
		List<Act_VO> list = new ArrayList<Act_VO>();
		List<ActFiestaVO> listf =  new ArrayList<ActFiestaVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ActPOIVO where POIID="+POIID+" order by actStartDate");
			query.setMaxResults(50);
			list = query.list();
					System.out.println(list.size());
					if(!(list.size()==0)) {
							for (Act_VO actVO : list) {
									ActFiestaVO actf = new ActFiestaVO(actVO);
									listf.add(actf); 
							}
					}else {
						System.out.println("null x_x");
					}
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return listf;
	}

	@Override
	public List<ActFiestaVO> getActByDate(Timestamp selectDate) {
		List<Act_VO> list = new ArrayList<Act_VO>();
		List<ActFiestaVO> listf =  new ArrayList<ActFiestaVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Act_VO where to_char(actDate,'yyyy/mm/dd')=? order by actID");
			query.setParameter(0, selectDate);
			list = query.list();
					if(!(list.size()==0)) {
							for (Act_VO actVO : list) {
									ActFiestaVO actf = new ActFiestaVO(actVO);
									listf.add(actf); 
							}
					}else {
						System.out.println("null x_x");
					}
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return listf;
	}

	@Override
	public List<ActFiestaVO> getActByWks() {
		String sb=tools.GetWKSFromNow();
		List<Act_VO> list = new ArrayList<Act_VO>();
		List<ActFiestaVO> listf =  new ArrayList<ActFiestaVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			System.out.println(sb);
			Query query = session.createQuery("from Act_VO where to_char(actStartDate,'yyyy/mm/dd') in ("+sb+") order by actID");
			
			list = query.list();
					if(!(list.size()==0)) {
							for (Act_VO actVO : list) {
									ActFiestaVO actf = new ActFiestaVO(actVO);
									listf.add(actf); 
							}
					}else {
						System.out.println("null x_x");
					}
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return listf;
	}

	@Override
	public Set<ActMemVO> whosin(Integer actID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ActPOIVO> showthetags(Integer actID) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {

		Timestamp ts = tools.nowTimestamp();
		Integer actType = 1;
		Integer memID = 1;
		Timestamp actCreateDate = ts;
		String actName = "123";
		Integer actStatus = 1;
		Integer actPriID = 1;
		Timestamp actStartDate = ts;
		Integer actTimeTypeID = 1;
		Double actLong = 12.3;
		Double actLat = 45.6;
		Integer actPost = 999;
		String actAdr = "qqq";
		
		Set<Integer> poiincome = new HashSet<>();
		poiincome.add(20);
		poiincome.add(2);
		poiincome.add(5);
		
		Act_DAO dao = new Act_DAO();
		Act_VO actVO1=new Act_VO();
		actVO1.setActType(actType);

		actVO1.setMemID(memID);
		actVO1.setActCreateDate(actCreateDate);
		actVO1.setActName(actName);
		actVO1.setActStatus(actStatus);
		actVO1.setActPriID(actPriID);
		actVO1.setActStartDate(actStartDate);

		actVO1.setActTimeTypeID(actTimeTypeID);

		actVO1.setActLong(actLong);
		actVO1.setActLat(actLat);
		actVO1.setActPost(actPost);

		actVO1.setActAdr(actAdr);
		Integer actID=dao.insert(actVO1);




		// 為了回傳用的

		Act_VO actVO2 = new Act_VO();

		Set<ActMemVO> amset = new HashSet<ActMemVO>();
		Set<ActPOIVO> apset = new HashSet<ActPOIVO>();

		ActMemVO amVO = new ActMemVO();
		MemberHVO mvo = new MemberHVO();
			mvo.setMemID(1);
		amVO.setMemberHVO(mvo);
		amVO.setActJoinDate(tools.nowTimestamp());
		amVO.setActMemStatus(1);

		for (Integer poiID : poiincome) {
			ActPOIVO apVO = new ActPOIVO();
			POIVO pv = new POIVO();
				pv.setPOIID(poiID);
			apVO.setPOIVO(pv);
			
			Act_VO actVO3 = new Act_VO();
				actVO3.setActID(actID);
			apVO.setActVO(actVO2);
			
			apset.add(apVO);
		}

		actVO2.setActPOIs(apset);
		actVO2.setActMems(amset);

		dao.update(actVO2);

		// ● 查詢-findByPrimaryKey (優秀!) (一方dept2.hbm.xml必須設為lazy="false")
		// DeptVO deptVO3 = dao.findByPrimaryKey(30);
		// System.out.print(deptVO3.getDeptno() + ",");
		// System.out.print(deptVO3.getDname() + ",");
		// System.out.print(deptVO3.getLoc());
		// System.out.println("\n-----------------");
		// Set<EmpVO> set3 = deptVO3.getEmps();
		// for (EmpVO aEmp : set3) {
		// System.out.print(aEmp.getEmpno() + ",");
		// System.out.print(aEmp.getEname() + ",");
		// System.out.print(aEmp.getJob() + ",");
		// System.out.print(aEmp.getHiredate() + ",");
		// System.out.print(aEmp.getSal() + ",");
		// System.out.print(aEmp.getComm() + ",");
		// System.out.print(aEmp.getDeptVO().getDeptno() + ",");
		// System.out.print(aEmp.getDeptVO().getDname() + ",");
		// System.out.print(aEmp.getDeptVO().getLoc());
		// System.out.println();
		// }

		// ● 查詢-getAll-1 (一方dept2.hbm.xml不用設為lazy="false",因為沒用到多方的物件)
		// List<DeptVO> list1 = dao.getAll();
		// for (DeptVO aDept : list1) {
		// System.out.print(aDept.getDeptno() + ",");
		// System.out.print(aDept.getDname() + ",");
		// System.out.print(aDept.getLoc());
		// System.out.println();
		// }

		// ● 查詢-getAll-2 (優秀!!!) (一方dept2.hbm.xml必須設為lazy="false")
		// List<Act_VO> list2 = dao.getAll();
		// for (Act_VO actvo : list2) {
		// System.out.print(actvo.getActID() + ",");
		// System.out.print(actvo.getActName() + ",");
		// System.out.print(actvo.getMemID());
		// System.out.println("\n-----------------");
		//
		// Set<ActMemVO> set2 = actvo.getActMems();
		// for (ActMemVO memv : set2) {
		// System.out.print(memv.getActMemStatus() + ",");
		// System.out.print(memv.getActJoinDate() + ",");
		// System.out.println();
		// }
		// System.out.println();
		// }

	}

	@Override
	public List<ActFiestaVO> getActByClub(Integer clubID) {
		// TODO Auto-generated method stub
		return null;
	}

}
