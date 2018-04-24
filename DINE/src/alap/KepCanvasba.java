package alap;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;

public class KepCanvasba {	
	
	public KepCanvasba(Canvas canvas, PaintEvent p, int sel, ArrayList<String> files) {
		
		
		//TODO a pngpath változó megszüntethetõ
				String pngkepPath;
								
				if(files.size()>0) {			
					pngkepPath = files.get(sel).replace("\\", "\\\\");
				} else {
					pngkepPath = "c:\\Users\\nemeth1davi520\\git\\DINE\\DINE\\src\\alap\\default-phone0.png";
				}
				
				Image pngkep = new Image(Display.getDefault(), pngkepPath);
				
	
		int iw = pngkep.getBounds().width;
		int ih = pngkep.getBounds().height;		
		int cw = canvas.getBounds().width;
		int ch = canvas.getBounds().height;
		int prevBoundW = 0;
		int prevBoundH = 0;	
		Point prevLoc = new Point(0, 0);
					
		//a canvasnál muszáj a +4px, mert a border miatt 2-2 pixelt levesz oldalról
		if (ih >= iw) {		//állókép
			float ratio = ih / (ch - 4);
			prevBoundH = ch - 4;
			prevBoundW = (int) (iw / ratio);
			prevLoc.x = ((cw - 4) - prevBoundW) / 2;
		} else {		//fekvõkép
			float ratio = iw / (cw - 4);
			prevBoundW = cw - 4;
			prevBoundH = (int) (ih / ratio);
			prevLoc.y = ((ch -4) - prevBoundH) / 2;					
		}
				
		p.gc.fillRectangle(0, 0, cw, ch);	
		
		p.gc.setAntialias(SWT.ON);
		p.gc.drawImage(pngkep, 0, 0, iw, ih, prevLoc.x, prevLoc.y, prevBoundW, prevBoundH);

		
		System.out.println("KepCanvasba lefutott");
	}
	
}
