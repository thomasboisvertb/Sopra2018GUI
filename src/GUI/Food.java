package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Food {

    private ImageView imageView;
    private int xCoordinate;
    private int yCoordinate;
    private double xPosition;
    private double yPosition;


    public Food (double side, int x, int y, double xPosition, double yPosition, int qte){

        ImageView imageview = new ImageView();
        Image food;

        switch (qte){
            case 1 : food = new Image("/Ressources/1food.png",side/1.5,side/1.5,true,true); break;
            case 2 : food = new Image("/Ressources/2food.png",side/1.5,side/1.5,true,true); break;
            case 3 : food = new Image("/Ressources/3food.png",side/1.5,side/1.5,true,true); break;
            case 4 : food = new Image("/Ressources/4food.png",side/1.5,side/1.5,true,true); break;
            default : food = new Image("/Ressources/5food.png",side/1.5,side/1.5,true,true); break;
        }

        imageview.setImage(food);
        this.imageView = imageview;

        this.xCoordinate = x;
        this.yCoordinate = y;

        this.xPosition = xPosition;
        this.yPosition = yPosition;

        imageview.setX(this.xPosition-side/1.2);
        imageview.setY(this.yPosition-side/1.8);

    }

    public ImageView getImageView() {
        return imageView;
    }
}
