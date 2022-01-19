import java.lang.Math;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private Planet planet;
    private static final double G = 6.67e-11;
    

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;  
    }

    public double calcDistance(Planet p) {
        return Math.pow(Math.pow((p.xxPos - this.xxPos), 2) + Math.pow((p.yyPos - this.yyPos), 2), (double) 1/2);
    }

    public double calcForceExertedBy(Planet p) {
        return (G * this.mass * p.mass) / Math.pow(this.calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p) {
        return (p.xxPos - this.xxPos)/this.calcDistance(p) * this.calcForceExertedBy(p);
    }

    public double calcForceExertedByY(Planet p) {
        return (p.yyPos - this.yyPos)/this.calcDistance(p) * this.calcForceExertedBy(p);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceX = 0;
        for (Planet p : planets) {
            if (this.equals(p)) {
                continue;
            } else {
                netForceX = netForceX + this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceY = 0;
        for (Planet p : planets) {
            if (this.equals(p)) {
                continue;
            } else {
                netForceY = netForceY + this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel = xxVel + dt * aX;
        yyVel = yyVel + dt * aY;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, imgFileName);
    }

}