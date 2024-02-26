import java.util.ArrayList;
import java.util.List;

public final class Playlist {

    private String name;
    private List<Song> songs;

    public Playlist (String _name) {
        this.name = _name;
        songs = new ArrayList<Song>();
    }

    public String getName() {
        return this.name;
    }

    public List<Song> getSongs() {
        return this.songs;
    }

    public Song getSongFromPosition(int position) {
        if(position >= 0 || position < songs.size() ) {
            return this.songs.get(position);
        } else {
            return null;
        }
    }

    public void addSong(Song mp3File) {
        songs.add(mp3File);
    }

    public void addSongs(List<Song> mp3Files) {
        for (Song mp3File : mp3Files) {
            songs.add(mp3File);
        }
    }

    public void deleteAllSongs() {
        songs = new ArrayList<Song>();
    }

    public boolean deleteSong (Song mp3File) {
        return songs.remove(mp3File);
    }

    public boolean deleteSong (int index) {
        Song deleted = songs.remove(index);
        if (deleted != null) {
            return true;
        } else {
            return false;
        }
    }


    
}
