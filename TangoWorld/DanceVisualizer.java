package TangoWorld;

import TangoWorld.Moves.Move;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Arrays;
import java.util.Optional;

import java.util.List;

public class DanceVisualizer extends Application {
	// The canvas
	Pane root = new Pane();
	
	public static List<Object> getUserInput() {
		// Create dialog
		Dialog<List<Object>> dialog = new Dialog<>();
		dialog.setTitle("Tango Dance Configuration");
		dialog.setHeaderText("Enter dance parameters:");
		
		// Create input fields
		TextField songField = new TextField("Gato");
		TextField movesField = new TextField("100");
		TextField seedField = new TextField("316");
		
		// Layout
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.add(new Label("Song:"), 0, 0);
		grid.add(songField, 1, 0);
		grid.add(new Label("Number of moves:"), 0, 1);
		grid.add(movesField, 1, 1);
		grid.add(new Label("Seed:"), 0, 2);
		grid.add(seedField, 1, 2);
		
		dialog.getDialogPane().setContent(grid);
		
		// Buttons
		ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okButton);
		
		// Convert result to List
		dialog.setResultConverter(button -> {
			if (button == okButton) {
				return Arrays.asList(
						songField.getText().isEmpty() ? "Gato" : songField.getText(),
						movesField.getText().isEmpty() ? 100 : Integer.parseInt(movesField.getText()),
						seedField.getText().isEmpty() ? 316L : Long.parseLong(seedField.getText())
				);
			}
			return null;
		});
		
	
		// Show dialog and wait
		Optional<List<Object>> result = dialog.showAndWait();
		
		// if the X button is clicked
		if (result.isEmpty()) {
			Platform.exit();
			System.exit(0);
		}
		
		return result.orElse(Arrays.asList("Gato", 100, 316L));
	}
	
	@Override
	public void start(Stage primaryStage) {
		// Prompt user to enter song name, # of moves, and a # for the seed
		List<Object> inputs = getUserInput();
		String song = (String) inputs.get(0);
		int numberOfMoves = (int) inputs.get(1);
		long seed = (long) inputs.get(2);
		
		// Create Tango Object & a dance sequence
		Tango tango = new Tango(song);
		List<Move> moves = tango.createDance(numberOfMoves, seed);
		
		// Make root pane big enough
		int width = 9000;
		int height = 9000;
		root.setMinSize(width, height);
		
		// Position of the ROOT node
		double startX = (double) width / 2;
		double parentX = startX;
		double startY = 50;
		double verticalSpacing = 250;
		double horizontalSpacing = 90;
		double parentY = startY;
		
		for (int i = 0; i < moves.size()-1; i++) {
			Move parentMove = moves.get(i);
			Move childMove = moves.get(i + 1);
			
			String label = String.valueOf(i + 1);
			
			// Add parent to screen
			GraphNode parentNode = new GraphNode(parentMove, parentX, parentY, Color.RED);
			root.getChildren().addAll(parentNode, parentNode.getLabel());
			
			// Get Children
			List<Move> possibleNextMoves = parentMove.getPossibleNextMoves();
			
			// Check if next Move in the dance sequence IS a child of the Parent node or not
			boolean isChildOfParent = false;
			for (Move possibleNextMove : possibleNextMoves) {
				if (possibleNextMove.getName().equals(childMove.getName())) {
					isChildOfParent = true;
					break;
				}
			}
			if(isChildOfParent){
				// Calculate how to center the parent's children nodes
				double totalChildWidth = (possibleNextMoves.size() - 1) * horizontalSpacing;
				double firstChildX = parentX - (totalChildWidth / 2);
				
				double childX;
				double childY = parentY + verticalSpacing;
				// Display children nodes
				for(int j = 0; j < possibleNextMoves.size(); j++){
					Move pnm = possibleNextMoves.get(j);
					childX = firstChildX + (j * horizontalSpacing);
					
					GraphNode childNode = new GraphNode(pnm, childX, childY, Color.DARKBLUE);
					
					if(pnm.getName().equals(childMove.getName())){
						// RED line and outline
						drawParentChildLine(parentNode, childNode, Color.RED, label);
						// shift parentX
						parentX = childX;
						parentY = childY;
						
						// No need to show this child, since next Move, it will overlap
					}else{
						root.getChildren().addAll(childNode, childNode.getLabel());
						// draw a BLACK line from parent to child
						drawParentChildLine(parentNode, childNode, Color.BLACK, "");
					}
				}
			
			}else{
				// nextMove is NOT a child of parent
				// Keep parentX the same
				// Shift parentY down
				parentY += verticalSpacing;
				GraphNode childNode = new GraphNode(childMove, parentX, parentY, Color.RED);
				drawParentChildLine(parentNode, childNode, Color.RED, label);
			}
		}
		
		// AFTER loop, draw the FINAL node (no children)
		Move finalMove = moves.getLast();
		// Assume parentX and parentY were properly updated in the for loop's last iteration
		GraphNode finalNode = new GraphNode(finalMove, parentX, parentY, Color.RED);
		root.getChildren().addAll(finalNode, finalNode.getLabel());
		
		
		// Show window
		StackPane container = new StackPane();
		
		// Wrap in ScrollPane
		ScrollPane scrollPane = new ScrollPane(root);
		scrollPane.setPannable(true); // Enable drag-to-pan
		scrollPane.setHvalue(0.5);
		
		Pane overlay = new Pane();
		overlay.setMouseTransparent(true); // Click through to scroll pane
		
		Label songLabel = new Label("Song: " + tango.getSong());
		songLabel.setStyle("-fx-font-size: 50px; -fx-padding: 10;");
		
		// Position in overlay's bottom-right
		StackPane.setAlignment(songLabel, Pos.BOTTOM_RIGHT);
		StackPane.setMargin(songLabel, new Insets(11));
		container.getChildren().addAll(scrollPane, overlay, songLabel);
		
		// Create Scene with ScrollPane instead of root
		// Scene scene = new Scene(scrollPane, 1500, 700);
		Scene scene = new Scene(container, 1500, 700);
		primaryStage.setTitle("Tango Dance Sequence Visualizer");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void drawParentChildLine(GraphNode parent, GraphNode child, Color color, String labelText) {
		// Create line from parent center to child center
		javafx.scene.shape.Line line = new javafx.scene.shape.Line(
				parent.getCenterX(), parent.getCenterY(),
				child.getCenterX(), child.getCenterY()
		);
		
		// Style the line
		line.setStroke(color);
		line.setStrokeWidth(1.5);
		
		// Add line BEHIND nodes
		root.getChildren().add(line);
		line.toBack();
		
		if (labelText != null && !labelText.isEmpty()) {
			Text label = new Text(labelText);
			
			// Calculate midpoint
			double midX = (parent.getCenterX() + child.getCenterX()) / 2;
			double midY = (parent.getCenterY() + child.getCenterY()) / 2;
			
			// Center text
			label.setX(midX - label.getLayoutBounds().getWidth() / 2);
			label.setY(midY - 5); // Slight offset
			
			// Style
			label.setFill(Color.BLACK); //color
			label.setFont(Font.font(20)); //size
			
			root.getChildren().add(label);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}