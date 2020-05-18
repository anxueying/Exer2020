package home.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author axy
 * day16 map课后练习 第2题
 */
public class FavoriteSinger {
    public static void main(String[] args) {
        HashMap<String,HashSet<String>> singerSong = new HashMap<>();
        singerSong.put("张学友",new HashSet<>());
        singerSong.get("张学友").add("《一路上有你》");
        singerSong.get("张学友").add("《吻别》");
        singerSong.get("张学友").add("《一千个伤心的理由》");


        singerSong.put("王菲",new HashSet<>());
        singerSong.get("王菲").add("《红豆》");
        singerSong.get("王菲").add("《传奇》");
        singerSong.get("王菲").add("《容易受伤的女人》");

        for (Map.Entry<String, HashSet<String>> entry : singerSong.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
