package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * select 
  catalog0_.id as id1_0_, 
  catalog0_.category_id as category4_0_, 
  catalog0_.description as descript2_0_, 
  catalog0_.name as name3_0_, 
  catalog0_.tower_id as tower_id5_0_ 
from 
  catalogs catalog0_ 
  inner join towers tower1_ on catalog0_.tower_id = tower1_.id 
  inner join organizations organizati2_ on tower1_.org_id = organizati2_.id 
where 
  (catalog0_.description like ?) 
  and (
    catalog0_.name like ? 
    or catalog0_.description like ?
  ) 
  and tower1_.id = 1 
  and organizati2_.id = 1
 */
import org.springframework.data.jpa.domain.Specification;

public class CatalogSpecification implements Specification<Catalog> {
	
	private final Map<String,String> criteria;
   
    private final String searchText;

    public CatalogSpecification(Map<String, String> criteria,String searchText) {
		super();
		this.criteria = criteria;
		this.searchText=searchText;
	}

	@Override
	public Predicate toPredicate(Root<Catalog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		 Iterator<String> keys = criteria.keySet().iterator();
	     List<Predicate> filters = new ArrayList<>();
	     
	     if (criteria.size() != 0) {
	            while (keys.hasNext()) {
	                String key = (String) keys.next();
	                String filterValue =  criteria.get(key).toString();
	                System.out.println("Filter value --->" + filterValue);
	                if (filterValue != null) {
	                    filters.add(
	                    		criteriaBuilder.like( root.<String>get(key), 
	                    				"%" + filterValue.toUpperCase() + "%"));
	                }
	            }
	     }
	     
	     if(searchText!= null && searchText.trim().length()>0) {
	    	 filters.add(
             		criteriaBuilder.or(
             			criteriaBuilder.like(root.get("name"), "%" + searchText + "%"),
             			criteriaBuilder.like(root.get("description"), "%" + searchText + "%"))
             		);
	     }
	     
	     Join<Catalog, Tower> tower = root.join("tower");
	     Join<Tower,Organization> org = tower.join("organization");
	     Predicate p1 = criteriaBuilder.equal(tower.get("id"), 1);
	     Predicate p2 = criteriaBuilder.equal(org.get("id"), 1);
	     filters.add(p1);
	     filters.add(p2);
	     
	     return criteriaBuilder.and(filters.toArray(new Predicate[filters.size()]));
    
	}

}
