package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[1000];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/donjonfloor2.png")));

            tile[640] = new Tile();
            tile[640].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/topleftcornerwall.png")));

            tile[621] = new Tile() ;
            tile[621].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/bottomleftcornerwall.png")));

            tile[644] = new Tile();
            tile[644].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/toprightcornerwall.png")));

            tile[628] = new Tile();
            tile[628].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/bottomrightcornerwall.png")));

            tile[636] = new Tile();
            tile[636].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/topandbottom.png")));
            tile[636].collision = true;

            tile[635] = new Tile();
            tile[635].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/straight.png")));
            tile[635].collision = true;

            tile[610] = new Tile();
            tile[610].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/brickwall.png")));

            tile[622] = new Tile();
            tile[622].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/middlebottombrick.png")));
            tile[622].collision = true;

            tile[612] = new Tile();
            tile[612].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/donjonfloor2.png")));

            tile[64] = new Tile();
            tile[64].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/bottomleftcornerwall.png")));
            tile[64].collision = true;

            tile[69] = new Tile();
            tile[69].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/bottomrightcornerwall.png")));
            tile[69].collision = true;

        }catch(IOException e){

            e.printStackTrace();
        }

    }

    public void loadMap(){

        System.out.println("=== DÉBUT CHARGEMENT MAP ===");

        try{
            InputStream is = getClass().getResourceAsStream("/fichiers_maps/map_minimum/A1test");

            if(is == null) {
                System.out.println("❌ ERREUR: Fichier de carte introuvable!");
                System.out.println("Chemin cherché: /fichiers_maps/map_minimum/A1test.txt");
                return;
            }

            System.out.println("✅ Fichier trouvé!");

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;

            while (row < gp.maxScreenRow) {

                String line = br.readLine();

                if(line == null) {
                    System.out.println("Fin du fichier atteinte à la ligne " + row);
                    break;
                }

                // ✅ Afficher TOUTES les lignes pour voir ce qui est lu
                System.out.println("Ligne " + row + ": " + line.substring(0, Math.min(50, line.length())) + "...");

                String numbers[] = line.split(",");

                for(int col = 0; col < gp.maxScreenCol && col < numbers.length; col++) {

                    String numStr = numbers[col].trim();
                    int num = Integer.parseInt(numStr);
                    mapTileNum[col][row] = num;

                    // Afficher les 10 premières valeurs de la première ligne
                    if(row == 0 && col < 10) {
                        System.out.println("  mapTileNum[" + col + "][0] = " + num);
                    }
                }

                row++;
            }

            br.close();
            System.out.println("✅ Chargement terminé: " + row + " lignes lues");
            System.out.println("=== FIN CHARGEMENT MAP ===");

        }catch(Exception e){
            System.out.println("❌ ERREUR lors du chargement:");
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = mapTileNum[col][row];

            if(tile[tileNum] == null) {
                System.out.println("⚠️ ERREUR: tile[" + tileNum + "] est null à la position col=" + col + ", row=" + row);
                // ✅ Utiliser la tile par défaut au lieu de crasher
                if(tile[0] != null) {
                    g.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
                }
            } else {
                g.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            }

            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
