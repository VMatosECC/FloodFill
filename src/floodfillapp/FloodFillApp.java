/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floodfillapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/* **********************************************************************
The flood-fill algorithm is used to fill a defined bounded region with a 
specific color or pattern. The following implementation uses recursion; 
however, it could also be implemented proceduraly using queues.
It commonly occurs in applications like paint programs, image editing, 
and graphics software to fill regions, draw shapes, and perform various 
editing tasks. 
 ******************************************************************** */
public class FloodFillApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Set the stage with the GUI declared in thye file FloodFillScene.fxml
        Parent root = FXMLLoader.load(getClass().getResource("FloodFillScene.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
