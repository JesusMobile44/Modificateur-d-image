import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {launch(args); }

    public void start(Stage primaryStage){

        primaryStage.setTitle("Laboratoire 6");
        primaryStage.setResizable(true);
        primaryStage.setMaximized(true);

        BorderPane borderPane = new BorderPane();

        MenuItem image1 = new MenuItem("Image 1");
        MenuItem image2 = new MenuItem("Image 2");
        MenuItem image3 = new MenuItem("Image 3");
        MenuItem reinitialiser = new MenuItem("Réinitialiser");
        Menu charger = new Menu("Charger une image");
        Menu fichiers = new Menu("Fichiers");
        Menu actions = new Menu("Actions");

        fichiers.getItems().add(charger);
        charger.getItems().addAll(image1,image2,image3);
        actions.getItems().add(reinitialiser);

        MenuBar menuBar = new MenuBar(fichiers,actions);
        ContextMenu contextMenu = new ContextMenu(fichiers,actions);

        Image defaultImage = new Image("file:default.jpg");
        Image imageView1 = new Image("file:image1.jpg");
        Image imageView2 = new Image("file:image2.jpg");
        Image imageView3 = new Image("file:image3.jpg");
        ImageView imageView = new ImageView(defaultImage);
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);
        ColorAdjust imageColor = new ColorAdjust();
        imageView.setEffect(imageColor);

        Slider luminosite = new Slider(-1,1,0);
        Slider contraste = new Slider(-1,1,0);
        Slider teinte = new Slider(-1,1,0);
        Slider saturation = new Slider(-1,1,0);
        imageColor.brightnessProperty().bind(luminosite.valueProperty());
        imageColor.contrastProperty().bind(contraste.valueProperty());
        imageColor.hueProperty().bind(teinte.valueProperty());
        imageColor.saturationProperty().bind(saturation.valueProperty());

        Label lumiLabel = new Label("Luminosité");
        Label contLabel = new Label("Contraste");
        Label teinLabel = new Label("Teinte");
        Label satuLabel = new Label("Saturation");

        Label texteBas = new Label("Bienvenue dans le modificateur d'image!");
        texteBas.setPadding(new Insets(20));

        Tooltip tooltipLumi = new Tooltip("Rend l'image plus clair ou plus sombre");
        Tooltip tooltipCont = new Tooltip("Diminue ou augmente la différence entre les couleurs");
        Tooltip tooltipTein = new Tooltip("Change la teinte(couleur) de l'image");
        Tooltip tooltipSatu = new Tooltip("Diminue ou augmente l'intensité des couleurs");
        luminosite.setTooltip(tooltipLumi);
        contraste.setTooltip(tooltipCont);
        teinte.setTooltip(tooltipTein);
        saturation.setTooltip(tooltipSatu);

        image1.setOnAction(event -> {
            imageView.setImage(imageView1);
            texteBas.setText("image 1 chargée");
        });

        image2.setOnAction(event -> {
            imageView.setImage(imageView2);
            texteBas.setText("image 2 chargée");
        });

        image3.setOnAction(event -> {
            imageView.setImage(imageView3);
            texteBas.setText("image 3 chargée");
        });

        reinitialiser.setOnAction(event -> {
            luminosite.setValue(0);
            contraste.setValue(0);
            teinte.setValue(0);
            saturation.setValue(0);
            imageView.setImage(defaultImage);
            texteBas.setText("image réinitialisée");
        });

        //Groups and shizzles===========================================================================================
        VBox sliders = new VBox(lumiLabel,luminosite,contLabel,contraste,teinLabel,teinte,satuLabel,saturation);
        sliders.setAlignment(Pos.CENTER);
        sliders.setSpacing(10);

        HBox hb = new HBox(imageView,sliders);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(20);

        borderPane.setBottom(texteBas);
        borderPane.setCenter(hb);
        borderPane.setTop(menuBar);
        borderPane.setOnContextMenuRequested(event -> {
            contextMenu.show(borderPane,event.getScreenX(),event.getScreenY());
        });

        Scene primaryScene = new Scene(borderPane);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
}
