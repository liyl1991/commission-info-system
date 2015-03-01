package cn.haohao.vas.core.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public class ValidCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 147382725589041734L;

	public static String VALID_CODE_SESSION_KEY = "VALID_CODE_SESSION_KEY_122";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// 高度参数 默认20
		String h = request.getParameter("height");
		int height = 20;
		if (h != null && h.trim().length() > 0) {
			height = Integer.parseInt(h);
		}
		// 宽带参数 默认60
		String w = request.getParameter("width");
		int width = 60;
		if (w != null && w.trim().length() > 0) {
			width = Integer.parseInt(w);
		}
		// 长度参数 默认4
		String l = request.getParameter("length");
		int length = 4;
		if (l != null && l.trim().length() > 0) {
			length = Integer.parseInt(l);
		}

		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 在内存中创建图象

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// 画边框
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证码
		String sRand = "";
		for (int i = 0; i < length; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 16);
		}
		// 将认证码存入SESSION

		request.getSession().setAttribute(VALID_CODE_SESSION_KEY, sRand);
		// 图象生效
		g.dispose();
		OutputStream out = response.getOutputStream();

		request.setAttribute("gzip", "false");
		// 输出图象到页面
		ImageIO.write(image, "JPEG", out);
	}

	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		doPost(arg0, arg1);
	}

	private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public static String getValidCode(HttpSession session) {
		String validCode = (String) session.getAttribute(VALID_CODE_SESSION_KEY);
		if (validCode != null)
			session.removeAttribute(VALID_CODE_SESSION_KEY);
		return validCode;
	}
}
