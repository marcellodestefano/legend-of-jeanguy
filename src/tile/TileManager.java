package tile;

import com.sun.source.doctree.SystemPropertyTree;
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

    public String currentZone;
    public int currentChunkX;
    public int currentChunkY;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[1000];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        currentZone = "overworld";
        currentChunkX = 0;
        currentChunkY = 0;

        getTileImage();
        loadChunk(currentZone, currentChunkX, currentChunkY);
    }

    public void getTileImage() {

        try{

            // Donjon map 1

            tile[64] = new Tile();
            tile[64].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/bottomleftcornerwall.png")));

            tile[69] = new Tile();
            tile[69].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/bottomrightcornerwall.png")));

            tile[610] = new Tile();
            tile[610].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/brickwall.png")));

            tile[612] = new Tile();
            tile[612].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/donjonfloor2.png")));

            tile[613] = new Tile();
            tile[613].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/door1.png")));

            tile[614] = new Tile();
            tile[614].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/door2.png")));

            tile[616] = new Tile();
            tile[616].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/flooright.png")));

            tile[617] = new Tile();
            tile[617].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/floorleft.png")));

            tile[621] = new Tile();
            tile[621].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/leftsidebrick.png")));

            tile[622] = new Tile();
            tile[622].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/middlebottombrick.png")));

            tile[623] = new Tile();
            tile[623].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/middledoor.png")));

            tile[628] = new Tile();
            tile[628].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/rightbottombrick.png")));

            tile[635] = new Tile();
            tile[635].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/straight.png")));

            tile[636] = new Tile();
            tile[636].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/topandbottom.png")));

            tile[638] = new Tile();
            tile[638].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/topdoorleft.png")));

            tile[640] = new Tile();
            tile[640].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/topleftcornerwall.png")));

            tile[641] = new Tile();
            tile[641].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/topmiddledoor.png")));

            tile[644] = new Tile();
            tile[644].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/toprightcornerwall.png")));

            tile[645] = new Tile();
            tile[645].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/toprightdoor.png")));

            tile[646] = new Tile();
            tile[646].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/endleft.png")));

            tile[647] = new Tile();
            tile[647].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/endright.png")));

            tile[649] = new Tile();
            tile[649].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/mainentry.png")));

            tile[651] = new Tile();
            tile[651].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/entrymiddle.png")));

            // Exterieur

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/path/Chemin.png")));

            tile[07] = new Tile();
            tile[07].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/path/blackvoid.jpg")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/path/panneau.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/path/rockblocker.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/path/sandwithlittleherbs.png")));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/CheminGazon.png")));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/FleursVert1.png")));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/FleursVert3.png")));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonBas.png")));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonBasDroit.png")));

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonBasGauche.png")));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonDroit.png")));

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonGauche.png")));

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/greentree/HautDroit.png")));

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/greentree/HautGauche.png")));

            tile[24] = new Tile();
            tile[24].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/greentree/HautMultiDroit.png")));

            tile[25] = new Tile();
            tile[25].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/greentree/HautMultiGauche.png")));

            tile[26] = new Tile();
            tile[26].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/greentree/RacineDroite.png")));

            tile[27] = new Tile();
            tile[27].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/greentree/RacineGauche.png")));

            tile[40] = new Tile();
            tile[40].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/shop/BasDroitViolet.png")));

            tile[41] = new Tile();
            tile[41].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/shop/BasGaucheViolet.png")));

            tile[42] = new Tile();
            tile[42].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/shop/BasViolet.png")));

            tile[43] = new Tile();
            tile[43].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/shop/HautDroitViolet.png")));

            tile[44] = new Tile();
            tile[44].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/shop/HautGaucheViolet.png")));

            tile[45] = new Tile();
            tile[45].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/shop/HautViolet.png")));

            tile[46] = new Tile();
            tile[46].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/baseforhouse/BrownDoor.png")));

            tile[81] = new Tile();
            tile[81].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/entrancedonjon/left1.png")));

            tile[82] = new Tile();
            tile[82].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/entrancedonjon/left2.png")));

            tile[83] = new Tile();
            tile[83].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/entrancedonjon/middleentrance.png")));

            tile[85] = new Tile();
            tile[85].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/entrancedonjon/right2.png")));

            tile[84] = new Tile();
            tile[84].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/entrancedonjon/right1.png")));

            tile[110] = new Tile();
            tile[110].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonHaut.png")));

            tile[111] = new Tile();
            tile[111].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonHautDroit.png")));

            tile[112] = new Tile();
            tile[112].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonHautGauche.png")));

            tile[113] = new Tile();
            tile[113].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonPlein.png")));

            tile[119] = new Tile();
            tile[119].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/SavageGrass.png")));

            tile[410] = new Tile();
            tile[410].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/baseforhouse/PurpleWindow.png")));

            tile[411] = new Tile();
            tile[411].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/baseforhouse/StoneWall.png")));

            tile[412] = new Tile();
            tile[412].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/baseforhouse/TopStoneWall.png")));

            // Test donjon
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

    public void loadChunk(String zone, int chunkX, int chunkY) {

        System.out.println("CHARGEMENT [" + zone + "] CHUNK [" + chunkX + "," + chunkY + "]");

        try {

            // "/fichiers_maps/overworld/chunk_0_0"
            String mapPath = "/fichiers_maps/" + zone + "/chunk_" + chunkX + "_" + chunkY;
            InputStream is = getClass().getResourceAsStream(mapPath);

            if(is == null) {
                System.out.println("ERREUR: Chunk introuvable: " + mapPath);
                return;
            }

            System.out.println("Chunk trouvé: " + mapPath);

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int row = 0;

            while (row < gp.maxScreenRow) {
                String line = br.readLine();

                if(line == null) {
                    System.out.println("Fin du fichier à la ligne " + row);
                    break;
                }

                String numbers[] = line.split(",");

                for(int col = 0; col < gp.maxScreenCol && col < numbers.length; col++) {
                    String numStr = numbers[col].trim();
                    int num = Integer.parseInt(numStr);
                    mapTileNum[col][row] = num;
                }

                row++;
            }

            br.close();

            // Mettre à jour les variables globales
            currentZone = zone;
            currentChunkX = chunkX;
            currentChunkY = chunkY;

            System.out.println("Chunk chargé: " + zone + " [" + chunkX + "," + chunkY + "]");

        } catch(Exception e) {
            System.out.println("ERREUR lors du chargement:");
            e.printStackTrace();
        }
    }

    public void changeChunk(String direction) {
        switch(direction) {
            case "NORTH":
                loadChunk(currentZone, currentChunkX, currentChunkY + 1);
                break;
            case "SOUTH":
                loadChunk(currentZone, currentChunkX, currentChunkY - 1);
                break;
            case "EAST":
                loadChunk(currentZone, currentChunkX + 1, currentChunkY);
                break;
            case "WEST":
                loadChunk(currentZone, currentChunkX - 1, currentChunkY);
                break;
        }
    }


    public void changeZone(String newZone, int spawnChunkX, int spawnChunkY) {
        loadChunk(newZone, spawnChunkX, spawnChunkY);
    }

