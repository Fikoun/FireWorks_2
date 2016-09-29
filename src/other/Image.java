package other;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Image
{
	// JEDNODUCHÁ TŘÍDA PROZÍSKÁNÍ OBRÁZKU
	// vytvořeno z důvodu ostrých hran
	public BufferedImage get;
	public Image(String path)
	{	
		try{get = ImageIO.read(Image.class.getResourceAsStream(path));}
		catch (IOException e) {e.printStackTrace();}
	}
	
}
