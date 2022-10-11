package edu.java.ojdbc.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.java.ojdbc.controller.BlogDaoImpl;
import edu.java.ojdbc.model.Blog;
import edu.java.ojdbc.view.BlogCreateFrame.OnBlogInsertListener;

import static edu.java.ojdbc.model.Blog.Entity.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BlogMain implements OnBlogInsertListener {
    // 메인 화면에서 보여줄 JTable의 컬럼 이름들
    private static final String[] COLUMN_NAMES = {
            COL_BLOG_NO, COL_TITLE, COL_AUTHOR, COL_MODIFIED_DATE
    };
    
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    
    private BlogDaoImpl dao;
    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BlogMain window = new BlogMain();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public BlogMain() {
        initialize(); // UI 컴포넌트 생성, 초기화
        dao = BlogDaoImpl.getInstance();
        initializeTable(); // 메인 화면에서의 JTable 데이터 초기화
    }

    private void initializeTable() {
        // 데이터는 없는, 컬럼 이름들만 설정된 테이블 모델 객체를 생성. -> 데이터 초기화.
        model = new DefaultTableModel(null, COLUMN_NAMES);
        // 테이블에 모들 객체를 설정.
        table.setModel(model);

        // DB에서 데이터를 검색. 
        List<Blog> list = dao.select();
        for (Blog b : list) {
            Object[] row = {
                    b.getBlogNo(), b.getTitle(), b.getAuthor(), b.getModifiedDate()
            };
            model.addRow(row); // 테이블 모델에 행(row) 데이터로 추가.
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setTitle("블로그 메인");
        
        JPanel buttonPanel = new JPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        
        JButton btnCreate = new JButton("새 글 작성");
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 새 블로그 글 작성 Frame 만들기.
                BlogCreateFrame.newBlogCreateFrame(frame, BlogMain.this);
            }
        });
        btnCreate.setFont(new Font("D2Coding", Font.PLAIN, 24));
        buttonPanel.add(btnCreate);
        
        JButton btnRead = new JButton("상세보기");
        btnRead.setFont(new Font("D2Coding", Font.PLAIN, 24));
        buttonPanel.add(btnRead);
        
        JButton btnDelete = new JButton("삭제");
        btnDelete.setFont(new Font("D2Coding", Font.PLAIN, 24));
        buttonPanel.add(btnDelete);
        
        JScrollPane scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        scrollPane.setViewportView(table);
    }

    @Override // BlogCreateFrame.OnBlogInsertListener 인터페이스의 메서드 구현.
    public void onBlogInserted() {
        initializeTable();
    }
    
}
