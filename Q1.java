
class X{
	int i;
	boolean printed=false; // Start be printing the given number
	boolean incremented=true; // Just to allow printing to start first 

	X(int i){
		this.i=i;
	}

	synchronized void inc(){
		while(incremented){
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("ex");
			}
		}
		i++;
		incremented=true;
		printed=false;
		notifyAll();
	}
	synchronized void print(){
		while(printed){
			try {
				wait();
			}
			catch (InterruptedException e){
				System.out.println("ext");
			}
		}
		System.out.println(i);
		printed=true;
		incremented=false;
		notifyAll();
	}
}

class T1 extends Thread{
	X a;

	T1(X a){
		this.a=a;
	}

	public void run(){
		while(a.i<100){
			a.inc();
		}
	}
}

class T2 extends Thread{
	X a;

	T2(X a){
		this.a=a;
	}

	public void run(){
		while(a.i<100){
			a.print();
		}
	}

}

public class Q1 {
	public static void main(String[] args){
		X a = new X(0);
		T1 thread1 = new T1(a);
		T2 thread2 = new T2(a);
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
