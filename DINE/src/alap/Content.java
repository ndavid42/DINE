package alap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Content {
	
	Canvas canvas;	
	Text txtJazzInput;
	Text faljListaOutput;
	Text txtJazzFilenameOutput;
	
	ArrayList<Button> buttons = new ArrayList<Button>(); //a gombokat tartalmazza, amik állapota lekérhetõ
	ArrayList<String> files = new ArrayList<String>();	//a megnyitott képek path-ját tartalmazza
		
	int sel;
	String paths = null;
	public Image img = null;
	
	public void filesInit() {
		files.clear();
		files.add("c:\\Users\\nemeth1davi520\\eclipse-workspace\\Kep2\\src\\default-phone0.png");
		sel = 0;
	}
	
	
	
	public void run (Shell shell) {		
		
		if (img == null) {					
			img = new Image(Display.getDefault(), "c:\\Users\\nemeth1davi520\\eclipse-workspace\\Kep2\\src\\default-phone0.png");			  		
		}
		
		
		//cikkszám vezérlõk
				Text txtJazzInput = new Text(shell, SWT.BORDER | SWT.MULTI);
				txtJazzInput.setToolTipText("Jazz");
				txtJazzInput.setBounds(10, 64, 258, 36);
				
				Text txtJazzFilenameOutput = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
				txtJazzFilenameOutput.setToolTipText("Filename");
				txtJazzFilenameOutput.setBounds(10, 127, 258, 36);
				
				
				Label labelJazz = new Label(shell, SWT.NONE);
				labelJazz.setBounds(10, 43, 55, 15);
				labelJazz.setText("Jazz");
				
				Label labelJazzFilename = new Label(shell, SWT.NONE);
				labelJazzFilename.setText("Filename");
				labelJazzFilename.setBounds(10, 106, 55, 15);
				
				
				Button btnJazzPaste = new Button(shell, SWT.NONE);
				btnJazzPaste.setToolTipText("Paste from clipboard");
				btnJazzPaste.setBounds(69, 12, 62, 25);
				btnJazzPaste.setText("Paste");
				
				Button btnJazzDel = new Button(shell, SWT.NONE);
				btnJazzDel.setToolTipText("Delete the input field");
				btnJazzDel.setBounds(137, 12, 62, 25);
				btnJazzDel.setText("Del");
				
			
			//badgeGroup vezérlõk
				Group badgeGroup = new Group(shell, SWT.NONE);
				badgeGroup.setBounds(699, 11, 106, 175);
				
				Label lblVagy = new Label(badgeGroup, SWT.NONE);
				lblVagy.setBounds(67, 25, 32, 15);
				lblVagy.setText("OR");
				
				
				Label lblNewLabel = new Label(badgeGroup, SWT.CENTER);
				lblNewLabel.setBounds(57, 19, 12, 30);
				lblNewLabel.setAlignment(SWT.LEFT);
				lblNewLabel.setText("\\\r\n/");
				
				Button btn4G = new Button(badgeGroup, SWT.CHECK);
				btn4G.setBounds(10, 15, 93, 16);
				btn4G.setText("4G");
				buttons.add(btn4G);
				
				Button btn4Gplus = new Button(badgeGroup, SWT.CHECK);
				btn4Gplus.setBounds(10, 37, 93, 16);
				btn4Gplus.setText("4G+");
				buttons.add(btn4Gplus);

				Button btn4Ghang = new Button(badgeGroup, SWT.CHECK);
				btn4Ghang.setBounds(10, 59, 93, 16);
				btn4Ghang.setText("4G Hang");
				buttons.add(btn4Ghang);
				
				Button btnHelloHolnap = new Button(badgeGroup, SWT.CHECK);
				btnHelloHolnap.setBounds(10, 81, 93, 16);
				btnHelloHolnap.setText("hello holnap!");
				buttons.add(btnHelloHolnap);
						
				Button btnSimUnlock = new Button(badgeGroup, SWT.CHECK);
				btnSimUnlock.setBounds(10, 103, 93, 16);
				btnSimUnlock.setText("SIM unlock");
				buttons.add(btnSimUnlock);
						
				Button btnRcs = new Button(badgeGroup, SWT.CHECK);
				btnRcs.setBounds(10, 125, 93, 16);
				btnRcs.setText("RCS");
				buttons.add(btnRcs);
						
				Button btnHuaweiLogo = new Button(badgeGroup, SWT.CHECK);
				btnHuaweiLogo.setBounds(10, 149, 93, 16);
				btnHuaweiLogo.setText("Huawei logo");
				badgeGroup.setTabList(new Control[]{btn4G, btn4Gplus, btn4Ghang, btnHelloHolnap, btnSimUnlock, btnRcs, btnHuaweiLogo});
				buttons.add(btnHuaweiLogo);
				
				
			//egyéb beállítás vezérlõk
				Button btnSharpen = new Button(shell, SWT.CHECK);
				btnSharpen.setToolTipText("Sharpen the images");
				btnSharpen.setBounds(709, 192, 93, 16);
				btnSharpen.setSelection(true);
				btnSharpen.setText("Sharpen");
				
				Button btnPngquant = new Button(shell, SWT.CHECK);
				btnPngquant.setToolTipText("Optimize the images' size");
				btnPngquant.setBounds(709, 214, 93, 16);
				btnPngquant.setSelection(true);
				btnPngquant.setText("PNGquant");
				
				
			//kimenet vezérlõk
				Button btnReszpoZip = new Button(shell, SWT.CHECK);
				btnReszpoZip.setBounds(709, 242, 93, 16);
				btnReszpoZip.setText("respo ZIP");
				
				Button btnAtgZip = new Button(shell, SWT.CHECK);
				btnAtgZip.setBounds(709, 264, 93, 16);
				btnAtgZip.setText("ATG ZIP");
				
			
				canvas = new Canvas(shell, SWT.BORDER);
				canvas.setBounds(282, 12, 404, 404);

				
			//egyéb vezérlõ
				Button btnMehet = new Button(shell, SWT.NONE);
				btnMehet.setBounds(709, 391, 75, 25);
				btnMehet.setText("Go!");
						
				
				Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
				label.setBounds(10, 175, 258, 2);
				
				Composite compImagelist = new Composite(shell, SWT.NONE);
				compImagelist.setBounds(10, 182, 258, 236);
					
					Button btnUp = new Button(compImagelist, SWT.BORDER | SWT.FLAT | SWT.ARROW );
					btnUp.setBounds(192, 211, 30, 25);
					btnUp.setToolTipText("Move image up");
					btnUp.setText("fel");
					
					Button btnDown = new Button(compImagelist, SWT.BORDER | SWT.FLAT | SWT.ARROW | SWT.DOWN );
					btnDown.setBounds(228, 211, 30, 25);
					btnDown.setToolTipText("Move image down");
					btnDown.setText("le");
					
					Button btnAdd = new Button(compImagelist, SWT.NONE);
					btnAdd.setBounds(0, 211, 55, 25);
					btnAdd.setToolTipText("Open/add new image to the list");
					btnAdd.setText("Add");
					
				Button btnRemove = new Button(compImagelist, SWT.NONE);
				btnRemove.setBounds(61, 211, 55, 25);
				btnRemove.setToolTipText("Remove image from the list");
				btnRemove.setText("Remove");
				
				Button btnClear = new Button(compImagelist, SWT.NONE);
				btnClear.setBounds(122, 211, 55, 25);
				btnClear.setToolTipText("Clear the list");
				btnClear.setText("Clear");
				
					List imageList = new List(compImagelist, SWT.BORDER);
					imageList.setBounds(0, 25, 258, 180);
					
					Label lblImages = new Label(compImagelist, SWT.NONE);
					lblImages.setBounds(0, 4, 55, 15);
					lblImages.setText("Images");
					compImagelist.setTabList(new Control[]{imageList, btnAdd, btnRemove, btnClear, btnUp, btnDown});
				
				shell.setTabList(new Control[]{btnJazzPaste, btnJazzDel, txtJazzInput, compImagelist, badgeGroup, btnSharpen, btnPngquant, btnReszpoZip, btnAtgZip, btnMehet});
					
				
				
				//		---		---		---		---		---		---		---
				
				
				canvas.addPaintListener(new PaintListener() {
					@Override
					public void paintControl(PaintEvent p) {
						new KepCanvasba(canvas, p, sel, files);
						
						//csak akkor rakja rá az ikonokat, ha az elsõ képrõl van szó és van egyáltalán kép (a fedault képre nem rakja rá)
						if(sel == 0 && files.size() > 0) {
							new IkonCanvasba(canvas, p, buttons);
						}
						
						
						System.out.println("canvas paintlistener");
					}	
				});
				
				btnClear.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
					
						imageList.removeAll();						
						files.clear();
						sel = 0;						 
						canvas.redraw();
					}
				});
				
				btnRemove.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {	
						if(imageList.getItemCount() > 0) {
							sel = imageList.getSelectionIndex();
							files.remove(imageList.getSelectionIndex());
							imageList.remove(imageList.getSelectionIndex());				
							
							if(imageList.getItemCount() == 0) {								
								imageList.deselectAll();
								
							} else {
								if (sel == imageList.getItemCount()-1) {
									System.out.println("if " + sel);	
									imageList.setSelection(sel);
								} else {
									System.out.println("else " + sel);	
									if (sel > 0) {
										sel--;
									}
									
									imageList.setSelection(sel);
								}
							}
								
							
							canvas.redraw();
						}
					}

				
				});			
					
				btnAdd.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {								
						
					        FileDialog dlg = new FileDialog(shell, SWT.MULTI);
					        dlg.setText("Open image file");
					        dlg.setFilterPath("C:/");
					        String[] filterExt = { "*.png", "*.jpg", "*.*" };
					        dlg.setFilterExtensions(filterExt);
					       
					        if (dlg.open() != null) {
					          String[] names = dlg.getFileNames();
					          
					          for (int i = 0, n = names.length; i < n; i++) {
					            StringBuffer buf = new StringBuffer(dlg.getFilterPath());
					            if (buf.charAt(buf.length() - 1) != File.separatorChar)
					              buf.append(File.separatorChar);
					            buf.append(names[i]);
					            files.add(buf.toString());
					            System.out.println("hossz: " + files.size());		            
					         			    		
					            imageList.add(names[i]);			    		
					          }
					        }
					        
					        if (files.size() > 0) {
					        	 System.out.println("files" + files);
					        	 paths = dlg.getFilterPath() + "\\" + imageList.getItem(0);			        
					        	 System.out.println("paths: " + paths);  
					        }
					       
					        
					        
					        imageList.setSelection(0);
					        sel = 0;
					        canvas.redraw();
					}
				});
					
					
					
						
			//képlista vezérlõ LISTENER				
					btnDown.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							
							if(files.size() > 0) {
								String temp = files.get(sel);				
								for (int a = 0; a < files.size()-1; a++) {
									if (a == sel) {
										files.set(a, files.get(a+1));
										files.set(a+1, temp);
										
										String temp2 = temp.substring(temp.lastIndexOf("\\")+1);
										imageList.setItem(a, imageList.getItem(a+1));
										imageList.setItem(a+1, temp2);
										
										imageList.setSelection(sel+1);							
									}
								}
								
								if (sel < files.size()-1) {
									sel++;
								}
							}	
							
							canvas.redraw();							
						}
						
						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
						}
					});
					
					
					btnUp.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
								    			    
						    if(files.size() > 0) {
						    	String temp = files.get(sel);
						    	for (int a = files.size(); a > 0; a--) {
									if (a == sel) {
										files.set(a, files.get(a-1));
										files.set(a-1, temp);
										
										String temp2 = temp.substring(temp.lastIndexOf("\\")+1);
										imageList.setItem(a, imageList.getItem(a-1));
										imageList.setItem(a-1, temp2);
										
										imageList.setSelection(sel-1);						
									}//ifend
								}//forend
						    	
						    	if (sel > 0) {
						    		sel--;
						    	}
						    }//ifend
						    
							
						    canvas.redraw();
						}
					});
					
				imageList.addSelectionListener(new SelectionListener() {

				      public void widgetSelected(SelectionEvent event) {
				    	  sel = imageList.getSelectionIndex();
				    	  System.out.println("You selected: " + sel + " " + files.get(sel));
				    	  				    	  				    	  
				    	  img = new Image(Display.getDefault(), files.get(sel).replace("\\", "\\\\"));
				  		
				    	  canvas.redraw();
				      }

				      public void widgetDefaultSelected(SelectionEvent event) {
				    	  sel = 0;
				      }
				    });
				
					
			//=== === === === === === === === === === === === === === === === === === === === === 	
			//cikkszám LISTENER
				txtJazzInput.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent arg0) {
						
						txtJazzFilenameOutput.setText(txtJazzInput.getText().replace(" ", "_").replace("#", "_").replace("+", "_").replace("-", "_").replace("(", "_").replace(")", "_").toUpperCase());
					}
				});
				
				btnJazzPaste.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						txtJazzInput.paste();
					}
				});
				
				btnJazzDel.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						txtJazzInput.setText("");
					}
				});
				
					
				
				
			//badgeGroup LISTENER	TODO
				// vagy az egyik, vagy a másik lehet kiválasztva a kettõbõl
				// nem akartam radioval, mert lehet, h egyikre sincs szükség (pl. tv-nél)
				btn4G.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						if (btn4Gplus.getSelection()) {
							btn4Gplus.setSelection(false);
						}
						canvas.redraw();   
					}			
				});
						
				btn4Gplus.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						if (btn4G.getSelection()) {
							btn4G.setSelection(false);
						}
						canvas.redraw();
					}
				});
				
				btn4Ghang.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {				
						canvas.redraw();
					}
				});

				btnHelloHolnap.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						canvas.redraw();
					}
				});
				
				btnSimUnlock.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						canvas.redraw();						
					}
				});
				
				btnRcs.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						canvas.redraw();
					}
				});

				btnHuaweiLogo.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						canvas.redraw();
					}
				});
				
			//egyéb LISTENER	
				btnSharpen.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
					}
				});

				btnPngquant.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
					}
				});

				btnReszpoZip.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
					}
				});

				btnAtgZip.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
					}
				});
				
				
				
			
				
				
				
				btnMehet.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						System.out.println("4g: " + btn4G.getSelection());
						System.out.println("4g+: " + btn4Gplus.getSelection());
						System.out.println("4g hang: " + btn4Ghang.getSelection());
						System.out.println("simu: " + btnSimUnlock.getSelection());
						System.out.println("hh!: " + btnHelloHolnap.getSelection());
						System.out.println("hua: " + btnHuaweiLogo.getSelection());
						System.out.println("rcs: " + btnRcs.getSelection());
						System.out.println();		
						
						
						//rem LTE+ VOLTE SIM
						//magick convert -page +0+0 %file1%  -page %icon12% %ltep2%  -page %icon22% %volte2%  -page %icon32% %sim2%  -background none -layers merge +repage %file1%
						//magick convert -page +0+0 %file2%  -page %icon14% %ltep4%  -page %icon24% %volte4%  -page %icon34% %sim4%  -background none -layers merge +repage %file2%
						//magick convert -page +0+0 %file3%  -page %icon18% %ltep8%  -page %icon28% %volte8%  -page %icon38% %sim8%  -background none -layers merge +repage %file3%
						
						//set "ltep8=c:\_upload\_script\ikon\4gplus_800.png"
						//set "ltep4=c:\_upload\_script\ikon\4gplus_400.png"
						//set "ltep2=c:\_upload\_script\ikon\4gplus_200.png"
						
						//set "icon12=+164+7"
						//set "icon14=+329+14"
						//set "icon18=+658+28"

						//set "icon22=+164+39"
						//set "icon24=+329+78"
						//set "icon28=+658+156"

						//set "icon32=+164+71"
						//set "icon34=+329+142"
						//set "icon38=+658+284"
							
							
						
				//ez is oké, mûködik		
				XMLmake(txtJazzFilenameOutput.getText(), files.get(0));
					
				
				//ez oké, ez mûködik
