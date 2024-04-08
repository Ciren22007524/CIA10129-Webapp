package com.product.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductJDBCDAOImpl implements ProductDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "SaiKou97607";

	private static final String INSERT_STMT = 
			"INSERT INTO product (pCatNo,pName,pInfo,pSize,pColor,pPrice,pStat,pSalQty,pComPeople,pComScore) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT pNo,pCatNo,pName,pInfo,pSize,pColor,pPrice,pStat,pSalQty,pComPeople,pComScore FROM product order by pNo";
	private static final String GET_ONE_STMT = 
			"SELECT pNo,pCatNo,pName,pInfo,pSize,pColor,pPrice,pStat,pSalQty,pComPeople,pComScore FROM product where pNo = ?";
	private static final String DELETE = 
			"DELETE FROM product where pNo = ?";
	private static final String UPDATE = 
			"UPDATE product set pCatNo=?, pName=?, pInfo=?, pSize=?, pColor=?, pPrice=?, pStat=?, pSalQty=?, pComPeople=?, pComScore=? where pNo = ?";

	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 類別載入器註冊JDBC連線
			Class.forName(driver);
			// 取得連線物件con
			con = DriverManager.getConnection(url, userid, passwd);
			// 取得PrepareStatement，執行預設SQL語法
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productVO.getpCatNo());
			pstmt.setString(2, productVO.getpName());
			pstmt.setString(3, productVO.getpInfo());
			pstmt.setInt(4, productVO.getpSize());
			pstmt.setString(5, productVO.getpColor());
			pstmt.setBigDecimal(6, productVO.getpPrice());
			pstmt.setByte(7, productVO.getpStat());
			pstmt.setInt(8, productVO.getpSalQty());
			pstmt.setInt(9, productVO.getpComPeople());
			pstmt.setInt(10, productVO.getpComScore());
//			pstmt.setInt(11, productVO.getpNo()); //Auto_increment

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, productVO.getpCatNo());
			pstmt.setString(2, productVO.getpName());
			pstmt.setString(3, productVO.getpInfo());
			pstmt.setInt(4, productVO.getpSize());
			pstmt.setString(5, productVO.getpColor());
			pstmt.setBigDecimal(6, productVO.getpPrice());
			pstmt.setByte(7, productVO.getpStat());
			pstmt.setInt(8, productVO.getpSalQty());
			pstmt.setInt(9, productVO.getpComPeople());
			pstmt.setInt(10, productVO.getpComScore());
			pstmt.setInt(11, productVO.getpNo());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer pNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ProductVO findByPrimaryKey(Integer pNo) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// productVo 也稱為 Domain objects
				productVO = new ProductVO();
				productVO.setpNo(rs.getInt("pNo"));
				productVO.setpCatNo(rs.getInt("pCatNo"));
				productVO.setpName(rs.getString("pName"));
				productVO.setpInfo(rs.getString("pInfo"));
				productVO.setpSize(rs.getInt("pSize"));
				productVO.setpColor(rs.getString("pColor"));
				productVO.setpPrice(rs.getBigDecimal("pPrice"));
				productVO.setpStat(rs.getByte("pStat"));
				productVO.setpSalQty(rs.getInt("pSalQty"));
				productVO.setpComPeople(rs.getInt("pComPeople"));
				productVO.setpComScore(rs.getInt("pComScore"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// productVo 也稱為 Domain objects
				productVO = new ProductVO();
				productVO.setpNo(rs.getInt("pNo"));
				productVO.setpCatNo(rs.getInt("pCatNo"));
				productVO.setpName(rs.getString("pName"));
				productVO.setpInfo(rs.getString("pInfo"));
				productVO.setpSize(rs.getInt("pSize"));
				productVO.setpColor(rs.getString("pColor"));
				productVO.setpPrice(rs.getBigDecimal("pPrice"));
				productVO.setpStat(rs.getByte("pStat"));
				productVO.setpSalQty(rs.getInt("pSalQty"));
				productVO.setpComPeople(rs.getInt("pComPeople"));
				productVO.setpComScore(rs.getInt("pComScore"));
				list.add(productVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		ProductJDBCDAOImpl dao = new ProductJDBCDAOImpl();

		// 新增
		ProductVO productVO1 = new ProductVO();
		productVO1.setpCatNo(Integer.valueOf(1644));
		productVO1.setpName("男士襯衫");
		productVO1.setpInfo("舒適且時尚的男士襯衫");
		productVO1.setpSize(Integer.valueOf(2));
		productVO1.setpColor("藍色");
		productVO1.setpPrice(new BigDecimal("30"));
		productVO1.setpStat(Byte.valueOf("1"));
		productVO1.setpSalQty(Integer.valueOf(1000));
		productVO1.setpComPeople(Integer.valueOf(500));
		productVO1.setpComScore(Integer.valueOf(2));
		dao.insert(productVO1);

		// 修改
		ProductVO productVO2 = new ProductVO();
		productVO2.setpCatNo(Integer.valueOf(1644));
		productVO2.setpName("男士襯衫");
		productVO2.setpInfo("舒適且時尚的男士襯衫");
		productVO2.setpSize(Integer.valueOf(2));
		productVO2.setpColor("藍色");
		productVO2.setpPrice(new BigDecimal("30"));
		productVO2.setpStat(Byte.valueOf("1"));
		productVO2.setpSalQty(Integer.valueOf(1000));
		productVO2.setpComPeople(Integer.valueOf(500));
		productVO2.setpComScore(Integer.valueOf(2));
		productVO2.setpNo(Integer.valueOf(1600000));
		dao.update(productVO2);

		// 刪除
		dao.delete(1645958);

		// 查詢
		ProductVO productVO3 = dao.findByPrimaryKey(1645958);
		System.out.print(productVO3.getpNo() + ",");
		System.out.print(productVO3.getpCatNo() + ",");
		System.out.print(productVO3.getpName() + ",");
		System.out.print(productVO3.getpInfo() + ",");
		System.out.print(productVO3.getpSize() + ",");
		System.out.print(productVO3.getpColor() + ",");
		System.out.println(productVO3.getpPrice() + ",");
		System.out.print(productVO3.getpStat() + ",");
		System.out.print(productVO3.getpSalQty() + ",");
		System.out.print(productVO3.getpComPeople() + ",");
		System.out.println(productVO3.getpComScore());
		System.out.println("---------------------");

		// 查詢
		List<ProductVO> list = dao.getAll();
		for (ProductVO aProduct : list) {
			System.out.print(aProduct.getpNo() + ",");
			System.out.print(aProduct.getpCatNo() + ",");
			System.out.print(aProduct.getpName() + ",");
			System.out.print(aProduct.getpInfo() + ",");
			System.out.print(aProduct.getpSize() + ",");
			System.out.print(aProduct.getpColor() + ",");
			System.out.print(aProduct.getpPrice() + ",");
			System.out.print(aProduct.getpStat() + ",");
			System.out.print(aProduct.getpSalQty() + ",");
			System.out.print(aProduct.getpComPeople() + ",");
			System.out.print(aProduct.getpComScore());
			System.out.println();
		}
	}
}
