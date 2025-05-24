package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameExe {
    private static final int TILE_SIZE = 40;
    private static final int W = 800;
    private static final int H = 600;
    
    private static final int X_TILES = W / TILE_SIZE;
    private static final int Y_TILES = H / TILE_SIZE;
    
    private Tile[][] gird = new Tile[X_TILES][Y_TILES];
   //oyun alanı büyüklüğü ve fayansların x ve y düzlemi
    
    private Scene scene;//SPECİAL

    public void setScene(Scene scene) {//1
        this.scene = scene;
    }

    public Scene getScene() {//2
        return scene;
    }// 1-2 yerler scene hataları düzenlemesi ve scene girdisi

    public Parent createContect() {
        
    	Pane root = new Pane();
        root.setPrefSize(W, H);

        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {//fayansların x ve y düzlemde 40ar adet oluştukları
                
            	boolean hasBomb = Math.random() < 0.15;//bomba ihtimali
                Tile tile = new Tile(x, y, hasBomb);
                
                gird[x][y] = tile;//girde ekledim
                
                root.getChildren().add(tile);
            }
        }

        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = gird[x][y];
                
                if (tile.hasBomb) {
                   
                	continue;//burada oyunumda bombaları gösterdim
                }
                
                long bombs = getNeighbors(tile).stream().filter(t -> t.hasBomb).count();
                //bomba varsa değdiği alanları filtreler ve yayınlar 1 2 gibi puan
                
                if (bombs > 0)
                    tile.text.setText(String.valueOf(bombs));
            }
        }

        return root;
    }

    private List<Tile> getNeighbors(Tile tile) {
        List<Tile> neighbors = new ArrayList<>();

        int[] points = new int[] {
        	
        	-1, -1,
            0, 1,
            0, -1,
            1, 0,
            1, -1,
            1, 1,
            -1, 1,
            -1, 0
        };
        /*hatırlamam için / / /
         * 				  / x /
         * 				  / / /
         */

        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[++i];
          
            
            int newX = tile.x + dx;
           int newY = tile.y + dy;

            if (newX >= 0 && newX < X_TILES && newY >= 0 && newY < Y_TILES) {
                
            	
            	
            	neighbors.add(gird[newX][newY]);//yeni x ve y değerlerini komşulara ekledim
            }
        }

        return neighbors;
    }

    private class Tile extends StackPane {
        private int x;
        private int y;
       
        private boolean hasBomb;
        
        private boolean isOpen = false;

        private Rectangle border = new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2);//ONEMLi
      
        private Text text = new Text();

        public Tile(int x, int y, boolean hasBomb) {
            this.x = x;
            this.y = y;
            this.hasBomb = hasBomb;

            text.setFill(Color.WHITE);
            border.setStroke(Color.LIGHTGRAY);
            
            text.setFont(Font.font(20));
           
            text.setVisible(false);

            getChildren().addAll(border, text);
            
            
            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);

            setOnMouseClicked(e -> open());//Tıklayınca kutular acılması
        }

        public void open() {
            if (isOpen) {
                return;
            }

            isOpen = true;//devam

            if (hasBomb) {
                
                if (scene != null) {
                    scene.setRoot(createContect());//bomba ve oyunu rest
                }
                return;
            }

            if (!text.getText().isEmpty()) {
                text.setVisible(true);//bos kutu ıcın
            }

            border.setFill(Color.DARKRED);//arkaplan rengı

            if (text.getText().isEmpty()) {
                
            	getNeighbors(this).forEach(Tile::open);//komsularda bos ıse puan dahılında ızgaraları acar
            }
        }
    }


}


