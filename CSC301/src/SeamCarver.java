import java.awt.Color;
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

	private Picture input;
	private double[][] energy_list;
	
	public SeamCarver(Picture picture){
		// create a seam carver object based on the given picture
		if (picture == null) throw new java.lang.NullPointerException("input is null");
		input = picture;
		energy_list = new double[this.width()][this.height()];
		for(int i = 0; i < this.height(); i++){
			for(int j = 0; j < this.width(); j++){
				energy_list[j][i] = this.energy(j, i);
			}
		}
	}
	public Picture picture(){
		// current picture
		return input;
	}
	public int width(){
		// width of current picture
		return input.width();
	}
	public int height(){
		// height of current picture
		return input.height();
	}
	public double energy(int x, int y){
		// energy of pixel at column x and row y
		if(x < 0 || y < 0 || x >= this.width() || y >= this.height()) throw new java.lang.IndexOutOfBoundsException("x or y is not vaild");
		Color beforeX;
		Color beforeY;
		Color afterX;
		Color afterY;
		if(x == 0) beforeX = input.get(input.width()-1, y);
		else beforeX = input.get(x-1, y);
		if(y == 0) beforeY = input.get(x, input.height() - 1);
		else beforeY = input.get(x, y-1);
		if(x == input.width() - 1) afterX = input.get(0, y);
		else afterX = input.get(x+1, y);
		if(y == input.height() - 1) afterY = input.get(x, 0);
		else afterY = input.get(x, y+1);
		
		double RGBX = Math.pow(beforeX.getRed() - afterX.getRed(), 2) + Math.pow(beforeX.getGreen() - afterX.getGreen(), 2) + Math.pow(beforeX.getBlue() - afterX.getBlue(), 2);
		double RGBY = Math.pow(beforeY.getRed() - afterY.getRed(), 2) + Math.pow(beforeY.getGreen() - afterY.getGreen(), 2) + Math.pow(beforeY.getBlue() - afterY.getBlue(), 2);
		double fin = Math.sqrt(RGBX + RGBY);
		return fin;
	}
	public int[] findHorizontalSeam(){
		// sequence of indices for horizontal seam
		double[][] DisTo = new double[this.width()][this.height()];
		int[][] PathTo = new int[this.width()][this.height()]; // down = -1, right = 0, up = 1
		int[] fin = new int[this.width()];
		int min_index = 0;
		double min = Double.MAX_VALUE;
		for(int i = 0; i < this.width(); i++){
			for(int j = 0; j < this.height(); j++){
				DisTo[i][j] = Double.MAX_VALUE;
			}
		}
		
		for(int i = 0; i < this.width(); i++){
			for(int j = 0; j < this.height(); j++){
				//left
				if(i == 0) {
					DisTo[i][j] = energy_list[i][j];
					PathTo[i][j] = 0;
				}
				//right
				if(i == this.width()-1){
					if(DisTo[i][j] < min){
						min = DisTo[i][j];
						min_index = j;
					}
				}
				else{
					//right
					double calc = DisTo[i][j] + energy_list[i+1][j];
					if(DisTo[i+1][j] > calc) {
						DisTo[i+1][j] = calc;
						PathTo[i+1][j] = 0;
					}
					//right finish
					//up
					if(j != 0){
						calc = DisTo[i][j] + energy_list[i+1][j-1];
						if(DisTo[i+1][j-1] > calc) {
							DisTo[i+1][j-1] = calc;
							PathTo[i+1][j-1] = 1;
						}
					}//up finish
					//down
					if(j != this.height()-1){
						calc = DisTo[i][j] + energy_list[i+1][j+1];
						if(DisTo[i+1][j+1] > calc){
							DisTo[i+1][j+1] = calc;
							PathTo[i+1][j+1] = -1;
						}
					}//down finish
				}
			}
		}
		
		
		for(int i = this.width() - 1; i >= 0; i--){
			fin[i] = min_index;
			if(PathTo[i][min_index] == 0) continue;
			else if(PathTo[i][min_index] == -1) min_index--;
			else if(PathTo[i][min_index] == 1) min_index++;
		}
		return fin;
	}
	
	public int[] findVerticalSeam(){
		// sequence of indices for vertical seam
		
		double[][] DisTo = new double[this.width()][this.height()];
		int[][] PathTo = new int[this.width()][this.height()]; // left = -1, down = 0, right = 1
		int[] fin = new int[this.height()];
		int min_index = 0;
		double min = Double.MAX_VALUE;
		
		for(int i = 0; i < this.width(); i++){
			for(int j = 0; j < this.height(); j++){
				DisTo[i][j] = Double.MAX_VALUE;
				PathTo[i][j] = 0;
				if(i == 0) fin[j] = 0;
			}
		}
		
		
		for(int j = 0; j < this.height(); j++){
			for(int i = 0; i < this.width(); i++){
				//Top
				if(j == 0) DisTo[i][j] = energy_list[i][j];
				//Bottom
				if(j == this.height() - 1){
					if(min > DisTo[i][j]){
						min = DisTo[i][j];
						min_index = i;
					}
					
				}
				else if(j != this.height() -1){
					//down
					double calc = DisTo[i][j] + energy_list[i][j+1];
					if(DisTo[i][j+1] > calc) {
						DisTo[i][j+1] = calc;
						PathTo[i][j+1] = 0;
					}
					//down finish
					//left
					if(i != 0){
						calc = DisTo[i][j] + energy_list[i-1][j+1];
						if(DisTo[i-1][j+1] > calc) {
							DisTo[i-1][j+1] = calc;
							PathTo[i-1][j+1] = -1;
						}
					}//left finish
					//right
					if(i != this.width() - 1){
						calc = DisTo[i][j] + energy_list[i+1][j+1];
						if(DisTo[i+1][j+1] > calc){
							DisTo[i+1][j+1] = calc;
							PathTo[i+1][j+1] = 1;
						}
					}//right finish
				}
			}
		}
		for(int i = this.height() - 1; i >= 0; i--){
			fin[i] = min_index;
			if(PathTo[min_index][i] == 0) continue;
			else if(PathTo[min_index][i] == -1) min_index++;
			else if(PathTo[min_index][i] == 1) min_index--;
		}
		return fin;
	}
	public void removeHorizontalSeam(int[] seam){
		// remove horizontal seam from current picture
		if(seam == null) throw new java.lang.NullPointerException("input is null");
		if(seam.length != input.width() || input.height() == 1) throw new java.lang.IllegalArgumentException("input length is not correct");
		Picture temp = new Picture(this.width(), this.height() - 1);
		int index = 0;
		int deq = 0;
		for(int i = 0; i < input.width(); i++){
			for(int j = 0; j < input.height(); j++){
				if(seam[i] == j) deq = 1;
				else{
					temp.set(i, j - deq, input.get(i, j));
				}
			}
			deq = 0;
		}
		
		input = temp;
		for(int i = 0; i < input.width(); i++){
			for(int j = 0; j < input.height(); j++){
				energy_list[i][j] = this.energy(i, j);
			}
		}
		
		
	}
	public void removeVerticalSeam(int[] seam){
		// remove vertical seam from current picture
		if(seam == null) throw new java.lang.NullPointerException("input is null");
		if(seam.length != input.height() || input.width() == 1) throw new java.lang.IllegalArgumentException("input length is not correct");
		Picture temp = new Picture(this.width()-1, this.height());
		int index = 0;
		int deq = 0;
		for(int i = 0; i < seam.length+1; i++){
			if(seam[i] - seam[i+1] > 1 || seam[i] - seam[i+1] < -1) throw new java.lang.IllegalArgumentException("Wrong seam");
		}
		for(int j = 0; j < input.height(); j++){
			for(int i = 0; i < input.width(); i++){
				if(seam[j] == i) deq = 1;
				else{
					temp.set(i - deq, j, input.get(i, j));
				}
			}
			deq = 0;
		}
		
		input = temp;
		for(int i = 0; i < input.width(); i++){
			for(int j = 0; j < input.height(); j++){
				energy_list[i][j] = this.energy(i, j);
			}
		}
		
	}
	public static void main(String[] args){
		// do unit testing of this class
		SeamCarver test = new SeamCarver(new Picture("HJocean.png"));
		System.out.println(test.width());
		System.out.println(test.height());
		System.out.println(test.findVerticalSeam());
		test.findHorizontalSeam();
		test.removeHorizontalSeam(test.findHorizontalSeam());
		test.removeVerticalSeam(test.findVerticalSeam());
	}

}