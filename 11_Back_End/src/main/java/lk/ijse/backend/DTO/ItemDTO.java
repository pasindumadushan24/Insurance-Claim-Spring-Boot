package lk.ijse.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class ItemDTO {



    private String iid;
    private String iName;
    private int qty;
    private int price;

    public ItemDTO(String iid, String iName, int qty, int price) {
        this.iid = iid;
        this.iName = iName;
        this.qty = qty;
        this.price = price;
    }

    public String getIid() {
        return iid;
    }

    public String getIName() {
        return iName;
    }

    public int getQty() {
        return qty;
    }

    public int getPrice() {
        return price;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public void setIName(String iName) {
        this.iName = iName;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}


