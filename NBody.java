class NBody {
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double R = readRadius(filename);
        Body[] bodies = readBodies(filename);
        int N = bodies.length;

        StdDraw.setScale(-R, R);
        StdDraw.clear();
        // StdDraw.picture(0, 0, "images/starfield.jpg");

        // for (int i = 0; i < N; i++) {
        //     bodies[i].draw();
        // }

        StdDraw.enableDoubleBuffering();
        
        double time = 0;
        while (time < T) {
            double[] xForces = new double[N];
            double[] yForces = new double[N];
            for (int i = 0; i < N; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < N; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < N; i++) {
                bodies[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", N);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", bodies[i].xxPos, bodies[i].yyPos,
                    bodies[i].xxVel, bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
    public static double readRadius(String filePath) {
        In in = new In(filePath);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }

    public static Body[] readBodies(String filePath) {
        In in = new In(filePath);
        int N = in.readInt();
        double R = in.readDouble();
        Body[] res = new Body[N];
        for (int i = 0; i < N; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            res[i] = new Body(xP, yP, xV, yV, m, img);
        }
        return res;
    }
}