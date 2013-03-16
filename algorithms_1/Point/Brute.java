import java.util.*;

public class Brute {
	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

		In in = new In(args[0]);
		int N = in.readInt();
		Point[] arr = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			arr[i] = new Point(x,y);
			arr[i].draw();
		}

		// Use this in the real program, it works great!
		/*
		Point[] temp;
		int tempSize = 0;
		for (int i = 0; i < N-2; i++) {
			for (int j = i+1; j < N-1; j++) {
				double slope = arr[i].slopeTo(arr[j]);	
				temp = new Point[N];
				tempSize = 0;
				temp[0] = arr[i]; tempSize++;
				temp[1] = arr[j]; tempSize++;

				for (int k = j+1; k < N; k++) {
					if (arr[i].slopeTo(arr[k]) == slope) {
						temp[tempSize] = arr[k];
						tempSize++;
					}
				}
				if (tempSize >= 4) {
					Arrays.sort(temp, 0, tempSize - 1);
					StdOut.print(temp[0].toString());		
					for (int x = 1; x < tempSize; x++)
						StdOut.print(" -> " + temp[x].toString());
					StdOut.println();
					temp[0].drawTo(temp[tempSize - 1]);
				}

			}
					
		}
*/
		Arrays.sort(arr);
		for (int i = 0; i < N-3; i++)
			for (int j = i+1; j < N-2; j++) 
				for (int k = j+1; k < N-1; k++)
					for (int l = k+1; l < N; l++) {
						double slope = arr[i].slopeTo(arr[j]);
						if (arr[i].slopeTo(arr[k]) == slope && arr[i].slopeTo(arr[l]) == slope) {
							// sort the points from smallest to largest
							StdOut.println(arr[i].toString() + " -> " +
											arr[j].toString() + " -> " + 
											arr[k].toString() + " -> " +
											arr[l].toString());
							arr[i].drawTo(arr[l]);
							StdDraw.show();
						}
					}

		StdDraw.show();

	}
}