import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

import sun.audio.AudioStream;

public class MarioWorld {
	int x, y;
	int worldtype;
	MainCharacter mario = new MainCharacter(40, 400, "HARPS");
	List<Moveable> nonMarios = new ArrayList<Moveable>();
	List<Moveable> coins = new ArrayList<Moveable>();
	List<Platform> platforms = new ArrayList<Platform>();
	private MarioPanel panel;
	private int delay = 30;
	Timer timer;
	int score, time;
	boolean gameover=false;
	private int warningtime = 0;
	int dx = 0;
	boolean hover = false;
	boolean warning1 = false;
	Clip song;
	int spawntime = 0;
	BufferedImage image;
	private Image bkgrnd, shop, shop1, boss, warning;
	protected boolean ingame;

	public MarioWorld(MarioPanel marioPanel, int world) {
		worldtype = world;
		panel = marioPanel;
		try {
			warning = ImageIO.read(new File("Images/warning.jpg"));
			boss = ImageIO.read(new File("Images/boss.jpg"));
			image = ImageIO.read(new File("Images/coin_00000.png"));
			bkgrnd = ImageIO.read(new File("Images/scroller_background.png"));
			shop = ImageIO.read(new File("Images/back3.jpg"));
			shop1 = ImageIO.read(new File("Images/back3 hover.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Platform portal = new Platform(20, 500, 100, 20, 2);
		portal.type = 2;
		portal.typeofportal = "shop";
		platforms.add(portal);

		Platform p1 = new Platform(0, 520, 2000, 20, 1);

		platforms.add(p1);
	
		startTimer();

	}

	private void startTimer() {
		// TODO Auto-generated method stub
		timer = new Timer(delay, new ActionListener() {

			private int temp;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// what should happen each time the timer goes off?
				if (!mario.explode) {
					moveStuff();
				}
				temp = worldtype;
				checkCollisions();
				checkSpawnStuff();
				checkWorldChange();
				panel.repaint();

			}

			private void checkWorldChange() {
				if (worldtype != temp) {
					mario.enter = false;
					mario.x = 150;
					mario.y = 400;
					nonMarios.clear();
					coins.clear();
					platforms.clear();
					if (worldtype == 1) {
						Platform portal = new Platform(20, 500, 100, 20, 2);
						portal.type = 2;
						portal.typeofportal = "shop";
						platforms.add(portal);

						Platform p1 = new Platform(0, 520, 2000, 20, 1);

						platforms.add(p1);
					}
					if (worldtype == 2) {
						Platform portal = new Platform(1000, 500, 100, 20, 2);
						Platform portal1 = new Platform(1200, 500, 100, 20, 2);
						portal.type = 2;
						portal1.type = 2;
						portal.typeofportal = "one";
						portal1.typeofportal = "boss";
						platforms.add(portal);
						platforms.add(portal1);
						Platform p1 = new Platform(0, 520, 2000, 20, 3);
						platforms.add(p1);
					}
					if (worldtype == 3) {
						Platform portal = new Platform(20, 500, 100, 20, 2);
						portal.type = 2;
						portal.typeofportal = "shop";
						platforms.add(portal);

						Platform p1 = new Platform(0, 520, 2000, 20, 3);
						platforms.add(p1);
					}
				}

			}

			private void checkSpawnStuff() {
				// TODO Auto-generated method stub
				if (worldtype == 1 && ingame) {

					spawntime++;

					if (spawntime == 20) {
						for (Moveable m : nonMarios) {
							if (Math.random() < .4) {
								if (m.right) {
									m.right = false;
								} else
									m.right = true;
							}
						}
					}
					if (spawntime == 50 && nonMarios.size() < 15) {
						int xpos = (int) ((int) 400 + Math.random() * (601));
						if (xpos > mario.x + 10 || xpos < mario.x - 10) {
							Moveable m = new Moveable(xpos, 500);
							m.type = 1;
							nonMarios.add(m);
						}

						spawntime = 0;
					}

				} else if (worldtype == 3) {
					for (Moveable m : nonMarios) {
						if (Math.random() < .004 || m.x > 1350 || m.x < 50) {
							if (m.right) {
								m.right = false;
							} else
								m.right = true;
						}
					}
					if (nonMarios.size() == 0&&!gameover) {
						Moveable boss = new Moveable(500, 90);
						boss.type = 2;
						boss.w = 300;
						boss.h = 600;
						nonMarios.add(boss);
					}

				}
			}

			private void checkCollisions() {
				// TODO Auto-generated method stub
				int mx = mario.x;
				int my = mario.y;
				for (int y = 0; y < nonMarios.size(); y++) {
					if (nonMarios.get(y).deathtimer > 20) {
						nonMarios.get(y).coin1 = true;
						nonMarios.get(y).jump1 = true;

						coins.add(nonMarios.remove(y));

					}
				}
				if (coins.size() > 0) {
					for (int c = 0; c < coins.size(); c++) {
						int cx = coins.get(c).x;

						int cy = coins.get(c).y;

						if (cx > mx - 20 && cx < mx + 40 && cy > my - 40 && cy < my + 10) {
							coins.remove(c);
							score++;
						}
					}
				}
				if (mario.attack) {
					for (int x = 0; x < nonMarios.size(); x++) {
						int nmx = nonMarios.get(x).x;
						int nmy = nonMarios.get(x).y;

						if (nonMarios.get(x).type == 1 && nmx - mx < 40 && nmx - mx > 0 && nmy > my - 40 && nmy < my + 2
								&& mario.direction == 0) {
							nonMarios.get(x).dying = true;

						} else if (nonMarios.get(x).type == 1 && mx - nmx < 20 && mx - nmx > 0 && nmy > my - 40
								&& nmy < my + 2 && mario.direction == 1) {
							nonMarios.get(x).dying = true;

						} else if (nonMarios.get(x).type == 2 && mario.direction==0 && mx+30>nmx && mx+30<nmx+nonMarios.get(x).w){
							nonMarios.get(x).health -= 1;
					
							if (nonMarios.get(x).health == 0) {
								nonMarios.get(x).dying = true;
								gameover = true;
							}

						}
						else if (nonMarios.get(x).type == 2 && mario.direction==1 && mx-30>nmx && mx-30<nmx+nonMarios.get(x).w){
							nonMarios.get(x).health--;
							System.out.println("HIT!");
							if (nonMarios.get(x).health == 0) {
								nonMarios.get(x).dying = true;
								gameover = true;
							}
						}
					}
				}

				for (Platform d : platforms) {
					boolean within = mario.within(d);
					if (!mario.jumping && d.type == 2 && d.typeofportal.equals("shop") && within && mario.enter) {
						worldtype = 2;

					}
					if (!mario.jumping && d.type == 2 && d.typeofportal.equals("boss") && within && mario.enter
							&& mario.weapon) {
						worldtype = 3;

					}
					if (!mario.jumping && d.type == 2 && d.typeofportal.equals("boss") && within && mario.enter
							&& !mario.weapon) {
						warning1 = true;

						warningtime = 0;

					}
					if (warning1)
						warningtime++;
					if (warningtime > 300) {
						warningtime = 0;
						warning1 = false;
					}

					if (!mario.jumping && d.type == 2 && d.typeofportal.equals("one") && within && mario.enter) {
						worldtype = 1;

					}
					for (Moveable c : coins) {
						if (c.falling && d.type == 1 && c.y + c.h >= d.y && c.jumping) {
							c.y = d.y - c.h;
							c.falling = false;
							c.jumping = false;
						}
					}
					if (mario.falling && my + mario.height >= d.y && mario.jumping && (within)) {

						mario.y = d.y - mario.height;
						mario.falling = false;
						mario.jumping = false;
					}
					if (!mario.isOnPlatform(platforms)) {
						mario.falling = true;
						mario.fall = true;
						mario.jumping = true;
					} else {
						mario.jumping = false;
					}

				}

			}

			private void moveStuff() {
				// TODO Auto-generated method stub
				mario.move();
				for (Moveable m : coins) {
					m.move();
				}
				for (Moveable m : nonMarios) {
					m.move();
				}

			}

		});
		timer.start();
	}

	public void draw(Graphics g) {
		if (worldtype == 1)
			g.drawImage(this.bkgrnd, 0, 0, 1600, 820, null);
		else if (worldtype == 2 && !hover && !warning1) {
			g.drawImage(shop, 0, 0, 1400, 820, null);
		} else if (worldtype == 2 && hover) {

			g.drawImage(shop1, 0, 0, 1400, 820, null);

		} else if (worldtype == 2 && !hover && warning1) {
			g.drawImage(warning, 0, 0, 1400, 820, null);
		} else if (worldtype == 3) {
			g.drawImage(boss, 0, 0, 1500, 920, null);
			if (!gameover){
				
				g.drawString("here come dat boi", 720,50);
			}
			else {
				g.setFont(new Font("Arial",Font.PLAIN,60));
				g.drawString("oh shit waddup", 720,50);
			}
		}
		for (Moveable m : nonMarios) {
			m.draw(g);
		}
		for (Moveable m : coins) {
			m.draw(g);
		}
		for (Platform d : platforms) {
			d.draw(g);
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Dolce Vita", Font.PLAIN, 20));
		g.drawString("COINS: " + score + " ", 30, 30);
		g.drawImage(image, 100, 13, 20, 20, null);
		mario.draw(g);

	}

	public void right() {
		// TODO Auto-generated method stub

	}

	public void stop() {
		// TODO Auto-generated method stub

	}

	public void left() {
		// TODO Auto-generated method stub

	}

}
