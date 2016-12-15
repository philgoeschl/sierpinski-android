package at.fh.swengb.sierpinskitriangle

//import android.R
import android.graphics.{Bitmap, Canvas, Color, Paint}
import android.os.Bundle
import android.widget.ImageView
import android.support.v7.app.AppCompatActivity


class MainActivity extends AppCompatActivity {
  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    var paint: Paint = new Paint
    paint.setColor(Color.BLACK)
    paint.setStyle(Paint.Style.STROKE)
    var bitmap: Bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
    var canvas: Canvas = new Canvas(bitmap)
    var x: Float = 0
    var y: Float = 0
    var width: Float = bitmap.getWidth
    var halfWidth: Float = bitmap.getWidth / 2
    var height: Double = Math.sqrt((width * width) - (halfWidth * halfWidth))
    var depth: Int = 0

    Sierpinski(x, y, width, height.toFloat, depth, canvas, paint, bitmap)
  }

  private[sierpinskitriangle] def Sierpinski(x: Float, y: Float, width: Float, height: Float, depth: Int, canvas: Canvas, paint: Paint, bitmap: Bitmap) {
    drawSierpinski(x, y, width, height, canvas, paint, bitmap)
    if (depth == 7) return

    var newWidth: Float = width / 2
    var newHeight: Float = height / 2
    Sierpinski(x, y, newWidth, newHeight, depth+1, canvas, paint, bitmap)
    Sierpinski(x + newWidth / 2, y + newHeight, newWidth, newHeight, depth+1, canvas, paint, bitmap)
    Sierpinski(x + newWidth, y, newWidth, newHeight, depth+1, canvas, paint, bitmap)
  }

  private[sierpinskitriangle] def drawSierpinski(x: Float, y: Float, width: Float, height: Float, canvas: Canvas, paint: Paint, bitmap: Bitmap) {
    var imageView: ImageView = findViewById(R.id.imageView).asInstanceOf[ImageView]
    canvas.drawLine(x, y, x + width, y, paint)
    canvas.drawLine(x, y, x + (width / 2), y + height, paint)
    canvas.drawLine(x + width, y, x + (width / 2), y + height, paint)
    imageView.setImageBitmap(bitmap)
  }
}
