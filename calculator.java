
package hellofx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.*;
import javafx.stage.Stage;
import  javafx.scene.text.Font;
import javafx.scene.paint.Color;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;


public class keypad extends Application 
{
    String str="";
    
    @Override
    
    public void start(Stage primaryStage) 
    {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        int numRows = 4 ;
        int numColumns = 6 ;
        for (int row = 0 ; row < numRows ; row++ )
        {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        for (int col = 0 ; col < numColumns; col++ ) 
        {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }
        grid.setVgap(5); 
        grid.setHgap(5);
        grid.setMinSize(400, 400);
//        grid.setGridLinesVisible(true);
        
        // col , row
        Label res = new Label("Najaf's Calci");
        res.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        grid.add(res, 0,0,4,1);
        Label response = new Label("Output:");
        response.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        grid.add(response, 0, 5,3,1);
        
        for (int i = 0 ; i < 9 ; i++) 
        {
            Button button = createButton(Integer.toString(i+1),response);
            
            grid.add(button, i % 3, i/3+1); //(0,0)(1,0)(2,0)(0,1)(1,1)(2,1)(0,2)(1,2)(2,2)
        }
        
        grid.add(createButton("✄",response), 0, 4);
        grid.add(createButton("0",response), 1, 4);
        grid.add(createButton(".",response), 2, 4);  // 4 row // 3 col
        
        grid.add(createButton("+",response), 3, 1);
        grid.add(createButton("-",response), 3, 2);
        grid.add(createButton("*",response), 3, 3);
        grid.add(createButton("/",response), 3, 4);
        grid.add(createButton("=",response), 3, 5);
        
        Scene scene = new Scene(grid);
        scene.getStylesheets().add("style.css"); 
        primaryStage.setTitle("calculator"); 
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Button createButton(String text,Label l)
    {
        Button button = new Button(text);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        // button.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        button.setOnAction((e) -> {System.out.println(text);
        
                                    if(button.getText()=="=")
                                    {
                                        try
                                        {
                                            str = engine.eval(str).toString();
                                            l.setText(str);
                                            str="";
                                        }
                                        catch(Exception ex)
                                        {
                                            l.setText("Wrong Expression");
                                            str="";
                                        }
                                        
                                    }
                                    else if(button.getText()=="✄")
                                    {
                                        if(str.length()>0)
                                        str=str.substring(0,str.length()-1);
                                        l.setText(str);
                                    }
                                    else
                                    {
                                        str=str+button.getText();
                                        l.setText(str);
                                    }
//                                    l.setAlignment(Pos.CENTER);
                                   }
                                   );
        return button ;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
