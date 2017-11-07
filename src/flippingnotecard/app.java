//package flippingNoteCard;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.PrintStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.GroupLayout;
//import javax.swing.GroupLayout.Alignment;
//import javax.swing.GroupLayout.ParallelGroup;
//import javax.swing.GroupLayout.SequentialGroup;
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JLabel;
//import javax.swing.JLayeredPane;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JProgressBar;
//import javax.swing.JScrollPane;
//import javax.swing.JTextField;
//import javax.swing.LayoutStyle;
//import javax.swing.LayoutStyle.ComponentPlacement;
//import javax.swing.UIManager;
//import javax.swing.UIManager.LookAndFeelInfo;
//
//public class app extends javax.swing.JFrame
//{
//  Course course = new Course();
//  Content content = new Content();
//  flipCard fc = new flipCard();
//  private JLabel ContentEditCourseName;
//  private JButton ContentSaveExit;
//  private JPanel EditContentList;
//  private JPanel ListEditPanel;
//  private JButton backListBtn;
//  
//  public app()
//  {
//    initComponents();
//    setTitle("Note Card Flipping");
//    menu();
//  }
//  
//  private JLabel backgroupLabel;
//  private JLabel cardBottomLabel;
//  
//  public void menu()
//  {
//    ArrayList<String> arl = this.course.showCourse();
//    this.mainPage.removeAll();
//    for (final String courseName : arl) {
//      JButton j = new JButton(courseName);
//      j.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//          app.this.courseList(courseName);
//          app.this.controlPanel(1);
//        }
//      });
//      this.mainPage.add(j);
//    }
//    this.mainPage.revalidate();
//    this.mainPage.repaint();
//    controlPanel(2);
//  }
//  
//  private JLabel cardTopLabel;
//  private JPanel cardView;
//  private JButton contentAdd;
//  public void courseList(String courseName)
//  {
//    this.listPanel.removeAll();
//    final HashMap<String, String> arl = this.content.showContent(courseName);
//    for (final String listHead : arl.keySet()) {
//      JButton j = new JButton(listHead);
//      j.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//          app.this.controlPanel(5);
//          app.this.cardTopLabel.setText(listHead);
//          app.this.cardBottomLabel.setText((String)arl.get(listHead));
//          app.this.jProgressBar1.setVisible(false);
//          app.this.nextCardBtn.setVisible(false);
//          app.this.backListBtn.setVisible(true);
//          app.this.flipAnswerBtn.setVisible(false);
//        }
//      });
//      this.listPanel.add(j);
//    }
//    this.listPanel.revalidate();
//    this.listPanel.repaint();
//    this.fc.setMap(arl);
//  }
//  
//  public void controlPanel(int panelID)
//  {
//    reset();
//    switch (panelID)
//    {
//    case 1: 
//      this.listView.setVisible(true);
//      break;
//    case 2:  this.mainPage.setVisible(true);
//      break;
//    case 3:  this.editCourse.setVisible(true);
//      break;
//    case 4:  this.editList.setVisible(true);
//      break;
//    case 5:  this.cardView.setVisible(true);
//    }
//  }
//  
//  public void editMenu()
//  {
//    this.editCourseListPanel.removeAll();
//    ArrayList<String> arl = this.course.showCourse();
//    for (String courseName : arl) {
//      JCheckBox j = new JCheckBox(courseName);
//      this.editCourseListPanel.add(j);
//    }
//    this.editCourseListPanel.revalidate();
//    this.editCourseListPanel.repaint();
//  }
//  
//  public void editListCourseSide()
//  {
//    this.courseEditPanel.setVisible(true);
//    this.jScrollPane3.setVisible(true);
//    ArrayList<String> arl = this.course.showCourse();
//    this.courseConfirmList.removeAll();
//    for (final String courseName : arl) {
//      JButton j = new JButton(courseName);
//      j.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//          app.this.courseEditPanel.setVisible(false);
//          app.this.jScrollPane3.setVisible(false);
//          app.this.ListEditPanel.setVisible(true);
//          app.this.jScrollPane4.setVisible(true);
//          app.this.editListSide(courseName);
//        }
//      });
//      this.courseConfirmList.add(j);
//    }
//    this.courseConfirmList.revalidate();
//    this.courseConfirmList.repaint();
//  }
//  
//  public void editListSide(String courseName)
//  {
//    HashMap<String, String> arl = this.content.showContent(courseName);
//    this.EditContentList.removeAll();
//    for (String ListHead : arl.keySet()) {
//      JCheckBox j = new JCheckBox(ListHead);
//      this.EditContentList.add(j);
//    }
//    this.ContentEditCourseName.setText(courseName);
//    this.EditContentList.validate();
//    this.EditContentList.repaint();
//    this.fc.setMap(arl);
//  }
//  
//  public void randomSelectedCourseStarter()
//  {
//    this.fc.initRandomListMap();
//    ArrayList rSelectedList = this.fc.getIndexedMap();
//    this.cardTopLabel.setText((String)rSelectedList.get(this.fc.getCounter()));
//    this.jProgressBar1.setMaximum(rSelectedList.size());
//    this.jProgressBar1.setValue(this.fc.getCounter() + 1);
//  }
//  
//  public void nextCard()
//  {
//    this.fc.setCounter();
//    ArrayList e = this.fc.getIndexedMap();
//    this.jProgressBar1.setValue(this.fc.getCounter() + 1);
//    this.cardTopLabel.setText((String)e.get(this.fc.getCounter()));
//    this.cardBottomLabel.setText("");
//    if (this.fc.getCounter() == e.size() - 1) {
//      this.nextCardBtn.setVisible(false);
//    }
//  }
//  
//  public void flipAnswer(int counter)
//  {
//    ArrayList rSelectedList = this.fc.getIndexedMap();
//    this.cardTopLabel.setText((String)rSelectedList.get(this.fc.getCounter()));
//    this.cardBottomLabel.setText(((String)this.fc.getListMap().get(rSelectedList.get(counter))).toString());
//  }
//  
//  public void pickSelectedDelete(JPanel panel)
//  {
//    String result = "";
//    if (panel == this.editCourseListPanel) {
//      for (java.awt.Component c : panel.getComponents()) {
//        if (c.getClass().equals(JCheckBox.class)) {
//          JCheckBox jcb = (JCheckBox)c;
//          if (jcb.isSelected()) {
//            System.err.println(jcb.getText());
//            this.course.deleteCourse(jcb.getText());
//            result = result + jcb.getText() + ", ";
//          }
//        }
//      }
//      result = result + "deleted";
//      JOptionPane.showMessageDialog(null, result);
//    } else if (panel == this.EditContentList)
//    {
//      for (java.awt.Component c : panel.getComponents()) {
//        if (c.getClass().equals(JCheckBox.class)) {
//          JCheckBox jcb = (JCheckBox)c;
//          if (jcb.isSelected()) {
//            System.err.println(jcb.getText());
//            this.content.deleteSelectedContent(jcb.getText());
//            result = result + jcb.getText() + ", ";
//          }
//        }
//      }
//      result = result + "deleted";
//      JOptionPane.showMessageDialog(null, result);
//    }
//  }
//  
//  public void deleteAll(JPanel panel)
//  {
//    if (panel == this.editCourse) {
//      this.course.deleteAllCourse();
//      JOptionPane.showMessageDialog(null, "All deleted");
//    } else if (panel == this.EditContentList)
//    {
//      for (String listHead : this.fc.getListMap().keySet()) {
//        this.content.deleteSelectedContent(listHead);
//      }
//      JOptionPane.showMessageDialog(null, "All deleted");
//    }
//  }
//  
//  public void dataAdd(JPanel panel)
//  {
//    if (panel == this.editCourse) {
//      String courseName = this.courseEditField.getText();
//      this.course.addCourse(courseName);
//    } else if (panel == this.EditContentList)
//    {
//      String cardName = this.getCardNameText.getText();
//      String cardAnswer = this.getCardDesText.getText();
//      this.content.addContent(cardName, cardAnswer, this.ContentEditCourseName.getText());
//    }
//  }
//  
//  private JButton contentDeleteAllBtn;
//  private JButton contentDeleteSelectedBtn;
//  
//  public void reset()
//  {
//    this.backListBtn.setVisible(false);
//    this.jProgressBar1.setVisible(true);
//    this.nextCardBtn.setVisible(true);
//    this.flipAnswerBtn.setVisible(true);
//    this.mainPage.setVisible(false);
//    this.editCourse.setVisible(false);
//    this.editList.setVisible(false);
//    this.cardView.setVisible(false);
//    this.listView.setVisible(false);
//    this.courseEditPanel.setVisible(false);
//    this.ListEditPanel.setVisible(false);
//    this.jScrollPane3.setVisible(false);
//    this.jScrollPane4.setVisible(false);
//    this.fc.resetCounter();
//    this.cardBottomLabel.setText("");
//    this.cardTopLabel.setText("");
//  }
//  
//  private JButton courseAddBtn;
//  private JPanel courseConfirmList;
//  private JButton courseDeleteAllBtn;
//  private JButton courseDeleteSelectedBtn;
//  private JTextField courseEditField;
//  private JPanel courseEditPanel;
//  
//  private void initComponents()
//  {
//    this.jLayeredPane1 = new JLayeredPane();
//    this.mainPage = new JPanel();
//    this.listView = new JPanel();
//    this.jScrollPane2 = new JScrollPane();
//    this.listPanel = new JPanel();
//    this.randomStarter = new JButton();
//    this.cardView = new JPanel();
//    this.cardBottomLabel = new JLabel();
//    this.cardTopLabel = new JLabel();
//    this.backgroupLabel = new JLabel();
//    this.flipAnswerBtn = new JButton();
//    this.nextCardBtn = new JButton();
//    this.jProgressBar1 = new JProgressBar();
//    this.backListBtn = new JButton();
//    this.editList = new JPanel();
//    this.ListEditPanel = new JPanel();
//    this.contentDeleteAllBtn = new JButton();
//    this.contentDeleteSelectedBtn = new JButton();
//    this.contentAdd = new JButton();
//    this.ContentSaveExit = new JButton();
//    this.getCardNameText = new JTextField();
//    this.getCardDesText = new JTextField();
//    this.jLabel1 = new JLabel();
//    this.jLabel2 = new JLabel();
//    this.jScrollPane4 = new JScrollPane();
//    this.EditContentList = new JPanel();
//    this.ContentEditCourseName = new JLabel();
//    this.courseEditPanel = new JPanel();
//    this.jScrollPane3 = new JScrollPane();
//    this.courseConfirmList = new JPanel();
//    this.editCourse = new JPanel();
//    this.jScrollPane1 = new JScrollPane();
//    this.editCourseListPanel = new JPanel();
//    this.courseDeleteAllBtn = new JButton();
//    this.courseDeleteSelectedBtn = new JButton();
//    this.courseEditReset = new JButton();
//    this.courseAddBtn = new JButton();
//    this.courseEditField = new JTextField();
//    this.jLabel3 = new JLabel();
//    this.home = new JButton();
//    this.editCourseBtn = new JButton();
//    this.editListBtn = new JButton();
//    
//    setDefaultCloseOperation(3);
//    setCursor(new java.awt.Cursor(0));
//    
//    this.mainPage.setBackground(Color.red);
//    this.mainPage.setPreferredSize(new Dimension(703, 452));
//    this.mainPage.setLayout(new GridLayout(10, 1, 1, 0));
//    
//    this.listView.setBackground(new Color(102, 102, 255));
//    this.listView.setPreferredSize(new Dimension(703, 452));
//    
//    this.jScrollPane2.setHorizontalScrollBarPolicy(31);
//    
//    this.listPanel.setLayout(new GridLayout(0, 1));
//    this.jScrollPane2.setViewportView(this.listPanel);
//    
//    this.randomStarter.setText("Start Random");
//    this.randomStarter.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.randomStarterActionPerformed(evt);
//      }
//      
//    });
//    GroupLayout listViewLayout = new GroupLayout(this.listView);
//    this.listView.setLayout(listViewLayout);
//    listViewLayout.setHorizontalGroup(listViewLayout
//      .createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addComponent(this.jScrollPane2)
//      .addGroup(listViewLayout.createSequentialGroup()
//      .addGap(232, 232, 232)
//      .addComponent(this.randomStarter, -2, 231, -2)
//      .addContainerGap(238, 32767)));
//    
//    listViewLayout.setVerticalGroup(listViewLayout
//      .createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(listViewLayout.createSequentialGroup()
//      .addGap(1, 1, 1)
//      .addComponent(this.jScrollPane2, -2, 395, -2)
//      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
//      .addComponent(this.randomStarter, -1, 34, 32767)
//      .addContainerGap()));
//    
//
//    this.cardView.setBackground(new Color(0, 255, 255));
//    this.cardView.setName("");
//    this.cardView.setPreferredSize(new Dimension(703, 452));
//    this.cardView.setLayout(null);
//    
//    this.cardBottomLabel.setFont(new java.awt.Font("Trebuchet MS", 0, 18));
//    this.cardBottomLabel.setHorizontalAlignment(0);
//    this.cardBottomLabel.setVerticalAlignment(1);
//    this.cardView.add(this.cardBottomLabel);
//    this.cardBottomLabel.setBounds(120, 240, 410, 130);
//    
//    this.cardTopLabel.setFont(new java.awt.Font("Trebuchet MS", 0, 18));
//    this.cardTopLabel.setHorizontalAlignment(0);
//    this.cardTopLabel.setVerticalAlignment(1);
//    this.cardTopLabel.setHorizontalTextPosition(0);
//    this.cardView.add(this.cardTopLabel);
//    this.cardTopLabel.setBounds(120, 140, 410, 80);
//    
//    this.backgroupLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/notecard/NotecardBack.jpg")));
//    this.cardView.add(this.backgroupLabel);
//    this.backgroupLabel.setBounds(60, 80, 500, 300);
//    
//    this.flipAnswerBtn.setText("Flip to Answer");
//    this.flipAnswerBtn.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.flipAnswerBtnActionPerformed(evt);
//      }
//    });
//    this.cardView.add(this.flipAnswerBtn);
//    this.flipAnswerBtn.setBounds(130, 400, 410, 30);
//    
//    this.nextCardBtn.setText("Next");
//    this.nextCardBtn.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.nextCardBtnActionPerformed(evt);
//      }
//    });
//    this.cardView.add(this.nextCardBtn);
//    this.nextCardBtn.setBounds(600, 210, 80, 60);
//    
//    this.jProgressBar1.setForeground(new Color(51, 255, 51));
//    this.cardView.add(this.jProgressBar1);
//    this.jProgressBar1.setBounds(150, 20, 410, 30);
//    
//    this.backListBtn.setText("Back to List");
//    this.backListBtn.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.backListBtnActionPerformed(evt);
//      }
//    });
//    this.cardView.add(this.backListBtn);
//    this.backListBtn.setBounds(10, 10, 110, 50);
//    
//    this.editList.setBackground(new Color(102, 255, 102));
//    this.editList.setPreferredSize(new Dimension(703, 452));
//    
//    this.ListEditPanel.setEnabled(false);
//    
//    this.contentDeleteAllBtn.setText("Delete All");
//    this.contentDeleteAllBtn.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.contentDeleteAllBtnActionPerformed(evt);
//      }
//      
//    });
//    this.contentDeleteSelectedBtn.setText("Delete Selected");
//    this.contentDeleteSelectedBtn.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.contentDeleteSelectedBtnActionPerformed(evt);
//      }
//      
//    });
//    this.contentAdd.setText("Add Card");
//    this.contentAdd.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.contentAddActionPerformed(evt);
//      }
//      
//    });
//    this.ContentSaveExit.setText("Cancel and Exit");
//    this.ContentSaveExit.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.ContentSaveExitActionPerformed(evt);
//      }
//      
//    });
//    this.getCardDesText.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.getCardDesTextActionPerformed(evt);
//      }
//      
//    });
//    this.jLabel1.setText("Card Head:");
//    
//    this.jLabel2.setText("Description:");
//    
//    this.jScrollPane4.setHorizontalScrollBarPolicy(31);
//    
//    this.EditContentList.setLayout(new GridLayout(0, 1));
//    this.jScrollPane4.setViewportView(this.EditContentList);
//    
//    GroupLayout ListEditPanelLayout = new GroupLayout(this.ListEditPanel);
//    this.ListEditPanel.setLayout(ListEditPanelLayout);
//    ListEditPanelLayout.setHorizontalGroup(ListEditPanelLayout
//      .createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(ListEditPanelLayout.createSequentialGroup()
//      .addContainerGap()
//      .addComponent(this.jScrollPane4, -2, 252, -2)
//      .addGroup(ListEditPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(ListEditPanelLayout.createSequentialGroup()
//      .addGap(57, 57, 57)
//      .addGroup(ListEditPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addComponent(this.jLabel1, -2, 69, -2)
//      .addComponent(this.jLabel2, -2, 69, -2))
//      .addGap(37, 37, 37)
//      .addGroup(ListEditPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addComponent(this.getCardNameText, -2, 244, -2)
//      .addComponent(this.getCardDesText, -2, 244, -2))
//      .addContainerGap(45, 32767))
//      .addGroup(ListEditPanelLayout.createSequentialGroup()
//      .addGroup(ListEditPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//      .addGroup(GroupLayout.Alignment.LEADING, ListEditPanelLayout.createSequentialGroup()
//      .addGap(35, 35, 35)
//      .addComponent(this.contentAdd, -2, 151, -2)
//      .addGap(0, 0, 32767))
//      .addGroup(ListEditPanelLayout.createSequentialGroup()
//      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
//      .addComponent(this.contentDeleteSelectedBtn, -2, 151, -2)))
//      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 87, 32767)
//      .addGroup(ListEditPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addComponent(this.ContentSaveExit, -2, 151, -2)
//      .addComponent(this.contentDeleteAllBtn, -2, 151, -2))
//      .addGap(23, 23, 23))
//      .addGroup(GroupLayout.Alignment.TRAILING, ListEditPanelLayout.createSequentialGroup()
//      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
//      .addComponent(this.ContentEditCourseName, -2, 116, -2)
//      .addGap(158, 158, 158)))));
//    
//    ListEditPanelLayout.setVerticalGroup(ListEditPanelLayout
//      .createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(GroupLayout.Alignment.TRAILING, ListEditPanelLayout.createSequentialGroup()
//      .addGroup(ListEditPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//      .addGroup(ListEditPanelLayout.createSequentialGroup()
//      .addContainerGap()
//      .addComponent(this.jScrollPane4))
//      .addGroup(GroupLayout.Alignment.LEADING, ListEditPanelLayout.createSequentialGroup()
//      .addGap(20, 20, 20)
//      .addGroup(ListEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//      .addComponent(this.contentDeleteAllBtn, -2, 50, -2)
//      .addComponent(this.contentDeleteSelectedBtn, -2, 50, -2))
//      .addGap(29, 29, 29)
//      .addComponent(this.ContentEditCourseName)
//      .addGap(30, 30, 30)
//      .addGroup(ListEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//      .addComponent(this.jLabel1)
//      .addComponent(this.getCardNameText, -2, 50, -2))
//      .addGroup(ListEditPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(ListEditPanelLayout.createSequentialGroup()
//      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 44, 32767)
//      .addComponent(this.getCardDesText, -2, 115, -2)
//      .addGap(18, 18, 18))
//      .addGroup(ListEditPanelLayout.createSequentialGroup()
//      .addGap(45, 45, 45)
//      .addComponent(this.jLabel2, -2, 24, -2)
//      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)))
//      .addGroup(ListEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//      .addComponent(this.contentAdd, -2, 46, -2)
//      .addComponent(this.ContentSaveExit, -2, 46, -2))))
//      .addGap(48, 48, 48)));
//    
//
//    this.courseConfirmList.setLayout(new GridLayout(0, 1));
//    this.jScrollPane3.setViewportView(this.courseConfirmList);
//    this.courseConfirmList.getAccessibleContext().setAccessibleParent(this.listView);
//    
//    GroupLayout courseEditPanelLayout = new GroupLayout(this.courseEditPanel);
//    this.courseEditPanel.setLayout(courseEditPanelLayout);
//    courseEditPanelLayout.setHorizontalGroup(courseEditPanelLayout
//      .createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(GroupLayout.Alignment.TRAILING, courseEditPanelLayout.createSequentialGroup()
//      .addContainerGap(139, 32767)
//      .addComponent(this.jScrollPane3, -2, 428, -2)
//      .addGap(134, 134, 134)));
//    
//    courseEditPanelLayout.setVerticalGroup(courseEditPanelLayout
//      .createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(courseEditPanelLayout.createSequentialGroup()
//      .addGap(40, 40, 40)
//      .addComponent(this.jScrollPane3, -2, 361, -2)
//      .addContainerGap(50, 32767)));
//    
//
//    GroupLayout editListLayout = new GroupLayout(this.editList);
//    this.editList.setLayout(editListLayout);
//    editListLayout.setHorizontalGroup(editListLayout
//      .createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(editListLayout.createSequentialGroup()
//      .addComponent(this.ListEditPanel, -2, -1, -2)
//      .addGap(0, 0, 32767))
//      .addGroup(editListLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(editListLayout.createSequentialGroup()
//      .addComponent(this.courseEditPanel, -2, -1, -2)
//      .addGap(0, 13, 32767))));
//    
//    editListLayout.setVerticalGroup(editListLayout
//      .createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(editListLayout.createSequentialGroup()
//      .addComponent(this.ListEditPanel, -2, -1, -2)
//      .addGap(0, 2, 32767))
//      .addGroup(editListLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(editListLayout.createSequentialGroup()
//      .addComponent(this.courseEditPanel, -2, -1, -2)
//      .addGap(0, 0, 32767))));
//    
//
//    this.editCourse.setBackground(new Color(255, 255, 0));
//    this.editCourse.setPreferredSize(new Dimension(703, 452));
//    
//    this.jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
//    this.jScrollPane1.setHorizontalScrollBarPolicy(31);
//    
//    this.editCourseListPanel.setLayout(new GridLayout(0, 1));
//    this.jScrollPane1.setViewportView(this.editCourseListPanel);
//    
//    this.courseDeleteAllBtn.setText("Delete All");
//    this.courseDeleteAllBtn.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.courseDeleteAllBtnActionPerformed(evt);
//      }
//      
//    });
//    this.courseDeleteSelectedBtn.setText("Delete Selected");
//    this.courseDeleteSelectedBtn.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.courseDeleteSelectedBtnActionPerformed(evt);
//      }
//      
//    });
//    this.courseEditReset.setText("Cancel and Exit");
//    this.courseEditReset.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.courseEditResetActionPerformed(evt);
//      }
//      
//    });
//    this.courseAddBtn.setText("Add Course");
//    this.courseAddBtn.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.courseAddBtnActionPerformed(evt);
//      }
//      
//    });
//    this.courseEditField.setToolTipText("");
//    this.courseEditField.setAutoscrolls(false);
//    
//    this.jLabel3.setText("course Name:");
//    
//    GroupLayout editCourseLayout = new GroupLayout(this.editCourse);
//    this.editCourse.setLayout(editCourseLayout);
//    editCourseLayout.setHorizontalGroup(editCourseLayout
//      .createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(editCourseLayout.createSequentialGroup()
//      .addComponent(this.jScrollPane1, -2, 183, -2)
//      .addGroup(editCourseLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//      .addGroup(editCourseLayout.createSequentialGroup()
//      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 63, 32767)
//      .addGroup(editCourseLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(GroupLayout.Alignment.TRAILING, editCourseLayout.createSequentialGroup()
//      .addComponent(this.courseDeleteSelectedBtn, -2, 123, -2)
//      .addGap(36, 36, 36)
//      .addComponent(this.courseDeleteAllBtn, -2, 123, -2))
//      .addComponent(this.courseEditReset, GroupLayout.Alignment.TRAILING, -2, 123, -2))
//      .addGap(175, 175, 175))
//      .addGroup(editCourseLayout.createSequentialGroup()
//      .addGap(28, 28, 28)
//      .addComponent(this.jLabel3)
//      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//      .addComponent(this.courseEditField, -2, 272, -2)
//      .addContainerGap(-1, 32767))))
//      .addGroup(editCourseLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(editCourseLayout.createSequentialGroup()
//      .addGap(256, 256, 256)
//      .addComponent(this.courseAddBtn, -2, 123, -2)
//      .addContainerGap(324, 32767))));
//    
//    editCourseLayout.setVerticalGroup(editCourseLayout
//      .createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addComponent(this.jScrollPane1)
//      .addGroup(editCourseLayout.createSequentialGroup()
//      .addContainerGap()
//      .addGroup(editCourseLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//      .addComponent(this.courseDeleteAllBtn, -2, 54, -2)
//      .addComponent(this.courseDeleteSelectedBtn, -2, 54, -2))
//      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 131, 32767)
//      .addGroup(editCourseLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//      .addComponent(this.courseEditField, -2, 70, -2)
//      .addComponent(this.jLabel3))
//      .addGap(53, 53, 53)
//      .addComponent(this.courseEditReset, -2, 54, -2)
//      .addGap(79, 79, 79))
//      .addGroup(editCourseLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(GroupLayout.Alignment.TRAILING, editCourseLayout.createSequentialGroup()
//      .addContainerGap(319, 32767)
//      .addComponent(this.courseAddBtn, -2, 54, -2)
//      .addGap(79, 79, 79))));
//    
//
//    this.home.setText("Home");
//    this.home.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.homeActionPerformed(evt);
//      }
//      
//    });
//    this.editCourseBtn.setText("Edit Course");
//    this.editCourseBtn.setMaximumSize(new Dimension(59, 23));
//    this.editCourseBtn.setMinimumSize(new Dimension(59, 23));
//    this.editCourseBtn.setPreferredSize(new Dimension(59, 23));
//    this.editCourseBtn.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.editCourseBtnActionPerformed(evt);
//      }
//      
//    });
//    this.editListBtn.setText("Edit Cards");
//    this.editListBtn.setMaximumSize(new Dimension(59, 23));
//    this.editListBtn.setMinimumSize(new Dimension(59, 23));
//    this.editListBtn.setPreferredSize(new Dimension(59, 23));
//    this.editListBtn.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent evt) {
//        app.this.editListBtnActionPerformed(evt);
//      }
//      
//    });
//    this.jLayeredPane1.setLayer(this.mainPage, JLayeredPane.DEFAULT_LAYER.intValue());
//    this.jLayeredPane1.setLayer(this.listView, JLayeredPane.DEFAULT_LAYER.intValue());
//    this.jLayeredPane1.setLayer(this.cardView, JLayeredPane.PALETTE_LAYER.intValue());
//    this.jLayeredPane1.setLayer(this.editList, JLayeredPane.DEFAULT_LAYER.intValue());
//    this.jLayeredPane1.setLayer(this.editCourse, JLayeredPane.DEFAULT_LAYER.intValue());
//    this.jLayeredPane1.setLayer(this.home, JLayeredPane.DEFAULT_LAYER.intValue());
//    this.jLayeredPane1.setLayer(this.editCourseBtn, JLayeredPane.DEFAULT_LAYER.intValue());
//    this.jLayeredPane1.setLayer(this.editListBtn, JLayeredPane.DEFAULT_LAYER.intValue());
//    
//    GroupLayout jLayeredPane1Layout = new GroupLayout(this.jLayeredPane1);
//    this.jLayeredPane1.setLayout(jLayeredPane1Layout);
//    jLayeredPane1Layout.setHorizontalGroup(jLayeredPane1Layout
//      .createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(jLayeredPane1Layout.createSequentialGroup()
//      .addComponent(this.home, -2, 106, -2)
//      .addGap(66, 66, 66)
//      .addComponent(this.editCourseBtn, -2, 106, -2)
//      .addGap(57, 57, 57)
//      .addComponent(this.editListBtn, -2, 106, -2)
//      .addGap(0, 263, 32767))
//      .addGroup(jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addComponent(this.mainPage, GroupLayout.Alignment.TRAILING, -2, -1, -2))
//      .addGroup(jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(jLayeredPane1Layout.createSequentialGroup()
//      .addComponent(this.listView, -2, 701, -2)
//      .addGap(0, 3, 32767)))
//      .addGroup(jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(jLayeredPane1Layout.createSequentialGroup()
//      .addComponent(this.cardView, -2, 701, -2)
//      .addGap(0, 3, 32767)))
//      .addGroup(jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addComponent(this.editList, -2, 704, 32767))
//      .addGroup(jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addComponent(this.editCourse, -1, 704, 32767)));
//    
//    jLayeredPane1Layout.setVerticalGroup(jLayeredPane1Layout
//      .createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(jLayeredPane1Layout.createSequentialGroup()
//      .addContainerGap()
//      .addGroup(jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
//      .addGroup(jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//      .addComponent(this.editCourseBtn, -1, 59, 32767)
//      .addComponent(this.editListBtn, -1, 59, 32767))
//      .addComponent(this.home, -1, -1, 32767))
//      .addContainerGap(483, 32767))
//      .addGroup(jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
//      .addGap(0, 101, 32767)
//      .addComponent(this.mainPage, -2, -1, -2)))
//      .addGroup(jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
//      .addGap(0, 101, 32767)
//      .addComponent(this.listView, -2, -1, -2)))
//      .addGroup(jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
//      .addGap(0, 101, 32767)
//      .addComponent(this.cardView, -2, -1, -2)))
//      .addGroup(jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
//      .addGap(0, 102, 32767)
//      .addComponent(this.editList, -2, 451, -2)))
//      .addGroup(jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//      .addGroup(GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
//      .addGap(0, 101, 32767)
//      .addComponent(this.editCourse, -2, -1, -2))));
//    
//
//    getContentPane().add(this.jLayeredPane1, "Center");
//    
//    pack();
//  }
//  
//  private void homeActionPerformed(ActionEvent evt)
//  {
//    controlPanel(2);
//    menu();
//  }
//  private JButton courseEditReset;
//  private JPanel editCourse;
//  
//  private void editCourseBtnActionPerformed(ActionEvent evt)
//  {
//    editMenu();
//    controlPanel(3);
//  }
//  
//  private JButton editCourseBtn;
//  
//  private void editListBtnActionPerformed(ActionEvent evt)
//  {
//    controlPanel(4);
//    editListCourseSide();
//  }
//  
//  private JPanel editCourseListPanel;
//  
//  private void randomStarterActionPerformed(ActionEvent evt)
//  {
//    controlPanel(5);
//    randomSelectedCourseStarter();
//  }
//  
//  private JPanel editList;
//  
//  private void contentDeleteAllBtnActionPerformed(ActionEvent evt)
//  {
//    deleteAll(this.EditContentList);
//  }
//  
//  private void getCardDesTextActionPerformed(ActionEvent evt) {}
//  
//  private JButton editListBtn;
//  private JButton flipAnswerBtn;
//  
//  private void nextCardBtnActionPerformed(ActionEvent evt)
//  {
//    nextCard();
//  }
//  
//  private JTextField getCardDesText;
//  private JTextField getCardNameText;
//  private JButton home;
//  
//  private void flipAnswerBtnActionPerformed(ActionEvent evt)
//  {
//    flipAnswer(this.fc.getCounter());
//  }
//  
//  private JLabel jLabel1;
//  
//  private void courseDeleteSelectedBtnActionPerformed(ActionEvent evt)
//  {
//    pickSelectedDelete(this.editCourseListPanel); }
//  
//  private JLabel jLabel2;
//  
//  private void courseDeleteAllBtnActionPerformed(ActionEvent evt) { deleteAll(this.editCourse); }
//  
//  private JLabel jLabel3;
//  private void courseAddBtnActionPerformed(ActionEvent evt) {
//    dataAdd(this.editCourse); }
//  
//
//  private void courseEditResetActionPerformed(ActionEvent evt) {
//    menu();
//    controlPanel(2);
//  }
//  
//  private void backListBtnActionPerformed(ActionEvent evt) {
//    controlPanel(1);
//  }
//  
//  private void contentDeleteSelectedBtnActionPerformed(ActionEvent evt) {
//    pickSelectedDelete(this.EditContentList);
//  }
//  
//  private void contentAddActionPerformed(ActionEvent evt) {
//    dataAdd(this.EditContentList);
//  }
//  
//  private void ContentSaveExitActionPerformed(ActionEvent evt) {
//    menu();
//    controlPanel(2); }
//  
//  private JLayeredPane jLayeredPane1;
//  private JProgressBar jProgressBar1;
//  private JScrollPane jScrollPane1;
//  private JScrollPane jScrollPane2;
//  private JScrollPane jScrollPane3;
//  private JScrollPane jScrollPane4;
//  private JPanel listPanel;
//  private JPanel listView;
//  private JPanel mainPage;
//  private JButton nextCardBtn;
//  private JButton randomStarter;
//  
//    /**
//     *
//     * @param args
//     */
//    public static void main(String args[]) {
//        if ("Nimbus".equals(info.getName())) {
//          javax.swing.UIManager.setLookAndFeel(info.getClassName());
//          break;
//        }
//      }
//    } catch (ClassNotFoundException ex) {
//      Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
//    } catch (InstantiationException ex) {
//      Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
//    } catch (IllegalAccessException ex) {
//      Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
//    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//      Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    
//
//
//    java.awt.EventQueue.invokeLater(new Runnable() {
//      public void run() {
//        new app().setVisible(true);
//      }
//    });
//  }
//}
//
// 