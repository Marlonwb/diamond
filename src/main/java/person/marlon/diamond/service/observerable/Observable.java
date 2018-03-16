package person.marlon.diamond.service.observerable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;

public class Observable {
    private ConcurrentHashMap<String,Observer> observers;

    public Observable() {
        observers = new ConcurrentHashMap<>();
    }

    public void addObserver(Observer observer){
        if(observer == null){
            throw new NullPointerException();
        }
        if(!observers.containsKey(observer.getId())){
            observers.put(observer.getId(),observer);
        }
    }

    public void removeObserver(Observer observer){
        if(observers.containsKey(observer.getId())){
            observers.remove(observer.getId());
        }
    }

    public void notifyToSome(Condition condition){
        List<Observer> partObservers = null;
        //TODO
    }

    public void notifyToAll(){
        for(Map.Entry<String,Observer> entry : observers.entrySet()){
            entry.getValue().update();
        }
    }
}
