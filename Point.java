// Decrire un point

public class Point {
	
	public double x;
	public double y;
	
	public Point(double xx, double yy) {
		x = xx;
		y = yy;
	}
	//~ public void spinpoint(Point center, double Angle){
		
		
		//~ double x1 = x - center.x;
		//~ double y1 = y - center.y;

		//~ double x2 = x1 * Math.cos(0.1) - y1 * Math.sin(0.1);
		//~ double y2 = x1 * Math.sin(0.1) + y1 * Math.cos(0.1);

		//~ x = x2 + center.x;
		//~ y = y2 + center.y;	
	//~ }
	public double getx(){
		return x;
	}
	public double gety(){
		return y;
	}
	
	public double DistanceEntrePoints(Point b){
		return Math.pow(Math.pow(this.y-b.y,2)+Math.pow(this.x-b.x,2),(double)1/(double)2);

	}
	public void rotation(Point centre, double theta){
		double x1 = centre.x;
		double y1 = centre.y;
		double d1 = Math.sqrt(Math.pow((x1-x),2) + Math.pow((y1-y),2));
		x =  d1 * Math.cos(theta) + x1;
		y =  d1 * Math.sin(theta) + y1;
	}
	
	public void rotateAround(Point center, double angle) {
		x = center.x + (Math.cos(Math.toRadians(angle)) * (x - center.x) - Math.sin(Math.toRadians(angle)) * (y - center.y));
		y = center.y + (Math.sin(Math.toRadians(angle)) * (x - center.x) + Math.cos(Math.toRadians(angle)) * (y - center.y));
	}
	//~ public void newpositionPIVOT(LinkedList<Liaison> ListeDeLiaison){
		//~ x = newx(ListeDeLiaison);
		//~ y = newy(ListeDeLiaison);
	
	//~ }
	
}
