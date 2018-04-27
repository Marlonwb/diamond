package person.marlon.diamond.common.demo.model;

import java.util.*;

public class Content {

    private String scene;

    private  List<Option> optionList;

    private String jumpContentKey;

    public Content(String scene,Option...os) {
        this.scene = scene;
        optionList = new ArrayList<>();
        Collections.addAll(optionList, os);
    }

    public Content(String scene, String jumpContentKey) {
        this.scene = scene;
        this.jumpContentKey = jumpContentKey;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }
}
