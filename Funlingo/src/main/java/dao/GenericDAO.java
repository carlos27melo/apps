package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class GenericDAO<T> {

//	public void salvar(T entidade) {
//		Session sessao = HibernateUtil.getSessionFactory().openSession();
//		Transaction transacao = null;
//		try {
//			transacao = sessao.beginTransaction();
//			sessao.save(entidade);
//			transacao.commit();
//		} catch (RuntimeException e) {
//			if(transacao!=null) {
//				transacao.rollback();
//			}
//			throw e;
//		}finally {
//			sessao.close();
//		}
//	}
	
}
