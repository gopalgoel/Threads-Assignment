class Matrix{
	int[][] m;
	int row;
	int col;
	int assignColumnIndex=0;
	boolean copied=false; // marks the success of copy
	Matrix(int[][] m,int row,int col){
		this.m=m;
		this.row=row;
		this.col=col;
	}

	synchronized void assignColumn(TwoToOne t) throws InterruptedException{
		if(assignColumnIndex>=col) {
			throw new InterruptedException();
		}
		else{
			t.cl=assignColumnIndex++;
			System.out.println("Thread " + t.threadNumber + " has been assigned column "+ t.cl);
		}
	}

	void copyColumn(Array a, int cl){
		for(int i=0;i<row;i++){
			a.arr[cl+(col)*i]=m[i][cl];
		}
	}
}

class Array{
	int[] arr;
	int index=0;
	int size;
	Array(int size){
		this.size=size;
		arr=new int[size];
	}
}

class TwoToOne extends Thread{
	Array a;
	Matrix m;
	int cl; // column it is copying
	int startIndex;
	int threadNumber;
	static int threadNumberCount=0;

	TwoToOne(Matrix m,Array a){
		this.a=a;
		this.m=m;
		threadNumber=++threadNumberCount;
	}
	public void run(){
		try{
			do{
				m.assignColumn(this);
				m.copyColumn(a,cl);
				if(cl==m.col-1){
					for(int i=0;i<a.size;i++){
						System.out.println(a.arr[i]);
					}
				}
			}while(!m.copied);
		}
		catch(InterruptedException e){}
	}
}


class Q4{
	public static void main(String[] args){
		int row=4;
		int col=9;
		int count=1;
		int[][] mat = new int[row][col];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				mat[i][j]=count++;
			}
		}
		Matrix m = new Matrix(mat,row,col);
		Array a = new Array(row*col);
		Thread t1 = new TwoToOne(m,a);
		Thread t2 = new TwoToOne(m,a);
		Thread t3 = new TwoToOne(m,a);
		Thread t4 = new TwoToOne(m,a);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
