# Legend of Jean-Guy

A 2D action-adventure game built in Java, inspired by classic dungeon crawlers. Guide Jean-Guy through procedurally generated dungeons, battle enemies, and collect items in this retro-style RPG.

## 📋 Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Requirements](#requirements)
- [Installation](#installation)
- [How to Play](#how-to-play)
- [Game Mechanics](#game-mechanics)
- [Development](#development)
- [Testing](#testing)
- [Assets](#assets)

## ✨ Features

- **Multiple playable characters**: Jean-Guy (main protagonist)
- **Enemy AI**: Octoroks with projectile attacks
- **Tile-based map system**: 16x16 pixel tiles with dungeon environments
- **Combat system**: Melee attacks, projectiles, shield blocking, and collision detection
- **Item system**: Collectible items and power-ups
- **Sound effects and music**: Immersive audio experience
- **UI system**: Health bars and game menus

## 📁 Project Structure

```
Java2D/
├── src/
│   ├── main/
│   │   ├── Main.java               
│   │   ├── GamePanel.java           
│   │   ├── UI.java           
│   ├── entities/
│   │   ├── bullets/                  
│   │   │   ├── Bullets.java
│   │   │   └── Octorokatk.java
│   │   ├── players/                 
│   │   │   ├── Action.java
│   │   │   ├── Bat.java
│   │   │   ├── Gumba.java
│   │   │   ├── JeanGuy.java
│   │   │   ├── Marchand.java
│   │   │   ├── NonPlayable.java
│   │   │   ├── Octorok.java
│   │   │   ├── Odette.java
│   │   │   ├── Playable.java
│   │   │   ├── Players.java
│   │   │   └── MaskGuy.java
│   │   └── equipements/             
│   │       ├── armes/│
│   │       │   ├── Armes.java
│   │       │   ├── BouclierBois.java
│   │       │   └── EpeeBois.java 
│   │       ├── soins/
│   │       │   ├── Soins.java
│   │       │   ├── Coeur.java
│   │       │   └── CoeurMax.java 
│   │       └── Equipements.java         
│   ├── input/
│   │   └── KeyHandler.java    
│   ├── utils/
│   │   ├── AlgorthmMovement.java
│   │   ├── AlgorithmMovementRange.java
│   │   ├── AttackCollisions.java
│   │   ├── BulletCollisions.java
│   │   ├── CollisionDistance.java
│   │   ├── CollisionEquipement.java
│   │   ├── Collisions.java
│   │   ├── CollisionsMap.java
│   │   ├── CollisionNpcMap.java
│   │   ├── CreationMonstres.java
│   │   └── Collisions.java       
│   ├── test/
│   │   ├── Tile.java
│   │   └── TileManager.java   
│   ├── test/
│   │   ├── BulletsTest.java
│   │   ├── JeanGuyTest.java
│   │   ├── NonPlayableTest.java  
│   │   ├── GamePanelTest.java              
│   │   └── OdetteTest.java                                
├── resources/
    ├── assets/                       
    │   ├── donjon/                   
    │   ├── players/                 
    │   ├── items/                  
    │   └── ui/                   
    ├── maps/                       
    ├── musics/                  
    └── sounds/                 

```

## 🔧 Requirements

- **Java Development Kit (JDK)**: 11 or higher
- **Operating System**: Linux, macOS, or Windows
- **Dependencies**: JUnit 5 (for testing only)

## 🚀 Installation

### Clone the Repository

```bash
git clone <repository-url>
cd Java2D
```

### Compile the Game

```bash
# Create bin directory
mkdir -p bin

# Compile all source files
javac -d bin -sourcepath src/main src/main/**/*.java

# Copy resources to bin
cp -r resources bin/
```

### Run the Game

```bash
java -cp bin main.Main
```

## 🎮 How to Play

### Controls (French AZERTY Keyboard)

- **Z**: Move up
- **Q**: Move left  
- **S**: Move down
- **D**: Move right
- **J**: Attack with sword
- **K**: Block with shield
- **Enter (Entrée)**: Confirm / Interact with objects
- **Escape (Echap)**: Pause menu

### Objective

Navigate through dungeons, defeat enemies, collect items, and go save the princess Odette.

### Gameplay Tips

- Watch enemy movement patterns to avoid projectiles
- Use **K** to block incoming attacks with your shield
- Collect hearts to restore health
- Use walls and obstacles for cover

## ⚙️ Game Mechanics

### Combat System

- **Melee Attacks**: Close-range sword strikes (J key)
- **Shield Block**: Defend against attacks (K key)
- **Projectiles**: Enemies like Octoroks shoot projectiles
- **Collision Detection**: Precise hitbox-based combat
- **Health System**: Both players and enemies have HP

### Map System

- **Tile-based rendering**: 16×16 pixel tiles
- **Map dimensions**: 16 tiles wide × 12 tiles tall (256×192 pixels)
- **Collision tiles**: Walls and obstacles block movement
- **Multiple environments**: Dungeon, forest, and more

### Entity System

All entities (players, enemies, items) inherit from `Entity.java`:
- Position tracking (`worldx`, `worldy`)
- Sprite animation
- Collision boxes
- Movement speed
- Health/damage stats

### Item System

- Collectible items throughout the map
- Automatic pickup on collision
- Item effects (health restoration, stat boosts)

## 🛠️ Development

### Key Classes

**Main.java**
- Entry point
- Window initialization

**GamePanel.java**
- Game loop (60 FPS)
- Update and render cycle
- Manages all game systems

**CollisionChecker.java**
- Tile collision detection
- Entity-to-entity collision
- Projectile collision

**EntityHandler.java**
- Spawns and manages enemies
- Updates entity states
- Handles entity removal

**Maps.java**
- Loads map data from text files
- Renders tile layers
- Camera system

**KeyHandler.java**
- Captures keyboard input (ZQSD, J, K, Enter, Escape)
- Maps keys to game actions

### Adding New Content

**New Enemy Type:**
1. Create class in `entities/players/` extending `Players.java`
2. Implement movement AI and attack patterns
3. Add sprite assets to `resources/assets/players/`
4. Register in `EntityHandler.java`

**New Item:**
1. Create class in `items/` extending `SuperItem.java`
2. Implement item effect logic
3. Add sprite to `resources/assets/items/`
4. Register in `ItemHandler.java`

**New Map:**
1. Create text file in `resources/maps/`
2. Use tile IDs from `Tiles.java`
3. Format: space-separated integers (16 columns × 12 rows)

## 🧪 Testing

Run unit tests with JUnit:

```bash
# Compile tests (requires JUnit 5)
javac -cp bin:junit-platform-console-standalone.jar -d bin src/test/*.java

# Run tests
java -jar junit-platform-console-standalone.jar --class-path bin --scan-class-path
```

**Current Tests:**
- `BulletsTest.java`: Tests Octorok projectile mechanics

**Test Code Example:**
```java
@Test
void octorokatk(){
    GamePanel gamePanel = new GamePanel();
    KeyHandler keyHandler = new KeyHandler(gamePanel);
    Players sender = new Octorok(gamePanel);
    Players receiver = new JeanGuy(gamePanel, keyHandler);

    Octorokatk octorokatk = new Octorokatk(gamePanel, sender, receiver);

    octorokatk.calcSpeed();
    System.out.println(octorokatk.getIsActive());
    octorokatk.setIsActive();
    System.out.println(octorokatk.getIsActive());
    System.out.println(octorokatk.getPosition());
}
```

This test:
- Creates an Octorok projectile from sender to receiver
- Calculates projectile speed and trajectory
- Tests activation state changes
- Verifies position tracking with debug output

## 🎨 Assets

The game includes **131 PNG assets** organized in:

- **Dungeon tiles**: Walls, floors, doors, pillars, stairs
- **Character sprites**: Jean-Guy, Octorok, and other entities
- **Items**: Health potions, keys, treasures
- **UI elements**: Hearts, buttons, menus

All tiles are **16×16 pixels** for consistent rendering.

## 📝 License

This project is an educational project for Epitech.

## 👥 Contributors

- **Development Team**: Marcello De Stefano, Theo Van Sever, Arthur Yomkil, Pierre Lebrethon

## 🐛 Known Issues

- None currently reported

## 🔮 Future Features

- Inventory system
- Map screen
- Multiplayer support
- More enemy types
- Boss battles
- Procedural dungeon generation
- Achievement system

---

**Profitez du jeu Legend of Jean-Guy!** 🎮⚔️🛡️
