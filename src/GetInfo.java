import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
//import java.util.Scanner;
//import java.awt.Desktop;

public class GetInfo {

	// Markers
//	private String APP0 = "ffffffe0";
	
	private FileInfo pic1 = new FileInfo();
	public ArrayList<Byte> myByteList = new ArrayList<Byte>();		
	private byte[] byteArray;
	public static void main(String[] args) {
		GetInfo get = new GetInfo();
		get.run();
	}

	public void run() {
		System.out.println("Aplikacja dekodująca informacje z pliku .jpg");
//		displayFile();
			
		String fileName = "mustang67";
//		Scanner keyBoard = new Scanner(System.in);
//		fileName = keyBoard.nextLine();
		
		byteArray = readBytesToArray("C:\\Users\\Bartek\\Pictures\\" + fileName + ".jpg\\");
		addToMyByteList(byteArray);
		setAttributes();
		System.out.println();		
		getAttributes();
	}
	
	public void addToMyByteList(byte[] arr) {
		int n = 0;
		outerloop:
		for (int i=0; i<arr.length; i++) {
//			System.out.print(i + "\t");
			for (int j=0; j<16; j++) {
				if (arr[n] == 0xffffffff && arr[n+1] == 0xffffffd9) {
					break outerloop;
				}
				else 
//					System.out.printf("%-10s ", Integer.toHexString(byteArray[n]));
					myByteList.add(arr[n]);
					n++;
			}		
//			System.out.println();
		}
//		System.out.printf("%-10s ", Integer.toHexString(byteArray[n]));
//		System.out.printf("%-10s ", Integer.toHexString(byteArray[n+1]));
		myByteList.add(byteArray[n]);
		myByteList.add(byteArray[n+1]);
	}
	
	public byte[] getByteArray() {
		return byteArray;
	}
	public ArrayList<Byte> getByteArrayList() {
		return myByteList;
	}
	
	private void getAttributes() {
		System.out.print("File size: " + pic1.getSize() + ", Width: " + pic1.getWidth() + ", Height: " + pic1.getHeight() + ", Data precision: " + pic1.getDataPrecision());
		System.out.println(", Colors: " + pic1.getNumberOfComponents() + ", File identifier: " + pic1.getFileIdentifier() + ", Density unit: " + pic1.getDensityUnit() + ", X density: " + pic1.getXdensity() + ", Y denisty: " + pic1.getYdensity());
		
	}
	
	private void setAttributes() {
		pic1.setSize(myByteList.size());
		String stValue;
		int[] identy = new int[5];
		for (int i=0; i<500; i++) {
			if (myByteList.get(i) == 0xffffffff && myByteList.get(i+1) == 0xffffffc0) {
				stValue = replaceStr(Integer.toHexString(myByteList.get(i+5))) + replaceStr(Integer.toHexString(myByteList.get(i+6)));
				pic1.setHeight(Integer.parseInt(stValue, 16));
				stValue = replaceStr(Integer.toHexString(myByteList.get(i+7))) + replaceStr(Integer.toHexString(myByteList.get(i+8)));
				pic1.setWidth(Integer.parseInt(stValue, 16));
				stValue = replaceStr(Integer.toHexString(myByteList.get(i+4)));
				pic1.setDataPrecision(Integer.parseInt(stValue, 16));
				stValue = replaceStr(Integer.toHexString(myByteList.get(i+9)));
				pic1.setNumberOfComponents(Integer.parseInt(stValue, 16));
			}
			
			if (myByteList.get(i) == 0xffffffff && myByteList.get(i+1) == 0xffffffe0) {
				for (int j=0; j<identy.length; j++) {
					stValue = replaceStr(Integer.toHexString(myByteList.get(i+4+j)));
					identy[j] = Integer.parseInt(stValue, 16);
				}
				pic1.setFileIdentifier(identy);
				stValue = replaceStr(Integer.toHexString(myByteList.get(i+11)));
				pic1.setDensityUnit(Integer.parseInt(stValue, 16));
				stValue = replaceStr(Integer.toHexString(myByteList.get(i+12))) + replaceStr(Integer.toHexString(myByteList.get(i+13)));
				pic1.setXdensity(Integer.parseInt(stValue, 16));
				stValue = replaceStr(Integer.toHexString(myByteList.get(i+14))) + replaceStr(Integer.toHexString(myByteList.get(i+15)));
				pic1.setYdensity(Integer.parseInt(stValue, 16));
			}
//			if (myByteList.get(i) == 0xffffffff && myByteList.get(i+1) == 0xffffffda) {
//				
//			}
		}
	}
	
	public String replaceStr(String str) {
		str = str.replace("f", "");
		return str;
	}
	
//	private void dispAttributes() {
//		Path path = Paths.get("mustang67.jpg");
//		try {
//			System.out.println(Files.size(path));
//		}
//		catch(IOException ex) {
//			ex.printStackTrace();
//		}
//	}
	
//	private void displayFile() {
//		File f = new File("C:\\Users\\Bartek\\Pictures\\mustang67.jpg\\");
//		Desktop desk = Desktop.getDesktop();
//		try {
//			desk.open(f);
//		}
//		catch(IOException ex) {
//			ex.printStackTrace();
//		}
//	}
	
	public byte[] readBytesToArray(String fileName) {									// wczytuje kolejne bajty z pliku do tablicy
		byte[] arr = {0};
		Path path = Paths.get(fileName);
		try {
			arr = Files.readAllBytes(path);
			return arr;
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return arr;
	}
}
