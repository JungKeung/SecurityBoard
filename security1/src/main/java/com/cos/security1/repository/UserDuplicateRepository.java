package com.cos.security1.repository;

import com.cos.security1.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDuplicateRepository {

    private final EntityManager em;

//    public String findByEmail(String email){
//        return (em.createQuery("select u from User u where u.email = :email", User.class)
//                .setParameter("email", email)
//                //getSingleResult() 결과가 없으면 NoResultException 발생, 결과가 1개보다 많으면 NoUniqueResultException 발생
//                .getSingleResult().toString());
//    }



    public Optional<User> findByEmail(String email){
        Optional<User> user = null;
        try{
            user = Optional.ofNullable(em.createQuery("select u from User u where u.email = :email", User.class)
                    .setParameter("email", email).getSingleResult());
        } catch (NoResultException e) {
            user = Optional.empty();
        } finally {
            return user;
        }
    }
}
