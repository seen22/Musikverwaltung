public final class Song {
    
    private String title;
    private String genre;
    private String artist;
    private String album;
    private String path;

    
    public String getTitle() {
        return this.title;
    }
    public String getGenre() {
        return this.genre;
    }
    public String getArtist() {
        return this.artist;
    }
    public String getAlbum() {
        return this.album;
    }
    public String getPath() {
        return this.path;
    }    

    public Song (String _title, String _genre, String _artist, String _album, String _path) {
        this.title = _title;
        this.genre = _genre;
        this.artist = _artist;
        this.album = _album;
        this.path = _path;
    }

}