//				new Zip().zipFiles("c:\\_upload\\_nokia\\" + txtJazzFilenameOutput.getText(), "c:\\_upload\\_nokia\\" + txtJazzFilenameOutput.getText() + ".zip");
					    
						 
						 				
					
				//ez lenne a törlés, ha mûködne ez a szar
						String com = "cmd.exe /c rd c:\\_upload\\_nokia\\" + txtJazzFilenameOutput.getText() + " /s /q";
						System.out.println(com);
						
						//az ellen, hogy megint kitöröljem az egész programot :DDD
						if (txtJazzFilenameOutput.getText() != null && !txtJazzFilenameOutput.getText().isEmpty()) {
							executeCommand(com);
						}
						
						
						
						
						ImageInfo info;
						try {
							info = new ImageInfo("c:\\_upload\\_nokia\\asus.png");
							MagickImage image=new MagickImage(info);
							DrawInfo aInfo = new DrawInfo(info);
							aInfo.setFill(PixelPacket.queryColorDatabase("yellow"));
							//aInfo.setUnderColor(PixelPacket.queryColorDatabase("red"));

							aInfo.setOpacity(100);
							aInfo.setPointsize(36);
							aInfo.setFont("Arial");

							aInfo.setGeometry("+50+50");
							aInfo.setText("JMagick Tutorial");

							image.annotateImage(aInfo);
							image.setFileName("text.jpg");
							image.writeImage(info); 							
							
						} catch (MagickException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					
					
					} //widgetselected end

					
					
				
					
					
					
					private void XMLmake(String filename, String filepath) {
					   String path = "c:\\_upload\\_nokia\\" + filename;
						

					    try {
							Files.createDirectories(Paths.get(path));
							Files.createDirectories(Paths.get(path + "\\ProductPictures"));
							Files.createDirectories(Paths.get(path + "\\RealSize"));
							
							for(int i = 0; i < files.size(); i++) {
								String form = String.format("%02d", i+1);
								
								Files.createDirectories(Paths.get(path + "\\ProductPictures\\ProductPictures" + form));
								
								 File fwp = new File(path + "\\ProductPictures\\ProductPictures" + form + "\\meta_inf.xml");
							    //TODO külön mentés ablak kéne
							     
								 //beleírja az xml fájlba a kontentet
							    try {
							        FileWriter fw = new FileWriter(fwp);
		
							        fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
							        fw.write("<respic alt=\"" + filename.toLowerCase() + "\" title=\"" + filename.toLowerCase() + "\">\r\n");
							        fw.write("\t<pic file=\"" + filename.toLowerCase() + "_" + form + "_200.png\" width=\"200\"/>\r\n");
							        fw.write("\t<pic file=\"" + filename.toLowerCase() + "_" + form + "_400.png\" width=\"400\"/>\r\n");
							        fw.write("\t<pic file=\"" + filename.toLowerCase() + "_" + form + "_800.png\" width=\"800\"/>\r\n");
							        fw.write("</respic>");
		
							        fw.close();
		
							    } catch (IOException e) {
							        e.printStackTrace();
							    }
							    
							   
							}//forend
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    	
						
					} //XMLmake vége
					
						
					public String executeCommand(String command) {

						StringBuffer output = new StringBuffer();

						Process p;
						try {
							p = Runtime.getRuntime().exec(command);
							//p.waitFor();
							BufferedReader reader = 
				                           new BufferedReader(new InputStreamReader(p.getInputStream()));

							String line = "";			
							while ((line = reader.readLine())!= null) {
								output.append(line + "\n");
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

						return output.toString();

					}
					
					
					public void deleteDirectory(Path path) {
						   	
								try {
									Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
									   @Override
									   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
									      
										   Files.delete(file);
									       
									       return FileVisitResult.CONTINUE;
									   }

									   @Override
									   public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
									       Files.delete(dir);
									       return FileVisitResult.CONTINUE;
									   }
									});
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						 
						}
					
					
					
				}); //btnmehet actionlistener end
				
				
			
				
				
	} //run shell end
}
