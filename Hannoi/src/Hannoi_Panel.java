import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Hannoi_Panel extends JPanel{
	
	
	
	
	/*定义 一个方块数组 有7个方块   一共用到6个 定7是为了方便  方块编号从1号到6号 
	 * blocks[0]是没用到的
	 * 
	 */
	
		static Hannoi_block blocks[]=new Hannoi_block[7] ;
	
	
		public Hannoi_Panel(){
//			createBlocks(Hannoi_Frame.nums);

		}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		//画线===============start
//		g.drawLine(0, 1, 600, 1);
		g.drawLine(0, 0, 600, 0);
		
		g.drawLine(0, 0, 0, 500);
		
		g.drawLine(0, 500, 600, 500);
		g.drawLine(0, 501, 600, 501);
		
		
		g.drawLine(600,0,600,500);
		g.drawLine(601,0,601,500);
		// 画 游戏区域那个线================
		
		
		
		//  柱子=======================   对应横坐标分别是  100  300 500   
		// 一号柱子的区域 为 0到200  二号为 200到400    三号 为 400到600 
		// 也就是当 方块拖到这一片区域内 放下   就会自动落到 应该的正确的位置
		g.drawLine(100, 500, 100, 100);
		g.drawLine(300, 500, 300, 100);
		g.drawLine(500, 500, 500, 100);
		 	
		if(Hannoi_Frame.state==1) {				//当state = 1 时重新创建方块
			createBlocks(Hannoi_Frame.nums); 		//创建方块
			Hannoi_Frame.state=0;
		}
		for(int i=Hannoi_Frame.nums;i>=1;i--) {
			blocks[i].paint(g);					//绘制方块
		}
		
		if(Hannoi_block.isWin()) {
			
			g.drawString("you are win", 250, 70);			//判读是否获胜
		}
		
	}
	
	
	
	public void action() {
		this.requestFocus();					//获得焦点
		
		
		Thread thread = new Thread() {				//新建一个线程   将绘图放到该线程 里
		@Override
		public void run() {
			while(true) {
				
				repaint();
//				System.out.println("重新绘制");
				try {
					sleep(40);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		};
		thread.start();
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	//创建方块
	public void createBlocks(int a) {
		int temp = a;
		
		while(a>=1) {
			System.out.println("init Block"+a);
			blocks[a]=new Hannoi_block(a);
			blocks[a].setLocal(100-a*10, 470-(temp-a)*30);
			blocks[a].setHeight(30);
			blocks[a].setWidth(a*20);
			a--;
		}
		
	}
	
	
//	public Hannoi_block getBlocks(int a) {
//		
//		return blocks[a];
//	}
//	public void setBlocks(int a) {
//		
//	}

	
	
	

	
	
	
	
	
	
	
	
	
}
