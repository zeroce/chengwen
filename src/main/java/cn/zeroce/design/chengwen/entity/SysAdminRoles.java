package cn.zeroce.design.chengwen.entity;

public class SysAdminRoles {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_admin_roles.id
     *
     * @mbg.generated Fri Mar 20 21:11:16 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_admin_roles.sys_admin_id
     *
     * @mbg.generated Fri Mar 20 21:11:16 CST 2020
     */
    private Integer sysAdminId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_admin_roles.sys_roles_id
     *
     * @mbg.generated Fri Mar 20 21:11:16 CST 2020
     */
    private Integer sysRolesId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_admin_roles.sys_admin_username
     *
     * @mbg.generated Fri Mar 20 21:11:16 CST 2020
     */
    private String sysAdminUsername;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_admin_roles.id
     *
     * @return the value of sys_admin_roles.id
     *
     * @mbg.generated Fri Mar 20 21:11:16 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_admin_roles.id
     *
     * @param id the value for sys_admin_roles.id
     *
     * @mbg.generated Fri Mar 20 21:11:16 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_admin_roles.sys_admin_id
     *
     * @return the value of sys_admin_roles.sys_admin_id
     *
     * @mbg.generated Fri Mar 20 21:11:16 CST 2020
     */
    public Integer getSysAdminId() {
        return sysAdminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_admin_roles.sys_admin_id
     *
     * @param sysAdminId the value for sys_admin_roles.sys_admin_id
     *
     * @mbg.generated Fri Mar 20 21:11:16 CST 2020
     */
    public void setSysAdminId(Integer sysAdminId) {
        this.sysAdminId = sysAdminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_admin_roles.sys_roles_id
     *
     * @return the value of sys_admin_roles.sys_roles_id
     *
     * @mbg.generated Fri Mar 20 21:11:16 CST 2020
     */
    public Integer getSysRolesId() {
        return sysRolesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_admin_roles.sys_roles_id
     *
     * @param sysRolesId the value for sys_admin_roles.sys_roles_id
     *
     * @mbg.generated Fri Mar 20 21:11:16 CST 2020
     */
    public void setSysRolesId(Integer sysRolesId) {
        this.sysRolesId = sysRolesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_admin_roles.sys_admin_username
     *
     * @return the value of sys_admin_roles.sys_admin_username
     *
     * @mbg.generated Fri Mar 20 21:11:16 CST 2020
     */
    public String getSysAdminUsername() {
        return sysAdminUsername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_admin_roles.sys_admin_username
     *
     * @param sysAdminUsername the value for sys_admin_roles.sys_admin_username
     *
     * @mbg.generated Fri Mar 20 21:11:16 CST 2020
     */
    public void setSysAdminUsername(String sysAdminUsername) {
        this.sysAdminUsername = sysAdminUsername == null ? null : sysAdminUsername.trim();
    }
}