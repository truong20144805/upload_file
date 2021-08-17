package Service;

import Model.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicService {
    private static List<Music> musicList = new ArrayList<>();
    public void save(Music music){
        musicList.add(music);
    }


}
