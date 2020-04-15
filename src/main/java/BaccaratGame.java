import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

//BaccaratGame Class
public class BaccaratGame extends Application implements EventHandler<ActionEvent> {
	
	//Arrays, Dealer, and Logic
	ArrayList<Card> playerHand = new ArrayList<Card>();;
	ArrayList<Card> bankerHand = new ArrayList<Card>();;
	ArrayList<Card> playerFirst2 = new ArrayList<Card>();;
	ArrayList<Card> bankerFirst2 = new ArrayList<Card>();;
	BaccaratDealer theDealer = new BaccaratDealer();;
	BaccaratGameLogic gameLogic = new BaccaratGameLogic();;
	
	//Bets, total winnings, and draw count
	double currentBet;
	double totalWinnings; 
	int cardDrawCount;
	
	//Labels for messages
	Label wager, bwon, pwon, twon, curWin;
	Popup bankerWon, playerWon, Tie;
	
	//Buttons for the board
	Button startButton, bet10, bet25, bet100, bet500, bet5000, bet25000, betTie, betPlayer, betBanker; 	
	Button dealCards, drawCard,drawCard2,drawCard3,drawCard4;
	
	//Boolleans that keep track of session activity
	Boolean userBetTie = false; 
	Boolean userBetPlayer = false;
	Boolean userBetBanker = false;
	Boolean playInSession = false;
	
	//Gameboard image and imageviews
	Image gameBoardImage;
	ImageView imageViewBoard, playerBetBoard, bankerBetBoard, tieBetBoard, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7;
	
	//Stage
	Stage primaryStage1;
	BaccaratGame g;
	
	ListView<String> gameStatsList = new ListView<String>();
	
	//evaluateWinnings:
	// 	Function that evaluates how much user has won
	//	and returns a double value
	public double evaluateWinnings()
	{
		//If the user has not bet on any entity, return 0.00
		if ((!userBetPlayer && !userBetTie && !userBetBanker)){
			return 0.0;
		}
		//If there was a tie and the user bet on tie, return 8 * current bet
		// otherwise, return 0
		if( gameLogic.whoWon(playerHand, bankerHand).equals("Tie"))
		{	
			if (userBetTie)
			{
				return currentBet * 8.00;
			}
			else {
				return 0;
			}
		}
		//If the banker won and the user bet on banker, return 95% of bet
		// otherwise, return a negative amount of the current bet
		else if (gameLogic.whoWon(playerHand, bankerHand).equals("Banker"))
		{
			if (userBetBanker)
			{
				return currentBet * 0.95;
			}
			return -currentBet;
		}
		//If the player won and use bet on player, return the current bet
		// otherwise return a negative amount of the current bet
		else {
			if (userBetPlayer) 
			{
				return currentBet;
			}
			return -currentBet;
		}
	}
	
	//setGameStats:
	// 	Function that updates the play statistics panel
	public void setGameStats() {
		gameStatsList.getItems().addAll("      Player: " + gameLogic.handTotal(playerHand) + "        |       Banker: " + gameLogic.handTotal(bankerHand));
	}
	
	//main:
	public static void main(String[] args) throws FileNotFoundException {
		launch(args);
	}
	
	//cleanUp:
	//	Function that initializes the game for the next play
	public void cleanUp() {
		
		//Initializing particular variabls
		userBetTie = false; 
		userBetPlayer = false;
		userBetBanker = false;
		cardDrawCount = 0;
		playInSession = false;
		dealCards.setDisable(false);
		imageView2 = null;
		imageView3 = null;
		imageView4 = null;
		imageView5 = null;
		imageView6 = null;
		imageView7 = null;
	}
	
