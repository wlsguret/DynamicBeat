package dynamic_beat_10;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage();

	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menubar.png")));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);

	private int mouseX, mouseY;

	private boolean isMainScreen = false; 
	private boolean isGameScreen = false;

	ArrayList<Track> trackList = new ArrayList<Track>(); // ������ �� ������ ���� ����Ʈ����

	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	private Music introMusic = new Music("introMusic.mp3", true);
	private int nowSelected = 0;

	public DynamicBeat() {
		setUndecorated(true); // �⺻ �޴��� ��Ȱ��ȭ
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // ȭ��ũ�⸦ ���� �� �� ����
		setLocationRelativeTo(null); // ȭ���� ���߾ӿ� ����������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����â�� ���� �� ���α׷��� ����ǵ��� ����
		setVisible(true); // ȭ���� ���� ���̰� ���� �⺻���� false�̱� ������ �ʼ�����
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		introMusic.start();

		trackList.add(new Track("Answer Title Image.png", "Answer Start Image.jpg", "Answer Game Image.jpg",
				"Square_a_Saw_-_Answer.mp3", "Square_a_Saw_-_Answer.mp3"));
		trackList.add(new Track("Hold me Title Image.png", "Hold me Start Image.jpg", "Hold me Game Image.jpg",
				"Pokki_DJ_-_Hold_Me.mp3", "Pokki_DJ_-_Hold_Me.mp3"));
		trackList.add(new Track("Smoking Title Image.png", "Smoking Start Image.png", "Smoking Game Image.jpg",
				"Smoking_With_Poets_-_Pain_t__On_Me.mp3", "Smoking_With_Poets_-_Pain_t__On_Me.mp3"));

		
		
		
		exitButton.setBounds(1245, 0, 30, 30); // Bounds=���� (x,y,width,height)
		exitButton.setBorderPainted(false); // �ܰ������׸���(false)
		exitButton.setContentAreaFilled(false); // ���뿵��ä���(false)
		exitButton.setFocusPainted(false); // ����(focus)�Ǿ����� ����� �׵θ�(false)
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage); // ���콺�� �ö󰡸� �̹��� ����
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ���� �ո������ ����
				buttonEnteredMusicPlay();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage); // ���콺�� ������ ���� �̹����� ����
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Ŀ���� �⺻������ ����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				buttonPressedMusicPlay();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);

		startButton.setBounds(110, 220, 400, 100); // Bounds=���� (x,y,width,height)
		startButton.setBorderPainted(false); // �ܰ������׸���(false)
		startButton.setContentAreaFilled(false); // ���뿵��ä���(false)
		startButton.setFocusPainted(false); // ����(focus)�Ǿ����� ����� �׵θ�(false)
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage); // ���콺�� �ö󰡸� �̹��� ����
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ���� �ո������ ����
				buttonEnteredMusicPlay();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage); // ���콺�� ������ ���� �̹����� ����
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Ŀ���� �⺻������ ����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				buttonPressedMusicPlay();
				enterMain();
			}
		});
		add(startButton);

		quitButton.setBounds(110, 350, 400, 100); // Bounds=���� (x,y,width,height)
		quitButton.setBorderPainted(false); // �ܰ������׸���(false)
		quitButton.setContentAreaFilled(false); // ���뿵��ä���(false)
		quitButton.setFocusPainted(false); // ����(focus)�Ǿ����� ����� �׵θ�(false)
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage); // ���콺�� �ö󰡸� �̹��� ����
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ���� �ո������ ����
				buttonEnteredMusicPlay();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage); // ���콺�� ������ ���� �̹����� ����
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Ŀ���� �⺻������ ����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				buttonPressedMusicPlay();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);

		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60); // Bounds=���� (x,y,width,height)
		leftButton.setBorderPainted(false); // �ܰ������׸���(false)
		leftButton.setContentAreaFilled(false); // ���뿵��ä���(false)
		leftButton.setFocusPainted(false); // ����(focus)�Ǿ����� ����� �׵θ�(false)
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage); // ���콺�� �ö󰡸� �̹��� ����
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ���� �ո������ ����
				buttonEnteredMusicPlay();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage); // ���콺�� ������ ���� �̹����� ����
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Ŀ���� �⺻������ ����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				buttonPressedMusicPlay();
				selectLeft(); // ���� ���� �������� �̵�
			}
		});
		add(leftButton);

		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60); // Bounds=���� (x,y,width,height)
		rightButton.setBorderPainted(false); // �ܰ������׸���(false)
		rightButton.setContentAreaFilled(false); // ���뿵��ä���(false)
		rightButton.setFocusPainted(false); // ����(focus)�Ǿ����� ����� �׵θ�(false)
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage); // ���콺�� �ö󰡸� �̹��� ����
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ���� �ո������ ����
				buttonEnteredMusicPlay();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage); // ���콺�� ������ ���� �̹����� ����
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Ŀ���� �⺻������ ����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				buttonPressedMusicPlay();
				selectRight(); // ���� ���� ���������� �̵�
			}
		});
		add(rightButton);
		
		easyButton.setVisible(false);
		easyButton.setBounds(375, 630, 250, 67); // Bounds=���� (x,y,width,height)
		easyButton.setBorderPainted(false); // �ܰ������׸���(false)
		easyButton.setContentAreaFilled(false); // ���뿵��ä���(false)
		easyButton.setFocusPainted(false); // ����(focus)�Ǿ����� ����� �׵θ�(false)
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage); // ���콺�� �ö󰡸� �̹��� ����
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ���� �ո������ ����
				buttonEnteredMusicPlay();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage); // ���콺�� ������ ���� �̹����� ����
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Ŀ���� �⺻������ ����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				buttonPressedMusicPlay();
				gameStart(nowSelected, "easy");
			}
		});
		add(easyButton);
		
		hardButton.setVisible(false);
		hardButton.setBounds(655, 630, 250, 67); // Bounds=���� (x,y,width,height)
		hardButton.setBorderPainted(false); // �ܰ������׸���(false)
		hardButton.setContentAreaFilled(false); // ���뿵��ä���(false)
		hardButton.setFocusPainted(false); // ����(focus)�Ǿ����� ����� �׵θ�(false)
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage); // ���콺�� �ö󰡸� �̹��� ����
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ���� �ո������ ����
				buttonEnteredMusicPlay();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage); // ���콺�� ������ ���� �̹����� ����
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Ŀ���� �⺻������ ����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				buttonPressedMusicPlay();
				gameStart(nowSelected, "hard");
			}
		});
		add(hardButton);
		
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60); // Bounds=���� (x,y,width,height)
		backButton.setBorderPainted(false); // �ܰ������׸���(false)
		backButton.setContentAreaFilled(false); // ���뿵��ä���(false)
		backButton.setFocusPainted(false); // ����(focus)�Ǿ����� ����� �׵θ�(false)
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage); // ���콺�� �ö󰡸� �̹��� ����
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ���� �ո������ ����
				buttonEnteredMusicPlay();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage); // ���콺�� ������ ���� �̹����� ����
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Ŀ���� �⺻������ ����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				buttonPressedMusicPlay();
				backMain();
			}
		});
		add(backButton);

		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY); // �޴��ٸ� ���콺�� ��� �̵�����ġ�� ȭ���� ��Ÿ��
			}
		});
		add(menuBar);
		
	}

	
	
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) { // g.drawImgae() �� '�̹���'�� '�����̴� �̹���' �׸� �� ���
											// paintComponents()�� add(menuBar); ó�� "add()" �� �߰��� �̹����� �׸� �� ���
		g.drawImage(background, 0, 0, null); // ��� �׸���
		if (isMainScreen) {
			g.drawImage(selectedImage, 390, 110, null); // �� �����̹��� �׸���
			g.drawImage(titleImage, 390, 300, null); // �� Ÿ��Ʋ�̹��� �׸���
		}
		if(isGameScreen) {
			g.drawImage(noteRouteImage, 228, 30, null);
			g.drawImage(noteRouteImage, 332, 30, null);
			g.drawImage(noteRouteImage, 436, 30, null);
			g.drawImage(noteRouteImage, 540, 30, null);
			g.drawImage(noteRouteImage, 640, 30, null);
			g.drawImage(noteRouteImage, 744, 30, null);
			g.drawImage(noteRouteImage, 848, 30, null);
			g.drawImage(noteRouteImage, 952, 30, null);
			g.drawImage(noteRouteLineImage, 224, 30, null);
			g.drawImage(noteRouteLineImage, 328, 30, null);
			g.drawImage(noteRouteLineImage, 432, 30, null);
			g.drawImage(noteRouteLineImage, 536, 30, null);
			g.drawImage(noteRouteLineImage, 740, 30, null);
			g.drawImage(noteRouteLineImage, 844, 30, null);
			g.drawImage(noteRouteLineImage, 948, 30, null);
			g.drawImage(noteRouteLineImage, 1052, 30, null);
			g.drawImage(gameInfoImage, 0, 660, null); 
			g.drawImage(judgementLineImage, 0, 580, null);
			g.drawImage(noteBasicImage, 228, 120, null);
			g.drawImage(noteBasicImage, 332, 580, null);
			g.drawImage(noteBasicImage, 436, 500, null);
			g.drawImage(noteBasicImage, 540, 340, null);
			g.drawImage(noteBasicImage, 640, 340, null);
			g.drawImage(noteBasicImage, 744, 325, null);
			g.drawImage(noteBasicImage, 848, 305, null);
			g.drawImage(noteBasicImage, 952, 305, null);
			g.setColor(Color.white);
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("Square_a_Saw_-_Answer", 20, 702);
			g.drawString("Easy", 1190, 702);
			g.setFont(new Font("Arial", Font.PLAIN, 26));
			g.setColor(Color.DARK_GRAY);
			g.drawString("S", 270, 609);
			g.drawString("D", 374, 609);
			g.drawString("F", 478, 609);
			g.drawString("Space Bar", 580, 609);
			g.drawString("J", 784, 609);
			g.drawString("K", 889, 609);
			g.drawString("L", 993, 609);
			g.setColor(Color.LIGHT_GRAY);
			g.setFont(new Font("Elephant", Font.BOLD, 30));
			g.drawString("000000", 565, 702);	
		}
		paintComponents(g);
		this.repaint();
	}

	public void selectTrack(int nowSelected) { // ���� �� ���� �����Ҷ� �̹����� ������ �ٲٴ� �Լ�
		if (selectedMusic != null)
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImgae()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();

	}

	public void selectLeft() {
		if (nowSelected == 0) // ���� ���õ� ���� ù���̶�� ������������ �̵�
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0; // ���� ���õ� ���� ���������̶�� ù������ �̵�
		else
			nowSelected++;
		selectTrack(nowSelected);
	}
	
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic != null)
			selectedMusic.close();
		isMainScreen = false;
		leftButton.setVisible(false); 
		rightButton.setVisible(false); 
		easyButton.setVisible(false); 
		hardButton.setVisible(false); 
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImgae())).getImage();
		backButton.setVisible(true);
		isGameScreen = true;
		
	}
	
	public void backMain() {
		backButton.setVisible(false);
		isGameScreen = false;
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		leftButton.setVisible(true); 
		rightButton.setVisible(true); 
		easyButton.setVisible(true); 
		hardButton.setVisible(true);
		isMainScreen = true;
		selectTrack(nowSelected);
	}
	
	public void enterMain() {
		startButton.setVisible(false); 
		quitButton.setVisible(false); 
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage(); 																												
		leftButton.setVisible(true); 
		rightButton.setVisible(true); 
		easyButton.setVisible(true); 
		hardButton.setVisible(true); 
		isMainScreen = true;
		introMusic.close(); // ����ȭ�鿡�� ��Ʈ�� ������ �����Ѵ�
		selectTrack(0); // Ʈ������Ʈ���� ù��° ���� �����ض�
	}
	
	public void buttonEnteredMusicPlay() {
		Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
		buttonEnteredMusic.start();
	}
	
	public void buttonPressedMusicPlay() {
		Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
		buttonPressedMusic.start();
	}

	
}