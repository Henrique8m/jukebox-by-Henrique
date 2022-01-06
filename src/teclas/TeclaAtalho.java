package teclas;

import gui.MainController;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TeclaAtalho{
	
	public TeclaAtalho() {
		}

	public void onKeyPress(KeyEvent evt, MainController main) {
		
		if(evt.getCode() == KeyCode.LEFT) {
			
			
			
			if ((main.pastaAtual - 2) > 0 ) {
				
				main.pastaAtual --;
				previousFolder(main, main.pastas[main.pastaAtual-2].toURI().toString() + main.linkCapa);
				
			}	
			
			else {
				if((main.pastaAtual - 1) > 0 ) {
					
					main.pastaAtual --;
					previousFolder(main, main.pastas[main.pastas.length-1].toURI().toString() + main.linkCapa);
				}
				
				else {
					if((main.pastaAtual) > 0 ) {
						
						main.pastaAtual --;
						previousFolder(main, main.pastas[main.pastas.length-2].toURI().toString() + main.linkCapa);
						
					}
					
					else {
						
						main.pastaAtual = main.pastaLefht - 1;
						previousFolder(main, main.pastas[main.pastas.length-3].toURI().toString() + main.linkCapa);
						
					}
					
				}
				
			}
			
		}
		
		else if(evt.getCode() == KeyCode.RIGHT) {
			
			if (main.pastaAtual < (main.pastas.length - 3)) {
			
				main.pastaAtual ++;
				nexteFolder(main, main.pastas[main.pastaAtual + 2].toURI().toString() + main.linkCapa);
					
			}	
			
			else {
				if((main.pastaAtual < (main.pastas.length - 2))) {
					
					main.pastaAtual ++;
					nexteFolder(main, main.pastas[0].toURI().toString() + main.linkCapa);
										
				}
				
				else {				
					if((main.pastaAtual < (main.pastas.length - 1))) {
					
						main.pastaAtual ++;
						nexteFolder(main, main.pastas[1].toURI().toString() + main.linkCapa);
											
					}
					
					else {
						
						main.pastaAtual =0;
						nexteFolder(main, main.pastas[2].toURI().toString() + main.linkCapa);
						
						
					}

				}
				
			}
			
		}
		
	
	}
	
	public void previousFolder(MainController main, String imagem) {

		main.imageView05.setImage(main.imageView04.getImage());
		main.imageView04.setImage(main.imageView03.getImage());
		main.imageView03.setImage(main.imageView02.getImage());
		main.imageView02.setImage(main.imageView01.getImage());		
		main.image = new Image(imagem);
		main.imageView01.setImage(main.image);
	
	}
	
	public void nexteFolder(MainController main, String imagem) {
		
		main.imageView01.setImage(main.imageView02.getImage());
		main.imageView02.setImage(main.imageView03.getImage());
		main.imageView03.setImage(main.imageView04.getImage());
		main.imageView04.setImage(main.imageView05.getImage());		
		main.image = new Image(imagem);
		main.imageView05.setImage(main.image);
		
	}
	
}