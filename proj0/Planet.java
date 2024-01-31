public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G = 6.67e-11;


    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
	double xxDist = this.xxPos - p.xxPos;
	double yyDist = this.yyPos - p.yyPos;
	return Math.sqrt(xxDist * xxDist + yyDist * yyDist);
    }

   public double calcForceExertedBy(Planet p){
        double distance = this.calcDistance(p);
        return G * this.mass * p.mass / (distance * distance);
   }

   public static double abs(double num) {
    	if(num < 0) return -num;
    	else return num;
   }

   public double calcForceExertedByX(Planet p) {
    	double F = this.calcForceExertedBy(p); 
    	double xxDist = abs(this.xxPos - p.xxPos);
    	double r = this.calcDistance(p);
    	double Fx = F * xxDist / r;
    	return Fx; 
   }

   public double calcForceExertedByY(Planet p) {
    	double F = this.calcForceExertedBy(p); 
    	double yyDist = abs(this.yyPos - p.yyPos); 
    	double r = this.calcDistance(p);
    	double Fy = F * yyDist / r; 
    	return Fy; 
   }

   public double calcNetForceExertedByX(Planet[] planets){
	double Fxt = 0;
	for(Planet p : planets){
	if (this.equals(p)) continue; 
	Fxt += this.calcForceExertedByX(p);
	}
	return Fxt;
   }


   public double calcNetForceExertedByY(Planet[] planets){
	double Fyt = 0;
	for(Planet p : planets){
	if (this.equals(p)) continue; 
	Fyt += this.calcForceExertedByY(p);
	}
	return Fyt;
   }

   public void update(double dt, double Fx, double Fy){
	double ax =  Fx / this.mass;
	double ay =  Fy / this.mass;
	this.xxVel = this.xxVel + dt * ax;
	this.yyVel = this.yyVel + dt * ay;
	this.xxPos += dt * xxVel;
	this.yyPos += dt * yyVel;

   }

   public void draw(){
	StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
   }

}
