package alap;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;

public class Ikon {
	private int x, y = 0;	//kezdõpoz
	private int nr;			//hányadik ikon
	private int size;		//melyik méret
	//private int[] picSize = {800, 400, 200};
	
		
	private Display display = Display.getCurrent();
	
	private Image lte_200 = new Image(display, getClass().getResourceAsStream("4g_200.png"));
	private Image lte_400 = new Image(display, getClass().getResourceAsStream("4g_400.png"));
	private Image lte_800 = new Image(display, getClass().getResourceAsStream("4g_800.png"));
	
	private Image ltp_200 = new Image(display, getClass().getResourceAsStream("4gplus_200.png"));
	private Image ltp_400 = new Image(display, getClass().getResourceAsStream("4gplus_400.png"));
	private Image ltp_800 = new Image(display, getClass().getResourceAsStream("4gplus_800.png"));

	private Image vol_200 = new Image(display, getClass().getResourceAsStream("4ghang_200.png"));
	private Image vol_400 = new Image(display, getClass().getResourceAsStream("4ghang_400.png"));
	private Image vol_800 = new Image(display, getClass().getResourceAsStream("4ghang_800.png"));

	private Image hel_200 = new Image(display, getClass().getResourceAsStream("helloholnap_200.png"));
	private Image hel_400 = new Image(display, getClass().getResourceAsStream("helloholnap_400.png"));
	private Image hel_800 = new Image(display, getClass().getResourceAsStream("helloholnap_800.png"));

	private Image sim_200 = new Image(display, getClass().getResourceAsStream("simunlock_200.png"));
	private Image sim_400 = new Image(display, getClass().getResourceAsStream("simunlock_400.png"));
	private Image sim_800 = new Image(display, getClass().getResourceAsStream("simunlock_800.png"));

	private Image rcs_200 = new Image(display, getClass().getResourceAsStream("rcs_200.png"));
	private Image rcs_400 = new Image(display, getClass().getResourceAsStream("rcs_400.png"));
	private Image rcs_800 = new Image(display, getClass().getResourceAsStream("rcs_800.png"));

	private Image hua_200 = new Image(display, getClass().getResourceAsStream("huacert_200.png"));
	private Image hua_400 = new Image(display, getClass().getResourceAsStream("huacert_400.png"));
	private Image hua_800 = new Image(display, getClass().getResourceAsStream("huacert_800.png"));

	
	private Image[][] ikonArray = new Image[][]{
				{lte_200, lte_400, lte_800},
				{ltp_200, ltp_400, ltp_800},
				{vol_200, vol_400, vol_800},
				{hel_200, hel_400, hel_800},
				{sim_200, sim_400, sim_800},
				{rcs_200, rcs_400, rcs_800},
				{hua_200, hua_400, hua_800}
			};
	
	
	public Image i200, i400, i800;
	
	//lte, ltp, vlt, hel, sim, rcs, hua
	public Ikon(Canvas canvas, PaintEvent e, int nr, int size, int z) {	
	
		this.nr = nr;
		this.size = size;

		switch (nr) {
			case 1: y=14; break;
			case 2: y=78; break;
			case 3: y=142; break;
			case 4: y=206; break;
			case 5: y=270; break;
			case 6: y=334; break;
			default: y=14;
			break;
		}
				
		i200 = ikonArray[z][0]; //TODO
		i400 = ikonArray[z][1];
		i800 = ikonArray[z][2];
		
				
		//a huawei logo szélesebb, kicsit balra kell csúsztatni
		x = z==6 ? 324 : 329;
		//y = z==6 ? y-5 : y;		
		

		// TODO Auto-generated method stub
		System.out.println("Ikon() \n");

		e.gc.drawImage(
				i400,
				0, 0, i400.getBounds().width, i400.getBounds().height, 
				x, y, i400.getBounds().width, i400.getBounds().height
		);		
					
	}
	
		
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	public int getNr() {
		return nr;
	}

	public int getSize() {
		return size;
	}

	
	
	
}
