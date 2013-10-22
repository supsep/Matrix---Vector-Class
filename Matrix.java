//
//Sepehr Taheri 
//v00704838
//  CSC305 A2 Matrix Java
//


  public class Matrix {
    final int M;             // # of rows
    final int N;             // # of columns
    public double[][] data;  // M-by-N array

    //create M-by-N matrix of NULLS
    public Matrix() {
        M = 1;
        N = 3;
        data = new double[M][N];

    }
    // Create MxN Matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                    this.data[i][j] = 0;
                    
                }
        }

    }
    //Create Matrix from 2d array
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                    this.data[i][j] = data[i][j];
    }
    //Return the N-by-N identity matrix
    public static Matrix identity(int N) {
        Matrix I = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    // Print Matrix
    public void printMatrix() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) 
                System.out.printf("%9.4f ", data[i][j]);
            System.out.println();
        }
    }

    // Equality
    public boolean eq(Matrix B) {
       	Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.data[i][j] != B.data[i][j]) return false;
        return true;
    }
    //Returns A^transpose
    public Matrix transpose() {
            int Mtwo = this.M;
            int Ntwo = this.N;
            Matrix Atranspose = new Matrix(Ntwo, Mtwo);
            for (int i = 0; i < Mtwo; i++) {
                for (int j = 0; j < Ntwo; j++) {
                      Atranspose.data[j][i] = this.data[i][j];
                }
        }
        return Atranspose;
    }

    //Multiplies Matrix by scalar
    public Matrix scalarMultiply(double scalar) {

            Matrix C = this;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    C.data[i][j] =  this.data[i][j] * scalar ;
                }
        }
        return C;
    }
    /*Multiplies Matrix by another matrix
    public Matrix multiply(Matrix B) {
        Matrix A = this;
        if (A.M != B.N) throw new RuntimeException("Illegal matrix dimensions for Matrix Multiplication :");
            Matrix C = new Matrix(N, M);
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    C.data[i][j] = this.data[i][j] * B.data[i][j];
                }   
         }
        return C;
    }*/
       // Multiplies Matrix by another matrix (better version)
    public Matrix multiplyT(Matrix B) {
        Matrix A = this;
        int m_a = A.data.length;
        int n_a = A.data[0].length;
        int n_b = B.data[0].length;
        System.out.println(+m_a);
        System.out.println(+n_a);
        System.out.println(+n_b);


        Matrix C = new Matrix(m_a, n_b);
        for (int i = 0; i < m_a; i++) {
            for (int j = 0; j < n_b; j++) {
                for (int k = 0; k < n_a; k++) {
                    C.data[i][j] = C.data[i][j] +  this.data[i][k] * B.data[k][j];
                }   
         }
     }
        return C;
    }
    //Dot product of two Verticies ( must be 1 x M )
    public double dot(Matrix B) {
        Matrix A = this;
        Matrix C = B.transpose();
        double Product = 0;
        
            for (int i = 0; i < 2; i++) {
                    Product = Product+ (A.data[0][i]) * (C.data[i][0]);
                }
                return Product;

        }
    
    // Returns the inverse of 2x2, 3x3 Matrices
    public Matrix inverse(){
        Matrix A = this;
        Matrix Inverse = new Matrix(M, M);

        if(A.M != A.N) throw new RuntimeException("Illegal matrix dimensions for inverse - Require Square Matrix");

        else if ( A.M == 2) {
            double Determinant = ((A.data[0][0]*A.data[1][1]) - (A.data[0][1]*A.data[1][0]));
            Inverse.data[0][0] =  A.data[1][1];
            Inverse.data[0][1] = - A.data[0][1];
            Inverse.data[1][0] = - A.data[1][0];
            Inverse.data[1][1] = A.data[0][0];
            Inverse.scalarMultiply(1/Determinant);
            return Inverse;

        }
        else if ( A.M == 3){
            double Determinant = (A.data[0][0]*A.data[1][1]*A.data[2][2]) + (A.data[1][0]*A.data[2][1]*A.data[0][2]) +(A.data[2][0]*A.data[0][1]*A.data[1][2]) -(A.data[0][0]*A.data[2][1]*A.data[1][2]) -(A.data[2][0]*A.data[1][1]*A.data[0][2]) -(A.data[1][0]*A.data[0][1]*A.data[2][2]);
            System.out.println("det" + Determinant);
            Inverse.data[0][0] =  (A.data[1][1] * A.data[2][2]) - (A.data[1][2]*A.data[2][1]);
            Inverse.data[0][1] =  (A.data[0][2] * A.data[2][1]) - (A.data[0][1]*A.data[2][2]);
            Inverse.data[0][2] =  (A.data[0][1] * A.data[1][2]) - (A.data[0][2]*A.data[1][1]);
            Inverse.data[1][0] =  (A.data[1][2] * A.data[2][0]) - (A.data[1][0]*A.data[2][2]);
            Inverse.data[1][1] =  (A.data[0][0] * A.data[2][2]) - (A.data[0][2]*A.data[2][0]);
            Inverse.data[1][2] =  (A.data[0][2] * A.data[1][0]) - (A.data[0][0]*A.data[1][2]);
            Inverse.data[2][0] =  (A.data[1][0] * A.data[2][1]) - (A.data[1][1]*A.data[2][1]);
            Inverse.data[2][1] =  (A.data[0][1] * A.data[2][0]) - (A.data[0][0]*A.data[2][1]);
            Inverse.data[2][2] =  (A.data[0][0] * A.data[1][1]) - (A.data[0][1]*A.data[1][0]);
            Inverse.scalarMultiply(1/Determinant);
            return Inverse;

        }
        return Inverse;
    }

    // test cases
    public static void main(String[] args) {
        System.out.println("Matrix A");
        double[][] a = { { 1, 2, 3 }, { 4, 5, 6 }, { 9, 1, 3} };

        double[][] b = { { 12, 23, 35 }, { 87, 47, 6 }, { 34, 53, 3} };
        Matrix A = new Matrix(a);
        Matrix B = new Matrix(b);
        A.printMatrix();        
        System.out.println();
        System.out.println("Matrix B");
        
        B.printMatrix();    
        System.out.println();   
        System.out.println("A = B ? "); 
        System.out.println(A.eq(B));
        System.out.println();

        Matrix Ctranspose = A.transpose();
        System.out.println("Atranspose = ");
        Ctranspose.printMatrix();

        System.out.println();

        Matrix AtimesB = A.multiply(B);
        System.out.println("A*B= C");
        AtimesB.printMatrix();

        System.out.println();
        Matrix Atimesfive = A.scalarMultiply(5);
        System.out.println("A*5= ");
        Atimesfive.printMatrix();

        //System.out.println(A.dot(B)); Error

        double[][] e = { { 1, 2, 3 }};
        double[][] f = {{ 12, 23, 35 } };
        Matrix E = new Matrix(e);
        Matrix F = new Matrix(f);
        System.out.println();
        System.out.println("Matrix E");
        E.printMatrix();
        System.out.println();
        System.out.println("Matrix F");
        F.printMatrix();
        System.out.println();
        System.out.println("E dot product F");
        System.out.println(E.dot(F));

        double[][] i = {{ 1, 2}, {4, 6}};
        Matrix In = new Matrix(i);
        System.out.println();
        System.out.println("Matrix Q");
        System.out.println();
        In.printMatrix();
        System.out.println("Inverse of Q");
        Matrix InInverse = In.inverse();
        InInverse.printMatrix();

        double[][] z = {{ 1, 2, 3}, {4, 6 ,9 },{100, 21, 999}};
        Matrix In2 = new Matrix(z);
        System.out.println();
        System.out.println("Matrix Z");
        In2.printMatrix();

        System.out.println();
        System.out.println("Inverse of Z");
        Matrix InInverse2 = In2.inverse();
        InInverse2.printMatrix();

    }
 



















}