public class RandomPoint {
    private static final double EARTHRAIDUS = 6371;         //地球半径
    public double startlat = Math.toRadians(39.90601);      //起始点纬度(弧度表示)
    public double startlon = Math.toRadians(116.387909);    //起始点经度(弧度表示)
    public double maxdist = 30;                             //单次最大行驶距离


    public RandomPoint() {
    }

    public RandomPoint(double startlat, double startlon) {
        this.startlat = startlat;
        this.startlon = startlon;
    }

    public RandomPoint(double startlat, double startlon, double maxdist) {
        this.startlat = startlat;
        this.startlon = startlon;
        this.maxdist = maxdist;
    }

    public void setStartlat(double startlat) {
        this.startlat = startlat;
    }

    public void setStartlon(double startlon) {
        this.startlon = startlon;
    }

    /**
     * φ2 = asin( sin φ1 ⋅ cos δ + cos φ1 ⋅ sin δ ⋅ cos θ )
     * λ2 = λ1 + atan2( sin θ ⋅ sin δ ⋅ cos φ1, cos δ − sin φ1 ⋅ sin φ2 )
     * where φ is latitude, λ is longitude, θ is the bearing (clockwise from north), δ is the angular distance d/R;
     * d being the distance travelled, R the earth’s radius
     *
     * @return [lat, lon, dist, brg]
     */
    public double[] generatePoint(double rand1, double rand2) {
        // Convert maximum distance to radians.
        double v = this.maxdist / EARTHRAIDUS;
        // Compute a random distance from 0 to v
        double dist = Math.acos(rand1 * (Math.cos(v) - 1) + 1);
        // Compute a random bearing from 0 to 2*PI radians
        double brg = 2 * Math.PI * rand2;
        // Calculate the coordinates of the final random point.
        double lat = Math.asin(Math.sin(startlat) * Math.cos(dist) + Math.cos(startlat) * Math.sin(dist) * Math.cos(brg));
        double lon = startlon +
                Math.atan2(Math.sin(brg) * Math.sin(dist) * Math.cos(startlat),
                        Math.cos(dist) - Math.sin(startlat) * Math.sin(lat));
        if (lon < -Math.PI) lon += 2 * Math.PI;
        if (lon > Math.PI) lon -= 2 * Math.PI;
        // 存入数组
        double[] point = new double[]{Math.toDegrees(startlat), Math.toDegrees(startlon),
                Math.toDegrees(lat), Math.toDegrees(lon), dist * EARTHRAIDUS, Math.toDegrees(brg)};
        // 更新当前经纬度
        setStartlat(lat);
        setStartlon(lon);
        return point;
    }
}

