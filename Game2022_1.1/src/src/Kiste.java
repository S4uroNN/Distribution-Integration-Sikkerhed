package src;

public class Kiste {

    private String name;

    private int point;

    private pair position;


    public Kiste(String name,  int point, pair position){
        this.name = name;
        this.point = point;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    public pair getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setPosition(pair position) {
        this.position = position;
    }
}
