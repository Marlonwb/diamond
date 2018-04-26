package person.marlon.diamond.service.observerable;

import java.util.UUID;

public class Observer {
    private String id;
    private String name;
    private boolean state = false;

    public Observer(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public void update(){
        this.state = true;
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
}
