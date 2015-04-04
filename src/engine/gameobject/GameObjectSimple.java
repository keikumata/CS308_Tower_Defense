package engine.gameobject;

import engine.grid.GridCell;
import engine.pathfinding.EndOfPathException;
import gameobject.Weapon;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 
 * @author Jeremy 
 *
 */
public class GameObjectSimple implements GameObject {

    @XStreamOmitField
    private transient Node myNode;
    private String myImagePath;
    private String myLabel;
    private Point2D myPoint;
    private Health myHealth;
    private Mover myMover;
    private List<Weapon> myWeapons;

//    @Override
//    public void move () {
//        Pointlike point = myMover.move(myPoint.getX(), myPoint.getY());
//    }
//    public void move (double x, double y) {
//        PointSimple point = myMover.move(x, y);
//        myPoint = new Point2D(point.getX(), point.getY());
//        myNode.setTranslateX(transformToScreenX(myPoint.getX()));
//        myNode.setTranslateY(transformToScreenY(myPoint.getY()));
//    }

    @Override
    public void addWeapon (Weapon weapon) {
        myWeapons.add(weapon);
    }

    @Override
    public boolean isDead () {
        return myHealth.isDead();
    }

    @Override
    public void changeHealth (double amount) {
        myHealth.changeHealth(amount);
    }

    // temporary
    public GameObject clone () {
        try {
            return (GameObject) super.clone();
        }
        catch (CloneNotSupportedException e) {
            System.out.println(this.getLabel() + " can't be cloned");
            return null;
        }
    }

    @Override
    public String getLabel () {
        return myLabel;
    }

    @Override
    public PointSimple getPoint () {
        return new PointSimple(myPoint);
    }


    public void initializeNode () {
        Image image = new Image(myImagePath);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        myNode = imageView;

    }
    
    private double transformToScreenX(double X){
        //TODO: Implement this method
        return X;
    }
    
    private double transformToScreenY(double Y) {
        //TODO: Implement this method
        return Y;
    }

    @Override
    public Node getGraphic () {
        return myNode;
    }
    @Override
    public void setSpeed (double speed) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public Weapon getWeapon () {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public GridCell getGridDimensions () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void move () throws EndOfPathException {
        // TODO Auto-generated method stub
        
    }

}
