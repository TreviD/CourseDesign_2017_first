
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Hannoi_block {
	
	
	
	
	private int x;
	private int y;
	private int width;
	private int height;
//	private BufferedImage image;
	private int id;
	private int local;					//看位于哪一根柱子 1 ，2 ，3   三根柱子横坐标分别为 100 300 500
	
	
	public Hannoi_block(int id) {
		this.id=id;
	}
	
//	public Hannoi_block(int x,int y,int width,int heigth) {
//		this.x = x;
//		this.y = y;
//		this.width = width;
//		this.height = heigth;
//		
//	}
//	public Hannoi_block(BufferedImage image) {
////		this.image=image;
//	}

	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
//	public void makeBlock(int a) {
//		
////		Hannoi_block block = new Hannoi_block();
//		
//		switch(a) {
//		case 1:
//			this.setWidth(20);
//			break;
//		case 2:
//			this.setWidth(40);
//			break;
//		case 3:
//			this.setWidth(60);
//			break;
//		case 4:
//			this.setWidth(80);
//			break;
//		case 5:
//			this.setWidth(100);
//			break;
//		case 6:
//			this.setWidth(120);
//			break;
//			
//		default:
//			break;
//		}
//		this.setHeight(50);
//		
//		
//		
//	
//		
//	}
	
	
	
	
	public void setLocal(int x,int y) {
		this.x = x;
		this.y = y;
		
	}
	
	public void paint(Graphics g) {								//画方块
//		g.drawImage(image,x,y,null);
		g.drawLine(x, y, x+20*id, y);
		g.drawLine(x, y+30, x+20*id, y+30);
		
		g.drawLine(x+20*id, y, x+20*id, y+30);
		g.drawLine(x, y, x, y+30);
		
	}
	
	
	
	
	
	
	
//	public void setLocal(int local) {
//		this.local=local;
//	}
	public int getLocal() {
		return local;
	}
	
	
	
	
	//判断是否可以落下
			public Boolean canDown(int a,Hannoi_block[] blocks,int x) {
				
				//a表示第几个方块 x表示将要落到第几根柱子
				
				
				int nums = Hannoi_Frame.nums;			//Hannoi_Frame.nums 表示一共有几个方块
				int local_x=0;				//表示柱子的x坐标
				
//				for(int i=nums;i>=1;i--) {
//					if( (blocks[a].getX()+a*10) == (blocks[i].getX()+i*10) && blocks[a].getWidth()<blocks[i].getWidth()) {
//						x--;
//					}
//					if(blocks[a].getX()+a*10 != blocks[i].getX()+i*10)
//						y--;
//				
//				
//				
//				}
				
				switch (x){
				case 1:
					local_x=100;
					break;
				case 2:
					local_x=300;
					break;
				case 3:
					local_x=500;
					break;
				}
				
				
				int temp=9999;
				
				
				//首先找出所有位于local_x 的方块   并找出其中最顶层的。。。。
				for(int i=nums;i>=1;i--) {
					
					if(blocks[i].getX()+i*10 >= local_x-100 && blocks[i].getX()+i*10<local_x+100) {
						
						if(temp>blocks[i].getWidth())				//找出最顶层 （宽最小的）
							temp=blocks[i].getWidth();				//如果temp比方块的宽 大 那么就把 方块的宽赋值给temp
																	//也就是找到最小的 也就是最上层的方块
					
					
					
					}
					
					
				}
				System.out.println("temp  "+temp+"  "+blocks[a].getWidth());
				
//				if(temp==9999||temp>blocks[a].getWidth())				//当 temp 等于 9999 表示 local_x这跟柱子没有其他方块 则可以放
//					return true;												//当temp不等于9999但temp大于 blocks[a]的宽时表示 此时 local_x 这根柱子的顶层比a宽 可以放
//				else					
//					return false;
				if(temp==blocks[a].getWidth()) {						//当最上层 就是 要操作的方块时候 那就可以放  因为移动到了这一根柱子的区域  所以 这个方块也算在这跟柱子上
					System.out.println("可以放下");
					return true;
				}else {
					System.out.println("不能放下");
					return false;
				}
				
				
			}
			
			
			
			
			//判断是否可以抬起
			public Boolean canBlockUp(int a , Hannoi_block[] blocks , int x) {
				int nums = Hannoi_Frame.nums;			//Hannoi_Frame.nums 表示一共有几个方块
				int local_x=0;				//表示柱子的x坐标
				
				switch (x){
				case 1:
					local_x=100;
					break;
				case 2:
					local_x=300;
					break;
				case 3:
					local_x=500;
					break;
				}
				
				int temp=9999;
					
				for(int i=nums;i>=1;i--) {							//判断抬起 也类似
					
					if(blocks[i].getX()+i*10 == local_x) {
						
						if(blocks[i].getWidth()<temp)
							temp=blocks[i].getWidth();
					
					}
					
					
				}
				
				
				
				if(temp==blocks[a].getWidth())	{			//如果最上层就是 要操作的方块 那么就可以抬起
					System.out.println("可以抬起");
					return true;
				}
				else {
					System.out.println("不可以抬起");

					return false;
				}
				
				
				
//				return null;
			}
			
		
	//判断是否获胜  		获胜条件 所有方块在 B 或者 C上
			public static Boolean isWin() {
				Hannoi_block[] blocks=Hannoi_Panel.blocks;
				int nums = Hannoi_Frame.nums;
				int x=nums,y=nums;
				for(int i=nums;i>=1;i--) {
					if(blocks[i].getX()+i*10 == 300)
						x--;
					if(blocks[i].getX()+i*10==500)
						y--;
					
				}
				
				if(x==0||y==0)
					return true;
				else
					return false;
			}
			
			
			
	//放下方块
			public void putDown(int a,int px,int py) {
				int nums=Hannoi_Frame.nums;
				int num=0;							//用来计数 看原来这根柱子上有几个方块
				Hannoi_block [] blocks = Hannoi_Panel.blocks;			//得到所有方块 为了方便下面调用

				if(px>=0 && px<=200) {
					
					for(int i=nums;i>=1;i--) {
						
						if(blocks[i].getX()+10*i==100 && blocks[i].getY()==(470-num*30))		//计数 看有几个方块在这跟柱子上（这是第一根柱子的判断 ）  下同
							num++;
					}
					blocks[a].setLocal(100-10*a, 470-(num)*30);						//设定 带操作方块的 坐标 x坐标 固定 受方块自身影响  y坐标受方块数量影响
				}else if(px>200 && px<=400) {							// 第二根柱子
					for(int i=nums;i>=1;i--) {
						if(blocks[i].getX()+10*i==300 && blocks[i].getY()==(470-num*30))
							num++;
					}
					blocks[a].setLocal(300-a*10, 470-(num)*30);
				}
				else if(px>400 && px<=600) {
					for(int i=nums;i>=1;i--) {
						if(blocks[i].getX()+10*i==500 && blocks[i].getY()==(470-num*30))
							num++;
					}
					
					blocks[a].setLocal(500-a*10, 470-(num)*30);
				}
				System.out.println("........"+px+"   "+py);
				System.out.println(a+"   "+blocks[a].getX()+"   "+blocks[a].getY());
					
					
					
			}
			
			
			
			
			
			//找出某一根柱子最上层  // 这个方法没用到～～～～～～～～ 可以删掉 
			public int findHighestBlock(int a,int x) {
				int local_x=0;
				
				switch (x){
				case 1:
					local_x=100;
					break;
				case 2:
					local_x=300;
					break;
				case 3:
					local_x=500;
					break;
				}
				
				
				int temp=9999;
				int nums=Hannoi_Frame.nums;
				Hannoi_block [] blocks = Hannoi_Panel.blocks;
				
				for(int i=nums;i>=1;i--) {
					if(i==a)
						continue;
					if(blocks[i].getX()+i*10 == local_x) {
						
						if(temp>blocks[i].getWidth())				//找出最顶层 （宽最小的）
							temp=blocks[i].getWidth();
					
					
					}
					
					
				}
				
				
				
				return temp*20;
			}
			
	
	
}
