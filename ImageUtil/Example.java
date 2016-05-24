//package ImageUtil;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
///**
// *
// * @author James
// */
//public class Example {
//    public static void main(String[] args) {
//        String imgLocation = (args.length == 1 ? args[0] : null);
//        if (imgLocation == null)
//            throw new IllegalArgumentException("One argument required: path-to-image");
//
//        try {
//            CroppedImage img = null;
//            if (imgLocation.startsWith("http")) {
//                //read the image from a URL
//                img = ImageLoader.fromUrl(new URL(imgLocation));
//            }
//            else {
//                File f = new File(imgLocation);
//                if (!f.exists() || !f.isFile())
//                    throw new IllegalArgumentException("Invalid path to image");
//                else {
//                    //read the image from a file
//                    img = ImageLoader.fromFile(f);
//                }
//            }
//
//            //output source type
//            System.out.println("Image source type: "+ img.getSourceType());
//            //output dimensions
//            System.out.println("Image dimensions: "+ img.getWidth() +"x"+ img.getHeight());
//
//            //crop it
//            CroppedImage cropped = img.crop(200, 200, 500, 350);
//            cropped.dispose();
//
//            //resize
//            CroppedImage resized = img.getResizedToWidth(400);
//            //save it with varying softness and quality
//            softenAndSave(resized, 0.95f, 0f);
//            softenAndSave(resized, 0.95f, 0.1f);
//            softenAndSave(resized, 0.95f, 0.2f);
//            softenAndSave(resized, 0.95f, 0.3f);
//            softenAndSave(resized, 0.8f, 0.08f);
//            softenAndSave(resized, 0.6f, 0.08f);
//            softenAndSave(resized, 0.4f, 0.08f);
//            resized.dispose();
//            
//            //write a 0.95 quality JPG without using Sun's JPG codec
//            resized.writeToFile(new File("resized--q0.95--s0.0--nocodec.jpg"));
//
//            //resize it to a square with different settings for edge cropping
//            squareIt(img, 400, 0.0, 0.95f, 0.08f);
//            squareIt(img, 400, 0.1, 0.95f, 0.08f);
//            squareIt(img, 400, 0.2, 0.95f, 0.08f);
//
//            //small thumbs
//            squareIt(img, 50, 0.0, 0.95f, 0.08f);
//            squareIt(img, 50, 0.1, 0.95f, 0.08f);
//            squareIt(img, 50, 0.1, 0.5f, 0.08f);            
//        }
//        catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//
//    }
////
////    private static void softenAndSave(CroppedImage img, float quality, float soften) throws IOException {
////        img.soften(soften).writeToJPG(new File("resized--q"+ quality +"--s"+ soften +".jpg"), quality);
////    }
////
////    private static void squareIt(CroppedImage img, int width, double cropEdges, float quality, float soften) throws IOException {
////        CroppedImage square = img.getResizedToSquare(width, cropEdges).soften(soften);
////        square.writeToJPG(new File("square--w"+ width +"--e"+ cropEdges +"--q"+ quality +".jpg"), quality);
////        square.dispose();
////    }
//
//}
