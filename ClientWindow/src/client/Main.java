package client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene scene;
    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("   WG-Plan.24");
        button = new Button("Bestätigen");
        primaryStage.getIcons().add(new Image("/client/Download.png"));
        Label methodenAuswahlLabel = new Label("Wählen Sie eine Option aus:");

        //Erstellung Choice Box
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Aufgaben anzeigen", "Aufgabe hinzufügen", "Aufgabe ändern", "Aufgabe löschen");

        //GET
        Label idLabel = new Label("Gewünschte Aufgaben ID eingeben:");
        TextField idInput = new TextField();
        ScrollPane getScrall = new ScrollPane();
        getScrall.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        //POST
        Label namePostLabel = new Label("Name des Beauftragten:");
        Label aufTitelPostLabel = new Label("Titel der Aufgabe:");
        Label aufBeschrPostLabel = new Label("Beschreibung der Aufgabe:");
        TextField namePostInput = new TextField();
        TextField aufTitelPostInput = new TextField();
        TextField aufBeschrPostInput = new TextField();
        Button button1 = new Button("Speichern");


        //Labels für PUT anfrage ( nur zum auf Done setzen)
        Label idPutLabel = new Label("Bitte ID eintragen der zu ändernden Aufgabe eintragen:");
        TextField idPutInput = new TextField();
        Label namePutLabel = new Label("Neuer Name des Beauftragten:");
        Label aufTitelPutLabel = new Label("Neuer Titel der Aufgabe:");
        Label aufBeschrPutLabel = new Label("Neue Beschreibung der Aufgabe:");
        TextField namePutInput = new TextField();
        TextField aufTitelPutInput = new TextField();
        TextField aufBeschrPutInput = new TextField();
        CheckBox done = new CheckBox("Erledigt");
        CheckBox notDone = new CheckBox("Zu erledigen");
        Button save = new Button("Speichern");

        //DELETE
        Label idDeleteLabel = new Label("Bitte geben Sie die ID der zu löschenden Aufgabe ein:");
        Button delete = new Button("Aufgabe Löschen");


        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        Label outputLabel = new Label();

        getScrall.setContent(outputLabel);
        Requests requests1 = new Requests();
        layout.getChildren().addAll(methodenAuswahlLabel, choiceBox);
        layout.getChildren().add(getScrall);
        requests1.get(0);
        outputLabel.setText(requests1.drucker(requests1.getDruckerInput()));

        //Das wird ausgeführt wenn man etwas auswählt in der Choice Box
        choiceBox.setOnAction(e -> {
            layout.getChildren().removeAll(namePostLabel, namePostInput, aufTitelPostLabel, aufTitelPostInput, aufBeschrPostLabel, aufBeschrPostInput, idLabel, idInput, button1, button, idPutLabel, idPutInput, done, notDone, save, namePutLabel, namePutInput, aufTitelPutLabel, aufTitelPutInput, aufBeschrPutLabel, aufBeschrPutInput, idDeleteLabel, delete, outputLabel, getScrall);
            String methoden = choiceBox.getValue();
            Requests requests = new Requests();

            //Switch Case für die Choice Box
            switch (methoden) {
                case "Aufgaben anzeigen":
                    layout.getChildren().addAll(idLabel, idInput, button, getScrall);
                    button.setOnAction(a -> {
                        requests.get(Integer.parseInt(idInput.getText()));
                        outputLabel.setText(requests.drucker(requests.getDruckerInput()));
                        idInput.clear();
                    });
                    break;

                case "Aufgabe hinzufügen":
                    layout.getChildren().addAll(namePostLabel, namePostInput, aufTitelPostLabel, aufTitelPostInput, aufBeschrPostLabel, aufBeschrPostInput, button1);
                    button1.setOnAction(d -> {
                        requests.post(namePostInput.getText(), aufTitelPostInput.getText(), aufBeschrPostInput.getText(), false);
                        namePostInput.clear();
                        aufTitelPostInput.clear();
                        aufBeschrPostInput.clear();
                    });
                    break;

                case "Aufgabe ändern":
                    layout.getChildren().addAll(idPutLabel, idPutInput, namePutLabel, namePutInput, aufTitelPutLabel, aufTitelPutInput, aufBeschrPutLabel, aufBeschrPutInput, done, notDone, save, getScrall);
                    requests.get(0);
                    outputLabel.setText(requests.drucker(requests.getDruckerInput()));

                    save.setOnAction(b -> {
                        if (done.isSelected()) {
                            Boolean isDone = true;
                            requests.put(Integer.parseInt(idPutInput.getText()), namePutInput.getText(), aufTitelPutInput.getText(), aufBeschrPutInput.getText(), isDone);
                            requests.get(0);
                            outputLabel.setText(requests.drucker(requests.getDruckerInput()));
                        } else if (notDone.isSelected()) {
                            Boolean isDone = false;
                            requests.put(Integer.parseInt(idPutInput.getText()), namePutInput.getText(), aufTitelPutInput.getText(), aufBeschrPutInput.getText(), isDone);
                            requests.get(0);
                            outputLabel.setText(requests.drucker(requests.getDruckerInput()));
                        }
                        idPutInput.clear();
                        namePutInput.clear();
                        aufTitelPutInput.clear();
                        aufBeschrPutInput.clear();
                        done.setSelected(false);
                        notDone.setSelected(false);
                    });
                    break;

                case "Aufgabe löschen":
                    layout.getChildren().addAll(idDeleteLabel, idInput, delete, getScrall);
                    requests.get(0);
                    outputLabel.setText(requests.drucker(requests.getDruckerInput()));
                    delete.setOnAction(c -> {
                        requests.delete(Integer.parseInt(idInput.getText()));
                        requests.get(0);
                        outputLabel.setText(requests.drucker(requests.getDruckerInput()));
                        idInput.clear();
                    });
                    break;

                default:
                    layout.getChildren().addAll();
                    break;
            }
        });

        scene = new Scene(layout, 600, 900);
        window.setScene(scene);
        window.show();
    }
}
