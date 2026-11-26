package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orgID;

    @Column(name = "org_name", nullable = false, length = 150)
    private String orgName;

    @Column(name = "org_email", nullable = false, unique = true, length = 150)
    private String orgEmail;

    @Column(name = "org_address", length = 255)
    private String orgAddress;

    @Column(name = "org_phone", length = 20)
    private String orgPhone;

    public Organization() {
    }

    public Long getOrgID() {
        return orgID;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone;
    }
}