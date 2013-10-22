//
//Sepehr Taheri 
//v00704838
//  CSC305 A2 3D point Java
//


public class Threedee extends Matrix {


    public Threedee() {

    }
    //Creates vector basec on 3x1 2d array
    public Threedee(double[][] data) {

        Matrix Point = new Matrix(data);
        this.data = new double[M][N];

        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                    this.data[i][j] = data[i][j];
            }
        }
     }
    // Print Vector
    public void printThreeDee(){
        this.printMatrix();
    }
    // Scale the Vector
    public void scale(double[][] scalar) {
        this.data[0][0] = this.data[0][0] * scalar[0][0] ;
        this.data[0][1] = this.data[0][1] * scalar[0][1] ;
        this.data[0][2] = this.data[0][2] * scalar[0][2] ;
 }
    // Move the vector
    public void translate(double[][] pos) {    
        this.data[0][0] = this.data[0][0] + pos[0][0] ;
        this.data[0][1] = this.data[0][1] + pos[0][1] ;
        this.data[0][2] = this.data[0][2] + pos[0][2] ;
 }
    // Rotate based on x/y/z input
     public Matrix rotate(String axis, double radians) {    
        Matrix Rotater = new Matrix(3, 3);
        Matrix Var = this;
        Var = Var.transpose();

        if(axis == "x"){
            Rotater.data[0][0] = 1;
            Rotater.data[0][1] = 0;
            Rotater.data[0][2] = 0;
            Rotater.data[1][0] = 0;
            Rotater.data[1][1] = Math.cos(radians);
            Rotater.data[1][2] = - Math.sin(radians);
            Rotater.data[2][0] = 0;
            Rotater.data[2][1] = Math.sin(radians);
            Rotater.data[2][2] = Math.cos(radians);
        }
        else if(axis == "y"){
            Rotater.data[0][0] = Math.cos(radians);
            Rotater.data[0][1] = 0;
            Rotater.data[0][2] = Math.sin(radians);
            Rotater.data[1][0] = 0;
            Rotater.data[1][1] = 1;
            Rotater.data[1][2] = 0;
            Rotater.data[2][0] = - Math.cos(radians);
            Rotater.data[2][1] = 0;
            Rotater.data[2][2] = Math.cos(radians);
        
        }
        else if(axis == "z"){
            Rotater.data[0][0] = Math.cos(radians);
            Rotater.data[0][1] = - Math.sin(radians);
            Rotater.data[0][2] = 0;
            Rotater.data[1][0] = Math.sin(radians);
            Rotater.data[1][1] = Math.cos(radians);
            Rotater.data[1][2] = 0;
            Rotater.data[2][0] = 0;
            Rotater.data[2][1] = 0;
            Rotater.data[2][2] = 1;

    }
        Var.printMatrix();
        Rotater.printMatrix();
      return Rotater.multiplyT(Var);
 }

    //Test cases
    public static void main(String[] args) {

        double[][] f = {{1 ,2 ,3}};
        Threedee Point = new Threedee(f);
        Point.printThreeDee();

        double[][] scalar = {{10,5,29}};
        Point.scale(scalar);
        Point.printThreeDee();

        double[][] pos = {{1,41,-25}};
        Point.translate(pos);
        Point.printThreeDee();

        Matrix Rotated = Point.rotate("z",1.5);
        Rotated.printMatrix();






            }
}