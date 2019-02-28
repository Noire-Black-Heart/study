package com.billiard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class TFrame extends JFrame implements MouseListener,MouseMotionListener,Runnable{
	
	private Color[] clr = new Color[5];
	private double X=0,Y=0;/*鼠标当前坐标*/
	private String msact = null;
	private Ball mBall = new Ball(211,211,Color.WHITE);/*母球*/
	private double strength = 1;
	private Ball[] balls = new Ball[10];/*9球*/
	
	private BufferedImage gbl = null;
	private BufferedImage bi = null;
	private Graphics gbi = null;

	private Thread t;
	public TFrame(){
		this.setBounds(200,100,800,470);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		clr[0] = new Color(128,64,0);/*brown*/
		clr[1] = new Color(0,200,0);/*green*/
		init();
	}
	public void drawDesk(Graphics g){
		bi = (BufferedImage) createImage(800,550);
		gbi = bi.getGraphics();
		gbi.setColor(clr[0]);
		gbi.fillRoundRect(0, 30, 800, 430, 100, 100);/*look API*/	
		gbi.setColor(clr[1]);
		gbi.fillRect(45, 65, 710, 360);
		/*我的签名*/
		if(gbl!=null)
			gbi.drawImage(gbl,100,100, null);
		
		gbi.setColor(Color.WHITE);
		gbi.drawLine(222, 65, 222, 425);/*发球线*/
		/*draw 6 black hole*/
		gbi.setColor(Color.BLACK);
		gbi.fillOval(30, 50, 40, 40);
		gbi.fillOval(30, 400, 40, 40);
		gbi.fillOval(730, 50, 40, 40);
		gbi.fillOval(730, 400, 40, 40);
		gbi.fillOval(380, 40, 40, 40);
		gbi.fillOval(380, 410, 40, 40);
		/*draw star*/
		for(int i=45+88;i<=800-88;i+=88){
			gbi.fillOval(i, 47, 5, 5);
			gbi.fillOval(i, 442, 5, 5);
		}
		for(int i=65+90;i<=430-90;i+=90){
			gbi.fillOval(23, i, 5, 5);
			gbi.fillOval(773, i, 5, 5);
		}
		
	}
	public void paint(Graphics g){
		drawDesk(g);
		if(mBall.vsble){
			gbi.setColor(mBall.clr);
			gbi.fillOval((int)mBall.X, (int)mBall.Y, 24, 24);
		}
		for(int i=1;i<=9;i++){
			if(balls[i].vsble){
				gbi.setColor(balls[i].clr);
				gbi.fillOval((int)balls[i].X, (int)balls[i].Y, 24, 24);
				gbi.setColor(Color.WHITE);
				gbi.drawString(i+"", (int)balls[i].X+10, (int)balls[i].Y+15);
			}
		}
		/*瞄准*/
		gbi.fillOval((int)X-12,(int)Y-12,24,24);
		gbi.drawLine((int)mBall.X+12, (int)mBall.Y+12,(int)X,(int)Y);
		gbi.setColor(Color.BLACK);
		gbi.drawLine((int)X-5,(int)Y,(int)X+5,(int)Y);
		gbi.drawLine((int)X,(int)Y-5,(int)X,(int)Y+5);
		g.drawImage(bi,0,0, null);
	}
	public void init(){
		

		/*mBall = new Ball(211,211,Color.WHITE);母球*/
		balls[0] = mBall;/*引用*/
		balls[1] = new Ball(574,227,Color.YELLOW);
		balls[2] = new Ball(574+42,227+24,Color.BLUE);
		balls[3] = new Ball(574+83,227,Color.RED);
		balls[4] = new Ball(574+42,227-24,new Color(100,20,130));
		balls[5] = new Ball(574+21,227-12,Color.PINK);
		balls[6] = new Ball(574+21,227+12,Color.GREEN);
		balls[7] = new Ball(574+62,227+12,new Color(90,44,30));
		balls[8] = new Ball(574+62,227-12,Color.BLACK);
		balls[9] = new Ball(574+42,227,new Color(200,190,190));
//		File inputFile = new File("bin/gbl.png");
//	    try {
//			gbl = ImageIO.read(inputFile);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		balls[0] = mBall;/*引用*/
//		balls[1] = new Ball(50,227,Color.YELLOW);
//		balls[2] = new Ball(50,227+12,Color.BLUE);
//		balls[3] = new Ball(150+83,227,Color.RED);
//		balls[4] = new Ball(533+42,227-24,new Color(100,20,130));
//		balls[5] = new Ball(250+21,227-12,Color.PINK);
//		balls[6] = new Ball(100+21,127+12,Color.GREEN);
//		balls[7] = new Ball(474+62,327+12,new Color(90,44,30));
//		balls[8] = new Ball(574+62,627-12,Color.BLACK);
//		balls[9] = new Ball(274+42,427,new Color(200,190,190));
//		for(int i=3;i<=9;i++)
//			balls[i].vx=i*10;
//		balls[1].vx=0.1;
//		balls[3].vsble=false;
//		balls[9].vsble=false;
//		balls[7].vsble=false;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override /*按下*/
	public void mousePressed(MouseEvent e) {

		this.msact = "Pressed";
		this.X=e.getX();
		this.Y=e.getY();
	}

	@Override /*释放*/
	public void mouseReleased(MouseEvent e) {

		this.msact = null;
		double v = 10*strength;
		double yc = Y-mBall.Y-12;
		double xc = X-mBall.X-12;
		double k;
		k= (Y-mBall.Y-12)/(X-mBall.X-12);
		k=k>0?k:-k;
		if(mBall.vsble){
			mBall.vx = v/Math.sqrt(1+k*k);
			mBall.vy = k*v/Math.sqrt(1+k*k);
//			mBall.vx = v*xc/Math.hypot(xc, yc);
//			mBall.vy = v*yc/Math.hypot(xc, yc);
			if(xc<0)
				mBall.vx=-mBall.vx;
			if(yc<0)
				mBall.vy=-mBall.vy;
		}
		else{
			mBall.vsble=true;
			mBall.X=this.X-12;
			mBall.Y=this.Y-12;
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		//for(int i=0;i<=9;i++)
//			for(int j=0;j<=9;j++)
//			System.out.print(balls[0].crlst[j]);
//		System.out.println();
	}
	@Override/*移动*/
	public void mouseMoved(MouseEvent e) {

		if(msact==null){
		this.X=e.getX();
		this.Y=e.getY();
		repaint();
		}
	}
	public void moveBall(){
		for(int i=0;i<=9;i++)
			balls[i].lifeIsMove();
	}

	public void vslowdown(){
		for(int i=0;i<=9;i++)
			balls[i].vslow();
	}
	public void isinHole(){
		/*这个函数没好好写，意思意思就行了*/
		/*请看之前paint的注释，这是6个Black Hole*/
		/*什么是Black Hole?就是黑洞的意思*/
		/*什么是黑洞？黑洞你懂吗？
		 * 就是一靠近，就立刻被吸引*/
		/*这也是为了减少游戏难度*/
		for(int i=0;i<=9;i++){
			/*四角*/
			if((balls[i].X<=45+12||balls[i].X>=721-12) &&
					(balls[i].Y<=65+12||balls[i].Y>=401-12)){
				balls[i].vsble=false;
				balls[i].vx=balls[i].vy=0;
			}
			/*中间两洞*/
			if(balls[i].X<=420 && balls[i].X>=380 &&
					(balls[i].Y<=65+12||balls[i].Y>=401-12)){
				balls[i].vsble=false;
				balls[i].vx=balls[i].vy=0;
			}
		}
	}
	public void crashWall(){
		for(int i=0;i<=9;i++){
			if(balls[i].X-Math.abs(balls[i].vx)<=45 || balls[i].X+Math.abs(balls[i].vx)>=721){
				balls[i].overturnx();
			}
			if(balls[i].Y-Math.abs(balls[i].vy)<=65 || balls[i].Y+Math.abs(balls[i].vy)>=401){
				balls[i].overturny();
			}
		}
	}
	/*计算碰撞。。。
	 * 这里的判断写的我自己也不是很懂
	 * 喝了点酒，就写成这个样子了
	 * crlst存储的是相撞信息
	 * 但是在哪里初始化的呢，
	 * 和在哪里复原的呢
	 * 我已然忘记了
	 * 我试了试去掉crlst那行
	 * 还真不行*/
	public void crashBall(){
		for(int i=0;i<=9;i++){
			for(int j=0;j<=9;j++)
				if(i!=j && balls[i].vsble
						&& (Math.abs(balls[i].vx)+Math.abs(balls[i].vy))>0.01
						&& balls[i].isCrash(balls[j])
						&& balls[j].crlst[i]!=true){
					balls[i].crlst[j]=true;
					balls[i].mmtmove(balls[j]);
					balls[i].crashWith(balls[j]);	
				}
		}
		for(int i=0;i<=9;i++){
			for(int j=0;j<=9;j++)
				balls[i].crlst[j]=false;
		}
	}

	@Override
	public void run() {

		try {
			int iiii=0;
			while(true){
				iiii++;
				isinHole();
				crashWall();
				crashBall();
				
				moveBall();
				vslowdown();
				if(iiii==2){
					this.repaint();
					iiii=0;
				}
				Thread.sleep(10);
			}
		} catch (InterruptedException ee) {
			ee.printStackTrace();
		}
	}

}

