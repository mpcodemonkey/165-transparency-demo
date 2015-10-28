package utilities;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class BlendMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public BlendMenu() {
		setTitle("Blend Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.SOUTH);
		
		JButton btnApplyChanges = new JButton("Apply Changes");
		splitPane.setLeftComponent(btnApplyChanges);
		
		JButton btnExitMenu = new JButton("Exit Menu");
		splitPane.setRightComponent(btnExitMenu);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		ButtonGroup source = new ButtonGroup();
		ButtonGroup dest = new ButtonGroup();
		ButtonGroup test = new ButtonGroup();
		
		JLabel lblSourceFunction = new JLabel("Source Function");
		panel.add(lblSourceFunction, "4, 2");
		
		JLabel lblDestFunction = new JLabel("Dest Function");
		panel.add(lblDestFunction, "12, 2");
		
		JLabel lblTestFunction = new JLabel("Test Function");
		panel.add(lblTestFunction, "20, 2");
		
		JRadioButton sf_source = new JRadioButton("Source");
		panel.add(sf_source, "4, 4");
		
		JRadioButton df_src = new JRadioButton("Source");
		panel.add(df_src, "12, 4");
		
		JRadioButton tf_gt = new JRadioButton("Greater");
		panel.add(tf_gt, "20, 4");
		
		JRadioButton sf_dest = new JRadioButton("Dest");
		panel.add(sf_dest, "4, 6");
		
		JRadioButton df_dest = new JRadioButton("Dest");
		panel.add(df_dest, "12, 6");
		
		JRadioButton tf_e = new JRadioButton("Equal");
		panel.add(tf_e, "20, 6");
		
		source.add(sf_source);
		source.add(sf_dest);
		
		dest.add(df_src);
		dest.add(df_dest);
		
		test.add(tf_gt);
		test.add(tf_e);
		
		this.setVisible(false);
	}

}
