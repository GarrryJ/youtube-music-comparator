//https://www.youtube.com/watch?v=zPGf4liO-KQ
package ru.verborum.youtubemusiccomparator;

import java.io.File;
import java.nio.file.Paths;

public class Comparator {
    
    private String pathToSave = Paths.get("").toAbsolutePath().toString();
    private File wavFile;
    private YouTubeLoader youTubeLoader = new YouTubeLoader(pathToSave);
    private SimilarityCalculator similarityCalculator;
    
    public Comparator(File file){
        this.wavFile = file;
        this.similarityCalculator = new SimilarityCalculator(this.wavFile);
    }
    
    public Comparator(String url) {
        this.wavFile = this.youTubeLoader.download(url, "original");
        this.similarityCalculator = new SimilarityCalculator(this.wavFile);
    }
    
    public void changePathToSave(String path) {
        this.pathToSave = path;
        this.youTubeLoader = new YouTubeLoader(pathToSave);
    }
    
    public float compareWith(String url) {
        File videoToCompare = youTubeLoader.download(url, "videoToCompare");
        float similarity = this.similarityCalculator.calculateSimilarity(videoToCompare);
        videoToCompare.delete();
        return similarity;
    }
    
}
