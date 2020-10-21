package shoestore.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import shoestore.repository.custom.PointRepositoryCustom;

public class PointRepositoryImpl implements PointRepositoryCustom {
	@PersistenceContext 
	EntityManager entityManager;
	
	
	@Override
	public int getMaxValueInScreen(Long screenId,String type) {
		String sql="select max(value) from point where screenId=" +screenId+ " and type='"+type+"'"+
				   "  group by screenId";
		Query query=entityManager.createNativeQuery(sql);
		Object object=query.getSingleResult();
		if(object!=null) {
			Integer result=Integer.parseInt(object.toString());
			return result;
		}
		
		return Integer.valueOf(0);
	}


	@Override
	public int getMinValueInScreen(Long screenId,String type) {
		String sql="select min(value) from point where screenId=" +screenId+" and type='"+type+"'"+
				   "  group by screenId;";
		Query query=entityManager.createNativeQuery(sql);
		Object object=query.getSingleResult();
		if(object!=null) {
			Integer result=Integer.parseInt(object.toString());
			return result;
		}
		
		return Integer.valueOf(0);
	}

}
