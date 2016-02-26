class Queue implements Cloneable{
	
	int[] buf;
	int size;
	int f_i; // front index
	int r_i; // rear index
	int i=1;
	Queue(int s){
		buf=new int[s+1]; // circular queue so one block always empty
		f_i=1;
		r_i=0;
		size=s;
	}

	synchronized void enqueue() throws InterruptedException{
		f_i=f_i%(size+1);
		while(f_i==r_i) {
			wait();
		}
			buf[f_i]=i++;
			System.out.println(buf[f_i++] + " is inserted in the queue.");
			notifyAll();
	}
	synchronized void dequeue() throws InterruptedException{
		r_i%=(size+1);
		while((r_i+1)%(size+1)==f_i ){
			wait();
		}
		r_i=(r_i+1)%(size+1);
			System.out.println(buf[r_i]+" is dequeued.");
			notifyAll();
	}
	

};

class AddElement implements Runnable{
	Queue q;
	AddElement(int i, Queue q){
		this.q=q;
	}
	public void run() {
		while(q.i<500 && q.i>0){
			try {
				q.enqueue();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException");
			}
		}
	}
}

class RemoveElement implements Runnable{
	Queue q;
	
	RemoveElement(Queue q){
		this.q=q;
	}
	public void run(){
		while(q.i<500 && q.i>0){
			try {
				q.dequeue();
			} catch (InterruptedException e) {
			 System.out.println("InterruptedException");	
			}
		}
	}
}

public class Q3 {
	public static void main(String[] args)throws InterruptedException{
		Queue q= new Queue(100);
		AddElement e1 = new AddElement(0,q);
		RemoveElement e2 = new RemoveElement(q);
		Thread myt1 = new Thread(e1);
		Thread myt2 = new Thread(e2);
		myt1.start();
		myt2.start();	
	}
}
