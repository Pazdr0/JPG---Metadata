
public class FileInfo {

	private int size;
	private int width;
	private int height;
	private int dataPrecision;							// częstotliowść próbkowania
	private int numberOfComponents;						// liczba kolorów
	private int[] fileIdentifier;
	private int densityUnit;
	private int Xdensity;
	private int Ydensity;
	
	
	/*************** Settery ***************/
	public void setDensityUnit(int unit) {
		densityUnit = unit;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setFileIdentifier(int[] fileIdentifier) {
		this.fileIdentifier = new int[fileIdentifier.length];
		for (int i=0; i<fileIdentifier.length; i++) {
			this.fileIdentifier[i] = fileIdentifier[i];
		}
	}
	public void setNumberOfComponents(int number) {
		this.numberOfComponents = number;
	}
	public void setDataPrecision(int data) {
		dataPrecision = data;
	}
	public void setXdensity(int dens) {
		Xdensity = dens;
	}
	public void setYdensity(int dens) {
		Ydensity = dens;
	}
	
	/*************** Gettery ***************/
	public String getDensityUnit() {
		if (densityUnit == 0) 
			return "X/Y ratio";
		else if (densityUnit == 1)
			return "dots/inch";
		else 
			return "dots/cm";
	}
	public String getYdensity() {
		return Integer.toString(Ydensity);
	}
	public String getXdensity() {
		return Integer.toString(Xdensity);
	}
	public String getDataPrecision() {
		return Integer.toString(dataPrecision);
	}
	public String getNumberOfComponents() {
		if (numberOfComponents == 3) {
			return "RGB";
		}
		else if (numberOfComponents >= 4) 
			return "CMYK";
		else 
			return "Greyscale";
	}
	public String getWidth() {
		return Integer.toString(width) + "px";
	}
	public String getHeight() {
		return Integer.toString(height) + "px";
	}
	public String getSize() {
		return Integer.toString(size/1024) + " KB";
	}
	public String getFileIdentifier() {
		String st = new String();
		for (int i=0; i<fileIdentifier.length; i++) {
			if (fileIdentifier[i] == 0) 
				st = st + "#" + "0";
			else
				st = st + Character.toString((char)fileIdentifier[i]);
		}
		return st;
	}
}
