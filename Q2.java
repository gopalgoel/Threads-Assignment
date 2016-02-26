class ArrayData{
	int[] arr;
	int key;
	int size;
	int indexAssigned=0; // 
	boolean searched = false;
	ArrayData(int[] arr, int key){
		this.arr=arr;
		this.key=key;
		size=arr.length;
	}


	void binarySearch(int fi, int ri){
		if(ri>=fi){
			int mid= fi +(ri-fi)/2;
			if(arr[mid]==key){
				searched=true;
				System.out.println("Key Found at index "+ mid);
			}
			if(arr[mid]>key){
				binarySearch(fi,mid-1);
			}
			if(arr[mid]<key){
				binarySearch(mid+1,ri);
			}
		}
	}

	synchronized void assignIndex(BinarySearchThread b) throws InterruptedException{
		if(indexAssigned==size){
			if(b.fi==(size-100) && b.ri==(size-1) && searched==false){
				System.out.println("Key Not Found");
			}
			throw new InterruptedException();
		}
		b.fi=indexAssigned;
		indexAssigned+=100;
		b.ri=indexAssigned-1;
		System.out.println(	"Thread " + b.threadNumber + " is assigned " +
				b.fi+" to "+ b.ri );

	}
}

class BinarySearchThread extends Thread{
	ArrayData a;
	int fi;
	int ri;
	int threadNumber;
	static int threadNumberCount=0;

	BinarySearchThread(ArrayData a){
		this.a=a;
		threadNumber=++threadNumberCount;
	}
	public void run(){
		try{
			do{
				a.assignIndex(this);	
				a.binarySearch(fi,ri);
			}while(!a.searched);
		}
		catch(InterruptedException e){}
	}
}

class Q2{
	public static void main(String[] args){
		int[] arr;
		int size=1000;
		arr=new int[size];
		for(int i=0;i<size;i++){
			arr[i]=i;
		}
		int key=9669;
		ArrayData a = new ArrayData(arr,key); // size,key
		Thread t1 = new BinarySearchThread(a);
		Thread t2 = new BinarySearchThread(a);
		Thread t3 = new BinarySearchThread(a);
		Thread t4 = new BinarySearchThread(a);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}