	//freshStart:
	//	Function that restarts the game 
	//
	public void freshStart() {
		
		//Initializing all variables
		playerHand = null;
		bankerHand = null;	
		currentBet = 0;
		totalWinnings = 10000;
		userBetTie = false; 
		userBetPlayer = false;
		userBetBanker = false;
		cardDrawCount = 0;
		playInSession = false;
		dealCards.setDisable(false);
		imageView2 = null;
		imageView3 = null;
		imageView4 = null;
		imageView5 = null;
		imageView6 = null;
		imageView7 = null;
		
		gameStatsList = new ListView<String>();
		
		//Start a new instance of the game
		try {
			startGame(primaryStage1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//showNewDeckMessage:
	//	Show a window that relays message when the deck is less than 6 cards
	//
	public void showNewDeckMessage() throws FileNotFoundException {
		
		//Create stage 
		Stage s = new Stage();
		
		//Get image that will be the background of the window
		Image newDeck = new Image(new FileInputStream("src/newDeck.png"));
		ImageView newDeckImage = new ImageView(newDeck);
		
		//Create group and scene 
		Group root = new Group(newDeckImage);
		Scene scene = new Scene(root);
		
		//Set scene
		s.setScene(scene);
		s.setAlwaysOnTop(true);
		s.setResizable(false);
		s.centerOnScreen();
		s.show();
	}
	
	//showWhoWon:
	//	Show a window that shows who won
	//
	public void showWhoWon() throws FileNotFoundException {
		
		//Create stage 
		Stage s = new Stage();
		
		//Get image that will be the background of the window
		Image newDeck;
		ImageView newDeckImage;
		
		//call whoWon function in gameLogic class and store the returned string
		String whoWon = gameLogic.whoWon(playerHand, bankerHand);
		
		//Declare root and handle the 3 cases: Player wins, Banker Wins, and Tie
		Group root;
		if (whoWon.equals("Player")) {
			newDeck = new Image(new FileInputStream("src/playerWins.png"));
			newDeckImage = new ImageView(newDeck);
			root = new Group(newDeckImage);
			setGameStats();
			s.setOnCloseRequest(e -> {
				try {
					nextPlay();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			});

		}
		else if (whoWon.equals("Banker")) {
			newDeck = new Image(new FileInputStream("src/bankerWins.png"));
			newDeckImage = new ImageView(newDeck);
			root = new Group(newDeckImage);
			setGameStats();
			s.setOnCloseRequest(e -> {
				try {
					nextPlay();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			});

		}
		else {
			newDeck = new Image(new FileInputStream("src/Tie.png"));
			newDeckImage = new ImageView(newDeck);
			root = new Group(newDeckImage);
			setGameStats();
			s.setOnCloseRequest(e -> {
				try {
					nextPlay();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			});
		}
		
		
		
		//Set scene
		Scene scene = new Scene(root);
		s.setScene(scene);
		s.setAlwaysOnTop(true);
		s.setResizable(false);
		s.centerOnScreen();
		s.show();
	}
	
	//startGame:
	//	Function that starts an instance of the play 
	public void startGame(Stage stage) throws FileNotFoundException 
	{	
		//Check if the deck size is 5 or less, and generate new deck if needed
		if(g.theDealer.deck.size() <= 5) {
			g.theDealer.deleteDeck();
			g.theDealer.generateDeck();
			g.theDealer.shuffleDeck();
			showNewDeckMessage();
		}
		
		//Start screen of the game 
		Scene scene2 = createGameScene(g);
		stage.setScene(scene2);
		stage.centerOnScreen();
		stage.setResizable(false);
		stage.show();
	}
	
	//start:
	//	Function that starts gameplay after the user clicks start game
	@Override
 	public void start(Stage primaryStage) throws FileNotFoundException{
		
		//Initialize balance to $10000 and current bet to $0
		totalWinnings = 10000;
		currentBet = 0;
		
		//Declare instance of BaccaratGame and store into global variable
		// This will allow the same deck to be used for multiple gameplays
		BaccaratGame firstPlay = new BaccaratGame();
		firstPlay.theDealer.generateDeck();
		firstPlay.theDealer.shuffleDeck();
		g = firstPlay;
		
		//Get the image that will be the background of the start window
		Image startGameImage = new Image(new FileInputStream("src/titlepage.png"));
		ImageView imageView = new ImageView(startGameImage);
		
		//Set the scene on stage
		Scene scene1 = createStartScene(imageView);
		primaryStage.setScene(scene1);

		//When the start button is pushed, call startGame
		startButton.setOnAction(e -> {
			try {
				startGame(primaryStage);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		//Set the stage
		primaryStage.setTitle("Baccarat");
		primaryS.tage1 = primaryStage;
		primaryStage.setResizable(false);
		primaryStage.show();
	}
 	
	//nextPlay:
	//	Function that calculates the winnings and moves the game forward
 	public void nextPlay() throws FileNotFoundException {
 		
 		//Before starting the next play evaluate winnings and set current bet to 0
 		totalWinnings += evaluateWinnings();
		currentBet = 0;
		curWin.setText("Balance: $" + totalWinnings + "0");
		wager.setText("Wager: $" + currentBet + "0");
		
		//Call initializer for the variables and start next game with same deck
		cleanUp();
 		startGame(primaryStage1);
 	}
 	
	//handle:
 	//	Handle that gets called when buttons on the poker table are clicked
	@Override
	public void handle(ActionEvent event) {
		
		//If the user clicks draw card, disable all betting options
		if (event.getSource() == drawCard) {
			cardDrawCount++;
			bet10.setDisable(true);
			bet25.setDisable(true);
			bet100.setDisable(true);
			bet500.setDisable(true);
			bet5000.setDisable(true);
			bet25000.setDisable(true);
			dealCards.setDisable(true);
			
			//If user hits draw card for the first time
			// show the first card 
			if (cardDrawCount == 1) {
				imageView2.setVisible(true);
				
				Circle r = new Circle(10);
				r.relocate(165,355);
	
				PathTransition transition = new PathTransition();
				transition.setNode(imageView2);
				transition.setDuration(Duration.millis(230));
				transition.setPath(r);
				transition.setCycleCount(1);
				transition.play();
			}
			//If user hits draw card for the second time
			// show the second card 
			else if (cardDrawCount == 2){
				imageView4.setVisible(true);
				
				Circle r = new Circle(10);
				r.relocate(792,355);
	
				PathTransition transition = new PathTransition();
				transition.setNode(imageView4);
				transition.setDuration(Duration.millis(230));
				transition.setPath(r);
				transition.setCycleCount(1);
				transition.play();
			}
			//If user hits draw card for the third time
			// show the third card 
			else if (cardDrawCount == 3){
				imageView3.setVisible(true);
				
				Circle r = new Circle(10);
				r.relocate(190,380);
				
				PathTransition transition = new PathTransition();
				transition.setNode(imageView3);
				transition.setDuration(Duration.millis(200));
				transition.setPath(r);
				transition.setCycleCount(1);
				transition.play();
			}
			//If user hits draw card for the fourth time
			// show the fourth card 
			else if(cardDrawCount == 4) {
				imageView5.setVisible(true);
				Circle r = new Circle(10);
				r.relocate(817,380);
	
				PathTransition transition = new PathTransition();
				transition.setNode(imageView5);
				transition.setDuration(Duration.millis(200));
				transition.setPath(r);
				transition.setCycleCount(1);
				transition.play();
				
				//Evaluate if there is a next card to be drawn, if not
				// then, show who won
				if (imageView7 == null && imageView6 == null){
					drawCard.setDisable(true);
					try {
						showWhoWon();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
	
			}
			//If user hits draw card for the fifth time
			// show the fifth card 
			else if (cardDrawCount == 5){
				if (imageView7 != null) {
					imageView7.setVisible(true);
				  	Circle r = new Circle(10);
					r.relocate(215,405);
	
					PathTransition transition = new PathTransition();
					transition.setNode(imageView7);
					transition.setDuration(Duration.millis(200));
					transition.setPath(r);
					transition.setCycleCount(1);
					transition.play();
				}
				else {
					//Evaluate if there is a next card to be drawn, if not
					// then, show who won
					if (imageView6 == null){
						drawCard.setDisable(true);
						try {
							showWhoWon();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
					else {
						imageView6.setVisible(true);
					  	Circle r = new Circle(10);
					  	r.relocate(842,405);
		
						PathTransition transition = new PathTransition();
						transition.setNode(imageView6);
						transition.setDuration(Duration.millis(200));
						transition.setPath(r);
						transition.setCycleCount(1);
						transition.play();
						try {
							showWhoWon();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			}
			//If user hits draw card for the sixth time
			// show the sixth card 
			else if (cardDrawCount == 6){
				if (imageView6 != null) {
				  imageView6.setVisible(true);	
				  drawCard.setDisable(true);
				  Circle r = new Circle(10);
					r.relocate(842,405);
					PathTransition transition = new PathTransition();
					transition.setNode(imageView6);
					transition.setDuration(Duration.millis(200));
					transition.setPath(r);
					transition.setCycleCount(1);
					transition.play();
				}
				else {
					drawCard.setDisable(true);
				}
				try {
					showWhoWon();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//createStartScene:
	//	Start scene that shows when the game has started
	//
	public Scene createStartScene(ImageView image) {
		
		//Create a start and set style
		startButton = new Button("Start Game");
		startButton.setStyle("-fx-font-size: 1.5em; -fx-font-color: #000000; -fx-background-color: #005716; ");
		startButton.setLayoutX(375);
		startButton.setLayoutY(200);
		startButton.setMinWidth(100);
		startButton.setMinHeight(25);
		
		//Add to root and scene
		Group root = new Group(image, startButton);
		Scene scene = new Scene(root);
		
		//Return the scene
		return scene;
	}
	
	//createGameScene:
	//	Game scene that has poker table once the game has started
	//
	public Scene createGameScene(BaccaratGame g) throws FileNotFoundException 
	{
		//Get image that will be the background of the window
		gameBoardImage = new Image(new FileInputStream("src/board.png"));
		imageViewBoard = new ImageView(gameBoardImage);
		
		Image playerBetImage = new Image(new FileInputStream("src/board.png"));
		playerBetBoard = new ImageView(playerBetImage);
		
		Image bankerBetImage = new Image(new FileInputStream("src/board.png"));
		bankerBetBoard = new ImageView(bankerBetImage);
		
		Image TieBetImage = new Image(new FileInputStream("src/board.png"));
		tieBetBoard = new ImageView(TieBetImage);
		
		//Deal player and banker hand
		playerHand = g.theDealer.dealHand();	
		bankerHand = g.theDealer.dealHand();
		
		playerFirst2 = playerHand;
		bankerFirst2 = bankerHand;
		
		//Get the numbers associated with each of the 4 cards
		int playerCard1 = playerHand.get(0).cardNumber;
		int playerCard2 = playerHand.get(1).cardNumber;
		int bankerCard1 = bankerHand.get(0).cardNumber;
		int bankerCard2 = bankerHand.get(1).cardNumber;
		
		
		//Determine if the player is to draw another card
		// and if so, get the image of the card
		if (g.gameLogic.evaluatePlayerDraw(playerHand) )
		{
			Card c;
			if (g.gameLogic.handTotal(bankerFirst2) != 8 && g.gameLogic.handTotal(bankerFirst2) != 9) {
				c = g.theDealer.drawOne();
				playerHand.add(c);
				
				Image playerCard3Image = g.theDealer.cardImages.get(c.cardNumber).getValue();
				imageView7 = new ImageView(playerCard3Image);
				
				//Determine if the banker is to draw another card
				// and if so, get the image of the card
				if (g.gameLogic.evaluateBankerDraw(bankerHand, c))
				{
					Card c1  = g.theDealer.drawOne();
					bankerHand.add(c1);
					Image bankerCard3Image = g.theDealer.cardImages.get(c1.cardNumber).getValue();
					imageView6 = new ImageView(bankerCard3Image);
				}
				else {
					imageView6 = null;
				}
			}
		}
		//If the player isn't drawing a 4th card, set 5th and 6th card to null
		else {
			Card c = null;
			if ( (g.gameLogic.evaluateBankerDraw(bankerHand, c) && (g.gameLogic.handTotal(playerFirst2) != 8 && g.gameLogic.handTotal(playerFirst2) != 9) ) ) {
				c  = g.theDealer.drawOne();
				bankerHand.add(c);
				Image bankerCard3Image = g.theDealer.cardImages.get(c.cardNumber).getValue();
				imageView6 = new ImageView(bankerCard3Image);
				imageView7 = null;
			}
			else {
				imageView6 = null; 
				imageView7 = null;
			}
		}
		
		//Get the images from the HashMap in BaccaratDealer cardImages for each card
		//Get player images
		Image playerCard1Image = g.theDealer.cardImages.get(playerCard1).getValue();
		Image playerCard2Image = g.theDealer.cardImages.get(playerCard2).getValue();
		
		//Get banker images
		Image bankerCard1Image = g.theDealer.cardImages.get(bankerCard1).getValue();
		Image bankerCard2Image = g.theDealer.cardImages.get(bankerCard2).getValue();
		
		//Store the images in ImageView objects
		imageView2 = new ImageView(playerCard1Image);
		imageView3 = new ImageView(playerCard2Image);
		imageView4 = new ImageView(bankerCard1Image);
		imageView5 = new ImageView(bankerCard2Image);
		
		//Store the background of the window
		Group root = new Group(imageViewBoard);
		Scene scene = new Scene(root, 1344, 755);
		
		//Create BorderPane object
		BorderPane borderPane = new BorderPane();
		
		//Boxes to be added to borderPane
		HBox topButtons = new HBox();
		VBox leftButtons = new VBox();
		HBox gameStats = new HBox();
		
		//Buttons
		topButtons.setSpacing(30);
		leftButtons.setSpacing(30);
		gameStats.setSpacing(30);
		
		//Add boxes to borderPane
		borderPane.setTop(topButtons);
		borderPane.setLeft(leftButtons);
		borderPane.setBottom(gameStats);
		
		//Create Draw button and set it to disabled
		drawCard = new Button("Draw");
		drawCard.setStyle("-fx-font-size: 20px; -fx-background-color: #005716; -fx-border-color: #014714; -fx-border-width: 5; -fx-border-insets: 1;");
		drawCard.setPadding(new Insets(10));
		drawCard.setDisable(true);
		drawCard.setOnAction(this);
		
		//Create Deal button 
		dealCards = new Button("Deal");
		dealCards.setStyle("-fx-font-size: 20px; -fx-background-color: #005716; -fx-border-color: #014714;-fx-border-width: 5; -fx-border-insets: 1;");
		dealCards.setPadding(new Insets(10));
		
		//When the deal button is clicked, enable the draw card button and set playInSession to true
		dealCards.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) {
				drawCard.setDisable(false);
				dealCards.setDisable(true);
				playInSession = true;
			}
		});

		//Player card 1
		imageView3.setX(-55);
		imageView3.setY(315);
		imageView3.setFitWidth(100);
		imageView3.setFitHeight(150);
		imageView3.setVisible(false);
		
		//Player card 2
		imageView2.setX(-80);
		imageView2.setY(290);
		imageView2.setFitWidth(100);
		imageView2.setFitHeight(150);
		imageView2.setVisible(false);
		
		//Banker card 1
		imageView4.setX(575);
		imageView4.setY(315);
		imageView4.setFitWidth(100);
		imageView4.setFitHeight(150);
		imageView4.setVisible(false);
		
		//Banker card 2
		imageView5.setX(550);
		imageView5.setY(290);
		imageView5.setFitWidth(100);
		imageView5.setFitHeight(150);
		imageView5.setVisible(false);
		
		Pane cardsPlace = new Pane();
		
		//If the banker and player's third card is not null, then add them onto the cardsPlace pane
		if (imageView7 != null && imageView6 != null) {
			//Player card 3
			imageView7.setX(-30);
			imageView7.setY(340);
			imageView7.setFitWidth(100);
			imageView7.setFitHeight(150);
			imageView7.setVisible(false);
			
			//Banker card 3
			imageView6.setX(600);
			imageView6.setY(340);
			imageView6.setFitWidth(100);
			imageView6.setFitHeight(150);
			imageView6.setVisible(false);
			
			cardsPlace.getChildren().addAll(imageView2, imageView3, imageView4, imageView5, imageView7, imageView6);
		}
		//If the player's third card is not null, then add it to the cardsPlace pane
		else if (imageView7 != null) {
			//Player card 3
			imageView7.setX(-30);
			imageView7.setY(340);
			imageView7.setFitWidth(100);
			imageView7.setFitHeight(150);
			imageView7.setVisible(false);
			
			cardsPlace.getChildren().addAll(imageView2, imageView3, imageView4, imageView5, imageView7);
		}
		//If the banker's third card is not null, then add it to the cardsPlace pane
		else if (imageView6 != null) {
			//Banker card 3
			imageView6.setX(600);
			imageView6.setY(340);
			imageView6.setFitWidth(100);
			imageView6.setFitHeight(150);
			imageView6.setVisible(false);
			
			cardsPlace.getChildren().addAll(imageView2, imageView3, imageView4, imageView5, imageView6);
		}
		//Otherwise add only the first 4 cards to the cardsPlace pane
		else {
			cardsPlace.getChildren().addAll(imageView2, imageView3, imageView4, imageView5);
		}
		
		//Set cardsPlace pane in the center of borderPane
		borderPane.setCenter(cardsPlace);
		
		//Buttons for padding
		Button blank = new Button("");
		Button blank2 = new Button("");
		Button blank3 = new Button("");
		Button blank4 = new Button("");
		blank.setVisible(false);
		blank2.setVisible(false);
		blank3.setVisible(false);
		blank4.setVisible(false);
		
		//Create button for betting $10 and have it add $10 on action
		bet10 = new Button("$10");
		bet10.setStyle("-fx-font-size: 15px; -fx-background-color: #005716; -fx-border-color: #014714;-fx-border-width: 5; -fx-border-insets: 1;");
		bet10.setPadding(new Insets(8));
		bet10.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event) {
						if (currentBet + 10 <= totalWinnings) {
							currentBet += 10;
							wager.setText("Wager: $" + currentBet + "0");
						}
					}
				});
		
		//Create button for betting $25 and have it add $25 on action
		bet25 = new Button("$25");
		bet25.setStyle("-fx-font-size: 15px; -fx-background-color: #005716; -fx-border-color: #014714;-fx-border-width: 5; -fx-border-insets: 1;");
		bet25.setPadding(new Insets(8));
		bet25.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event) {
						if (currentBet + 25 <= totalWinnings) {
							currentBet += 25;
							wager.setText("Wager: $" + currentBet + "0");
						}
					}
				});
		
		//Create button for betting $100 and have it add $100 on action
		bet100 = new Button("$100");
		bet100.setStyle("-fx-font-size: 15px; -fx-background-color: #005716; -fx-border-color: #014714;-fx-border-width: 5; -fx-border-insets: 1;");
		bet100.setPadding(new Insets(8));
		bet100.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event) {
						if (currentBet + 100 <= totalWinnings) {
							currentBet += 100;
							wager.setText("Wager: $" + currentBet + "0");
						}
					}
				});
		
		//Create button for betting $500 and have it add $500 on action
		bet500 = new Button("$500");
		bet500.setStyle("-fx-font-size: 15px; -fx-background-color: #005716; -fx-border-color: #014714;-fx-border-width: 5; -fx-border-insets: 1;");
		bet500.setPadding(new Insets(8));
		bet500.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event) {
						if (currentBet + 500 <= totalWinnings) {
							currentBet += 500;
							wager.setText("Wager: $" + currentBet + "0");
						}
					}
				});
		//Create button for betting $5000 and have it add $5000 on action
		bet5000 = new Button("$5000");
		bet5000.setStyle("-fx-font-size: 15px; -fx-background-color: #005716; -fx-border-color: #014714;-fx-border-width: 5; -fx-border-insets: 1;");
		bet5000.setPadding(new Insets(8));
		bet5000.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event) {
						if (currentBet + 5000 <= totalWinnings) {
							currentBet += 5000;
							wager.setText("Wager: $" + currentBet + "0");
						}
					}
				});
		//Create button for betting $25000 and have it add $25000 on action
		bet25000 = new Button("$25000");
		bet25000.setStyle("-fx-font-size: 15px; -fx-background-color: #005716; -fx-border-color: #014714;-fx-border-width: 5; -fx-border-insets: 1;");
		bet25000.setPadding(new Insets(8));
		bet25000.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event) {
						if (currentBet + 25000 <= totalWinnings) {
							currentBet += 25000;
							wager.setText("Wager: $" + currentBet + "0");
						}
					}
				});
		
