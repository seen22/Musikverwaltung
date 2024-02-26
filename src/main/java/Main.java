import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.awt.event.*;
import static javax.swing.JOptionPane.showMessageDialog;




public class Main {
	static Playlist currentPlaylist;
	final static Gui gui = new Gui();

	static MusicPlayer musicPlayer = new MusicPlayer();


	
	// read all mp3 files in the resources folder
	private static void readAllSongs() throws Exception {
		Playlist allSongPlaylist = new Playlist(Const.ALL_SONGS_PLAYLIST);
		allSongPlaylist.addSongs(Mp3Util.getSongsFromFolder(Const.MP3_FOLDER));
		Mp3Util.playListToFile(allSongPlaylist);
	}

	private static File findFileOnPc(String statingFolder) {
		JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(statingFolder));
				int result = fileChooser.showOpenDialog(gui);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					return selectedFile; 
				} else {
					return null;
				}
	}

	
	// check if all songs playlist exists if yes displayit on the screen
		// otherwise read the mp3 files from resources and create the playlist file
	private static void onProgramStart() throws Exception {
		
		// check if the file that containes all songs exicts
		File allSongsPlaylist = new File (Const.PLAYLIST_FOLDER, Const.ALL_SONGS_PLAYLIST);
		if (!allSongsPlaylist.exists()) {
			// when it's the first start ever the need to be created
			readAllSongs();
		}
		String allSongsPathPlayList = Const.PLAYLIST_FOLDER + Const.ALL_SONGS_PLAYLIST;
		Playlist playlist = Mp3Util.fileToPlaylist(allSongsPathPlayList);
		displaySongs(playlist);
	}

	private static void displaySongs(Playlist playlist) {
		currentPlaylist = playlist;
		gui.getLabelCurrentName().setText(currentPlaylist.getName());
		System.out.println(currentPlaylist.getName());
		List<Song> songs = playlist.getSongs();
		gui.getTableModel().getDataVector().removeAllElements();
		gui.getTableModel().fireTableDataChanged();
		for (Song song : songs) {
			String[] songInfo = {song.getArtist(), song.getTitle(), song.getGenre(), song.getAlbum()};
			gui.getTableModel().addRow(songInfo);
		}
	}


	public static void main(String[] args) throws Exception {		
		onProgramStart();

		gui.getbtnChoosePlaylist().addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				File selectedFile = findFileOnPc(Const.PLAYLIST_FOLDER);
				if (selectedFile == null) {
					System.out.println("no file selected !");
					return;
				}
					try {
						Playlist playlist = Mp3Util.fileToPlaylist(selectedFile.toPath().toString());
						displaySongs(playlist);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			
		});

		gui.getBtnRemoveSong().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = gui.getTable().getSelectedRow();
				if (selectedRow >= 0) {
					currentPlaylist.deleteSong(selectedRow);
					try {
						Mp3Util.playListToFile(currentPlaylist);
						displaySongs(currentPlaylist);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});


		gui.getBtnAddSong().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File selectedFile = findFileOnPc(Const.HOME_DIR);
				if (selectedFile == null) {
					System.out.println("no file selected !");
					return;
				}
				try {
					String filePath = selectedFile.toPath().toString();
					Song newSong = Mp3Util.getSongInfos(filePath);
					currentPlaylist.addSong(newSong);
					Mp3Util.playListToFile(currentPlaylist);
					displaySongs(currentPlaylist);

				} catch (UnsupportedTagException e1) {
					e1.printStackTrace();
				} catch (InvalidDataException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});


		gui.getbtnMakePlaylist().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String playlistName = gui.getTextFieldPlaylistName().getText();
				boolean nameExicts = !playlistName.isBlank();

				int[] selectedRows = gui.getTable().getSelectedRows();
				boolean hasSelection = selectedRows.length > 0;

				if (nameExicts && hasSelection) {
					Playlist newPlaylist = new Playlist(playlistName);
					for (int rowIndex : selectedRows) {
						Song song = currentPlaylist.getSongFromPosition(rowIndex);
						newPlaylist.addSong(song);
					}
					try {
						Mp3Util.playListToFile(newPlaylist);
						displaySongs(newPlaylist);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					String msg = "make sure to enter palaylist name and songs are selected !";
					showMessageDialog(null, msg);
				}

			}
		});


		gui.getBtnSearch().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Playlist searResults = new Playlist("search results");
				String searchQuery = gui.getTextFieldSearch().getText().toLowerCase();
				if (!searchQuery.isBlank()) {
					for (Song song : currentPlaylist.getSongs()) {
						if (song.getTitle().toLowerCase().contains(searchQuery)){
							searResults.addSong(song);
							continue;
						} else if (song.getArtist().toLowerCase().contains(searchQuery)){
							searResults.addSong(song);
							continue;
						}  else if (song.getGenre().toLowerCase().contains(searchQuery)){
							searResults.addSong(song);
							continue;
						}   else if (song.getAlbum().toLowerCase().contains(searchQuery)){
							searResults.addSong(song);
							continue;
						}
					}
					if (searResults.getSongs().size() <= 0) {
						String msg = "no songs were found !";
						showMessageDialog(null, msg);
					} else {
						currentPlaylist = searResults;
						displaySongs(currentPlaylist);
					}
			
				} else {
					String msg = "enter search query !";
					showMessageDialog(null, msg);
				}

			}
		});


		gui.getBtnPlaySong().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = gui.getTable().getSelectedRow();
				if (index >= 0) {
					String path = currentPlaylist.getSongFromPosition(index).getPath();
					System.out.println(path);
					musicPlayer.setMyFile(new File (path));
					musicPlayer.playSong();

				}
				
			}
		});


		gui.getBtnStop().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				musicPlayer.stopSong();
			}
		});


		
		gui.setLayout(null);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to close an the end
		gui.setSize(1000, 500);// Determine the dimensions of the window
		gui.setLocation(600, 700);
		gui.setVisible(true); // to display the window on the computer screen
		gui.setLocationRelativeTo(null); //to show the windo in the middle of the screen
		gui.setResizable(false);
		
	}
}
