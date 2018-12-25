package ng.haqqq.a9jacars;

public class AdsModel {

    private String title;
    private String description;
    private String price;

    private String pic;
    private String category;


    public AdsModel() {
    }

    public AdsModel(String title, String description, String price, String pic, String category) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.pic = pic;
        this.category = category;

    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }


    public String getPic() {
        return pic;
    }

    public String getCategory() {
        return category;
    }



    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
