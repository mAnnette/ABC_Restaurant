package abc_restaurant.model;

import java.io.Serializable;

public class Facility implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
    private String name;
    private String description;
    private String image;
    private String location;

    public Facility() {}

    
    public Facility(int id, String name, String description, String image, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.location = location;
    }

    // Getters and Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

}
