package com.example.opencv_01;

import static android.os.Environment.getExternalStoragePublicDirectory;
import static org.opencv.imgproc.Imgproc.COLOR_RGBA2RGB;
import static org.opencv.imgproc.Imgproc.cvtColor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /*
     TODO 0. - Porizeni fotografie a detekce tvari pomoci knihovny OpenCV
       v projektu je jiz naimportovana knihovna ve verzi opencv-contrib:4.5.3.0
       (build.gradle implementation 'com.quickbirdstudios:opencv-contrib:4.5.3.0')
       Jednotlive kroky jsou v nasledujicich komentarich
    */

    ImageView imageView;
    private CascadeClassifier faceDetector;
    Bitmap gImageBitmap;

    private String [] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions(permissions, 200);
        if(!OpenCVLoader.initDebug()) {
            Log.d("OpenCV", "CANNOT LOAD OPENCV LIB");
        }else {
            initializeOpenCVDependencies();
            Log.d("OpenCV", "OPENCV LIB OK");
        }

        imageView = findViewById(R.id.imageView);
    }


    private void initializeOpenCVDependencies(){
        //vytvoreni faceDetector ze souboru res/raw/lbpcascade_frontalface.xml
        try{
            // Copy the resource into a temp file so OpenCV can load it
            InputStream is = getResources().openRawResource(R.raw.lbpcascade_frontalface);
            File cascadeDir = getDir("cascade", Context.MODE_APPEND);
            File mCascadeFile = new File(cascadeDir, "lbpcascade_frontalface.xml");
            FileOutputStream os = new FileOutputStream(mCascadeFile);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();
            // Load the cascade classifier
            faceDetector = new CascadeClassifier(mCascadeFile.getAbsolutePath());

            /*
            facemark = Face.createFacemarkLBF();
            InputStream isFaceMark = getResources().openRawResource(R.raw.lbfmodel);
            File faceMarkDir = getDir("facemark", Context.MODE_APPEND);
            File faceMarkFile = new File(faceMarkDir, "lbfmodel.yaml");
            FileOutputStream osFaceMark = new FileOutputStream(faceMarkFile);

            byte[] bufferFM = new byte[4096];
            int bytesReadFM;
            while ((bytesReadFM = isFaceMark.read(bufferFM)) != -1) {
                osFaceMark.write(bufferFM, 0, bytesReadFM);
            }
            isFaceMark.close();
            osFaceMark.close();

            facemark.loadModel(faceMarkFile.getAbsolutePath());
            */

            Log.e("OpenCV", "loading cascade OK");
        } catch (Exception e) {
            Log.e("OpenCV", "Error loading cascade", e);
        }

    }

    public void takePhoto(View view) {
        Toast.makeText(this, "TAKE PHOTO", Toast.LENGTH_SHORT).show();

        /* TODO 1. - zavolat aktivitu, ktera umozni pomoci kamery poridit fotku a poslat ji do metody onActivityResult
            je na vyber pouzit fotku v malem nebo velkem rozliseni (pro velke rozliseni je potreba slozitejsi zrojovy kod)
            pro presnejsi detekci tvari je vhodne udelat slozitejsi variantu
            detekce by mela fungovat i s fotkou v mensim rozliseni, pokud by vam slozitejsi varianta delala problem
            Navod na obe cesty naleznete zde:
            https://developer.android.com/training/camera/photobasics
        */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /* TODO 2. - pracovat s porizenou fotkou
            - nacist porizenou fotku do promenne gImageBitmap
            - zobrazit fotku (Bitmap) do imageView
         */

    }

    public void detectFaces(View view) {
        Toast.makeText(this, "FACE DETECTION", Toast.LENGTH_SHORT).show();
        /* TODO 3. - Detekce tvari
               - zavolat detekci tvari a zobrazit vyslednou detekci jako obdelnik kolem tvare s nahodnou barvou
               - vypsat pocet nalezenych tvari v obraze
               - napoveda: https://github.com/opencv/opencv/tree/master/samples/android/face-detection
               Jedna z moznych cest:
                1. konvertovat fotku z promenne typu Bitmap do Mat aby s porizenou fotkou mohla knihovna OpenCV pracovat
                   Mat photoMat = new Mat(gImageBitmap.getWidth(), gImageBitmap.getHeight(), CvType.CV_8UC4);
                   Utils.bitmapToMat(gImageBitmap, photoMat);
                2. obraz ve formatu Mat pouzit na vstup detektoru tvari z knihovny OpenCV
                   MatOfRect faceRects = new MatOfRect();
                   faceDetector.detectMultiScale(photoMat, faceRects)
                3. prochazet vystup detektoru tvari (obdelniky reprezentujici nalezene tvare v facesArray) vykreslit pomoci OpenCV do Mat
                   Rect[] facesArray = faceRects.toArray();
                   Imgproc.rectangle()
                4. Mat konvertovat zpet do Bitmap
                   Bitmap bm = Bitmap.createBitmap(photoMat.cols(), photoMat.rows(), Bitmap.Config.ARGB_8888);
                   Utils.matToBitmap(photoMat, bm);
                5. Bitmap zobrazit v ImageView

         */

        /* TODO 4. BONUS - Anonymizace/rozmazani tvari + ulozeni vysledku do galerie obrazku
         */
    }
}