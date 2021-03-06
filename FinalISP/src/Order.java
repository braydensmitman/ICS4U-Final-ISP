import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;




public class Order {

	public LinkedList<Product> inventory = new LinkedList<Product>();
	
	private String name;
	private String address;
	private double total;
	
	private RandomAccessFile raf = null;
	
	public Order() {
		
	}
	
	public Order(String name,String address,double total) {
		this.name = name;
		this.address = address;
		this.total = total;
	}
			
	public void addItem(BufferedImage image,String name,double quantity,double price) throws IOException {
		inventory.add(new Product(image,name,quantity,price));
		if(raf!=null) {
			writeBinFile(inventory.size()-1);
		}
	}
	
	public void addItem() throws IOException {
		inventory.add(new Product());
		if(raf!=null) {
			writeBinFile(inventory.size()-1);
		}
	}
	
	public void removeItem(Product item) throws IOException{
		inventory.remove(item);
		if(raf!=null) {
			emptyBinFile();
			writeBinFile();
		}
	}
	
	public void removeItem(int item) throws IOException{
		inventory.remove(item);
		if(raf!=null) {
			emptyBinFile();
			writeBinFile();
		}
	}
	
	public void emptyBinFile() throws IOException {
		raf = Menu.setRafBin(1);
		
	}
	
	public void writeBinFile(RandomAccessFile raf)throws IOException{
		this.raf = raf;
		for(int x = 0 ; inventory.size()>x ; x++) {
			inventory.get(x).writeBinFile(raf, x);
			
		}
	}
	
	public void writeBinFile()throws IOException{

		for(int x = 0 ; inventory.size()>x ; x++) {
			inventory.get(x).writeBinFile(raf, x);
		}
	}
	
	public void writeBinFile(int x)throws IOException{
			inventory.getLast().writeBinFile(raf, x);
	}
	
	public void readBinFile(RandomAccessFile raf)throws IOException{
		this.raf = raf;
		for(int x =inventory.size(); raf.length()/96>x ; x++) {
			inventory.add(new Product());
			inventory.getLast().readBinFile(raf, x);
		}
	}	
	
	public void printOrder() {
		System.out.println("Name: " + name);
		System.out.println("Address: "+ address);
		System.out.println("Total: "+total);
		System.out.println();
		
		for(int x = 0 ; inventory.size()>x ; x++) {
			System.out.println("Product #"+(x+1));
			inventory.get(x).printProduct();
		}
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public double getTotal() {
		return total;
	}
	
	
	
}
