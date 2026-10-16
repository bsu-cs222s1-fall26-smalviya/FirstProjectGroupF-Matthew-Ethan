package edu.bsu.cs;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    private TextField articleInput;
    private Button submitButton;
    private Label statusLabel;
    private ListView<String> revisionListView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Wikipedia Revision Reporter");

        articleInput = new TextField();
        articleInput.setPromptText("Enter Wikipedia article name");

        submitButton = new Button("Get Revisions");

        statusLabel = new Label();

        revisionListView = new ListView<>();

        submitButton.setOnAction(e -> handleSearch());

        HBox inputBox = new HBox(10, articleInput, submitButton);
        inputBox.setPadding(new Insets(10));

        VBox root = new VBox(10, inputBox, statusLabel, revisionListView);
        root.setPadding(new Insets(10));

        submitButton.setDefaultButton(true);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleSearch() {
        String articleName = articleInput.getText().trim();

        if (articleName.isEmpty()) {
            showAlert("Error", "No article name was entered.");
            return;
        }

        setInteractionEnabled(false);
        statusLabel.setText("Querying Wikipedia...");
        revisionListView.getItems().clear();

        Task<RevisionReportResult> task = new Task<>() {
            @Override
            protected RevisionReportResult call() {
                WikipediaQueryBuilder queryBuilder = new WikipediaQueryBuilder();
                WikipediaClient client = new WikipediaClient(queryBuilder);
                RedirectParser redirectParser = new RedirectParser();
                RevisionParser revisionParser = new RevisionParser();
                RevisionReportService service = new RevisionReportService(client, redirectParser, revisionParser);
                return service.getReportForArticle(articleName);
            }
        };

        task.setOnSucceeded(e -> {
            setInteractionEnabled(true);
            statusLabel.setText("");
            RevisionReportResult result = task.getValue();

            if (result == null) {
                showAlert("Network Error", "Failed to connect to Wikipedia.");
                return;
            }

            if (result.hasRedirect()) {
                statusLabel.setText("Redirected to: " + result.getRedirectTarget());
            }

            if (result.isEmpty()) {
                revisionListView.getItems().add("Wikipedia article not found.");
                return;
            }

            RevisionFormatter formatter = new RevisionFormatter();
            List<Revision> revisions = result.getRevisions();
            for (int i = 0; i < revisions.size(); i++) {
                String formatted = (i + 1) + "  " + formatter.format(revisions.get(i));
                revisionListView.getItems().add(formatted);
            }
        });

        task.setOnFailed(e -> {
            setInteractionEnabled(true);
            statusLabel.setText("");
            showAlert("Error", "An unexpected error occurred during the query.");
        });

        new Thread(task).start();
    }

    private void setInteractionEnabled(boolean enabled) {
        articleInput.setDisable(!enabled);
        submitButton.setDisable(!enabled);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


