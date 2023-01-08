//Final Project
//Name: Ridwanul Haque
//Class: ICS3U1
//Teacher: Ms.Strelkovska
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;// with this I can get something that can compare 2 2d arrays
//import java.util.Timer;
//import java.util.TimerTask;
public class FinalProjectMaze extends JPanel implements  KeyListener, ActionListener, MouseListener  {

	// variables  
	int map[][]={
			{1,0,0,1,1,1,1,0,0,0,1},
			{1,0,0,0,1,0,1,1,1,1,1},
			{1,1,1,1,1,0,0,0,0,1,1},
			{1,0,0,1,0,0,0,1,0,1,1},
			{1,0,0,1,0,1,1,1,0,0,1},
			{1,0,0,0,0,0,0,1,0,1,1},
			{1,0,1,1,1,1,1,1,1,1,1},
			{1,0,1,0,0,0,0,1,0,1,1},
			{1,0,1,1,1,0,0,1,0,1,1},
			{1,0,0,0,0,1,0,0,1,1,1},
			{1,1,1,1,0,1,1,1,1,1,0}};

	int reveal[][]={
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0}};
	int bombCount[][]={
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2}};
	int zeros[][]={
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2},
		{2,2,2,2,2,2,2,2,2,2,2}};
	int check[][]={
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0}};

	ImageIcon pic1, pic2, flag;    //variables for the images

	JLabel lbl[][] = new JLabel[map.length][map[0].length];       // 2D array of JLabel
	JButton howTo, exit;     //declare 2 buttons
	JLabel lifelbl;
	JLabel scorelbl;
	JLabel timerlbl;
	int score= 0;
	int numLives=3;
	int secondspassed= 0;
	int displayMinutes=0;
	int randomNum = 0;
	int clickA = 1;
	int clickB = 1;
	int challenge;
	boolean gameIsOver=false;
	Object[] possibilities = {"Easy", "Normal", "Hard","Impossible"};
	Timer timer;
	//TimerTask task;
	JPanel btnPanel = new JPanel();  // center panel for the maze
	JPanel southPanel = new JPanel();  // bottom panel for the buttons
	JPanel northPanel = new JPanel();
	
	
	public FinalProjectMaze(){    // constructor
		this.setLayout(new BorderLayout());
		// load images
		flag = new ImageIcon("RedFlag(1).png");
		pic1= new ImageIcon("bomb3.png");
		pic2= new ImageIcon("block3.png");
		
		btnPanel.setLayout(new GridLayout(lbl.length,lbl[0].length));// will be 11 x 11 grid 
		southPanel.setLayout(new FlowLayout()); //  for the buttons
		//Get users input for difficulty
		String s = (String)JOptionPane.showInputDialog(null, "What Difficulty level do you wanna try?", "Choose Difficulty:",JOptionPane.QUESTION_MESSAGE,null,possibilities,possibilities[0]);
		if(s == possibilities[0]){ // determines what you selected and genrate the map accordinly
			challenge = 0;
			System.out.println("Easy");
			for (int a = 0; a < map.length; a++ ) {
				for (int b = 0; b < map[0].length; b++ ) {
					randomNum = 0;
					randomNum = (int)Math.round(Math.random()*10);
					if(randomNum>8){
						randomNum=0;
					}
					else if(randomNum<=8){
						randomNum=1;
					}
					map[a][b]=randomNum;
				}
			}
		}
		else if(s == possibilities[1]){
			challenge = 1;
			System.out.println("Normal");
			for (int a = 0; a < map.length; a++ ) {
				for (int b = 0; b < map[0].length; b++ ) {
					randomNum = 0;
					randomNum = (int)Math.round(Math.random()*10);
					if(randomNum>7){
						randomNum=0;
					}
					else if(randomNum<=7){
						randomNum=1;
					}
					map[a][b]=randomNum;
				}
			}
		}
		else if(s == possibilities[2]){
			challenge = 2;
			System.out.println("Hard");
			for (int a = 0; a < map.length; a++ ) {
				for (int b = 0; b < map[0].length; b++ ) {
					randomNum = 0;
					randomNum = (int)Math.round(Math.random()*10);
					if(randomNum>5){
						randomNum=0;
					}
					else if(randomNum<=5){
						randomNum=1;
					}
					map[a][b]=randomNum;
				}
			}
		}
		else if(s == possibilities[3]){
			challenge = 3;
			System.out.println("Impossible");
			for (int a = 0; a < map.length; a++ ) {
				for (int b = 0; b < map[0].length; b++ ) {
					randomNum = 0;
					randomNum = (int)Math.round(Math.random()*10);
					if(randomNum>3){
						randomNum=0;
					}
					else if(randomNum<=3){
						randomNum=1;
					}
					map[a][b]=randomNum;
				}
			}
		}
		else{
			System.exit(0);
		}
		for (int i = 0; i < lbl.length; i++ ) {
			for (int j = 0; j < lbl[0].length; j++ ) {
				lbl[i][j] = new JLabel();                    //create labels
				lbl[i][j].setName(i+"," + j);
				lbl[i][j].addMouseListener(this); 
				if (map[i][j]==0 && reveal[i][j]==1){
					lbl[i][j].setIcon(pic1);     // set image bomb on the label
				}
				else if(map[i][j]==0 && reveal[i][i]==0){
					lbl[i][j].setIcon(pic2);      
				}
				else if(map[i][j]==1){
					lbl[i][j].setIcon(pic2);      // set image block on the label
				}

				btnPanel.add(lbl[i][j] );     // add labels to the center panel
			}
		}	

		this.add( southPanel, BorderLayout.SOUTH );
		this.add(northPanel,BorderLayout.NORTH);
		this.add( btnPanel, BorderLayout.CENTER  );
		
		timer = new Timer(1000,this);
		/*task = new TimerTask(){
			public void run(){
				secondspassed++;
				if(secondspassed==60)
				{
					secondspassed=0;
				}
				if((secondspassed%60)==0){
				displayMinutes++;
				}
				timerlbl.setText("Timer: " + displayMinutes+":"+secondspassed);	
			}
		};*/
		// Controls For NorthPanel
		
		lifelbl = new JLabel("3 Lives ",SwingConstants.CENTER);
		northPanel.add(lifelbl);
		scorelbl = new JLabel("Score ",SwingConstants.CENTER);
		northPanel.add(scorelbl);
		timerlbl = new JLabel("Timer ", SwingConstants.LEFT);
		northPanel.add(timerlbl);

		// Control For SouthPanel
		//set button b3  
		howTo= new JButton("How To Play");
		howTo.addKeyListener(this);
		southPanel.add(howTo);
		howTo.addActionListener(this);

		exit= new JButton("Exit");
		exit.addKeyListener(this);
		southPanel.add(exit);
		exit.addActionListener(this);

		//addMouseListener(this);  // add mouse listener to the panel

	}// end of constructor

	//tell numbers around selected tile
	public void countNumbers(int i,int j){
		int count = 0;
		for(int r = i-1; r<i+2; r++){
			for(int c = j-1; c<j+2; c++){
				if(r>=0 && r<map.length && c>= 0 && c< map.length ){
					if(map[r][c]==0){
						count++;
						
					}
				}
			}
		}
		if(count==0){
			zeros[i][j]=count;
		}
		
		bombCount[i][j]=count;
		lbl[i][j].setIcon(null);
		lbl[i][j].setText("      "+String.valueOf(count));
	}
	public void surroundings(int i , int j){
		for(int r = i-1; r<i+2; r++){ // instead I thought of this.
			for(int c = j-1; c<j+2; c++){
				if(r>=0 && r<map.length && c>= 0 && c< map.length ){
						if(map[r][c]==0 && reveal[r][c]==0){
						reveal[r][c] = 1;
						lbl[r][c].setIcon(pic1);     // set image bomb on the label
					}
					else if(map[r][c]==1){
						countNumbers(r,c);
						check[r][c]=1;
						if(zeros[r][c]!=0){
							zeros[r][c]=1;
						}
					}							
				}
			}
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==howTo){
			JOptionPane.showMessageDialog(null, "The goal of the game is to click all the nonbomb tiles while trying to avoid the bombs.\n1. A number appears once you click a nonbomb tile; that number tells you how many bombs are near that tile.\n2.Your score increase for each nonbomb tile you click. \n3. Once your life hits zero you lose and the game exits.\n*Tip: Use the numbers to determine where the bombs are.\n*Tip: You can right click a square to place a flag where you think a bomb is. This will remind you to avoid that spot. ", "How To Play",JOptionPane.INFORMATION_MESSAGE ); 
		}
		else if (e.getSource()==timer){
			secondspassed++;
			if(secondspassed==60){
				secondspassed=0;
			}
			if((secondspassed%60)==0){
			displayMinutes++;
			}
			timerlbl.setText("Timer: " + displayMinutes+":"+ secondspassed);
		}
		else if(e.getSource() == exit){
			JOptionPane.showMessageDialog(null, "Bye!!!\n Your score is "+ score, "Difficulty: "+possibilities[challenge]+"... " + "You Lost",JOptionPane.INFORMATION_MESSAGE );
			System.exit(0);
		}

	}
	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {}

	public void mouseClicked(MouseEvent e) {
		JLabel label = (JLabel)e.getSource();
		String name = label.getName();
		boolean firstClick = false;
		int i = 0;
		int j = 0;

		if(name != null && name.length()>2){
			String[] nums = name.split(",");
			i = Integer.parseInt(nums[0]);
			j = Integer.parseInt(nums[1]);
			System.out.println(i + ", " + j);
			if(e.isMetaDown()){
				System.out.println("rightclick");
				if(map[i][j]==0 && reveal[i][j]==0 && clickA==1){ 
					lbl[i][j].setIcon(flag);     // set image flag on the label
					clickA=2;	
				}
				else if(map[i][j]==0 && reveal[i][j]==0 && clickA==2){ 
					lbl[i][j].setIcon(pic2);     // set image back
					clickA=1;
				}
				if(map[i][j]==1 && check[i][j]!=1 && clickB==1){					
					lbl[i][j].setIcon(flag);
					clickB=2;
				}
				else if(map[i][j]==1 && check[i][j]!=1 && clickB==2){					
					lbl[i][j].setIcon(pic2);     // set image back
					clickB=1;
				}
			}
			else{
				if(map[i][j]==0 && reveal[i][j]==0){
					reveal[i][j] = 1;
					lbl[i][j].setIcon(pic1);     // set image bomb on the label
					numLives--;
					lifelbl.setText("Lives: " + numLives);
					if(numLives<=0){
						timer.stop();
						JOptionPane.showMessageDialog(null, "Game Over!!!\n Your score is "+ score +"\nAnd finished at the time: " + displayMinutes+" minutes and "+secondspassed+" seconds.", "Difficulty: "+possibilities[challenge]+"... " + "You Lost",JOptionPane.INFORMATION_MESSAGE ); 
						System.exit(0);
					}
				}
				else if(map[i][j]==1){
					countNumbers(i,j);
					if(check[i][j]!=1){
						score++;
						scorelbl.setText("Score: "+ score);
					}
					check[i][j]=1;
					

				}
				boolean winner = Arrays.deepEquals(map,check); // Compares two 2d Array
				if(winner==true){
					timer.stop();
					JOptionPane.showMessageDialog(null, "You Win!!!\n Your score is "+ score +"\nAnd finished at the time: " + displayMinutes+" minutes and "+secondspassed+" seconds."	, "Difficulty: "+possibilities[challenge]+"... " +  "Congratulations!!!",JOptionPane.INFORMATION_MESSAGE ); 
					System.exit(0);
				}
				if(score==1){
					firstClick=true;
					timer.start();
				}

				if((firstClick==true && map[i][j]!=0) || bombCount[i][j]==0){
					if(zeros[i][j]!=1){
						surroundings(i,j);
					}
					//timer.scheduleAtFixedRate(task,1000,1000);
				}
			}
		}
		
		
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public static void main(String[] args) {
		JFrame f = new JFrame("MineSweeper");//java JFrame object

		Container cont = f.getContentPane();  // get container - top of the frame
		cont.setLayout(new BorderLayout());  // set Layout to Border 

		FinalProjectMaze bp= new FinalProjectMaze();  // create an object of our game panel
		cont.add(bp, BorderLayout.CENTER ); // add this game panel to the center of the frame

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // make frame closed when x button is pressed
		f.setVisible(true);     // make the frame visible
		f.setSize(500, 600);  // set the size of the frame
		f.setResizable(false);


	}//end of main
}//end of class