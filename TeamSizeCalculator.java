import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.scene.control.Dialog;
import javafx.util.Pair;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class TeamSizeCalculator extends Application {
	private String masterUsername = "csc200";
	private String masterPassword = "password";

	@Override
	public void start(Stage primaryStage) {
		Dialog<Pair<String,String>> dialog = new Dialog<>();
		dialog.setTitle("Login Dialog");
		dialog.setHeaderText("Login to use Team Size Calculator");
		dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		
		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(10);

		TextField username = new TextField();
		username.setPromptText("Username");
		
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		grid.add(new Label("Username:"),0,0);
		grid.add(username,1,0);
		grid.add(new Label("Password:"),0,1);
		grid.add(password,1,1);

		dialog.getDialogPane().setContent(grid);

		dialog.setResultConverter(dialogButton-> {
			if(dialogButton == loginButtonType) {
				return new Pair<>(username.getText(), password.getText());
			}
			return null;
		});
		
			Optional<Pair<String, String>> result;
		do {
			result = dialog.showAndWait();
			username.clear();
			password.clear();
		} while(!result.get().getKey().equals(masterUsername) && !result.get().getValue().equals(masterPassword));
		
		System.out.println("How many people in your team?");

	}

	public static void main(String [] args) {
		launch(args);
	}
}
