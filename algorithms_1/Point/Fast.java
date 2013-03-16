import java.util.*;

public class Fast {

	private static void printArray(Point[] arr) {
		for(int i = 0; i < arr.length; i++)
			StdOut.print(arr[i].toString());
		StdOut.println();
	}

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


		// i = Origin index
		// j = start equal slope index 
		// k = end equal slope index;

		Point[] temp;

		printArray(arr);
		for (int i = 0; i < N-3; i++) { // pick a point as the origin
			Arrays.sort(arr, i, N-1, arr[i].SLOPE_ORDER);

			for (int j = i+1; j < N-2; j++) { //

				int k = j+1;
				while ((arr[i].slopeTo(arr[j]) == arr[i].slopeTo(arr[k])) && (k < N)) k=k+1;
				// handle i.k not being the same slope as i.j
				if ((k-j >= 2)) {	// handle i.k being the same slope as i.j
					temp = new Point[2+k-j];
					temp[0] = arr[i];
					for (int y = 0; y < k-j+1; y++)
						temp[y+1] = arr[j+y];

					// Why is 1234,5678 in the temp array???

					//StdOut.print("temp(before sort): "); printArray(temp);
					Arrays.sort(temp);
					//StdOut.print("temp(after sort): "); printArray(temp);


					// draw it
					temp[0].drawTo(temp[temp.length-1]);
					StdOut.print(arr[i].toString());
					for (int x = j; x <= k; x++ )
						StdOut.print(" -> " + arr[x].toString());
				}

				// i and j through k should all have the same slope
			}
		}




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
					Arrays.sort(temp);
					StdOut.print(temp[0].toString());		
					for (int x = 1; x < tempSize; x++)
						StdOut.print(" -> " + temp[x].toString());
					StdOut.println();
					temp[0].drawTo(temp[tempSize - 1]);
				}

			}
					
		}
*/
		StdDraw.show();

	}
}