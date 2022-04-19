package dhairyapandya.com.grocforme;

public class model {
    String Name,Price,Quantity,purl,Grade,Wholeseller;
    model()
    {

    }
    public model(String Name, String Price, String Quantity, String purl, String Grade, String Wholeseller) {
        this.Name = Name;
        this.Price = Price;
        this.Quantity = Quantity;
        this.purl = purl;
        this.Grade = Grade;
        this.Wholeseller = Wholeseller;
    }

    public String getWholeseller() {
        return Wholeseller;
    }

    public void setWholeseller(String Wholeseller) {
        this.Wholeseller = Wholeseller;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String Grade) {
        this.Grade = Grade;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
