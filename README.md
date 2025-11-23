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
- **Combat system**: Melee attacks, projectiles, and collision detection
- **Item system**: Collectible items and power-ups
- **Sound effects and music**: Immersive audio experience
- **Save/Load system**: Progress persistence
- **UI system**: Health bars, inventory, and game menus

## 📁 Project Structure

```
Java2D/
├── src/
│   ├── main/
│   │   ├── Main.java                 # Entry point
│   │   ├── GamePanel.java            # Core game loop and rendering
│   │   ├── CollisionChecker.java     # Collision detection
│   │   ├── EntityHandler.java        # Entity management
│   │   ├── EventHandler.java         # Game events
│   │   └── UtilityTools.java         # Helper utilities
│   ├── entities/
│   │   ├── bullets/                  # Projectile classes
│   │   │   ├── Bullets.java
│   │   │   └── Octorokatk.java
│   │   ├── players/                  # Player/enemy classes
│   │   │   ├── Players.java
│   │   │   ├── JeanGuy.java
│   │   │   └── Octorok.java
│   │   └── Entity.java               # Base entity class
│   ├── input/
│   │   └── KeyHandler.java           # Keyboard input handling
│   ├── items/
│   │   ├── SuperItem.java            # Base item class
│   │   └── ItemHandler.java          # Item management
│   ├── maps/
│   │   ├── Maps.java                 # Map loading/rendering
│   │   └── Tiles.java                # Tile system
│   ├── sound/
│   │   └── SoundHandler.java         # Audio management
│   └── ui/
│       └── UI.java                   # User interface
├── resources/
│   ├── assets/                       # Game assets (131 PNG files)
│   │   ├── donjon/                   # Dungeon tiles
│   │   ├── players/                  # Character sprites
│   │   ├── items/                    # Item graphics
│   │   └── ui/                       # UI elements
│   ├── maps/                         # Map data files
│   ├── musics/                       # Background music
│   └── sounds/                       # Sound effects
└── src/test/                         # JUnit test files
    └── BulletsTest.java
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

### Controls

- **Arrow Keys / WASD**: Move Jean-Guy
- **Space / Enter**: Attack / Interact
- **ESC**: Pause menu
- **I**: Inventory
- **M**: Map

### Objective

Navigate through dungeons, defeat enemies, collect items, and survive as long as possible.

## ⚙️ Game Mechanics

### Combat System

- **Melee Attacks**: Close-range damage to enemies
- **Projectiles**: Enemies like Octoroks shoot projectiles
- **Collision Detection**: Precise hitbox-based combat
- **Health System**: Both players and enemies have HP

### Map System

- **Tile-based rendering**: 16x16 pixel tiles
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
- Inventory management
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
- Captures keyboard input
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
- `BulletsTest.java`: Tests projectile mechanics, speed calculation, and activation

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

- **Development Team**: Marcello De Stefano, Theo Van Sever, Arthur Yomkil and Pierre Lebrethon

## 🐛 Known Issues

- None currently reported

## 🔮 Future Features

- Multiplayer support
- More enemy types
- Boss battles
- Procedural dungeon generation
- Achievement system

---

**Enjoy playing Legend of Jean-Guy!** 🎮