		//Create button for betting on player and if the play is not in session, have it change color and change userBetPlayer to true
		betPlayer = new Button("Player");
		betPlayer.setStyle("-fx-font-size: 15px; -fx-background-color: #506e50; -fx-border-color: #014714;-fx-border-width: 3; -fx-border-insets: 2;");
		betPlayer.setPadding(new Insets(8));
		betPlayer.relocate(370,600);
		betPlayer.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event) {
						if (playInSession == false) {
							userBetPlayer = true;
							userBetTie = false;
							userBetBanker = false;
							betPlayer.setStyle("-fx-font-size: 15px; -fx-background-color: #057301; -fx-border-color: #014714;-fx-border-width: 3; -fx-border-insets: 2;");
							betTie.setStyle("-fx-font-size: 15px; -fx-background-color: #506e50; -fx-border-color: #014714;-fx-border-width: 3; -fx-border-insets: 2;");
							betBanker.setStyle("-fx-font-size: 15px; -fx-background-color: #506e50; -fx-border-color: #014714;-fx-border-width: 3; -fx-border-insets: 2;");
						}
					}
				});
		//Create button for betting on tie and if the play is not in session, have it change color and change userBetTie to true
		betTie = new Button("Tie");
		betTie.setStyle("-fx-font-size: 15px; -fx-background-color: #506e50; -fx-border-color: #014714;-fx-border-width: 3; -fx-border-insets: 2;");
		betTie.setPadding(new Insets(8));
		betTie.relocate(495,600);
		betTie.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event) {
						if (playInSession == false) {
							userBetPlayer = false;
							userBetTie = true;
							userBetBanker = false;
							betPlayer.setStyle("-fx-font-size: 15px; -fx-background-color: #506e50; -fx-border-color: #014714;-fx-border-width: 3; -fx-border-insets: 2;");
							betTie.setStyle("-fx-font-size: 15px; -fx-background-color: #057301; -fx-border-color: #014714;-fx-border-width: 3; -fx-border-insets: 2;");
							betBanker.setStyle("-fx-font-size: 15px; -fx-background-color: #506e50; -fx-border-color: #014714;-fx-border-width: 3; -fx-border-insets: 2;");
						}
					}
				});
		//Create button for betting on banker and if the play is not in session, have it change color and change userBetBanker to true
		betBanker = new Button("Banker");
		betBanker.setStyle("-fx-font-size: 15px; -fx-background-color: #506e50; -fx-border-color: #014714;-fx-border-width: 3; -fx-border-insets: 2;");
		betBanker.setPadding(new Insets(8));
		betBanker.relocate(590,600);
		betBanker.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event) {
						if (playInSession == false) {
							userBetPlayer = false;
							userBetTie = false;
							userBetBanker = true;
							betTie.setStyle("-fx-font-size: 15px; -fx-background-color: #506e50; -fx-border-color: #014714;-fx-border-width: 3; -fx-border-insets: 2;");
							betPlayer.setStyle("-fx-font-size: 15px; -fx-background-color: #506e50; -fx-border-color: #014714;-fx-border-width: 3; -fx-border-insets: 2;");
							betBanker.setStyle("-fx-font-size: 15px; -fx-background-color: #057301; -fx-border-color: #014714;-fx-border-width: 3; -fx-border-insets: 2;");
						}
					}
				});
		
		//Create label that shows the current balance
		curWin = new Label("Balance: $" + totalWinnings + "0");
		curWin.setStyle("-fx-text-fill: #012103; -fx-font-size: 30px;");
		curWin.relocate(0, 715);
		
		//Add all buttons to topButtons and leftButtons boxes
		topButtons.getChildren().addAll(dealCards, drawCard);
		leftButtons.getChildren().addAll( blank, blank2, blank3, blank4, bet10, bet25, bet100, bet500, bet5000, bet25000);
		
		//Create label that shows the current wager
		wager = new Label();
		wager.setText("Wager: $" + currentBet + "0");
		wager.setStyle("-fx-font-family: 'cursive'; -fx-text-fill: #012103; -fx-font-size: 30px;");
		wager.relocate(0, 685);
		
		//Game menu for options
		Menu menu = new Menu("Game Options");
		menu.setStyle("-fx-background-color: #005716;");
		
		MenuItem freshStart = new MenuItem("Fresh start");
		MenuItem exit = new MenuItem("Exit");
		
		menu.getItems().add(freshStart);
		menu.getItems().add(exit);
		
		//Call handle function when user hits fresh start menu item
		freshStart.setOnAction(new EventHandler<ActionEvent> ()
				{
					@Override
					public void handle(ActionEvent event) {
						primaryStage1.close();
						freshStart();
					}
			
				});
		//Call handle function when user hits exit
		exit.setOnAction(e -> System.exit(0));
		
		//Creating menuBar and adding menu to it
		MenuBar gameMenu = new MenuBar();
		gameMenu.getMenus().add(menu);
		gameMenu.relocate(916, 0);
		gameMenu.setStyle("-fx-background-color: #005716;");
		
		//Setting the size and location of the game statistics listView
		gameStatsList.setMaxSize(207, 1300);
		gameStatsList.resize(210, 1000);
		gameStatsList.setStyle("-fx-font-color: #005716; -fx-background-color: #005716;-fx-background-insets: 0 ;-fx-border-width: 0px;");
		
		//Adding listview to Vbox that will go on the scene
		VBox vbox = new VBox();
		vbox.setStyle("-fx-border-style: solid inside;" +
	                "-fx-border-width: 5;" +
	                "-fx-border-color: #005716;");
		vbox.getChildren().add(gameStatsList);
		vbox.relocate(1085, 125);
		vbox.resize(207, 1002);
		
		//Add all panes, boxes, and buttons to root 2
		Group root2 = new Group(borderPane, leftButtons, topButtons, curWin, wager, betTie, betPlayer, betBanker, gameMenu, vbox);
		
		//Add root2 to root and return scene
		root.getChildren().add(root2);
		
		return scene;
	}
	
}
