import org.apache.commons.lang3.*;
import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PT_lab6 {
    public static void main(String[] args) {
        String inputPath = "C:\\Users\\kosta\\IdeaProjects\\PT_lab6\\src\\main\\resources\\img\\input";
        String outputPath = "C:\\Users\\kosta\\IdeaProjects\\PT_lab6\\src\\main\\resources\\img\\output";



        ForkJoinPool pool = new ForkJoinPool(3);
        List<Path> files;
        Path source = Path.of(inputPath);
        try (Stream<Path> stream = Files.list(source)){
            files = stream.collect(Collectors.toList());
            Collection<Path> collection = new ArrayList<Path>(files);
            Stream<Path>  pathStream = collection.stream();
            long time = System.currentTimeMillis();
            try {
                pool.submit(() -> {
                    Stream<Pair<String, BufferedImage>> outputPairStream = pathStream.map(val -> {
                        String name = String.valueOf(val.getFileName());
                        BufferedImage image = null;
                        try {
                            image = ImageIO.read(val.toFile());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return Pair.of(name, image);
                    }).map(val -> {
                        BufferedImage image = new BufferedImage(val.getRight().getWidth(),
                                val.getRight().getHeight(),
                                val.getRight().getType());
                        for (int i = 0; i < val.getRight().getWidth(); i++) {
                            for (int j = 0; j < val.getRight().getHeight(); j++) {
                                int rgb = val.getRight().getRGB(i, j);
                                Color color = new Color(rgb);
                                int r = color.getRed();
                                int g = color.getGreen();
                                int b = color.getBlue();
                                Color outColor = new Color(r, b, g);
                                int outRgb = outColor.getRGB();
                                image.setRGB(i, j, outRgb);
                            }
                        }
                        return Pair.of(val.getLeft(), image);
                    }).parallel();
                    outputPairStream.forEach(val -> {
                        try {
                            ImageIO.write(val.getValue(), "jpg", new File(outputPath+"\\"+val.getKey()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }).get();
                System.out.println(System.currentTimeMillis() - time);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Unable to load images");
            e.printStackTrace();
        }
}
}

//6057
//4957

