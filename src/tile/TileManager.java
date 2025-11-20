package tile;

import com.sun.source.doctree.SystemPropertyTree;
import entities.players.*;
import main.GamePanel;
import utils.CollisionsMap;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.awt.desktop.SystemSleepListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    private JeanGuy jeanGuy;
    private String currentZone;
    private String folderName;
    private String choices;
    private ArrayList<Tile> pathTiles = new ArrayList<>();
    private ArrayList<Tile> chunkTiles = new ArrayList<>();
    private ArrayList<String> info = new ArrayList<>();

    public TileManager(GamePanel gp, JeanGuy jeanGuy) {
        this.gp = gp;
        this.jeanGuy = jeanGuy;

        tile = new Tile[1000];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        currentZone = "map_minimum";
        folderName = "exterior.txt";
        getTileImage();
        loadChunk(currentZone, folderName);
    }

    public ArrayList<Tile> getPathTiles(){
        return this.pathTiles;
    }

    public ArrayList<Tile> getChunkTiles(){
        return this.chunkTiles;
    }

    public int[][] getMapTiles(){
        return this.mapTileNum;
    }

    public Tile[] getTiles(){
        return this.tile;
    }



    public void getTileImage() {

        try{

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/path/blackvoid.jpg")));

            tile[313] = new Tile();
            tile[313].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/bottomcommode.png")));

            tile[314] = new Tile();
            tile[314].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/bottomleft.png")));

            tile[315] = new Tile();
            tile[315].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/bottommiddle.png")));

            tile[316] = new Tile();
            tile[316].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/bottomright.png")));

            tile[317] = new Tile();
            tile[317].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/caisse.png")));

            tile[318] = new Tile();
            tile[318].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/left.png")));

            tile[319] = new Tile();
            tile[319].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/leftrock.png")));

            tile[321] = new Tile();
            tile[321].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/right.png")));

            tile[322] = new Tile();
            tile[322].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/rightrock.png")));

            tile[324] = new Tile();
            tile[324].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/topcommode.png")));

            tile[325] = new Tile();
            tile[325].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/topleft.png")));

            tile[326] = new Tile();
            tile[326].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/topmiddle.png")));

            tile[337] = new Tile();
            tile[337].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/topright.png")));

            tile[338] = new Tile();
            tile[338].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/woodfloor.png")));
            pathTiles.add(tile[338]);

            tile[339] = new Tile();
            tile[339].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/entrance1.png")));

            tile[340] = new Tile();
            tile[340].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/houses/interior/entrance2.png")));


            // Donjon map 1

            tile[63] = new Tile();
            tile[63].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/bottomleftcorner.png")));

            tile[64] = new Tile();
            tile[64].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/bottomleftcornerwall.png")));

            tile[65] = new Tile();
            tile[65].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/bottommiddle.png")));

            tile[68] = new Tile();
            tile[68].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/bottomrightcorner.png")));

            tile[69] = new Tile();
            tile[69].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/bottomrightcornerwall.png")));

            tile[610] = new Tile();
            tile[610].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/brickwall.png")));

            tile[611] = new Tile();
            tile[611].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/donjonfloor1.png")));
            pathTiles.add(tile[611]);

            tile[612] = new Tile();
            tile[612].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/donjonfloor2.png")));

            tile[621] = new Tile();
            tile[621].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/leftsidebrick.png")));

            tile[622] = new Tile();
            tile[622].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/middlebottombrick.png")));



            tile[624] = new Tile();
            tile[624].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/middleleft.png")));

            tile[626] = new Tile();
            tile[626].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/middleright.png")));

            tile[627] = new Tile();
            tile[627].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/middlewall.png")));

            tile[628] = new Tile();
            tile[628].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/rightbottombrick.png")));

            tile[630] = new Tile();
            tile[630].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/stairs.png")));
            pathTiles.add(tile[630]);

            tile[631] = new Tile();
            tile[631].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/statue1.png")));

            tile[632] = new Tile();
            tile[632].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/statue2.png")));

            tile[633] = new Tile();
            tile[633].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/statue3.png")));

            tile[634] = new Tile();
            tile[634].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/statue4.png")));

            tile[635] = new Tile();
            tile[635].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/straight.png")));

            tile[636] = new Tile();
            tile[636].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/topandbottom.png")));

            tile[639] = new Tile();
            tile[639].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/topleftcorner.png")));


            tile[640] = new Tile();
            tile[640].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/topleftcornerwall.png")));

            tile[644] = new Tile();
            tile[644].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/toprightcornerwall.png")));

            tile[646] = new Tile();
            tile[646].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/endleft.png")));

            tile[647] = new Tile();
            tile[647].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/endright.png")));


            tile[69] = new Tile();
            tile[69].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/bottomrightcornerwall.png")));

            tile[610] = new Tile();
            tile[610].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/brickwall.png")));

            tile[612] = new Tile();
            tile[612].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/donjonfloor2.png")));
            pathTiles.add(tile[612]);

            tile[613] = new Tile();
            tile[613].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/door1.png")));

            tile[614] = new Tile();
            tile[614].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/door2.png")));

            tile[616] = new Tile();
            tile[616].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/flooright.png")));
            pathTiles.add(tile[616]);

            tile[617] = new Tile();
            tile[617].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/floorleft.png")));
            pathTiles.add(tile[617]);

            tile[621] = new Tile();
            tile[621].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/leftsidebrick.png")));

            tile[622] = new Tile();
            tile[622].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/middlebottombrick.png")));

            tile[623] = new Tile();
            tile[623].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/middledoor.png")));
            chunkTiles.add(tile[623]);

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
            chunkTiles.add(tile[649]);

            tile[651] = new Tile();
            tile[651].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/entrymiddle.png")));
            chunkTiles.add(tile[651]);
            // Exterieur

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/path/Chemin.png")));
            pathTiles.add(tile[0]);

            tile[07] = new Tile();
            tile[07].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/path/blackvoid.jpg")));
            chunkTiles.add(tile[07]);

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/path/panneau.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/path/rockblocker.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/path/sandwithlittleherbs.png")));
            pathTiles.add(tile[3]);


            tile[10] = new Tile();
            tile[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/CheminGazon.png")));
            pathTiles.add(tile[10]);


            tile[11] = new Tile();
            tile[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/FleursVert1.png")));
            pathTiles.add(tile[11]);

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/FleursVert3.png")));
            pathTiles.add(tile[13]);


            tile[15] = new Tile();
            tile[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonBas.png")));
            pathTiles.add(tile[15]);

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonBasDroit.png")));
            pathTiles.add(tile[16]);


            tile[17] = new Tile();
            tile[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonBasGauche.png")));
            pathTiles.add(tile[17]);


            tile[18] = new Tile();
            tile[18].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonDroit.png")));
            pathTiles.add(tile[18]);

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonGauche.png")));
            pathTiles.add(tile[19]);

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
            pathTiles.add(tile[110]);


            tile[111] = new Tile();
            tile[111].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonHautDroit.png")));
            pathTiles.add(tile[111]);


            tile[112] = new Tile();
            tile[112].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonHautGauche.png")));
            pathTiles.add(tile[112]);

            tile[113] = new Tile();
            tile[113].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/GazonPlein.png")));
            pathTiles.add(tile[113]);

            tile[119] = new Tile();
            tile[119].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/world/grass/SavageGrass.png")));
            pathTiles.add(tile[119]);

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


            tile[635] = new Tile();
            tile[635].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/straight.png")));

            tile[610] = new Tile();
            tile[610].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/brickwall.png")));

            tile[622] = new Tile();
            tile[622].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/middlebottombrick.png")));

            tile[612] = new Tile();
            tile[612].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/donjonfloor2.png")));
            pathTiles.add(tile[612]);


            tile[64] = new Tile();
            tile[64].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/bottomleftcornerwall.png")));


            tile[69] = new Tile();
            tile[69].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/donjon/bottomrightcornerwall.png")));


        }catch(IOException e){

            e.printStackTrace();
        }

    }



    public void changeMap(String order){

        String lastLine = null;
        try {
            String mapPath = "/fichiers_maps/" + currentZone + "/"+ folderName;

            InputStream is = getClass().getResourceAsStream(mapPath);
            if (is != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }

            assert lastLine != null;
            String[] infos = lastLine.split(",");
            this.info.addAll(Arrays.asList(infos));
            int index=-1;
            for (int i =0; i < info.size();i++) {
                if (info.get(i).equals(order)){
                    index = i;
                }
            }
            if (index!=-1) {
                this.currentZone = info.get(index + 1);
                this.folderName = info.get(index + 2);
                loadChunk(currentZone, folderName);
            }

            }

        } catch(Exception e ){
            e.printStackTrace();
        }
    }



    public void loadChunk(String zone, String folderName) {
        try {
            String mapPath = "/fichiers_maps/" + zone + "/"+ folderName;
            InputStream is = getClass().getResourceAsStream(mapPath);

            if(is != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                int row = 0;
                while (row < gp.maxScreenRow) {
                    String line = br.readLine();

                    String numbers[] = line.split(",");

                    for(int col = 0 ; col < gp.maxScreenCol && col < numbers.length; col++) {
                        String numStr = numbers[col].trim();
                        int num = Integer.parseInt(numStr);
                        mapTileNum[col][row] = num;
                    }

                    row++;
                }

                gp.setInfo(info);
                gp.setAddplayers(true);
            br.close();
            currentZone = zone;

            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void clearInfo(){
        this.info.clear();
    }




    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            if (!(tile[tileNum] == null)) {
                g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);

                col++;
                x += gp.tileSize;

                if (col == gp.maxScreenCol) {
                    col = 0;
                    x = 0;
                    row++;
                    y += gp.tileSize;
                }
            }
        }
    }
}
