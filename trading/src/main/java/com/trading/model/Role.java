package com.trading.model;

import java.util.Objects;

public class Role {

    private int roleId;
    private String role;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return roleId == role1.roleId &&
                Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, role);
    }
}
