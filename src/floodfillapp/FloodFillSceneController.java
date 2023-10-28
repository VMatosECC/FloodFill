
package floodfillapp;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;



/**
 * FXML Controller class
 *
 * @author 12164
 */
public class FloodFillSceneController implements Initializable {

    int MAXROW = 6;
    int MAXCOL = 6;
    
    @FXML
    private TextField txtRow;
    @FXML
    private TextField txtColumn;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button btnChangeColor;
    @FXML
    private GridPane grid;
    

    @FXML
    private Label lblStartingCellColor;
    @FXML
    private Label lblTotalChangesCaption;
    @FXML
    private Label lblTotalChanges;

    private String chosenColor  = "#000000";
    private Color oldColor;         // <<<<<<<<<<<<<<<  
    private Color[][] cellColors;
    private int totalChangesCounter = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                // Initialize the grid with random cell colors
      
        
        cellColors = new Color[MAXROW][MAXCOL];
        Random random = new Random();
        for (int row = 0; row < MAXROW; row++) {
            for (int col = 0; col < MAXCOL; col++) {
                Color randomColor = getRandomColor(random);
                cellColors[row][col] = randomColor;
                grid.add(createColoredCell(randomColor), col, row);
            }
        }
    }

        

    @FXML
    private void colorPickerClick(ActionEvent event) {
        chosenColor = colorPicker.getValue().toString();
        
    }

    @FXML
    private void btnChangeColorClick(ActionEvent event) {
        System.out.println("row " + txtRow.getText());
        System.out.println("Col " + txtColumn.getText());
        System.out.println("Chosen color: " + chosenColor);
        lblTotalChanges.setText(++totalChangesCounter + "");
        lblTotalChanges.setVisible(true);
        lblTotalChangesCaption.setVisible(true);
        changeColor();    
    }
    
        private Color getRandomColor(Random random) {
        Color[] possibleColors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE};
        return possibleColors[random.nextInt(possibleColors.length)];
    }

    private Region createColoredCell(Color color) {
        Region cell = new Region();
        cell.setMinSize(75, 75);
        cell.setMaxSize(75, 75);
           
        // Create a white border for the cells
        BorderStroke borderStroke = new BorderStroke(
                Color.WHITE, 
                BorderStrokeStyle.SOLID, 
                null, 
                new BorderWidths(3)
        );
        
        Background myCurrentBackColor = new Background(new BackgroundFill(color, null, null));
        Border whiteBorder = new Border(borderStroke);
        cell.setBorder(whiteBorder);
        cell.setBackground(myCurrentBackColor);
        
        //Add a listener to each cell
        cell.setOnMouseClicked(event -> {
            int clickedColumn = GridPane.getColumnIndex(cell);
            int clickedRow = GridPane.getRowIndex(cell);
            System.out.println("Clicked on cell at Row: " + clickedRow + 
                           ", Column: " + clickedColumn + " Color: " + color);
            txtRow.setText(clickedRow+"");
            txtColumn.setText(clickedColumn+"");
            lblStartingCellColor.setBackground(myCurrentBackColor);
            lblStartingCellColor.setText(color.toString());
        });
        
        return cell;
    }

        private void changeColor() {
        int startRow = Integer.parseInt(txtRow.getText());
        int startCol = Integer.parseInt(txtColumn.getText());
        oldColor = cellColors[startRow][startCol];  // <<<<<<<<<<<<<<<
        Color newColor = colorPicker.getValue();
        floodFill(startRow, startCol, newColor);
    }

    private void floodFill(int row, int col, Color newColor) {
        if (row < 0 || row >= MAXROW || col < 0 || col >= MAXCOL) {
            return;
        }

        if (cellColors[row][col].equals(oldColor)) {
            cellColors[row][col] = newColor;
            grid.add(createColoredCell(newColor), col, row);
            //grid.add(createColoredCell(newColor), row, col);
            
            floodFill(row - 1, col, newColor); // Up
            floodFill(row + 1, col, newColor); // Down
            floodFill(row, col - 1, newColor); // Left
            floodFill(row, col + 1, newColor); // Right

            //In case you also want to include diagonal vicinity
            floodFill(row - 1, col + 1, newColor); // Diagonal: Right Up
            floodFill(row + 1, col + 1, newColor); // Diagonal: Right Down
            floodFill(row - 1, col - 1, newColor); // Diagonal: Left Up
            floodFill(row + 1, col - 1, newColor); // Diagonal: Left Down


        }
    }

    
}//class
