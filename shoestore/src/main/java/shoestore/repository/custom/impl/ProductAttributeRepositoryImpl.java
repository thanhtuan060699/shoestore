package shoestore.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import shoestore.repository.custom.ProductAttributeRepositoryCustom;

@Repository
public class ProductAttributeRepositoryImpl implements ProductAttributeRepositoryCustom{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public int getTotalQuantity(Long brandId) {
		
		String sql="select sum(product_attribute.quantity) from brand join product on brand.id=product.brand_id " + 
				"  join product_attribute on product.id=product_attribute.product_id " + 
				"  where brand.id=" +brandId+ 
				"  group by brand.id";
		Query query=entityManager.createNativeQuery(sql);
		Object object=query.getSingleResult();
		if(object!=null) {
			Integer result=Integer.parseInt(object.toString());
			return result;
		}
		
		return Integer.valueOf(0);
	}

}
