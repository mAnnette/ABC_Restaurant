package abc_restaurant.model;

import java.io.Serializable;

public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

    private int serviceId;  
    private String serviceName;
    private String description;
    private String image;

    
    public Service() {}

  
    public Service(int serviceId, String serviceName, String description, String image) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.description = description;
        this.image = image;
    }

 
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    @Override
    public String toString() {
        return "Service{" +
                "serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

}
