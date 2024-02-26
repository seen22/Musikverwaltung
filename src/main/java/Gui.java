import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Gui extends JFrame {
	
	ImageIcon iconMusic;
	JLabel background;


	private DefaultTableModel tableModel = new DefaultTableModel();
	private JTable table = new JTable(tableModel);

	private JScrollPane tableScroller = new JScrollPane( table );


	private JButton btnPlaySong = new JButton("Play song");
	private JButton btnStop = new JButton("stop music");

	private JButton btnMakePlaylist = new JButton("new Playlist");
	private JButton btnSearch = new JButton("Search");
	private JButton btnAddSong = new JButton("add Song");
	private JButton btnRemoveSong = new JButton("remove Song");
	private JButton btnChoosePlaylist = new JButton("Choose Playlist");

	private JLabel labelCurrentName = new JLabel("");
	

	private JTextField textFieldPlaylistName = new JTextField();
	private JTextField textFieldSearch = new JTextField();



    static JLabel labelTotal = new JLabel("");

	public JTextField getTextFieldSearch() {
		return this.textFieldSearch;
	}

	public ImageIcon getIconMusic() {
		return this.iconMusic;
	}

	public DefaultTableModel getTableModel() {
		return this.tableModel;
	}


	public JTable getTable() {
		return this.table;
	}


	public JScrollPane getTableScroller() {
		return this.tableScroller;
	}


	public JLabel getLabelCurrentName() {
		return this.labelCurrentName;
	}

	public JTextField getTextFieldPlaylistName() {
		return this.textFieldPlaylistName;
	}


	public JButton getbtnMakePlaylist() {
		return this.btnMakePlaylist;
	}


	public JButton getBtnSearch() {
		return this.btnSearch;
	}

	public JButton getBtnAddSong() {
		return this.btnAddSong;
	}


	public JButton getBtnRemoveSong() {
		return this.btnRemoveSong;
	}

	public JButton getBtnPlaySong() {
		return this.btnPlaySong;
	}
	public JButton getBtnStop() {
		return this.btnStop;
	}


	public JButton getbtnChoosePlaylist() {
		return this.btnChoosePlaylist;
	}


    public Gui() {
    	super("Musikverwaltung");
		
		// einfuegen Background Bild
		iconMusic = new ImageIcon (Const.RES_FOLDER + "iStock.jpg");
		background= new JLabel(iconMusic);
		background.setBounds(0, 0, 1000, 700);
		
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		getColorModel();
		tableModel.addColumn("Artist");
		tableModel.addColumn("Titel");
		tableModel.addColumn("Genre");
		tableModel.addColumn("Album");

      
      // Einfuegen die Deminsional


	  btnPlaySong.setBounds(350, 260, 100, 30);
	  btnStop.setBounds(500, 260, 100, 30);
      tableScroller.setBounds(350, 40, 500, 200);
      labelCurrentName.setBounds(350, 5, 200, 50);
	  labelCurrentName.setForeground(Color.white);
      btnChoosePlaylist.setBounds(20, 120, 250, 30);
      btnAddSong.setBounds(20, 160, 110, 30);
      btnRemoveSong.setBounds(140, 160, 115, 30);
      btnMakePlaylist.setBounds(20, 280, 115, 30);  
      textFieldPlaylistName.setBounds(140, 280, 100, 30);
	  textFieldSearch.setBounds(140, 320, 100, 30);
      btnSearch.setBounds(20, 320, 115, 30);
      labelTotal.setBounds(350, 230, 100, 30);
      

      // Einfuegen zu JFrame
	  add(btnPlaySong);
	  add(btnStop);
      add(tableScroller); 
      add(background);
      add(textFieldPlaylistName);
      add(labelCurrentName);
      add(btnChoosePlaylist);
      add(btnAddSong);
      add(btnRemoveSong);
      add(btnMakePlaylist);
	  add(textFieldSearch);
      add(btnSearch);
      add(labelTotal);
      add(background);      
	}
}