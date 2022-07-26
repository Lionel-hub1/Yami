package lio.foody.yami;

public class User {
    String  usrImage;
    String usrFirstName;
    String usrLastName;
    String account_Status;
    String usrPhone;
    String usrCountry;
    String usrDistrict;
    String usrEmail;
    String usrPassword;
    String usrCPassword;

    public User() {
    }

    public User(String usrImage, String usrFirstName, String usrLastName, String account_Status, String usrPhone, String usrCountry, String usrDistrict, String usrEmail, String usrPassword, String usrCPassword) {
        this.usrImage = usrImage;
        this.usrFirstName = usrFirstName;
        this.usrLastName = usrLastName;
        this.account_Status = account_Status;
        this.usrPhone = usrPhone;
        this.usrCountry = usrCountry;
        this.usrDistrict = usrDistrict;
        this.usrEmail = usrEmail;
        this.usrPassword = usrPassword;
        this.usrCPassword = usrCPassword;
    }

    public User(String usrImage, String usrFirstName, String usrLastName, String account_Status, String usrPhone, String usrCountry, String usrDistrict, String usrEmail) {
        this.usrImage = usrImage;
        this.usrFirstName = usrFirstName;
        this.usrLastName = usrLastName;
        this.account_Status = account_Status;
        this.usrPhone = "+"+usrPhone;
        this.usrCountry = usrCountry;
        this.usrDistrict = usrDistrict;
        this.usrEmail = usrEmail;
    }

    public String getUsrImage() {
        return usrImage;
    }

    public void setUsrImage(String usrImage) {
        this.usrImage = usrImage;
    }

    public String getUsrFirstName() {
        return usrFirstName;
    }

    public void setUsrFirstName(String usrFirstName) {
        this.usrFirstName = usrFirstName;
    }

    public String getUsrLastName() {
        return usrLastName;
    }

    public void setUsrLastName(String usrLastName) {
        this.usrLastName = usrLastName;
    }

    public String getAccount_Status() {
        return account_Status;
    }

    public void setAccount_Status(String account_Status) {
        this.account_Status = account_Status;
    }

    public String getUsrPhone() {
        return usrPhone;
    }

    public void setUsrPhone(String usrPhone) {
        this.usrPhone = usrPhone;
    }

    public String getUsrCountry() {
        return usrCountry;
    }

    public void setUsrCountry(String usrCountry) {
        this.usrCountry = usrCountry;
    }

    public String getUsrDistrict() {
        return usrDistrict;
    }

    public void setUsrDistrict(String usrDistrict) {
        this.usrDistrict = usrDistrict;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public String getUsrCPassword() {
        return usrCPassword;
    }

    public void setUsrCPassword(String usrCPassword) {
        this.usrCPassword = usrCPassword;
    }
}
