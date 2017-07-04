import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Hannoi_Frame extends JFrame{

	
	
	Hannoi_Panel HannoiPanel;
	static int state=1;						//设定游戏状态，state=1 时 HannoiPanel调用createBlock 创建方块 默认为1 修改后为1
	static int nums=3;					//游戏方块数量，静态 类型 全局调用
	static int a,px,py,initPx,initPy,initX;			// a 表示当前操作的是哪一个方块   px py 表示鼠标指针坐标，用于拖动方块  initPx initPy 定义初始位置的坐标 便于无法放下时还原
												//initX 用于演示时候定位 三根柱子中的一根 223行
	static Hannoi_block[] blocks=Hannoi_Panel.blocks;		//定义一个方块数组，等于 面板中创建的方块数组 便于在 Frame（这个类）中调用
	
	static JTextArea tf = new JTextArea();			//定义一个文本区域
	
	public Hannoi_Frame() {
		HannoiPanel =new Hannoi_Panel();						//初始化 一个面板
	}
	
	
	
	public void showMe() {
		
		
		
//		JTextArea abc = new JTextArea("nihao");
//		JButton abc = new JButton("asdgsadf");
//		abc.setLayout(null);
//		abc.setBounds(650,650,100,100);
		
		
		
		JButton[] jbtn = new JButton[4];						//	按钮
		jbtn[0]=new JButton("3");							//new JButton("按钮上面文字")
		jbtn[1]=new JButton("4");
		jbtn[2]=new JButton("5");
		jbtn[3]=new JButton("6");
		JButton demo_btn = new JButton("演示");
		
		demo_btn.setLayout(null);										//设定布局样式为 null   不用默认的布局 自己定义
		demo_btn.setSize(100,50);										//setSize(int width, int heigth)  设定长宽 （大小）
		demo_btn.setLocation(650, 320);							//设定坐标
		
		for(int i=0;i<4;i++) {
//			jbtn[i]=new JButton(String.valueOf(i+3));
			jbtn[i].setSize(100, 50);
			jbtn[i].setLayout(null);
			jbtn[i].setLocation(650, 100+50*i);
			
			
		}

//		jbtn[0].setLocation(650, 100);
//		jbtn[1].setLocation(650, 150);
//		jbtn[2].setLocation(650, 200);
//		jbtn[3].setLocation(650, 250);
		
		HannoiPanel.setLayout(null);
		HannoiPanel.setSize(610,510);
		this.setLayout(null);							//this 表示Hannoi_Frame
		
		tf.setLayout(null);
		tf.setSize(600,50);
		tf.setLocation(0,510);
//		tf.setText("123");
		
		
		
		this.setTitle("HannoiTower v0.1");			
		this.setSize(800,600);
		this.setLocation(300,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				//设定默认 点击关闭按钮 的操作， 这个是点击关闭 就终止
		this.add(HannoiPanel);										//讲之前创建的面板 添加到Hannoi_Frame 中来
		
		this.add(tf);												//添加
		
		
		

//按钮 事件监听 ============================== start ====================================
		
		jbtn[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nums=3;						//按钮事件监听 
				state=1;					//修改后 state为1   用于HannoiPanel 判断 调用createBlock方法
				
			}
			
		});
		jbtn[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nums=4;
				state=1;
			}
			
		});
		
		jbtn[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nums=5;
				state=1;
				
			}
			
		});
		jbtn[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nums=6;
				state=1;
			}
			
		});
		
		
		demo_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread t = new Thread() {							//新建一个线程 将演示的操作放在 新的线程中来操作   否则 会出现画面停止刷新 只操作方块（表现出来的结果 ，就是 点击演示 后，画面不刷新，画面不动，等演示函数 执行完成后 再刷新，所有方块 全部跳到 第三根柱子）
//				Hannoi_Frame.demoAction();
					
					@Override
					public void run() {
						Hannoi_Frame.demoAction();
					}
				};
				t.start();					//启动线程
				
			}
			
		});
	//按钮事件监听============================ end ====================================
		
		this.add(jbtn[0]);
		this.add(jbtn[1]);
		this.add(jbtn[2]);
		this.add(jbtn[3]);
		this.add(demo_btn);
		
