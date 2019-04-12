package com.company.project.auth.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "auth_permission")
public class Permission implements Serializable {
    /**
     * 权限ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 菜单/按钮名称
     */
    @Column(name = "permission_name")
    private String permissionName;

    /**
     * 权限编码
     */
    @Column(name = "permission_code")
    private String permissionCode;

    /**
     * 描述
     */
    private String remark;

    /**
     * 父级ID
     */
    @Column(name = "parent_id")
    private Integer parentId;

    private List<Permission> children;

    private static final long serialVersionUID = 1L;

    /**
     * 获取权限ID
     *
     * @return id - 权限ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置权限ID
     *
     * @param id 权限ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取菜单/按钮名称
     *
     * @return permission_name - 菜单/按钮名称
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * 设置菜单/按钮名称
     *
     * @param permissionName 菜单/按钮名称
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /**
     * 获取权限编码
     *
     * @return permission_code - 权限编码
     */
    public String getPermissionCode() {
        return permissionCode;
    }

    /**
     * 设置权限编码
     *
     * @param permissionCode 权限编码
     */
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    /**
     * 获取描述
     *
     * @return remark - 描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置描述
     *
     * @param remark 描述
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取父级ID
     *
     * @return parent_id - 父级ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父级ID
     *
     * @param parentId 父级ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }
}