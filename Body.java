public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	private static final double G = 6.67e-11;
	public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body body) {
		xxPos = body.xxPos;
		yyPos = body.yyPos;
		xxVel = body.xxVel;
		yyVel = body.yyVel;
		mass = body.mass;
		imgFileName = body.imgFileName;
	}

	private double calcDistance(Body body) {
		double dx = this.xxPos - body.xxPos;
		double dy = this.yyPos - body.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	private double calcForceExertedBy(Body body) {
		double dist = this.calcDistance(body);
		return G * this.mass * body.mass / dist / dist;
	}

	private double calcForceExertedByX(Body body) {
		return -this.calcForceExertedBy(body) * (this.xxPos - body.xxPos) / this.calcDistance(body);
	}

	private double calcForceExertedByY(Body body) {
		return -this.calcForceExertedBy(body) * (this.yyPos - body.yyPos) / this.calcDistance(body);
	}

	public double calcNetForceExertedByX(Body[] bodies) {
		double res = 0;
		for (Body body : bodies) {
			if (!this.equals(body))
				res += this.calcForceExertedByX(body);
		}
		return res;
	}

	public double calcNetForceExertedByY(Body[] bodies) {
		double res = 0;
		for (Body body : bodies) {
			if (!this.equals(body))
				res += this.calcForceExertedByY(body);
		}
		return res;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel += dt * aX;
		this.yyVel += dt * aY;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}