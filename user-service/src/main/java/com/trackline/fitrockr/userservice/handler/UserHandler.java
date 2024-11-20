package com.trackline.fitrockr.userservice.handler;

import com.trackline.fitrockr.userservice.db.entity.User;
import com.trackline.fitrockr.userservice.db.entity.User_;
import com.trackline.fitrockr.userservice.db.repo.UserRepo;
import com.trackline.fitrockr.userservice.exception.BadRequestException;
import com.trackline.fitrockr.userservice.exception.NotFoundException;
import com.trackline.fitrockr.userservice.handler.mapper.UserMapper;
import com.trackline.fitrockr.userservice.model.*;
import com.trackline.fitrockr.userservice.util.SanitizeHelper;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserHandler {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserHandler(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Transactional
    public ResponseUserModel createUser(@NonNull UserCreateModel createModel) {
        Objects.requireNonNull(createModel);

        if (createModel.name() == null || createModel.name().isBlank()) {
            throw new BadRequestException("name must not be null/blank");
        }
        if (createModel.email() == null || createModel.email().isBlank()) {
            throw new BadRequestException("email must not be null/blank");
        }
        if (createModel.country() == null) {
            throw new BadRequestException("country must not be null");
        }
        if (createModel.language() == null) {
            throw new BadRequestException("language must not be null");
        }

        User entity = new User();
        entity.setName(createModel.name());
        entity.setEmail(createModel.email());
        entity.setCountry(createModel.country());
        entity.setLanguage(createModel.language());

        entity = userRepo.save(entity);

        UserReadModel data = userMapper.toModel(entity);
        return new ResponseUserModel(data);
    }

    @Transactional
    public ResponseUserModel getUserById(@NonNull Long userId) {
        Objects.requireNonNull(userId);

        User entity = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("user with id %d not found".formatted(userId)));

        UserReadModel data = userMapper.toModel(entity);
        return new ResponseUserModel(data);
    }

    @Transactional
    public ResponseUsersModel getUsersFiltered(
            @Nullable String filterName,
            @Nullable String filterEmail) {

        Specification<User> spec = (root, query, cb) -> {

            HibernateCriteriaBuilder hcb = (HibernateCriteriaBuilder)cb;
            Predicate userPredicate = hcb.and();
            if (filterName != null) {
                Predicate nameFilterPredicate = hcb.like(root.get(User_.NAME), SanitizeHelper.buildSaneLikeFilterValue(filterName));
                userPredicate = hcb.and(userPredicate, nameFilterPredicate);
            }
            if (filterEmail != null) {
                Predicate emailFilterPredicate = hcb.like(root.get(User_.EMAIL), SanitizeHelper.buildSaneLikeFilterValue(filterEmail));
                userPredicate = hcb.and(userPredicate, emailFilterPredicate);
            }

            return userPredicate;
        };

        List<User> entities = userRepo.findAll(spec);
        List<UserReadModel> data  = entities.stream().map((userMapper::toModel)).toList();
        return new ResponseUsersModel(data);
    }

    @Transactional
    public void deleteUserById(@NonNull Long userId) {
        Objects.requireNonNull(userId);

        User entity = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("user with id %d not found".formatted(userId)));
        userRepo.delete(entity);
    }

}
