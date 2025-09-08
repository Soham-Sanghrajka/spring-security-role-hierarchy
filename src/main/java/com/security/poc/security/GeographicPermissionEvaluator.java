package com.security.poc.security;

<<<<<<< HEAD
=======

import com.security.poc.constant.OperationType;
import com.security.poc.constant.ResourceType;
>>>>>>> 9465a732f88077dfa536a11a857b48420a2f4339
import com.security.poc.constant.RoleConstants;
import com.security.poc.entity.Group;
import com.security.poc.entity.Role;
import com.security.poc.entity.User;
import com.security.poc.exception.ResourceNotFoundException;
<<<<<<< HEAD
=======
import com.security.poc.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 9465a732f88077dfa536a11a857b48420a2f4339
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("permissionEvaluator")
public class GeographicPermissionEvaluator implements PermissionEvaluator {
<<<<<<< HEAD
    
=======

    @Autowired
    private GroupRepository groupRepository;

>>>>>>> 9465a732f88077dfa536a11a857b48420a2f4339
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null || !(permission instanceof String)) {
            return false;
        }

        User user = (User) authentication.getPrincipal();

        // Super admin check first
        if (checkSuperAdminAccess(user)) {
            return true;
        }

        // Handle geographic entity permission check
        if (permission instanceof String && targetDomainObject == null) {
            return checkOperationPermission(user, (String) permission);
        }

        // Handle geographic permissions
        if (targetDomainObject instanceof Group) {
            return checkGeographicAccess(user, (Group) targetDomainObject);
        }

        // Handle ID based checks
        if (targetDomainObject instanceof Long || targetDomainObject instanceof String) {
            Long groupId = targetDomainObject instanceof String ?
                    Long.parseLong((String) targetDomainObject) : (Long) targetDomainObject;

<<<<<<< HEAD
        // Deny access if no conditions are met
        return false;
    }

    private boolean checkUserCanAccessUserDetails(User user, Object targetUser) {
        UserDTO targetUserDto = (UserDTO) targetUser;

        if(targetUserDto == null) {
            throw  new ResourceNotFoundException("User Not Found.");
        }

        // Only allow access if both users are in the same country, state, and city (if needed)
        if (RoleConstants.ROLE_COUNTRY_ADMIN.equals(user.getGroupRole()) && user.getCountry().getName().equals(targetUserDto.getCountry().getName())) {
            return true;
        }

        if (RoleConstants.ROLE_STATE_ADMIN.equals(user.getGroupRole())
                && user.getState().equals(targetUserDto.getState())
                && user.getCountry().equals(targetUserDto.getCountry())) {
            return true;
        }

        if (RoleConstants.ROLE_CITY_ADMIN.equals(user.getGroupRole())
                && user.getCity().equals(targetUserDto.getCity())
                && user.getState().equals(targetUserDto.getState())
                && user.getCountry().equals(targetUserDto.getCountry())) {
            return true;
        }

        if (RoleConstants.ROLE_DISTRICT_ADMIN.equals(user.getGroupRole())
                && user.getDistrict().equals(targetUserDto.getDistrict())
                && user.getCity().equals(targetUserDto.getCity())
                && user.getState().equals(targetUserDto.getState())
                && user.getCountry().equals(targetUserDto.getCountry())) {
            return true;
=======
            Group group = groupRepository.findById(groupId)
                    .orElseThrow(() -> new ResourceNotFoundException("Group not found"));
            return checkGeographicAccess(user, group);
>>>>>>> 9465a732f88077dfa536a11a857b48420a2f4339
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return hasPermission(authentication, targetId, permission);
    }

    private boolean checkGeographicAccess(User user, Group group) {
        // Get user's highest role
        String roleType = user.getRoles().stream()
                .map(Role::getName)
                .filter(name -> name.endsWith("_ADMIN"))
                .findFirst()
                .orElse("");

        return switch (roleType) {
            case RoleConstants.ROLE_SUPER_ADMIN -> checkSuperAdminAccess(user);
            case RoleConstants.ROLE_COUNTRY_ADMIN -> checkCountryAccess(user, group);
            case RoleConstants.ROLE_STATE_ADMIN -> checkStateAccess(user, group);
            case RoleConstants.ROLE_CITY_ADMIN -> checkCityAccess(user, group);
            case RoleConstants.ROLE_DISTRICT_ADMIN -> checkDistrictAccess(user, group);
            case RoleConstants.ROLE_CHAPTER_ADMIN -> checkChapterAccess(user, group);
            default -> false;
        };
    }

    private boolean checkSuperAdminAccess(User user) {
        return user.getRoles().stream()
                .anyMatch(r -> r.getName().equals(RoleConstants.ROLE_SUPER_ADMIN));
    }

    private boolean checkCountryAccess(User user, Group group) {
        return user.getRoles().stream()
                .anyMatch(r -> r.getName().equals(RoleConstants.ROLE_COUNTRY_ADMIN))
                && user.getCountry().getName()
                .equals(group.getDistrict().getCity().getCountry().getName());
    }

    private boolean checkStateAccess(User user, Group group) {
        return user.getRoles().stream()
                .anyMatch(r -> r.getName().equals(RoleConstants.ROLE_STATE_ADMIN))
                && user.getCountry().getName()
                .equals(group.getDistrict().getCity().getCountry().getName())
                && user.getState().getName()
                .equals(group.getDistrict().getCity().getState().getName());
    }

    private boolean checkCityAccess(User user, Group group) {
        return user.getRoles().stream()
                .anyMatch(r -> r.getName().equals(RoleConstants.ROLE_CITY_ADMIN))
                && user.getCity().getName()
                .equals(group.getDistrict().getCity().getName())
                && user.getState().getName()
                .equals(group.getDistrict().getCity().getState().getName())
                && user.getCountry().getName()
                .equals(group.getDistrict().getCity().getCountry().getName());
    }

    private boolean checkDistrictAccess(User user, Group group) {
        return user.getRoles().stream()
                .anyMatch(r -> r.getName().equals(RoleConstants.ROLE_DISTRICT_ADMIN))
                && user.getDistrict().equals(group.getDistrict())
                && user.getCity().getName()
                .equals(group.getDistrict().getCity().getName())
                && user.getState().getName()
                .equals(group.getDistrict().getCity().getState().getName())
                && user.getCountry().getName()
                .equals(group.getDistrict().getCity().getCountry().getName());
    }

    private boolean checkChapterAccess(User user, Group group) {
        return user.getRoles().stream()
                .anyMatch(r -> r.getName().equals(RoleConstants.ROLE_CHAPTER_ADMIN))
                && group.getUserGroupRoles().stream()
                .anyMatch(ugr -> ugr.getUser().getId().equals(user.getId()));
    }

    private boolean checkOperationPermission(User user, String permission) {
        try {
            String[] parts = permission.split("_");
            if (parts.length != 2) return false;

            OperationType operation = OperationType.valueOf(parts[1]);
            ResourceType resource = ResourceType.valueOf(parts[0]);

            // Check if user has super admin role
            if (checkSuperAdminAccess(user)) {
                return true;
            }

            return user.getRoles().stream()
                    .flatMap(role -> role.getPrivileges().stream())
                    .anyMatch(perm -> perm.getOperation() == operation &&
                            perm.getResourceType() == resource);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}