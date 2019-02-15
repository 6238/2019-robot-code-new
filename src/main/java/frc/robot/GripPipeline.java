package frc.robot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import org.opencv.core.*;
import org.opencv.core.Core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;

/**
 * GripPipeline class.
 *
 * <p>
 * An OpenCV pipeline generated by GRIP.
 *
 * @author GRIP
 */
public class GripPipeline{

    // Outputs
    private ArrayList<Line> findLinesOutput = new ArrayList<Line>();

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    /**
     * This is the primary method that runs the entire pipeline and updates the
     * outputs.
     */
    public void process(Mat source0) {
        // Step Find_Lines0:
        Mat findLinesInput = source0;
        findLines(findLinesInput, findLinesOutput);

    }

    /**
     * This method is a generated getter for the output of a Find_Lines.
     * 
     * @return ArrayList<Line> output from Find_Lines.
     */
    public ArrayList<Line> findLinesOutput() {
        return findLinesOutput;
    }

    /**
     * Finds all line segments in an image.
     * 
     * @param input    The image on which to perform the find lines.
     * @param lineList The output where the lines are stored.
     */
    private void findLines(Mat input, ArrayList<Line> lineList) {
        final LineSegmentDetector lsd = Imgproc.createLineSegmentDetector();
        final Mat lines = new Mat();
        lineList.clear();
        if (input.channels() == 1) {
            lsd.detect(input, lines);
        } else {
            final Mat tmp = new Mat();
            Imgproc.cvtColor(input, tmp, Imgproc.COLOR_BGR2GRAY);
            lsd.detect(tmp, lines);
        }
        if (!lines.empty()) {
            for (int i = 0; i < lines.rows(); i++) {
                lineList.add(new Line(lines.get(i, 0)[0], lines.get(i, 0)[1], lines.get(i, 0)[2], lines.get(i, 0)[3]));
            }
        }
    }

}