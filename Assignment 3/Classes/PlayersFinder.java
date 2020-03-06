package eg.edu.alexu.csd.datastructure.iceHockey;

import java.awt.Point;

public class PlayersFinder implements IPlayersFinder{
	 static int dataOfBlocks[][] = new int [50][5];
	 static int counter=0;

	public java.awt.Point[] findPlayers(String[] photo, int team, int threshold) {
		if(photo == null|| photo.length == 0 || photo[0] == null) return null;
		int n = photo.length;
		int m = photo[0].length();
		char pixels[][] = new char [50][50];
		
		for(int i=0; i < n; i++)
		{
			for(int j=0; j < m; j++)
			{
				pixels[i][j] = photo[i].charAt(j);
			}
		}
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<m; j++)
			{
				if(String.valueOf(pixels[i][j]).equals(Integer.toString(team)))
				{
					dataOfBlocks[counter][1] = j;
					dataOfBlocks[counter][2] = j;
					dataOfBlocks[counter][3] = i;
					dataOfBlocks[counter][4] = i;
					findArea(i, j, pixels, team, n, m);
					counter++;
				}
			}
		}
		int numPoints=0;
		for(int i=0; i<=counter; i++)
		{
			if(dataOfBlocks[i][0] >= threshold) numPoints++;
		}
		Point[] pointsReturned =  new Point[numPoints];
		for(int i=0; i<numPoints; i++)
		{
			pointsReturned[i] = new Point();
		}
		int z=0;

		while(counter >= 0)
		{
			if(dataOfBlocks[counter][0] >= threshold)
			{
				int xcenter = (2*(dataOfBlocks[counter][2]+1)+2*dataOfBlocks[counter][1])/2;
				int ycenter = (2*(dataOfBlocks[counter][4]+1)+2*dataOfBlocks[counter][3])/2;
				pointsReturned[z].x = xcenter;
				pointsReturned[z].y = ycenter;
				z++;
			}
			counter--;
		}

		int tempX;
		int tempY;
		for(int i=0; i<numPoints; i++)
		{
			for(int j=0; j<numPoints; j++)
			{
				if(j == numPoints-1) break;
				if(pointsReturned[j].getX() > pointsReturned[j+1].getX())
				{
					tempX = (int) pointsReturned[j+1].getX();
					tempY = (int) pointsReturned[j+1].getY();
					pointsReturned[j+1].x =(int) pointsReturned[j].getX();
					pointsReturned[j+1].y =(int) pointsReturned[j].getY();
					pointsReturned[j].x = tempX;
					pointsReturned[j].y = tempY;
				}
				if(pointsReturned[j].getX() == pointsReturned[j+1].getX())
				{
					if(pointsReturned[j].getY() > pointsReturned[j+1].getY())
					{
						tempX = (int) pointsReturned[j+1].getX();
						tempY = (int) pointsReturned[j+1].getY();
						pointsReturned[j+1].x =(int) pointsReturned[j].getX();
						pointsReturned[j+1].y =(int) pointsReturned[j].getY();
						pointsReturned[j].x = tempX;
						pointsReturned[j].y = tempY;
					}
				}

			}
		}

		counter = 0;
		dataOfBlocks = new int[50][5];
		
		return pointsReturned;
		
	}
	public static void findArea(int i, int j, char[][] pixels, int val, int n, int m)
	{
		pixels[i][j] = '0';
		dataOfBlocks[counter][0] += 4;
		if(i < n) {
			String a = Integer.toString(val);
			String b = String.valueOf(pixels[i+1][j]);
			if(b.equals(a))
			{
				if(i+1 > dataOfBlocks[counter][4]) dataOfBlocks[counter][4] = (i+1);	
				else if(i+1 < dataOfBlocks[counter][3])dataOfBlocks[counter][3] = (i+1);		
				
				findArea(i+1, j, pixels, val, n, m);
			}
		}
		if(i > 0)
		{
			String c = Integer.toString(val);
			String d = String.valueOf(pixels[i-1][j]);
			if(d.equals(c))
			{
				if(i-1 > dataOfBlocks[counter][4]) dataOfBlocks[counter][4] = (i-1);
				else if(i-1 < dataOfBlocks[counter][3]) dataOfBlocks[counter][3] = (i-1);

				
				findArea(i-1, j, pixels, val, n, m);
			}
		}
		if(j < m)
		{
			String e = Integer.toString(val);
			String f = String.valueOf(pixels[i][j+1]);
			if(f.equals(e)) {
				if(j+1 > dataOfBlocks[counter][2]) dataOfBlocks[counter][2] = j+1;
				else if(j+1 < dataOfBlocks[counter][1]) dataOfBlocks[counter][1] = j+1;

				findArea(i, j+1, pixels, val, n, m);
			}
		}
		if(j > 0)
		{
			String g = Integer.toString(val);
			String h = String.valueOf(pixels[i][j-1]);
			if(h.equals(g))
			{
				if(j-1 > dataOfBlocks[counter][2]) dataOfBlocks[counter][2] = j-1;
				else if(j-1 < dataOfBlocks[counter][1]) dataOfBlocks[counter][1] = j-1;

				findArea(i, j-1, pixels, val, n, m);
			}
		}
	}
}
