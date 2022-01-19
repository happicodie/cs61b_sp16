public class NBody {
    public static double readRadius(String dataFile) {
        In in = new In(dataFile);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String dataFile) {
        In in = new In(dataFile);
        int N = in.readInt();
        Planet[] planets = new Planet[N];
        in.readDouble();
        for (int i = 0; i < N; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = "images/" + in.readString();
            planets[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int N = planets.length;

        // sets up the universe, so it goes from (-radius, -radius) to (radius, radius)
        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        double t = 0;
        while (t <= T) {
            // clear the drawing canvas
            StdDraw.clear();
            // sets up the background image
            StdDraw.picture(0, 0, "images/starfield.jpg", 2*radius, 2*radius);
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            double[] xForces = new double[N];
            double[] yForces = new double[N];
            for (int i = 0; i < N; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < N; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            t += dt;
        }

        StdOut.printf("%d\n", N);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < N; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
