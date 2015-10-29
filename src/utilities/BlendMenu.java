package utilities;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import transparencyDemoGame.TransparencyDemo;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.awt.event.ActionEvent;

public class BlendMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TransparencyDemo fix;

	/**
	 * Create the frame.
	 */
	public BlendMenu(TransparencyDemo td) {
		fix = td;
		setTitle("Blend Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
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
		sf_source.setSelected(true);
		panel.add(sf_source, "4, 4");
		
		JRadioButton df_src = new JRadioButton("Source");
		df_src.setSelected(true);
		panel.add(df_src, "12, 4");
		
		JRadioButton tf_gt = new JRadioButton("Greater");
		tf_gt.setSelected(true);
		panel.add(tf_gt, "20, 4");
		
		JRadioButton sf_dest = new JRadioButton("Dest");
		panel.add(sf_dest, "4, 6");
		
		
		JRadioButton df_dest = new JRadioButton("Dest");
		panel.add(df_dest, "12, 6");
		
		JRadioButton tf_e = new JRadioButton("Equal");
		panel.add(tf_e, "20, 6");
		
		
		JRadioButton rdbtnConstalpha = new JRadioButton("ConstAlpha");
		panel.add(rdbtnConstalpha, "4, 8");
		
		JRadioButton df_const = new JRadioButton("ConstAlpha");
		panel.add(df_const, "12, 8");
		
		JRadioButton tf_gOrE = new JRadioButton("GreaterOrEqual");
		panel.add(tf_gOrE, "20, 8");
		
		JRadioButton rdbtnConstcolor = new JRadioButton("ConstColor");
		panel.add(rdbtnConstcolor, "4, 10");
		
		JRadioButton df_const_color = new JRadioButton("ConstColor");
		panel.add(df_const_color, "12, 10");
		
		JRadioButton tf_always = new JRadioButton("Always");
		panel.add(tf_always, "20, 10");
		
		JRadioButton destColorRadioButton = new JRadioButton("DestColor");
		panel.add(destColorRadioButton, "4, 12");
		
		JRadioButton df_source_color = new JRadioButton("SourceColor");
		panel.add(df_source_color, "12, 12");
		
		JRadioButton tf_lt = new JRadioButton("LessThan");
		panel.add(tf_lt, "20, 12");
		
		JRadioButton sf_one = new JRadioButton("One");
		panel.add(sf_one, "4, 14");
		
		JRadioButton df_one = new JRadioButton("One");
		panel.add(df_one, "12, 14");
		
		JRadioButton tf_lOrE = new JRadioButton("LessOrEqual");
		panel.add(tf_lOrE, "20, 14");
		
		JRadioButton sf_one_min_const_alpha = new JRadioButton("OneMinConstAlpha");
		panel.add(sf_one_min_const_alpha, "4, 16");
		
		JRadioButton df_one_min_const_alpha = new JRadioButton("OneMinConstAlpha");
		panel.add(df_one_min_const_alpha, "12, 16");
		
		JRadioButton tf_never = new JRadioButton("Never");
		panel.add(tf_never, "20, 16");
		
		JRadioButton sf_one_min_const_col = new JRadioButton("OneMinConstCol");
		panel.add(sf_one_min_const_col, "4, 18");
		
		JRadioButton df_one_min_const_color = new JRadioButton("OneMinConstCol");
		panel.add(df_one_min_const_color, "12, 18");
		
		JRadioButton tf_notEqual = new JRadioButton("NotEqual");
		panel.add(tf_notEqual, "20, 18");
		
		JRadioButton sf_one_min_dest_alpha = new JRadioButton("OneMinDestAlpha");
		panel.add(sf_one_min_dest_alpha, "4, 20");
		
		JRadioButton df_one_min_dest_alpha = new JRadioButton("OneMinDestAlpha");
		panel.add(df_one_min_dest_alpha, "12, 20");
		
		JRadioButton sf_one_minus_dest_col = new JRadioButton("OneMinDestCol");
		panel.add(sf_one_minus_dest_col, "4, 22");
		
		JRadioButton df_one_min_src_alpha = new JRadioButton("OneMinSrcAlpha");
		panel.add(df_one_min_src_alpha, "12, 22");
		
		JRadioButton sf_one_min_src_alpha = new JRadioButton("OneMinSrcAlpha");
		panel.add(sf_one_min_src_alpha, "4, 24");
		
		JRadioButton df_one_min_src_col = new JRadioButton("OneMinSrcCol");
		panel.add(df_one_min_src_col, "12, 24");
		
		JRadioButton sf_src_alpha_sat = new JRadioButton("SrcAlphaSaturate");
		panel.add(sf_src_alpha_sat, "4, 26");
		
		JRadioButton df_zero = new JRadioButton("Zero");
		panel.add(df_zero, "12, 26");
		
		JRadioButton sf_zero = new JRadioButton("Zero");
		panel.add(sf_zero, "4, 28");
		source.add(sf_zero);
		
		source.add(sf_source);
		source.add(sf_dest);
		source.add(rdbtnConstalpha);
		source.add(rdbtnConstcolor);
		source.add(destColorRadioButton);
		source.add(sf_one);
		source.add(sf_one_min_const_alpha);
		source.add(sf_one_min_const_col);
		source.add(sf_one_min_dest_alpha);
		source.add(sf_one_minus_dest_col);
		source.add(sf_one_min_src_alpha);
		source.add(sf_src_alpha_sat);
		source.add(sf_zero);
		
		
		dest.add(df_src);
		dest.add(df_dest);
		dest.add(df_const);
		dest.add(df_const_color);
		dest.add(df_source_color);
		dest.add(df_one);
		dest.add(df_one_min_const_alpha);
		dest.add(df_one_min_const_color);
		dest.add(df_one_min_dest_alpha);
		dest.add(df_one_min_src_alpha);
		dest.add(df_one_min_src_col);
		dest.add(df_zero);

		
		test.add(tf_gt);
		test.add(tf_e);
		test.add(tf_gOrE);
		test.add(tf_always);
		test.add(tf_lt);
		test.add(tf_lOrE);
		test.add(tf_never);
		test.add(tf_notEqual);
		

		
		this.setVisible(false);
		
		//create action listeners
		btnApplyChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int selectedSourceFunction = 1;
				int selectedDestFunction = 1;
				int selectedTestFunction = 1;
				
				Enumeration<AbstractButton> sb = source.getElements();
				while(sb.hasMoreElements()){
					JRadioButton jr = (JRadioButton) sb.nextElement();
					if(jr.isSelected()){
						break;
					}
					selectedSourceFunction++;
				}
				
				Enumeration<AbstractButton> db = dest.getElements();
				while(db.hasMoreElements()){
					JRadioButton jr = (JRadioButton) db.nextElement();
					if(jr.isSelected()){
						break;
					}
					selectedDestFunction++;
				}
				
				Enumeration<AbstractButton> tb = test.getElements();
				while(tb.hasMoreElements()){
					JRadioButton jr = (JRadioButton) tb.nextElement();
					if(jr.isSelected()){
						break;
					}
					selectedTestFunction++;
				}
				
				fix.alterBlendState(selectedSourceFunction, selectedDestFunction, selectedTestFunction);
			}
		});
		
	}

}
