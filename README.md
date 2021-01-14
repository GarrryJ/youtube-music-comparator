#### Library for compare .wav audiofile with audiofile from YouTube video by link. 

##### *How to use?*
For example:
```java
Comparator comparator = new Comparator("https://www.youtube.com/watch?v=zPGf4liO-KQ");
// or
// Comparator comparator = new Comparator(new File("<PATH_TO_WAV_FILE>"));
System.out.println(comparator.compareWith("https://www.youtube.com/watch?v=_MMBImhlu74"));
```
Here comparator loads wav file from original video and saves it in the Comparator class. In next line (where System.out.print is) comparator prints float similarity percent of these two audios. For example in this case output was "1.0" because in both links are the same but one is remastered version.
