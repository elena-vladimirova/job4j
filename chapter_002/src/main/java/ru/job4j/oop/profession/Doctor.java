package ru.job4j.oop.profession;

import java.util.Date;

public class Doctor extends Profession {

    private Date licenseExpireDate;

    public Date getLicenseExpireDate() {
        return licenseExpireDate;
    }

    public void setLicenseExpireDate(Date licenseExpireDate) {
        this.licenseExpireDate = licenseExpireDate;
    }
}
