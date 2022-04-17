package elvi.com.rapixtaxi.models;

public class Driver {
    String id;
    String name;
    String email;
    String vehiculeBrand;
    String vehiclePlate;

    public Driver(String id, String name, String email, String vehiculeBrand, String vehiclePlate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.vehiculeBrand = vehiculeBrand;
        this.vehiclePlate = vehiclePlate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVehiculeBrand() {
        return vehiculeBrand;
    }

    public void setVehiculeBrand(String vehiculeBrand) {
        this.vehiculeBrand = vehiculeBrand;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }
}
