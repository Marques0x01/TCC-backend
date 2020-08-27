package com.tcc.backend.model;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification implements Specification<Product> {

    private List<SearchCriteria> criterias;

    public ProductSpecification() {
        this.criterias = new ArrayList<>();
    }

    public void add(SearchCriteria searchCriteria){
        this.criterias.add(searchCriteria);
    }

    @Override
    public Specification<Product> and(Specification<Product> other) {
        return null;
    }

    @Override
    public Specification<Product> or(Specification<Product> other) {
        return null;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : criterias) {
            if(criteria.getKey().equals("city") || criteria.getKey().equals("state")){
                Join<Product, Address> join = root.join("address", JoinType.LEFT);
                return criteriaBuilder.equal(criteriaBuilder.lower(join.get(criteria.getKey())), criteria.getValue());
            }
            if (criteria.getOperation().equalsIgnoreCase(">")) {
                if(criteria.getValue() instanceof Double){
                    return criteriaBuilder.greaterThanOrEqualTo(
                            root.get(criteria.getKey()).as(Double.class), Double.parseDouble(criteria.getValue().toString()));
                }
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.<String> get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase("<")) {
                if(criteria.getValue() instanceof Double){
                    return criteriaBuilder.lessThanOrEqualTo(
                            root.get(criteria.getKey()).as(Double.class), Double.parseDouble(criteria.getValue().toString()));
                }
                return criteriaBuilder.lessThanOrEqualTo(
                        root.<String> get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase(":")) {
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    return criteriaBuilder.like(
                            root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
                }
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());

            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
