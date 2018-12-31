package ng.haqqq.a9jacars;

public class AdsDetailAdapter {

    public String ImageServerUrl;
    public String ImageTitleName;
    public String price;
    public String location;
    public int id;
    public String description;
    public String category;
    public String type;
    public String userid;
    public String featured;
    public String phone;
    public String email;

    public String getImageServerUrl() {
        return ImageServerUrl;
    }

    public void setImageServerUrl(String imageServerUrl) {
        this.ImageServerUrl = imageServerUrl;
    }

    public String getImageTitleName() {
        return ImageTitleName;
    }

    public void setImageTitleName(String Imagetitlename) {
        this.ImageTitleName = Imagetitlename;
    }

    public String getPrice(){ return price;}
    public void setPrice(String price){ this.price = price;}

    public String getLocation(){ return price;}
    public void setLocation(String location){ this.location = location;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){ this.description = description;}


    public String getCategory() {
        return category;
    }

    public void setCategory(String category){ this.category = category;}

    public String getType() {
        return type;
    }

    public void setType(String type){ this.type = type;}

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid){ this.userid = userid;}

    public int getId() {
        return id;
    }

    public void setId(int id){ this.id = id;}

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured){ this.featured= featured;}
}