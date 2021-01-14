package ru.verborum.youtubemusiccomparator;

import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.YoutubeException;
import com.github.kiulian.downloader.model.YoutubeVideo;
import com.github.kiulian.downloader.model.formats.AudioVideoFormat;
import com.github.kiulian.downloader.model.formats.Format;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class YouTubeLoader {

    private String path;
    private YoutubeDownloader downloader;

    public YouTubeLoader(String path) {
        this.path = path;
        downloader = new YoutubeDownloader();
    }

    public void convertMp4ToWav(File source, File output){
        Encoder forMusic = new Encoder();
        EncodingAttributes specifications = new EncodingAttributes();
        specifications.setFormat("wav");
        AudioAttributes a = new AudioAttributes();
        a.setVolume(256);
        a.setCodec("pcm_u8");
        specifications.setAudioAttributes(a);
        try{
            forMusic.encode(source, output, specifications);
            source.delete();
        }
        catch(EncoderException | IllegalArgumentException ex){
            ex.printStackTrace();
        }
    }

    public String getIdFromURL(String url) {
        if (url.indexOf('=') != -1)
            url = url.substring(url.indexOf('=') + 1);
        return url;
    }

    public File download(String url, String fileName) {
        String videoId = getIdFromURL(url);
        YoutubeVideo video = null;
        try {
            video = downloader.getVideo(videoId);
        } catch (YoutubeException e) {
            e.printStackTrace();
        }
        
        ///////////////////////////////////////
        System.out.println(video.details().title());
        
        List<AudioVideoFormat> videoWithAudioFormats = video.videoWithAudioFormats();
        File outputDir = new File(this.path);
        Format format = videoWithAudioFormats.get(0);
        File videoFile = null;
        try {
            videoFile = video.download(format, outputDir, fileName);
        } catch (IOException | YoutubeException e) {
            e.printStackTrace();
        }
        convertMp4ToWav(videoFile, new File(this.path + "/" + fileName + ".wav"));
        return new File(this.path + "/" + fileName + ".wav");
    }
}