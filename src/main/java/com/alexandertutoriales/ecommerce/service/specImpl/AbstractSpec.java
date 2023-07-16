package com.alexandertutoriales.ecommerce.service.specImpl;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AbstractSpec {
    /** The Constant M_ID. */
    protected static final String M_ID = "id";

    @SuppressWarnings("unchecked")
    public <T, U, V> Join<U, V> joinFetch(CriteriaQuery<?> query, Object object, String field, JoinType joinType) {

        Join<U, V> join;

        // If the query is for counting, the fetch is avoided
        if (isCountQuery(query)) {
            if (object instanceof Root<?>) {
                join = ((Root<U>) object).join(field, joinType);
            } else {
                join = ((Join<T, U>) object).join(field, joinType);
            }
        }

        else {
            Fetch<U, V> fetch;
            if (object instanceof Root<?>) {
                fetch = ((Root<U>) object).fetch(field, joinType);
            } else {
                fetch = ((Join<T, U>) object).fetch(field, joinType);
            }
            join = (Join<U, V>) fetch;
        }

        return join;
    }

    /**
     * Add to the CriteriaBuilder a list of predicates collected by the AND
     * operator.
     *
     * @param cb         the cb
     * @param conditions the conditions
     * @return the predicate
     */
    public Predicate and(CriteriaBuilder cb, List<Predicate> conditions) {
        return cb.and(conditions.toArray(new Predicate[conditions.size()]));
    }

    /**
     * Add to the CriteriaBuilder a list of predicates collected by the OR operator.
     *
     * @param cb         the cb
     * @param conditions the conditions
     * @return the predicate
     */
    public Predicate or(CriteriaBuilder cb, List<Predicate> conditions) {
        return cb.or(conditions.toArray(new Predicate[conditions.size()]));
    }

    /**
     * Like operation to find a substring in the database.
     *
     * @param cb      the cb
     * @param value   the value
     * @param pattern the patern
     * @return the predicate
     */
    public Predicate like(CriteriaBuilder cb, Expression<String> value, String pattern) {
        return cb.like(cb.upper(value), new StringBuilder("%").append(pattern.toUpperCase()).append('%').toString());
    }

    /**
     * Checks if is a count query.
     *
     * @param query the query
     * @return true, if is count query
     */
    private boolean isCountQuery(CriteriaQuery<?> query) {
        Class<?> resultType = query.getResultType();
        return resultType.equals(Long.class) || resultType.equals(long.class);
}

}
