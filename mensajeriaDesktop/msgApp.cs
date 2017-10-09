using System;
using System.Drawing;
using System.Windows.Forms;


public class FormMsg : Form
{
	private Button btnSearch;
	private Button btnApache;
    private Label lblMsg;
    private TextBox tbox;
	private Label lblTitle;
	
	private FormMsg()
	{
		// Force the entire form to repaint when it is resized.
		SetStyle(ControlStyles.ResizeRedraw, true);

		// form properties.
		Size = new Size(300, 300);
		Text = "Mensajeria";
        CenterToScreen();

		//Buttons
		btnSearch = new Button();
		btnSearch.Text = "Buscar G";
		btnSearch.Location = new Point(70, 60);
		Controls.Add(btnSearch);
		
		btnApache = new Button();
		btnApache.Text = "Buscar A";
		btnApache.Location = new Point(160, 60);
		Controls.Add(btnApache);
		
		// Label
		lblMsg = new Label();
		lblMsg.Text = "Digíta un código para buscar su mensaje...";
		lblMsg.BackColor = Color.White;
        lblMsg.Location = new Point(50, 150); 
        lblMsg.Size = new Size(200, 100);
		Controls.Add(lblMsg);

        lblTitle = new Label();        
        lblTitle.Location = new Point(50, 120); 
        lblTitle.AutoSize = true;
		Controls.Add(lblTitle);        

        //TextBox
        tbox = new TextBox();
        tbox.Parent = this;
        tbox.Location = new Point(50, 30);
  		tbox.BackColor = Color.White;
        tbox.Size = new Size(200, 50);

		//Events
        btnSearch.Click += new EventHandler(SearchMsg);
        btnApache.Click += new EventHandler(SearchApache);
	}    
#region Buscar
	
	private void SearchMsg(Object sender, EventArgs e)
	{
	        this.lblMsg.Text = webSrvGlassFish(this.tbox.Text);	
			this.lblTitle.Text = "Mensaje N°: " + this.tbox.Text;			
	}
	
	private void SearchApache(Object sender, EventArgs e)
	{
	        this.lblMsg.Text = webSrvApache(this.tbox.Text);	
			this.lblTitle.Text = "Mensaje N°: " + this.tbox.Text;			
	}
#endregion

#region Conexion	
	
	private String webSrvGlassFish(string id){
		try
		{
			websrvSearch srvSearch = new websrvSearch ();
			String msg = srvSearch.searchMsg (id);
			return msg;
		}
		catch (System.Exception)
		{
			this.lblMsg.Text = "No se pudo conectar con el servidor.";	
			this.lblTitle.Text = "Error";
			throw;
		}
	} 
	
	private String webSrvApache(string id){
		try
		{
			websrvMensaje srvSearchApache = new websrvMensaje ();
			String msg = srvSearchApache.searchMsg (id);
			return msg;
		}
		catch (System.Exception)
		{
			this.lblMsg.Text = "No se pudo conectar con el servidor.";	
			this.lblTitle.Text = "Error";
			throw;
		}
	} 
#endregion

	public static void Main(String[] args)
	{
		FormMsg form = new FormMsg();
		Application.Run(form);
	}
}

 

