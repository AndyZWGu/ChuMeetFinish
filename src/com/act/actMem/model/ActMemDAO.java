package com.act.actMem.model;
import org.hibernate.*;

import com.act.act.model.Act_VO;
import com.member.model.MemberHVO;

import hibernate.util.HibernateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class ActMemDAO implements ActMem_Interface {

	private static final String GET_ALL_STMT = "from DeptVO order by deptno";

	@Override
	public void insert(ActMemVO actMemVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(actMemVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}


	public void update(ActMemVO actMemVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(actMemVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	@Override
	public void delete(ActMemVO actMemVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			int actid=actMemVO.getActVO().getActID();
//			int memid=actMemVO.getMemberHVO().getMemID();
			session.beginTransaction();
//			Query query = session.createQuery("delete ActMemVO where  actMemVO.actVO.actID="+actid+" AND actMemVO.memberHVO.memID="+memid);
//			System.out.println("鍒櫎鐨勭瓎鏁�=" + query.executeUpdate());
			session.delete(actMemVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
		

	@Override
	public List<ActMemVO> myActList1(Integer memID) {
		List<ActMemVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from ActMemVO where memid="+memID+" where actMemStatus=1 order by actVO.actStartDate");
		list = (List<ActMemVO>) query.list();
		session.getTransaction().commit();
		return list;
	}
	@Override
	public List<ActMemVO> myActList2(Integer memID) {
		List<ActMemVO> list2 = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from ActMemVO where memid="+memID+" where actMemStatus=2 order by actVO.actStartDate");
		list2 = (List<ActMemVO>) query.list();
		session.getTransaction().commit();
		return list2;
	}
	@Override
	public List<ActMemVO> myActList5(Integer memID) {
		List<ActMemVO> list5 = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from ActMemVO where memid="+memID+" where actMemStatus=5 order by actVO.actStartDate");
		list5 = (List<ActMemVO>) query.list();
		session.getTransaction().commit();
		return list5;
	}
	
	public HashMap<Integer, ActMemVO[]> memPackage(int actID){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		HashMap<Integer, ActMemVO[]> memPack=null;
			Query q1=session.createSQLQuery("select * from actmem where actid="+actID+"where actMemStatus = 1");
			
		return memPack;
	
	}


	@Override
	public ActMemVO getOne(Integer actID, Integer memID) {
		ActMemVO amVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from ActMemVO where actID="+actID+" AND memID="+memID);
			List<Object> actmemVOs=query.list();
			amVO=(ActMemVO) actmemVOs.get(0);
			
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return amVO;
	}


	@Override
	public List<ActMemVO> myActListAll(Integer memID) {
		List<ActMemVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from ActMemVO where memberHVO.memid="+memID+" order by actVO.actStartDate");
		list = (List<ActMemVO>) query.list();
		session.getTransaction().commit();
		return list;
	}



//
//	public static void main(String[] args){
//		ActMemDAO amDAO=new ActMemDAO();
//		ActMemVO am=amDAO.getOne(1, 2);
//		System.out.println(am.getMemberHVO().getMemName());
//	}

}
