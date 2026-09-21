Description: A simple and interactive Java car racing game where the player controls a car to avoid enemy cars and survive as long as possible. The game includes road animation, sound effects, score counting, timer system, and restart functionality. The objective of the project is to practice Java GUI programming, graphics, animation, event handling, and game logic implementation.

Features: Smooth car movement, random enemy car generation, moving road animation, score counting system, timer display, sound effects, keyboard controls, game over system, restart system, and interactive colorful GUI design.

The structural Description of every week updates of Car Racing Game:

Car-Racing-Game/

├── src/

│   └── weeks/

│       ├── Week2.java    # Environment Setup & Window Canvas Initialization

│       ├── Week3.java    # Track Layout & Vector Graphics Rendering

│       ├── Week4.java    # KeyListener Integration & Player Movement Bounds

│       ├── Week5.java    # Enemy Spawning System & Lane Scrolling Physics

│       ├── Week6.java    # Frame Counter, Score System & Survival Timer

│       ├── Week7.java    # Collision Detection Engine & Game Over State

│       ├── Week8.java    # Java Sound API Integration & WAV Asset Loader

│       └── Week9.java    # Finite State Machine, Welcome Screen & System Restart

└── resources(sound files-week 8)

    └── weeks/
    
        ├── welcome.wav   -Menu Background Music
        
        ├── run.wav       -Engine Driving Background Audio
        
        ├── score.wav     -Point Score Sound Effect
        
        └── crash.wav     -Collision Sound Effect

📅 Weekly Development Summary

Week 1: Project Planning & Requirements Analysis

The project commenced with an analysis of Java GUI frameworks and game architecture patterns suitable for desktop applications. Requirements were defined around key Java Swing and AWT components—specifically JFrame, JPanel, and Graphics2D—to establish rendering pipelines, game loop timing, and event-driven keyboard controls. This phase established the conceptual framework, functional specifications, and initial directory layout required for a modular development lifecycle.

Week 2: Environment Setup & Window Canvas Initialization

Development moved into NetBeans IDE to establish the core project structure, package organization, and primary execution entry point. The base Week2 class was created by extending JPanel and embedding it inside a non-resizable JFrame configured to a target resolution of 600×700 pixels. Swing threading best practices were implemented using SwingUtilities.invokeLater() to guarantee safe thread execution for GUI creation and rendering. 

Week 3: Graphics Rendering & Vector Track Design

The graphical rendering system was built within paintComponent(Graphics g) using Graphics2D shapes and color models. The visual layout was split into three functional zones: outer green grass borders, white track margins, and a dark gray inner driving road. Custom vector graphic methods were created to render player and enemy vehicles using rounded rectangles (fillRoundRect) for the chassis, cyan windshields, dark wheels, yellow headlights, and tail lights.   

Week 4: Player Input Handling & Road Boundaries

Interactive player mechanics were introduced by implementing Java’s KeyListener interface. The keyPressed event handler was configured to intercept left and right directional arrow inputs, updating the player’s horizontal coordinate (playerX) in fixed increments. Bounding limits (150 < playerX < 390) were enforced to restrict movement strictly within the asphalt track boundaries, preventing the vehicle from driving off-road.   

Week 5: Dynamic Motion Physics & Enemy Spawning

Real-time motion physics and vertical track scrolling were integrated into the update loop. Vertical offsets (roadOffsetY) were continuously incremented to slide the yellow central lane dividers downward, creating a realistic illusion of forward speed. Concurrently, an automated enemy vehicle spawning system was implemented using java.util.Random, driving the enemy car downward and resetting its position to the top of the track with randomized lane coordinates upon reaching the screen bottom.   

Week 6: Game Loop Architecture & Survival HUD

A 60 FPS real-time game loop was established using javax.swing.Timer executing at a 16ms tick interval. Frame counter logic was added to compute continuous survival duration in seconds. Simultaneously, a dynamic scoring system was implemented to award +10 points each time an enemy vehicle was successfully dodged, with both metrics rendered in real-time on the heads-up display (HUD).   

Week 7: Bounding-Box Collision Detection & Game Over Logic

Vehicle interaction physics were built using java.awt.Rectangle bounding boxes. During each game loop frame, collision checks evaluated boundary intersections between playerRect and enemyRect using intersects(). Upon detecting a collision, the system instantly stopped the timer loop, locked player controls, updated the state to isGameOver = true, and rendered a semi-transparent dark overlay displaying the player's final performance statistics.   

Week 8: Audio Engine Integration & Classpath Management

Audio capabilities were integrated using the Java Sound API (javax.sound.sampled). Sound files (.wav) were embedded directly into the project classpath (/weeks/) and loaded dynamically via getClass().getResource() to prevent platform-specific file path breaks. The audio manager configured looping background audio for menu and driving states (welcome.wav, run.wav), alongside triggered single-shot playback for point scoring (score.wav) and crash events (crash.wav).   

Week 9: Finite State Machine & System Restart

The game architecture was refactored into a finite state machine managed by boolean state flags (isWelcomeScreen and isGameOver). A dedicated Welcome Screen interface was introduced with animated track backgrounds, title typography, control instructions, and background audio. Transition logic was bound to the ENTER key to start gameplay, while an instant restart mechanism bound to the R key was created to reset all spatial coordinates, timers, scores, and audio streams after a crash.   

Week 10: Final Quality Assurance, Optimization & Repository Polish

The final phase focused on code refactoring, performance optimization, and comprehensive documentation. Resource stream management was tested to ensure proper garbage collection of audio resources, screen redraw performance was verified for consistent frame rates, and visual layout alignments were polished. Complete project documentation, architectural overviews, and setup instructions were compiled to deliver a production-ready GitHub repository.

🏁 Conclusion: 
The Java 2D Car Racing Game project successfully demonstrates the power and flexibility of core Java Swing and AWT frameworks when building performant, object-oriented desktop games without external heavy engines. Through a structured 10-week developmental lifecycle, the application evolved from simple window initialization into a complete arcade experience featuring custom vector rendering, a state-driven architecture, robust collision physics, responsive user input, and embedded multi-channel audio.   By maintaining clean component isolation, leveraging native Swing timing mechanisms, and utilizing classpath resource streaming, the project serves as an ideal reference implementation for Java game loops, GUI event handling, and cross-platform desktop application packaging.           
