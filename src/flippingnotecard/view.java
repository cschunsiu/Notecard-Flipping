/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flippingnotecard;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author cschunsiu
 */
public class view extends javax.swing.JFrame {
    Course course = new Course();
    Content content = new Content();
    flipcard fc = new flipcard();

    /**
     * Creates new form view
     */
    public view() {
        initComponents();
        setTitle("Flipping Note Card");
        menu();
    }
    
    //control View and reset view Visibility and elements
    public void controlPanel(int panelID)
    {
      reset();
      switch (panelID)
      {
      case 1: courseEdit.setVisible(true);
        break;
      case 2:  mainPage.setVisible(true);
        break;
      case 3:  courseListEdit.setVisible(true);
        break;
      case 4:  cardPage.setVisible(true);
        break;
      case 5:  courseListPage.setVisible(true);
      break;
      }
    }
    
    //generate mainPage as a menu
    public void menu()
    {
      ArrayList<String> arl = course.showCourse();
      mainCourse.removeAll();
      for (String courseName : arl) {
        JButton j = new JButton(courseName);
        j.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            courseList(courseName);
            controlPanel(5);
          }
        });
        mainCourse.add(j);
      }
      mainCourse.revalidate();
      mainCourse.repaint();
      reset();
      controlPanel(2);
    }
    
    //generate course content
    public void courseList(String courseName)
    {
      courseListPanel.removeAll();
      HashMap<String, String> hmList = content.showContent(courseName);
      for (String listHead : hmList.keySet()) {
        System.err.println(listHead);
        JButton j = new JButton(listHead);
        j.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            reset();
            controlPanel(4);
            cardTopLabel.setText(listHead);
            cardBottomLabel.setText((String)hmList.get(listHead));
            jProgressBar1.setVisible(false);
            nextCardBtn.setVisible(false);
            backListBtn.setVisible(true);
            flipAnswerBtn.setVisible(false);
          }
        });
        courseListPanel.add(j);
      }
      courseListPanel.revalidate();
      courseListPanel.repaint();
      fc.setMap(hmList);
    }
    
    //generate courses in a Edit Mode
    public void editMenu()
    {
      courseEditPanel.removeAll();
      ArrayList<String> arl = course.showCourse();
      for (String courseName : arl) {
        JCheckBox j = new JCheckBox(courseName);
        courseEditPanel.add(j);
      }
      courseEditPanel.revalidate();
      courseEditPanel.repaint();
    }
    
    //generate courses for content Mode
    public void editCourseListCourseSide()
    {
      courseConfirmPanel.setVisible(true);
      jScrollPane4.setVisible(true);
      ArrayList<String> arl = course.showCourse();
      courseConfirmList.removeAll();
      for (String courseName : arl) {
        JButton j = new JButton(courseName);
        j.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            courseConfirmPanel.setVisible(false);
            jScrollPane4.setVisible(false);
            ListEditPanel.setVisible(true);
            jScrollPane5.setVisible(true);
            editListSide(courseName);
          }
        });
        courseConfirmList.add(j);
      }
      courseConfirmList.revalidate();
      courseConfirmList.repaint();
    }
    
    //generate Course Lists in Edit Mode
    public void editListSide(String courseName)
    {
      HashMap<String, String> arl = content.showContent(courseName);
      EditContentList.removeAll();
      for (String ListHead : arl.keySet()) {
        JCheckBox j = new JCheckBox(ListHead);
        EditContentList.add(j);
      }
      ContentEditCourseName.setText(courseName);
      EditContentList.validate();
      EditContentList.repaint();
      fc.setMap(arl);
    }
    
    //randomize Hashmap position and provide the first result
    public void randomSelectedCourseStarter()
    {
      fc.initRandomListMap();
      ArrayList rSelectedList = fc.getIndexedMap();
      cardTopLabel.setText((String)rSelectedList.get(fc.getCounter()));
      jProgressBar1.setMaximum(rSelectedList.size());
      jProgressBar1.setValue(fc.getCounter() + 1);
    }
    
    //get to the next result in Random HashMap
    public void nextCard()
    {
      fc.setCounter();
      ArrayList e = fc.getIndexedMap();
      jProgressBar1.setValue(fc.getCounter() + 1);
      cardTopLabel.setText((String)e.get(fc.getCounter()));
      cardBottomLabel.setText("");
      if (fc.getCounter() == e.size() - 1) {
        nextCardBtn.setVisible(false);
      }
    }
    
    //get Hashmap value
    public void flipAnswer(int counter)
    {
      ArrayList rSelectedList = fc.getIndexedMap();
      cardTopLabel.setText((String)rSelectedList.get(fc.getCounter()));
      cardBottomLabel.setText(((String)fc.getListMap().get(rSelectedList.get(counter))).toString());
    }

    //get selected courses or content and delete
    public void pickSelectedDelete(JPanel panel)
    {
      String result = "";
      if (panel == courseEditPanel) {
        for (java.awt.Component c : panel.getComponents()) {
          if (c.getClass().equals(JCheckBox.class)) {
            JCheckBox jcb = (JCheckBox)c;
            if (jcb.isSelected()) {
              System.err.println(jcb.getText());
              course.deleteCourse(jcb.getText());
              result = result + jcb.getText() + ", ";
            }
          }
        }
        result = result + "deleted";
        JOptionPane.showMessageDialog(null, result);
        menu();
          controlPanel(2);
      } else if (panel == EditContentList)
      {
        for (java.awt.Component c : panel.getComponents()) {
          if (c.getClass().equals(JCheckBox.class)) {
            JCheckBox jcb = (JCheckBox)c;
            if (jcb.isSelected()) {
              System.err.println(jcb.getText());
              content.deleteSelectedContent(jcb.getText());
              result = result + jcb.getText() + ", ";
            }
          }
        }
        result = result + "deleted";
        JOptionPane.showMessageDialog(null, result);
        menu();
        controlPanel(2);
      }
    }

    //delete All courses and content or just content
    public void deleteAll(JPanel panel)
    {
      if (panel == courseEdit) {
        course.deleteAllCourse();
        JOptionPane.showMessageDialog(null, "All deleted");
        menu();
        controlPanel(2);
      } else if (panel == EditContentList)
      {
        for (String listHead : fc.getListMap().keySet()) {
          content.deleteSelectedContent(listHead);
        }
        JOptionPane.showMessageDialog(null, "All deleted");
        menu();
        controlPanel(2);
      }
    }

    //add course or content 
    public void dataAdd(JPanel panel)
    {
      if (panel == courseEdit) {
        String courseName = courseEditField.getText();
        course.addCourse(courseName);
        menu();
        controlPanel(2);
      } else if (panel == EditContentList)
      {
        String cardName = getCardNameText.getText();
        String cardAnswer = getCardDesText.getText();
        content.addContent(cardName, cardAnswer, ContentEditCourseName.getText());
        menu();
        controlPanel(2);
      }
    }
    
    //reset View or elements
    public void reset()
    {
      backListBtn.setVisible(false);
      jProgressBar1.setVisible(true);
      nextCardBtn.setVisible(true);
      flipAnswerBtn.setVisible(true);
      mainPage.setVisible(false);
      courseEdit.setVisible(false);
      courseListEdit.setVisible(false);
      cardPage.setVisible(false);
      courseListPage.setVisible(false);
      ListEditPanel.setVisible(false);
      jScrollPane4.setVisible(false);
      jScrollPane5.setVisible(false);
      courseConfirmPanel.setVisible(false);
      fc.resetCounter();
      cardBottomLabel.setText("");
      cardTopLabel.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        mainPage = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainCourse = new javax.swing.JPanel();
        courseEdit = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        courseEditPanel = new javax.swing.JPanel();
        courseDeleteSelectedBtn = new javax.swing.JButton();
        courseDeleteAllBtn = new javax.swing.JButton();
        courseEditField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        addCourseBtn = new javax.swing.JButton();
        SaveExitBtn = new javax.swing.JButton();
        courseListEdit = new javax.swing.JPanel();
        courseConfirmPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        courseConfirmList = new javax.swing.JPanel();
        ListEditPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        EditContentList = new javax.swing.JPanel();
        ContentEditCourseName = new javax.swing.JLabel();
        courseListDeleteAllBtn = new javax.swing.JButton();
        courseListDeleteSelectedBtn = new javax.swing.JButton();
        SaveExitBtn2 = new javax.swing.JButton();
        AddCardBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        getCardNameText = new javax.swing.JTextField();
        getCardDesText = new javax.swing.JTextField();
        cardPage = new javax.swing.JPanel();
        cardBottomLabel = new javax.swing.JLabel();
        cardTopLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        flipAnswerBtn = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        nextCardBtn = new javax.swing.JButton();
        backListBtn = new javax.swing.JButton();
        courseListPage = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        courseListPanel = new javax.swing.JPanel();
        randomStarterBtn = new javax.swing.JButton();
        homeBtn = new javax.swing.JButton();
        editCourseBtn = new javax.swing.JButton();
        editCourseListBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPage.setBackground(new java.awt.Color(204, 204, 204));

        mainCourse.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPane1.setViewportView(mainCourse);

        javax.swing.GroupLayout mainPageLayout = new javax.swing.GroupLayout(mainPage);
        mainPage.setLayout(mainPageLayout);
        mainPageLayout.setHorizontalGroup(
            mainPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
        );
        mainPageLayout.setVerticalGroup(
            mainPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );

        courseEdit.setBackground(new java.awt.Color(204, 204, 204));

        courseEditPanel.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPane3.setViewportView(courseEditPanel);

        courseDeleteSelectedBtn.setText("Delete Selected");
        courseDeleteSelectedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseDeleteSelectedBtnActionPerformed(evt);
            }
        });

        courseDeleteAllBtn.setText("Delete All");
        courseDeleteAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseDeleteAllBtnActionPerformed(evt);
            }
        });

        jLabel4.setText("Course Name:");

        addCourseBtn.setText("Add Course");
        addCourseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCourseBtnActionPerformed(evt);
            }
        });

        SaveExitBtn.setText("Save and Exit");
        SaveExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveExitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout courseEditLayout = new javax.swing.GroupLayout(courseEdit);
        courseEdit.setLayout(courseEditLayout);
        courseEditLayout.setHorizontalGroup(
            courseEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(courseEditLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(courseEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(courseEditLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(courseEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(courseDeleteSelectedBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addCourseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(courseEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(courseDeleteAllBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SaveExitBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(courseEditLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addComponent(courseEditField, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)))
                .addGap(0, 69, Short.MAX_VALUE))
        );
        courseEditLayout.setVerticalGroup(
            courseEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(courseEditLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(courseEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(courseDeleteAllBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(courseDeleteSelectedBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(123, 123, 123)
                .addGroup(courseEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(courseEditField, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(47, 47, 47)
                .addGroup(courseEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCourseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SaveExitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(135, Short.MAX_VALUE))
        );

        courseListEdit.setBackground(new java.awt.Color(153, 255, 153));

        courseConfirmList.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPane4.setViewportView(courseConfirmList);

        javax.swing.GroupLayout courseConfirmPanelLayout = new javax.swing.GroupLayout(courseConfirmPanel);
        courseConfirmPanel.setLayout(courseConfirmPanelLayout);
        courseConfirmPanelLayout.setHorizontalGroup(
            courseConfirmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(courseConfirmPanelLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );
        courseConfirmPanelLayout.setVerticalGroup(
            courseConfirmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(courseConfirmPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        EditContentList.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPane5.setViewportView(EditContentList);

        ContentEditCourseName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        courseListDeleteAllBtn.setText("Delete All");
        courseListDeleteAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseListDeleteAllBtnActionPerformed(evt);
            }
        });

        courseListDeleteSelectedBtn.setText("Delete Selected");
        courseListDeleteSelectedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseListDeleteSelectedBtnActionPerformed(evt);
            }
        });

        SaveExitBtn2.setText("Save and Exit");
        SaveExitBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveExitBtn2ActionPerformed(evt);
            }
        });

        AddCardBtn.setText("Add Card");
        AddCardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCardBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Card Name:");

        jLabel3.setText("Card Answer:");

        javax.swing.GroupLayout ListEditPanelLayout = new javax.swing.GroupLayout(ListEditPanel);
        ListEditPanel.setLayout(ListEditPanelLayout);
        ListEditPanelLayout.setHorizontalGroup(
            ListEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ListEditPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(ListEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ListEditPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addGroup(ListEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ListEditPanelLayout.createSequentialGroup()
                                .addComponent(ContentEditCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(225, 225, 225))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ListEditPanelLayout.createSequentialGroup()
                                .addGroup(ListEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(55, 55, 55)
                                .addGroup(ListEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(getCardDesText, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                    .addComponent(getCardNameText))
                                .addGap(135, 135, 135))))
                    .addGroup(ListEditPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(ListEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(AddCardBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(courseListDeleteSelectedBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ListEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(courseListDeleteAllBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SaveExitBtn2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(90, 90, 90))))
        );
        ListEditPanelLayout.setVerticalGroup(
            ListEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
            .addGroup(ListEditPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(ListEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(courseListDeleteAllBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(courseListDeleteSelectedBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(ContentEditCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addGroup(ListEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(getCardNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(ListEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ListEditPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3)
                        .addContainerGap(217, Short.MAX_VALUE))
                    .addGroup(ListEditPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(getCardDesText)
                        .addGap(18, 18, 18)
                        .addGroup(ListEditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SaveExitBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddCardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))))
        );

        javax.swing.GroupLayout courseListEditLayout = new javax.swing.GroupLayout(courseListEdit);
        courseListEdit.setLayout(courseListEditLayout);
        courseListEditLayout.setHorizontalGroup(
            courseListEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(courseConfirmPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(courseListEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(courseListEditLayout.createSequentialGroup()
                    .addComponent(ListEditPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        courseListEditLayout.setVerticalGroup(
            courseListEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(courseConfirmPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(courseListEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ListEditPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardPage.setBackground(new java.awt.Color(204, 204, 204));
        cardPage.setLayout(null);

        cardBottomLabel.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        cardPage.add(cardBottomLabel);
        cardBottomLabel.setBounds(280, 240, 400, 100);

        cardTopLabel.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        cardPage.add(cardTopLabel);
        cardTopLabel.setBounds(280, 130, 390, 80);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flippingnotecard/NotecardBack.jpg"))); // NOI18N
        cardPage.add(jLabel1);
        jLabel1.setBounds(210, 90, 500, 300);

        flipAnswerBtn.setText("Flip Card Answer");
        flipAnswerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flipAnswerBtnActionPerformed(evt);
            }
        });
        cardPage.add(flipAnswerBtn);
        flipAnswerBtn.setBounds(230, 420, 450, 30);
        cardPage.add(jProgressBar1);
        jProgressBar1.setBounds(240, 30, 440, 30);

        nextCardBtn.setText("Next");
        nextCardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextCardBtnActionPerformed(evt);
            }
        });
        cardPage.add(nextCardBtn);
        nextCardBtn.setBounds(780, 220, 110, 60);

        backListBtn.setText("Back");
        backListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backListBtnActionPerformed(evt);
            }
        });
        cardPage.add(backListBtn);
        backListBtn.setBounds(20, 10, 90, 60);

        courseListPage.setBackground(new java.awt.Color(204, 204, 204));

        courseListPanel.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPane2.setViewportView(courseListPanel);

        randomStarterBtn.setText("Random Start");
        randomStarterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomStarterBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout courseListPageLayout = new javax.swing.GroupLayout(courseListPage);
        courseListPage.setLayout(courseListPageLayout);
        courseListPageLayout.setHorizontalGroup(
            courseListPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, courseListPageLayout.createSequentialGroup()
                .addContainerGap(229, Short.MAX_VALUE)
                .addComponent(randomStarterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(222, 222, 222))
        );
        courseListPageLayout.setVerticalGroup(
            courseListPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(courseListPageLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(randomStarterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jLayeredPane1.setLayer(mainPage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(courseEdit, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(courseListEdit, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cardPage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(courseListPage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(courseEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(courseListEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addComponent(cardPage, javax.swing.GroupLayout.PREFERRED_SIZE, 958, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 1, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(courseListPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(courseEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(courseListEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(cardPage, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(courseListPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        homeBtn.setText("Home");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });

        editCourseBtn.setText("Edit Course");
        editCourseBtn.setMaximumSize(new java.awt.Dimension(59, 23));
        editCourseBtn.setMinimumSize(new java.awt.Dimension(59, 23));
        editCourseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCourseBtnActionPerformed(evt);
            }
        });

        editCourseListBtn.setText("Edit Course List");
        editCourseListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCourseListBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(homeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addComponent(editCourseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editCourseListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editCourseListBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editCourseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        reset();
        controlPanel(2);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void editCourseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCourseBtnActionPerformed
        reset();
        controlPanel(1);
        editMenu();
    }//GEN-LAST:event_editCourseBtnActionPerformed

    private void nextCardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextCardBtnActionPerformed
        nextCard();
    }//GEN-LAST:event_nextCardBtnActionPerformed

    private void editCourseListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCourseListBtnActionPerformed
        reset();
        controlPanel(3);
        editCourseListCourseSide();
    }//GEN-LAST:event_editCourseListBtnActionPerformed

    private void randomStarterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomStarterBtnActionPerformed
        controlPanel(4);
        randomSelectedCourseStarter();
    }//GEN-LAST:event_randomStarterBtnActionPerformed

    private void flipAnswerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flipAnswerBtnActionPerformed
        flipAnswer(fc.getCounter());
    }//GEN-LAST:event_flipAnswerBtnActionPerformed

    private void courseDeleteSelectedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseDeleteSelectedBtnActionPerformed
        pickSelectedDelete(courseEditPanel);
    }//GEN-LAST:event_courseDeleteSelectedBtnActionPerformed

    private void courseDeleteAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseDeleteAllBtnActionPerformed
        deleteAll(courseEdit);
    }//GEN-LAST:event_courseDeleteAllBtnActionPerformed

    private void addCourseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCourseBtnActionPerformed
        dataAdd(courseEdit);
    }//GEN-LAST:event_addCourseBtnActionPerformed

    private void courseListDeleteSelectedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseListDeleteSelectedBtnActionPerformed
        pickSelectedDelete(EditContentList);
    }//GEN-LAST:event_courseListDeleteSelectedBtnActionPerformed

    private void courseListDeleteAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseListDeleteAllBtnActionPerformed
        deleteAll(EditContentList);
    }//GEN-LAST:event_courseListDeleteAllBtnActionPerformed

    private void AddCardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCardBtnActionPerformed
        dataAdd(EditContentList);
    }//GEN-LAST:event_AddCardBtnActionPerformed

    private void SaveExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveExitBtnActionPerformed
        menu();
        controlPanel(2);
    }//GEN-LAST:event_SaveExitBtnActionPerformed

    private void SaveExitBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveExitBtn2ActionPerformed
       menu();
       controlPanel(2);
    }//GEN-LAST:event_SaveExitBtn2ActionPerformed

    private void backListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backListBtnActionPerformed
        controlPanel(5);
    }//GEN-LAST:event_backListBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddCardBtn;
    private javax.swing.JLabel ContentEditCourseName;
    private javax.swing.JPanel EditContentList;
    private javax.swing.JPanel ListEditPanel;
    private javax.swing.JButton SaveExitBtn;
    private javax.swing.JButton SaveExitBtn2;
    private javax.swing.JButton addCourseBtn;
    private javax.swing.JButton backListBtn;
    private javax.swing.JLabel cardBottomLabel;
    private javax.swing.JPanel cardPage;
    private javax.swing.JLabel cardTopLabel;
    private javax.swing.JPanel courseConfirmList;
    private javax.swing.JPanel courseConfirmPanel;
    private javax.swing.JButton courseDeleteAllBtn;
    private javax.swing.JButton courseDeleteSelectedBtn;
    private javax.swing.JPanel courseEdit;
    private javax.swing.JTextField courseEditField;
    private javax.swing.JPanel courseEditPanel;
    private javax.swing.JButton courseListDeleteAllBtn;
    private javax.swing.JButton courseListDeleteSelectedBtn;
    private javax.swing.JPanel courseListEdit;
    private javax.swing.JPanel courseListPage;
    private javax.swing.JPanel courseListPanel;
    private javax.swing.JButton editCourseBtn;
    private javax.swing.JButton editCourseListBtn;
    private javax.swing.JButton flipAnswerBtn;
    private javax.swing.JTextField getCardDesText;
    private javax.swing.JTextField getCardNameText;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel mainCourse;
    private javax.swing.JPanel mainPage;
    private javax.swing.JButton nextCardBtn;
    private javax.swing.JButton randomStarterBtn;
    // End of variables declaration//GEN-END:variables
}
