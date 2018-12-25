package ng.haqqq.a9jacars;

import java.util.Date;

public class User {
    String email;
    String fullName;
    String phone;
    String pic;

    Date sessionExpiryDate;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPic(String pic){ this.pic = pic;}
    public void setSessionExpiryDate(Date sessionExpiryDate) {
        this.sessionExpiryDate = sessionExpiryDate;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }
    public String getPic(){ return pic;}
    public Date getSessionExpiryDate() {
        return sessionExpiryDate;
    }



    public String getPhone(){
        return phone;
    }
}