//		this.add(abc);
		
		
		HannoiPanel.action();
		this.addMouseListener(new MyMouseList());					//鼠标 点击事件 监听
		this.addMouseMotionListener(new MyMouseMotionList());		//鼠标 拖拽 等事件监听
		this.setVisible(true);							//设定 可见
	}
	
	
	
	static class MyMouseList extends MouseAdapter{					//定义一个监听器
		
		
		// 鼠标点击事件
		public void mousePressed(MouseEvent e) {
			px=e.getX();										//得到 鼠标点击时候 x 轴坐标
			initPx=e.getX();						//初始值
			initPy=e.getY();
			py=e.getY();						//y轴坐标
			
			
			//========= 判断点击的坐标 位于哪一根柱子的区域内 ============
			int x=0;
			if(px>=100&&px<=200)
				x=1;										
			else if(px>200 && px<=400)
				x=2;
			else
				x=3;
			// ==========================================
			
			int i;
			for( i=1;i<=nums;i++) {
				if(px >= blocks[i].getX() && px <= blocks[i].getX()+20*i
						&& py >=blocks[i].getY()+25 && py <= blocks[i].getY()+30+25)
				{
					
					
					a=i;
					break;
				}
			}						//根据 鼠标点击坐标 和方块的坐标 找出点击的是哪一个 方块   （根据实际 有25像素的偏移 所以y坐标都加25）
			
			
			
			if(i==nums+1) {					//i == nums + 1 表示上面的循环 全部进行了一遍 也就是都不满足 即 鼠标没点到方块
				a=0;
			}
			if(!blocks[a].canBlockUp(a, blocks, x)) {			//判断方块是否可以抬起   上面是否有更小的方块  如果有 也给 a赋值为0
				a=0;
			}
			
			
			//输出 没啥意义========== 
			System.out.println("被点击"+px +"   "+py+"  "+a);
			if(a!=0)
				System.out.println(blocks[a].getX()+"  "+blocks[a].getY());
			
			//===================
		}
		
		
		
		//鼠标释放 事件
		public void mouseReleased(MouseEvent e) {
			px=e.getX();
			py=e.getY();	
			
			int x=0;								//判断释放时候鼠标坐标
			if(px>=0&&px<=200)
				x=1;
			else if(px>200 && px<=400)
				x=2;
			else
				x=3;
			
														//判断初始时候在哪一根柱子
			if(initPx>=100&&initPx<=200)
				initX=1;
			else if(initPx>200 && initPx<=400)
				initX=2;
			else
				initX=3;
			
			
			
															//判断是否能够放下 如果能 就调用 Hannoi_block 里面的 putDown(方块编号 ， 方块当前x坐标 ， 当前 y坐标) 函数 
			if(blocks[a].canDown(a, blocks, x))
				blocks[a].putDown(a, px, py);
			else
				blocks[a].putDown(a, initPx, initPy);			//不能放下 则放回原位
			
			
			
			
//			if(Hannoi_block.isWin()) {
//				
//			}
		}
	}
	
	
	
	static class MyMouseMotionList extends MouseMotionAdapter {
	
		//鼠标拖拽 事件监听
		public void mouseDragged(MouseEvent e) {
			
			int x=0;
			if(px>=100 && px<=200)
				x=1;
			else if(px>200 && px<=400)
				x=2;
			else
				x=3;
			
			
			//当 a不等于 0 时候 就设定 blocks[a]的位置
			if(a!=0 ) {
				blocks[a].setLocal(e.getX(), e.getY()-25);
				
			}
			
			
		}
		
//		public void mouseMoved(MouseEvent e) {
////			int x=0;
////			if(px>=100&&px<=200)
////				x=1;
////			else if(px>200 && px<=400)
////				x=2;
////			else
////				x=3;
////			
////			if(a!=0 ) {
////				blocks[a].setLocal(e.getX(), e.getY()-25);
////				
////			}
//		}
		
	}
	
	
	
	//演示函数 =====================
	public static void demoAction() {
		
		state=1;						//演示前  将 所有方块 放回初始 位置
		try {
			Thread.sleep(1000);			//线程 休眠 1000ms  	为了让上面state=1 后 画面有时间刷新 不然 因为画面刷新 和这个演示操作是两个不同的线程 所以可能会导致 上面画面还没刷新 演示操作就接着操作
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Hannoi_Frame.Hannoi(nums,'A','B','C');			//Hannoi函数 
		tf.setText("");								
		Thread.interrupted();						//执行完 后 终止线程		
		
		
	}
	
	
	// 演示 汉诺塔     ============================= 操作 ===========================
	
//	static int i;
	public static int move(char x, int n, char y) {

//		Timer time = new Timer();
		
		int _x=0,_y=0;

		if(y=='A') {									//这边的 y 是指 移动到某一根柱子  根据 y的值 赋给 _x 坐标
			_x=100;					
		}else if(y=='B') {
			_x=300;
		}else
			_x=500;
		
		System.out.println("move " + n + " from " + x + " to " + y);
		
		blocks[n].putDown(n,_x,400);				// 因为putDown 函数 主要和 x坐标 有关是根据x坐标判断放在哪， 所以 y坐标随便写  
		
		tf.setText("move " + n + " from " + x + " to " + y);				//设定 文本区域 显示
		
		try {
			Thread.sleep(1000);				//线程休眠1000ms  不然太快了
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static int Hannoi(int n, char a, char b, char c) {

		if (n == 1) {
			move(a, 1, c);
		}

		else {
			Hannoi(n - 1, a, c, b);							//汉诺塔操作  递归   先将 上面 n-1个方块 放到柱子b
			move(a, n, c);									// 将最底部 方块 放到 柱子c
			Hannoi(n - 1, b, a, c);							// 把 n-1 从 b 移动到 c
		}
//		i++;											
		return 0;
	}
	
	
	
	// 汉诺塔 操作================================== end =========================

	
	
	
	
	public static void main(String[] args) {
		
		Hannoi_Frame window = new Hannoi_Frame();
		window.showMe();
	
	}
	
}
