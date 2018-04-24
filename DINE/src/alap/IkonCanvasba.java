package alap;

import java.util.ArrayList;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;

public class IkonCanvasba { //TODO		
	boolean[] ikon = new boolean[7];
	ArrayList<Ikon> ikonok = new ArrayList<Ikon>();		//Ikon elemeket tartalmaz
	
	
	public IkonCanvasba(Canvas canvas, PaintEvent p, ArrayList<Button> buttons) {		
				
				System.out.println("ikonCanvasba()");
				
				for(int i=0; i<ikon.length; i++) {
					ikon[i] = false;
				}
				
				
				for(int b=0; b < buttons.size(); b++) {
					if(buttons.get(b).getSelection()) {
						ikon[b] = true;					
					}
				}
				
				ikonok.clear();						 			
				
				for(int z = 0; z < ikon.length; z++) {
					if (ikon[z]) {
						//ikonok.add(new Ikon(e, ikonok.size()+1, 2, z));
						ikonok.add(new Ikon(canvas, p, ikonok.size()+1, 2, z));
					}
				}
				
	}//ikonCanvasba end
	
}	