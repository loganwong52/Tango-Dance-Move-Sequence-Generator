package TangoWorld;

import TangoWorld.Moves.Move;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.geometry.VPos;


public class GraphNode extends Circle {
	private Text label;
	private Move move;
	
	public GraphNode(Move move, double x, double y, Color perimeterColor) {
		super(x, y, 40);
		this.move = move;
		
		String moveName = move.getName();
		String readableMoveName = moveName.replace(" ", "\n");
		this.label = new Text(readableMoveName);
		
		// Center text inside circle
		label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
		label.setTextOrigin(VPos.CENTER);
		label.setX(x - label.getLayoutBounds().getWidth() / 2);
		label.setY(y);
		
		// Style
		setFill(Color.LIGHTBLUE);
		// setStroke(Color.DARKBLUE);
		setStroke(perimeterColor);
		setStrokeWidth(2);
	}
	public Move getMove() { return move; }
	
	public Text getLabel() { return label; }
}