//    public void loadMap(){
//
//        System.out.println("=== DÉBUT CHARGEMENT MAP ===");
//
//        try{
//            InputStream is = getClass().getResourceAsStream("/fichiers_maps/map_minimum/chunk_0_1");
//
//            if(is == null) {
//                System.out.println("ERREUR: Fichier de carte introuvable!");
//                System.out.println("Chemin cherché: /fichiers_maps/map_minimum/chunk_0_1.txt");
//                return;
//            }
//
//            System.out.println("Fichier trouvé!");
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            int row = 0;
//
//            while (row < gp.maxScreenRow) {
//
//                String line = br.readLine();
//
//                if(line == null) {
//                    System.out.println("Fin du fichier atteinte à la ligne " + row);
//                    break;
//                }
//
//                // ✅ Afficher TOUTES les lignes pour voir ce qui est lu
//                System.out.println("Ligne " + row + ": " + line.substring(0, Math.min(50, line.length())) + "...");
//
//                String numbers[] = line.split(",");
//
//                for(int col = 0; col < gp.maxScreenCol && col < numbers.length; col++) {
//
//                    String numStr = numbers[col].trim();
//                    int num = Integer.parseInt(numStr);
//                    mapTileNum[col][row] = num;
//
//                    // Afficher les 10 premières valeurs de la première ligne
//                    if(row == 0 && col < 10) {
//                        System.out.println("  mapTileNum[" + col + "][0] = " + num);
//                    }
//                }
//
//                row++;
//            }
//
//            br.close();
//            System.out.println("✅ Chargement terminé: " + row + " lignes lues");
//            System.out.println("=== FIN CHARGEMENT MAP ===");
//
//        }catch(Exception e){
//            System.out.println("❌ ERREUR lors du chargement:");
//            e.printStackTrace();
//        }
//    }

    public void draw(Graphics g) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = mapTileNum[col][row];

            if(tile[tileNum] == null) {
//                System.out.println("⚠ERREUR: tile[" + tileNum + "] est null à la position col=" + col + ", row=" + row);

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
