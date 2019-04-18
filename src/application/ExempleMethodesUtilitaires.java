package application;

import javafx.animation.PathTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

//Note: les classes données ici sont "moches" dans le sens ou aucun
//Layout n'est créé; tous les controls sont placés de façon absolue...
public class ExempleMethodesUtilitaires extends Application {
	@Override
	public void start(Stage stage) {
		//On crée et paramètre un cercle
		Circle circle = new Circle();
		circle.setCenterX(300.0f);
		circle.setCenterY(135.5f);
		circle.setRadius(25.0f);
		circle.setFill(Color.BROWN);
		circle.setStrokeWidth(20);

		//On va faire bouger notre cercle le long d'un chemin.
		//Un chemin est composé de points, et d'une façon de voyager
		//entre ces points.
		Path path = new Path();

		//On crée notre point de départ du chemin...
		MoveTo moveTo = new MoveTo(208, 71);

		//...puis trajet en ligne droite (LineTo) jusqu'à (421, 161).
		LineTo line1 = new LineTo(421, 161);

		//Et les autres lignes droites, jusqu'à retourner au
		//point de départ en (208, 71).
		LineTo line2 = new LineTo(226, 232);
		LineTo line3 = new LineTo(332, 52);
		LineTo line4 = new LineTo(369, 250);
		LineTo line5 = new LineTo(208, 71);

		//On ajoute tous ces éléments au chemin.
		path.getElements().add(moveTo);
		path.getElements().addAll(line1, line2, line3, line4, line5);

		//Puis on crée l'animation qui va suivre ce chemin.
		PathTransition pathTransition = new PathTransition();

		//Le chemin doit être parcouru en 1 seconde.
		pathTransition.setDuration(Duration.millis(1000));

		//Il sera parcouru par le cercle.
		pathTransition.setNode(circle);

		//Enfin, on affecte le chemin précédemment défini à l'animation.
		pathTransition.setPath(path);

		//Pas très intéressant ici... Sert juste à ce que la figure (le cercle)
		//soit toujours orthogonal au chemin.
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

		pathTransition.setCycleCount(50);
		pathTransition.setAutoReverse(false);

		//On crée un bouton Play.
		Button playButton = new Button("Play");
		playButton.setLayoutX(300);
		playButton.setLayoutY(250);

		//On utilise la méthode utilitaire setOnMouseClicked() pour directement affecter un
		//nouvel EventHandler. Ici encore on utilise la définition d'une classe anonyme,
		//héritant de la classe template EventHandler<MouseEvent> (ce qui est logique étant donné
		//que ola méthode est "setOnMouseClicked"), puis on implémente la méthode Handle.

		//CETTE FAÇON DE FAIRE EST LA PLUS COURANTE EN PROGRAMMATION JAVA !
		//À RETENIR !
		circle.setOnMouseClicked (new EventHandler<javafx.scene.input.MouseEvent>() {
			@Override
			public void handle(javafx.scene.input.MouseEvent e) {
				System.out.println("Hello World !");
				circle.setFill(Color.DARKSLATEBLUE);
			}
		});

		//Ici de même on utilise la méthode utilitaire setOnMouseClicked() pour affecter
		//au bouton le déclenchement de l'animation.
		playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("Hello Wordl !");
				pathTransition.play();
			}

		});

		//On crée le bouton Stop
		Button stopButton = new Button("Stop");
		stopButton.setLayoutX(250);
		stopButton.setLayoutY(250);

		//Et on utilise la méthode utilitaire setOnMouseClicked() pour affecter au
		//bouton Stop l'action d'arrêter l'animation.
		stopButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("Hello World !");
				pathTransition.stop();
			}
		});

		//Puis le reste...
		Group root = new Group(circle, playButton, stopButton);
		Scene scene = new Scene (root, 600, 300);
		scene.setFill(Color.LAVENDER);
		stage.setTitle("Convenience Methods Example");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String args[]) {
		launch(args);
	}

}
