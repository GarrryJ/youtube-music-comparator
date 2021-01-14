package ru.verborum.youtubemusiccomparator;

import com.musicg.fingerprint.FingerprintManager;
import com.musicg.fingerprint.FingerprintSimilarity;
import com.musicg.fingerprint.FingerprintSimilarityComputer;
import com.musicg.wave.Wave;

import java.io.File;

public class SimilarityCalculator {
    private byte[] mysteriousSongFingerprint;

    public SimilarityCalculator(File mysteriousSongFile) {
        this.mysteriousSongFingerprint = new FingerprintManager().extractFingerprint(new Wave(mysteriousSongFile.getPath()));
    }

    public float calculateSimilarity(File checkFile) {
        byte[] fingerPrint = new FingerprintManager().extractFingerprint(new Wave(checkFile.getPath()));
        FingerprintSimilarity fingerprintSimilarity = new FingerprintSimilarityComputer(mysteriousSongFingerprint, fingerPrint).getFingerprintsSimilarity();
        return (float)fingerprintSimilarity.getSimilarity();
    }
}
