package abc_restaurant.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class MenuItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
    private String name;
    private BigDecimal price;
    private String description;
    private int categoryId;
    private String categoryName; 
    private String image;
    
    
    public MenuItem() {
       
    }
    

    public MenuItem(int id, String name, BigDecimal price, String description, int categoryId, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.image = image;
    }

 
    public int getId() { 
        return id; 
    }

    public void setId(int id) { 
        this.id = id; 
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public BigDecimal getPrice() { 
        return price; 
    }

    public void setPrice(BigDecimal price) { 
        this.price = price; 
    }

    public String getDescription() { 
        return description; 
    }

    public void setDescription(String description) { 
        this.description = description; 
    }

    public int getCategoryId() { 
        return categoryId; 
    }

    public void setCategoryId(int categoryId) { 
        this.categoryId = categoryId; 
    }

    public String getCategoryName() { 
        return categoryName; 
    }

    public void setCategoryName(String categoryName) { 
        this.categoryName = categoryName; 
    }
    
    public String getImage() { 
    	return image; 
    }
    
    public void setImage(String image) { 
    	this.image = image; 
    }
	

}
