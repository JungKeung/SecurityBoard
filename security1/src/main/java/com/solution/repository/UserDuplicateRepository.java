package com.solution.repository;

import com.solution.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDuplicateRepository {

    private final EntityManager em;

    public Optional<User> findByEmail(String email){
            Optional<User> user = Optional.ofNullable ( (em.createQuery ( "select u from User u where u.email = :email", User.class )
                    .setParameter ( "email", email )
                    .getSingleResult ()) );
            return user;
        }
    }

