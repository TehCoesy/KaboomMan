package Entities.Statics;

public class PowerUp extends StaticEntity{
    private String type;

    public PowerUp(String type) {
        this.type = type;
    }

    @Override
    public void kill() {
        this.dead = true;
        this.collidable = false;
    }

    public String eat() {
        this.done = true;
        return this.type;
    }

    public String getType() { return this.type; }
}
